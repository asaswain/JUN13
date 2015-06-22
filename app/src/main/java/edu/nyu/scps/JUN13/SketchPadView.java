package edu.nyu.scps.JUN13;

/**
 * Created by swaina on 6/21/15.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

public class SketchPadView extends View {
    private PointF point = new PointF();	//holds 2 floats
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public SketchPadView(Context context) {
        super(context);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        return true;	//do nothing

                    case MotionEvent.ACTION_UP:
                        return true;	//do nothing

                    case MotionEvent.ACTION_MOVE:
                        //Put finger's x, y into point.
                        point.set(event.getX(), event.getY());
                        invalidate();	//call onDraw method of TouchView
                        return true;	//do nothing else

                    default:
                        return false;
                }
            }
        });
    }

    public void setPaintColor(int paintColor) {
        paint.setColor(paintColor);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int width = getWidth();
        final int height = getHeight();
        float radius = .1f * Math.min(width, height);

        canvas.drawColor(Color.WHITE);	//background

        StarDrawable redStar = new StarDrawable(point.x, point.y, radius, paint);
        redStar.draw(canvas);
    }
}
