package com.example.root.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.activity_main);

        Button enter = (Button) findViewById(R.id.button);

        // Listening to register new account link
        enter.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to ListView screen
                Intent i = new Intent(getApplicationContext(), AndroidSQLite.class);
                startActivity(i);
            }
        });
    }

}
