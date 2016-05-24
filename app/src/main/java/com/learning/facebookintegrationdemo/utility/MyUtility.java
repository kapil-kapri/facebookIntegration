package com.learning.facebookintegrationdemo.utility;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by kapilkapri on 17/05/16.
 */
public class MyUtility {
    public static void addFragmentToActivity
            (AppCompatActivity activity, Fragment fragment, int containerId, String tag) {
        String backStateName = fragment.getClass().getName();
        String fragmentTag = backStateName;
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped && fragmentManager.findFragmentByTag(fragmentTag) == null) { //fragment not in back stack, create it.
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(containerId, fragment, backStateName);
            fragmentTransaction.addToBackStack(backStateName);
            fragmentTransaction.commit();
        }
    }


    public static void addFragmentToActivity
            (AppCompatActivity activity, Fragment fragment, String tag) {
        addFragmentToActivity(activity, fragment, Constants.fragmentContainer, tag);
    }


    public static void replaceFragmentToActivity
            (AppCompatActivity activity, Fragment fragment, int containerId, boolean addToBackStack) {
        String backStateName = fragment.getClass().getName();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped && fragmentManager.findFragmentByTag(backStateName) == null) { //fragment not in back stack, create it.
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(containerId, fragment, backStateName);
            if (addToBackStack)
                fragmentTransaction.addToBackStack(backStateName);
            fragmentTransaction.commit();
        }
    }
}
