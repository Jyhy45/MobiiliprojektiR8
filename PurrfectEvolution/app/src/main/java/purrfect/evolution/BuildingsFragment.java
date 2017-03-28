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

    BuildingGrid buildingGrid;

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
                Building building = new Building();
                buildingSelector(item, building);
                switch (view.getId()) {
                    case R.id.building1:
                        buildingGrid.setBuilding1(building);
                        Toast.makeText(context,"Tääl on :" + buildingGrid.getBuilding1().getmBType(),Toast.LENGTH_SHORT).show();
                        break;
//                    case R.id.building2:
//                        buildingGrid.setBuilding1(buildingSelector(item, building));
//                        Toast.makeText(context,"Tääl on :" + buildingGrid.getBuilding2().getmBType(),Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.building3:
//                        buildingGrid.setBuilding1(buildingSelector(item, building));
//                        Toast.makeText(context,"Tääl on :" + buildingGrid.getBuilding3().getmBType(),Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.building4:
//                        buildingGrid.setBuilding1(buildingSelector(item, building));
//                        Toast.makeText(context,"Tääl on :" + buildingGrid.getBuilding4().getmBType(),Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.building5:
//                        buildingGrid.setBuilding1(buildingSelector(item, building));
//                        Toast.makeText(context,"Tääl on :" + buildingGrid.getBuilding5().getmBType(),Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.building6:
//                        buildingGrid.setBuilding1(buildingSelector(item, building));
//                        Toast.makeText(context,"Tääl on :" + buildingGrid.getBuilding6().getmBType(),Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.building7:
//                        buildingGrid.setBuilding1(buildingSelector(item, building));
//                        Toast.makeText(context,"Tääl on :" + buildingGrid.getBuilding7().getmBType(),Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.building8:
//                        buildingGrid.setBuilding1(buildingSelector(item, building));
//                        Toast.makeText(context,"Tääl on :" + buildingGrid.getBuilding8().getmBType(),Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.building9:
//                        buildingGrid.setBuilding1(buildingSelector(item, building));
//                        Toast.makeText(context,"Tääl on :" + buildingGrid.getBuilding9().getmBType(),Toast.LENGTH_SHORT).show();
//                        break;
                }


                return true;
            }
        });

        popup.show();



    }

    public void buildingSelector(MenuItem item, Building building)
    {

        switch(item.getItemId()) {
            case R.id.Scratching_Post:
                building.setmBType(Building.BuildingType.SCRATCHINPOST);
                break;
            case R.id.Feeding_Station:
                building.setmBType(Building.BuildingType.FEEDING_STATION);
                break;
            case R.id.Chew_Mouse:
                building.setmBType(Building.BuildingType.CHEW_MOUSE);
                break;
            case R.id.Yarn_Ball:
                building.setmBType(Building.BuildingType.YARN_BALL);
                break;
            case R.id.Catnip:
                building.setmBType(Building.BuildingType.CATNIP);
                break;

        }

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
