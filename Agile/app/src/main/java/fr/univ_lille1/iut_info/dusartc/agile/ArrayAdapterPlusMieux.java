package fr.univ_lille1.iut_info.dusartc.agile;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by dusartc on 24/03/16.
 */
public class ArrayAdapterPlusMieux extends ArrayAdapter {

    private Context context;
    private String[] subSpinnerString;

    public ArrayAdapterPlusMieux(Context context, int resource, int textViewResourceId, String[] subSpinnerString){
        super(context,resource,textViewResourceId, subSpinnerString);
        this.context=context;
        this.subSpinnerString = subSpinnerString;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Object tab = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listevoeux, parent, false);
        }
        Spinner subSpinner = (Spinner) convertView.findViewById(R.id.spinner);
        TextView textView = (TextView) convertView.findViewById(R.id.textView_voeux);
        textView.setText("bonjourlol");
        //subSpinner.setAdapter(ArrayAdapter.createFromResource(context, 1, android.R.layout.simple_spinner_item));
        subSpinner.setAdapter(new ArrayAdapter<CharSequence>(context, android.R.layout.simple_spinner_item, subSpinnerString));
        return convertView;
    }

}
