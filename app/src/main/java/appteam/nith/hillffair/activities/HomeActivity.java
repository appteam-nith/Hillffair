package appteam.nith.hillffair.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.Manifest;
import android.widget.TextView;
import android.widget.Toast;

import appteam.nith.hillffair.Notification.NotificationActivity;
import appteam.nith.hillffair.R;
import appteam.nith.hillffair.adapters.HomeAdapter;
import appteam.nith.hillffair.application.SharedPref;
import appteam.nith.hillffair.fragments.ProfileTab2;
import appteam.nith.hillffair.models.ProfileDataModel;
import appteam.nith.hillffair.models.main_screen_model;
import appteam.nith.hillffair.utilities.APIINTERFACE;
import appteam.nith.hillffair.utilities.RecyclerItemClickListener;
import appteam.nith.hillffair.utilities.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private HomeAdapter adapter;
    private SharedPref pref;
    private static final int PERMISSIONS_REQUEST_PHONE_CALL = 100;
    private static String[] PERMISSIONS_PHONECALL = {Manifest.permission.CALL_PHONE};
    final String number[] = {"9882654141", "9882852966"};
    int a=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pref= new SharedPref(this);
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
        if(pref.getUserName()==null)
        profileBasicInfo(pref.getUserId());

        recyclerView = (RecyclerView) findViewById(R.id.list);
        ArrayList<main_screen_model> list=new ArrayList<>();
        list.add(new main_screen_model(R.drawable.swords_crossed,"Battle Day",R.color.battleday));
        list.add(new main_screen_model(R.drawable.quiz,"Quiz",R.color.quiz));
        list.add(new main_screen_model(R.drawable.sponsor,"Sponsors",R.color.sponsor));
        list.add(new main_screen_model(R.drawable.news_feed,"NewsFeed",R.color.newsFeed));
        list.add(new main_screen_model(R.drawable.core,"Core Team",R.color.coreTeam));
        list.add(new main_screen_model(R.drawable.clubs,"Clubs",R.color.club));
        list.add(new main_screen_model(R.drawable.about,"About Hill'ffair",R.color.about));
        list.add(new main_screen_model(R.drawable.contributors,"Contributors",R.color.contributors));

        adapter = new HomeAdapter(list, this);
        GridLayoutManager staggeredGridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(adapter);
        //recyclerView.addItemDecoration(new DividerItemDecoration(HomeActivity.this, LinearLayoutManager.VERTICAL, Color.BLACK));

        staggeredGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                return 1;
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 6) {
                    startActivity(new Intent(HomeActivity.this,aboutHillffairActivity.class));
                }
                else if(position==0){
                    startActivity(new Intent(HomeActivity.this,BattleDayActivity.class));
                }
                else if (position==3){
                    startActivity(new Intent(HomeActivity.this, NewsfeedActivity.class));
                }
                else if (position==1){
                    startActivity(new Intent(HomeActivity.this,QuizActivity.class));
                }
                else if(position==2){
                    startActivity(new Intent(HomeActivity.this,SponsorActivity.class));
                }
                else if(position==4){
                    startActivity(new Intent(HomeActivity.this,CoreTeamActivity.class));
                }
                else if(position==5){
                    startActivity(new Intent(HomeActivity.this, EventActivity.class));
                }
                else if(position==7){
                    startActivity(new Intent(HomeActivity.this, ContributorsActivity.class));
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
        if (id == R.id.logout) {
            pref.setUserId(null);
            pref.setRollNo(null);
            pref.setUserName(null);
            startActivity(new Intent(HomeActivity.this,LoginActivity.class));
            finish();
            return true;

        } else if(id == R.id.contacts){

            CharSequence name[] = {"Deepak Kumar Jain\n(Hillffair Secretary)\n9882654141\n",
                    "Rishabh Bhandari\n(Clubs Secretary)\n9882852966\n"};


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(android.R.drawable.ic_menu_call);
            builder.setTitle("Emergency Contact");
            builder.setItems(name, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    a=i;
                    call(a);
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

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
                startActivity(new Intent(HomeActivity.this, NotificationActivity.class));
                break;
            case R.id.logout:
                pref.setUserId(null);
                pref.setRollNo(null);
                pref.setUserName(null);
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
                finish();
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

    private void profileBasicInfo(String id){

        APIINTERFACE mAPI = Utils.getRetrofitService();
        Call<ProfileDataModel> mService = mAPI.profileBasicInfo(id);
        mService.enqueue(new Callback<ProfileDataModel>() {
            @Override
            public void onResponse(Call<ProfileDataModel> call, Response<ProfileDataModel> response) {
                if(response!=null&&response.isSuccess()){
                    if(response.body().isSuccess()){
                        ProfileTab2.ProfileBasicDetailModel model=response.body().getProfileInfo();
                        if(model!=null){
                            pref.setUserName(model.getName());
                            if(model.getRollno()==null)
                               pref.setRollNo("");
                            else
                                pref.setRollNo(model.getRollno());
                        }

                    }
                }

            }

            @Override
            public void onFailure(Call<ProfileDataModel> call, Throwable t) {
                t.printStackTrace();
               }
        });


    }
    private void call(int i) {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST_PHONE_CALL);
        } else {
            //Open call function
            String phone = number[i];
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:+91" + phone));
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_PHONE_CALL) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                call(a);
            } else {
                Toast.makeText(this, "Sorry!!! Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
