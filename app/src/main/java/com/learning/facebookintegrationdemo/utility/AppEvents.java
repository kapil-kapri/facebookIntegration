package com.learning.facebookintegrationdemo.utility;

import android.os.Bundle;

import com.facebook.appevents.AppEventsLogger;
import com.learning.facebookintegrationdemo.application.FacebookIntegrationApplication;

/**
 * Created by kapilkapri on 24/05/16.
 */
public class AppEvents {
    public static void eventCurrentPageName(String pageName){
        AppEventsLogger logger = AppEventsLogger.newLogger(FacebookIntegrationApplication.getAppContext());
        Bundle parameters = new Bundle();
        parameters.putString(AppEventsConstants.PAGE_NAME, pageName);
        logger.logEvent(AppEventsConstants.EVENT_NAME_CURRENT_PAGE,
                parameters);
    }

    public static void eventLoginType(String loginType) {
        AppEventsLogger logger = AppEventsLogger.newLogger(FacebookIntegrationApplication.getAppContext());
        Bundle parameters = new Bundle();
        parameters.putString(AppEventsConstants.LOGIN_TYPE, loginType);
        logger.logEvent(AppEventsConstants.EVENT_NAME_LOGIN,
                parameters);
    }

    public static void eventLoginResult(String result) {
        AppEventsLogger logger = AppEventsLogger.newLogger(FacebookIntegrationApplication.getAppContext());
        Bundle parameters = new Bundle();
        parameters.putString(AppEventsConstants.LOGIN_RESULT, result);
        logger.logEvent(AppEventsConstants.EVENT_NAME_LOGIN,
                parameters);
    }
}
