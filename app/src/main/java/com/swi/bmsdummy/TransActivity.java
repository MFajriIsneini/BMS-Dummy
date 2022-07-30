package com.swi.bmsdummy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TransActivity extends BaseActivity {

    EditText editBankTujuan, editNoRek, editNominal, editNoHp, editBerita;
    Button btnNext;
    String title, strAmount, strRek;
    static final int uiRequestCode= 101;
    static final int uiResultCode= 102;
    Intent intent = new Intent();

    @Override
    public int getLayoutId() {
        return R.layout.activity_trans;
    }

    @Override
    public void initViews() {
        editBankTujuan = findViewById(R.id.et_bank_tujuan);
        editNoRek = findViewById(R.id.et_no_rekening);
        editNominal = findViewById(R.id.et_nominal);
        editNoHp = findViewById(R.id.et_no_hp);
        editBerita = findViewById(R.id.et_berita);
        btnNext = findViewById(R.id.btn_next);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        getSupportActionBar().setTitle(title);
        if (title.equals("Tarik Tunai")) {
            findViewById(R.id.bank_tujuan).setVisibility(View.GONE);
            findViewById(R.id.no_rekening).setVisibility(View.GONE);
            findViewById(R.id.berita).setVisibility(View.GONE);
        } else if (title.equals("Cek Saldo")) {
            findViewById(R.id.form).setVisibility(View.GONE);
            findViewById(R.id.biaya_admin).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setListener() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.equals("Cek Saldo")) {
                    setParamIntent("inquiry");
                } else if (title.equals("Tarik Tunai")) {
                    setParamIntent("withdrawal");
                } else {
                    setParamIntent("transfer");
                }
                callMiniAtmNdp();
            }
        });
    }


    private void setParamIntent(String menu) {
        intent.putExtra("App", "PartnerApps");
        intent.putExtra("menu", menu);
        intent.putExtra("amount", editNominal.getText().toString());
        intent.putExtra("feeAmount", "4000");
        intent.putExtra("DateTime", "");
        intent.putExtra("toCBC", editBankTujuan.getText().toString());
        intent.putExtra("toAcc", editNoRek.getText().toString());
        intent.putExtra("reference", editBerita.getText().toString());
        intent.putExtra("transaction_ref_number", "");
        intent.putExtra("AdditionalData", "");
    }

    private void callMiniAtmNdp() {
        intent.setClassName("com.pax_miniatm_ndp.edc", "com.pax_miniatm_ndp.pay.MainActivity");
        startActivityForResult(intent, uiRequestCode);
    }

    private void intentTransDetail() {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);

        Intent intent = new Intent(this, TransDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case uiRequestCode:
                String dt = data.getStringExtra("Data");
                String respon = data.getStringExtra("Respon");
                int dur= 10;
                Log.i("RSP", "data: " + dt);
                Log.i("RSP", "respon: " + respon);
                finish();
                Toast.makeText(this, "response: "+respon, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "data: "+dt, Toast.LENGTH_LONG).show();
                break;
        }
    }
}