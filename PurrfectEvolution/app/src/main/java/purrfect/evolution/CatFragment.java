package purrfect.evolution;


import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;


import android.content.Context;
import android.os.Bundle;

import android.support.v7.widget.AppCompatImageView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */

public class CatFragment extends Fragment implements Animation.AnimationListener {

    ImageView image;
    ArrayList<ImageView> imageList;
    ArrayList<Animation> animationList;
    int i;
    Random random;
    int height;
    int width;

    private static final String ARG_SECTION_NUMBER = "section_number";

    public CatFragment() {
        imageList = new ArrayList<>();
        animationList = new ArrayList<>();
        random = new Random();
        i = 0;
    }

    public void setScreenSize(int heightp, int widthp) {
        height = heightp;
        width = widthp;
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static CatFragment newInstance() {
        CatFragment fragment = new CatFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cat, container, false);
        return rootView;
    }

    public void onCatClick(View view, Context context, RelativeLayout relativeLayout) {
        switch (view.getId()) {
            case R.id.imageButton2:
                // TODO: Create different animations for hearts
                // TODO: Create coin sprite animaion for when a button is clicked many coins are released in multiple directions...

                //Toast.makeText(context, "" + height + width, Toast.LENGTH_SHORT).show();

                // Create new imageView, add it to ArrayList and set image and sprite animation XMLs
                image = new ImageView(context);

                imageList.add(image);
                imageList.get(i).setImageResource(R.drawable.ic_heart_0);
                imageList.get(i).setBackgroundResource(R.drawable.animation_list_filling);

                // Create layout parameters for created ImageView
                RelativeLayout r1 = (RelativeLayout) relativeLayout;
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                // Get random location for ImageViews on screen
                lp.leftMargin = calculateLeftMargin();
                lp.topMargin = calculateTopMargin();
                // Add ImageView with set parameters to layout
                r1.addView(imageList.get(i), lp);

                // Add moving animation to ArrayList
                animationList.add(AnimationUtils.loadAnimation(context, R.anim.move));
                // Set animation listener
                animationList.get(i).setAnimationListener(this);
                // Start sprite animation
                ((AnimationDrawable) imageList.get(i).getBackground()).start();
                // Start move animation
                imageList.get(i).startAnimation(animationList.get(i));

                i++;
        }
    }

    public int calculateLeftMargin() {
        int finalX = random.nextInt(width);
        return finalX;
    }

    public int calculateTopMargin() {
        int finalY = random.nextInt(height);
        return finalY;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        // On animation end removes first object from ArrayLists and "trims it down".
        imageList.remove(0);
        animationList.remove(0);
        i--;
       // Toast.makeText(c, "Animation end", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
