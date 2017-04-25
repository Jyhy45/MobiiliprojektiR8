package purrfect.evolution;

import android.content.SharedPreferences;
import android.util.Log;

import java.lang.reflect.Field;

import static android.content.ContentValues.TAG;

/**
 * Created by jyhy on 3/27/17.
 *
 */

final class DataContainerForPurfectEvolution {
    double mCurrentSuscripers = 0;
    double mSuscripersToBeGained = 0;
    double mCurrentMoneyIncrease = 0;
    double mCurrentHappinessIncrease = 0;

    double mCurrentMoney = 0;
    double mCurrentHappines = 0;

    long mLifeTimeClicks = 0;
    long mSessionClicks = 0;
    long mThisCycleClicks = 0;

    double mLifeTimeMoneyEarnings = 0;
    double mSessionMoneyEarnings = 0;
    double mThisCycleMoneyEarnings = 0;

    double mLifeTimeHappinessEarnings = 0;
    double mSessionHappinessEarnings = 0;
    double mThisCycleHappinessEarnings = 0;

    int catHead,catBody,catTail;

    //options variables
    int mSoundOnOff = 0;

    int mAnimationsOnOff = 0;

    //Variables for buildings for saving purposes
    int mTypeBuilding1 = 0;

    long mLevelBuilding1 = 0;
    int mTypeBuilding2 = 0;

    long mLevelBuilding2 = 0;
    int mTypeBuilding3 = 0;

    long mLevelBuilding3 = 0;
    int mTypeBuilding4 = 0;

    long mLevelBuilding4 = 0;
    int mTypeBuilding5 = 0;

    long mLevelBuilding5 = 0;
    int mTypeBuilding6 = 0;

    long mLevelBuilding6 = 0;
    int mTypeBuilding7 = 0;

    long mLevelBuilding7 = 0;
    int mTypeBuilding8 = 0;

    long mLevelBuilding8 = 0;
    int mTypeBuilding9 = 0;

    long mLevelBuilding9 = 0;


    public int getmSoundOnOff() {
        return mSoundOnOff;
    }

    public void setmSoundOnOff(int mSoundOnOff) {
        this.mSoundOnOff = mSoundOnOff;
    }

    public int getmAnimationsOnOff() {
        return mAnimationsOnOff;
    }

    public void setmAnimationsOnOff(int mAnimationsOnOff) {
        this.mAnimationsOnOff = mAnimationsOnOff;
    }


    public DataContainerForPurfectEvolution()
    {

    }
    /**
     * Loads values for all variables from sharedPreferences
     * @param data is instance of SharedPreferences in which persisting data is stored between sessions.
     *
     * @return Should return true if load was successful needs some TLC
     * */

