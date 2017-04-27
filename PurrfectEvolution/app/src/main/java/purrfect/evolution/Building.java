package purrfect.evolution;


import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by jyhy on 3/24/17.
 */



public  class Building {

    enum BuildingType {
        NONE(0),               //no type assigned
        SCRATCHINPOST(1),      //produces happines
        FEEDING_STATION(2),    //produces happines
        CHEW_MOUSE(3),         //produces happines
        YARN_BALL(4),          //creates yarnballs in to the cat screen that can tapped --> gives happines
        CATNIP(5);           //produces happines

        private int _value;


        BuildingType(int value) {
            this._value = value;
        }

        public int get_value() {
            return _value;
        }

        public static BuildingType fromInt(int i) {
            for (BuildingType b : BuildingType.values()) {
                if (b.get_value() == i) {
                    return b;
                }
            }
            return null;

        }

    }

    public Building(BuildingType buildingType, int buildingLevel) {
        this.mBuildingLevel = buildingLevel;
        this.mBType = buildingType;
    }

    public Building() {
        this(BuildingType.NONE, 0);
    }


    private BuildingType mBType;
    private long mBuildingLevel = 0;
    private double mProductionAmountPerSecond;

    public double getmCurrentBuildingCost() {
        return mCurrentBuildingCost;
    }

    private double mCurrentBuildingCost;

    public double getmProductionAmountPerSecond() {
        return mProductionAmountPerSecond;
    }


    public void calculateAndSetProductionAmountPerSecond() {
        //income=baseincome*lvl
        if (mBType == BuildingType.NONE) {
            mProductionAmountPerSecond = 0;
        } else if (mBType == BuildingType.SCRATCHINPOST) {
            mProductionAmountPerSecond = 1 * this.mBuildingLevel;
        } else if (mBType == BuildingType.FEEDING_STATION) {
            mProductionAmountPerSecond = 5 * this.mBuildingLevel;
        } else if (mBType == BuildingType.CHEW_MOUSE) {
            mProductionAmountPerSecond = 30 * this.mBuildingLevel;
        } else if (mBType == BuildingType.YARN_BALL) {
            mProductionAmountPerSecond = 100 * this.mBuildingLevel;
        } else if (mBType == BuildingType.CATNIP) {
            mProductionAmountPerSecond = 300 * this.mBuildingLevel;
        } else {
            mProductionAmountPerSecond = 0;
            Log.d(TAG, "calculateAndSetProductionAmountPerSecond: unknow buildingtype fix me");
        }
    }

    public void calculateAndSetBuildingLevelUpCost() {
        //baseCost*multiplier^lvl
        if (mBType == BuildingType.NONE) {
            mCurrentBuildingCost = Double.MAX_VALUE;
        } else {
            double mMultiplier = 1.07;
            mCurrentBuildingCost = getBaseCost() * Math.pow(mMultiplier, this.mBuildingLevel);
        }
    }

    public double getBaseCost() {
        return getBaseCost(this.mBType);
    }

    public double getBaseCost(BuildingType bType) {
        if (bType == BuildingType.NONE) {
            return Double.MAX_VALUE;
        } else if (bType == BuildingType.SCRATCHINPOST) {
            return 20.0d;
        } else if (bType == BuildingType.FEEDING_STATION) {
            return 100.0d;
        } else if (bType == BuildingType.CHEW_MOUSE) {
            return 500.0d;
        } else if (bType == BuildingType.YARN_BALL) {
            return 2500.0d;
        } else if (bType == BuildingType.CATNIP) {
            return 10000.0d;
        } else {
            return Double.MAX_VALUE;

        }
    }

    public void levelUpBuildingFree() {
        mBuildingLevel++;
        this.calculateAndSetBuildingLevelUpCost();
        this.calculateAndSetProductionAmountPerSecond();
    }

    public boolean levelUpBuildingNotFree(DataContainerForPurfectEvolution data) {
        calculateAndSetBuildingLevelUpCost();
        boolean res = data.spentMoney(mCurrentBuildingCost);
        Log.d(TAG, "levelUpBuildingNotFree: used money?:" + res + " " + mCurrentBuildingCost);
        if (res) {
            mBuildingLevel++;
            this.calculateAndSetBuildingLevelUpCost();
            this.calculateAndSetProductionAmountPerSecond();
            return true;
        } else {
            return false;
        }
    }


    public BuildingType getmBType() {
        return mBType;
    }

    public void setmBType(BuildingType mBType) {
        this.mBType = mBType;
    }


    public long getmBuildingLevel() {
        return mBuildingLevel;
    }

    public void setmBuildingLevel(long mBuildingLevel) {
        this.mBuildingLevel = mBuildingLevel;
    }

}