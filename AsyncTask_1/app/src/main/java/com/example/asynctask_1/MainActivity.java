package com.example.asynctask_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView txtState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        txtState = (TextView) findViewById(R.id.textView);
    }

    public void onProgressButton(View view){
        new MyProgressBarAsynctask().execute();
    }

    class MyProgressBarAsynctask extends AsyncTask<Void, Integer, Void> {

        private  int progressBarValue = 0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Начало процесса", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Void o) {
            super.onPostExecute(o);
            Toast.makeText(MainActivity.this, "Конец процесса", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            txtState.setText(values[0]+ "%");
        }

        @Override
        protected Void doInBackground(Void... objects) {
            while (progressBarValue<100){
                progressBarValue++;
                publishProgress(progressBarValue);
                SystemClock.sleep(200);
            }

            return null;
        }
    }
    }
