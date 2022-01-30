package com.example.newcollector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ownedPokemon extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecylerViewAdapter2 mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button removeButton;

    ArrayList<newCard> newCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owned_pokemon);

        newCards = PrefConfig.loadTheList(getApplicationContext());

        mRecyclerView = findViewById(R.id.recyclerview_id2);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this,3);
        mAdapter = new RecylerViewAdapter2(this,newCards);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        removeButton = (Button) findViewById(R.id.removebutton);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.removeAllViews();
                newCards.removeAll(newCards);
                PrefConfig.saveData(getApplicationContext(), newCards);
            }
        });

    }
}