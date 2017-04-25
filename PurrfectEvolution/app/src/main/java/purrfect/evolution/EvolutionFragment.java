package purrfect.evolution;


import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;


import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class EvolutionFragment extends Fragment {

    ImageView catImage;
    PopupWindow popupWindow;
    PopupWindow tailPopUpWindow;
    PopupWindow bodyPopUpWindow;
    PopupWindow headPopUpWindow;
    CatData catData;

    public EvolutionFragment(){
        catData = MainActivity.getCatData();
    }

    public static EvolutionFragment newInstance() {
        EvolutionFragment fragment = new EvolutionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_evolution , container, false);

        //catImage = (ImageView) rootView.findViewById(R.id.imageView4);

        //catImage.setImageDrawable(catData.getCat());

        //updateCat();
        rootView.findViewById(R.id.imageView_hanta).setForeground(getActivity().getDrawable(catData.getMhanta()));
        rootView.findViewById(R.id.imageViewKroppa).setForeground(getActivity().getDrawable(catData.getMkroppa()));
        rootView.findViewById(R.id.imageViewPaa).setForeground(getActivity().getDrawable(catData.getMpaa()));
        //catImage.setImageDrawable(null);
        catData.catUpdate();
        //catImage.setImageDrawable(catData.getCat());

        /*catImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Pop-up Menu

        }});*/
        return rootView;
    }

    public void updateCat() {
        catData.catUpdate();



    }

    public void onMainMenuClick(View view, Context context) {
        switch (view.getId()) {
            case (R.id.hantavalinta):
                popupWindow.dismiss();
                //showTailMenuPopUp(view, context);
                break;
            case (R.id.bodyvalinta):
                popupWindow.dismiss();
                //showBodyMenuPopUp(view, context);
                break;
            case (R.id.paavalinta):
                popupWindow.dismiss();
                //showHeadMenuPopUp(view, context);
                break;
        }
    }

    public void onTailMenuClick(View view, Context context) {
        switch (view.getId()) {
            case (R.id.hanta1):
                tailPopUpWindow.dismiss();
                break;
            case (R.id.hanta2):
                tailPopUpWindow.dismiss();
                break;
            case (R.id.hanta3):
                tailPopUpWindow.dismiss();
                break;
            case (R.id.hanta4):
                tailPopUpWindow.dismiss();
                break;
            case (R.id.hanta5):
                tailPopUpWindow.dismiss();
                break;
            case (R.id.hanta6):
                tailPopUpWindow.dismiss();
                break;
            case (R.id.hanta7):
                tailPopUpWindow.dismiss();
                break;
            case (R.id.hanta8):
                tailPopUpWindow.dismiss();
                break;
            case (R.id.hanta9):
                tailPopUpWindow.dismiss();
                break;
        }
    }

    public void onBodyMenuClick(View view, Context context) {
        switch (view.getId()) {
            case (R.id.body1):
                bodyPopUpWindow.dismiss();
                break;
            case (R.id.body2):
                bodyPopUpWindow.dismiss();
                break;
            case (R.id.body3):
                bodyPopUpWindow.dismiss();
                break;
            case (R.id.body4):
                bodyPopUpWindow.dismiss();
                break;
            case (R.id.body5):
                bodyPopUpWindow.dismiss();
                break;
            case (R.id.body6):
                bodyPopUpWindow.dismiss();
                break;
            case (R.id.body7):
                bodyPopUpWindow.dismiss();
                break;
            case (R.id.body8):
                bodyPopUpWindow.dismiss();
                break;
            case (R.id.body9):
                bodyPopUpWindow.dismiss();
                break;
        }
    }

    public void onHeadMenuClick(View view, Context context) {
        switch (view.getId()) {
            case (R.id.head1):
                headPopUpWindow.dismiss();
                //catImage.setImageDrawable(catData.getCat());
                break;
            case (R.id.head2):
                headPopUpWindow.dismiss();
                break;
            case (R.id.head3):
                headPopUpWindow.dismiss();
                break;
            case (R.id.head4):
                headPopUpWindow.dismiss();
                break;
            case (R.id.head5):
                headPopUpWindow.dismiss();
                break;
            case (R.id.head6):
                headPopUpWindow.dismiss();
                break;
            case (R.id.head7):
                headPopUpWindow.dismiss();
                break;
            case (R.id.head8):
                headPopUpWindow.dismiss();
                break;
            case (R.id.head9):
                headPopUpWindow.dismiss();
                break;
        }
    }

    public void showMainMenuPopUp(final View view, final Context mCtx ) {

        LayoutInflater layoutInflater = (LayoutInflater) mCtx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.mainmenu_popup, null);
        popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setFocusable(true);
        popupWindow.update();
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);

        popupWindow.showAtLocation(view, 1, 0, 0);
        //popupWindow.showAsDropDown(view, 0, 0);
    }

    public void showTailMenuPopUp(final View view, final Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.tailmenu_popup, null);
        tailPopUpWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, true);
        tailPopUpWindow.setFocusable(true);
        tailPopUpWindow.update();
        tailPopUpWindow.setBackgroundDrawable(new BitmapDrawable());
        tailPopUpWindow.setOutsideTouchable(true);

        tailPopUpWindow.showAtLocation(view, 1, 0, 0);
    }

    public void showBodyMenuPopUp(final View view, final Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.bodymenu_popup, null);
        bodyPopUpWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, true);
        bodyPopUpWindow.setFocusable(true);
        bodyPopUpWindow.update();
        bodyPopUpWindow.setBackgroundDrawable(new BitmapDrawable());
        bodyPopUpWindow.setOutsideTouchable(true);

        bodyPopUpWindow.showAtLocation(view, 1, 0, 0);
    }

    public void showHeadMenuPopUp(final View view, final Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.headmenu_popup, null);
        headPopUpWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, true);
        headPopUpWindow.setFocusable(true);
        headPopUpWindow.update();
        headPopUpWindow.setBackgroundDrawable(new BitmapDrawable());
        headPopUpWindow.setOutsideTouchable(true);

        headPopUpWindow.showAtLocation(view, 1, 0, 0);
    }

}
