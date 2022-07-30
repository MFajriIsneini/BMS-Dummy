package com.swi.bmsdummy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    LinearLayout btnCekSaldo, btnTarikTunai, btnTransfer;
    String title;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        btnCekSaldo = findViewById(R.id.cek_saldo);
        btnTarikTunai = findViewById(R.id.tarik_tunai);
        btnTransfer = findViewById(R.id.transfer);
    }

    @Override
    public void setListener() {
        btnCekSaldo.setOnClickListener(this);
        btnTarikTunai.setOnClickListener(this);
        btnTransfer.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cek_saldo:
                title = "Cek Saldo";
                break;
            case R.id.tarik_tunai:
                title = "Tarik Tunai";
                break;
            case R.id.transfer:
                title = "Transfer";
                break;
            default:
                break;
        }
        Intent intent = new Intent(this, TransActivity.class);
        intent.putExtra("title", title);
        startActivity(intent);
    }
}