package purrfect.evolution;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

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

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


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
                    return OptionsFragment.newInstance(1);
                case 2:
                    return BuildingsFragment.newInstance(1);
                case 1:
                    return EvolutionFragment.newInstance(1);
                case 0:
                    return CatFragment.newInstance(1);
                default:
                    return CatFragment.newInstance(1);
            }

        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }


    }

    ImageView image;
    List<ImageView> imageList;
    List<Animation> animationList;
    int i;


    public void onCatClick()
    {
        image = new ImageView(this);


        imageList.add(image);
        imageList.get(i).setImageResource(R.drawable.ic_heart_0);

        // Keksi tapa lisätä "täyttö animaatio" luotaviin kuviin.
        //imageList.get(i).setBackgroundResource((ImageView) findViewById(R.drawable.animation_list_filling));
        RelativeLayout r1 = (RelativeLayout) findViewById(R.id.cat_fragment);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.BELOW, R.id.imageButton2);
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, R.id.imageButton2);

        r1.addView(imageList.get(i),lp);

        animationList.add(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move));

        //animationList.get(i).setAnimationListener(this);



        imageList.get(i).startAnimation(animationList.get(i));
        i++;
    }

}
