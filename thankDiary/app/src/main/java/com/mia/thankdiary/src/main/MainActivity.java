package com.mia.thankdiary.src.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.mia.thankdiary.R;
import com.mia.thankdiary.databinding.ActivityMainBinding;
import com.mia.thankdiary.src.common.BaseActivity;
import com.mia.thankdiary.src.main.history.HistoryFragment;
import com.mia.thankdiary.src.main.write.WriteFragment;
import com.mia.thankdiary.src.user.UserSettingFragment;

public class MainActivity extends FragmentActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        initListener();
    }

    private void initView() {
        MainPageAdapter mAdapter = new MainPageAdapter(this);
        mAdapter.addFragment(new WriteFragment());
        mAdapter.addFragment(new HistoryFragment());
        mAdapter.addFragment(new UserSettingFragment());

        binding.viewpager.setAdapter(mAdapter);
        binding.viewpager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        binding.indicator.setViewPager(binding.viewpager);
        binding.indicator.createIndicators(mAdapter.getItemCount(), 0);
    }

    private void initListener() {
        binding.viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
    }
}