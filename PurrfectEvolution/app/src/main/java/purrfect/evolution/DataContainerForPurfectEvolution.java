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
    public static void receivedClick(){
        mLifeTimeClicks++;
        mSessionClicks++;
        mThisCycleClicks++;

    }
    public static void earnedMoney(double money){
        mLifeTimeMoneyEarnings+=money;
        mSessionMoneyEarnings+=money;
        mThisCycleMoneyEarnings+=money;
        mCurrentMoney+=money;

    }
    public static void earnedHappines(double happines){
        mLifeTimeHappinesEarnings+=happines;
        mSessionHappinesEarnings+=happines;
        mThisCycleHappinesEarnings+=happines;
        mCurrentHappines+=happines;

    }
    public static boolean spenthappines(double spenthappines){
        if (Double.compare(spenthappines,mCurrentMoney)>=0)
        {
            mCurrentMoney-=spenthappines;
            return true;
        }else{
            return false;
        }
    }
    public static boolean spentMoney(double spentmoney){
        if (Double.compare(spentmoney,mCurrentMoney)>=0)
        {
            mCurrentMoney-=spentmoney;
            return true;
        }else{
            return false;
        }
    }
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
