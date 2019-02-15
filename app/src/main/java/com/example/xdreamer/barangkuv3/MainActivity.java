package com.example.xdreamer.barangkuv3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.xdreamer.barangkuv3.fragment.AkunFragment;
import com.example.xdreamer.barangkuv3.fragment.HomeFragment;
import com.example.xdreamer.barangkuv3.fragment.InboxFragment;
import com.example.xdreamer.barangkuv3.fragment.KeranjangFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    boolean session;


    // UserPreference userPreference;

    private BottomNavigationView botNav;
    private FrameLayout frameLayout;

    private AkunFragment akunFragment;
    private HomeFragment homeFragment;
    private InboxFragment inboxFragment;
    private KeranjangFragment keranjangFragment;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener stateListener;

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();



    }

    @Override
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
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InboxFragment()).commit();
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


    // userPreference = new UserPreference(getApplicationContext());
/*
        botNav = findViewById(R.id.bottomNav);
        frameLayout = findViewById(R.id.frame_main);
        akunFragment = new AkunFragment();
        homeFragment = new HomeFragment();
        inboxFragment = new InboxFragment();
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
                        setFragment(inboxFragment);
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
