package com.example.tyler.androiddatastorage;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    //referenced - https://developer.android.com/guide/topics/data/data-storage.l and used
    //a lot of code there for the
    //public static final String PREFS_NAME = "PrefsFile";

    TextView textViewNum;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewNum = (TextView) findViewById(R.id.textViewNumber);
        myDb = new DatabaseHelper(this);

        Cursor resultCursor = myDb.getData();
        if (resultCursor.getCount() == 0) {
            myDb.insertData("0");
        } else {
            resultCursor.moveToFirst();
            String tempString = resultCursor.getString(0);
            textViewNum.setText(tempString);
        }

        //restore the prefs
        //SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        //String result = settings.getString("userInt", "0");
        //textViewNum.setText(result);
    }

    public void onClickAdvance(View v) {
        Integer tmpInt = Integer.parseInt(textViewNum.getText().toString());
        tmpInt++;
        String tempString = tmpInt.toString();
        textViewNum.setText(tempString);
    }

    public void onClickSaveCount(View v) {
        String tempString = textViewNum.getText().toString();
        boolean isUpdated = myDb.updateData(tempString);

        if (isUpdated) {
            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Data NOT Inserted", Toast.LENGTH_LONG).show();
        }
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
