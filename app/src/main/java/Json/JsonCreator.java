package Json;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by sanjib on 12/2/2017.
 */

public class JsonCreator {

    public static String createJson(Person person){
        String jsonString = null;
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", person.getName());
            jsonObject.put("age", person.getAge());
            jsonObject.put("bloodgroup", person.bloodgroup);

            JSONArray jsonArray = new JSONArray();
            for(int i = 0; i < person.getPersonContact().size(); i++){
                JSONObject jobj = new JSONObject();
                if(i == 0)
                jobj.put("phone", person.getPersonContact().get(i).getHomephnumber());
                if(i == 1)
                    jobj.put("phone", person.getPersonContact().get(i).getOfficephnumber());
                jsonArray.put(jobj);
            }
            jsonObject.put("contact", jsonArray);

            JSONArray jsonArray1 = new JSONArray();
            for(int i = 0; i < person.getPersonAddress().size(); i++){
                JSONObject jobj = new JSONObject();
                if(i == 0)
                jobj.put("address", person.getPersonAddress().get(i).getHomeAddress());
                if(i == 1)
                    jobj.put("address", person.getPersonAddress().get(i).getOfficeAddress());
                jsonArray1.put(jobj);
            }
            jsonObject.put("location", jsonArray1);

            jsonString = jsonObject.toString();
        }catch (Exception e){
            e.printStackTrace();
        }

        return jsonString;
    }//createJsonString

}//jsonCreator
