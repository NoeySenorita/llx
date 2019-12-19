package com.outsourcing.llxpb.ui.customView.pieView;

public class ViewData {
    public String name; //名字
    public int num;   //数值
    public float percentage; //百分比

    public ViewData(String name, int num, float percentage) {
        this.name = name;
        this.num = num;
        this.percentage = percentage;
    }
    public ViewData(int num, float percentage) {
        this.num = num;
        this.percentage = percentage;
    }
}
