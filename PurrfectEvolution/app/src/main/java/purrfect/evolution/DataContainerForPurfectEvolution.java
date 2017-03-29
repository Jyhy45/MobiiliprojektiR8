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

    //Variables for buildings for saving purposes
    int mTypeBuilding1 = 0;
    double mLevelBuilding1 = 0;

    int mTypeBuilding2 = 0;
    double mLevelBuilding2 = 0;

    int mTypeBuilding3 = 0;
    double mLevelBuilding3 = 0;

    int mTypeBuilding4 = 0;
    double mLevelBuilding4 = 0;

    int mTypeBuilding5 = 0;
    double mLevelBuilding5 = 0;

    int mTypeBuilding6 = 0;
    double mLevelBuilding6 = 0;

    int mTypeBuilding7 = 0;
    double mLevelBuilding7 = 0;

    int mTypeBuilding8 = 0;
    double mLevelBuilding8 = 0;

    int mTypeBuilding9 = 0;
    double mLevelBuilding9 = 0;


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
        if (Double.compare(mSpentHappiness,mCurrentMoney)>=0)
        {
            mCurrentMoney-=mSpentHappiness;
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
        if (Double.compare(mSpentMoney,mCurrentMoney)>=0)
        {
            mCurrentMoney-=mSpentMoney;
            return true;
        }else{
            return false;
        }
    }


    /** Resets cycle variables*/
    public  void resetCycle(){
        mThisCycleClicks=0;
        mThisCycleHappinessEarnings =0;
        mThisCycleMoneyEarnings=0;
    }

    public void resetSoft(){
        //TODO:reset most variables and gain suscripers
    }
    public void resetHard(){
        //TODO:reset all variables to their default values

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

}
