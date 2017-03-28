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
    static double mCurrentMoney=0;
    static double mCurrentHappines=0;

    static long mLifeTimeClicks=0;
    static long mSessionClicks=0;
    static long mThisCycleClicks=0;

    static double mLifeTimeMoneyEarnings=0;
    static double mSessionMoneyEarnings=0;
    static double mThisCycleMoneyEarnings=0;

    static double mLifeTimeHappinessEarnings =0;
    static double mSessionHappinessEarnings =0;
    static double mThisCycleHappinessEarnings =0;

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
                    field.setLong(c,_value);
                    Log.d(TAG, "loadDataFromPreference: Success name "+_name+" value "+_value);

                } catch (Exception exc){
                    Log.e(TAG, "loadDataFromPreference: long FAIL",exc );
                    //return false;
                }

            }else if(double.class.equals(ctb)) {
                try {
                    String _name = field.getName();
                    double _value = Double.longBitsToDouble(data.getLong(_name,0));
                    field.setDouble(c,_value);
                    Log.d(TAG, "loadDataFromPreference: Success name "+_name+" value "+_value);
                }catch (Exception exc){
                    Log.e(TAG, "loadDataFromPreference: double FAIL ",exc );
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
                    long _value= field.getLong(c);
                    editor.putLong(_name,_value);
                    Log.d(TAG,"saveDataToPreference: name: "+_name+" value"+_value);

                } catch (Exception exc){
                    Log.e(TAG, "saveDataToPreference: ",exc);
                    return false;
                }


            }else if (double.class.equals(classToBeInspected)) {
                try {
                    String _name=field.getName();
                    double _value= field.getDouble(c);
                    editor.putLong(_name,Double.doubleToRawLongBits(_value));
                    Log.d(TAG,"saveDataToPreference: name: "+_name+" value"+_value);
                } catch (Exception exc){
                    Log.e(TAG, "saveDataToPreference: ",exc);

                    return false;
                }
            }else{
                Log.d(TAG, "saveDataToPreference: Unhandled variable type");
                return false;
            }
        }
        return true;
    }

    /**
     * Increment all click variables
     * */
    public static void receivedClick(){
        mLifeTimeClicks++;
        mSessionClicks++;
        mThisCycleClicks++;

    }


    /**
     * Increments all money variables
     * @param money Amount of money to be incremented
     * */
    public static void earnedMoney(double money){
        mLifeTimeMoneyEarnings+=money;
        mSessionMoneyEarnings+=money;
        mThisCycleMoneyEarnings+=money;
        mCurrentMoney+=money;

    }


    /**
     * Incerements all happiness variables
     * @param happiness amount of happiness to added to variables
     * */
    public static void earnedHappiness(double happiness){
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
    public static boolean spentHappiness(double mSpentHappiness){
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
    public static boolean spentMoney(double mSpentMoney){
        if (Double.compare(mSpentMoney,mCurrentMoney)>=0)
        {
            mCurrentMoney-=mSpentMoney;
            return true;
        }else{
            return false;
        }
    }


    /** Resets cycle variables*/
    public static void resetCycle(){
        mThisCycleClicks=0;
        mThisCycleHappinessEarnings =0;
        mThisCycleMoneyEarnings=0;
    }

    public static double getmCurrentMoney() {
        return mCurrentMoney;
    }

    public static double getmCurrentHappines() {
        return mCurrentHappines;
    }
}
