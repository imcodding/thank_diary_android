package com.mia.thankdiary.src.main.write;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mia.thankdiary.R;
import com.mia.thankdiary.databinding.FragmentWriteBinding;
import com.mia.thankdiary.src.common.BaseFragment;

public class WriteFragment extends BaseFragment<FragmentWriteBinding> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWriteBinding.inflate(inflater);

        return binding.getRoot();
    }
}