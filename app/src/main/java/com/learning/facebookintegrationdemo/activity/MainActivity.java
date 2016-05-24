package com.learning.facebookintegrationdemo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.learning.facebookintegrationdemo.ListenerInterfaces.ListenerInterfaces;
import com.learning.facebookintegrationdemo.R;
import com.learning.facebookintegrationdemo.fragment.HomeFragment;
import com.learning.facebookintegrationdemo.fragment.LoginFragment;
import com.learning.facebookintegrationdemo.utility.AppEvents;
import com.learning.facebookintegrationdemo.utility.Constants;
import com.learning.facebookintegrationdemo.utility.MyUtility;


/**
 * Created by kapilkapri on 17/05/16.
 */
public class MainActivity extends AppCompatActivity implements ListenerInterfaces.MyFbCallbacks {
    public static int APP_REQUEST_CODE = 99;
    public static ListenerInterfaces.MyFbCallbacks myFbCallbacks;
    private Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppEvents.eventCurrentPageName("MAIN_PAGE");
        myFbCallbacks=this;
        AccessToken accessToken = AccountKit.getCurrentAccessToken();

        if (accessToken != null) {
            //Handle Returning User
            // Home Screen
            openHomePage();
        } else {
            //Handle new or logged out user
            // Login Screen
            openLoginPage();
        }
    }

    private void openLoginPage() {
        // Login screen
        Bundle bundle = new Bundle();
        fragment = LoginFragment.newInstance(bundle);
        MyUtility.replaceFragmentToActivity(MainActivity.this,
                fragment, Constants.fragmentContainer, false);
    }

    private void openHomePage() {
        // Login screen
        // Bundle bundle = new Bundle();
        fragment = HomeFragment.newInstance();
        MyUtility.replaceFragmentToActivity(MainActivity.this,
                fragment, Constants.fragmentContainer, false);
    }



    @Override
    public void onLoginSuccess() {
        openHomePage();
    }

    @Override
    public void onLoginError() {

    }

    @Override
    public void onLogoutSuccess() {

    }

    @Override
    public void onLogoutError() {

    }
}