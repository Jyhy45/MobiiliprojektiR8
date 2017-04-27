package purrfect.evolution;


import android.support.v4.app.Fragment;



import android.content.Context;
import android.os.Bundle;


import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;


import java.util.ArrayList;

import static android.content.ContentValues.TAG;


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

    public void buttonsAtStart(Context context, LinearLayout linearLayout) {

        ArrayList<View> touchables =linearLayout.getTouchables();
        for (View touchable : touchables) {
            Log.i(TAG, "bananaa");
            ImageButton imageButton = (ImageButton) touchable;
            buildingNumber(touchable);
            imageUpdate(imageButton, context);
        }
    }

    public void imageUpdate(View view, Context context)
    {
        ImageView imageView = (ImageView) view;

        switch (buildingGrid.getBuildingArray().get(buildingNumber).getmBType()) {
            case NONE:
                imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.rakennus_tausta));
                break;
            case SCRATCHINPOST:
                imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.raapimapuutaso1));
                break;
            case FEEDING_STATION:
                if(buildingGrid.getBuildingArray().get(buildingNumber).getmBuildingLevel()>1)
                {
                    imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.fishielevel2));
                }
                else
                {
                    imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.fishie));
                }
                break;
            case CHEW_MOUSE:
                imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.leluhiiritaso1));
                break;
            case YARN_BALL:
                imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.yarnball));
                break;
            case CATNIP:
                imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.catler));
                break;
            default:
                imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.rakennus_tausta));
                break;
        }
    }

    public void buildingNumber(View view){

        buildingNumber = 0;

        switch (view.getId()) {
            case R.id.building1:
                buildingNumber = 0;
                break;
            case R.id.building2:
                buildingNumber = 1;
                break;
            case R.id.building3:
                buildingNumber = 2;
                break;
            case R.id.building4:
                buildingNumber = 3;
                break;
            case R.id.building5:
                buildingNumber = 4;
                break;
            case R.id.building6:
                buildingNumber = 5;
                break;
            case R.id.building7:
                buildingNumber = 6;
                break;
            case R.id.building8:
                buildingNumber = 7;
                break;
            case R.id.building9:
                buildingNumber = 8;
                break;
        }



    }

    public void popUpInflater(final View view, final Context context) {
        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(context, view);
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.popup_menu, popup.getMenu());

        buildingNumber(view);

        for (int i = 0; i < popup.getMenu().size(); i++) {
            popup.getMenu().getItem(i).setEnabled(false);
            }

            if (Double.compare(mDataContainer.getmCurrentMoney(),buildingGrid.getBuildingArray().get(buildingNumber).getBaseCost(Building.BuildingType.SCRATCHINPOST)) >= 0)
            {
                popup.getMenu().getItem(0).setEnabled(true);
            }
        if (Double.compare(mDataContainer.getmCurrentMoney(),buildingGrid.getBuildingArray().get(buildingNumber).getBaseCost(Building.BuildingType.FEEDING_STATION)) >= 0)
            {
                popup.getMenu().getItem(1).setEnabled(true);
            }
        if (Double.compare(mDataContainer.getmCurrentMoney(),buildingGrid.getBuildingArray().get(buildingNumber).getBaseCost(Building.BuildingType.CHEW_MOUSE)) >= 0)
            {
                popup.getMenu().getItem(2).setEnabled(true);
            }
        if (Double.compare(mDataContainer.getmCurrentMoney(),buildingGrid.getBuildingArray().get(buildingNumber).getBaseCost(Building.BuildingType.YARN_BALL)) >= 0)
            {
                popup.getMenu().getItem(3).setEnabled(true);
            }
        if (Double.compare(mDataContainer.getmCurrentMoney(),buildingGrid.getBuildingArray().get(buildingNumber).getBaseCost(Building.BuildingType.CATNIP)) >= 0)
            {
                popup.getMenu().getItem(4).setEnabled(true);
            }

            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    buildingGrid.getBuildingArray().set(buildingNumber,buildingSelector(item,buildingGrid.getBuildingArray().get(buildingNumber)));
                    imageUpdate(view,context);

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

        buildingNumber(view);

        for (int i = 0; i < popup.getMenu().size(); i++) {
            popup.getMenu().getItem(i).setEnabled(false);
        }

        if (Double.compare(mDataContainer.getmCurrentMoney(),buildingGrid.getBuildingArray().get(buildingNumber).getmCurrentBuildingCost()) >= 0)
        {
            popup.getMenu().getItem(0).setEnabled(true);
        }
        popup.getMenu().getItem(1).setEnabled(true);






        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {

                if(actionSelector(item, buildingGrid.getBuildingArray().get(buildingNumber)) == 1)
                {
                    buildingGrid.getBuildingArray().get(buildingNumber).setmBType(Building.BuildingType.NONE);
                    imageUpdate(view,context);
                }
                else
                {
                    imageUpdate(view,context);
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
        building.setmBuildingLevel(1);



        mDataContainer.spentMoney(building.getBaseCost());

        return building;

    }

    public int actionSelector(MenuItem item, Building building) {

        switch (item.getItemId()) {
            case R.id.Upgrade:
                building.levelUpBuildingNotFree(mDataContainer);

                return 0;

            case R.id.Destroy:
                return 1;


        }
        return 0;
    }


    public void onClick(View v,Context context) {

        buildingNumber(v);

        if(buildingGrid.getBuildingArray().get(buildingNumber).getmBType() == Building.BuildingType.NONE)
        {
            popUpInflater(v,context);
        }
        else
        {
            popUpInflater2(v,context);
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
        Context context = getContext();
        buttonsAtStart(context,(LinearLayout) rootView.findViewById(R.id.fragment_buildings));
        return rootView;
    }



}
