package com.example.lab5_project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    private final static String FILE_NAME = "content.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // сохранение файла
    public void saveText(View view) {

        FileOutputStream fos = null;
        String name = "Город: ";
        String size = "Численость: ";
        String def = "\n";

        try {
            EditText textBox = (EditText) findViewById(R.id.editor1);
            String text = textBox.getText().toString();
            fos = openFileOutput(FILE_NAME, MODE_APPEND);
            fos.write(name.getBytes());
            fos.write(text.getBytes());

            EditText textBox2 = (EditText) findViewById(R.id.editor2);
            String text2 = textBox2.getText().toString();
            fos = openFileOutput(FILE_NAME, MODE_APPEND);
            fos.write(size.getBytes());
            fos.write(text2.getBytes());
            fos.write(def.getBytes());


            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    // открытие файла
    public void openText(View view) {

        FileInputStream fin = null;
        TextView textView = (TextView) findViewById(R.id.text);
        try {
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            textView.setText(text);
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }


}
