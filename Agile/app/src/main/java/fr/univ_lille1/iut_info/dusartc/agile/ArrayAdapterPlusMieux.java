package fr.univ_lille1.iut_info.dusartc.agile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by dusartc on 24/03/16.
 */
public class ArrayAdapterPlusMieux extends ArrayAdapter {

    private Context context;
    private String[] subSpinnerString;

    public ArrayAdapterPlusMieux(Context context, int resource, int textViewResourceId, String[] subSpinnerString){
        super(context,resource,textViewResourceId);
        this.context=context;
        this.subSpinnerString = subSpinnerString;
    }

    public View getView(int position,View convertView, ViewGroup parent){
        View rep = super.getView(position, convertView, parent);
        Spinner subSpinner = (Spinner) rep.findViewById(R.id.subSpinner);
        subSpinner.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, subSpinnerString));
        return rep;
    }

}
