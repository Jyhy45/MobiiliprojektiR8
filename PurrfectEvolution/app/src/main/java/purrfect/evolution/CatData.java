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
    InsetDrawable[] layers;
    Drawable hanta;
    Drawable kroppa;
    Drawable paa;

    //(kissahanta, kissabody, kissapaa);

    public CatData(Drawable tail, Drawable body, Drawable head) {
        layers = new InsetDrawable[3];
        hanta = tail;
        kroppa = body;
        paa = head;

        catUpdate();

        //layers[0] = new InsetDrawable(tail, 0);
        //layers[1] = new InsetDrawable(body, 0);
        //layers[2] = new InsetDrawable(head, 0);

        //catImageList = new LayerDrawable(layers);

        // Ao. komennolla voidaan vaihtaa kuvaa tietyssä sijainnissa
        //catImageList.setDrawable();
    }

    public void createCat(Drawable drawable1, Drawable drawable2, Drawable drawable3) {

        layers[0] = new InsetDrawable(drawable1, 0);
        layers[1] = new InsetDrawable(drawable2, 0);
        layers[2] = new InsetDrawable(drawable3, 0);

        catImageList = new LayerDrawable(layers);
    }

    public void catUpdate() {

        layers[0] = new InsetDrawable(hanta, 0);
        layers[1] = new InsetDrawable(kroppa, 0);
        layers[2] = new InsetDrawable(paa, 0);
        //catImageList.setDrawable(0, hanta);
        //catImageList.setDrawable(1, hanta);
        //catImageList.setDrawable(2, hanta);

        catImageList = new LayerDrawable(layers);
    }

    public LayerDrawable getCat() {
        return catImageList;
    }

    public void setCatTail(Drawable drawable1) {
        //catImageList.setDrawable(0, drawable1);
        hanta = drawable1;
    }

    public void setCatHead(Drawable drawable2) {
        //catImageList.setDrawable(1, drawable2);
        paa = drawable2;
    }

    public void setCatBody(Drawable drawable3) {
        //catImageList.setDrawable(2, drawable3);
        kroppa = drawable3;
    }
}