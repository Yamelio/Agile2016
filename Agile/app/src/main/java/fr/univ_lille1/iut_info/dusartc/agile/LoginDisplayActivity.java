package fr.univ_lille1.iut_info.dusartc.agile;

import android.app.Activity;
import android.os.Bundle;

import android.widget.EditText;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import  android.content.Intent;
import static android.widget.AdapterView.*;

/**
 * Created by deregnab on 24/03/16.
 */
public class LoginDisplayActivity extends Activity {
    //final String EXTRA_LOGIN = "login";

    public void onCreate(Bundle savedInstanceStat){
        super.onCreate(savedInstanceStat);
        setContentView(R.layout.login_display);
        //final EditText login = (EditText) findViewById(R.id.login);
        //final EditText pass = (EditText) findViewById(R.id.mdp);
        Button loginButton = (Button) findViewById(R.id.buttonConnection);
        loginButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
               goToListeVoeux();
            }
        });


    }

    public void goToListeVoeux(){
        //Intent intent = new Intent(LoginDisplayActivity.this, ListeVoeuxActivity.class);
        Intent intent = new Intent(LoginDisplayActivity.this, AjoutVoeuActivity.class);

        //intent.putExtra(EXTRA_LOGIN, login.getText().toString());
        startActivity(intent);
    }

}
