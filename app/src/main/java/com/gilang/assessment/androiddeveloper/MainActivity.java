package com.gilang.assessment.androiddeveloper;

import android.os.Bundle;
import com.gilang.assessment.androiddeveloper.adapter.ViewPagerAdapter;
import com.gilang.assessment.androiddeveloper.ui.movie.MovieFragment;
import com.gilang.assessment.androiddeveloper.ui.tv.TvFragment;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitControl();
    }

    void InitControl(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {

        TextView tabHome = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabHome.setText("Movie");
        tabHome.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_movie, 0, 0);
        tabHome.setSelected(true);
        tabLayout.getTabAt(0).setCustomView(tabHome);

        TextView tabTrans = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTrans.setText("TV Show");
        tabTrans.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tv, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTrans);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new MovieFragment(), "Movie");
        adapter.addFrag(new TvFragment(), "TV Show");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
