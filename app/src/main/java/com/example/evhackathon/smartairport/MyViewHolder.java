package com.example.evhackathon.smartairport;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Varsha on 3/9/2018.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView details;
    public TextView type;

    public MyViewHolder(View view) {
        super(view);
        name = (TextView) view.findViewById(R.id.name);
        details = (TextView) view.findViewById(R.id.details);
        type = (TextView) view.findViewById(R.id.type);
    }
}
