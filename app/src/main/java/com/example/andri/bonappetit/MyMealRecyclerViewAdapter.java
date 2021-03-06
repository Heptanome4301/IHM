package com.example.andri.bonappetit;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andri.bonappetit.MealFragment.OnListFragmentInteractionListener;
import com.example.andri.bonappetit.dummy.RestaurantContent.RestaurantItem;

import java.text.DecimalFormat;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link RestaurantItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyMealRecyclerViewAdapter extends RecyclerView.Adapter<MyMealRecyclerViewAdapter.ViewHolder> {

    private final List<RestaurantItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context context;

    public MyMealRecyclerViewAdapter(List<RestaurantItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_meal, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        // holder.mIdView.setText(mValues.get(position).id);
        holder.mTitleView.setText(mValues.get(position).title);
        holder.mSnippetView.setText(mValues.get(position).snippet);
        holder.mDateView.setText(mValues.get(position).date);
        holder.mDistanceView.setText(mValues.get(position).distance+"m");
        DecimalFormat df = new DecimalFormat("#.#");
        String rating = df.format(mValues.get(position).rating) + "/5";
        holder.mRatingView.setText(rating);
        String seats = "(" + mValues.get(position).seatsTaken + "/" + mValues.get(position).totalSeats + ")";
        holder.mSeatsView.setText(seats);
        df = new DecimalFormat("#.#");
        String price = df.format(mValues.get(position).price) + "€";
        holder.mPriceView.setText(price);

        if (context != null) {
            Drawable d = ContextCompat.getDrawable(context, mValues.get(position).idImg);
            holder.mImageView.setImageDrawable(d);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        //  public final TextView mIdView;
        public final TextView mTitleView;
        public final TextView mSnippetView;
        public final TextView mDateView;
        public final TextView mDistanceView;
        public final TextView mRatingView;
        public final TextView mSeatsView;
        public final TextView mPriceView;
        public final ImageView mImageView;
        public RestaurantItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.title);
            mDateView = (TextView) view.findViewById(R.id.date);
            mDistanceView = (TextView) view.findViewById(R.id.location);
            mRatingView = (TextView) view.findViewById(R.id.rating);
            mSnippetView = (TextView) view.findViewById(R.id.snippet);
            mSeatsView = (TextView) view.findViewById(R.id.seats);
            mPriceView = (TextView) view.findViewById(R.id.price);
            mImageView = (ImageView) view.findViewById(R.id.thumbnail);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }
    }
}
