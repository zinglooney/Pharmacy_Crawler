package com.example.spyros.pharmacy_crawler;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.jsoup.Jsoup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
            private Button getBtn;
            private TextView result;



     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         result = (TextView) findViewById(R.id.result);
         getBtn=(Button)findViewById(R.id.getBtn);
         getBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 getWebsite();
             }
         });
     }
     private void getWebsite(){
         new Thread(new Runnable() {
             @Override
             public void run() {
                 final StringBuilder builder=new StringBuilder();


              try{
                  Document doc = Jsoup.connect("https://www.vrisko.gr/efimeries-farmakeion/patra").get();
                 String title;

                  for (Element row : doc.select("div.ResultLeft")) {
                      title=row.select("div[style*=padding-bottom:6px;]").text();
                      final String phone=row.select("span.SpPhone").text();
                      final String address=row.select("div.ResultAddr").text();
                      builder.append("\n").append("Όνομα : ").append(title ).append("\n").append("\t")
                                        .append("Τηλέφωνο: ").append(phone).append("\n").append("\t")
                                        .append("Διεύθυνση : ").append(address).append("\t").append("\n");

                  }

              }
              catch (Exception e){e.printStackTrace();}

              runOnUiThread(new Runnable(){
         @Override
                 public void run(){
             result.setText(builder.toString());
       }
   });
            }
         }).start();
     }
}


