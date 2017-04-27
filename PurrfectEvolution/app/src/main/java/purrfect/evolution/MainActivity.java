package purrfect.evolution;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

//TODO: add some statistic calculations


public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    public static DataContainerForPurfectEvolution getmDataContainer() {
        return mDataContainer;
    }

    private static DataContainerForPurfectEvolution mDataContainer = new DataContainerForPurfectEvolution();
    private static BuildingGrid mbuildingGrid = new BuildingGrid();
    private static CatData catData;
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


    CatFragment catFragment;
    EvolutionFragment evolutionFragment;

    public static CatData getCatData() { return catData; }

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
            updateText();
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

        evolutionFragment = new EvolutionFragment();
        catData = new CatData();

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
        mDataContainer.loadCatDataFromGrid();
        //boolean silent = settings.getBoolean("silentMode", false);

        mDataContainer.calculateEverything();
        handler.postDelayed(updateTickRunnable,mInterval);


        //let's bring headers on top
        rahe = (TextView) findViewById(R.id.textView);
        onni = (TextView) findViewById(R.id.textView2);

        raheKuva = (ImageView) findViewById(R.id.imageView);
        onniKuva = (ImageView) findViewById(R.id.imageView2);


        rahe.bringToFront();
        onni.bringToFront();

        raheKuva.bringToFront();
        onniKuva.bringToFront();


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
            return 5;
        }
    }

    public void options_Onclick(View view)
    {
        Context context = MainActivity.this;
        RelativeLayout v = (RelativeLayout) findViewById(R.id.options_fragment);
        optionsFragment.onClick(view,context,v);
    }

    public void onEvolutionCatClick(View view) {
        evolutionFragment.showMainMenuPopUp(view, MainActivity.this);
    }


    public void onMainMenuClick(View view) {
        evolutionFragment.onMainMenuClick(view, MainActivity.this);
        switch (view.getId()) {
            case (R.id.hantavalinta):

                evolutionFragment.showTailMenuPopUp(view, MainActivity.this);
                break;
            case (R.id.bodyvalinta):

                evolutionFragment.showBodyMenuPopUp(view, MainActivity.this);
                break;
            case (R.id.paavalinta):

                evolutionFragment.showHeadMenuPopUp(view, MainActivity.this);
                break;
        }
    }

    public void onTailMenuClick(View view) {
        evolutionFragment.onTailMenuClick(view, MainActivity.this);
        switch (view.getId()) {
            case (R.id.hanta1):
                catData.setMhanta(R.drawable.kisse_hanta);
                findViewById(R.id.imageView_hanta).setForeground(getDrawable(catData.getMhanta()));
                break;
            case (R.id.hanta2):
                catData.setMhanta(R.drawable.kisse_hanta2);
                findViewById(R.id.imageView_hanta).setForeground(getDrawable(catData.getMhanta()));
                break;
            case (R.id.hanta3):
                catData.setMhanta(R.drawable.kisse_hanta3);
                findViewById(R.id.imageView_hanta).setForeground(getDrawable(catData.getMhanta()));
                break;
            case (R.id.hanta4):
                catData.setMhanta(R.drawable.nokikissas_hanta);
                findViewById(R.id.imageView_hanta).setForeground(getDrawable(catData.getMhanta()));
                break;
            case (R.id.hanta5):
                catData.setMhanta(R.drawable.taikakissa_hanta);
                findViewById(R.id.imageView_hanta).setForeground(getDrawable(catData.getMhanta()));
                break;
            case (R.id.hanta6):
                catData.setMhanta(R.drawable.porrokissa_hanta);
                findViewById(R.id.imageView_hanta).setForeground(getDrawable(catData.getMhanta()));
                break;
            case (R.id.hanta7):
                catData.setMhanta(R.drawable.kissakala_hanta);
                findViewById(R.id.imageView_hanta).setForeground(getDrawable(catData.getMhanta()));
                break;
            case (R.id.hanta8):
                //catData.setMhanta(R.drawable.nokikissas_hanta);
                //findViewById(R.id.imageView_hanta).setForeground(getDrawable(catData.getMhanta()));
                break;
            case (R.id.hanta9):
                //catData.setMhanta(R.drawable.nokikissas_hanta);
                //findViewById(R.id.imageView_hanta).setForeground(getDrawable(catData.getMhanta()));
                break;
        }

     //   evolutionFragment.updateCat();
    }

    public void onBodyMenuClick(View view) {
        evolutionFragment.onBodyMenuClick(view, MainActivity.this);
        switch (view.getId()) {
            case (R.id.body1):
                catData.setMkroppa(R.drawable.kisse_kroppa);
                findViewById(R.id.imageViewKroppa).setForeground(getDrawable(catData.getMkroppa()));
                break;
            case (R.id.body2):
                catData.setMkroppa(R.drawable.kisse_kroppa2);
                findViewById(R.id.imageViewKroppa).setForeground(getDrawable(catData.getMkroppa()));
                break;
            case (R.id.body3):
                catData.setMkroppa(R.drawable.kisse_kroppa3);
                findViewById(R.id.imageViewKroppa).setForeground(getDrawable(catData.getMkroppa()));
                break;
            case (R.id.body4):
                catData.setMkroppa(R.drawable.nokikissa_kroppa);
                findViewById(R.id.imageViewKroppa).setForeground(getDrawable(catData.getMkroppa()));
                break;
            case (R.id.body5):
                catData.setMkroppa(R.drawable.taikakissa_kroppa);
                findViewById(R.id.imageViewKroppa).setForeground(getDrawable(catData.getMkroppa()));
                break;
            case (R.id.body6):
                catData.setMkroppa(R.drawable.porrokissa_kroppa);
                findViewById(R.id.imageViewKroppa).setForeground(getDrawable(catData.getMkroppa()));
                break;
            case (R.id.body7):
                catData.setMkroppa(R.drawable.kissakala_kroppa);
                findViewById(R.id.imageViewKroppa).setForeground(getDrawable(catData.getMkroppa()));
                break;
            case (R.id.body8):
                //catData.setMkroppa(R.drawable.nokikissa_kroppa);
                //findViewById(R.id.imageViewKroppa).setForeground(getDrawable(catData.getMkroppa()));
                break;
            case (R.id.body9):
                //catData.setMkroppa(R.drawable.nokikissa_kroppa);
                //findViewById(R.id.imageViewKroppa).setForeground(getDrawable(catData.getMkroppa()));
                break;
        }

    }

    public void onHeadMenuClick(View view) {
        evolutionFragment.onHeadMenuClick(view, MainActivity.this);
        switch (view.getId()) {
            case (R.id.head1):
                catData.setMpaa(R.drawable.kisse_paa);
                findViewById(R.id.imageViewPaa).setForeground(getDrawable(catData.getMpaa()));
                break;
            case (R.id.head2):
                catData.setMpaa(R.drawable.kisse_paa2);
                findViewById(R.id.imageViewPaa).setForeground(getDrawable(catData.getMpaa()));
                break;
            case (R.id.head3):
                catData.setMpaa(R.drawable.kisse_paa3);
                findViewById(R.id.imageViewPaa).setForeground(getDrawable(catData.getMpaa()));
                break;
            case (R.id.head4):
                catData.setMpaa(R.drawable.nokikissa_paa);
                findViewById(R.id.imageViewPaa).setForeground(getDrawable(catData.getMpaa()));
                break;
            case (R.id.head5):
                catData.setMpaa(R.drawable.taikakissa_paa);
                findViewById(R.id.imageViewPaa).setForeground(getDrawable(catData.getMpaa()));
                break;
            case (R.id.head6):
                catData.setMpaa(R.drawable.porrokissa_paa);
                findViewById(R.id.imageViewPaa).setForeground(getDrawable(catData.getMpaa()));
                break;
            case (R.id.head7):
                catData.setMpaa(R.drawable.kissakala_paa);
                findViewById(R.id.imageViewPaa).setForeground(getDrawable(catData.getMpaa()));
                break;
            case (R.id.head8):
                //catData.setMpaa(R.drawable.nokikissa_paa);
                //findViewById(R.id.imageViewPaa).setForeground(getDrawable(catData.getMpaa()));
                break;
            case (R.id.head9):
                //catData.setMpaa(R.drawable.nokikissa_paa);
                //findViewById(R.id.imageViewPaa).setForeground(getDrawable(catData.getMpaa()));
                break;
        }

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
        mDataContainer.resetSoft();
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
        mDataContainer.saveCatDataToGrid();

        SharedPreferences settings = getPreferences(0);
        SharedPreferences.Editor editor = settings.edit();
        mDataContainer.saveDataToPreference(editor);
        //editor.putBoolean("silentMode", mSilentMode);

        // Commit the edits!
        editor.commit();
        Log.d(TAG, "onStop: now here");

    }

}
