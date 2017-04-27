package purrfect.evolution;


/**
 * Created by jyhy on 4/10/17.
 */

class CatData {
    int mpaa;
    int mkroppa;
    int mhanta;

    public int getMpaa() {
        if (mpaa!=0){
            return mpaa;
        }else{
            return mpaa = R.drawable.porrokissa_paa;
        }
    }

    public void setMpaa(int mpaa) {
        this.mpaa = mpaa;
    }

    public int getMkroppa() {
        if (mkroppa!=0){
            return mkroppa;
        }else{
            return mkroppa = R.drawable.porrokissa_kroppa;
        }
    }

    public void setMkroppa(int mkroppa) {
        this.mkroppa = mkroppa;
    }

    public int getMhanta() {
        if (mhanta!=0){
            return mhanta;
        }else{
            return mhanta = R.drawable.porrokissa_hanta;
        }
    }

    public void setMhanta(int mhanta) {
        this.mhanta = mhanta;
    }


    public CatData() {

    }


}