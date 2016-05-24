package com.learning.facebookintegrationdemo.ListenerInterfaces;

/**
 * Created by kapilkapri on 17/05/16.
 */
public class ListenerInterfaces {
    public interface MyFbCallbacks
    {
        public void onLoginSuccess();
        public void onLoginError();
        public void onLogoutSuccess();
        public void onLogoutError();
    }
}
