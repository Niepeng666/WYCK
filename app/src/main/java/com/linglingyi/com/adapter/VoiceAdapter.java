package com.linglingyi.com.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.linglingyi.com.activity.VoiceActivity;
import com.linglingyi.com.callback.CancelCallback;
import com.linglingyi.com.entity.VoiceEntity;
import com.linglingyi.com.model.Area;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.model.IndustryModel;
import com.linglingyi.com.model.PreviewPlanModel;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.DateUtil;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.Utils;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.dialog.PreviewPlanDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.wuyouchuangke.com.R;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author dyx
 * @date on 2019/4/23
 * @describe
 */
public class VoiceAdapter extends BaseMultiItemQuickAdapter<VoiceEntity, VoiceAdapter.MyViewHolder> {
    public static String[] colorString = new String[]{
            "金额",
            "开始时间",
            "结束时间",
            "地区",
            "年",
            "月"
    };
    private static String[][] filterString = new String[][]{
            new String[]{"年", "-"},
            new String[]{"月", "-"},
            new String[]{"号", ""},
            new String[]{"金额", ""},
            new String[]{"地区", ""},
            new String[]{"。", ""},
            new String[]{"开始时间", ""},
            new String[]{"结束时间", ""},
            new String[]{"万", "0000"}
    };
    private static Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*");
    private PreviewPlanModel previewPlanModel;
    private BindCard mBindCard;
    private String merchanNo;

    public VoiceAdapter(List<VoiceEntity> data, BindCard bindCard) {
        super(data);
        mBindCard = bindCard;
        addItemType(VoiceEntity.LEFT_TYPE_NOTICE, R.layout.cell_voice_notice);
        addItemType(VoiceEntity.LEFT_YTPE_CONTENT, R.layout.cell_voice_content);
        addItemType(VoiceEntity.RIGHT_TYPE, R.layout.cell_voice_input);
    }

    public static String dealString(String string) {
        String content = string;
        for (int i = 0; i < filterString.length; i++) {
            content = content.replace(filterString[i][0], filterString[i][1]);
        }
        return content;
    }


    public void setPreviewPlanModel(PreviewPlanModel previewPlanModel) {
        this.previewPlanModel = previewPlanModel;
    }

    public void setMerchanNo(String merchanNo) {
        this.merchanNo = merchanNo;
    }

    @Override
    protected void convert(VoiceAdapter.MyViewHolder helper, VoiceEntity item) {
        switch (helper.getItemViewType()) {
            case VoiceEntity.LEFT_TYPE_NOTICE:
                helper.setText(R.id.tv_content, getSpanString(item.getInputContent()));
                break;
            case VoiceEntity.LEFT_YTPE_CONTENT:
                fillPlan(helper, item);
                break;
            case VoiceEntity.RIGHT_TYPE:
                helper.setText(R.id.tv_content, item.getInputContent());
                break;
            default:
                break;
        }
    }

