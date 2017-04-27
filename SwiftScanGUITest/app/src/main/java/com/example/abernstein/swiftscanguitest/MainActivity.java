package com.example.abernstein.swiftscanguitest;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<String> listOfCompanies;
    public ArrayList<Company> companies;

    public void launchSettings(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void addNewCompany(Company c){
        companies.add(c);
        for (Company i : companies){
            listOfCompanies.add(companies.indexOf(i), i.getUserGivenName());
        }
    }


    //TODO: implement companies class properly by editing the instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String newName = intent.getStringExtra("NAME");
        int newID = intent.getIntExtra("ID", 0);

        Company newCompany;

        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);

        if (newID != 0){
            newCompany = new Company(this, newID, newName);
            addNewCompany(newCompany);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOfCompanies);
        final ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSettings();
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
