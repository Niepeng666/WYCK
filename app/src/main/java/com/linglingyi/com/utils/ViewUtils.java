package com.linglingyi.com.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wuyouchuangke.com.R;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

import java.util.Timer;
import java.util.TimerTask;


public class ViewUtils {
    /**
     * 自定义吐司
     *
     * @param context  上下文
     * @param text     吐司内容
     * @param duration 显示时长
     */
    public static void makeToast01(Context context,
                                   String text, int duration) {
        Toast result = new Toast(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.toast_layout, null);
        TextView textView = (TextView) layout.findViewById(R.id.toast_text);
        textView.setText(text);
        result.setView(layout);
        result.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        result.setDuration(duration);
        result.show();
    }

    public static Dialog showChoseDialog01(Context context, boolean isCanceledOnTouchOutside,
                                           String msg, String cancelDes, String confrimDes,
                                           final OnChoseDialogClickCallback onChoseDialogClickCallback) {
        final Dialog dialog = new Dialog(context, R.style.MyProgressDialog);
        dialog.setContentView(R.layout.chose_dialoga);
        dialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
        Button confirmBt = (Button) dialog.findViewById(R.id.bt_cancelPlan);
        Button cancelBt = (Button) dialog.findViewById(R.id.bt_suspendCancel);
        TextView tv_dialogTitle = (TextView) dialog.findViewById(R.id.tv_dialogTitle);
        tv_dialogTitle.setText(msg);
        confirmBt.setText(confrimDes);
        cancelBt.setText(cancelDes);

        cancelBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onChoseDialogClickCallback != null) {
                    dialog.dismiss();
                    onChoseDialogClickCallback.clickCancel();
                } else {
                    dialog.dismiss();
                }
            }
        });
        confirmBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onChoseDialogClickCallback != null) {
                    dialog.dismiss();
                    onChoseDialogClickCallback.clickOk();
                } else {
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
        return dialog;
    }

    public static Dialog showValidatePhoto(Context context) {
        final Dialog dialog = new Dialog(context, R.style.MyProgressDialog);
        dialog.setContentView(R.layout.dialog_show_validate_photo);
        dialog.findViewById(R.id.ll_showValidatePhoto).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        return dialog;
    }


    public static Dialog showCvvPhoto(Context context) {
        final Dialog dialog = new Dialog(context, R.style.MyProgressDialog);
        dialog.setContentView(R.layout.dialog_show_cvv_photo);
        dialog.findViewById(R.id.ll_showValidatePhoto).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        return dialog;
    }

    /**
     * 自定义吐司
     *
     * @param context  上下文
     * @param text     吐司内容
     * @param duration 显示时长
     */
    public static void makeToast(Context context,
                                 String text, int duration) {
        /*Toast result = new Toast(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.toast_layout, null);
		TextView textView = (TextView) layout.findViewById(R.id.toast_text);
		textView.setText(text);
		result.setView(layout);
		result.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		result.setDuration(duration);
		result.show();*/

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_toast_layout, null);
        TextView chapterNameTV = (TextView) view.findViewById(R.id.chapterName);
        chapterNameTV.setText(text);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(duration);
        toast.setView(view);
        toast.show();
    }

    /**
     * 显示文字的 Toast,以及Intent跳转
     *
     * @param context
     * @param text
     * @param duration
     * @param intentClassName 不为空表示不关闭当前
     * @param intentType      为""表示不跳转
     */
    public static void makeToast2(final Activity context,
                                  CharSequence text, int duration,
                                  final Class<?> intentClassName, final String intentType) {
        if (!TextUtils.isEmpty(text)) {
            Toast result = new Toast(context);

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.toast_layout, null);
            TextView textView = (TextView) layout.findViewById(R.id.toast_text);
            textView.setText(text);

            result.setView(layout);
            result.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            result.setDuration(duration);
            result.show();
        }


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (null == intentClassName) {
                    context.finish();
                    return;
                }
                if (!StringUtil.isEmpty(intentType)) {
//                    Intent intent = context
//                            .getPackageManager()
//                            .getLaunchIntentForPackage(context.getPackageName());
                    Intent intent = new Intent(context, intentClassName);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
//                    ActivityManager.exit2();
                }
            }
        }, 1000);
    }


    public static void overridePendingTransitionCome(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    public static void overridePendingTransitionComeFinish(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        activity.finish();
    }

    public static void overridePendingTransitionBack(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    /**
     * 得到自定义的progressDialog
     *
     * @param context
     * @param msg
     * @return
     */
    public static Dialog createLoadingDialog(Context context, String msg, boolean isCancel) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.loading_dialog, null);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);
        // main.xml中的ImageView  
        CircleProgressBar img = (CircleProgressBar) v.findViewById(R.id.img);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);
        img.setColorSchemeResources(R.color.black);

        tipTextView.setText(msg);

        Dialog loadingDialog = new Dialog(context, R.style.transparentBackDialog);
        loadingDialog.setCancelable(isCancel);
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT));
        return loadingDialog;

    }


    /**
     * 选择提示框
     *
     * @param context                    上下文
     * @param isCanceledOnTouchOutside   是否可以点击其他区域
     * @param msg                        提示内容
     * @param onChoseDialogClickCallback 点击事件的回调
     * @param cancelVisibale             取消按键是否可视
     * @return
     */
    public static Dialog showChoseDialog(Context context, boolean isCanceledOnTouchOutside, String msg, int cancelVisibale,
                                         final OnChoseDialogClickCallback onChoseDialogClickCallback) {
        final Dialog dialog = new Dialog(context, R.style.MyProgressDialog);
        dialog.setContentView(R.layout.chose_dialog);
        dialog.setCanceledOnTouchOutside(false);
        Button confirmBt = (Button) dialog.findViewById(R.id.left_bt);
        Button cancleBt = (Button) dialog.findViewById(R.id.right_bt);
        dialog.findViewById(R.id.verticalbars_iv).setVisibility(cancelVisibale);
        cancleBt.setVisibility(cancelVisibale);
        ((TextView) dialog.findViewById(R.id.title_text)).setText(msg);
        cancleBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onChoseDialogClickCallback != null) {
                    dialog.dismiss();
                    onChoseDialogClickCallback.clickCancel();
                } else {
                    dialog.dismiss();
                }
            }
        });
        confirmBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onChoseDialogClickCallback != null) {
                    dialog.dismiss();
                    onChoseDialogClickCallback.clickOk();
                } else {
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
        return dialog;
    }

    /**
     * 选择提示框
     *
     * @param context                    上下文
     * @param isCanceledOnTouchOutside   是否可以点击其他区域
     * @param msg                        提示内容
     * @param onChoseDialogClickCallback 点击事件的回调
     * @param cancelDes                  取消按钮文字设置
     * @param confrimDes                 确定按钮文字设置
     * @return
     */
    public static Dialog showChoseDialog02(Context context, boolean isCanceledOnTouchOutside, String msg, String cancelDes, String confrimDes,
                                           final OnChoseDialogClickCallback onChoseDialogClickCallback) {
        final Dialog dialog = new Dialog(context, R.style.MyProgressDialog);
        dialog.setContentView(R.layout.chose_dialog);
        dialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);

        Button confirmBt = (Button) dialog.findViewById(R.id.left_bt);
        Button cancleBt = (Button) dialog.findViewById(R.id.right_bt);
        ((TextView) dialog.findViewById(R.id.title_text)).setText(msg);
        confirmBt.setText(confrimDes);
        cancleBt.setText(cancelDes);

        cancleBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onChoseDialogClickCallback != null) {
                    dialog.dismiss();
                    onChoseDialogClickCallback.clickCancel();
                } else {
                    dialog.dismiss();
                }
            }
        });
        confirmBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onChoseDialogClickCallback != null) {
                    dialog.dismiss();
                    onChoseDialogClickCallback.clickOk();
                } else {
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
        return dialog;
    }

    /**
     * 用于获取状态栏的高度。 使用Resource对象获取（推荐这种方式）
     *
     * @return 返回状态栏高度的像素值。
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static Dialog showTipDialog(Context context, String title, String content) {
        final Dialog dialog = new Dialog(context, R.style.MyProgressDialog);
        dialog.setContentView(R.layout.mainpage_dialog);
        TextView tv_dialogTitle = (TextView) dialog.findViewById(R.id.tv_dialogTitle);
        TextView tv_content = (TextView) dialog.findViewById(R.id.tv_content);
        Button bt_known = (Button) dialog.findViewById(R.id.bt_known);

        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content)) {
            tv_dialogTitle.setText(title);
            tv_content.setText(content);
        }

        dialog.setCanceledOnTouchOutside(true);
        bt_known.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        return dialog;
    }

    /**
     * 选择提示框
     *
     * @param context                    上下文
     * @param isCanceledOnTouchOutside   是否可以点击其他区域
     * @param msg                        提示内容
     * @param onChoseDialogClickCallback 点击事件的回调
     * @param cancelDes                  取消按钮文字设置
     * @param confrimDes                 确定按钮文字设置
     * @return
     */
    public static Dialog showPay(Context context, String msg, String cancelDes, String confrimDes,
                                 final OnChoseDialogClickCallback onChoseDialogClickCallback) {
        final Dialog dialog = new Dialog(context, R.style.MyProgressDialog);
        dialog.setContentView(R.layout.chose_dialog);
        dialog.setCanceledOnTouchOutside(false);

        Button confirmBt = (Button) dialog.findViewById(R.id.left_bt);
        Button cancleBt = (Button) dialog.findViewById(R.id.right_bt);
        ((TextView) dialog.findViewById(R.id.title_text)).setText(msg);
        confirmBt.setText(confrimDes);
        cancleBt.setText(cancelDes);

        cancleBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onChoseDialogClickCallback != null) {
                    dialog.dismiss();
                    onChoseDialogClickCallback.clickCancel();
                } else {
                    dialog.dismiss();
                }
            }
        });
        confirmBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onChoseDialogClickCallback != null) {
                    dialog.dismiss();
                    onChoseDialogClickCallback.clickOk();
                } else {
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
        return dialog;
    }


    public interface OnChoseDialogClickCallback {
        void clickOk();

        void clickCancel();
    }
}
