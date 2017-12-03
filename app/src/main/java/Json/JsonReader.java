package Json;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by sanjib on 12/3/2017.
 */

public class JsonReader {

    public static void readJsonString(String jsonString){
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            String name = jsonObject.getString("name");
            String age = jsonObject.getString("age");
            String bloodgrp = jsonObject.getString("bloodgroup");

            String phhome = null;
            String phOffice = null;
            JSONArray jsonArray1 = jsonObject.getJSONArray("contact");
            for (int i = 0; i < jsonArray1.length(); i++){
                JSONObject jobj1 = jsonArray1.getJSONObject(i);
                if(i==0)
                phhome = jobj1.getString("phone");
                if(i==1)
                    phOffice = jobj1.getString("phone");
            }


            System.out.println("Test Data : "+"JsonReader : "+ name  +"   "+age+"   "+bloodgrp + "   "+
                                phhome +"   "+phOffice);
        }catch (Exception e){
            e.printStackTrace();
        }
    }//readJsonString

}//json Reader