    public boolean loadDataFromPreference(SharedPreferences data){
        // TODO: 27.3.2017 Add resetting session variables.
        // FIXME: 27.3.2017 Return values and test if it actually load values correctly.
        //mThisCycleHappinessEarnings = Double.longBitsToDouble(data.getLong("mThisCycleHappinessEarnings",0));
        Class<?> c = this.getClass();
        for(Field field :c.getDeclaredFields()){
            field.setAccessible(true);
            Class<?> ctb = field.getType();
            if(long.class.equals(ctb)){
                try {
                    String _name = field.getName();
                    long _value =data.getLong(_name,0);
                    field.setLong(this,_value);
                    Log.d(TAG, "loadDataFromPreference: Success name "+_name+" value "+_value);

                } catch (Exception exc){
                    Log.e(TAG, "loadDataFromPreference: long FAIL"+c+" ",exc );
                    //return false;
                }

            }else if(double.class.equals(ctb)) {
                try {
                    String _name = field.getName();
                    double _value = Double.longBitsToDouble(data.getLong(_name,0));
                    field.setDouble(this,_value);

                    Log.d(TAG, "loadDataFromPreference: Success name "+_name+" value "+_value);
                }catch (Exception exc){
                    Log.e(TAG, "loadDataFromPreference: double FAIL "+c+"  ",exc );
                }

            }else if (int.class.equals(ctb)){
                try {
                    String _name = field.getName();
                    int _value =data.getInt(_name,0);
                    field.setInt(this,_value);
                    Log.d(TAG, "loadDataFromPreference: Success name "+_name+" value "+_value);

                } catch (Exception exc){
                    Log.e(TAG, "loadDataFromPreference: long FAIL"+c+" ",exc );
                    //return false;
                }

            }else{
                Log.d(TAG, "loadDataFromPreference: last else datatype not regokniced");
            }

        }


        return true;
    }
    /**
     * Saves all variables to sharedpreferences.
     * @param editor SharedPreferences.Editor instance for saving data.. remember to commit changes.
     * @return should return true if saving was successful.
     * */
    public boolean saveDataToPreference(SharedPreferences.Editor editor){
        //editor.putLong("mThisCycleHappinessEarnings",Double.doubleToRawLongBits(mThisCycleHappinessEarnings) );
        Class<?> c = this.getClass();
        for (Field field : c.getDeclaredFields()){
            Class<?> classToBeInspected = field.getType();
            if (long.class.equals(classToBeInspected)){
                try {
                    String _name=field.getName();
                    long _value= field.getLong(this);
                    editor.putLong(_name,_value);
                    Log.d(TAG,"saveDataToPreference: name: "+_name+" value"+_value);

                } catch (Exception exc){
                    Log.e(TAG, "saveDataToPreference: ",exc);
                    //return false;
                }


            }else if (double.class.equals(classToBeInspected)) {
                try {
                    String _name=field.getName();
                    double _value= field.getDouble(this);
                    editor.putLong(_name,Double.doubleToRawLongBits(_value));
                    Log.d(TAG,"saveDataToPreference: name: "+_name+" value"+_value);
                } catch (Exception exc){
                    Log.e(TAG, "saveDataToPreference: ",exc);

                    //return false;
                }
            }else if (int.class.equals(classToBeInspected)){
                try {
                    String _name=field.getName();
                    int _value= field.getInt(this);
                    editor.putInt(_name,_value);
                    Log.d(TAG,"saveDataToPreference: name: "+_name+" value"+_value);
                } catch (Exception exc){
                    Log.e(TAG, "saveDataToPreference: ",exc);

                    //return false;
                }

            }else{
                Log.d(TAG, "saveDataToPreference: Unhandled variable type");
                //return false;
            }
        }
        return true;
    }

    /**
     * Increment all click variables
     * */
    public  void receivedClick(){
        mLifeTimeClicks++;
        mSessionClicks++;
        mThisCycleClicks++;
        //TODO: encrease happines by corrent amount.
        earnedHappiness(2);

    }


    /**
     * Increments all money variables
     * @param money Amount of money to be incremented
     * */
    public  void earnedMoney(double money){
        mLifeTimeMoneyEarnings+=money;
        mSessionMoneyEarnings+=money;
        mThisCycleMoneyEarnings+=money;
        mCurrentMoney+=money;

    }

    public boolean convertHappinessToMoney(){
        double _happiness = getmCurrentHappines();
        double _moneyToBeGained=0;
        _moneyToBeGained = _happiness * (mCurrentSuscripers/0.001d+0.5d);
        boolean res = spentHappiness(_happiness);
        if (res){
            earnedMoney(_moneyToBeGained);
            return true;
        }else{

            return false;
        }
    }

    /**
     * Incerements all happiness variables
     * @param happiness amount of happiness to added to variables
     * */
    public  void earnedHappiness(double happiness){
        mLifeTimeHappinessEarnings +=happiness;
        mSessionHappinessEarnings +=happiness;
        mThisCycleHappinessEarnings +=happiness;
        mCurrentHappines+=happiness;

    }


    /**
     * Spends happiness if there is enough happiness to spend
     * @param mSpentHappiness amount of happiness to be spent will only spend happiness
     *                       if there is enough happiness
     *
     * @return Return true if happiness was used; return false if happiness was not spent
     * */
    public  boolean spentHappiness(double mSpentHappiness){
        if (Double.compare(mSpentHappiness,mCurrentHappines)>=0)
        {
            mCurrentHappines-=mSpentHappiness;
            return true;
        }else{
            return false;
        }
    }

    /**
     * Spends money if there is enough money to spend
     * @param mSpentMoney amount of money to be spent will only spend money
     *                       if there is enough money
     *
     * @return Return true if money was used; return false if money was not spent
     * */
    public  boolean spentMoney(double mSpentMoney){
        if (Double.compare(mCurrentMoney,mSpentMoney)>=0)
        {
            mCurrentMoney-=mSpentMoney;
            return true;
        }else{
            return false;
        }
    }


    /** Resets cycle variables*/
    public  void resetSessio(){
        mSessionClicks=0;
        mSessionHappinessEarnings =0;
        mSessionMoneyEarnings=0;
    }

