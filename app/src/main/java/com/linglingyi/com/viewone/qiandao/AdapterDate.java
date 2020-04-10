package com.linglingyi.com.viewone.qiandao;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */

public class AdapterDate extends BaseAdapter {

    private Context context;
    private List<Integer> days = new ArrayList<>();
    //日历数据
    private List<Boolean> status = new ArrayList<>();
    //签到状态，实际应用中初始化签到状态可通过该字段传递
    private OnSignedSuccess onSignedSuccess;
    //签到成功的回调方法，相应的可自行添加签到失败时的回调方法

    public AdapterDate(Context context) {
        this.context = context;
        int maxDay = DateUtil.getCurrentMonthLastDay();//获取当月天数
        for (int i = 0; i < DateUtil.getFirstDayOfMonth() - 1; i++) {
            //DateUtil.getFirstDayOfMonth()获取当月第一天是星期几，星期日是第一天，依次类推
            days.add(0);
            //0代表需要隐藏的item
            status.add(false);
            //false代表为签到状态
        }
        for (int i = 0; i < maxDay; i++) {
            days.add(i + 1);
            //初始化日历数据
            status.add(false);
            //初始化日历签到状态
        }
    }

    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public Object getItem(int i) {
        return days.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_gv, null);
            viewHolder = new ViewHolder();
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv = (TextView) view.findViewById(R.id.tvWeek);
        viewHolder.rlItem = (RelativeLayout) view.findViewById(R.id.rlItem);
        viewHolder.ivStatus = (RelativeLayout) view.findViewById(R.id.ivStatus);
        viewHolder.qiandao = (TextView) view.findViewById(R.id.qiandao);
        viewHolder.tv.setText(days.get(i) + "");
        if (days.get(i) == 0) {
            viewHolder.rlItem.setVisibility(View.GONE);
        }
        if (status.get(i)) {
            viewHolder.tv.setTextColor(Color.WHITE);
            viewHolder.ivStatus.setBackgroundResource(R.drawable.shape_circle_blue);
            viewHolder.getQiandao().setText("已签");
            viewHolder.getQiandao().setTextColor(Color.parseColor("#ffdca3"));
        } else {
            viewHolder.tv.setTextColor(Color.parseColor("#666666"));
            viewHolder.ivStatus.setBackground(null);
        }
        return view;
    }

    //调用查看当前总共已签到的日期
    public void attendance(String[] str) {
        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < days.size(); j++) {
                LogUtil.i("AdapterDate",days.get(j)+"");
                if (str[i].equals(String.valueOf(days.get(j)))) {
                    status.set(j, true);
                    notifyDataSetChanged();
                }
            }
        }
    }

    //对签到按钮时传的日期进行判断，并刷新显示
    public void addAttendance(String str){
        LogUtil.i("AdapterDate","addAttendance--"+str+""+days.size());
        String substring = str.substring(0, 1);
        if (substring.equals("0")){
            str=substring.substring(1);
        }
        LogUtil.i("AdapterDate","addAttendance2--"+str+""+days.size());
        for (int j = 0; j < days.size(); j++) {
            LogUtil.i("AdapterDate",days.get(j)+"");
            if (str.equals(String.valueOf(days.get(j)))) {
                status.set(j, true);
                notifyDataSetChanged();
            }
        }
    }


    class ViewHolder {
        RelativeLayout rlItem;
        TextView tv, qiandao;
        RelativeLayout ivStatus;


        public RelativeLayout getRlItem() {
            return rlItem;
        }

        public void setRlItem(RelativeLayout rlItem) {
            this.rlItem = rlItem;
        }

        public TextView getTv() {
            return tv;
        }

        public void setTv(TextView tv) {
            this.tv = tv;
        }

        public TextView getQiandao() {
            return qiandao;
        }

        public void setQiandao(TextView qiandao) {
            this.qiandao = qiandao;
        }

        public RelativeLayout getIvStatus() {
            return ivStatus;
        }

        public void setIvStatus(RelativeLayout ivStatus) {
            this.ivStatus = ivStatus;
        }
    }

    public void setOnSignedSuccess(OnSignedSuccess onSignedSuccess) {
        this.onSignedSuccess = onSignedSuccess;
    }


    public void setUpdate() {
        notifyDataSetChanged();
    }
}
