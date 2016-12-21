package timothyyudi.ituneslist;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by root on 12/21/2016.
 */

public class ITunesSearchParser {

    public static ArrayList<SongModel> parse(JSONObject response){
        JSONArray results = null;
        try {
            results = response.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<SongModel> songs = new ArrayList<>();
        for(int i=0;i<results.length();i++){
            JSONObject tempJSONObj= null;
            try {tempJSONObj = (JSONObject) results.get(i);} catch (JSONException e) {e.printStackTrace();}

            String songAlbum,songTitle,imageURL;
            try {songTitle = tempJSONObj.getString("trackName");}catch(JSONException e){songTitle="";}
            try {songAlbum = tempJSONObj.getString("collectionName");} catch (JSONException e) {songAlbum = "";}
            try {imageURL = tempJSONObj.getString("artworkUrl100");} catch (JSONException e) {imageURL = "";}
            songs.add(new SongModel(songTitle,songAlbum,imageURL));
        }
        return songs;
    }
}
