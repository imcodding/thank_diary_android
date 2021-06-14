package com.mia.thankdiary.src.common;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mia.thankdiary.R;
import com.mia.thankdiary.src.common.util.SharedPreferenceUtil;
import com.mia.thankdiary.src.login.LoginActivity;
import com.mia.thankdiary.src.main.MainActivity;

public class MessageDialog extends Dialog {

    public MessageDialog(@NonNull Context context) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_message);

        TextView tvYes = findViewById(R.id.dialog_message_tv_yes);
        TextView tvNo = findViewById(R.id.dialog_message_tv_no);

        tvYes.setOnClickListener(v->{
            SharedPreferenceUtil.putBoolean("AUTO_LOGIN", false);
            Intent intent = new Intent(context, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(intent);
            ((MainActivity)context).finish();
            dismiss();
        });

        tvNo.setOnClickListener(v->{ dismiss(); });
    }
}
