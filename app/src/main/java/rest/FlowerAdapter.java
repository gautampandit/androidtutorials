package rest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.neopixlab.androidtutorials.MainActivity;
import com.neopixlab.androidtutorials.R;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by sanjib on 12/8/2017.
 */

public class FlowerAdapter extends ArrayAdapter{

    private Context context;
    private List<Flower> flowerList;

    public FlowerAdapter(@NonNull Context context, @LayoutRes int resource, List<Flower> objects) {
        super(context, resource, objects);
        this.context = context;
        this.flowerList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_list, parent, false);

        Flower flower = flowerList.get(position);
        TextView tv = (TextView) view.findViewById(R.id.textView1);
        tv.setText(flower.getName());

        if(flower.getBitmap() != null){
            ImageView iv = (ImageView)view.findViewById(R.id.imageView1);
            iv.setImageBitmap(flower.getBitmap());
        }else{
            FlowerAndView container = new FlowerAndView();
            container.flower = flower;
            container.view = view;
            ImageLoader loader = new ImageLoader();
            loader.execute(container);
        }


        return view;
    }

    class FlowerAndView{
        public Flower flower;
        public View view;
        public Bitmap bitmap;

    }

    private class ImageLoader extends AsyncTask<FlowerAndView, Void, FlowerAndView> {

        @Override
        protected FlowerAndView doInBackground(FlowerAndView... params) {

            FlowerAndView container = params[0];
            Flower flower = container.flower;

            try{
                String imageurl = MainActivity.PHOTOS_BASE_URL + flower.getPhoto();
                InputStream in = (InputStream) new URL(imageurl).getContent();
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                flower.setBitmap(bitmap);
                in.close();
                container.bitmap = bitmap;
                return container;
            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(FlowerAndView flowerAndView) {
            ImageView iv = (ImageView)flowerAndView.view.findViewById(R.id.imageView1);
            iv.setImageBitmap(flowerAndView.bitmap);
            flowerAndView.flower.setBitmap(flowerAndView.bitmap);
        }//onpostexecute
    }//ImageLoader

}//flowerAdapter
