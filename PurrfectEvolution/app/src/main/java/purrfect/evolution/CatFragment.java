package purrfect.evolution;


import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
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
    ImageButton catImage;
    ImageView tail,body,head;

    public CatFragment() {
        imageList = new ArrayList<>(25); // 25 is for testing purposes
        animationList = new ArrayList<>(25);
        random = new Random();
        i = 0;
    }

    public static CatFragment newInstance() {
        CatFragment fragment = new CatFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cat, container, false);
        catImage = (ImageButton) rootView.findViewById(R.id.imageButton2);

        CatData catData = MainActivity.getCatData();
        tail = (ImageView) rootView.findViewById(R.id.imageView_hantaCat);
        body = (ImageView) rootView.findViewById(R.id.imageViewKroppaCat);
        head = (ImageView) rootView.findViewById(R.id.imageViewPaaCat);
        tail.setForeground(getActivity().getDrawable(catData.getMhanta()));
        body.setForeground(getActivity().getDrawable(catData.getMkroppa()));
        head.setForeground(getActivity().getDrawable(catData.getMpaa()));
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(rootView!=null) {
            if (isVisibleToUser) {
                CatData catData = MainActivity.getCatData();
                tail = (ImageView) rootView.findViewById(R.id.imageView_hantaCat);
                body = (ImageView) rootView.findViewById(R.id.imageViewKroppaCat);
                head = (ImageView) rootView.findViewById(R.id.imageViewPaaCat);
                tail.setForeground(getActivity().getDrawable(catData.getMhanta()));
                body.setForeground(getActivity().getDrawable(catData.getMkroppa()));
                head.setForeground(getActivity().getDrawable(catData.getMpaa()));
            }
        }
    }

    public void onCatClick(View view, Context context, RelativeLayout relativeLayout) {
        switch (view.getId()) {
            case R.id.imageButton2:

                // TODO: Create coin sprite animaion for when a button is clicked many coins are released in multiple directions... ( Waiting for pictures )

                // Create new imageView, add it to ArrayList and set image and sprite animation XMLs
                image = new ImageView(context);

                imageList.add(image);

                // Get random location for ImageViews on screen
                imageList.get(i).setX(calculateRandomWidth());
                imageList.get(i).setY(calculateRandomHeight());

                imageList.get(i).setBackgroundResource(R.drawable.sydan_list);

                // Create layout parameters for created ImageView
                RelativeLayout r1 = (RelativeLayout) relativeLayout;
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        150, 150);

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

    public void setScreenSize(int heightp, int widthp) {
        height = heightp;
        width = widthp;
    }

    public float calculateRandomWidth() {
        float finalX = random.nextFloat() * width;
        return finalX;
    }

    public float calculateRandomHeight() {
        float finalY = random.nextFloat() * height;
        return finalY;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        // On animation end removes first object from ArrayLists and "trims it down".
        imageList.get(0).setBackgroundResource(0);
        imageList.remove(0);
        animationList.remove(0);
        i--;
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
