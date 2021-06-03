package com.mia.thankdiary.src.main.history;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mia.thankdiary.R;
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

        binding.historyIvLeft.setOnClickListener(v -> {
            mDiaryIndex = mDiaryIndex - 1;
            if (mDiaryIndex < 0) {
                int month = Integer.parseInt(mCurrentMonth) - 1;
                if (month < 1) month = 12;
                if (month < 10) mCurrentMonth = "0" + month;
                else mCurrentMonth = String.valueOf(month);

                mHistoryService.getHistoryByDay(mCurrentYear, mCurrentMonth);
                return;
            }
            setDiary(mDiaryIndex);
        });

        binding.historyIvRight.setOnClickListener(v -> {
            mDiaryIndex = mDiaryIndex + 1;
            if (mDiaryList == null || mDiaryIndex >= mDiaryList.size()) {
                int month = Integer.parseInt(mCurrentMonth) + 1;
                if (month > 12) month = 1;
                if (month < 10) mCurrentMonth = "0" + month;
                else mCurrentMonth = String.valueOf(month);

                mHistoryService.getHistoryByDay(mCurrentYear, mCurrentMonth);
                return;
            }
            setDiary(mDiaryIndex);
        });
    }

    private void initData() {
        showProgressDialog();
        mHistoryService.getHistoryByDay(mCurrentYear, mCurrentMonth);
        binding.historyTvMonth.setText(mCurrentMonth);
    }

    private void setDiary(int index) {
        Diary diary = mDiaryList.get(index);
        ArrayList<String> contents = diary.getContents();
        binding.historyTvThankFirst.setText(getString(R.string.first)+contents.get(0));
        binding.historyTvThankSecond.setText(getString(R.string.second)+contents.get(1));
        binding.historyTvThankThird.setText(getString(R.string.third)+contents.get(2));
        binding.historyTvDate.setText(diary.getCrtDate());
        binding.historyDayTvMessage.setVisibility(View.GONE);
    }

    @Override
    public void getHistByDaySuccess(int code, ArrayList<Diary> list) {
        hideProgressDialog();
        mDiaryList = list;
        if (code == SUCCESS_CODE) {
            mDiaryIndex = mDiaryList.size() - 1;
            binding.historyDayTvMessage.setVisibility(View.GONE);
            binding.historyLine1.setVisibility(View.VISIBLE);
            binding.historyLine2.setVisibility(View.VISIBLE);
            setDiary(list.size() - 1);
        } else {
            mDiaryIndex = 0;
            binding.historyDayTvMessage.setVisibility(View.VISIBLE);
            binding.historyLine1.setVisibility(View.GONE);
            binding.historyLine2.setVisibility(View.GONE);
            binding.historyTvThankFirst.setText("");
            binding.historyTvThankSecond.setText("");
            binding.historyTvThankThird.setText("");
            binding.historyTvDate.setText("");
        }
        binding.historyTvMonth.setText(mCurrentMonth);
    }

    @Override
    public void getHistByDayFailure(String message) {
        showToast(getString(R.string.network_not_working));
    }

    @Override
    public void getHistAllSuccess(int code, ArrayList<Diary> list) {

    }

    @Override
    public void getHistAllFailure(String message) {

    }
}