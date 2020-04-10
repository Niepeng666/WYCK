package com.linglingyi.com.viewone.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.model.ItemModel;
import com.linglingyi.com.utils.GlideUtils;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.viewone.WarpLinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @作者 chenlanxin
 * @创建日期 2019/5/8 15:56
 * @功能
 **/
public class BuyGoodsDialog extends DialogFragment {


    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.iv_goods_img)
    ImageView ivGoodsImg;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_stack)
    TextView tvStack;
    @BindView(R.id.tv_size)
    TextView tvSize;
    @BindView(R.id.warp_layout)
    WarpLinearLayout warpLayout;
    @BindView(R.id.tv_item_sub)
    Button tvItemSub;
    @BindView(R.id.tv_edit_num)
    TextView tvEditNum;
    @BindView(R.id.tv_item_plus)
    Button tvItemPlus;
    @BindView(R.id.bt_exchange)
    Button btExchange;
    Unbinder unbinder;
    private ItemModel goodsModel;
    private Context context;
    private TextView lastView = null;
    private String size = "";
    private OnButtonClickListener onButtonClickListener;
    private int stock = 1;

    public static BuyGoodsDialog getIntence(ItemModel goodsModel) {
        BuyGoodsDialog dialog = new BuyGoodsDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("goodsModel", goodsModel);
        dialog.setArguments(bundle);
        return dialog;
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.MyProgressDialog);
        setCancelable(false);
        context = getActivity();
        this.goodsModel = (ItemModel) getArguments().getSerializable("goodsModel");
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            Window window = getDialog().getWindow();
            window.setLayout(dm.widthPixels, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.ActionSheetDialogAnimation);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_buy_goods, container);
        unbinder = ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {
        String type = goodsModel.getType();
//        if (type.equals("1")) {//全积分
//            tvPrice.setText(goodsModel.getPoint() + "积分");
//        } else if (type.equals("2")) {//积分+金额
//            tvPrice.setText(goodsModel.getPoint() + "积分" + goodsModel.getPrice() + "元");
//        }
        tvPrice.setText(goodsModel.getPrice() + "元");
        tvStack.setText("库存：" + goodsModel.getInventory());
        addLabels(goodsModel.getSpecifications());
        GlideUtils.loadImage(context, goodsModel.getImage(), ivGoodsImg);
    }

    private void addLabels(List<String> list) {
        warpLayout.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            TextView textView = new TextView(context);
            textView.setHeight(context.getResources().getDimensionPixelSize(R.dimen.dp_28));
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(context.getResources().getDimensionPixelSize(R.dimen.dp_20), 0,
                    context.getResources().getDimensionPixelSize(R.dimen.dp_20), 0);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimensionPixelSize(R.dimen.sp_14));
            textView.setMaxLines(1);
            textView.setText(list.get(i));
            textView.setTag(list.get(i));
            textView.setTextColor(ContextCompat.getColor(context, R.color.black));
            textView.setBackground(getResources().getDrawable(R.drawable.goods_label));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView tv = (TextView) view;
                    size = (String) tv.getTag();
                    if (lastView != null && lastView != tv) {
                        lastView.setTextColor(Color.BLACK);
                        lastView.setBackground(ContextCompat.getDrawable(context, R.drawable.goods_label));
                    }
                    tvSize.setText("已选: “" + size + "”");
                    tv.setTextColor(ContextCompat.getColor(context, R.color.red));
                    tv.setBackground(ContextCompat.getDrawable(context, R.drawable.goods_label_select));
                    lastView = (TextView) view;
                }
            });
            warpLayout.addView(textView);
        }
    }

    @OnClick({R.id.iv_close, R.id.bt_exchange, R.id.tv_item_sub, R.id.tv_item_plus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.bt_exchange:
                if (StringUtil.isEmpty(size)) {
                    ViewUtils.makeToast(context, "请先选规格", 500);
                    return;
                }
                goodsModel.setSize(size);
                goodsModel.setGoodsCount(stock);
                dismiss();
                onButtonClickListener.onButtonClick(goodsModel);
                break;
            case R.id.tv_item_sub:
                // TODO: 2019/10/16 减数量
                if (stock == 1) {
                    ViewUtils.makeToast(context, "数量最少为1", 500);
                    return;
                }
                stock--;
                tvEditNum.setText(stock + "");
                break;
            case R.id.tv_item_plus:
                // TODO: 2019/10/16 加数量
                if (stock >= goodsModel.getInventory()) {
                    ViewUtils.makeToast(context, "数量已超过最大库存", 500);
                    return;
                }
                stock++;
                tvEditNum.setText(stock + "");
                break;
        }
    }


    public interface OnButtonClickListener {
        void onButtonClick(ItemModel model);
    }
}
