package com.example.mynotes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.Models.Notes;
import com.example.mynotes.NotesClickListenner;
import com.example.mynotes.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesListAdapter extends RecyclerView.Adapter<NotesViewHolder>{

    Context context;
    List <Notes> list;

    NotesClickListenner listenner;

    public NotesListAdapter(Context context, List<Notes> list, NotesClickListenner listenner) {
        this.context = context;
        this.list = list;
        this.listenner = listenner;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvTitle.setSelected(true);

        holder.tvPin.setText(list.get(position).getNotes());


        holder.tvPinDate.setText(list.get(position).getDate());
        holder.tvPinDate.setSelected(true);

        if(list.get(position).isPinned()){
            holder.ivPin.setImageResource(R.drawable.pin);
        } else {
            holder.ivPin.setImageResource(0);
        }
        int colorCode = getRandomColor();
        holder.nConteiner.setCardBackgroundColor(holder.itemView.getResources().getColor(colorCode, null));

        holder.nConteiner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenner.onClick(list.get(holder.getAdapterPosition()));
            }
        });

        holder.nConteiner.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listenner.onLongClick(list.get(holder.getAdapterPosition()), holder.nConteiner);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void filterList (List<Notes> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }


    private int getRandomColor(){
        List<Integer> colorCode = new ArrayList<>();
        colorCode.add(R.color.color1);
        colorCode.add(R.color.color2);
        colorCode.add(R.color.color3);
        colorCode.add(R.color.color4);
        colorCode.add(R.color.color5);

        Random random = new Random();
        int rndColor = random.nextInt(colorCode.size());
        return colorCode.get(rndColor);
    }
}
 class NotesViewHolder extends RecyclerView.ViewHolder {

    CardView nConteiner;
    ImageView ivPin;
    TextView tvTitle, tvPin, tvPinDate;
     public NotesViewHolder(@NonNull View itemView) {
         super(itemView);
         nConteiner = itemView.findViewById(R.id.nConteiner);
         ivPin = itemView.findViewById(R.id.ivPin);
         tvTitle = itemView.findViewById(R.id.tvTitle);
         tvPin = itemView.findViewById(R.id.tvPin);
         tvPinDate = itemView.findViewById(R.id.tvPinDate);
     }
 }