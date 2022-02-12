package com.example.lab6_mob201.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lab6_mob201.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import java.util.Arrays;


public class Fragment_Bai3 extends Fragment {
    private TextView tvProfile;
    private ImageView ivProfile;
    private LoginButton lBtnFacebook;
    private CallbackManager callbackManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FacebookSdk.sdkInitialize(getActivity());
        this.callbackManager = CallbackManager.Factory.create();
        View view = inflater.inflate(R.layout.fragment__bai3, container, false);
        this.initViewById(view);

        this.initButtonLogin();

        return view;
    }

    private void initButtonLogin() {
        this.lBtnFacebook.setReadPermissions(Arrays.asList("email"));
        this.lBtnFacebook.setFragment(this);

        this.lBtnFacebook.registerCallback(this.callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                tvProfile.setText("Tài khoản: " + loginResult.getAccessToken().getUserId());
                String urlImage = "https://graph.facebook.com/" + loginResult.getAccessToken().getUserId() + "/picture?return_ssl_resources=1";
                Picasso.get().load(urlImage).error(R.drawable.ic_baseline_error_24).into(ivProfile);

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(@NonNull FacebookException e) {

            }
        });
    }

    private void initViewById(View view) {
        this.tvProfile = view.findViewById(R.id.tv_info);
        this.ivProfile = view.findViewById(R.id.iv_profile);
        this.lBtnFacebook = view.findViewById(R.id.login_facebook);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (FacebookSdk.isInitialized()) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }
}