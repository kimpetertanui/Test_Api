package com.peterkimeli.adalabs.adapters;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.peterkimeli.adalabs.R;
import com.peterkimeli.adalabs.model.ListItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<ListItem> listItems;

    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }




    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
       return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ListItem listItem=listItems.get(position);

        holder.textViewHead.setText(listItem.getHead());
        holder.textViewHead.setText(listItem.getDesc());

        Picasso.with(context)
                .load(listItem.getImageUrl())
        .into(holder.imageView);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"You clicked " + listItem.getHead(),Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return listItems.size();
        //return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewHead;
        public TextView textViewDesc;
        public ImageView imageView;
        public LinearLayout linearLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewHead=(TextView) itemView.findViewById(R.id.textViewHead);
            textViewDesc=(TextView) itemView.findViewById(R.id.textViewDesc);
            imageView=(ImageView) itemView.findViewById(R.id.imageView);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}
