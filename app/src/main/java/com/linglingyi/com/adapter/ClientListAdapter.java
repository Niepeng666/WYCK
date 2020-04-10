package com.linglingyi.com.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.model.ClientModel;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.viewone.FontIconView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/7
 */
public class ClientListAdapter extends BaseQuickAdapter<ClientModel, ClientListAdapter.ViewHolder> {

    private String type;

    public ClientListAdapter(@Nullable List<ClientModel> data, String type) {
        super(R.layout.item_client_list, data);
        this.type = type;
    }

    @Override
    protected void convert(ViewHolder helper, ClientModel item) {
        helper.tvName.setText(item.getMerchantCnName());
        helper.tvCreateTime.setText(item.getCreateTime().substring(0, 10));
        if (!StringUtil.isEmpty(item.getLinkPhone())) {
            helper.tvPhone.setText("1".equals(item.getStatus()) ? item.getLinkPhone() : CommonUtils.translateShortNumber(item.getLinkPhone(), 3, 4));
        }
        if ("1".equals(item.getStatus())) {
            helper.iconPhone.setVisibility(View.VISIBLE);
        } else {
            helper.iconPhone.setVisibility(View.INVISIBLE);
        }
        if ("10A".equals(type)) {//直推间推
            if ("1".equals(item.getStatus())) {
                helper.ivLevel.setImageResource(R.drawable.iv_client_zjhy);
            } else {
                helper.ivLevel.setImageResource(R.drawable.iv_client_jjhy);
            }
        } else {
            switch (item.getLevel()) {
                case "1":
                    helper.ivLevel.setImageResource(R.drawable.putong_vip);
                    break;
                case "2":
                    helper.ivLevel.setImageResource(R.drawable.record_level_2);
                    break;
                case "3":
                    helper.ivLevel.setImageResource(R.drawable.gaoji_vip);
                    break;
                case "4":
                    helper.ivLevel.setImageResource(R.drawable.chuji_daili);
                    break;
                case "5":
                    helper.ivLevel.setImageResource(R.drawable.gaoji_daili);
                    break;
                case "6":
                    helper.ivLevel.setImageResource(R.drawable.zuanshi);
                    break;
                default:
                    break;
            }
        }
        helper.addOnClickListener(R.id.ly_phone);
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.iv_level)
        ImageView ivLevel;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.tv_create_time)
        TextView tvCreateTime;
        @BindView(R.id.icon_phone)
        FontIconView iconPhone;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
