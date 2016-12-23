package com.johndoe.mtourismbeta;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FloatingActionButton fab;
    public String mode="home";
    public int position;
    public LatLng coord;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("MTourism");


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.hide();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("latitude", coord.latitude);
                intent.putExtra("longitude", coord.longitude);
                intent.putExtra("titre", title);
                startActivity(intent);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment=new HomeFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frame_container, fragment).addToBackStack("tag").commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        int count = getFragmentManager().getBackStackEntryCount();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if(count==0){
            super.onBackPressed();
        }
        else if(mode.equals("home")) {}
        else {
            getFragmentManager().popBackStack();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_note:
                Intent intent = new Intent(this, Notes.class);
                startActivity(intent);
                return true;
            case R.id.action_camera:
                popUp();
                return true;
            case R.id.action_currency:
                Intent intent2 = new Intent(MainActivity.this, Converter.class);
                startActivity(intent2);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment=null;
        Bundle bundle = new Bundle();
        switch (id) {
            case R.id.home:
                fragment=new HomeFragment();
                mode="home";
                break;
            case  R.id.monuments:
                fragment = new ItemListFragment();
                bundle.putString("mode", "monuments");
                fragment.setArguments(bundle);
                mode="monuments";
                break;
            case  R.id.restos:
                fragment = new ItemListFragment();
                bundle.putString("mode", "restos");
                fragment.setArguments(bundle);
                mode="restos";
                break;
            case  R.id.hotels:
                fragment = new ItemListFragment();
                bundle.putString("mode", "hotels");
                fragment.setArguments(bundle);
                mode="hotels";
                break;
            case  R.id.nums:
                fragment = new NumsFragment();
                mode="nums";
                 break;
            case  R.id.circuits:
                fragment = new CircuitFragment();
                mode="circuits";
                break;
             default:
                  fragment=new HomeFragment();
                break;

        }

        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
          ft.replace(R.id.frame_container,fragment).addToBackStack("tag").commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public int getPosition(){ return position;}
    public String getMode(){ return mode;}
    public void setLatLng(LatLng lolo){
        coord=lolo;
    }
    public void setTitle(String title)
    { this.title=title;}

    public void getDetail(int position,String mode){
        fab.show();
        Fragment fragment=null;
        this.mode=mode;
        this.position=position;
       if(mode.equals("monuments"))
        fragment = new MonumentFragment();
        else
        fragment = new DetailFragment();
        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame_container, fragment).addToBackStack("tag").commit();
        }
        else
            Toast.makeText(MainActivity.this, "Erreur de Chargement de Fragment", Toast.LENGTH_SHORT).show();


    }
    public void changeAction(String titre){
        getSupportActionBar().setTitle(titre);
    }

    public void popUp(){
        View view=findViewById(R.id.action_camera);
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.popupmenu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu2) {
                    Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takeVideoIntent, 33);
                    }
                } else if (item.getItemId() == R.id.menu1) {
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivity(intent);
                }


                return true;
            }
        });
        popupMenu.show();
    }



}
