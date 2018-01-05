package com.rccorp.udacint;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rutwik on 05/01/18.
 */

public class CardAdapter extends PagerAdapter implements CardInterface {

    private List<CardView> mCards;
    private List<Roloitem> mData;
    private float mBaseElevation;

    private Context mContext;

    public CardAdapter() {
        mData = new ArrayList<>();
        mCards = new ArrayList<>();
    }

    public void addCardItem(Roloitem item) {
        mCards.add(null);
        mData.add(item);
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mCards.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.card_main, container, false);
        container.addView(view);
        bind(mData.get(position), view);
        CardView cardView = (CardView) view.findViewById(R.id.cardv_main);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mCards.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mCards.set(position, null);
    }

    private void bind(Roloitem item, View view) {
        mContext=view.getContext();
        TextView firstTextView = (TextView) view.findViewById(R.id.first_tv);
        TextView lastTextView = (TextView) view.findViewById(R.id.last_tv);
        ImageView avatarView=(ImageView)view.findViewById(R.id.avatar_imgv);
        firstTextView.setText(item.getFirst());
        lastTextView.setText(item.getLast());
        Glide.with(mContext).load(item.getAvatar()).into(avatarView);
    }

}

