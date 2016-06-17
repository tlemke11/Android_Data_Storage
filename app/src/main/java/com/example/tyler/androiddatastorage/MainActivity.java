package com.example.tyler.androiddatastorage;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    //referenced - https://developer.android.com/guide/topics/data/data-storage.html and used
    //a lot of code there for the
    //public static final String PREFS_NAME = "PrefsFile";

    TextView textViewNum;
    Button btnSaveCount, btnAdvance;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewNum = (TextView) findViewById(R.id.textViewNumber);
        myDb = new DatabaseHelper(this);

        Cursor resultCursor = myDb.getData();
        if (resultCursor.getCount() == 0) {
            System.out.println("Error: Nothing found!");
        } else {

            System.out.println("There was " + resultCursor.getCount() + " for Count.");

            String tempString = resultCursor.getString(1);
            System.out.println(tempString);
            textViewNum.setText(tempString);

            myDb.insertData("0");
        }


        //restore the prefs
        //SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        //String result = settings.getString("userInt", "0");
        //textViewNum.setText(result);
    }

    public void onClickAdvance(View v) {
        Integer tmpInt = Integer.parseInt(textViewNum.getText().toString());
        tmpInt++;
        textViewNum.setText(tmpInt.toString());
    }

    public void onClickSaveCount(View v) {
        String tempString = textViewNum.getText().toString();
        myDb.updateData("1", tempString);
    }

    @Override
    protected void onStop() {
        super.onStop();


        //SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        //SharedPreferences.Editor editor = settings.edit();
        //editor.putString("userInt", textViewNum.getText().toString());
        //System.out.println(textViewNum.getText().toString());

        //editor.commit();
    }
}
