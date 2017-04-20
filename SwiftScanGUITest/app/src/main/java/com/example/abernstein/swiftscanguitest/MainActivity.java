package com.example.abernstein.swiftscanguitest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    // TODO:add toasts to test everything

    Button signIn;
    Button signUp;
    CheckBox remember;
    EditText username;
    EditText password;
    TextView error;
    Toolbar toolbar;


    public static final String IS_LOGGED_IN = "IsLoggedInPref";

    public boolean isLoggedInCheck() {
        //when app launches, it checks a stored boolean to see whether the user has previously logged on.
        //get boolean from storage
        SharedPreferences settings = getSharedPreferences(IS_LOGGED_IN, 0);
        return settings.getBoolean("isBoxChecked", false);

    }

    public void storeFile(String FILENAME, String DATA) throws IOException {

        FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
        fos.write(DATA.getBytes());
        fos.close();
    }

    public void signUp(String username, String password, boolean isBoxChecked) throws IOException {
        //TODO:encrypt user data

        SharedPreferences settings = getSharedPreferences(IS_LOGGED_IN, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("rememberMe", isBoxChecked);
        editor.apply();

        storeFile("UserName", username);
        storeFile("PassWord", password);

        FileInputStream fis = null;
        String storedUsername = "";
        try {
            fis = openFileInput("UserName");
            storedUsername = String.valueOf(fis.read());
            fis.close();

            String storedPassword = "";
            fis = openFileInput("PassWord");
            storedPassword = String.valueOf(fis.read());
            fis.close();


            System.err.println("storedUN: " + storedUsername);

            Context context = getApplicationContext();
            CharSequence text = "Sign Up Complete! Username: " + storedUsername + "Password: " + storedPassword;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        catch(Exception e)
        {
            Log.d("Exception", e.toString());
            e.printStackTrace();
        }

    }

    public boolean checkUsername(String testUsername){
        FileInputStream fis = null;
        String storedUsername = "";
        try {
            fis = openFileInput("UserName");
            storedUsername = String.valueOf(fis.read());
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (testUsername.equals(storedUsername)){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPassword(String testPassword){
        FileInputStream fis = null;
        String storedPassword = "";
        try {
            fis = openFileInput("PassWord");
            storedPassword = String.valueOf(fis.read());
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (testPassword.equals(storedPassword)){
            return true;
        } else {
            return false;
        }
    }

    public void signIn(String testPassword, String testUsername){
        if (checkPassword(testPassword)&&checkUsername(testUsername))
        {
            //TODO:launch next activity
            Context context = getApplicationContext();
            CharSequence text = "Sign In Complete! Username: " + "Password: ";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            error.setText("Your username or password was incorrect.");
            username.setText("");
            password.setText("");
            Context context = getApplicationContext();
            CharSequence text = "Sign In Failed! Username: " + "Password: ";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            //TODO: skip sign in to activity
        }

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameText = username.toString();
                String passwordText = password.toString();
                boolean isBoxChecked = remember.isChecked();
                try {
                    signUp(usernameText, passwordText, isBoxChecked);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //TODO: launch next activity
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameText = username.toString();
                String passwordText = password.toString();
                //TODO: launch next activity
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