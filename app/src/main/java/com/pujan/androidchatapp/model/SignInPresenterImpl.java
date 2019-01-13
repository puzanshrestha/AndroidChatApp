package com.pujan.androidchatapp.model;

import com.pujan.androidchatapp.presenter.SignInPresenter;
import com.pujan.androidchatapp.view.SignInView;

public class SignInPresenterImpl implements SignInPresenter {

    private SignInView signInView;

    public SignInPresenterImpl(SignInView signInView) {
        this.signInView = signInView;
    }

    @Override
    public void signIn(String username, String password) {
            signInView.signInSuccess();
    }
}
