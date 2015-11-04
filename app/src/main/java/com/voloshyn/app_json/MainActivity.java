package com.voloshyn.app_json;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Завантаження...");
        pDialog.setCancelable(false);
        getJSON();

    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    private void getJSON(){
        showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST, JsonConfig.URL_JSON, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response:  " + response.toString());
                hideDialog();
                try {
                    JSONArray jArray = new JSONArray(response);
                    JSONObject group_element;
                    for (int i=0;i<jArray.length();i++){
                        group_element = jArray.getJSONObject(i);
                        group_element.getString("name");
                        JSONArray list_item_data = group_element.getJSONArray("mas_goods");
                        for (int j = 0;j<list_item_data.length();j++){
                            /*
                            *********
                            * сформувати елементи в середині групи
                            *   URL+ price
                            * у вигляді якогось списку
                            *
                             */
                        }

                        /*
                        конструктор елементу списку з Ім'ям, і списку елемнтів групи
                        list.add(new ListItem(group_element.);
                         */

                    }
                    /*
                    внести дані в адаптер і прикріпити адаптер до ліст вью
                    зміни...
                     */


                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Sorry. Json error!", Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }
}
