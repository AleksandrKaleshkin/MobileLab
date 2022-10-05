package ru.siurgtu.p0011firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView resultText;
    private EditText number_kg;
    private Button translate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.resultText);
        number_kg = findViewById(R.id.number_kg);
        translate = findViewById(R.id.translate);

        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float num1 = Float.parseFloat(number_kg.getText().toString());
                double res = num1 * 2.2 ;
                resultText.setText(String.valueOf(res));



            }
        });
    }




}





