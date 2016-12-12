package kbl.chelbi.clavier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;

import java.util.Iterator;


public class ClavierBerberView extends KeyboardView {

    private Paint paint;
    private Typeface typeface ;

    public ClavierBerberView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            paint = new Paint();
            typeface = Typeface.createFromAsset(getContext().getAssets(), "tf.ttf");
            paint.setTypeface(typeface);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        Iterator it = getKeyboard().getKeys().iterator();

        while (it.hasNext()){
            Keyboard.Key key = (Keyboard.Key) it.next();
            paint.setTextAlign(Paint.Align.CENTER);

            paint.setTextSize(key.height/2);
            int i = Color.parseColor("#ffffff");
            this.paint.setColor(i);

            if (key.label != null) {
                canvas.drawText(key.label.toString(), key.x + key.width / 2, key.y + key.height / 1.5F, this.paint);
            }
            else {
                key.icon.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                key.icon.draw(canvas);
            }
        }
    }


}
