package com.rccorp.udacint;

import android.support.v7.widget.CardView;


public interface CardInterface {

        int MAX_ELEVATION_FACTOR = 8;

        float getBaseElevation();

        CardView getCardViewAt(int position);

        int getCount();
}


