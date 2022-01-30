package com.example.newcollector;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PrefConfig{
    private static int Position;

    public static void saveData(Context context, ArrayList<newCard> newCards){
        SharedPreferences pref = context.getSharedPreferences("myShared", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();;
        Gson gson = new Gson();
        String json = gson.toJson(newCards);
        editor.putString("pokeName",json);
        editor.apply();
    }

    public static void loadData(Context context, ArrayList<newCard> newCards){
        SharedPreferences pref = context.getSharedPreferences("myShared", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("pokeName", null);
        Type type = new TypeToken<ArrayList<Card>>() {}.getType();
        newCards = gson.fromJson(json, type);

        if (newCards == null){
            newCards = new ArrayList<>();
        }
    }

    public static ArrayList<newCard> loadTheList(Context context){
        SharedPreferences pref = context.getSharedPreferences("myShared", MODE_PRIVATE);
        String json = pref.getString("pokeName", null);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<newCard>>() {}.getType();
        ArrayList<newCard> newCards = gson.fromJson(json, type);

        if (newCards == null){
            newCards = new ArrayList<>();
        }
        return newCards;
    }

}
