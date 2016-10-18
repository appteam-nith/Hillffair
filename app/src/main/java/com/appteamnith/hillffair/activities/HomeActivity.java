package com.appteamnith.hillffair.activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.adapters.HomeAdapter;
import com.appteamnith.hillffair.application.SharedPref;
import com.appteamnith.hillffair.utilities.DividerItemDecoration;
import com.appteamnith.hillffair.utilities.RecyclerItemClickListener;

import java.util.Arrays;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private HomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       SharedPref pref= new SharedPref(this);
        setTheme(pref.getThemeId());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initCollapsingToolbar();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, 0, 0);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.list);

        Integer photo[] = {R.drawable.jimg2, R.drawable.jimg4, R.drawable.jimg3, R.drawable.jimg1,
                R.drawable.jimg1, R.drawable.jimg3 , R.drawable.jimg4 };
        adapter = new HomeAdapter(Arrays.asList(photo), this);
        GridLayoutManager staggeredGridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(HomeActivity.this, LinearLayoutManager.VERTICAL, Color.BLACK));

        // code that vary the size of each column in the row of grid layout

        staggeredGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int value=1;

                switch (position){
                    case 0: value=2;
                        break;
                    case 1: value=1;
                        break;
                    case 2: value=1;
                        break;
                    case 3: value=2;
                        break;
                    case 4: value=1;
                        break;
                    case 5: value=1;
                        break;
                    case 6: value=1;
                        break;
                    default: value=1;
                    break;
                }

                return  value;
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 0) {
                    startActivity(new Intent(HomeActivity.this, NewsfeedActivity.class));
                }
                else if(position==1){
                    startActivity(new Intent(HomeActivity.this, EventActivity.class));
                }
                else if (position==2){
                    startActivity(new Intent(HomeActivity.this,CoreTeamActivity.class));
                }
                else if (position==3){
                    startActivity(new Intent(HomeActivity.this,QuizActivity.class));
                }
            }
        }));
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
    protected void onResume() {
        super.onResume();
        SharedPref pref= new SharedPref(this);
        setTheme(pref.getThemeId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch(id) {
            case R.id.profile:
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                break;
            case R.id.settings:
                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
                finish();
                break;
            case R.id.aboutus:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(String.format("%1$s", getString(R.string.app_name)));
                builder.setMessage(getResources().getText(R.string.aboutus_text));
                builder.setPositiveButton("OK", null);
                builder.setIcon(R.drawable.ic_action_about);
                AlertDialog welcomeAlert = builder.create();
                welcomeAlert.show();
                break;

            case R.id.report:  Intent intent = new Intent(Intent.ACTION_SENDTO);
                String uriText = "mailto:" + Uri.encode("appteam.nith@gmail.com") + "?subject=" +
                        Uri.encode("Reporting A Bug/Feedback") + "&body=" + Uri.encode("Hello, Appteam \nI want to report a bug/give feedback corresponding to the app Hillfair 2k16.\n.....\n\n-Your name");

                Uri uri = Uri.parse(uriText);
                intent.setData(uri);
                startActivity(Intent.createChooser(intent, "Send Email"));
                break;
            case R.id.license:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle(String.format("%1$s", getString(R.string.open_source_licenses)));
                CharSequence str=getResources().getText(R.string.licenses_text);
                builder2.setMessage(str );
                builder2.setPositiveButton("OK", null);
                AlertDialog welcomeAlert2 = builder2.create();
                welcomeAlert2.show();
                ((TextView) welcomeAlert2.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
                break;
            case R.id.notification:
                startActivity(new Intent(HomeActivity.this, NewsfeedActivity.class));
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
}
