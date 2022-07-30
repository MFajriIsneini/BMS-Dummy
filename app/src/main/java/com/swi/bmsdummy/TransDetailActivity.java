package com.swi.bmsdummy;

import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class TransDetailActivity extends BaseActivity {

    Button btnNext;
    private ArrayList<String> leftColumns = new ArrayList<>();
    private ArrayList<String> rightColumns = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_trans_detail;
    }

    @Override
    public void initViews() {

        btnNext = findViewById(R.id.btn_next);

        LinearLayout llDetailContainer = (LinearLayout) findViewById(R.id.detail_layout);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 15;

        for (int i = 0; i < leftColumns.size(); i++) {
            LinearLayout layer = ViewUtils.genSingleLineLayout(TransDetailActivity.this, leftColumns.get(i),
                    rightColumns.get(i));
            llDetailContainer.addView(layer, params);
        }

    }

    @Override
    public void setListener() {

    }
}
