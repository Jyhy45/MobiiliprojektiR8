package purrfect.evolution;

import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;


import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import static android.content.ContentValues.TAG;


public class OptionsFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    RelativeLayout optionsRelativeLayout;
    int nappula = 0;
    int nappula2 = 0;
    DataContainerForPurfectEvolution mDataContainer = MainActivity.getmDataContainer();

    public OptionsFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static OptionsFragment newInstance() {
        OptionsFragment fragment = new OptionsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void onClick(View view, Context context)
    {
        Log.i(TAG, "onClick: alussa");

        switch(view.getId()) {
            case R.id.On1:
                nappula = 1;
                nappula2 =2;
                if (mDataContainer.getmSoundOnOff() == 1) {
                }
                else if (mDataContainer.getmSoundOnOff()== 0) {
                    buttonToggle(view,context,nappula,nappula2);
                    musicToggle(mDataContainer.getmSoundOnOff());
                }
                break;
            case R.id.On2:
                nappula = 3;
                nappula2= 4;
                if (mDataContainer.getmAnimationsOnOff()==1) {
                }
                else if (mDataContainer.getmAnimationsOnOff()==0) {
                    buttonToggle(view,context,nappula,nappula2);
                    animationsToggle(mDataContainer.getmAnimationsOnOff());
                }
                break;
            case R.id.Off1:
                nappula = 1;
                nappula2 = 2;
                if (mDataContainer.getmSoundOnOff() == 1) {
                    buttonToggle(view,context,nappula,nappula2);
                    musicToggle(mDataContainer.getmSoundOnOff());
                }
                else if (mDataContainer.getmSoundOnOff() == 0) {
                }
                break;
            case R.id.Off2:
                nappula = 3;
                nappula2 = 4;
                if (mDataContainer.getmAnimationsOnOff()==1 ) {
                }
                else if (mDataContainer.getmAnimationsOnOff()== 0) {
                    buttonToggle(view,context,nappula,nappula2);
                    animationsToggle(mDataContainer.getmAnimationsOnOff());
                }
                break;
        }

        Log.i(TAG, "onClick: lopussa");
    }

    public void buttonToggle(View view, Context context,int nappula, int nappula2)
    {
        Log.i(TAG, "buttonToggle: alussa");
        ImageButton imageButton;
        ImageButton imageButton2;

        switch (nappula) {
            case 1:
                imageButton = (ImageButton) optionsRelativeLayout.findViewById(R.id.On1);
                break;
            case 3:
                imageButton = (ImageButton)optionsRelativeLayout.findViewById(R.id.On2);
                break;
            default:
                imageButton = (ImageButton)view;
                break;
        }
        switch (nappula2) {
            case 2:
                imageButton2 = (ImageButton)optionsRelativeLayout.findViewById(R.id.Off1);
                break;
            case 4:
                imageButton2 = (ImageButton)optionsRelativeLayout.findViewById(R.id.Off2);
                break;
            default:
                imageButton2 = (ImageButton)view;
                break;
        }
        Log.i(TAG, "buttonToggle: valissa");
        if(imageButton.getDrawable() == getResources().getDrawable(R.drawable.on_vihr))
        {
            imageButton.setImageDrawable(getResources().getDrawable(R.drawable.on_harm));
            imageButton2.setImageDrawable(getResources().getDrawable(R.drawable.off_pun));

        }
        else if(imageButton.getDrawable()== getResources().getDrawable((R.drawable.on_harm)))
        {
            imageButton.setImageDrawable(getResources().getDrawable(R.drawable.on_vihr));
            imageButton2.setImageDrawable(getResources().getDrawable(R.drawable.off_harm));
        }
        Log.i(TAG, "buttonToggle: lopussa");
    }

    void musicToggle(int state)
    {
        switch (state) {
            case 1:
                mDataContainer.setmSoundOnOff(0);
                break;
            case 0:
                mDataContainer.setmSoundOnOff(1);
                break;
        }
    }

    void animationsToggle(int state)
    {
        switch (state) {
            case 1:
                mDataContainer.setmAnimationsOnOff(0);
                break;
            case 0:
                mDataContainer.setmAnimationsOnOff(1);
                break;
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_options , container, false);
        optionsRelativeLayout = (RelativeLayout) rootView;
        return rootView;
    }

}
