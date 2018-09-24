package CustomControls;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

public class GIFView extends View {
    public Movie mMovie;
    public long movieStart;

    public GIFView(Context context) {
        super(context);
        initializeView();
    }

    public GIFView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView();
    }


    public GIFView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView();
    }


    private void initializeView() {

        InputStream is = null;
        try {
            is = getContext().getAssets().open("hourglass.gif");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMovie = Movie.decodeStream(is);
    }


    @Override

    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);
        super.onDraw(canvas);
        long now = android.os.SystemClock.uptimeMillis();
        if (movieStart == 0) {
            movieStart = now;
        }

        if (mMovie != null) {
            int relTime = (int) ((now - movieStart) % 3000);
            mMovie.setTime(relTime);
            mMovie.draw(canvas, ((float)(getWidth()*0.5) - (float)(mMovie.width()*0.5)), getHeight() - mMovie.height());
            this.invalidate();
        }
    }

    private int gifId;

    public void setGIFResource(int resId) {
        this.gifId = resId;
        initializeView();
    }

    public int getGIFResource() {
        return this.gifId;
    }

}


