package com.outsourcing.llxpb.manager;

import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.outsourcing.llxpb.Bean.UserBean;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.SPUtils;
import com.outsourcing.llxpb.util.UserUtil;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DELL on 2019/7/2.
 */

public class UserLoginManager {
    public static void loginUser(final String phone, final String password, final IHttpCallBack iHttpCallBack) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("mobile",phone);
            jsonObject1.put("password",password);
            jsonObject1.put("device_type",1000);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.post(HttpHost.httpHost+"/subcontractor/user/login")
                .upJson(jsonObject1.toString())
                .execute(new JsonCallBack<UserBean>() {
                    @Override
                    public void onSuccess(UserBean userBean, okhttp3.Call call, okhttp3.Response response) {
                        if (userBean != null && userBean.getMessage().contains("success")) {
                            String defaultProjectId = userBean.getData().getDefault_project_id() + "";
                            String spPid = (String) SPUtils.get("projectId", "");
                            if (TextUtils.isEmpty(spPid)) {  //初次进入没有ID      如果有ID则不换id
                                SPUtils.put("projectId", defaultProjectId);
                                UserUtil.projectId = defaultProjectId;
                            } else UserUtil.projectId = spPid;
                            UserUtil.phone = phone;
                            UserUtil.password = password;
                            UserUtil.uuid = userBean.getData().getUuid();
                            UserUtil.token = userBean.getData().getToken();
                            UserUtil.projectName = userBean.getData().getDefault_project_name();
                            UserUtil.default_project_short_name = userBean.getData().getDefault_project_short_name();
                            UserUtil.role_type = userBean.getData().getRole_type() + "";
                            UserUtil.project_count = userBean.getData().getProject_count();
                            UserUtil.project_id = userBean.getData().getDefault_project_id();
                            iHttpCallBack.onSuccess();
                        } else {
                            iHttpCallBack.onError(userBean.getMessage());
                        }
                    }
                });
    }
}