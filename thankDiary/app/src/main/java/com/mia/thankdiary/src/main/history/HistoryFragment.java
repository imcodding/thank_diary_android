package com.mia.thankdiary.src.main.history;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;

import com.mia.thankdiary.R;
import com.mia.thankdiary.databinding.FragmentHistoryBinding;
import com.mia.thankdiary.src.common.BaseFragment;
import com.mia.thankdiary.src.main.MainActivity;

import java.util.Objects;

public class HistoryFragment extends BaseFragment<FragmentHistoryBinding>  {

    private MainActivity mParentActivity;
    private FragmentManager mFragmentManager;
    private int mFragIndex = 0;

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
        mParentActivity = (MainActivity) getActivity();
        mFragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
    }

    private void initView() {
        switchFragment();
    }

    private void initListener() {
        binding.historyTvCalendar.setOnClickListener(v->{
            switchFragment();
        });
    }

    private void switchFragment() {
        if(mFragIndex == 0) {
            mFragIndex = 1;
            mFragmentManager.beginTransaction().replace(R.id.history_container, new HistoryByDayFragment()).commit();
            binding.historyTvCalendar.setText(getString(R.string.history_view_all));
        } else {
            mFragIndex = 0;
            mFragmentManager.beginTransaction().replace(R.id.history_container, new HistoryAllFragment()).commit();
            binding.historyTvCalendar.setText(getString(R.string.history_view_by_day));
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if(mParentActivity.isRefreshFrag()) {
            mParentActivity.setRefreshFrag(false);
            mFragIndex = 1;
            mFragmentManager.beginTransaction().replace(R.id.history_container, new HistoryByDayFragment()).commit();
        }
    }
}