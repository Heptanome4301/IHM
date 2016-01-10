package com.example.andri.bonappetit;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.andri.bonappetit.dialog.SortMealFragmentDialog;
import com.example.andri.bonappetit.dummy.RestaurantContent;
import com.example.andri.bonappetit.dummy.RestaurantContent.RestaurantItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MealFragment extends Fragment   {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MealFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MealFragment newInstance(int columnCount) {
        MealFragment fragment = new MealFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        setHasOptionsMenu(true);

    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        getActivity().setTitle(R.string.meals_title);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyMealRecyclerViewAdapter(RestaurantContent.ITEMS, mListener));

        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        menu.setGroupVisible(R.id.group_menu_1,true);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sort :
                SortMealFragmentDialog dialog = new SortMealFragmentDialog();
                dialog.show(getFragmentManager(), "sort_meals_tag");
                return true;
        }

        return false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void sort(int idSelected) {
        String[] array = getResources().getStringArray(R.array.sort_array);
   /*     String selected="";
        for(int i=0;i<array.length;i++) {
            if(idSelected==i) {
                Toast.makeText(getActivity(), "selected " + array[i],
                        Toast.LENGTH_SHORT).show();
                selected=array[i];
            }
        }*/
        switch (idSelected) {
            case 0 : //price
                RestaurantContent.sortByPrice();
                break;
            case 1 : //date
                // TODO
                break;
            case 2 : //name
                RestaurantContent.sortByTitle();
                break;
            case 3 : //rating
                RestaurantContent.sortByRating();
                break;
            case 4 : //distance
                // TODO
                break;
        }

        recyclerView.swapAdapter(new MyMealRecyclerViewAdapter(RestaurantContent.ITEMS,mListener), false);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(RestaurantItem item);
    }
}
