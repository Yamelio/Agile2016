package fr.univ_lille1.iut_info.dusartc.agile;

import android.os.AsyncTask;
import android.os.Build;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.SecureRandom;

/**
 * Created by dusartc on 29/03/16.
 */
public class HokutoNoSocket extends AsyncTask<Void, Void, String> implements Serializable {

    private Socket socket;
    private String url;
    private int port;

    public HokutoNoSocket(String url, int port){
        this.url = url;
        this.port = port;
    }

    public String doPost(String url, String param) throws UnknownHostException, IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
        writer.write("POST /micheline/" + url + param + "HTTP/1.1rn");
        writer.write("host: " + Build.MODEL + "rn");
        writer.write("rn");
        writer.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String ans = "";
        String line;
        while ((line = reader.readLine()) != null) {
            ans += line;
        }

        reader.close();
        writer.close();

        return ans;
    }

    public String doGet(String url, String param) throws UnknownHostException, IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
        writer.write("GET /micheline/" + url + param + "HTTP/1.1rn");
        writer.write("host: " + Build.MODEL + "rn");
        writer.write("rn");
        writer.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String ans = "";
        String line;
        while ((line = reader.readLine()) != null) {
            ans += line;
        }

        reader.close();
        writer.close();

        System.out.print("ouiouiouiouiouioui"+ans);
        return ans;
    }

    @Override
    protected String doInBackground(Void... params) {
        try{
            socket = new Socket(url, port);
        }catch (UnknownHostException e) {
            System.err.print("erreur unknown host");
        }catch (IOException e1){
            System.err.print("erreur IO");
        }
        return "oui";
    }
}
