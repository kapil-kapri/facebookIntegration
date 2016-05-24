package com.learning.facebookintegrationdemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.PhoneNumber;
import com.learning.facebookintegrationdemo.R;
import com.learning.facebookintegrationdemo.utility.AppEvents;

import butterknife.OnClick;

/**
 * Created by kapilkapri on 17/05/16.
 */

public class  HomeFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = "HomeFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String accountKitId;
    String phoneNumberString,email;
    private Button mLogout;
    private TextView mAccountDetails;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppEvents.eventCurrentPageName("HOME_PAGE");
        mAccountDetails=(TextView) view.findViewById(R.id.account_details);
        getDetails();
        StringBuilder data= new StringBuilder();
        if(null!=accountKitId){
           data.append("Account Id : "+accountKitId);
            if(phoneNumberString!=null){
                data.append("\n Mob No : "+phoneNumberString);
            }else if(email!=null){
                data.append("\n Email : "+email);
            }
        }else {
            data.append("No Details Found");
        }

        mAccountDetails.setText(data);
    }
    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    // TODO: Rename method, update argument and hook method into UI event

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {

        super.onDetach();
    }
    @OnClick(R.id.logout)
    public void logout(){
        AppEvents.eventLoginResult("LOGOUT");
        AccountKit.logOut();
        getActivity().finish();
    }
    private void getDetails(){
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(final Account account) {
                // Get Account Kit ID
                 accountKitId = account.getId();

                // Get phone number
                PhoneNumber phoneNumber = account.getPhoneNumber();
                 phoneNumberString = phoneNumber.toString();

                // Get email
                 email = account.getEmail();
            }

            @Override
            public void onError(final AccountKitError error) {
                // Handle Error
                int data= error.describeContents();
            }
        });
    }
}
