package org.sayar.mobisoftdemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.Fragment;

import org.sayar.mobisoftdemo.util.NetworkUtil;

/**
 * Created by Navid Mahboubi at 3/5/20.
 * Email: navid.mahboubi96@gmail.com
 * Phone: +989019199835
 */

public abstract class BaseFragment extends Fragment {

    protected View mainView;

    public abstract void registerWidget();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainView = view;
        registerWidget();
        setListeners();
    }

    public void setListeners() {
    }

    public void loadOnline() {

    }

    public void loadOffline() {

    }

    public void load() {
        if (NetworkUtil.isInternetConnected(getContext())) {
            loadOnline();
        } else {
            loadOffline();
        }
    }

    public void closeKeyboard() {
        try {
//              hide keyboard:
            InputMethodManager inputManager =
                    (InputMethodManager)
                            getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
