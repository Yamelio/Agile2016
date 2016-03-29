package fr.univ_lille1.iut_info.dusartc.agile;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AjoutVoeuActivity extends AppCompatActivity {

    private ArrayList<Voeu> listVoeux;
    private ArrayList<String> listDiplome;
    private ArrayList<String> listFormation;
    private String server = "http://172.18.48.149:8080/micheline/";
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout);
        queue = Volley.newRequestQueue(this);
        listDiplome = getDiplome();
        final Spinner spinner = (Spinner) findViewById(R.id.ajout_spinner);
        final ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listDiplome);
        spinner.setAdapter(adapter);

        final ListView listView = (ListView) findViewById(R.id.ajout_listView);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = spinner.getSelectedItem().toString();
                listFormation = getFormation(selected);
                ArrayAdapter adapter1 = new ArrayAdapter(AjoutVoeuActivity.this, android.R.layout.simple_list_item_1, listFormation);
                listView.setAdapter(adapter1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private ArrayList<String> getDiplome() {
        final ArrayList<String> ans = new ArrayList<>();
        StringRequest request = new StringRequest(Request.Method.GET, server + "formation/diplome",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        JSONArray array = null;
                        try {
                            array = new JSONArray(json);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                ans.add(object.getString("lib"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        showError(volleyError);
                    }
        });
        queue.add(request);
        return ans;
    }

    private ArrayList<String> getFormation(String requete) {
        final ArrayList<String> ans = new ArrayList<>();
        StringRequest request = new StringRequest(Request.Method.GET, server + "formation?" + requete,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        JSONArray array = null;
                        try {
                            array = new JSONArray(json);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                ans.add(object.getString("lib"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                showError(volleyError);
            }
        });
        queue.add(request);
        return ans;
    }

    private void showError(VolleyError error){

    }

    public void mayonnaise(View view){
        ListView listView = (ListView) findViewById(R.id.ajout_listView);
        String selected = listView.getSelectedItem().toString();

    }

}
