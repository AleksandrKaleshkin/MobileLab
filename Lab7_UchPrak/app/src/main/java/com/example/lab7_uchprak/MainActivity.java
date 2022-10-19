package com.example.lab7_uchprak;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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

                Cursor cursor = mDb.rawQuery("SELECT * FROM clients", null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    product += cursor.getString(1) + " \n ";
                    cursor.moveToNext();
                }
                cursor.close();

                textView.setText(product);
            }
        });
        ArrayList<HashMap<String, Object>> clients = new ArrayList<HashMap<String, Object>>();

// Список параметров конкретного клиента
        HashMap<String, Object> client;

// Отправляем запрос в БД
        Cursor cursor = mDb.rawQuery("SELECT * FROM clients", null);
        cursor.moveToFirst();

// Пробегаем по всем клиентам
        while (!cursor.isAfterLast()) {
            client = new HashMap<String, Object>();

            // Заполняем клиента
            client.put("name",  cursor.getString(1));
            client.put("age",  cursor.getString(2));

            // Закидываем клиента в список клиентов
            clients.add(client);

            // Переходим к следующему клиенту
            cursor.moveToNext();
        }
        cursor.close();

// Какие параметры клиента мы будем отображать в соответствующих
// элементах из разметки adapter_item.xml
        String[] from = { "name", "age"};
        int[] to = { R.id.textView, R.id.textView2};

// Создаем адаптер
        SimpleAdapter adapter = new SimpleAdapter(this, clients, R.layout.adapter_item, from, to);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}

