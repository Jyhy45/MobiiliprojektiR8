package purrfect.evolution;


import android.os.Bundle;
/**
 * Created by jyhy on 3/24/17.
 */



public  class Building {

    static enum BuildingType{
        SCRATCHINPOST,      //produces happines
        FEEDING_STATION,    //produces happines
        CHEW_MOUSE,         //produces happines
        YARN_BALL,          //creates yarnballs in to the cat screen that can tapped --> gives happines
        CATNIP,             //produces happines


    }

    Building(){

    }
    private double mBaseProductionTime=0;
    private BuildingType mBType;
    private double mBaseProductionAmount=0;
    private double mBuildingLevel=0;
    private double mBaseCost=0;
    public int mImagePath;                  //=R.drawable.ic_heart_0;
    public int mAnimationPath;              //=R.drawable.animation_list_filling;


    public double getmBaseProductionTime() {
        return mBaseProductionTime;
    }

    public void setmBaseProductionTime(double mBaseProductionTime) {
        this.mBaseProductionTime = mBaseProductionTime;
    }

    public BuildingType getmBType() {
        return mBType;
    }

    public void setmBType(BuildingType mBType) {
        this.mBType = mBType;
    }

    public double getmBaseProductionAmount() {
        return mBaseProductionAmount;
    }

    public void setmBaseProductionAmount(double mBaseProductionAmount) {
        this.mBaseProductionAmount = mBaseProductionAmount;
    }

    public double getmBuildingLevel() {
        return mBuildingLevel;
    }

    public void setmBuildingLevel(double mBuildingLevel) {
        this.mBuildingLevel = mBuildingLevel;
    }

    public double getmBaseCost() {
        return mBaseCost;
    }

    public void setmBaseCost(double mBaseCost) {
        this.mBaseCost = mBaseCost;
    }

    public int getmImagePath() {
        return mImagePath;
    }

    public void setmImagePath(int mImagePath) {
        this.mImagePath = mImagePath;
    }

    public int getmAnimationPath() {
        return mAnimationPath;
    }

    public void setmAnimationPath(int mAnimationPath) {
        this.mAnimationPath = mAnimationPath;
    }
}
