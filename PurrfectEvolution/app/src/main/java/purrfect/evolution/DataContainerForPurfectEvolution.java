package purrfect.evolution;

/**
 * Created by jyhy on 3/27/17.
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

    static double mLifeTimeHappinesEarnings=0;
    static double mSessionHappinesEarnings=0;
    static double mThisCycleHappinesEarnings=0;

    private DataContainerForPurfectEvolution()
    {

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
        mLifeTimeHappinesEarnings+=happines;
        mSessionHappinesEarnings+=happines;
        mThisCycleHappinesEarnings+=happines;
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
        mThisCycleHappinesEarnings=0;
        mThisCycleMoneyEarnings=0;
    }

    public static double getmCurrentMoney() {
        return mCurrentMoney;
    }

    public static double getmCurrentHappines() {
        return mCurrentHappines;
    }
}
