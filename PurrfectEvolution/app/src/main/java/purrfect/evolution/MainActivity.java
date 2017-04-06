package purrfect.evolution;

import android.content.SharedPreferences;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.util.DisplayMetrics;
import android.view.View;

import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

import java.util.List;

//TODO: add some statistic calculations


public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private static DataContainerForPurfectEvolution mDataContainer;
    private static BuildingGrid mbuildingGrid = new BuildingGrid();
    private ViewPager mViewPager;
    private static int mInterval = 1000;

    public static BuildingGrid getMbuildingGrid() {
        return mbuildingGrid;
    }
    BuildingsFragment buildingsFragment;

    private Handler handler = new Handler();


    TextView rahe;
    ImageView raheKuva;
    TextView onni;
    ImageView onniKuva;
    TextView dimangit;
    ImageView dimangitKuva;

    CatFragment catFragment;

    public static int getmInterval() {
        return mInterval;
    }


    public static void setmInterval(int Interval) {
        mInterval = Interval;
    }


    private Runnable updateTickRunnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(updateTickRunnable, mInterval);
            mDataContainer.tickTime();
            updateText(); //TODO: Aiheuttaa crashin viimesessä fragmentissä koska kursuu findbyId eikä kyseistä objektia ole
            Log.d(TAG, "run: now at update ticker");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: LIFECYCLE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildingsFragment = new BuildingsFragment();
        catFragment = new CatFragment();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        catFragment.setScreenSize(height,width);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Log.d(TAG, "onCreate: now here");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mDataContainer = new DataContainerForPurfectEvolution();

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        
        
        //saving preferences meyby make to its own function
        SharedPreferences data = getPreferences(0);
        mDataContainer.loadDataFromPreference(data);
        mDataContainer.loadBuildingDataToGrid(mbuildingGrid);
        //boolean silent = settings.getBoolean("silentMode", false);

        mDataContainer.calculateEverything();
        handler.postDelayed(updateTickRunnable,mInterval);


        //let's bring headers on top
        rahe = (TextView) findViewById(R.id.textView);
        onni = (TextView) findViewById(R.id.textView2);
        dimangit = (TextView) findViewById(R.id.textView3);
        raheKuva = (ImageView) findViewById(R.id.imageView);
        onniKuva = (ImageView) findViewById(R.id.imageView2);
        dimangitKuva = (ImageView) findViewById(R.id.imageView3);
        rahe.bringToFront();
        onni.bringToFront();
        dimangit.bringToFront();
        raheKuva.bringToFront();
        onniKuva.bringToFront();
        dimangitKuva.bringToFront();

    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: LIFECYCLE");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: LIFECYCLE");
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause: LIFECYCLE");
        super.onPause();
        handler.removeCallbacks(updateTickRunnable);

    }
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 3:
                    return OptionsFragment.newInstance();
                case 2:
                    return BuildingsFragment.newInstance();
                case 1:
                    return EvolutionFragment.newInstance();
                case 0:
                    return CatFragment.newInstance();
                default:
                    return CatFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }
    }


    public void onClickBuilding(View view)
    {



        Context context = MainActivity.this;

        buildingsFragment.onClick(view, context);



    }


    public void updateText()
    {

        String raheString = String.format("Money: %4.3e", mDataContainer.getmCurrentMoney());
        rahe.setText(raheString);
        String onniString = String.format("Happiness: %4.3e", mDataContainer.getmCurrentHappines());
        onni.setText(onniString);
    }




    public void onCatClick(View view)
    {
        Context context = MainActivity.this;
        RelativeLayout v = (RelativeLayout) findViewById(R.id.cat_fragment);
        catFragment.onCatClick(view, context, v);

        updateText();

        mDataContainer.receivedClick();


    }
    @Override
    protected void onStop(){
        Log.d(TAG, "onStop: LIFECYCLE");
        super.onStop();

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        handler.removeCallbacks(updateTickRunnable);
        mDataContainer.calculateEverything();
        mDataContainer.saveBuildingDataToDataContainer(mbuildingGrid);

        SharedPreferences settings = getPreferences(0);
        SharedPreferences.Editor editor = settings.edit();
        mDataContainer.saveDataToPreference(editor);
        //editor.putBoolean("silentMode", mSilentMode);

        // Commit the edits!
        editor.commit();
        Log.d(TAG, "onStop: now here");

    }

}
