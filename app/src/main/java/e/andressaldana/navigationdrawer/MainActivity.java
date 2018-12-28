package e.andressaldana.navigationdrawer;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RealTimeFragment.OnFragmentInteractionListener, BarChartFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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
        getMenuInflater().inflate(R.menu.main, menu);
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

        Fragment fragment = null;
        boolean isFramgentSelected = false;

        if (id == R.id.nav_home) {
            //fragment = new FragmentGeneralInfo();
            //isFramgentSelected = true;

        } else if (id == R.id.nav_statistics) {

            fragment = new BarChartFragment();
            ArrayList NoOfEmp = new ArrayList();
            final ArrayList<String> xLabels = new ArrayList<>();
            xLabels.add("Jan");
            xLabels.add("Feb");
            xLabels.add("Mar");
            xLabels.add("Apr");
            xLabels.add("May");
            xLabels.add("Jun");
            xLabels.add("Jul");
            xLabels.add("Aug");
            xLabels.add("Sep");
            xLabels.add("Oct");
            xLabels.add("Nov");
            xLabels.add("Dec");
            NoOfEmp.add("545");
            NoOfEmp.add("640");
            NoOfEmp.add("330");
            NoOfEmp.add("240");
            NoOfEmp.add("680");
            NoOfEmp.add("787");
            NoOfEmp.add("487");
            NoOfEmp.add("187");
            NoOfEmp.add("287");
            NoOfEmp.add("487");
            NoOfEmp.add("387");
            NoOfEmp.add("687");

            ((BarChartFragment) fragment).setChartLabels(xLabels);
            ((BarChartFragment) fragment).setChartData(NoOfEmp);

            isFramgentSelected = true;

        } else if (id == R.id.nav_real_time) {
            fragment = new RealTimeFragment();
            isFramgentSelected = true;
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        if(isFramgentSelected){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
