package com.linglingyi.com.utils;

import android.app.Activity;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

import java.util.List;

/**
 * 6.0权限管理
 */
public class PermissionsUtils {

    /**
     * 申请单个权限
     */
    public static void requestPermissionone(final Activity context, String permission) {

        XXPermissions.with(context)
                // 可设置被拒绝后继续申请，直到用户授权或者永久拒绝
                .constantRequest()
                .permission(permission)
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                        if (isAll) {
                            // ViewUtils.makeToast(context,"获取权限成功",1000);
                        } else {
                            ViewUtils.makeToast(context, "请开启权限！", 1000);
                        }
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        if (quick) {
                          /*  ViewUtils.makeToast(context, "请开启权限！", 1000);
                            //如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.gotoPermissionSettings(context);*/
                            ViewUtils.makeToast(context, "请开启所有权限！", 1000);
                            XXPermissions.gotoPermissionSettings(context);
                        } else {
                            ViewUtils.makeToast(context, "获取权限失败", 1000);
                        }
                    }
                });
    }
    /**
     * 申请某些的权限
     */
    public static void requestPermissionmust(final Activity context, String[] permission) {

        XXPermissions.with(context)
                // 可设置被拒绝后继续申请，直到用户授权或者永久拒绝
                .constantRequest()
                .permission(permission)
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                        if (isAll) {
                            // ViewUtils.makeToast(context,"获取权限成功",1000);
                        } else {
                            ViewUtils.makeToast(context, "请开启所有权限！", 1000);
                        }
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        if (quick) {
                            //如果是被永久拒绝就跳转到应用权限系统设置页面
                          /*  ViewUtils.makeToast(context, "请开启权限！", 1000);
                            XXPermissions.gotoPermissionSettings(context);*/
                            ViewUtils.makeToast(context, "请开启所有权限！", 1000);
                            XXPermissions.gotoPermissionSettings(context);
                        } else {
                            ViewUtils.makeToast(context, "获取权限失败", 1000);
                        }
                    }
                });
    }

    /**
     * 申请基本的权限
     */
    public static void requestPermissionselect(final Activity context) {

        XXPermissions.with(context)
                // 可设置被拒绝后继续申请，直到用户授权或者永久拒绝
                .constantRequest()
                // 支持请求6.0悬浮窗权限8.0请求安装权限
                //.permission(Permission.REQUEST_INSTALL_PACKAGES)
                // 不指定权限则自动获取清单中的危险权限
                .permission(Permission.CALL_PHONE, Permission.WRITE_EXTERNAL_STORAGE,
                        Permission.READ_EXTERNAL_STORAGE, Permission.CAMERA)
                //WRITE_EXTERNAL_STORAGE文件写权限，READ_EXTERNAL_STORAGE文件读权限，CAMERA相机相册权限
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                        if (isAll) {
                            // ViewUtils.makeToast(context,"获取权限成功",1000);
                        } else {
                            //ViewUtils.makeToast(context, "请开启权限！", 1000);
                        }
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        if (quick) {
                           /* //如果是被永久拒绝就跳转到应用权限系统设置页面
                            ViewUtils.makeToast(context, "请开启权限！", 1000);
                            XXPermissions.gotoPermissionSettings(context);*/
                        } else {
                            ViewUtils.makeToast(context, "获取权限失败", 1000);
                        }
                    }
                });
    }

    /**
     * 申请清单文件里所有敏感权限
     */
    public static void requestPermissionall(final Activity context) {

        XXPermissions.with(context)
                // 可设置被拒绝后继续申请，直到用户授权或者永久拒绝
                .constantRequest()
                // 支持请求6.0悬浮窗权限8.0请求安装权限
                //.permission(Permission.REQUEST_INSTALL_PACKAGES)
                // 不指定权限则自动获取清单中的危险权限
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                        if (isAll) {
                            // ViewUtils.makeToast(context,"获取权限成功",1000);
                        } else {
                            ViewUtils.makeToast(context, "请开启权限！", 1000);
                        }
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        if (quick) {
                            /*ViewUtils.makeToast(context, "请开启权限！", 1000);
                            //如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.gotoPermissionSettings(context);*/
                            ViewUtils.makeToast(context, "请开启所有权限！", 1000);
                            XXPermissions.gotoPermissionSettings(context);
                        } else {
                            ViewUtils.makeToast(context, "获取权限失败", 1000);
                        }
                    }
                });
    }

    /**
     * 检查某个权限是否全部授予了
     */
    public static boolean isHasPermission(final Activity context, String permission) {//
        if (XXPermissions.isHasPermission(context, permission)) {
            //ViewUtils.makeToast(context, "已经获取到权限，不需要再次申请了", 1000);
            return true;
        } else {
            return false;
            //ViewUtils.makeToast(context, "还没有获取到权限或者部分权限未授予", 1000);
        }
    }
    /**
     * 检查某些权限是否全部授予了
     */
    public static boolean isHasPermissionall(final Activity context, String[] permission) {//
        if (XXPermissions.isHasPermission(context, permission)) {
            //ViewUtils.makeToast(context, "已经获取到权限，不需要再次申请了", 1000);
            return true;
        } else {
            return false;
            //ViewUtils.makeToast(context, "还没有获取到权限或者部分权限未授予", 1000);
        }
    }
    /**
     * 跳转到权限设置界面
     *
     * @param
     */
    public static void gotoPermissionSettings(final Activity context) {//
        XXPermissions.gotoPermissionSettings(context);
    }
}
