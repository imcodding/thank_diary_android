package com.mia.thankdiary.src.main.history;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mia.thankdiary.R;
import com.mia.thankdiary.databinding.FragmentHistoryAllBinding;
import com.mia.thankdiary.src.common.BaseFragment;


public class HistoryAllFragment extends BaseFragment<FragmentHistoryAllBinding> {

    public static HistoryAllFragment newInstance(String param1, String param2) {
        HistoryAllFragment fragment = new HistoryAllFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHistoryAllBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }
}