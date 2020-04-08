package com.sofascoreacademy.sofascoreacademy;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public abstract class BaseActivity extends AppCompatActivity {

    private List<Call> networkCalls = new ArrayList<>();

    protected <T> void execute(Call<T> call, Callback<T> callback) {
        networkCalls.add(call);
        call.enqueue(callback);
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (Call networkCall : networkCalls) {
            networkCall.cancel();
        }
    }
}
