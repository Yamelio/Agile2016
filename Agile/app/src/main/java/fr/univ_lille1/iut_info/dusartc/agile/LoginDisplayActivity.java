package fr.univ_lille1.iut_info.dusartc.agile;

import android.app.Activity;
import android.os.Bundle;

import android.widget.EditText;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import  android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

import static android.widget.AdapterView.*;

/**
 * Created by deregnab on 24/03/16.
 */
public class LoginDisplayActivity extends Activity {
    final String EXTRA_LOGIN = "login";

    public void onCreate(Bundle savedInstanceStat){
        super.onCreate(savedInstanceStat);
        setContentView(R.layout.login_display);
        final EditText login = (EditText) findViewById(R.id.login);

        //final EditText pass = (EditText) findViewById(R.id.mdp);
        Button loginButton = (Button) findViewById(R.id.buttonConnection);
        loginButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginDisplayActivity.this, AjoutVoeuActivity.class);
/* Les tutos de KEVIN
                AsyncHttpClient client = new AsyncHttpClient();
                client.get("http://13.123.123.123:8080/lesgagnant", null, new AsyncHttpResponseHandler() {
                   @Override
                   public void onSuccess(String response) {
                       try {
                           JSONObject json = new JSONObject(response);
                           json.get("bite");
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }

                   }

                    @Override
                    public void onFailure(Throwable e, String response) {

                    }
                });
                requestparam data que t'envoies
                RequestParams params = new RequestParams();
                params.put("bite", "bite"); => {"bite":"bite"}
*/
                intent.putExtra(EXTRA_LOGIN, login.getText().toString());
                startActivity(intent);
            }
        });


    }



}
