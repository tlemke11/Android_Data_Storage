package com.example.tyler.androiddatastorage;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    //referenced - https://developer.android.com/guide/topics/data/data-storage.html and used
    //a lot of code there for the
    public static final String PREFS_NAME = "PrefsFile";

    EditText editTextNumber;
    Button btnSaveCount, btnAdvance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextNumber = (EditText) findViewById(R.id.editTextNumber);


        //restore the prefs
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String result = settings.getString("userInt", "0");
        editTextNumber.setText(result);
    }

    public void onClickAdvance(View v) {

        Integer tmpInt = Integer.parseInt(editTextNumber.getText().toString());
        tmpInt++;
        editTextNumber.setText(tmpInt.toString());
    }

    @Override
    protected void onStop() {
        super.onStop();


        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("userInt", editTextNumber.getText().toString());
        System.out.println(editTextNumber.getText().toString());

        editor.commit();
    }
}
