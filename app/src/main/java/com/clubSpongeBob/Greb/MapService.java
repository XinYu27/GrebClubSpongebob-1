package com.clubSpongeBob.Greb;

import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MapService {
    private static ExecutorService executor
            = Executors.newFixedThreadPool(6);
    private static final String API_KEY = CommonUtils.getSContext().getString(R.string.MAP_API_KEY);
    private static final String TAG = "Map Service";

    //downloading the data
    public static String getData(String source, String destination) throws Exception{
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + source.replace(" ","") + "&destinations=" + destination.replace(" ","") + "&key=" + API_KEY;

        Log.i(TAG, "Source: "+source);
        Log.i(TAG, "Destination: "+destination);
        Log.i(TAG, "Url: "+url);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();

        return response.body().string();
    }

    //need to pass the data from the textview
    public static Future<long[]> getDistanceTime(String source, String destination){

        return executor.submit(()->{
            long distance = 0;
            long time = 0;
            try{
                JSONObject jsonRespRouteDistance = new JSONObject(getData(source, destination))
                        .getJSONArray("rows")
                        .getJSONObject(0)
                        .getJSONArray ("elements")
                        .getJSONObject(0);

                //parsing json data and updating data
                JSONObject je = jsonRespRouteDistance.getJSONObject("distance");
                JSONObject jf = jsonRespRouteDistance.getJSONObject("duration");
                distance = je.getLong("value");
                time = jf.getLong("value");

                Log.i(TAG, "Distance: "+ distance);
                Log.i(TAG, "Time: " + time);

            } catch (Exception e){
                System.out.println("Error in Matrix API");
                e.printStackTrace();
                return null;
            }
            return new long[] {distance,time};
        });
    }

}