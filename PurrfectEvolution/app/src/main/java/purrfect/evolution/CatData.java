package purrfect.evolution;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by jyhy on 4/10/17.
 */

class CatData {

    LayerDrawable catImageList;

    public CatData(Drawable d1, Drawable d2, Drawable d3) {
        InsetDrawable[] layers = new InsetDrawable[3];
        //Resources resources = context.getResources();

        /*Drawable drawable = resources.getDrawable(R.drawable.kissa_hanta);
        layers[0] = new InsetDrawable(drawable, 0);
        Drawable drawable1 = resources.getDrawable(R.drawable.kissa_paa);
        layers[1] = new InsetDrawable(drawable1, 0);
        Drawable drawable2 = resources.getDrawable(R.drawable.kissa_vartalo);
        layers[2] = new InsetDrawable(drawable2, 0);*/

        layers[0] = new InsetDrawable(d1, 0);
        layers[1] = new InsetDrawable(d2, 0);
        layers[2] = new InsetDrawable(d3, 0);

        catImageList = new LayerDrawable(layers);

        // Ao. komennolla voidaan vaihtaa kuvaa tietyssä sijainnissa
        //catImageList.setDrawable();
    }

    public LayerDrawable getCat() {
        return catImageList;
    }
}
