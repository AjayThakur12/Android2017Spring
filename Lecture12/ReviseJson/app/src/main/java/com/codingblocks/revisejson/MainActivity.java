package com.codingblocks.revisejson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvName, tvWebsite;
    ListView lvCentreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = (TextView) findViewById(R.id.tvName);
        tvWebsite = (TextView) findViewById(R.id.tvWebsite);
        lvCentreList = (ListView) findViewById(R.id.centreList);


        try {
            InputStream is = getAssets().open("test.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder tjSb = new StringBuilder();
            String buf = "";
            while ((buf = br.readLine()) != null) {
                tjSb.append(buf);
            }

            JSONObject testJsonObj = new JSONObject(tjSb.toString());
            tvName.setText(testJsonObj.getString("name"));
            tvWebsite.setText(testJsonObj.getString("website"));

            ArrayList<String> centreNames = new ArrayList<>();

            JSONArray centreArray = testJsonObj.getJSONArray("centres");
            for (int i = 0; i < centreArray.length(); i++) {
                centreNames.add(centreArray.getJSONObject(i).getString("name"));
                // To get course details
//                centreArray.getJSONObject(i).getJSONArray("courses").getString(0)
            }

            ArrayAdapter<String> centreAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    centreNames
            );

            lvCentreList.setAdapter(centreAdapter);



        } catch (Exception e) {
            // This is a bad practice
        }
    }
}
