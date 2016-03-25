package fr.univ_lille1.iut_info.dusartc.agile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class ListeVoeuxActivity extends AppCompatActivity {

    private ArrayList<Voeu> listeVoeux;
    private ArrayAdapter<Voeu> adapteur;

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
            listeVoeux.add(new Voeu("DUT", "Informatique", "Lille", "Lille 1", 1));
            listeVoeux.add(new Voeu("Licence", "Informatique", "Lille", "Lille 1", 2));
        } else {
            //adapteur.notifyDataSetChanged();
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
}
