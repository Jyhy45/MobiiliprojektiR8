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

    private DataContainerForPurfectEvolution()
    {

    }
    public boolean loadDataFromPreference(SharedPreferences data){
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
                    return false;
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

            }

        }


        return true;
    }
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
     * Encerements all happines variables
     * @param happines amount of happpines to added to variables
     * */
    public static void earnedHappines(double happines){
        mLifeTimeHappinessEarnings +=happines;
        mSessionHappinessEarnings +=happines;
        mThisCycleHappinessEarnings +=happines;
        mCurrentHappines+=happines;

    }


    /**
     * Spends happines if there is enaugh happines to spend
     * @param mSpentHappines amount of happines to be spent will only spend happines
     *                       if there is enough happines
     *
     * @return Return true if happines was used; return false if happines was not spent
     * */
    public static boolean spentHappines(double mSpentHappines){
        if (Double.compare(mSpentHappines,mCurrentMoney)>=0)
        {
            mCurrentMoney-=mSpentHappines;
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
