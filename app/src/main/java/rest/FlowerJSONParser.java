package rest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanjib on 12/8/2017.
 */

public class FlowerJSONParser {

    public static List<Flower> parserFeed(String content){

        try {
            JSONArray jsonArray = new JSONArray(content);
            List<Flower> flowerList = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jobj = jsonArray.getJSONObject(i);
                Flower flower = new Flower();
                flower.setProductId(jobj.getInt("productId"));
                flower.setName(jobj.getString("name"));
                flower.setCategory(jobj.getString("category"));
                flower.setInstruction(jobj.getString("instructions"));
                flower.setPhoto(jobj.getString("photo"));
                flower.setPrice(jobj.getDouble("price"));
                flowerList.add(flower);
            }

            return flowerList;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }//parserfeed

}//flower json parser
