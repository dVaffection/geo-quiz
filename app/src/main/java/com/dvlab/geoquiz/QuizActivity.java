package com.dvlab.geoquiz;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends ActionBarActivity {

    private static final String TAG = QuizActivity.class.getSimpleName();
    private static final String CURRENT_QUESTION_INDEX_KEY = "currentQuestionIndex";

    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private TextView questionTextView;

    private TrueFalse[] questionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, false),
            new TrueFalse(R.string.question_mideast, true),
    };
    private int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate() called");

        setContentView(R.layout.activity_quiz);

        trueButton = (Button) findViewById(R.id.true_button);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int messageResource = isCorrectAnswer(true);
                Toast.makeText(QuizActivity.this, messageResource, Toast.LENGTH_SHORT).show();
            }
        });

        falseButton = (Button) findViewById(R.id.false_button);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int messageResource = isCorrectAnswer(false);
                Toast.makeText(QuizActivity.this, messageResource, Toast.LENGTH_SHORT).show();
            }
        });


        if (savedInstanceState != null) {
            currentQuestionIndex = savedInstanceState.getInt(CURRENT_QUESTION_INDEX_KEY, 0);
            Log.d(TAG, "saved `currentQuestionIndex`: " + String.valueOf(currentQuestionIndex));
        }


        questionTextView = (TextView) findViewById(R.id.question_text_view);
        int question = questionBank[currentQuestionIndex].getQuestion();
        questionTextView.setText(question);
        questionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToTheNextQuestion();
            }
        });

        nextButton = (ImageButton) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToTheNextQuestion();
            }
        });

        prevButton = (ImageButton) findViewById(R.id.prev_button);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToThePrevQuestion();
            }
        });
    }

    private void moveToTheNextQuestion() {
        currentQuestionIndex++;
        if (currentQuestionIndex >= questionBank.length) {
            currentQuestionIndex = 0;
        }

        displayQuestion(currentQuestionIndex);
    }

    private void moveToThePrevQuestion() {
        currentQuestionIndex--;
        if (currentQuestionIndex < 0) {
            currentQuestionIndex = questionBank.length - 1;
        }

        displayQuestion(currentQuestionIndex);
    }

    private void displayQuestion(int index) {
        int question = questionBank[index].getQuestion();
        questionTextView.setText(question);
    }

    private int isCorrectAnswer(boolean userChoice) {
        boolean questionChoice = questionBank[currentQuestionIndex].isTrueQuestion();

        int messageResource;
        if ((userChoice && questionChoice) || (!userChoice && !questionChoice)) {
            messageResource = R.string.correct_toast;
            moveToTheNextQuestion();
        } else {
            messageResource = R.string.incorrect_toast;
        }

        return messageResource;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "onSaveInstanceState()");
        outState.putInt(CURRENT_QUESTION_INDEX_KEY, currentQuestionIndex);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

}
