package com.mia.thankdiary.src.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mia.thankdiary.R;
import com.mia.thankdiary.src.user.interfaces.OnItemSettingClickListener;
import com.mia.thankdiary.src.user.model.Settings;

import java.util.ArrayList;

public class UserSettingListAdapter extends RecyclerView.Adapter<UserSettingListAdapter.SettingHolder> {

    private ArrayList<Settings> list;
    private Context context;
    private int[] images = new int[]{
            R.drawable.ic_pencil, R.drawable.ic_description,
            R.drawable.ic_alarm, R.drawable.ic_logout
    };

    private OnItemSettingClickListener listener;

    public UserSettingListAdapter(Context context) {
        this.context = context;
        initList();
    }

    public void setListener(OnItemSettingClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public SettingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setting, null, false);
        SettingHolder holder = new SettingHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SettingHolder holder, int position) {
        Settings item = list.get(position);
        holder.itemSettingIv.setImageResource(item.getImage());
        holder.itemSettingTv.setText(item.getText());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SettingHolder extends RecyclerView.ViewHolder {
        ImageView itemSettingIv;
        TextView itemSettingTv;

        public SettingHolder(@NonNull View itemView) {
            super(itemView);
            itemSettingIv = itemView.findViewById(R.id.item_setting_iv);
            itemSettingTv = itemView.findViewById(R.id.item_setting_tv);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(list.get(getAdapterPosition()));
                }
            });
        }
    }

    private void initList() {
        list = new ArrayList<>();
        list.add(new Settings(context.getString(R.string.user_setting_written), images[0]));
        list.add(new Settings(context.getString(R.string.user_setting_desc), images[1]));
//        list.add(new Settings(context.getString(R.string.user_setting_alarm), images[2]));
        list.add(new Settings(context.getString(R.string.user_setting_logout), images[3]));
    }
}
