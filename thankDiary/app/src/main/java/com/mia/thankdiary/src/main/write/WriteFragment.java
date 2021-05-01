package com.mia.thankdiary.src.main.write;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mia.thankdiary.R;
import com.mia.thankdiary.databinding.FragmentWriteBinding;
import com.mia.thankdiary.src.common.BaseFragment;
import com.mia.thankdiary.src.roomdb.Diary;

import static com.mia.thankdiary.config.ApplicationClass.roomDB;

public class WriteFragment extends BaseFragment<FragmentWriteBinding> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWriteBinding.inflate(inflater);

        initListener();

        return binding.getRoot();
    }

    private void initListener() {
        binding.writeTvSave.setOnClickListener(v->{
            saveDiary();
        });
    }

    private void saveDiary() {
        if(!validate()) showToast(getString(R.string.write_content_check));

        String firstThank = String.valueOf(binding.writeEtFirstThank.getText());
        String secondThank = String.valueOf(binding.writeEtSecondThank.getText());
        String thirdThank = String.valueOf(binding.writeEtThirdThank.getText());

        Diary diary = new Diary(firstThank, secondThank, thirdThank);

        // 비동기 처리 필요
        roomDB.diaryDao().insertDiary(diary);
    }

    private boolean validate() {
        String value;

        value = String.valueOf(binding.writeEtFirstThank.getText());
        if(value.isEmpty())
            return false;

        value = String.valueOf(binding.writeEtSecondThank.getText());
        if(value.isEmpty())
            return false;

        value = String.valueOf(binding.writeEtThirdThank.getText());
        if(value.isEmpty())
            return false;

        return true;
    }
}