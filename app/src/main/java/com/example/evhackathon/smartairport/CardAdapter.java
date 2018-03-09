package com.example.evhackathon.smartairport;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Varsha on 3/9/2018.
 */

public class CardAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context mContext;
    private List<Card> cardList;

    public CardAdapter(Context mContext, List<Card> cardList) {
        this.mContext = mContext;
        this.cardList = cardList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: MAKING NEW");
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_list_row, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Card card = cardList.get(position);
        holder.name.setText(card.getName());
        holder.details.setText(card.getDetails());
        holder.type.setText(card.getType());
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}
