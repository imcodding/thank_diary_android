package com.mia.thankdiary.src.main.history;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.chip.ChipGroup;
import com.mia.thankdiary.R;
import com.mia.thankdiary.databinding.FragmentHistoryBinding;
import com.mia.thankdiary.src.common.BaseFragment;
import com.mia.thankdiary.src.common.models.Diary;
import com.mia.thankdiary.src.main.history.interfaces.HistoryFragmentView;
import com.mia.thankdiary.src.main.history.service.HistoryService;

import java.util.ArrayList;
import java.util.Date;

import static com.mia.thankdiary.config.ApplicationClass.SUCCESS_CODE;
import static com.mia.thankdiary.config.ApplicationClass.YYYY_MM_DD;

public class HistoryFragment extends BaseFragment<FragmentHistoryBinding> implements HistoryFragmentView {

    private HistoryListAdapter mHistoryListAdapter;
    private HistoryService mHistoryService;


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
        mHistoryListAdapter = new HistoryListAdapter(getContext());
        mHistoryService = new HistoryService(this);
    }

    private void initView() {

    }

    private void initListener() {

        binding.historyChipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.history_chip_day:
                        break;
                    case R.id.history_chip_month:
                        break;
                    case R.id.history_chip_year:
                        break;
                }
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        // get history
        String date = YYYY_MM_DD.format(new Date());
//        mHistoryService.getHistoryByMonth(date);
//        mHistoryService.getHistoryByYear(date);
    }

    @Override
    public void getHistByDaySuccess(int code, Diary diary) {
        if(code == SUCCESS_CODE) {
            if(diary.getContents() != null) {
                mHistoryListAdapter.setContents(diary.getContents());
                binding.historyRvThanks.setAdapter(mHistoryListAdapter);
            }
        }
    }

    @Override
    public void getHistByDayFailure(String message) {
        showToast(getString(R.string.test_failure));
    }

    @Override
    public void getHistByYearSuccess(int code, ArrayList<Diary> list) {
        if(code == SUCCESS_CODE) {

        }
    }

    @Override
    public void getHistByYearFailure(String message) {

    }

    @Override
    public void getHistByMonthSuccess(int code, ArrayList<Diary> list) {
        if(code == SUCCESS_CODE) {

        }
    }

    @Override
    public void getHistByMonthFailure(String message) {

    }
}