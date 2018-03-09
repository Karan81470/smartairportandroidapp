package com.example.evhackathon.smartairport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Varsha on 3/9/2018.
 */

public class CardActivity extends AppCompatActivity {

    private List<Card> cardList = new ArrayList<>();  // Main content is here

    private RecyclerView recyclerView; // Layout's recyclerview

    private CardAdapter mAdapter; // Data to recyclerview adapter


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_recycler);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        // Make some data - not always needed - used to fill list
        for (int i = 0; i < 20; i++) {
            cardList.add(new Card());
        }

        mAdapter = new CardAdapter(this, cardList);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
