package com.example.andri.bonappetit;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.andri.bonappetit.dialog.SortMealFragmentDialog;
import com.example.andri.bonappetit.dummy.RestaurantContent;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        RestaurantsListFragment.OnFragmentInteractionListener,
        MealFragment.OnListFragmentInteractionListener,
        OnMapReadyCallback,
        SortMealFragmentDialog.NoticeDialogListener,
        GoogleMap.OnInfoWindowClickListener {

    private MealFragment mealFragment;
    private RestaurantsListFragment dummyFragment;
    private MapFragment mapFragment;
    public FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            RestaurantsListFragment fragment = new RestaurantsListFragment();
            dummyFragment = fragment;
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.add(R.id.fragment_container, dummyFragment);
            transaction.commit();




        }
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_resto);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.setGroupVisible(R.id.group_menu_1,false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the MainActivity/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.menu_search) {
            return true;
        } else if (id == R.id.menu_sort) {
            // return false so the event is not consumed and can be passed to the fragment
            return false;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_map) {

            fab.setVisibility(View.GONE);

            if (mapFragment == null)
                mapFragment = new MapFragment();

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.replace(R.id.fragment_container, mapFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            if(mealFragment!=null) {
                getSupportFragmentManager().beginTransaction().remove(mealFragment).commit();
            }

            mapFragment.getMapAsync(this);

        } else if (id == R.id.nav_user_meal) {
            if (mealFragment == null)
                mealFragment = new MealFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, mealFragment,"mealFrag").commit();
            if(mapFragment!=null) {
                getFragmentManager().beginTransaction().remove(mapFragment).commit();
            }

            fab.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_resto) {
            if (dummyFragment == null)
                dummyFragment = new RestaurantsListFragment();

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.replace(R.id.fragment_container, dummyFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            if(mealFragment!=null) {
                getSupportFragmentManager().beginTransaction().remove(mealFragment).commit();
            }

            fab.setVisibility(View.GONE);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void startMealsDetailsActivity(RestaurantContent.RestaurantItem item) {
        Intent intent = new Intent(this,MealDetailsActivity.class);
        intent.putExtra("title",item.title);
        intent.putExtra("date",item.date);
        intent.putExtra("rating",item.rating);
        intent.putExtra("id",item.id);
        intent.putExtra("location",item.location);
        intent.putExtra("snippet",item.snippet);
        intent.putExtra("seatsTaken",item.seatsTaken);
        intent.putExtra("totalSeats",item.totalSeats);
        intent.putExtra("price",item.price);
        intent.putExtra("idImg",item.idImg);
        intent.putExtra("user",item.user);
        intent.putExtra("distance",item.distance);
        startActivity(intent);
    }

    @Override
    public void onListFragmentInteraction(RestaurantContent.RestaurantItem item) {
        startMealsDetailsActivity(item);
    }

    @Override
    public void onMapReady(GoogleMap mMap) {
        final LatLng BEURK = new LatLng(45.781038, 4.873533);
        final LatLng RESTO_U = new LatLng(45.780917, 4.876201);
        final LatLng CHEZ_PATRICK = new LatLng(45.784489, 4.872739);

        setTitle(R.string.map_title);

        mMap.setOnInfoWindowClickListener(this);
        // add POIs
        Marker marker1 = mMap.addMarker(new MarkerOptions()
                .position(BEURK)
                .title("Le beurk")
                .snippet("Restaurant de l'INSA"));
        marker1.showInfoWindow();

        Marker marker2 = mMap.addMarker(new MarkerOptions()
                .position(RESTO_U)
                .title("Restaurant U")
                .snippet("Restaurant étudiant"));

        Marker marker3 = mMap.addMarker(new MarkerOptions()
                .position(CHEZ_PATRICK)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .title("Couscous chez Patrick")
                .snippet("Si si"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BEURK, 15));
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, int idSelected) {
     //  String tag = dialog.getTag();

        MealFragment fragment = (MealFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        fragment.sort(idSelected);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        startMealsDetailsActivity(RestaurantContent.ITEMS.get(0));
    }
}
