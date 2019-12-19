package com.outsourcing.llxpb.util.permission;

public  abstract class PermissionCallBack{
    protected abstract void onAcpGranted();
    protected void onAcpDenied(){}
}
