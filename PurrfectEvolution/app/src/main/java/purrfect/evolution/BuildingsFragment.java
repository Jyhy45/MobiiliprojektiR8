package purrfect.evolution;

import android.media.Image;
import android.provider.ContactsContract;
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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.button;


public class BuildingsFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    BuildingGrid buildingGrid = MainActivity.getMbuildingGrid();
    DataContainerForPurfectEvolution mDataContainer = MainActivity.getmDataContainer();
    int buildingNumber = 0;

    public BuildingsFragment() {

    }

    public void popUpInflater(final View view, final Context context)
    {
        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(context, view);
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.popup_menu, popup.getMenu());

        buildingNumber = 0;

        switch (view.getId())
        {
            case R.id.building1:
                buildingNumber=0;
                break;
            case R.id.building2:
                buildingNumber=1;
                break;
            case R.id.building3:
                buildingNumber=2;
                break;
            case R.id.building4:
                buildingNumber=3;
                break;
            case R.id.building5:
                buildingNumber=4;
                break;
            case R.id.building6:
                buildingNumber=5;
                break;
            case R.id.building7:
                buildingNumber=6;
                break;
            case R.id.building8:
                buildingNumber=7;
                break;
            case R.id.building9:
                buildingNumber=8;
                break;
        }

        for (int i = 0; i>4; i++) {
            popup.getMenu().getItem(i).setEnabled(false);


            if (mDataContainer.getmCurrentMoney() > buildingGrid.getBuildingArray().get(buildingNumber).getBaseCost(Building.BuildingType.SCRATCHINPOST)){

            }
            else if (mDataContainer.getmCurrentMoney() > buildingGrid.getBuildingArray().get(buildingNumber).getBaseCost(Building.BuildingType.FEEDING_STATION))
            {
                popup.getMenu().getItem(0).setEnabled(true);
            }
            else if (mDataContainer.getmCurrentMoney() > buildingGrid.getBuildingArray().get(buildingNumber).getBaseCost(Building.BuildingType.CHEW_MOUSE))
            {
                popup.getMenu().getItem(1).setEnabled(true);
            }
            else if (mDataContainer.getmCurrentMoney() > buildingGrid.getBuildingArray().get(buildingNumber).getBaseCost(Building.BuildingType.YARN_BALL))
            {
                popup.getMenu().getItem(2).setEnabled(true);
            }
            else if (mDataContainer.getmCurrentMoney() > buildingGrid.getBuildingArray().get(buildingNumber).getBaseCost(Building.BuildingType.CATNIP)) {
                {
                    popup.getMenu().getItem(3).setEnabled(true);
                }
            }

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (view.getId()) {
                    case R.id.building1:

                        buildingGrid.setBuilding1(buildingSelector(item, buildingGrid.getBuilding1()));
                        Toast.makeText(context,"Tääl on :" + buildingGrid.getBuilding1().getmBType(),Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.building2:
                        buildingGrid.setBuilding2(buildingSelector(item, buildingGrid.getBuilding2()));
                        Toast.makeText(context,"Tääl on :" + buildingGrid.getBuilding2().getmBType(),Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.building3:
                        buildingGrid.setBuilding3(buildingSelector(item, buildingGrid.getBuilding3()));
                        Toast.makeText(context,"Tääl on :" + buildingGrid.getBuilding3().getmBType(),Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.building4:
                        buildingGrid.setBuilding4(buildingSelector(item, buildingGrid.getBuilding4()));
                        Toast.makeText(context,"Tääl on :" + buildingGrid.getBuilding4().getmBType(),Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.building5:
                        buildingGrid.setBuilding5(buildingSelector(item, buildingGrid.getBuilding5()));
                        Toast.makeText(context,"Tääl on :" + buildingGrid.getBuilding5().getmBType(),Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.building6:
                        buildingGrid.setBuilding6(buildingSelector(item, buildingGrid.getBuilding6()));
                        Toast.makeText(context,"Tääl on :" + buildingGrid.getBuilding6().getmBType(),Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.building7:
                        buildingGrid.setBuilding7(buildingSelector(item, buildingGrid.getBuilding7()));
                        Toast.makeText(context,"Tääl on :" + buildingGrid.getBuilding7().getmBType(),Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.building8:
                        buildingGrid.setBuilding8(buildingSelector(item, buildingGrid.getBuilding8()));
                        Toast.makeText(context,"Tääl on :" + buildingGrid.getBuilding8().getmBType(),Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.building9:
                        buildingGrid.setBuilding9(buildingSelector(item, buildingGrid.getBuilding9()));
                        Toast.makeText(context,"Tääl on :" + buildingGrid.getBuilding9().getmBType(),Toast.LENGTH_SHORT).show();
                        break;
                }


                return true;
            }
        });

        popup.show();
    }

    public void popUpInflater2(final View view, final Context context) {
        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(context, view);
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.popup_menu2, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {

                switch (view.getId()) {
                    case R.id.building1:
                        if(actionSelector(item, buildingGrid.getBuilding1())== 1)
                        {
                            buildingGrid.getBuilding1().setmBType(Building.BuildingType.NONE);
                            buildingGrid.getBuilding1().setmBuildingLevel(0);
                        }
                        else
                        {

                        }
                        break;
                    case R.id.building2:
                        if(actionSelector(item, buildingGrid.getBuilding2())== 1)
                        {
                            buildingGrid.getBuilding2().setmBType(Building.BuildingType.NONE);
                            buildingGrid.getBuilding2().setmBuildingLevel(0);
                        }
                        else
                        {

                        }
                        break;
                    case R.id.building3:
                        if(actionSelector(item, buildingGrid.getBuilding3())== 1)
                        {
                            buildingGrid.getBuilding3().setmBType(Building.BuildingType.NONE);
                            buildingGrid.getBuilding3().setmBuildingLevel(0);
                        }
                        else
                        {

                        }
                        break;
                    case R.id.building4:
                        if(actionSelector(item, buildingGrid.getBuilding4())== 1)
                        {
                            buildingGrid.getBuilding4().setmBType(Building.BuildingType.NONE);
                            buildingGrid.getBuilding4().setmBuildingLevel(0);
                        }
                        else
                        {

                        }
                        break;
                    case R.id.building5:
                        if(actionSelector(item, buildingGrid.getBuilding5())== 1)
                        {
                            buildingGrid.getBuilding5().setmBType(Building.BuildingType.NONE);
                            buildingGrid.getBuilding5().setmBuildingLevel(0);
                        }
                        else
                        {

                        }
                        break;
                    case R.id.building6:
                        if(actionSelector(item, buildingGrid.getBuilding6())== 1)
                        {
                            buildingGrid.getBuilding6().setmBType(Building.BuildingType.NONE);
                            buildingGrid.getBuilding6().setmBuildingLevel(0);
                        }
                        else
                        {

                        }
                        break;
                    case R.id.building7:
                        if(actionSelector(item, buildingGrid.getBuilding7())== 1)
                        {
                            buildingGrid.getBuilding7().setmBType(Building.BuildingType.NONE);
                            buildingGrid.getBuilding7().setmBuildingLevel(0);
                        }
                        else
                        {

                        }
                        break;
                    case R.id.building8:
                        if(actionSelector(item, buildingGrid.getBuilding8())== 1)
                        {
                            buildingGrid.getBuilding8().setmBType(Building.BuildingType.NONE);
                            buildingGrid.getBuilding8().setmBuildingLevel(0);
                        }
                        else
                        {

                        }
                        break;
                    case R.id.building9:
                        if(actionSelector(item, buildingGrid.getBuilding9())== 1)
                        {
                            buildingGrid.getBuilding9().setmBType(Building.BuildingType.NONE);
                            buildingGrid.getBuilding9().setmBuildingLevel(0);
                        }
                        else
                        {

                        }
                        break;
                }

                return true;
            }
        });

        popup.show();
    }

    public Building buildingSelector(MenuItem item, Building building)
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

        return building;

    }

    public int actionSelector(MenuItem item, Building building) {

        switch (item.getItemId()) {
            case R.id.Upgrade:
                double oldLVL = building.getmBuildingLevel();
                building.setmBuildingLevel(oldLVL +1);
                return 0;

            case R.id.Destroy:
                return 1;


        }
        return 0;
    }


    public void onClick(View v,Context context) {

        switch (v.getId()) {
            case R.id.building1:
                if(buildingGrid.getBuilding1().getmBType() == Building.BuildingType.NONE)
                {
                    popUpInflater(v, context);
                }
                else
                {
                    popUpInflater2(v,context);
                }
                break;
            case R.id.building2:
                if(buildingGrid.getBuilding2().getmBType() == Building.BuildingType.NONE)
                {
                    popUpInflater(v, context);
                }
                else
                {
                    popUpInflater2(v,context);
                }
                break;
            case R.id.building3:
                if(buildingGrid.getBuilding3().getmBType() == Building.BuildingType.NONE)
                {
                    popUpInflater(v, context);
                }
                else
                {
                    popUpInflater2(v,context);
                }
                break;
            case R.id.building4:
                if(buildingGrid.getBuilding4().getmBType() == Building.BuildingType.NONE)
                {
                    popUpInflater(v, context);
                }
                else
                {
                    popUpInflater2(v,context);
                }
                break;
            case R.id.building5:
                if(buildingGrid.getBuilding5().getmBType() == Building.BuildingType.NONE)
                {
                    popUpInflater(v, context);
                }
                else
                {
                    popUpInflater2(v,context);
                }
                break;
            case R.id.building6:
                if(buildingGrid.getBuilding6().getmBType() == Building.BuildingType.NONE)
                {
                    popUpInflater(v, context);
                }
                else
                {
                    popUpInflater2(v,context);
                }
                break;
            case R.id.building7:
                if(buildingGrid.getBuilding7().getmBType() == Building.BuildingType.NONE)
                {
                    popUpInflater(v, context);
                }
                else
                {
                    popUpInflater2(v,context);
                }
                break;
            case R.id.building8:
                if(buildingGrid.getBuilding8().getmBType() == Building.BuildingType.NONE)
                {
                    popUpInflater(v, context);
                }
                else
                {
                    popUpInflater2(v,context);
                }
                break;
            case R.id.building9:
                if(buildingGrid.getBuilding9().getmBType() == Building.BuildingType.NONE)
                {
                    popUpInflater(v, context);
                }
                else
                {
                    popUpInflater2(v,context);
                }
                break;
        }

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
