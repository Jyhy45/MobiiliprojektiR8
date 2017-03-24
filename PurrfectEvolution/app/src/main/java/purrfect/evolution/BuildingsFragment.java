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
import android.widget.TextView;


public class BuildingsFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public BuildingsFragment() {


    }

    public static void onClick(View v) {
        switch (v.getId()) {
            case R.id.building1:
                break;
            case R.id.building2:
                break;
            case R.id.building3:
                break;
            case R.id.building4:
                break;
            case R.id.building5:
                break;
            case R.id.building6:
                break;
            case R.id.building7:
                break;
            case R.id.building8:
                break;
            case R.id.building9:
                break;
        }}

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static BuildingsFragment newInstance() {
        BuildingsFragment fragment = new BuildingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_buildings , container, false);


        return rootView;
    }



}
