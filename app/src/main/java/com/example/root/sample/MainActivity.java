package com.example.root.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
String msg="++++++";
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
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
    }




    public void onBackPressed() {
        // Write your code here
        moveTaskToBack(true);
         MainActivity.this.finish();
        // Switching to ListView screen
       // Intent i = new Intent(getApplicationContext(), MainActivity.class);
        //startActivity(i);     //either save instance checkout how
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "The onStart() event");
    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();

        Log.d(msg, "The onResume() event");
    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(msg, "The onPause() event");
    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg, "The onStop() event");
    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(msg, "The onDestroy() event");
    }

}
