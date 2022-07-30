package com.swi.bmsdummy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initViews();
        setListener();
    }

    public abstract int getLayoutId();
    public abstract void initViews();
    public abstract void setListener();
}
