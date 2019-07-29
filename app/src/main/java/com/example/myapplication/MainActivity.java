package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
//http://img3.wikia.nocookie.net/__cb20140511110240/simpsons/images/7/7c/Bart_Simpson_Season_25_Official.jpg

public class MainActivity extends AppCompatActivity {
    ImageView downloadimage;

    public void download (View view){

        final Integer i = Log.i("Activity", "button is pressed");
        imagedownloader task = new imagedownloader();
        Bitmap mybitmap = null;
        try {
            mybitmap = task.execute("https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png").get();
            downloadimage.setImageBitmap(mybitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        downloadimage.setImageBitmap(mybitmap);

    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         downloadimage = (ImageView) findViewById(R.id.imageview);

    }

    public class imagedownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection newconnection = (HttpURLConnection) url.openConnection();
                newconnection.connect();
                InputStream inputStream = newconnection.getInputStream();
                Bitmap mybitmap = BitmapFactory.decodeStream(inputStream);
              //  return mybitmap;

            }    catch(MalformedURLException e1)
                    {
                       e1.printStackTrace();
                    }
                 catch(IOException e1)
                {
                    e1.printStackTrace();
                }

              return null;
        }
    }
}
