package CustomControls;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


public class TextViewLatoBold extends TextView {
    public TextViewLatoBold(Context context) {
        super(context);
        setTypeface(context);
    }

    public TextViewLatoBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(context);
    }

    public TextViewLatoBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(context);
    }
    private void setTypeface(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), "Font/Lato-Bold.ttf"));

    }
}
