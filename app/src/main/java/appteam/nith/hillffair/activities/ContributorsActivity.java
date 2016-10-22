package appteam.nith.hillffair.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import appteam.nith.hillffair.R;
import appteam.nith.hillffair.adapters.contributorsAdapter;
import appteam.nith.hillffair.application.SharedPref;
import appteam.nith.hillffair.models.contributorsItem;

import java.util.ArrayList;

public class ContributorsActivity extends AppCompatActivity {

    RecyclerView rvContributors;
    contributorsAdapter ContributorAdapter;
    Toolbar tbContributers;
    ArrayList<contributorsItem> contributorsItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPref pref = new SharedPref(this);
        setTheme(pref.getThemeId());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributors);
        String BASE_URL = "https://github.com/";
        rvContributors = (RecyclerView) findViewById(R.id.contributors_view);

        contributorsItems = new ArrayList<>();
        contributorsItems.add(new contributorsItem("Sahil Badyal", BASE_URL + "sahilbadyal.png", BASE_URL + "sahilbadyal"));
        contributorsItems.add(new contributorsItem("Nishant Chaudhary", BASE_URL + "nishant23j.png", BASE_URL + "nishant23j"));
        contributorsItems.add(new contributorsItem("Kunal Sharma", "https://avatars1.githubusercontent.com/u/9267351?v=3&s=400", BASE_URL + "kunal12422"));
        contributorsItems.add(new contributorsItem("Shubham Naik", BASE_URL + "nrshubham.png", BASE_URL + "nrshubham"));
        contributorsItems.add(new contributorsItem("Ashima Anand", BASE_URL + "ashima1795.png", BASE_URL + "ashima1795"));

        //contributorsItems.add(new contributorsItem("", BASE_URL + ".png", BASE_URL + ""));

        contributorsItems.add(new contributorsItem("Ashish Gurjar", BASE_URL + "akgurjar.png", BASE_URL + "akgurjar"));
        contributorsItems.add(new contributorsItem("Sahil Ramola", BASE_URL + "RamolaWeb.png", BASE_URL + "RamolaWeb"));
        contributorsItems.add(new contributorsItem("Sukhbir Singh", BASE_URL + "sukhbir-singh.png",BASE_URL + "sukhbir-singh"));
        contributorsItems.add(new contributorsItem("Suraj Negi", BASE_URL + "Akatsuki06.png",BASE_URL + "Akatsuki06"));
        contributorsItems.add(new contributorsItem("Aditya Arora", BASE_URL + "adi23arora.png",BASE_URL + "adi23arora"));
        contributorsItems.add(new contributorsItem("Goutham Reddy ", BASE_URL + "zeus512.png", BASE_URL + "zeus512"));
        contributorsItems.add(new contributorsItem("Hemant Singh ", BASE_URL + "joshafest.png", BASE_URL + "joshafest"));
        contributorsItems.add(new contributorsItem("Narendra Dodwaria", BASE_URL + "narendra36.png", BASE_URL + "narendra36"));
        contributorsItems.add(new contributorsItem("Parvesh Dhull", BASE_URL + "Parveshdhull.png", BASE_URL + "Parveshdhull"));
        contributorsItems.add(new contributorsItem("Nitin", BASE_URL + "iamNitin16.png", BASE_URL + "iamNitin16"));
        contributorsItems.add(new contributorsItem("Anishka Gupta", BASE_URL + "Anishka0107.png", BASE_URL + "Anishka0107"));
        contributorsItems.add(new contributorsItem("Jalaz Choudhary", BASE_URL + "jaykay12.png", BASE_URL + "jaykay12"));
        contributorsItems.add(new contributorsItem("Jatin", BASE_URL + "Jatin0312.png", BASE_URL + "Jatin0312"));
        contributorsItems.add(new contributorsItem("Vijaya Laxmi", BASE_URL + "vijaya22.png", BASE_URL + "vijaya22"));
        contributorsItems.add(new contributorsItem("Vibhor Garg", BASE_URL + "vibhorg.png", BASE_URL + "vibhorg"));

        ContributorAdapter = new contributorsAdapter(contributorsItems, ContributorsActivity.this);
        rvContributors.setAdapter(ContributorAdapter);

        tbContributers = (Toolbar) findViewById(R.id.contributors_toolbar);
        tbContributers.setTitle("Contributors");
        setSupportActionBar(tbContributers);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager lvmanager = new LinearLayoutManager(this);
        lvmanager.setOrientation(LinearLayoutManager.VERTICAL);
        rvContributors.setLayoutManager(lvmanager);

    }
}
