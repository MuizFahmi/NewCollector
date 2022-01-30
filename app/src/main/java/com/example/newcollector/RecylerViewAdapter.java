package com.example.newcollector;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//take note the name is "Recyler" instead of "Recycler"
public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.ViewHolder> {
    private ArrayList<Card> cards;
    private Context mContext;
    private OnItemClickListener mListener;

    //interface
    public interface OnItemClickListener{
        void onItemClick(int position, View view);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView,addImage;
        public TextView textView;
        public Button button;

        public ViewHolder (View itemView, OnItemClickListener listener){
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.book_img_id);
            textView = (TextView) itemView.findViewById(R.id.book_title_id);
            addImage = (ImageView) itemView.findViewById(R.id.addimage);

            addImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position,view);
                        }
                    }
                }
            });
        }
    }

    //Constructor
    public RecylerViewAdapter(Context mcContext, ArrayList<Card> mCards) {
        this.cards = mCards;
        this.mContext = mcContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview,parent,false);
        return new ViewHolder(view, mListener) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card currentCard = cards.get(position);

        holder.textView.setText(cards.get(position).getTitle());
        holder.imageView.setImageResource(cards.get(position).getThumbnail());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public void filterList(ArrayList<Card> filteredList) {
        cards = filteredList;
        notifyDataSetChanged();
    }
}
