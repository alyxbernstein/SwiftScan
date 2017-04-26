package com.example.abernstein.swiftscanguitest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    public void applySettings(String name, int id){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("ID", id);
        intent.putExtra("NAME", name);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button done = (Button)findViewById(R.id.done);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applySettings("name", 0);
            }
        });
    }
}
