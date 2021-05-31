package com.mia.thankdiary.src.main.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mia.thankdiary.R;
import com.mia.thankdiary.src.common.models.Diary;
import com.mia.thankdiary.src.main.history.models.HistoryResponse;

import java.util.ArrayList;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.HistoryHolder> {
    ArrayList<Diary> list;
    ArrayList<String> contents;
    Context context;

    public HistoryListAdapter(Context context) {
        this.context = context;
    }

    public void setList(ArrayList<Diary> list) {
        this.list = list;
    }

    public void setContents(ArrayList<String> contents) { this.contents = contents; }


    @NonNull
    @Override
    public HistoryListAdapter.HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_thank, null);
        HistoryHolder historyHolder = new HistoryHolder(view);

        return historyHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryListAdapter.HistoryHolder holder, int position) {
//        HistoryResponse item = list.get(position);
        String content = contents.get(position);
        holder.itemThankTvContent.setText(position + ". " + content);
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder {
        TextView itemThankTvContent;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            itemThankTvContent = itemView.findViewById(R.id.item_thank_tv_content);
        }
    }
}
