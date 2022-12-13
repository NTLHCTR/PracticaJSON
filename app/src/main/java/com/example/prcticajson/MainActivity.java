package com.example.prcticajson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity
implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login (View view){
        EditText txtusuario =findViewById(R.id.txtusuario);
        EditText txtpass =findViewById(R.id.txtpass);
        //Toast.makeText(getApplicationContext(),"Usuario = "+txtusuario.getText() + " Contraseña = "+txtpass.getText(),Toast.LENGTH_LONG).show();

        /*Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://uealecpeterson.net/ws/ws1.php?usr="
                + txtusuario.getText() + "&pass=" + txtpass.getText(),
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");*/

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://uealecpeterson.net/ws/ws1.php?usr="
                + txtusuario.getText() + "&pass=" + txtpass.getText();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        if (response.equals("Login Correcto!")){
                            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                            startActivity(intent);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        queue.add(stringRequest);
    }

    @Override
    public void processFinish(String result) throws JSONException {
        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
    }
}