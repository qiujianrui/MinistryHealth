package com.yhjx.ministryhealth.base;

import androidx.fragment.app.Fragment;

import com.library.basemodule.dialog.LoadingDialog;
import com.library.basemodule.mvp.IView;

public class BaseFragment extends Fragment  implements IView {
    private LoadingDialog loadingDialog;

    @Override
    public void showLoading() {
        if (loadingDialog==null){
            loadingDialog=new LoadingDialog(getActivity());
        }
        loadingDialog.show();
    }

    @Override
    public void hideLoading() {
        if (loadingDialog==null){
            loadingDialog=new LoadingDialog(getActivity());
        }
        loadingDialog.dismiss();
    }

    @Override
    public void loadFailure(int errCode, String msg, String action) {

    }
}
