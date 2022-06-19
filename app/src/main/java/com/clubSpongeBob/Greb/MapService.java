package com.clubSpongeBob.Greb;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MapService {
    private static final String API_KEY = CommonUtils.getSContext().getString(R.string.MAP_API_KEY);

    //downloading the data
    public static String getData(String source, String destination) throws Exception {

        var url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + source.replace(" ","") + "&destinations=" + destination.replace(" ","") + "&key=" + API_KEY;
        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var client = HttpClient.newBuilder().build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        return response;
    }

    //need to pass the data from the textview
    public static long[] getdistancentimesssss(String source, String destination) throws Exception {

        long distance = 0;
        long time = 0;

        //parsing json data and updating data
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(getData(source, destination));
        JSONArray ja = (JSONArray) jo.get("rows");
        jo = (JSONObject) ja.get(0);
        ja = (JSONArray) jo.get("elements");
        jo = (JSONObject) ja.get(0);
        JSONObject je = (JSONObject) jo.get("distance");
        JSONObject jf = (JSONObject) jo.get("duration");
        distance = (long) je.get("value");
        time = (long) jf.get("value");

        return new long[] {distance,time};

    }


}