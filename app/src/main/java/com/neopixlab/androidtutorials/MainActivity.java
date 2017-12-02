package com.neopixlab.androidtutorials;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Json.JsonCreator;
import Json.PersonData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createJson();  //createJsonData

    }//onCreate

    private void createJson(){
        String jsonStringToPrint = null;
        PersonData pd = new PersonData();
        pd.createPersonData();
        if(pd.getPersonObj() != null){
            jsonStringToPrint = JsonCreator.createJson(pd.getPersonObj());
        }
        System.out.println("Test Data : "+jsonStringToPrint);
    }//createJson

}//MainActivity
