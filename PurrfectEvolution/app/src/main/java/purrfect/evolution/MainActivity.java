package purrfect.evolution;

import android.content.SharedPreferences;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

import java.util.ArrayList;
import java.util.List;

//TODO: add some statistic calculations


public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private static DataContainerForPurfectEvolution mDataContainer;

    public static BuildingGrid getMbuildingGrid() {
        return mbuildingGrid;
    }

    private static BuildingGrid mbuildingGrid = new BuildingGrid();

    /**
     * The {@link ViewPager} that will host the section contents.
     */

    private ViewPager mViewPager;

    ImageButton imageButton2;
    ImageView image;
    List<ImageView> imageList;
    List<Animation> animationList;
    int i;

    BuildingsFragment buildingsFragment;
    private Handler handler = new Handler();

    CatFragment catFragment;


    private static int mInterval = 1000;
    public static int getmInterval() {
        return mInterval;
    }

    public static void setmInterval(int Interval) {
        mInterval = Interval;
    }


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mDataContainer.tickTime();
            handler.postDelayed(runnable, mInterval);
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
        handler.postDelayed(runnable,mInterval);


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
        handler.removeCallbacks(runnable);

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


    public void updateText(RelativeLayout v)
    {
        TextView rahe = (TextView) v.findViewById(R.id.textView);
        TextView onni = (TextView) v.findViewById(R.id.textView);
        rahe.setText("Cash: " + mDataContainer.getmCurrentMoney());
        onni.setText("Happiness: " + mDataContainer.getmCurrentHappines());
    }




    public void onCatClick(View view)
    {
        Context context = MainActivity.this;
        RelativeLayout v = (RelativeLayout) findViewById(R.id.cat_fragment);
        catFragment.onCatClick(view, context, v);

        updateText(v);

        mDataContainer.receivedClick();


    }
    @Override
    protected void onStop(){
        Log.d(TAG, "onStop: LIFECYCLE");
        super.onStop();

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        handler.removeCallbacks(runnable);
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
