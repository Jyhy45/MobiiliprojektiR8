package purrfect.evolution;


import android.os.Bundle;
/**
 * Created by jyhy on 3/24/17.
 */
enum BuildingType{
    SCRATCHINPOST,      //produces happines
    FEEDING_STATION,    //produces happines
    CHEW_MOUSE,         //produces happines
    YARN_BALL,          //creates yarnballs in to the cat screen that can tapped --> gives happines
    CATNIP,             //produces happines


}


public abstract class Building {



    Building(){

    }
    private double mBaseProductionTime;
    private double mBaseProductionAmount;
    private double mBuildingLevel=0;
    private double mBaseCost;
    public int mImagePath;                  //=R.drawable.ic_heart_0;
    public int mAnimationPath;              //=R.drawable.animation_list_filling;

    private double getmBaseCost() {
        return mBaseCost;
    }

    public double getmBaseProductionAmount() {
        return mBaseProductionAmount;
    }

    public double getmBaseProductionTime() {
        return mBaseProductionTime;
    }

    public double getmBuildingLevel() {
        return mBuildingLevel;
    }

    public void setmBuildingLevel(double mBuildingLevel) {
        this.mBuildingLevel = mBuildingLevel;
    }

    public abstract double getCurrentProductionAmount();
    public abstract double getCurrentProductionTime();
    public abstract double getCurrentCost();


}
