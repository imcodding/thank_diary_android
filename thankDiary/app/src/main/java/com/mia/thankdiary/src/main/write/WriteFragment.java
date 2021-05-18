package com.mia.thankdiary.src.main.write;

import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mia.thankdiary.R;
import com.mia.thankdiary.databinding.FragmentWriteBinding;
import com.mia.thankdiary.src.common.BaseFragment;
import com.mia.thankdiary.src.main.write.interfaces.WriteFragmentView;
import com.mia.thankdiary.src.main.write.service.WriteService;
import com.mia.thankdiary.src.common.models.Diary;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static com.mia.thankdiary.config.ApplicationClass.YYYY_MM_DD;

public class WriteFragment extends BaseFragment<FragmentWriteBinding> implements WriteFragmentView {

    private WriteService mWriteService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWriteBinding.inflate(inflater);

        initVariable();
        initListener();
        initData();

        return binding.getRoot();
    }

    private void initVariable() {
        mWriteService = new WriteService(this);
    }

    private void initListener() {
        binding.writeTvSave.setOnClickListener(v->{
            saveDiary();
        });
    }

    private void initData() {
        showProgressDialog();
        mWriteService.getDiaryToday(YYYY_MM_DD.format(new Date()));
    }

    private void saveDiary() {
        if(!validate()) showToast(getString(R.string.write_content_check));

        String firstThank = String.valueOf(binding.writeEtFirstThank.getText());
        String secondThank = String.valueOf(binding.writeEtSecondThank.getText());
        String thirdThank = String.valueOf(binding.writeEtThirdThank.getText());

        ArrayList<String> thanks = new ArrayList<>();
        thanks.add(firstThank);
        thanks.add(secondThank);
        thanks.add(thirdThank);

        Diary diary = new Diary(thanks);
        HashMap<String, Object> postValues = diary.toMap();

        showProgressDialog();
        mWriteService.writeDiary(postValues, diary.getCrtDate());
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

    @Override
    public void writeSuccess(int code) {
        hideProgressDialog();
        showToast(getString(R.string.write_save_success));
    }

    @Override
    public void writeFailure(String message) {
        hideProgressDialog();
        showToast(getString(R.string.write_save_failure));
    }

    @Override
    public void getDiarySuccess(int code, Diary diary) {
        hideProgressDialog();
        ArrayList<String> contents = diary.getContents();
        binding.writeEtFirstThank.setText(contents.get(0));
        binding.writeEtSecondThank.setText(contents.get(1));
        binding.writeEtThirdThank.setText(contents.get(2));
    }

    @Override
    public void getDiaryFailure(String message) {

    }
}