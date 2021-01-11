package com.example.minim2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ErrorView extends AppCompatActivity {

    private String errorMessage;
    private TextView textError;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_view);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        errorMessage= getIntent().getExtras().getString("Error");

        textError=findViewById(R.id.textError);
        textError.setText(errorMessage);
    }
}