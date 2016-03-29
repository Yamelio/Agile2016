package fr.univ_lille1.iut_info.dusartc.agile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ListeVoeuxActivity extends AppCompatActivity {

    private ArrayList<Voeu> listeVoeux;
    private ArrayAdapter<Voeu> adapteur;
    private String login;
    private RequestQueue queue;
    private String server = "http://172.18.48.149:8080/micheline/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.liste_voeux_display);
        recupListeVoeux();

        ListView listview = (ListView) findViewById(R.id.listView_liste_voeux);
        adapteur = new ArrayAdapter<Voeu>(this, android.R.layout.simple_list_item_1, listeVoeux);
        listview.setAdapter(adapteur);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Voeu voeu = adapteur.getItem(position);
                Intent intent = changeActivity();
                intent.putExtra("voeu", voeu);
                startActivity(intent);
            }
        });
    }

    private void recupListeVoeux(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        try {
            listeVoeux = (ArrayList<Voeu>) bundle.getSerializable("listVoeux");
        } catch (Exception e){

        }
        if (listeVoeux == null) {
            listeVoeux = new ArrayList<>();
            //listeVoeux.add(new Voeu("DUT", "Informatique", "Lille", "Lille 1", 1));
            //listeVoeux.add(new Voeu("Licence", "Informatique", "Lille", "Lille 1", 2));
            login = "aaaa.aaaa";
            queue = Volley.newRequestQueue(this);
            StringRequest request = new StringRequest(Request.Method.GET, server + "voeux/" + login,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String json) {
                            try {
                                JSONArray array = new JSONArray(json);
                                for (int i = 0; i < array.length(); i++){
                                    JSONObject current = array.getJSONObject(i);
                                    JSONObject information = current.getJSONObject("formaEtabl");

                                    String ville = information.getString("ville");
                                    String domaine = information.getString("domaine");
                                    String diplome = information.getString("diplome");
                                    String nom = information.getString("nom");

                                    Formation formation = new Formation(diplome, domaine);
                                    Etablissement etablissement = new Etablissement(ville, nom);
                                    Voeu voeu = new Voeu(etablissement, formation, listeVoeux.size());
                                    listeVoeux.add(voeu);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            showError(volleyError);
                        }
            });
            queue.add(request);
        }
    }

    private Intent changeActivity(){
        Intent intent = new Intent(this, AjoutVoeuActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("listVoeux", (Serializable) listeVoeux);
        intent.putExtra("bundle", bundle);
        return intent;
    }

    public void addVoeu(View view){
        Intent intent = changeActivity();
        startActivity(intent);
    }

    public void showError(VolleyError error){
        System.out.print("non");
    }
}
