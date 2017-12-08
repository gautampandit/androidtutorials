package rest;


import android.util.Base64;

import java.io.BufferedReader;
import java.io.InputStream;
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
        StringBuffer response = null;
//        StringBuilder sb = null;
        try {
            URL url = new URL(uri);
            client = (HttpURLConnection) url.openConnection();
            client.setRequestMethod("GET");
            client.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = client.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                //with stringbuffer (StringBuffer is synchronized)
                response = new StringBuffer();
                String inputLine = null;
                while((inputLine = in.readLine()) != null){
                    response.append(inputLine);
                }

                //with StringBuidler (StringBuilder is not synchronized)
//                sb = new StringBuilder();
//                while((inputLine = in.readLine()) != null){
//                    sb.append(inputLine +"\n");
//                }

                in.close();

            }
            return response.toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if(client != null) {
                client.disconnect();
            }
        }

    }//getData

    public static String getData(String uri, String userName, String password){
        HttpURLConnection client = null;
        System.out.println("Hola "+ userName +"  "+ password);
        byte[] loginBytes = (userName +":"+password).getBytes();
        StringBuilder loginbuilder = new StringBuilder().append("Basic ").append(Base64.encode(loginBytes, Base64.DEFAULT));
        System.out.println("Hola "+ loginbuilder.toString());
        StringBuffer response = null;
//        StringBuilder sb = null;
        try {
            URL url = new URL(uri);

            client = (HttpURLConnection) url.openConnection();
            client.addRequestProperty("AUTHORIZATION", loginbuilder.toString());
            client.setRequestMethod("GET");
            client.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = client.getResponseCode();
            System.out.println("Hola "+ responseCode);
            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                //with stringbuffer (StringBuffer is synchronized)
                response = new StringBuffer();
                String inputLine = null;
                while((inputLine = in.readLine()) != null){
                    response.append(inputLine);
                }

                //with StringBuidler (StringBuilder is not synchronized)
//                sb = new StringBuilder();
//                while((inputLine = in.readLine()) != null){
//                    sb.append(inputLine +"\n");
//                }

                in.close();

            }
            return response.toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if(client != null) {
                client.disconnect();
            }
        }

    }//getData

}//Httpmanager
