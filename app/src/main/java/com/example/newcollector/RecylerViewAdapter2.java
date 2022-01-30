package com.example.newcollector;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//take note the name is "Recyler" instead of "Recycler"
public class RecylerViewAdapter2 extends RecyclerView.Adapter<RecylerViewAdapter2.ViewHolder> {
    private ArrayList<newCard> cards;
    private Context mContext;
    private OnItemClickListener mListener;

    //interface
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textView;
        public CardView cardView;

        public ViewHolder (View itemView, OnItemClickListener listener){
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.book_img_id2);
            textView = (TextView) itemView.findViewById(R.id.book_title_id2);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id2);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    //Constructor
    public RecylerViewAdapter2(Context mcContext, ArrayList<newCard> mCards) {
        this.cards = mCards;
        this.mContext = mcContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.ownedcardview,parent,false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(cards.get(position).getTitle());
        holder.imageView.setImageResource(cards.get(position).getThumbnail());

        ClickToView(holder, position);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public void ClickToView(ViewHolder holder, int position){
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext,cardActivity.class);

                //passing data to the book activity
                intent.putExtra("Title",cards.get(position).getTitle());
                intent.putExtra("Description",cards.get(position).getDescription());
                intent.putExtra("Category",cards.get(position).getCategory());
                intent.putExtra("Thumbnail",cards.get(position).getThumbnail());

                //Start the activity
                mContext.startActivity(intent);
            }
        });
    }
}
