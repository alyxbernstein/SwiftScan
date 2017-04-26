package com.example.abernstein.swiftscanguitest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public Button signIn;
    public Button signUp;
    public CheckBox remember;
    public EditText username;
    public EditText password;
    public TextView error;
    public Toolbar toolbar;

    public static final String IS_LOGGED_IN = "IsLoggedInPref";
    public static final String LOGIN = "LoginInfo";

    public boolean isLoggedInCheck() {
        //when app launches, it checks a stored boolean to see whether the user has previously logged on.
        //get boolean from storage
        SharedPreferences settings = getSharedPreferences(IS_LOGGED_IN, 0);
        return settings.getBoolean("rememberMe", false);

    }

    public void signUp(String username, String password, boolean isBoxChecked) {
        //TODO:encrypt user data
        SharedPreferences settings = getSharedPreferences(IS_LOGGED_IN, 0);
        SharedPreferences unpw = getSharedPreferences(LOGIN, 0);

        SharedPreferences.Editor settingsEditor = settings.edit();
        settingsEditor.putBoolean("rememberMe", isBoxChecked);
        settingsEditor.apply();

        SharedPreferences.Editor unpwEditor = unpw.edit();
        unpwEditor.putString("Username", username);
        unpwEditor.putString("Password", password);
        unpwEditor.apply();

        openNFCActivity();
    }

    public boolean checkUsername(String testUsername){
        SharedPreferences unpw = getSharedPreferences(LOGIN, 0);
        String storedUsername = unpw.getString("Username","FAIL");

        return testUsername.equalsIgnoreCase(storedUsername);

    }

    public boolean checkPassword(String testPassword){
        SharedPreferences unpw = getSharedPreferences(LOGIN, 0);
        String storedPassword = unpw.getString("Password","FAIL");

        return testPassword.equals(storedPassword);
    }

    public void signIn(String testPassword, String testUsername){
        if (checkPassword(testPassword)&&checkUsername(testUsername))
        {
            openNFCActivity();
        } else {
            error.setText("Your username or password was incorrect.");
        }
    }

    public void openNFCActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signIn = (Button) findViewById(R.id.signin);
        signUp = (Button) findViewById(R.id.signup);
        remember = (CheckBox) findViewById(R.id.rememberme);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        error = (TextView) findViewById(R.id.error);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        if (isLoggedInCheck()) {
            Context context = getApplicationContext();
            CharSequence text = "You are already signed in";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            openNFCActivity();
        } else {
            Context context = getApplicationContext();
            CharSequence text = "You are not signed in";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();
                boolean isBoxChecked = remember.isChecked();

                signUp(usernameText, passwordText, isBoxChecked);
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();
                signIn(usernameText, passwordText);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    
}