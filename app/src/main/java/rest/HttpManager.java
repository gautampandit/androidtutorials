package rest;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sanjib on 12/4/2017.
 */

public class HttpManager {

    private static final String USER_AGENT = "AndroidAgent";

    public static String getData(String uri){
        HttpURLConnection client = null;
        String inputLine = null;
        try {
            URL url = new URL(uri);
            client = (HttpURLConnection) url.openConnection();
            client.setRequestMethod("GET");
            client.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = client.getResponseCode();
            System.out.println("Hola response code "+responseCode );
            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                StringBuffer response = new StringBuffer();
                while((inputLine = in.readLine()) != null){
                    response.append(inputLine);
                    System.out.println("Hola yes");
                }
                in.close();

            }
            return inputLine;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            client.disconnect();
        }

    }//getData

}//Httpmanager