    public void resetSoft(){
        //TODO:reset most variables and gain suscripers
        //resets cycle variables
        calculateAndSetSuscripersToBeGained();
        mCurrentSuscripers+=mSuscripersToBeGained;

        mCurrentMoneyIncrease = 0;
        mCurrentHappinessIncrease = 0;

        mCurrentMoney = 0;
        mCurrentHappines = 0;

        mThisCycleClicks = 0;

        mThisCycleMoneyEarnings = 0;

        mThisCycleHappinessEarnings = 0;


        mTypeBuilding1 = 0;
        mLevelBuilding1 = 0;

        mTypeBuilding2 = 0;
        mLevelBuilding2 = 0;

        mTypeBuilding3 = 0;
        mLevelBuilding3 = 0;

        mTypeBuilding4 = 0;
        mLevelBuilding4 = 0;

        mTypeBuilding5 = 0;
        mLevelBuilding5 = 0;

        mTypeBuilding6 = 0;
        mLevelBuilding6 = 0;

        mTypeBuilding7 = 0;
        mLevelBuilding7 = 0;

        mTypeBuilding8 = 0;
        mLevelBuilding8 = 0;

        mTypeBuilding9 = 0;
        mLevelBuilding9 = 0;
        MainActivity.getMbuildingGrid().reset_buildings();
        //TODO:gain suscripers
    }
    public void resetHard(){
        //TODO:might have to call save to preferences and reset buildinggrid
        mCurrentSuscripers = 0;
        mSuscripersToBeGained = 0;
        mCurrentMoneyIncrease = 0;
        mCurrentHappinessIncrease = 0;

        mCurrentMoney = 0;
        mCurrentHappines = 0;

        mLifeTimeClicks = 0;
        mSessionClicks = 0;
        mThisCycleClicks = 0;

        mLifeTimeMoneyEarnings = 0;
        mSessionMoneyEarnings = 0;
        mThisCycleMoneyEarnings = 0;

        mLifeTimeHappinessEarnings = 0;
        mSessionHappinessEarnings = 0;
        mThisCycleHappinessEarnings = 0;


        mTypeBuilding1 = 0;
        mLevelBuilding1 = 0;

        mTypeBuilding2 = 0;
        mLevelBuilding2 = 0;

        mTypeBuilding3 = 0;
        mLevelBuilding3 = 0;

        mTypeBuilding4 = 0;
        mLevelBuilding4 = 0;

        mTypeBuilding5 = 0;
        mLevelBuilding5 = 0;

        mTypeBuilding6 = 0;
        mLevelBuilding6 = 0;

        mTypeBuilding7 = 0;
        mLevelBuilding7 = 0;

        mTypeBuilding8 = 0;
        mLevelBuilding8 = 0;

        mTypeBuilding9 = 0;
        mLevelBuilding9 = 0;
        MainActivity.getMbuildingGrid().reset_buildings();

    }
    public void tickTime(){
        //TODO:implement this
        mCurrentHappines+=(mCurrentHappinessIncrease);
        mCurrentMoney+=(mCurrentMoneyIncrease);

        //Log.d(TAG, "tickTime: Happines "+mCurrentHappines+" money "+mCurrentMoney);
        //Log.d(TAG, "tickTime: Increase Happines " +mCurrentHappinessIncrease+" money:" +mCurrentMoneyIncrease);

        //TODO: run this when buildings change .. not here .. only here for testing
        calculateEverything();
    }

    public  double getmCurrentMoney() {
        return mCurrentMoney;
    }

    public  double getmCurrentHappines() {
        return mCurrentHappines;
    }

