package com.appteamnith.hillffair;


import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

public class newsfeed extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private List<cards_data> card_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsfeed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

       // initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        card_data = new ArrayList<>();
        adapter = new CardAdapter(this, card_data);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
       // recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
       // recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareCards();

    }

   /* private void initCollapsingToolbar() {
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
    }*/

    /**
     * Adding few albums for testing
     */
    private void prepareCards() {


        cards_data a = new cards_data("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
         a = new cards_data("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
        a = new cards_data("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
         a = new cards_data("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
         a = new cards_data("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
         a = new cards_data("abcd",null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
         a = new cards_data("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
         a = new cards_data("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
         a = new cards_data("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);



        adapter.notifyDataSetChanged();
    }




    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);             int column = position % spanCount;

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                {
                    outRect.top = spacing;
                }
            }
            }
        }
 //   private int dpToPx(int dp) {
   //     Resources r = getResources();
     //   return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    //}

}