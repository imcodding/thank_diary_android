package com.mia.thankdiary.src.common;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import java.util.regex.Pattern;

public class BaseActivity<B extends ViewBinding> extends AppCompatActivity {
    protected B binding;
    protected ProgressDialog mProgressDialog;

    public void showToast(final String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void showProgressDialog() {
        if(mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if(mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void hideKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showLog(final String tag, final String message) {
        Log.d("SHOW_LOG_" + tag, message);
    }
}
