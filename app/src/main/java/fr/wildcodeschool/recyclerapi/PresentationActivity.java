package fr.wildcodeschool.recyclerapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PresentationActivity extends AppCompatActivity {

    final static String URL_PRESENTATION = "https://pokeapi.co/api/v2/pokemon/%d/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);

        // GET INTENT
        int index = 41;

        String url = String.format(URL_PRESENTATION, index);

        // création d'une queue pour requete asynchrones
        RequestQueue queue = Volley.newRequestQueue(this);

        final TextView tvResult = findViewById(R.id.tvResult);
        final ImageView imgResult = findViewById(R.id.ivResult);


        // RECUPERATION API (ARRAY OU OBJECT)
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // GET NAME
                        try {
                            JSONArray forms = response.getJSONArray("forms");
                            for (int i = 0; i < forms.length(); i++) {
                                // le tableau forms contient des objets qu'on va stocker dans form
                                JSONObject form = forms.getJSONObject(i);
                                String name = form.getString("name");
                                tvResult.setText(name);
                            }

                        // GET IMAGE
                            JSONObject sprites = response.getJSONObject("sprites");
                            String img = sprites.getString("front_default");
                            Glide.with(PresentationActivity.this).load(img) .into(imgResult);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // TODO GET PICTURE
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // RECUPERATION DU MESSAGE D'ERROR
                        tvResult.setText("Response: " + error.getLocalizedMessage());
                    }
                });

        // ajout à la queue pour requete asynchrone
        queue.add(jsonObjectRequest);
    }
}
