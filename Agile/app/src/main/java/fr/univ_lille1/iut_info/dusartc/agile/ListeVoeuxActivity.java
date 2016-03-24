package fr.univ_lille1.iut_info.dusartc.agile;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by dusartc on 24/03/16.
 */
public class ListeVoeuxActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.affichage);

        ListView listView = (ListView) findViewById(R.id.listView);

        Voeu[] listeVoeux = (Voeu[]) savedInstance.get("listeVoeux");
        if (listeVoeux == null){
            listeVoeux = new Voeu[]{new Voeu("oui","oui","oui","non")};
        }
        String[] subSpinnerString = new String[listeVoeux.length + 1];
        subSpinnerString[subSpinnerString.length-1] = "delete";
        for (int i=0; i<subSpinnerString.length; i++){
            subSpinnerString[i] = i+"";
        }

        listView.setAdapter(new ArrayAdapterPlusMieux(this, R.layout.listevoeux, R.id.listView, subSpinnerString));


    }

}
