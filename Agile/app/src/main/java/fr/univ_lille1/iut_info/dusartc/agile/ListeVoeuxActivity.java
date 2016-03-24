package fr.univ_lille1.iut_info.dusartc.agile;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by dusartc on 24/03/16.
 */
public class ListeVoeuxActivity extends ListActivity {

    private int count=0;
    private ArrayList<String> list;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.affichage);

        //ListView listView = (ListView) findViewById(R.id.listView);

        /*Voeu[] listeVoeux;
        try {
            listeVoeux = (Voeu[]) savedInstance.get("listeVoeux");
        } catch (Exception e){
            listeVoeux = new Voeu[]{new Voeu("oui","oui","oui","non")};
        }

        String[] subSpinnerString = new String[listeVoeux.length + 1];
        subSpinnerString[subSpinnerString.length-1] = "delete";
        for (int i=0; i<subSpinnerString.length; i++){
            subSpinnerString[i] = i+"";
        }*/
        //listView.setAdapter(new ArrayAdapterPlusMieux(this, R.layout.listevoeux
        //      , R.id.listView_voeux, subSpinnerString));

        list = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        setListAdapter(arrayAdapter);
    }

    public void addItem(View view){
        list.add("oui + "+count++);
        arrayAdapter.notifyDataSetChanged();
    }

}
