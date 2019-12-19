package com.outsourcing.llxpb.util.permission;

import android.content.Context;
import android.widget.Toast;

import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;

import java.util.List;

/**
 * Created by Hfengxiang on 2017/12/14.
 */
public class PermissionUtils {
    public static void requestPermissions(final Context context, final PermissionCallBack callBack, String... permissions) {
        Acp.getInstance(context).request(new AcpOptions.Builder()
                        .setPermissions(permissions)
                        .setDeniedMessage("您拒绝" + getPermissionString(permissions) + "权限申请,此功能将不能正常使用,您可以去设置页面重新授权!")
                        .setDeniedCloseBtn("关闭")
                        .setDeniedSettingBtn("去设置页面")
                        .setRationalMessage("此功能需要您授予"+getPermissionString(permissions)+"权限,否则将不能正常使用")
                        .setRationalBtn("好的")
                        .build(),
                new AcpListener() {
                    @Override
                    public void onGranted() {
                        callBack.onAcpGranted();
                    }

                    @Override
                    public void onDenied(List<String> permissions) {
                        Toast.makeText(context.getApplicationContext(), getPermissionString(permissions) + "权限拒绝", Toast.LENGTH_SHORT).show();
                        callBack.onAcpDenied();
                    }
                });

    }

    private static String getPermissionString(String[] permissions) {
        StringBuilder sb = new StringBuilder();
        if (permissions.length <= 3) {
            for (int i = 0; i < permissions.length; i++) {
                if (i < permissions.length)
                    sb.append(permissions[i]).append(",");
                else
                    sb.append(permissions[i]);
            }
        } else {
            for (int i = 0; i <= 3; i++) {
                if (i < 3) {
                    sb.append(permissions[i]).append(",");
                } else {
                    sb.append(permissions[i]).append("等");
                    break;
                }
            }
        }
        return sb.toString();
    }
    private static String getPermissionString(List<String> permissions) {
        StringBuilder sb = new StringBuilder();
        if (permissions.size() <= 3) {
            for (int i = 0; i < permissions.size(); i++) {
                if (i < permissions.size())
                    sb.append(permissions.get(i)).append(",");
                else
                    sb.append(permissions.get(i));
            }
        } else {
            for (int i = 0; i <= 3; i++) {
                if (i < 3) {
                    sb.append(permissions.get(i)).append(",");
                } else {
                    sb.append(permissions.get(i)).append("等");
                    break;
                }
            }
        }
        return sb.toString();
    }
}
