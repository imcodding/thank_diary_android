package com.mia.thankdiary.src.main.history;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.mia.thankdiary.R;
import com.mia.thankdiary.src.main.history.models.HistoryResponse;

import java.util.ArrayList;

public class HistoryPageAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<HistoryResponse> list;

    public HistoryPageAdapter(Context context)
    {
        this.context = context;
    }

    public void setList(ArrayList<HistoryResponse> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_page, null);

        Log.d("History", "instantiateItem"+position);

        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == (View)o);
    }

}
