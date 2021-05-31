package com.mia.thankdiary.src.main.history;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mia.thankdiary.databinding.FragmentHistoryByDayBinding;
import com.mia.thankdiary.src.common.BaseFragment;
import com.mia.thankdiary.src.common.models.Diary;
import com.mia.thankdiary.src.common.util.DateFormatUtil;
import com.mia.thankdiary.src.main.history.interfaces.HistoryFragmentView;
import com.mia.thankdiary.src.main.history.service.HistoryService;

import java.util.ArrayList;
import java.util.Date;

import static com.mia.thankdiary.config.ApplicationClass.SUCCESS_CODE;
import static com.mia.thankdiary.config.ApplicationClass.YYYY_MM_DD;

public class HistoryByDayFragment extends BaseFragment<FragmentHistoryByDayBinding> implements HistoryFragmentView {

    private HistoryService mHistoryService;
    private ArrayList<Diary> mDiaryList;
    private String mCurrentDate;
    private String mCurrentYear;
    private String mCurrentMonth;
    private int mDiaryIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHistoryByDayBinding.inflate(getLayoutInflater());

        initVariable();
        initListener();
        initData();

        return binding.getRoot();
    }

    private void initVariable() {
        mHistoryService = new HistoryService(this);
        mCurrentDate = YYYY_MM_DD.format(new Date());
        mCurrentYear = DateFormatUtil.getYear(mCurrentDate);
        mCurrentMonth = DateFormatUtil.getMonth(mCurrentDate);
    }

    private void initListener() {
        binding.historyIvLeft.setOnClickListener(v->{
            mDiaryIndex = mDiaryIndex - 1;
        });
        binding.historyIvRight.setOnClickListener(v->{
            mDiaryIndex = mDiaryIndex + 1;
        });
    }

    private void initData() {
        showProgressDialog();
        mHistoryService.getHistoryByDay(mCurrentYear, mCurrentMonth);
    }

    private void setDiary(int index) {
        if(index < 0) {
            
        }

        Diary diary = mDiaryList.get(index);
        ArrayList<String> contents = diary.getContents();
        binding.historyTvThankFirst.setText(contents.get(0));
        binding.historyTvThankSecond.setText(contents.get(1));
        binding.historyTvThankThird.setText(contents.get(2));
        binding.historyDayTvMessage.setVisibility(View.GONE);
    }

    @Override
    public void getHistByDaySuccess(int code, ArrayList<Diary> list) {
        hideProgressDialog();
        if (code == SUCCESS_CODE) {
            mDiaryList = list;
            mDiaryIndex = mDiaryList.size();
            binding.historyDayTvMessage.setVisibility(View.GONE);
            setDiary(list.size()-1);
        } else {
            binding.historyDayTvMessage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getHistByDayFailure(String message) {

    }

    @Override
    public void getHistAllSuccess(int code, ArrayList<Diary> list) {

    }

    @Override
    public void getHistAllFailure(String message) {

    }
}