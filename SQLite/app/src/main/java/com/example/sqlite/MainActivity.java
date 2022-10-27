package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    // Объявим переменные компонентов
    Button button;
    TextView textView;

    // Переменная для работы с БД
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDBHelper = new DatabaseHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

        // Найдем компоненты в XML разметке
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        // Пропишем обработчик клика кнопки
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product = "";

                Cursor cursor = mDb.rawQuery("SELECT * FROM groupmate", null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    product +=cursor.getString(0) + ". Фамилия: " +cursor.getString(1) + " Имя: " +cursor.getString(2) + "\nОтчество: " + cursor.getString(3)+ " Время: " + cursor.getString(4) +"\n\n";
                    cursor.moveToNext();
                }
                cursor.close();

                textView.setText(product);
            }
        });


    }
        public void BtnClick(View view) {
            EditText secName, firName, patronymic, time;
            String _secName, _firName, _patronymic, _time;


            secName = (EditText) findViewById(R.id.secName);
            _secName = secName.getText().toString();
            firName = (EditText) findViewById(R.id.firName);
            _firName = firName.getText().toString();
            patronymic = (EditText) findViewById(R.id.patr);
            _patronymic = patronymic.getText().toString();
            time = (EditText) findViewById(R.id.time);
            _time = time.getText().toString();


            String data = "INSERT INTO groupmate (id, secondName, firstName, patronymic, time) VALUES (_secName, _firName, _patronymic, _time)";
            mDb.execSQL(data);


        }
    }

