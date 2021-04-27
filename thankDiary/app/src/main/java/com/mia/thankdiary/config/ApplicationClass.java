package com.mia.thankdiary.config;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mia.thankdiary.src.roomdb.DiaryDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationClass extends Application {
    // SharedPreferences 키 값
    public static String TAG = "NOGOMIN_APP";
    public static SharedPreferences sSharedPreferences = null;

    //날짜 형식
    public static SimpleDateFormat YYYY_MM_DD_TIME = new SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.KOREA);
    public static SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

    // Firebase 데이터베이스
    public static DatabaseReference mPostReference;

    // Retrofit 인스턴스
    public static Retrofit retrofit;

    public static String USER_ID;

    public static DiaryDatabase roomDB;


    @Override
    public void onCreate() {
        super.onCreate();

        if (sSharedPreferences == null) {
            sSharedPreferences = getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        }

        if(roomDB == null) {
            roomDB = DiaryDatabase.getDatabase(getApplicationContext());
        }
    }

    public static DatabaseReference getDatabaseReference() {
        mPostReference = FirebaseDatabase.getInstance().getReference();

        return mPostReference;
    }

    public static Retrofit getRetrofit(String BASE_URL) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        if (retrofit == null) {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("Content-Type","application/json")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }

        return retrofit;
    }
}
