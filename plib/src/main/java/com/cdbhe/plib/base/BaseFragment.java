package com.cdbhe.plib.base;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cdbhe.plib.utils.LoadingDialog;

/**
 * Created by Kevin on 2018/2/1.
 */

public abstract class BaseFragment extends BasePermissionsFragment {
    protected Activity mActivity;
    protected Context mContext;
    private View mRootView;
    public LoadingDialog loadingDialog;

    public abstract int getLayoutResId();
    public abstract void init(Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutResId(), container, false);
        mActivity = getActivity();//获取Activity对象
        mContext = getContext();//获取上下文对象
        initLoadingDialog();//初始化LoadingDialog
        init(savedInstanceState);
        return mRootView;
    }

    /**
     * 初始化菊花模态加载框
     */
    public void initLoadingDialog(){
        loadingDialog = new LoadingDialog();
        loadingDialog.createLoadingDialog(mContext);
    }

    /**
     * 显示模态框
     */
    public void showDialog(){
        loadingDialog.showDialog();
    }

    /**
     * 关闭模态框
     */
    public void closeDialog(){
        loadingDialog.closeDialog();
    }

    /**
     * 设置模态框样式
     * @param indicatorName
     * @param indicatorColor
     */
    public void setDialogIndicator(String indicatorName,String indicatorColor){
        loadingDialog.setDialogIndicator(indicatorName,indicatorColor);
    }

    /**
     * 判断是否有网络
     * @return
     */
    protected boolean checkNetWork() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }
}
