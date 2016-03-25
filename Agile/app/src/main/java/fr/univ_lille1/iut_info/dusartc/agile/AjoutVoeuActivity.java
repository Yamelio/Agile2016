package fr.univ_lille1.iut_info.dusartc.agile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class AjoutVoeuActivity extends AppCompatActivity {

    private Voeu voeu;
    private ArrayList<Voeu> listvoeux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajoutvoeu);

        voeu = (Voeu) getIntent().getSerializableExtra("voeu");
        listvoeux = (ArrayList) getIntent().getSerializableExtra("listVoeux");

        if (voeu != null) {
            EditText etablissement = (EditText) findViewById(R.id.editTextEtablissement);
            etablissement.setText(voeu.getEtablissement().getNom() + " " + voeu.getEtablissement().getVille());
            etablissement.setFocusable(false);
            etablissement.setClickable(false);
            EditText formation = (EditText) findViewById(R.id.editTextFormation);
            formation.setText(voeu.getFormation().getDiplome() + " " + voeu.getFormation().getDomaine());
            formation.setFocusable(false);
            formation.setClickable(false);
            EditText order = (EditText) findViewById(R.id.editTextOrder);
            order.setText(voeu.getOrder()+"", TextView.BufferType.EDITABLE);
            Button delete = (Button) findViewById(R.id.buttonDelete);
            delete.setVisibility(View.VISIBLE);
        }
    }

    public void validerVoeu(View view){
        EditText etablissement = (EditText) findViewById(R.id.editTextEtablissement);
        EditText formation = (EditText) findViewById(R.id.editTextFormation);
        EditText order = (EditText) findViewById(R.id.editTextOrder);

        if (voeu == null){
            String[] parsed = etablissement.getText().toString().split(" ");
            Etablissement etablissement1 = new Etablissement(parsed[0], TextUtils.join(" ",parsed)); //TODO sans le premier element
            parsed = formation.getText().toString().split(" ");
            Formation formation1 = new Formation(parsed[0], parsed[1]);
            int order1 = Integer.parseInt(order.getText().toString());
            listvoeux.add(order1, new Voeu(etablissement1, formation1));
        } else {
            int order1 = Integer.parseInt(order.getText().toString());
            if(listvoeux.size() > 1){
                Voeu moved = listvoeux.get(order1-1);
                moved.setOrder(voeu.getOrder());
            }
            voeu.setOrder(order1);
        }

        Collections.sort(listvoeux, new Comparator<Voeu>() {
            @Override
            public int compare(Voeu lhs, Voeu rhs) {
                return lhs.compareTo(rhs);
            }
        });

        goToListeVoeux();
    }

    public void deleteVoeu(View view){
        int idx = listvoeux.indexOf(voeu);
        for (int i = idx+1; i < listvoeux.size(); i++){
            listvoeux.get(i).setOrder(i);
        }
        listvoeux.remove(voeu);
        Collections.sort(listvoeux, new Comparator<Voeu>() {
            @Override
            public int compare(Voeu lhs, Voeu rhs) {
                return lhs.compareTo(rhs);
            }
        });

        goToListeVoeux();
    }

    private void goToListeVoeux(){
        Intent intent = new Intent(this, ListeVoeuxActivity.class);
        intent.putExtra("listeVoeux", listvoeux);
        startActivity(intent);
    }
}
