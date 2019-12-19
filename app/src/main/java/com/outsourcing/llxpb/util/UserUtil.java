package com.outsourcing.llxpb.util;

import android.app.Activity;

import com.outsourcing.llxpb.Bean.CheckNameIdCareBean;
import com.outsourcing.llxpb.Bean.DeclareBean;
import com.outsourcing.llxpb.Bean.ProjectListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2019/7/2.
 */

public class UserUtil {
    public static String name ;
    public static String phone ;
    public static String password ;
    public static String projectId ;
    public static String projectName ;
    public static String default_project_short_name ;
    public static String uuid ;
    public static String token ;
    public static String role_type ;
    public static int project_count ;
    public static int project_id ;
    public static List<Activity> sActivities;
    public static Activity mainActivity;
    public static List<Activity> sendPerposesActivitys;
    public static List<Activity> addDeclareActivitys;
    public static List<DeclareBean.DataBean> localityDeclare = new ArrayList<>();
    public static List<DeclareBean.DataBean> servletDeclare = new ArrayList<>();
    public static List<ProjectListBean.DataBean> projectList = new ArrayList<>();

    public static double lat;
    public static double lon;
    public static int allow_execute_entry = 0;
    public static int city_id = -1;
    public static long city_id_time = 0;
    public static CheckNameIdCareBean sCheckNameIdCareBean ;


}
