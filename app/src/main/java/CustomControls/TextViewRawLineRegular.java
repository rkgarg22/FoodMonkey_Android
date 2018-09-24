package CustomControls;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by kartikeya on 22/09/2018.
 */

public class TextViewRawLineRegular extends TextView {
    public TextViewRawLineRegular(Context context) {
        super(context);
        setTypeface(context);
    }

    public TextViewRawLineRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(context);
    }

    public TextViewRawLineRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(context);
    }
    private void setTypeface(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), "Font/Rawline-Regular.ttf"));
    }
}
