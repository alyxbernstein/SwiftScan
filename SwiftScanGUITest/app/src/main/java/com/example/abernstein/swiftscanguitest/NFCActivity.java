package com.example.abernstein.swiftscanguitest;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NFCActivity extends AppCompatActivity {

    public ArrayList<String> listOfUses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        

        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOfUses);
        final ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //launch new fragment to add settings
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                //TODO: launch new activity with different attributes based on what they select
                //get item at the position selected
                //String z = listView.getItemAtPosition(position).toString();
            }
        });

    }
}
