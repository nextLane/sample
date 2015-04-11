package com.example.root.sample;

/**
 * Created by root on 30/3/15.
 */
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AndroidSQLite extends Activity {
    private static String url_all_forms = "http://192.168.64.1:80/team14/get_all_forms.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_FORMS = "forms";
    private static final String TAG_FID = "fid";
    private static final String TAG_TITLE = "title";
    private static final String TAG_XML = "xml";
    String ngoid = "";
    String volunteer_id = "";
    // products JSONArray


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


        Intent startingIntent = getIntent();
        if (startingIntent == null) {

            finish();
            return;
        }

        ngoid = startingIntent.getStringExtra("ngoid");
        volunteer_id = startingIntent.getStringExtra("volid");
        listContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                TextView txtTechCharacteristic = (TextView) v.findViewById(R.id.id);
                String txt = (String) txtTechCharacteristic.getText();
                Intent i = new Intent(AndroidSQLite.this, RunForm.class);

                i.putExtra("ID", txt);
                i.putExtra("ngoID", ngoid);
                i.putExtra("volid", volunteer_id);
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
//        String xml="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
        //              "<xmlgui>\n" +
        //            "<form id=\"1\" name=\"Robotics Club Registration\" submitTo=\"http://servername/xmlgui1-post.php\" ><field label=\"First Name\" type=\"text\" required=\"Y\" options=\"\"/><field label=\"Last Name\" type=\"text\" required=\"Y\" options=\"\"/>\n" +
        //          "<field label=\"Gender\" type=\"choice\" required=\"Y\" options=\"Male|Female|sdksfnekfn\"/>\n" +
        //        "<field label=\"Age on 15 Oct. 2010\" type=\"numeric\" required=\"N\" options=\"\"/>\n" +
        //      "<field label=\"checkbox\" type=\"CheckBox\" required=\"Y\" options=\"meoww|bowwwow|sfnjdnkjdbv\"/>\n" +
        //    "<field label=\"checkboxmeoww\" type=\"CheckBox2\" required=\"Y\" options=\"meoww|bowwwow|sfnjdnkjdbv\"/>\n" +
        //  "</form>\n" +
        //"</xmlgui>\n";
        // xml = xml.replace("\n", "\\n").replace("\r", "\\r");

//        String jStr = "{\"forms\":[{\"fid\":\"50\",\"title\":\"bmjana\",\"xml\":\"okiedokie\",\"updated_at\":\"2015-03-27 19:53:26\"},{\"fid\":\"55\",\"title\":\"Men Health\",\"xml\":\"wokeydokey\",\"updated_at\":\"2015-03-27 19:53:50\"},{\"fid\":\"58\",\"title\":\"child Health\",\"xml\":\"oladola\",\"updated_at\":\"2015-03-27 19:53:50\"}],\"success\":1}";

        //String jStr='{"forms":[{"fid":"1","title":"Agriculture Survey","xml":"<?xml version='1.0' encoding='utf-8'?><xmlgui><form id='1' name='Robotics Club Registration' submitTo='http:\/\/servername\/xmlgui-post.php'><field label='First Name' type='text' required='Y' options=''\/><field label='Last Name' type='text' required='Y' options=''\/><field label='Gender' type='choice' required='Y' options='Male|Female|sdksfnekfn'\/><field label='Age on 15 Oct. 2010' type='numeric' required='N' options=''\/><field label='checkbox' type='CheckBox' required='Y' options='meoww|bowwwow|sfnjdnkjdbv'\/><field label='checkboxmeoww' type='CheckBox2' required='Y' options='meoww|bowwwow|sfnjdnkjdbv'\/><\/form><\/xmlgui>'","updated_at":"2015-03-31 17:15:06"},{"fid":"2","title":"Women Health","xml":"<?xml version='1.0' encoding='utf-8'?><xmlgui><form id='1' name='Robotics Club Registration' submitTo='http:\/\/servername\/xmlgui-post.php'><field label='First Name' type='text' required='Y' options=''\/><field label='Last Name' type='text' required='Y' options=''\/><field label='Gender' type='choice' required='Y' options='Male|Female|sdksfnekfn'\/><field label='Age on 15 Oct. 2010' type='numeric' required='N' options=''\/><field label='checkbox' type='CheckBox' required='Y' options='meoww|bowwwow|sfnjdnkjdbv'\/><field label='checkboxmeoww' type='CheckBox2' required='Y' options='meoww|bowwwow|sfnjdnkjdbv'\/><\/form><\/xmlgui>'","updated_at":"2015-03-31 17:15:15"}],"success":1}';

         String jStr= "{\"forms\":[{\"fid\":\"1\",\"title\":\"Agriculture Survey\",\"xml\":\"oa?xml version='1.0' encoding='utf-8'?caoaxmlguicaoaform id='1' name='RoboticsClubRegistration'caoafield label='FirstName' type='text' required='Y' options='' cbaoafield label='LastName' type='text' required='Y' options='' cbaoafield label='Gender' type='choice' required='Y' options='Male|Female' cbaoafield label='Age' type='numeric' required='N' options='' cbaoafield label='checkbox' type='CheckBox' required='Y' options='X|XII|Diploma' cbaoafield label='checkboxmeoww' type='CheckBox2' required='Y' options='Minor|Major|NA' cba obaformca obaxmlguica\",\"updated_at\":\"2015-04-06 03:07:57\"},{\"fid\":\"2\",\"title\":\"Women Health\",\"xml\":\"oa?xml version='1.0' encoding='utf-8'?caoaxmlguicaoaform id='1' name='RoboticsClubRegistration'caoafield label='FirstName' type='text' required='Y' options='' cbaoafield label='LastName' type='text' required='Y' options='' cbaoafield label='Gender' type='choice' required='Y' options='Male|Female|sdksfnekfn' cbaoafield label='Ageon15Oct2010' type='numeric' required='N' options='' cbaoafield label='checkbox' type='CheckBox' required='Y' options='code|teach|cook' cbaoafield label='checkboxmeoww' type='CheckBox2' required='Y' options='Stressed|Contended|Very Happy' cba obaformca obaxmlguica\",\"updated_at\":\"2015-04-06 03:08:05\"}],\"success\":1}";
        Log.d("jjjj:",""+jStr);
        JSONObject json = null;
        JSONParser jp = new JSONParser();
        //Log.d("MMMMMMM","RRRRRR");
        url_all_forms += "?ngoid=" + ngoid + "&volunteer_id=" + volunteer_id;

        //json = jp.makeHttpRequest(url_all_forms);
        //Log.d("WWWWWWW", "YYYYY");

        try {
          json = new JSONObject(jStr);
         Log.d("jjjj:", "" + json);
        }
        catch (JSONException e) {
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
                    mySQLiteAdapter.insert(id, name, xmll);


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

            String[] from = new String[]{SQLiteAdapter.KEY_CONTENT, SQLiteAdapter.KEY_ID};
            int[] to = new int[]{R.id.text, R.id.id};

            SimpleCursorAdapter cursorAdapter =
                    new SimpleCursorAdapter(this, R.layout.row, cursor, from, to);

            listContent.setAdapter(cursorAdapter);

            mySQLiteAdapter.close();


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sync, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//noinspection SimplifiableIfStatement
        if (id == R.id.sync) {

            SQLiteAdapter sla = new SQLiteAdapter(this);
            ArrayList<String> p = sla.getAllTable();


            Log.d("xxxxxxxxx:", "Entered");
            SQLiteDatabase newDB = null;
            JSONObject jObject;
            JSONArray jArray = new JSONArray();
            //String dbPath= "/data/data/"
            //        + getApplicationContext().getPackageName() + "/databases/"
            //      + "MY_DATABASE";

            try {
                newDB = sla.openToWrite();
                Log.d("dbdbdb:", newDB.toString());
            } catch (SQLException e) {
                Log.d("Error", "Error while Opening Database");
                e.printStackTrace();


            }


            for (int y = 0; y < p.size(); y++) {
                Log.d("TTTTNNNN:", p.get(y));
            }


            int i = 0;
            if (!p.isEmpty()) {
                while (i < p.size()) {
                    //  Toast.makeText(activityName.this, "Table Name=> " + c.getString(0), Toast.LENGTH_LONG).show();

                    //    Log.d("Tblllll:", cq.getString(0));
                    try {

                        //we need ngo id and form id to be passed as params to php


                        // Cursor c = newDB.rawQuery("Select * from " + p.get(i) + " where synced = '0'", null);
                        Cursor c = newDB.rawQuery("Select * from " + p.get(i) + " where synced = 0", null);

                        //Log.d("Tblllll:", cq.getString(0));
                        int cnt = c.getColumnCount();
                        if (c.getCount() == 0) {
                            c.close();
                            Log.d("", "no items on the table");
                        } else {
                            c.moveToFirst();

                            int j = 1;

                            while (c.moveToNext()) {
                                jObject = new JSONObject();

                                for (j = 1; j <= cnt; j++) {
                                    jObject.put("" + j, c.getString(j - 1));

                                }

                                jArray.put(jObject);


                            }

                            c.close();
                            Log.d("", "ALL the data in the table" + jArray.toString());
                            int arrayLength = jArray.length();

                            Log.d("", "Length of the jArray" + arrayLength);
                            // HttpParams httpParams = new BasicHttpParams();
                            //HttpConnectionParams.setConnectionTimeout(httpParams, 9000);
                            //HttpConnectionParams.setSoTimeout(httpParams, 9000);

                            //HttpClient client = new DefaultHttpClient(httpParams);

                            String url = "http://192.168.64.1:80/team14/updateDatabase.php?arrayLength=" + arrayLength + "&colcount=" + (cnt - 1) + "&ngoid_formid=" + p.get(i);

                            //HttpPost request = new HttpPost(url);
                            //request.setEntity(new ByteArrayEntity(jArray.toString().getBytes("UTF8")));
                            //request.setHeader("json", jArray.toString());
                            //Log.d("Beforeeee:", "eeeee");
                            //HttpResponse response = client.execute(request);

                            // HttpEntity entity = response.getEntity();
                            // If the response does not enclose an entity, there is no need
                            //Log.d("Afterrrr:", "" + entity);

//                                Toast.makeText(getApplicationContext(), "Synced, submitted!",
                            //                                      Toast.LENGTH_SHORT).show();

                            //if (entity != null) {
                            //  InputStream instream = entity.getContent();


                            //String result = getStringFromInputStream(instream);
                            //if (result.equalsIgnoreCase("true")) {

                            //  Log.d("Synciinnnng", "1");

                            //newDB.rawQuery("UPDATE " + p.get(i) + " SET synced= 1", null);


                            //update the cols to 1
                            //  }
                            // Log.i("Read from server", result);
                            //}
                        }
                    } //catch (UnsupportedEncodingException uee) {
                        //  Log.d("Exceptions", "UnsupportedEncodingException");
                        //uee.printStackTrace();
                        //} catch (Throwable t) {
                        //  Log.d("", "request fail" + t.toString());
                //         }
                catch (JSONException e) {
                        e.printStackTrace();
                    }


                    i++;
                        // ArrayList<String> arrTblNames = new ArrayList<String>();
                        //SQLiteDatabase db =openOrCreateDatabase("surdb",SQLiteDatabase.CREATE_IF_NECESSARY,null);
                        //Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

                        //if (c.moveToFirst()) {
                        //  while ( !c.isAfterLast() ) {
                        //    arrTblNames.add( c.getString( c.getColumnIndex("name")) );
                        //  c.moveToNext();
                    }
                    newDB.close();

                }

            Toast.makeText(getApplicationContext(), "Synced to Server!",
                    Toast.LENGTH_SHORT).show();
                sla.close();

                return true;
            }

        return false;
    }



    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

}