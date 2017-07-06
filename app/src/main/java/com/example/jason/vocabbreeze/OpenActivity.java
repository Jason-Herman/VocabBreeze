package com.example.jason.vocabbreeze;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OpenActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editFeedback;
    Button btnFeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
        myDb = new DatabaseHelper(this);

        editFeedback = (EditText)findViewById(R.id.editText_feedback);
        btnFeedback = (Button)findViewById(R.id.button_feedback);
        Feedback();
    }
    public void Feedback() {
        btnFeedback.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cursor res = myDb.getFeedbackWord(editFeedback.getText().toString());
                    if(res.getCount() == 0) {
                        // show message
                        Toast.makeText(OpenActivity.this,"Word not found.",Toast.LENGTH_LONG).show();
                        return;
                    }
                    while (res.moveToNext()) {
                        // Double increment and set TTS to increment
                        Integer newInterval = 1;
                        if (Integer.valueOf(res.getString(3)) > 1) {
                            newInterval = Integer.valueOf(res.getString(3)) / 2;
                        }
                        myDb.updateData(res.getString(0), res.getString(1), res.getString(2), newInterval, newInterval);
                    }
                    Toast.makeText(OpenActivity.this,"You'll see " + editFeedback.getText().toString() + " more often.",Toast.LENGTH_LONG).show();

                }
            }
        );
    }
}
