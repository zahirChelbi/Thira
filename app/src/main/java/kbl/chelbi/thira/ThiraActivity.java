package kbl.chelbi.thira;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import kbl.chelbi.clavier.ClavierBerber;
import kbl.chelbi.editeur.AdvancedEditText;
import kbl.chelbi.file.FileOperation;
import kbl.chelbi.file.FileSelector;
import kbl.chelbi.file.OnHandleFileListener;


public class ThiraActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TabLayout tabLayout;
    public ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    public static ClavierBerber sClavier=null;
    public static int nbTab = 0;

    final String[] mFileFilter = { "*.*", ".jpeg", ".txt", ".png" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thira);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Un Adapter and setting that adapter to the viewPager
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFrag(new TabFragment(), "doc".toLowerCase());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        //La barre de menu latérale : Naviguation Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //clavier
        sClavier = new ClavierBerber(this ,findViewById(R.id.keyboard_view).getId());

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
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
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
            Toast.makeText(this,"Setting",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_add) {
            TabFragment tabFragment= new TabFragment();
            nbTab++;
            viewPagerAdapter.addFrag(tabFragment,"doc".toLowerCase());
            viewPager.setAdapter(viewPagerAdapter);
            viewPager.setCurrentItem(nbTab);
            viewPager.getAdapter().notifyDataSetChanged();

        } else if (id == R.id.nav_suppr) {
            viewPagerAdapter.supprFrag(viewPager.getCurrentItem());
            nbTab--;
            viewPager.setAdapter(viewPagerAdapter);

        } else if (id == R.id.nav_save) {
            new FileSelector(ThiraActivity.this, FileOperation.SAVE, mSaveFileListener, mFileFilter).show();

        } else if (id == R.id.nav_load) {
            new FileSelector(ThiraActivity.this, FileOperation.LOAD, mLoadFileListener, mFileFilter).show();


        } else if (id == R.id.nav_clavier) {
            Toast.makeText(ThiraActivity.this, "clavier berbere", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private String getText(){
        String str = ((AdvancedEditText)((LinearLayout)viewPager.getChildAt(viewPager.getCurrentItem())).getChildAt(0)).getText().toString();
        if(str.equals(null)){
            str="";
        }
        return str ;
    }

    OnHandleFileListener mLoadFileListener = new OnHandleFileListener() {
        @Override
        public void handleFile(final String filePath) {
            Toast.makeText(ThiraActivity.this, "Load: " + filePath, Toast.LENGTH_SHORT).show();

            File f = new File(filePath);
            String res= "";
            String line;
            try {

                FileReader fileReader = new FileReader(f);
                BufferedReader bufferedReader = new BufferedReader(fileReader);


                while ((line= bufferedReader.readLine())!=null) {
                    res=res+line+"\n";
                }

                ((AdvancedEditText)((LinearLayout) viewPager.getChildAt(viewPager.getCurrentItem())).getChildAt(0)).setText(res);
                bufferedReader.close();
                fileReader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };

    OnHandleFileListener mSaveFileListener = new OnHandleFileListener() {
        @Override
        public void handleFile(final String filePath) {

            String string = getText();
            try {
                File f = new File(filePath);
                if (!f.exists()) {
                    f.createNewFile();
                }
                FileWriter fileWriter = new FileWriter(f);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                writer.write(string);
                writer.close();
                fileWriter.close();


            } catch (IOException e) {
                e.printStackTrace();
            }



        }
    };




}
