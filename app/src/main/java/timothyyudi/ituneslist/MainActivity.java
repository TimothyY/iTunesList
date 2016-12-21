package timothyyudi.ituneslist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvSong;
    ArrayList<SongModel> songs;
    SongAdapter sAdapter;

    RequestQueue queue;
    Context mCtx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCtx=this;
        songs = new ArrayList<>();
//        songs.add(new SongModel("Counting Stars","OneRepublic",""));
//        songs.add(new SongModel("I Lived","OneRepublic",""));

        // Instantiate the RequestQueue.
        queue = Volley.newRequestQueue(this);
        String url ="https://itunes.apple.com/search?term=onerepublic";


        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        songs = ITunesSearchParser.parse(response);
                        sAdapter = new SongAdapter(mCtx,songs);
                        lvSong.setAdapter(sAdapter);
//                        lvSong.invalidate();
//                        sAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

        queue.add(jsObjRequest);

        lvSong = (ListView) findViewById(R.id.lvSong);
        sAdapter = new SongAdapter(this,songs);
        lvSong.setAdapter(sAdapter);
    }
}
