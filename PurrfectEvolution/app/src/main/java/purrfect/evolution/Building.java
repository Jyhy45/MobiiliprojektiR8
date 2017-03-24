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
    private float mBaseProductionTime;
    private float mBaseProductionAmount;
    private float mBuildingLevel=0;
    private float mBaseCost;
    public int mImagePath;                  //=R.drawable.ic_heart_0;
    public int mAnimationPath;              //=R.drawable.animation_list_filling;

    private float getmBaseCost() {
        return mBaseCost;
    }

    public float getmBaseProductionAmount() {
        return mBaseProductionAmount;
    }

    public float getmBaseProductionTime() {
        return mBaseProductionTime;
    }

    public float getmBuildingLevel() {
        return mBuildingLevel;
    }

    public void setmBuildingLevel(float mBuildingLevel) {
        this.mBuildingLevel = mBuildingLevel;
    }

    public abstract float getCurrentProductionAmount();
    public abstract float getCurrentProductionTime();
    public abstract float getCurrentCost();


}
