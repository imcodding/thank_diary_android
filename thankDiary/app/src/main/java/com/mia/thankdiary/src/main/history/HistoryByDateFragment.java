package com.mia.thankdiary.src.main.history;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mia.thankdiary.databinding.FragmentHistoryByDateBinding;
import com.mia.thankdiary.src.common.BaseFragment;

public class HistoryByDateFragment extends BaseFragment<FragmentHistoryByDateBinding> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHistoryByDateBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }
}