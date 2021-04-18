package com.mia.thankdiary.src.main.history;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mia.thankdiary.databinding.FragmentHistoryBinding;
import com.mia.thankdiary.src.common.BaseFragment;

public class HistoryFragment extends BaseFragment<FragmentHistoryBinding> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHistoryBinding.inflate(inflater);

        initVariable();
        initView();
        initListener();

        return binding.getRoot();
    }

    private void initVariable() {

    }

    private void initView() {

    }

    private void initListener() {

    }


    @Override
    public void onResume() {
        super.onResume();
        // get history
    }
}