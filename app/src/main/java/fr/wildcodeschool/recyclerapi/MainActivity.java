package fr.wildcodeschool.recyclerapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://pokeapi.co/api/v2/";

        final TextView tvResult = findViewById(R.id.tvResult);

        // création d'une queue pour requete asynchrones
        RequestQueue queue = Volley.newRequestQueue(this);


        // RECUPERATION API (ARRAY OU OBJECT)
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //POUR AFFICHER LA REPONSE
                        tvResult.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // RECUPERATION DU MESSAGE D'ERROR
                        tvResult.setText("Response: " + error.getLocalizedMessage());


                        // TODO: Handle error

                    }
                });

        // ajout à la queue pour requete asynchrone
        queue.add(jsonObjectRequest);

    }
}
