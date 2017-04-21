package purrfect.evolution;

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
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;


public class OptionsFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    RelativeLayout optionsRelativeLayout;

    public OptionsFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static OptionsFragment newInstance() {
        OptionsFragment fragment = new OptionsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void onClick(View view, Context context)
    {
        int state = 0;
        int state2 = 0;

        switch(view.getId()) {
            case R.id.On1:
                if (state == 1) {

                }
                else if (state == 0) {
                }

                break;
            case R.id.On2:
                if (state2 == 1) {
                }
                else if (state2 == 0) {
                }
                break;
            case R.id.Off1:
                if (state == 1) {
                }
                else if (state == 0) {
                }
                break;
            case R.id.Off2:
                if (state2 == 1) {
                }
                else if (state2 == 0) {
                }
                break;


    }

    }

    public void buttonToggle(View view, Context context)
    {
        ImageButton imageButton;
        imageButton = (ImageButton)view;
        ImageButton imageButton2;




        imageButton.setImageDrawable(getResources().getDrawable(R.drawable.off_harm));

        imageButton.setImageDrawable(getResources().getDrawable(R.drawable.off_pun));

        imageButton.setImageDrawable(getResources().getDrawable(R.drawable.on_harm));

        imageButton.setImageDrawable(getResources().getDrawable(R.drawable.on_vihr));

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_options , container, false);
        optionsRelativeLayout = (RelativeLayout) rootView;
        return rootView;
    }

}
