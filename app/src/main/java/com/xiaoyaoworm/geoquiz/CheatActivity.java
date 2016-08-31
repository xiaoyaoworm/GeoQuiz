package com.xiaoyaoworm.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE = "com.xiaoyaoworm.geoquiz.answer_is_true";

    private boolean answer_is_true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        answer_is_true = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
    }
}
