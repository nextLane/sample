package com.example.root.sample;

/**
 * Created by root on 30/3/15.
 */
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AndroidSQLite extends Activity {
    private static String url_all_forms = "http://10.100.62.145:80/surveyor/android_connect/get_all_forms.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_FORMS = "forms";
    private static final String TAG_FID = "fid";
    private static final String TAG_TITLE = "title";
    private static final String TAG_XML = "xml";

    // products JSONArray
    JSONArray products = null;
    private SQLiteAdapter mySQLiteAdapter;

    public void onBackPressed() {
        // Write your code here
        //moveTaskToBack(true);
        // RunForm.this.finish();
        // Switching to ListView screen
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);     //either save instance checkout how
    }
    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ListView listContent = (ListView) findViewById(R.id.contentlist);
        listContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                TextView txtTechCharacteristic = (TextView) v.findViewById(R.id.id);
                String txt = (String) txtTechCharacteristic.getText();
                Intent i = new Intent(AndroidSQLite.this, RunForm.class);

                   i.putExtra("ID", txt);

                startActivity(i);
            }


        });
      /*
       *  Create/Open a SQLite database
       *  and fill with dummy content
       *  and close it
       */
        mySQLiteAdapter = new SQLiteAdapter(this);
        mySQLiteAdapter.openToWrite();
        mySQLiteAdapter.deleteAll();
        String xml="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<xmlgui>\n" +
                "<form id=\"1\" name=\"Robotics Club Registration\" submitTo=\"http://servername/xmlgui1-post.php\" ><field label=\"First Name\" type=\"text\" required=\"Y\" options=\"\"/><field label=\"Last Name\" type=\"text\" required=\"Y\" options=\"\"/>\n" +
                "<field label=\"Gender\" type=\"choice\" required=\"Y\" options=\"Male|Female|sdksfnekfn\"/>\n" +
                "<field label=\"Age on 15 Oct. 2010\" type=\"numeric\" required=\"N\" options=\"\"/>\n" +
                "<field label=\"checkbox\" type=\"CheckBox\" required=\"Y\" options=\"meoww|bowwwow|sfnjdnkjdbv\"/>\n" +
                "<field label=\"checkboxmeoww\" type=\"CheckBox2\" required=\"Y\" options=\"meoww|bowwwow|sfnjdnkjdbv\"/>\n" +
                "</form>\n" +
                "</xmlgui>\n";
       // xml = xml.replace("\n", "\\n").replace("\r", "\\r");

      String jStr = "{\"forms\":[{\"fid\":\"50\",\"title\":\"bmjana\",\"xml\":\"okiedokie\",\"updated_at\":\"2015-03-27 19:53:26\"},{\"fid\":\"55\",\"title\":\"Men Health\",\"xml\":\"wokeydokey\",\"updated_at\":\"2015-03-27 19:53:50\"},{\"fid\":\"58\",\"title\":\"child Health\",\"xml\":\"oladola\",\"updated_at\":\"2015-03-27 19:53:50\"}],\"success\":1}";

        //String jStr='{"forms":[{"fid":"1","title":"Agriculture Survey","xml":"<?xml version='1.0' encoding='utf-8'?><xmlgui><form id='1' name='Robotics Club Registration' submitTo='http:\/\/servername\/xmlgui-post.php'><field label='First Name' type='text' required='Y' options=''\/><field label='Last Name' type='text' required='Y' options=''\/><field label='Gender' type='choice' required='Y' options='Male|Female|sdksfnekfn'\/><field label='Age on 15 Oct. 2010' type='numeric' required='N' options=''\/><field label='checkbox' type='CheckBox' required='Y' options='meoww|bowwwow|sfnjdnkjdbv'\/><field label='checkboxmeoww' type='CheckBox2' required='Y' options='meoww|bowwwow|sfnjdnkjdbv'\/><\/form><\/xmlgui>'","updated_at":"2015-03-31 17:15:06"},{"fid":"2","title":"Women Health","xml":"<?xml version='1.0' encoding='utf-8'?><xmlgui><form id='1' name='Robotics Club Registration' submitTo='http:\/\/servername\/xmlgui-post.php'><field label='First Name' type='text' required='Y' options=''\/><field label='Last Name' type='text' required='Y' options=''\/><field label='Gender' type='choice' required='Y' options='Male|Female|sdksfnekfn'\/><field label='Age on 15 Oct. 2010' type='numeric' required='N' options=''\/><field label='checkbox' type='CheckBox' required='Y' options='meoww|bowwwow|sfnjdnkjdbv'\/><field label='checkboxmeoww' type='CheckBox2' required='Y' options='meoww|bowwwow|sfnjdnkjdbv'\/><\/form><\/xmlgui>'","updated_at":"2015-03-31 17:15:15"}],"success":1}';

        Log.d("jjjj:",""+jStr);
        JSONObject json = null;
        try {
            json = new JSONObject(jStr);
            Log.d("jjjj:",""+json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Check your log cat for JSON reponse
        Log.d("All Forms: ", json.toString());


        try {
            // Checking for SUCCESS TAG
            int success = 0;
            try {
                success = json.getInt(TAG_SUCCESS);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (success == 1) {
                // products found
                // Getting Array of Products
                try {
                    products = json.getJSONArray(TAG_FORMS);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // looping through All Products from table
                for (int i = 0; i < products.length(); i++) {
                    JSONObject c = products.getJSONObject(i);

                    // Storing each json item in variable
                    String id = c.getString(TAG_FID);
                    Log.d("******: ", "preInserting .." + id);
                    //   Log.d("ididid: ", ""+db.getForm(Integer.parseInt(id)).getXML());

                    //if(db.getForm(Integer.parseInt(id))==null) {
                    String name = c.getString(TAG_TITLE);

                    String xmll = c.getString(TAG_XML);
                    // creating new HashMap
                    Log.d("++++Insert: ", "Inserting ..");
                    mySQLiteAdapter.insert(id,name ,xmll);


//                        }


                }
            } else {
                // no products found
                // Launch Add New product Activity
                //                    Intent i = new Intent(getApplicationContext(),
                //                    NewProductActivity.class);
                //          // Closing all previous activities
                //        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //      startActivity(i);
            }


 /*           mySQLiteAdapter.insert("A for Apply");
            mySQLiteAdapter.insert("B for Boy");
            mySQLiteAdapter.insert("C for Cat");
            mySQLiteAdapter.insert("D for Dog");
            mySQLiteAdapter.insert("E for Egg");
            mySQLiteAdapter.insert("F for Fish");
            mySQLiteAdapter.insert("G for Girl");
            mySQLiteAdapter.insert("H for Hand");
            mySQLiteAdapter.insert("I for Ice-scream");
            mySQLiteAdapter.insert("J for Jet");
            mySQLiteAdapter.insert("K for Kite");
            mySQLiteAdapter.insert("L for Lamp");
            mySQLiteAdapter.insert("M for Man");
            mySQLiteAdapter.insert("N for Nose");
            mySQLiteAdapter.insert("O for Orange");
            mySQLiteAdapter.insert("P for Pen");
            mySQLiteAdapter.insert("Q for Queen");
            mySQLiteAdapter.insert("R for Rain");
            mySQLiteAdapter.insert("S for Sugar");
            mySQLiteAdapter.insert("T for Tree");
            mySQLiteAdapter.insert("U for Umbrella");
            mySQLiteAdapter.insert("V for Van");
            mySQLiteAdapter.insert("W for Water");
            mySQLiteAdapter.insert("X for X'mas");
            mySQLiteAdapter.insert("Y for Yellow");
            mySQLiteAdapter.insert("Z for Zoo");
*/
            mySQLiteAdapter.close();

      /*
       *  Open the same SQLite database
       *  and read all it's content.
       */
            mySQLiteAdapter = new SQLiteAdapter(this);
            mySQLiteAdapter.openToRead();

            Cursor cursor = mySQLiteAdapter.queueAll();
            startManagingCursor(cursor);

            String[] from = new String[]{SQLiteAdapter.KEY_CONTENT,SQLiteAdapter.KEY_ID};
            int[] to = new int[]{R.id.text , R.id.id};

            SimpleCursorAdapter cursorAdapter =
                    new SimpleCursorAdapter(this, R.layout.row, cursor, from, to);

            listContent.setAdapter(cursorAdapter);

            mySQLiteAdapter.close();


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}