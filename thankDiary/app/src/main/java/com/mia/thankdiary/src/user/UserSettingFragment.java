package com.mia.thankdiary.src.user;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mia.thankdiary.R;
import com.mia.thankdiary.databinding.FragmentUserSettingBinding;
import com.mia.thankdiary.src.common.BaseFragment;
import com.mia.thankdiary.src.common.MessageDialog;
import com.mia.thankdiary.src.login.LoginActivity;
import com.mia.thankdiary.src.user.adapter.UserSettingListAdapter;
import com.mia.thankdiary.src.user.interfaces.OnItemSettingClickListener;
import com.mia.thankdiary.src.user.model.Settings;

public class UserSettingFragment extends BaseFragment<FragmentUserSettingBinding> implements OnItemSettingClickListener {

    private UserSettingListAdapter mUserSettingListAdapter;
    private MessageDialog mMessageDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserSettingBinding.inflate(inflater);

        initVariable();
        initView();
        initListener();

        return binding.getRoot();
    }

    private void initVariable() {
        mUserSettingListAdapter = new UserSettingListAdapter(getContext());
        mMessageDialog = new MessageDialog(getContext());
    }

    private void initView() {
        binding.userSettingRvList.setAdapter(mUserSettingListAdapter);
    }

    private void initListener() {
        mUserSettingListAdapter.setListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onItemClick(Settings item) {
        switch (item.getImage()) {
            case R.drawable.ic_description:

                break;
            case R.drawable.ic_logout:
                mMessageDialog.show();
                break;
        }
    }

}