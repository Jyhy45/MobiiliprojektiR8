package purrfect.evolution;


/**
 * Created by jyhy on 3/24/17.
 */



public  class Building {

    static enum BuildingType{
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
        public int get_value(){
            return _value;
        }

        public static BuildingType fromInt(int i) {
            for (BuildingType b : BuildingType.values()){
                if (b.get_value() == i){return b;}
            }
        return null;

        }

    }

    public Building(BuildingType buildingType,int buildingLevel){
        this.mBuildingLevel = buildingLevel;
        this.mBType = buildingType;
    }
    public Building(){
        this(BuildingType.NONE,0);
    }


    private double mBaseProductionTime=0;
    private BuildingType mBType;
    private double mBaseProductionAmount=0;
    private double mBuildingLevel=0;
    private double mBaseCost=20;
    public int mImagePath;                  //=R.drawable.ic_heart_0;
    public int mAnimationPath;              //=R.drawable.animation_list_filling;
    private double mProductionAmountPerSecond;

    public double getmCurrentBuildingCost() {
        return mCurrentBuildingCost;
    }

    private double mCurrentBuildingCost;

    public double getmProductionAmountPerSecond() {
        return mProductionAmountPerSecond;
    }
    public void calculateAndSetProductionAmountPerSecond(){

        if (mBType == BuildingType.NONE){
            mProductionAmountPerSecond =0;
        }else if (mBType == BuildingType.SCRATCHINPOST){
            mProductionAmountPerSecond =1*this.mBuildingLevel;
        }else if(mBType == BuildingType.FEEDING_STATION){
            mProductionAmountPerSecond =5*this.mBuildingLevel;
        }else if(mBType == BuildingType.CHEW_MOUSE){
            mProductionAmountPerSecond =10*this.mBuildingLevel;
        }else if(mBType == BuildingType.YARN_BALL){
            mProductionAmountPerSecond =100*this.mBuildingLevel;
        }else if(mBType == BuildingType.CATNIP){
            mProductionAmountPerSecond =300*this.mBuildingLevel;
        }else{
            mProductionAmountPerSecond =0;
        }
    }
    public void calculateAndSetBuildingLevelUpCost(){
        if (mBType == BuildingType.NONE){
            mCurrentBuildingCost = Double.MAX_VALUE;
        }else if (mBType == BuildingType.SCRATCHINPOST){
            mCurrentBuildingCost =1*this.mBuildingLevel;
        }else if(mBType == BuildingType.FEEDING_STATION){
            mCurrentBuildingCost =5*this.mBuildingLevel;
        }else if(mBType == BuildingType.CHEW_MOUSE){
            mCurrentBuildingCost =10*this.mBuildingLevel;
        }else if(mBType == BuildingType.YARN_BALL){
            mCurrentBuildingCost =100*this.mBuildingLevel;
        }else if(mBType == BuildingType.CATNIP){
            mCurrentBuildingCost =300*this.mBuildingLevel;
        }else{
            mCurrentBuildingCost = Double.MAX_VALUE;
        }
    }





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
