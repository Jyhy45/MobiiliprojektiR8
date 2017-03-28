package purrfect.evolution;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;


import android.content.Context;
import android.os.Bundle;

import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
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


    public BuildingsFragment() {

    }

    public void popUpInflater(final View view, final Context context)
    {



        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(context, view);
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.popup_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (view.getId()) {
                    case R.id.building1:
                        Toast.makeText(context,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.building2:
                        Toast.makeText(context,"laalaalaa",Toast.LENGTH_SHORT).show();
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


                return true;
            }
        });

        popup.show();



    }


    public void onClick(View v,Context context) {
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
