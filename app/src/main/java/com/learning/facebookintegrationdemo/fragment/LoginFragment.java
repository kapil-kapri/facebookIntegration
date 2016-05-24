package com.learning.facebookintegrationdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.learning.facebookintegrationdemo.R;
import com.learning.facebookintegrationdemo.activity.MainActivity;
import com.learning.facebookintegrationdemo.utility.AppEvents;

import butterknife.OnClick;

/**
 * Created by kapilkapri on 17/05/16.
 */

/*


ResponseType.TOKEN if the Enable Client Access Token Flow switch in your app's dashboard is ON, and ResponseType.CODE if it is OFF.


 */
public class LoginFragment extends BaseFragment{
    public static String TAG = "LoginFragment";


    public static LoginFragment newInstance(Bundle bundle) {
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppEvents.eventCurrentPageName("LOGIN_PAGE");
    }


    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_login;
    }

    @OnClick(R.id.login_phone)
    public void onLoginPhone() {
        AppEvents.eventLoginType("MOBILE_NO");

        final Intent intent = new Intent(getActivity(), AccountKitActivity.class);
        /*AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
                        AccountKitActivity.ResponseType.CODE); // or .ResponseType.TOKEN


        */
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
                        AccountKitActivity.ResponseType.TOKEN);

        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        startActivityForResult(intent, MainActivity.APP_REQUEST_CODE);
    }
    @OnClick(R.id.login_email)
    public void onLoginEmail() {

        AppEvents.eventLoginType("EMAIL_ID");
        final Intent intent = new Intent(getActivity(), AccountKitActivity.class);
       /* AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.EMAIL,
                        AccountKitActivity.ResponseType.CODE); // or .ResponseType.TOKEN*/


        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.EMAIL,
                        AccountKitActivity.ResponseType.TOKEN

                );

        // ... perform additional configuration ...
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        startActivityForResult(intent, MainActivity.APP_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(
            final int requestCode,
            final int resultCode,
            final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MainActivity.APP_REQUEST_CODE) { // confirm that this response matches your request
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String toastMessage;
            if (loginResult.getError() != null) {
                AppEvents.eventLoginResult("LOGIN_ERROR");
                toastMessage = loginResult.getError().getErrorType().getMessage();
                //showErrorActivity(loginResult.getError());
                MainActivity.myFbCallbacks.onLoginError();
            } else if (loginResult.wasCancelled()) {
                AppEvents.eventLoginResult("LOGIN_CANCELLED");
                toastMessage = "Login Cancelled";
            } else {


                AppEvents.eventLoginResult("SUCCESS");
                if (loginResult.getAccessToken() != null) {
                    toastMessage = "Success:" + loginResult.getAccessToken().getAccountId();
                } else {
                    toastMessage = String.format(
                            "Success:%s...",
                            loginResult.getAuthorizationCode().substring(0, 10));
                }

/*
                AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                    @Override
                    public void onSuccess(final Account account) {
                        // Get Account Kit ID
                        String accountKitId = account.getId();

                        // Get phone number
                        PhoneNumber phoneNumber = account.getPhoneNumber();
                        String phoneNumberString = phoneNumber.toString();

                        // Get email
                        String email = account.getEmail();
                    }

                    @Override
                    public void onError(final AccountKitError error) {
                       int data= error.describeContents();
                        // Handle Error
                    }
                });*/
                // If you have an authorization code, retrieve it from
                // loginResult.getAuthorizationCode()
                // and pass it to your server and exchange it for an access token.


                // Success! Start your next activity...
                MainActivity.myFbCallbacks.onLoginSuccess();
            }

            // Surface the result to your user in an appropriate way.
            Toast.makeText(
                    getActivity(),
                    toastMessage,
                    Toast.LENGTH_LONG)
                    .show();
        }
    }


}