package com.mia.thankdiary.src.main.history;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.mia.thankdiary.R;
import com.mia.thankdiary.databinding.FragmentHistoryBinding;
import com.mia.thankdiary.src.common.BaseFragment;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

public class HistoryFragment extends BaseFragment<FragmentHistoryBinding> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHistoryBinding.inflate(inflater);

        initListener();
        return binding.getRoot();
    }

    private void initListener() {
//        binding.historyCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//
//            }
//        });
//        binding.historyCalendar.selec
        binding.historyCalendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                showLog("History", date.toString());
            }
        });

//        binding.historyCalendar.addDecorator(new EventDe);
    }


    @Override
    public void onResume() {
        super.onResume();
        // get history
    }
}