package purrfect.evolution;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;


import android.content.Context;
import android.os.Bundle;

import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.button;


public class BuildingsFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    ImageButton imageButton;


    public BuildingsFragment() {

    }

    public void popUpInflater(View view, Context context)
    {



        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(context, view);
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.popup_menu, popup.getMenu());

        popup.show();
    }


    public void onClick(View v,Context context) {


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
        }
        popUpInflater(v, context);
    }

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
