package rest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by sanjib on 12/3/2017.
 */

public class InternetStatus {

    private Context context;

    public InternetStatus(Context context){
        this.context = context;
    }//constructor

    public boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnectedOrConnecting()){
            return true;
        }else{
            return  false;
        }

    }//isOnline

}//internet status
