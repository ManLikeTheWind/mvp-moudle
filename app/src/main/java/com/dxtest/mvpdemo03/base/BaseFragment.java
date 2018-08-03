package com.dxtest.mvpdemo03.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ....package: com.dxtest.mvpdemo03.base
 * <br>.author: dongxiang
 * <br>...date: 2018/5/2  16:41
 * <br>.descrp:
 * <br>..using:
 * <br>.e-mail:dongxiang_android_sdk@aliyun.com
 */

public abstract class BaseFragment extends Fragment implements BaseView {

    public abstract int getContentViewId();

    protected Context mContext;
    protected View mRootView;


    //  14 -  1
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    //  14 -  2
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //  14 - 3
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentViewId(), container, false);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //  14 - 4
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    //  14 - 5
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    //  14 - 6
    @Override
    public void onStart() {
        super.onStart();
    }

    //  14 - 7
    @Override
    public void onResume() {
        super.onResume();
    }

    //  14 - 8
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    //  14 - 9
    @Override
    public void onPause() {
        super.onPause();
    }

    //  14 - 10
    @Override
    public void onStop() {
        super.onStop();
    }

    //  14 - 11
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    //  14 - 12
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    //  14 - 13
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //  14 - 14
    @Override
    public void onDetach() {
        super.onDetach();
        mContext=null;
    }


    //-----------
    @Override
    public void showLoading(int id, Bundle args) {
        if (!isFragmentAttachedActivity()){
            return;
        }
        ((BaseActivity)mContext).showLoading(id,args);

    }

    @Override
    public void dimissLoading(int id) {
        if (!isFragmentAttachedActivity()){
            return;
        }
        ((BaseActivity)mContext).dimissLoading(id);

    }

    @Override
    public void showToast(String msg, int resId) {
        if (!isFragmentAttachedActivity()){
            return;
        }
        ((BaseActivity)mContext).showToast(msg,resId);

    }

    /**
     * 检查activity连接情况
     */
    public boolean isFragmentAttachedActivity() {
        return mContext!=null;
    }

    protected boolean isAttachedContext() {
        return getActivity() != null;
    }

    /** 检查activity连接情况*/
    public void checkActivityAttached() {
        if (getActivity() == null) {
            throw new ActivityNotAttachedException();
        }
    }
    public static class ActivityNotAttachedException extends RuntimeException {
        public ActivityNotAttachedException() {
            super("Fragment has disconnected from Activity ! - -.");
        }
    }
}
