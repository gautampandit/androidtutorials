package com.neopixlab.androidtutorials;

import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import Json.JsonCreator;
import Json.JsonReader;
import Json.PersonData;
import rest.Flower;
import rest.FlowerAdapter;
import rest.FlowerJSONParser;
import rest.FlowerXMLParser;
import rest.HttpManager;
import rest.InternetStatus;

public class MainActivity extends ListActivity {

    private TextView output;
    private Button doTask;
    private ProgressBar progressBar;
    private List<MyTask> tasks;
    private InternetStatus internetStatus;
    private String xmlContentUri = "http://services.hanselandpetal.com/feeds/flowers.xml";
    private String jsonContentUri = "http://services.hanselandpetal.com/feeds/flowers.json";

    private String xmlContentUri_secure = "http://services.hanselandpetal.com/secure/flowers.xml";
    private String jsonContentUri_secure = "http://services.hanselandpetal.com/secure/flowers.json";

    public static final String PHOTOS_BASE_URL = "http://services.hanselandpetal.com/photos/";

    private List<Flower> flowerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        internetStatus = new InternetStatus(this);

        tasks = new ArrayList<MyTask>();

//        output = (TextView)findViewById(R.id.textView);
//        output.setMovementMethod(new ScrollingMovementMethod());

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);


        doTask = (Button)findViewById(R.id.button);
        doTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(internetStatus.isOnline()){
                   // requestData(xmlContentUri);
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


    private void updateDisplay(){
//        if(flowerList != null){
//            for(Flower flower : flowerList){
//                output.append(flower.getName() + "\n");
//            }
//        }
        FlowerAdapter adapter = new FlowerAdapter(this, R.layout.item_list, flowerList);
        setListAdapter(adapter);
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

    private class MyTask extends AsyncTask<String, String, List<Flower>>{

        @Override
        protected void onPreExecute() {
 //           updateDisplay("Starting Task");
            if(tasks.size() == 0){
                progressBar.setVisibility(View.VISIBLE);
                tasks.add(this);
            }

        }//onPreexecute

        @Override
        protected List<Flower> doInBackground(String... params) {
   //         String content = HttpManager.getData(params[0]);
            String content = HttpManager.getData(params[0]);
            flowerList = FlowerJSONParser.parserFeed(content);
//            for (Flower flower: flowerList ) {
//                try{
//                    String imageurl = PHOTOS_BASE_URL + flower.getPhoto();
//                    InputStream in = (InputStream) new URL(imageurl).getContent();
//                    Bitmap bitmap = BitmapFactory.decodeStream(in);
//                    flower.setBitmap(bitmap);
//                    in.close();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
            return flowerList;
        }//doInback

        @Override
        protected void onPostExecute(List<Flower> flower) {

            tasks.remove(this);
            if(tasks.size() == 0){
                progressBar.setVisibility(View.GONE );
            }

            if(flower == null){
                Toast.makeText(MainActivity.this, "Can't connect to web service", Toast.LENGTH_SHORT).show();
                return;
            }
      //      flowerList = FlowerXMLParser.parseFeed(s);
      //      flowerList = FlowerJSONParser.parserFeed(s);
            updateDisplay();


        }//onPostExecute

        @Override
        protected void onProgressUpdate(String... values) {
  //          updateDisplay(values[0]);
        }//onProgressUpdate
    } //mytask


}//MainActivity
