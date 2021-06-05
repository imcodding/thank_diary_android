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
import com.mia.thankdiary.src.common.util.DateFormatUtil;
import com.mia.thankdiary.src.main.history.interfaces.OnItemClickListener;
import com.mia.thankdiary.src.main.history.models.HistoryResponse;

import java.util.ArrayList;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.HistoryHolder> {
    ArrayList<Diary> list;
    Context context;
    OnItemClickListener listener;

    public HistoryListAdapter(Context context) {
        this.context = context;
    }

    public void setList(ArrayList<Diary> list) {
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public HistoryListAdapter.HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_thank, null, false);
        HistoryHolder historyHolder = new HistoryHolder(view);

        return historyHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryListAdapter.HistoryHolder holder, int position) {
        Diary diary = list.get(position);
        holder.itemThankTvDate.setText(getDateStr(diary.getCrtDate()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder {
        TextView itemThankTvDate;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            itemThankTvDate = itemView.findViewById(R.id.item_thank_tv_date);
            itemThankTvDate.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(list.get(getAdapterPosition()));
                }
            });
        }
    }

    private String getDateStr(String date) {
        String str = "";
        String year = DateFormatUtil.getYear(date);
        String month = DateFormatUtil.getMonth(date);
        String day = DateFormatUtil.getDay(date);

        str += year + "년 " + month + "월 " + day + "일의 기록";
        return str;
    }
}
