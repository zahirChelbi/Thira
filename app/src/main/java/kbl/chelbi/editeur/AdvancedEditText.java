package kbl.chelbi.editeur;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.Scroller;
import kbl.chelbi.thira.R;


public class AdvancedEditText extends EditText {

    private static boolean SHOW_LINE_NUMBERS = true;
    private static int TEXT_SIZE = 13;
    private Rect mDrawingRect;
    private int mHighlightStart;
    private int mHighlightedLine;
    private Rect mLineBounds;
    private int mLinePadding;
    private float mScale;
    private Point sMaxSize;
    private Paint sNumPaint;
    private int sPadding;
    private Paint sPaintHighlight;
    private Scroller sScroller;



    public AdvancedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        Log.i("AdvancedEdit","constructeur");
        this.sNumPaint = new Paint();
        this.sNumPaint.setTypeface(Typeface.MONOSPACE);
        this.sNumPaint.setAntiAlias(true);
        this.sPaintHighlight = new Paint();
        this.mScale = context.getResources().getDisplayMetrics().density;
        this.sPadding = ((int)(6.0F * this.mScale));
        this.mHighlightStart = -1;
        this.mHighlightedLine = -1;
        this.mDrawingRect = new Rect();
        this.mLineBounds = new Rect();

        if (isInEditMode())    return;

        setTypeface(getTypeface(getContext()));

        setHorizontallyScrolling(true);

        setBackgroundResource(R.drawable.textfield_white);
        setTextColor(Color.BLACK);
        sPaintHighlight.setColor(Color.BLACK);
        sNumPaint.setColor(Color.GRAY);
        this.sPaintHighlight.setAlpha(48);

        //text size
        setTextSize(TEXT_SIZE);
        this.sNumPaint.setTextSize(0.85F * (TEXT_SIZE * this.mScale));

        //refresh view
        postInvalidate();
        refreshDrawableState();

        this.sScroller = new Scroller(getContext());
        this.sMaxSize = new Point();
        this.mLinePadding = this.sPadding;
        int i = getLineCount();
        if (SHOW_LINE_NUMBERS) {
            this.mLinePadding = ((int)(1.0D + Math.floor(Math.log10(i))));
            this.mLinePadding = ((int)(this.mLinePadding * this.sNumPaint.getTextSize() + this.sPadding + 0.5D * (TEXT_SIZE * this.mScale)));
            setPadding(this.mLinePadding, this.sPadding, this.sPadding, this.sPadding);
            return;
        }
        setPadding(this.sPadding, this.sPadding, this.sPadding, this.sPadding);
    }


    @Override
    public void onDraw(Canvas canvas) {
        int count, lineX, baseline;
        count = getLineCount();
        if (SHOW_LINE_NUMBERS) {
            int padding = (int)((int)(1 + Math.floor(Math.log10(count))) * this.sNumPaint.getTextSize() + this.sPadding + 0.5 * (TEXT_SIZE * this.mScale));
            if (mLinePadding != padding) {
                mLinePadding = padding;
                setPadding(mLinePadding, sPadding, sPadding, sPadding);
            }
        }
        getDrawingRect(this.mDrawingRect);
        computeLineHighlight();

        lineX = (int)(this.mDrawingRect.left + this.mLinePadding - 0.5D * (TEXT_SIZE * this.mScale));
        int min = 0;
        int max = count;
        getLineBounds(0, this.mLineBounds);
        int startBottom = mLineBounds.bottom;
        int startTop = mLineBounds.top;
        getLineBounds(count - 1, mLineBounds);
        int endBottom = mLineBounds.bottom;
        int endTop = mLineBounds.top;

        if (count > 1 && endBottom > startBottom && endTop > startTop) {
            min = Math.max(min, ((mDrawingRect.top - startBottom) * (count - 1)) / (endBottom - startBottom));
            max = Math.min(max, ((mDrawingRect.bottom - startTop) * (count - 1)) / (endTop - startTop) + 1);
        }
        for (int i = min; i < max; i++) {
            baseline = getLineBounds(i, mLineBounds);
            if ((sMaxSize != null) && (sMaxSize.x < mLineBounds.right)) {
                sMaxSize.x = mLineBounds.right;
            }
            if (i == mHighlightedLine) {
                canvas.drawRect(mLineBounds, sPaintHighlight);
            }
            if (SHOW_LINE_NUMBERS) {
                canvas.drawText("" + (i + 1), mDrawingRect.left + sPadding, baseline, sNumPaint);
                canvas.drawLine(lineX, mDrawingRect.top, lineX,mDrawingRect.bottom, sNumPaint);
            }
        }
        getLineBounds(count - 1, mLineBounds);
        if (sMaxSize != null) {
            sMaxSize.y = mLineBounds.bottom;
            sMaxSize.x = Math.max(sMaxSize.x + sPadding - mDrawingRect.width(), 0);
            sMaxSize.y = Math.max(sMaxSize.y + sPadding - mDrawingRect.height(), 0);
        }
        super.onDraw(canvas);
    }


    protected void computeLineHighlight() {
        int i, line, selStart;
        String text;

        if (!isEnabled()) {
            mHighlightedLine = -1;
            return;
        }

        selStart = getSelectionStart();
        if (mHighlightStart != selStart) {
            text = getText().toString();

            line = i = 0;
            while (i < selStart) {
                i = text.indexOf("\n", i);
                if (i < 0) {
                    break;
                }
                if (i < selStart) {
                    ++line;
                }
                ++i;
            }

            mHighlightedLine = line;
        }
    }

    public static Typeface getTypeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "tf.ttf");
    }




}
