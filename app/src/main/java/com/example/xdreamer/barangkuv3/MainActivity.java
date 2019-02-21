package com.example.xdreamer.barangkuv3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.xdreamer.barangkuv3.fragment.AkunFragment;
import com.example.xdreamer.barangkuv3.fragment.HomeFragment;
import com.example.xdreamer.barangkuv3.fragment.KategoriFragment;
import com.example.xdreamer.barangkuv3.fragment.KeranjangFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    boolean session;


    // UserPreference userPreference;

    private BottomNavigationView botNav;
    private FrameLayout frameLayout;

    private NavigationView navigationView;

    private AkunFragment akunFragment;
    private HomeFragment homeFragment;
    private KategoriFragment kategoriFragment;
    private KeranjangFragment keranjangFragment;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener stateListener;
    private static final String TAG_HOME = "home";
    private static final String TAG_KATEGORI = "kategori";
    private static final String TAG_KERANJANG = "keranjang";
    private static final String TAG_PROFIL = "profil";
    private static final String TAG_FAQ = "faq";
    private static final String TAG_SETTINGS = "settings";
    public static String CURRENT_TAG = TAG_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitle;

    private static int navItemIndex = 0;

    private android.support.v7.widget.Toolbar toolbar;

    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);



        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        activityTitle = getResources().getStringArray(R.array.nav_item_activity_titles);

        SetUpNavigationView();


        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }
    }

    private void loadHomeFragment() {
        selectNavMenu();

        setToolbarTitle();

        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            return;
        }
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
        drawer.closeDrawers();

        invalidateOptionsMenu();

    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                KategoriFragment kategoriFragment = new KategoriFragment();
                return kategoriFragment;
            case 2:
                KeranjangFragment keranjangFragment = new KeranjangFragment();
                return keranjangFragment;
            case 3:
                AkunFragment akunFragment = new AkunFragment();
                return akunFragment;
            default:
                return new HomeFragment();
        }
    }

/*
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.menu_daftar:
                Intent daf = new Intent(MainActivity.this, DaftarActivity.class);
                startActivity(daf);
                break;
            case R.id.menu_masuk:
                Intent s = new Intent(MainActivity.this, MasukActivity.class);
                startActivity(s);
                break;
            case R.id.menu_kategori:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new KategoriFragment()).commit();
                break;
            case R.id.menu_keranjang:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new KeranjangFragment()).commit();
                break;
            case R.id.menu_profil:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AkunFragment()).commit();
                break;
            case R.id.menu_logout:
                auth.signOut();
                break;
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    */


    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitle[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void SetUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.menu_kategori:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_KATEGORI;
                        break;
                    case R.id.menu_keranjang:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_KERANJANG;
                        break;
                    case R.id.menu_profil:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_PROFIL;
                        break;
                    case R.id.menu_faq:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_FAQ;
                        break;
                    default:
                        navItemIndex = 0;
                }
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);
                loadHomeFragment();
                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        if (shouldLoadHomeFragOnBackPress){
            if (navItemIndex != 0){
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }


        super.onBackPressed();
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.mainmenu, menu);
        }

        return true;
    }
*/

    // userPreference = new UserPreference(getApplicationContext());
/*
        botNav = findViewById(R.id.bottomNav);
        frameLayout = findViewById(R.id.frame_main);
        akunFragment = new AkunFragment();
        homeFragment = new HomeFragment();
        kategoriFragment = new KategoriFragment();
        keranjangFragment = new KeranjangFragment();
        auth = FirebaseAuth.getInstance();

      Toast.makeText(getApplicationContext(), "User Login Status: " + userPreference.isUserLoggedIn(), Toast.LENGTH_SHORT).show();

       if (userPreference.checkLogin()) {
            finish();
        }

        /*HashMap<String, String> user = userPreference.getUserDetails();

        String name = user.get(UserPreference.KEY_NAME);

        String email = user.get(UserPreference.KEY_EMAIL);

        stateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = auth.getCurrentUser();
                if (user != null) {

                }
            }
        };



        setFragment(homeFragment);

        botNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        setFragment(homeFragment);
                        return true;
                    case R.id.nav_inbox:
                        setFragment(kategoriFragment);
                        return true;
                    case R.id.nav_keranjang:
                        setFragment(keranjangFragment);
                        return true;
                    case R.id.nav_akun:
                        setFragment(akunFragment);
                        return true;
                }
                return false;
            }
        });

        */




    /* private void setFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_main, fragment);
        ft.commit();
    }

    /*
    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(stateListener);
    }
    */

   /* private void SESSION() {
        session = Boolean.valueOf(SaveDaftar.read(getApplicationContext(), "session", "false"));
        if (!session) {
            Intent signup = new Intent(getApplicationContext(), DaftarActivity.class);
            startActivity(signup);
        } else {
            Toast.makeText(getApplicationContext(), "sudah login", Toast.LENGTH_SHORT).show();

        }
    }
    */

}
