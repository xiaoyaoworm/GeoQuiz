package com.xiaoyaoworm.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoyaoworm.model.Question;

public class QuizActivity extends AppCompatActivity {

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

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mPrevButton = (ImageButton) findViewById(R.id.prev_button);

        mQuestion = (TextView) findViewById(R.id.question_text);

        updateQuestion();
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


}
