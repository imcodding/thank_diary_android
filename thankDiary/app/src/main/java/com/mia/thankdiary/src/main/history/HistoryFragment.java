package com.mia.thankdiary.src.main.history;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mia.thankdiary.R;
import com.mia.thankdiary.databinding.FragmentHistoryBinding;
import com.mia.thankdiary.src.common.BaseFragment;
import com.mia.thankdiary.src.common.models.Diary;
import com.mia.thankdiary.src.main.history.interfaces.HistoryFragmentView;
import com.mia.thankdiary.src.main.history.service.HistoryService;

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

        binding.historyIvRight.setOnClickListener(v->{ });
        binding.historyIvLeft.setOnClickListener(v->{});
    }


    @Override
    public void onResume() {
        super.onResume();
        // get history
        String date = YYYY_MM_DD.format(new Date());
        mHistoryService.getHistory(date);
    }

    @Override
    public void getHistorySuccess(int code, Diary diary) {
        if(code == SUCCESS_CODE) {
            if(diary.getContents() != null) {
                mHistoryListAdapter.setContents(diary.getContents());
                binding.historyRvThanks.setAdapter(mHistoryListAdapter);
            }
        }
    }

    @Override
    public void getHistoryFailure(String message) {
        showToast(getString(R.string.test_failure));
    }


}