    public CharSequence getSpanString(String inputContent) {
        SpannableString spannableString = new SpannableString(inputContent);
        for (String str : colorString) {
            int i = 1;
            while (inputContent.indexOf(str, i) >= 0) {
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#FA0000"));
                spannableString.setSpan(colorSpan, inputContent.indexOf(str, i), inputContent.indexOf(str, i) + str.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                i++;
            }
        }
        return spannableString;
    }
    /**
     * 填充计划详情
     */
    private void fillPlan(VoiceAdapter.MyViewHolder helper, VoiceEntity item) {
        helper.setGone(R.id.ll_amount, !StringUtil.isEmpty(item.getMoney()));
        helper.setGone(R.id.ll_time, !StringUtil.isEmpty(item.getStartTime()));
        helper.setGone(R.id.ll_fare, false);
        helper.setGone(R.id.ll_area, !StringUtil.isEmpty(item.getArea()));
        helper.setGone(R.id.tv_save, false);

//        if (!StringUtil.isEmpty(item.getMoney())) {
//            helper.setGone(R.id.ll_amount, true);
//        }
        helper.setText(R.id.tv_money, item.getMoney());
//        if (!StringUtil.isEmpty(item.getStartTime())) {
//            helper.setGone(R.id.ll_time, true);
//        }
        helper.setText(R.id.tv_start, item.getStartTime());
//        if (!StringUtil.isEmpty(item.getEndTime())) {
//            helper.setGone(R.id.ll_time, true);
//
//        }
        helper.setText(R.id.tv_end, item.getEndTime());
//        if (!StringUtil.isEmpty(item.getArea())) {
//            helper.setGone(R.id.ll_area, true);
//
//        }
        helper.setText(R.id.tv_address, item.getArea());

        helper.setGone(R.id.cl_parent, hasInput(item));//没有内容输入，隐藏计划栏
        if (isComplete(item)) {//输入完整，显示手续费和保存计划
            // TODO: 2019/5/8
            requestData(item, helper);
        }
    }


    /**
     * 计算周转金
     *
     * @param item
     * @param helper
     */
    private void requestData(final VoiceEntity item, final MyViewHolder helper) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "193000");
        httpParams.put("7", "2");
        httpParams.put("8", item.getMoney());
        httpParams.put("9", "2");
        String allDateBetweenTwoDate = DateUtil.getDateBetweenTwoDate(item.getStartTime(), item.getEndTime());
        httpParams.put("10", allDateBetweenTwoDate);
        httpParams.put("11", mBindCard.getBANK_ACCOUNT());
        httpParams.put("12", mBindCard.getID());
        httpParams.put("43", mBindCard.getAcqCode());
        httpParams.put("42", merchanNo);
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(mContext, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {

                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {

                    helper.setGone(R.id.ll_fare, true);
//                    helper.setGone(R.id.tv_save, true);
                    item.setComplete(true);
                    // : 2019/5/9 滑动至最后一行
                    getRecyclerView().scrollToPosition(mData.size() - 1);


                    BigDecimal fee = CommonUtils.formatNewWithScale(model.getStr9(), 2);
                    BigDecimal timesFee = CommonUtils.formatNewWithScale(model.getStr7(), 2);
                    //需要预留的金额
                    BigDecimal workingFund = CommonUtils.formatNewWithScale(model.getStr40(), 2);
                    BigDecimal totalFee = workingFund.add(fee).add(timesFee);

                    helper.setText(R.id.tv_fare, String.valueOf(fee));

                    previewPlanModel.setWorkingFund(workingFund.toString());
                    previewPlanModel.setTimesFee(timesFee.toString());
                    previewPlanModel.setFee(fee.toString());
                    previewPlanModel.setStartDate(item.getStartTime());
                    previewPlanModel.setEndDate(item.getEndTime());
                    previewPlanModel.setDayTimes("2");
                    previewPlanModel.setAcqcode(mBindCard.getAcqCode());
                    previewPlanModel.setF57(model.getStr57());
                    previewPlanModel.setRepayAmount(CommonUtils.formatNewWithScale(item.getMoney(), 2).toString());
                    previewPlanModel.setTotalFee(totalFee.toString());
                    previewPlanModel.setTotalServiceFee(fee.add(timesFee).toString());

//                    setPreviewPlanModel(previewPlanModel);
                    showPreviewPlanDialg();
                } else {
                    ViewUtils.makeToast(mContext, model.getStr39(), 2000);
                }
            }
        });
    }

    private void showPreviewPlanDialg() {
        PreviewPlanDialog previewPlanDialog = new PreviewPlanDialog();
        previewPlanDialog.setBindCard(mBindCard).setData(previewPlanModel).show(((VoiceActivity) mContext).getSupportFragmentManager(), "preview");
        previewPlanDialog.setCancelCallback(new CancelCallback() {
            @Override
            public void cancel() {
                getData().clear();
                addData(new VoiceEntity(VoiceEntity.LEFT_TYPE_NOTICE, Constant.VOICE_NOTICE));
                addData(new VoiceEntity().setItemType(VoiceEntity.LEFT_TYPE_NOTICE).setInputContent("请输入还款金额，例：\n金额2000"));
                addData(new VoiceEntity().setItemType(VoiceEntity.LEFT_YTPE_CONTENT));

                VoiceAdapter.this.notifyDataSetChanged();
            }
        });
    }

    /**
     * 判断是否有正确的计划内容输入
     *
     * @return true 有正确的计划内容输入  false 无正确的计划内容输入
     */
    private boolean hasInput(VoiceEntity item) {
        if (StringUtil.isEmpty(item.getMoney()) && StringUtil.isEmpty(item.getStartTime()) && StringUtil.isEmpty(item.getEndTime()) && StringUtil.isEmpty(item.getArea())) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isEmpty(TextView textView) {
        String content = textView.getText().toString();
        if (StringUtil.isEmpty(content)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 计划内容输入是否完整
     *
     * @param item
     * @return true 计划内容输入完整  false 计划内容输入不完整
     */
    private boolean isComplete(VoiceEntity item) {
        if (StringUtil.isEmpty(item.getMoney())) {
            return false;
        }
        if (StringUtil.isEmpty(item.getStartTime())) {
            return false;
        }
        if (StringUtil.isEmpty(item.getEndTime())) {
            return false;
        }
        if (StringUtil.isEmpty(item.getArea())) {
            return false;
        }
        String allDateBetweenTwoDate = DateUtil.getDateBetweenTwoDate(item.getStartTime(), item.getEndTime());
        LogUtils.i("allDateBetweenTwoDate=" + allDateBetweenTwoDate);
        if (StringUtil.isEmpty(allDateBetweenTwoDate)) {
            ViewUtils.makeToast(mContext, "开始时间或结束时间有误，请重新语音输入", 1500);
//            addData(new VoiceEntity().setItemType(VoiceEntity.LEFT_TYPE_NOTICE).setInputContent("开始时间或结束时间有误，请重新语音输入"));

            return false;
        }

        return true;
    }


    public void clearPlan(VoiceAdapter.MyViewHolder helper) {
        helper.tvMoney.setText("");
        helper.tvStart.setText("");
        helper.tvEnd.setText("");
        helper.tvAddress.setText("");

    }


    //判断字符串是不是数字
    private boolean isNunmber(String filterString) {
        if (StringUtil.isEmpty(filterString)) {
            return false;
        }
        Matcher isNum = NUMBER_PATTERN.matcher(filterString);
        if (isNum.matches()) {
            return true;
        }
        return false;
    }

    class MyViewHolder extends BaseViewHolder {
        @Nullable
        @BindView(R.id.tv_content)
        TextView tvContent;
        @Nullable
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @Nullable
        @BindView(R.id.ll_amount)
        LinearLayout llAmount;
        @Nullable
        @BindView(R.id.ll_time)
        LinearLayout llTime;
        @Nullable
        @BindView(R.id.tv_start)
        TextView tvStart;
        @Nullable
        @BindView(R.id.tv_end)
        TextView tvEnd;
        @Nullable
        @BindView(R.id.ll_fare)
        LinearLayout llFare;
        @Nullable
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @Nullable
        @BindView(R.id.ll_area)
        LinearLayout llArea;
        @Nullable
        @BindView(R.id.tv_save)
        TextView tvSave;
        @Nullable
        @BindView(R.id.ll_content)
        LinearLayout llContent;

        @Nullable
        @BindView(R.id.cl_parent)
        View clParent;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
