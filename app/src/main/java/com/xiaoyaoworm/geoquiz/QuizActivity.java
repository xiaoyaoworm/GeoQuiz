package com.xiaoyaoworm.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoyaoworm.model.Question;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String EXTRA_ANSWER_IS_TRUE = "com.xiaoyaoworm.geoquiz.answer_is_true";

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestion;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.questions_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa,false),
            new Question(R.string.question_american, true),
            new Question(R.string.question_asia, true)
    };

    private int mCurrentId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Log.d(TAG, "onCreate(Buddle) called");
        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mPrevButton = (ImageButton) findViewById(R.id.prev_button);

        mQuestion = (TextView) findViewById(R.id.question_text);

        if(savedInstanceState!=null){
            mCurrentId = savedInstanceState.getInt(KEY_INDEX);
        }
        updateQuestion();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentId);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    public void checkTrue(View view){
        checkResult(true);
    }

    public void checkFalse(View view){
        checkResult(false);
    }

    private void checkResult(boolean answer){
        Question current = mQuestionBank[mCurrentId];
        int mResId = 0;
        if(answer == current.isAnswerTrue()) {
            mResId = R.string.correct_toast;
        } else{
            mResId = R.string.incorrect_toast;
        }
        Toast.makeText(QuizActivity.this, mResId, Toast.LENGTH_SHORT).show();
    }

    public void goNext(View view){
        mCurrentId = (mCurrentId+1)%mQuestionBank.length;
        updateQuestion();
    }

    public void goPrev(View view){
        mCurrentId = (mCurrentId+mQuestionBank.length-1)%mQuestionBank.length;
        updateQuestion();
    }

    private void updateQuestion(){
        mQuestion.setText(mQuestionBank[mCurrentId].getTextResId());
    }

    public void cheat(View view){
        Intent i = new Intent(QuizActivity.this, CheatActivity.class);
        boolean answer_is_true = mQuestionBank[mCurrentId].isAnswerTrue();
        i.putExtra(EXTRA_ANSWER_IS_TRUE,answer_is_true);
        startActivity(i);
    }
}