    public boolean saveBuildingDataToDataContainer(BuildingGrid g){
        mLevelBuilding1 = g.getBuilding1().getmBuildingLevel();
        mLevelBuilding2 = g.getBuilding2().getmBuildingLevel();
        mLevelBuilding3 = g.getBuilding3().getmBuildingLevel();
        mLevelBuilding4 = g.getBuilding4().getmBuildingLevel();
        mLevelBuilding5 = g.getBuilding5().getmBuildingLevel();
        mLevelBuilding6 = g.getBuilding6().getmBuildingLevel();
        mLevelBuilding7 = g.getBuilding7().getmBuildingLevel();
        mLevelBuilding8 = g.getBuilding8().getmBuildingLevel();
        mLevelBuilding9 = g.getBuilding9().getmBuildingLevel();

        mTypeBuilding1 = g.getBuilding1().getmBType().get_value();
        mTypeBuilding2 = g.getBuilding2().getmBType().get_value();
        mTypeBuilding3 = g.getBuilding3().getmBType().get_value();
        mTypeBuilding4 = g.getBuilding4().getmBType().get_value();
        mTypeBuilding5 = g.getBuilding5().getmBType().get_value();
        mTypeBuilding6 = g.getBuilding6().getmBType().get_value();
        mTypeBuilding7 = g.getBuilding7().getmBType().get_value();
        mTypeBuilding8 = g.getBuilding8().getmBType().get_value();
        mTypeBuilding9 = g.getBuilding9().getmBType().get_value();

        
        return true;
    }
    public boolean loadBuildingDataToGrid(BuildingGrid g){
        g.getBuilding1().setmBType(Building.BuildingType.fromInt(mTypeBuilding1));
        g.getBuilding2().setmBType(Building.BuildingType.fromInt(mTypeBuilding2));
        g.getBuilding3().setmBType(Building.BuildingType.fromInt(mTypeBuilding3));
        g.getBuilding4().setmBType(Building.BuildingType.fromInt(mTypeBuilding4));
        g.getBuilding5().setmBType(Building.BuildingType.fromInt(mTypeBuilding5));
        g.getBuilding6().setmBType(Building.BuildingType.fromInt(mTypeBuilding6));
        g.getBuilding7().setmBType(Building.BuildingType.fromInt(mTypeBuilding7));
        g.getBuilding8().setmBType(Building.BuildingType.fromInt(mTypeBuilding8));
        g.getBuilding9().setmBType(Building.BuildingType.fromInt(mTypeBuilding9));

        g.getBuilding1().setmBuildingLevel(mLevelBuilding1);
        g.getBuilding2().setmBuildingLevel(mLevelBuilding2);
        g.getBuilding3().setmBuildingLevel(mLevelBuilding3);
        g.getBuilding4().setmBuildingLevel(mLevelBuilding4);
        g.getBuilding5().setmBuildingLevel(mLevelBuilding5);
        g.getBuilding6().setmBuildingLevel(mLevelBuilding6);
        g.getBuilding7().setmBuildingLevel(mLevelBuilding7);
        g.getBuilding8().setmBuildingLevel(mLevelBuilding8);
        g.getBuilding9().setmBuildingLevel(mLevelBuilding9);

        return true;
    }
    public void saveCatDataToGrid(){
        catBody = MainActivity.getCatData().getMkroppa();
        catHead = MainActivity.getCatData().getMpaa();
        catTail = MainActivity.getCatData().getMhanta();
    }
    public void loadCatDataFromGrid(){
        MainActivity.getCatData().setMkroppa(catBody);
        MainActivity.getCatData().setMpaa(catHead);
        MainActivity.getCatData().setMhanta(catTail);

    }

    public void calculateMoneyIncrease(){
        mCurrentMoneyIncrease=0;
        for (Building b: MainActivity.getMbuildingGrid().getBuildingArray()) {
            if (false){
                //TODO:not sure if there is periodic money ticker...
            }
        }


    }
    public void calculateHappinesIncrease(){
        mCurrentHappinessIncrease=0;
        for (Building b: MainActivity.getMbuildingGrid().getBuildingArray()){
            if (b.getmBType()== Building.BuildingType.FEEDING_STATION||
                    b.getmBType()== Building.BuildingType.CATNIP||
                    b.getmBType()== Building.BuildingType.CHEW_MOUSE||
                    b.getmBType()== Building.BuildingType.YARN_BALL||
                    b.getmBType()== Building.BuildingType.SCRATCHINPOST){
                mCurrentHappinessIncrease+=b.getmProductionAmountPerSecond();

            }
        }
    }

    public void calculateEverything(){
        //Log.d(TAG, "calculateEverything: Now Running");
        for (Building b: MainActivity.getMbuildingGrid().getBuildingArray()) {
            b.calculateAndSetBuildingLevelUpCost();
            b.calculateAndSetProductionAmountPerSecond();
        }
        calculateHappinesIncrease();
        calculateMoneyIncrease();
        calculateAndSetSuscripersToBeGained();
        //Log.d(TAG, "calculateEverything: Now ended");
    }

    public void calculateAndSetSuscripersToBeGained(){
        double leftOverMoney = (mThisCycleMoneyEarnings-(mCurrentSuscripers*10000d));
        if(Double.compare(leftOverMoney,0f)>0){
            mSuscripersToBeGained=leftOverMoney/10000d;
        }
    }
}
