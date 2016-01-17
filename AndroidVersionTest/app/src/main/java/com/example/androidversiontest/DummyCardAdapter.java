package com.example.androidversiontest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michael hantler on 2/1/2015.
 */
class DummyCardAdapter extends
        RecyclerView.Adapter<DummyCardAdapter.CardViewHolder> {


    static List<AndroidVersion> AndroidList= new ArrayList<>();
    private static RecyclerViewClickListener itemListener;
    private Context context;

    public DummyCardAdapter(List<AndroidVersion> AndroidList) {
        this.AndroidList = AndroidList;

    }

    public DummyCardAdapter(Context context, RecyclerViewClickListener itemListener)
    {
        this.context = context;
        this.itemListener = itemListener;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.android_view, parent, false);
        CardViewHolder vh = new CardViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int position) {

        Picasso.with(cardViewHolder.imageView.getContext())
                .load(AndroidList.get(position)
                        .get("imageurl"))
                .fit()
                .error(R.drawable.placeholder_card_view)
                .placeholder(R.drawable.placeholder_card_view)
                .into(cardViewHolder.imageView);


    }

    @Override
    public int getItemCount() {
        return AndroidList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public TextView name;

        public CardViewHolder(LinearLayout itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.androidimage);
            name      = (TextView)  itemView.findViewById(R.id.textView13);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {

            itemListener.recyclerViewListClicked(v, this.getPosition());

            int position  = getPosition();
            Context context = v.getContext();
            AndroidVersion android = (AndroidVersion) AndroidList.get(position);
            Intent intent = new Intent(context,AndroidDetailActivity.class);
//            intent.putExtra("id", bean.getId());
//            intent.putExtra("des", bean.getDescription());
            intent.putExtra("android",android);
            context.startActivity(intent);


        }

    }


}
