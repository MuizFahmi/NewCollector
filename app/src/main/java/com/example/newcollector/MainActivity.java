package com.example.newcollector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecylerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<Card> listCard;
    ArrayList<newCard> newCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for searching function
        EditText editText = findViewById(R.id.edittext);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        //creating the array list
        listCard = new ArrayList<>();

        //add some data to the array
        listCard.add(new Card("Charmander","Fire","Obviously prefers hot places. If it get caught in the rain steam is said to spout from the tip of its tail.",R.drawable.charmander));
        listCard.add(new Card("Clefable","Fairy","A timid Fairy Pokemon that is rarely seen. It will run and hide the moment it senses people.",R.drawable.clefable));
        listCard.add(new Card("Clefairy","Fairy","Its magical and cute appeal has many admirers. It is rare and found only in certain areas.",R.drawable.clefairy));
        listCard.add(new Card("Doduo","Normal","A bird that makes up for its poor flying with its fast foot speed. leaves giant footprints.",R.drawable.doduo));
        listCard.add(new Card("Dragonair","Dragon","A mystical Pokemon that exudes a gentle aura. Has the ability to change climate conditions.",R.drawable.dragonair));
        listCard.add(new Card("Dragonite","Dragon","An extremely rarely seen marine Pokemon. Its intelligence is said to match that of humans.",R.drawable.dragonite));
        listCard.add(new Card("Drowzee","Psychic","Puts enemies to sleep, then eats their dreams. Occasionally gets sick from eating bad dreams.",R.drawable.drowzee));
        listCard.add(new Card("Ekans","Poison","A carnivore that swallows its prey whole. Pidgeys and Spearows are its favorite food.",R.drawable.ekans));
        listCard.add(new Card("Gastly","Ghost","Almost invisible, this gaseous Pokemon cloaks the target and puts it to sleep without notice.",R.drawable.gastly));
        listCard.add(new Card("Gengar","Ghost","Under a full moon, this Pokemon likes to mimic the shadows of people and laugh at their fright.",R.drawable.gengar));
        listCard.add(new Card("Aerodactyl","Rock","A ferocious prehistoric Pokemon that goes for the enemy's throat with its serrated saw-like fangs.",R.drawable.aerodactyl));
        listCard.add(new Card("Alakazam","Psychic","Its brain can outperform a supercomputer. Its intelligence quotient is said to be 5000.",R.drawable.alakazam));
        listCard.add(new Card("Arboks","Poison","It is rumored that the ferocious warning markings on its belly differ from area to area.",R.drawable.arboks));
        listCard.add(new Card("Articuno","Ice","A legendary bird Pokemon that is said to appear to doomed people who are lost in icy mountains.",R.drawable.articuno));
        listCard.add(new Card("Bellsprout","Grass","A carnivorous Pokemon that traps and eats bugs. It uses its root foot to soak up needed moisture.",R.drawable.bellsprout));
        listCard.add(new Card("Bulbasaur","Grass","A strange seed was planted on its back at birth. Thus, a plant sprouted and now grows with this Pokemon.",R.drawable.bulbasaur));
        listCard.add(new Card("Caterpie","Bug","It is covered with green skin. When it grows, it shed the skin, covers itself with silk, and becomes a cocoon.",R.drawable.caterpie));
        listCard.add(new Card("Charizard","Fire","Its wings can carry this Pokemon close to an altitude of 4,600 feet.  It blows out fire at very high temperature.",R.drawable.charizard));
        listCard.add(new Card("Hitmonlee","Fighting","When in a hurry, its legs lengthen progressively. It runs smoothly with extra long, loping strides.",R.drawable.hitmonlee));
        listCard.add(new Card("Jynx","Ice","Merely by meditating, the Pokemon launches a powerful psychic energy attack.",R.drawable.jynx));
        listCard.add(new Card("Lickitung","Normal","Its tongue can be extended like a chameleon's. It leaves a stinging sensation when it Licks enemies.",R.drawable.lickitung));
        listCard.add(new Card("Machamp","Fighting","Using its amazing muscles, it throws a powerful punches that can knock its victim clear over the horizon.",R.drawable.machamp));
        listCard.add(new Card("Magnemite","Electric","Uses anti-gravity to stay suspended. Appears without warning and uses attacks like Thunder wave.",R.drawable.magnemite));
        listCard.add(new Card("Omanyte","Rock","Although long extinct, in rare cases, it can be genetically resurrected from fossils.",R.drawable.omanyte));
        listCard.add(new Card("Pikachu","Electric","When several of these Pokemon gather, their electricity can build and cause lightning storms.",R.drawable.pikachu));
        listCard.add(new Card("Rhyhorn","Ground","Its massive bones are 1,000 harder than human bones. It can easily knock a trailer flying.",R.drawable.rhyhorn));
        listCard.add(new Card("Sandshrew","Ground","Burrows deep underground in arid locations far from water, It only emerges to hunt for food.",R.drawable.sandshrew));
        listCard.add(new Card("Scyther","Bug","With ninja-like agility and speed, it can create the illusion that there is more than one of it.",R.drawable.scyther));
        listCard.add(new Card("Squirtle","Water","After birth, its back swells and hardens into a shell. its powerfully sprays foam from its mouth.",R.drawable.squirtle));
        listCard.add(new Card("Tentacool","Water","Drifts in shallow seas. Anglers who hook them by accident are often punished by its stinging acid.",R.drawable.tentacool));

        //calling the recycler view function
        buildRecyclerView();

        newCards = PrefConfig.loadTheList(getApplicationContext());
        mAdapter.setOnItemClickListener(new RecylerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position,View view) {
                addList(position);
                PrefConfig.saveData(getApplicationContext(), newCards);
                Toast.makeText(getApplicationContext(), listCard.get(position).getTitle()+" is saved", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addList(int position){
        String title = listCard.get(position).getTitle();
        String category = listCard.get(position).getCategory();
        String desc = listCard.get(position).getDescription();
        int thumbnail = listCard.get(position).getThumbnail();
        Boolean button = true;

        newCards.add(new newCard(title,category,desc,thumbnail,button));
    }

    private void filter(String text) {
        ArrayList<Card> filteredList = new ArrayList<>();

        for (Card item : listCard) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        mAdapter.filterList(filteredList);
    }

    private void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.recyclerview_id);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this,3);
        mAdapter = new RecylerViewAdapter(this,listCard);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_Alphabet:
                Collections.sort(listCard, Card.CardAlphabetComparator);
                Toast.makeText(MainActivity.this, "Sort by Alphabet", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
                return true;
            case R.id.menu_Type:
                Collections.sort(listCard, Card.CardTypeComparator);
                Toast.makeText(MainActivity.this, "Sort by Type", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}