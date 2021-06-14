package com.mia.thankdiary.src.main.history;

import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mia.thankdiary.R;
import com.mia.thankdiary.databinding.FragmentHistoryAllBinding;
import com.mia.thankdiary.src.common.BaseFragment;
import com.mia.thankdiary.src.common.models.Diary;
import com.mia.thankdiary.src.main.history.interfaces.HistoryFragmentView;
import com.mia.thankdiary.src.main.history.interfaces.OnItemClickListener;
import com.mia.thankdiary.src.main.history.service.HistoryService;

import java.util.ArrayList;
import java.util.Date;

import static com.mia.thankdiary.config.ApplicationClass.YYYY_MM_DD;


public class HistoryAllFragment extends BaseFragment<FragmentHistoryAllBinding> implements HistoryFragmentView, OnItemClickListener {

    private HistoryService mHistoryService;
    private HistoryListAdapter mHistoryListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHistoryAllBinding.inflate(inflater);

        initVariable();
        initListener();
        initData();

        return binding.getRoot();
    }

    private void initVariable() {
        mHistoryService = new HistoryService(this);
        mHistoryListAdapter = new HistoryListAdapter(getContext());
    }

    private void initListener() {
        binding.historyTvClose.setOnClickListener(v -> {
            binding.historyScrollDiary.setVisibility(View.GONE);
            binding.historyRvList.setVisibility(View.VISIBLE);
        });
    }

    private void initData() {
        showProgressDialog();
        mHistoryService.getHistoryAll(YYYY_MM_DD.format(new Date()));
    }

    @Override
    public void getHistAllSuccess(int code, ArrayList<Diary> list) {
        hideProgressDialog();
        if(list != null) {
            mHistoryListAdapter.setList(list);
            binding.historyRvList.setAdapter(mHistoryListAdapter);
            mHistoryListAdapter.setOnItemClickListener(this);
            binding.historyTvMessage.setVisibility(View.GONE);
        } else {
            binding.historyTvMessage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getHistAllFailure(String message) {
        showToast(getString(R.string.network_not_working));
    }


    @Override
    public void getHistByDaySuccess(int code, ArrayList<Diary> list) {

    }

    @Override
    public void getHistByDayFailure(String message) {

    }

    @Override
    public void onItemClick(Diary diary) {
        ArrayList<String> contents = diary.getContents();
        binding.historyScrollDiary.setVisibility(View.VISIBLE);
        binding.historyRvList.setVisibility(View.INVISIBLE);
        binding.historyTvThankFirst.setText(getString(R.string.first)+contents.get(0));
        binding.historyTvThankSecond.setText(getString(R.string.second)+contents.get(1));
        binding.historyTvThankThird.setText(getString(R.string.third)+contents.get(2));
    }
}