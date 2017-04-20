package purrfect.evolution;


import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;


import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class EvolutionFragment extends Fragment {

    ImageView catImage;

    public EvolutionFragment(){

    }

    public static EvolutionFragment newInstance() {
        EvolutionFragment fragment = new EvolutionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_evolution , container, false);

        catImage = (ImageView) rootView.findViewById(R.id.imageView4);
        //catImage.setImageResource(R.drawable.kissa_paa);

        catImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //v.getId() will give you the image id
                //catImage.setImageResource(0);
                CatData catData = MainActivity.getCatData();
                catImage.setImageDrawable(catData.getCat());
            }
        });

        //catImage.setImageResource(R.drawable.kissa_hanta);
        return rootView;
    }

}
