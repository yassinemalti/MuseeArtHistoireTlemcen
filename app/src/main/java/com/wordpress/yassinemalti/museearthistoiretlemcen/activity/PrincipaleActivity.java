package com.wordpress.yassinemalti.museearthistoiretlemcen.activity;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.wordpress.yassinemalti.museearthistoiretlemcen.R;

public class PrincipaleActivity extends AppCompatActivity
        implements  NavigationView.OnNavigationItemSelectedListener,
                    AccueilFragment.OnFragmentInteractionListener,
                    PresentationFragment.OnFragmentInteractionListener,
                    MotDuDirecteurFragment.OnFragmentInteractionListener,
                    JournalOfficielFragment.OnFragmentInteractionListener,
                    GalerieFragment.OnFragmentInteractionListener,
                    ContactFragment.OnFragmentInteractionListener,
                    LocaliserFragment.OnFragmentInteractionListener{

    private boolean viewIsAtHome;
    boolean doubleBackToExitPressedOnce = false;
    private int currentViewID;
    private String imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principale);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(homeClickListener);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (getIntent().getExtras() != null) {

            for (String key : getIntent().getExtras().keySet()) {
                String value = getIntent().getExtras().getString(key);
                if (key.equals("imageUri")) {
                    imageUri = value;
                }

            }
        }

        subscribeToPushService();

        navigationView.setCheckedItem(R.id.accueil);
        displayView(R.id.accueil);

        }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (!viewIsAtHome) {
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setCheckedItem(R.id.accueil);
            displayView(R.id.accueil);
        } else {
            if (doubleBackToExitPressedOnce){
                moveTaskToBack(true);
            } else {
                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "الرجاء الضغط مرة أخرى للخروج", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displayView(item.getItemId());
        return true;
    }

    public void displayView(int viewId) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);
        currentViewID = viewId;

        switch (viewId) {
            case R.id.accueil:
                fragment = new AccueilFragment();
                Bundle args = new Bundle();
                args.putString("imageUri", imageUri);
                fragment.setArguments(args);
                title  = "استقبال";
                viewIsAtHome = true;
                break;
            case R.id.presentation:
                fragment = new PresentationFragment();
                title  = "تعريف المتحف";
                viewIsAtHome = false;
                break;
            case R.id.motdudirecteur:
                fragment = new MotDuDirecteurFragment();
                title  = "كلمة المدير";
                viewIsAtHome = false;
                break;
            case R.id.journalofficiel:
                fragment = new JournalOfficielFragment();
                title  = "الجريدة الرسمية";
                viewIsAtHome = false;
                break;
            case R.id.galerie:
                fragment = new GalerieFragment();
                title  = "صور للمتحف";
                viewIsAtHome = false;
                break;
            case R.id.contact:
                fragment = new ContactFragment();
                title  = "اتصل بنا";
                viewIsAtHome = false;
                break;
            case R.id.localiser:
                fragment = new LocaliserFragment();
                title  = "تحديد الموقع";
                viewIsAtHome = false;
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    private View.OnClickListener homeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.openDrawer(GravityCompat.END);
        }
    };

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(currentViewID);
        displayView(currentViewID);

    }

    private void subscribeToPushService() {
        FirebaseMessaging.getInstance().subscribeToTopic("news");
    }

}
