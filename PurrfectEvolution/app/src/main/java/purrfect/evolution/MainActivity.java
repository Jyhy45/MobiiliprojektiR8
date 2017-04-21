package purrfect.evolution;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.facebook.CallbackManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import static android.content.ContentValues.TAG;

import java.util.List;

//TODO: add some statistic calculations


public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    public static DataContainerForPurfectEvolution getmDataContainer() {
        return mDataContainer;
    }

    private static DataContainerForPurfectEvolution mDataContainer = new DataContainerForPurfectEvolution();
    private static BuildingGrid mbuildingGrid = new BuildingGrid();
    private ViewPager mViewPager;
    private static int mInterval = 1000;

    public static BuildingGrid getMbuildingGrid() {
        return mbuildingGrid;
    }
    BuildingsFragment buildingsFragment;
    OptionsFragment optionsFragment;
    private Handler handler = new Handler();


    TextView rahe;
    ImageView raheKuva;
    TextView onni;
    ImageView onniKuva;
    TextView dimangit;
    ImageView dimangitKuva;

    ImageView options_text;

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
            //Log.d(TAG, "run: now at update ticker");


        }
    };

    //facebook stuff
    //CallbackManager callbackManager;
    //ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: LIFECYCLE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //facebook stuff
        //callbackManager = CallbackManager.Factory.create();
        //shareDialog = new ShareDialog(this);


        buildingsFragment = new BuildingsFragment();
        optionsFragment = new OptionsFragment();
        catFragment = new CatFragment();

        // Get screen size and pass it to catFragment
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        catFragment.setScreenSize(height,width);

        // Set program to fullscreen "mode"
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Log.d(TAG, "onCreate: now here");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


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
        options_text = (ImageView) findViewById((R.id.options_text));
        rahe.bringToFront();
        onni.bringToFront();
        dimangit.bringToFront();
        raheKuva.bringToFront();
        onniKuva.bringToFront();
        dimangitKuva.bringToFront();
        options_text.bringToFront();

        //facebook stuff
        //ShareButton shareButton = (ShareButton)findViewById(R.id.fb_share_button);
        //shareButton.setShareContent(facebookShareImage(BitmapFactory.decodeResource(getResources(),R.drawable.timangi)));
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
    //facebook stuff
    /*@Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }*/

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
                case 4:
                    return ShopFragment.newInstance();
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

    public void options_Onclick(View view)
    {
        Context context = MainActivity.this;

        optionsFragment.onClick(view,context);
    }


    public void onClickBuilding(View view)
    {
        Context context = MainActivity.this;

        buildingsFragment.onClick(view, context);
    }


    public void updateText()
    {

        String raheString = String.format("Money: %4.3g", mDataContainer.getmCurrentMoney());
        rahe.setText(raheString);
        String onniString = String.format("Happiness: %4.3g", mDataContainer.getmCurrentHappines());
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

    //facebook stuff
    /*public SharePhotoContent facebookShareImage(Bitmap image)
    {
            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(image)
                    .build();
            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .build();

            return content;
    }*/





    public void moneyButtonOnClick(View view)
    {
        mDataContainer.convertHappinessToMoney();
    }


    public void bananaReset(View view)
    {
        mDataContainer.resetHard();
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
