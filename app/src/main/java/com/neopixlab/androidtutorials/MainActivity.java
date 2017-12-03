package com.neopixlab.androidtutorials;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Json.JsonCreator;
import Json.JsonReader;
import Json.PersonData;
import rest.HttpManager;
import rest.InternetStatus;

public class MainActivity extends AppCompatActivity {

    private TextView output;
    private Button doTask;
    private ProgressBar progressBar;
    private List<MyTask> tasks;
    private InternetStatus internetStatus;
    private String xmlContentUri = "http://services.hanselandpetal.com/feeds/flowers.xml";
    private String jsonContentUri = "http://services.hanselandpetal.com/feeds/flowers.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        internetStatus = new InternetStatus(this);

        tasks = new ArrayList<MyTask>();

        output = (TextView)findViewById(R.id.textView);
        output.setMovementMethod(new ScrollingMovementMethod());

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        doTask = (Button)findViewById(R.id.button);
        doTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(internetStatus.isOnline()){
                    requestData(jsonContentUri);
                }else{
                    Toast.makeText(MainActivity.this, "No Internet ", Toast.LENGTH_SHORT).show();
                }

            }
        });

  //      createJson();  //createJsonData

    }//onCreate

    private void requestData(String uri) {
      //  new MyTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, uri);
        new MyTask().execute(uri);
    }//requestData


    private void updateDisplay(String message){
        output.append(message + "\n");
    }//update Display

    private void createJson(){
        String jsonStringToPrint = null;
        PersonData pd = new PersonData();
        pd.createPersonData();
        if(pd.getPersonObj() != null){
            jsonStringToPrint = JsonCreator.createJson(pd.getPersonObj());
        }
        System.out.println("Test Data : "+jsonStringToPrint);
        System.out.println("*********************************************");
        JsonReader.readJsonString(jsonStringToPrint);
    }//createJson

    private class MyTask extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            updateDisplay("Starting Task");
            if(tasks.size() == 0){
                progressBar.setVisibility(View.VISIBLE);
                tasks.add(this);
            }

        }//onPreexecute

        @Override
        protected String doInBackground(String... params) {
            String content = HttpManager.getData(params[0]);
            return content;
        }//doInback

        @Override
        protected void onPostExecute(String s) {
            updateDisplay(s);
            tasks.remove(this);
            if(tasks.size() == 0){
                progressBar.setVisibility(View.GONE );
            }

        }//onPostExecute

        @Override
        protected void onProgressUpdate(String... values) {
            updateDisplay(values[0]);
        }//onProgressUpdate
    } //mytask


}//MainActivity
