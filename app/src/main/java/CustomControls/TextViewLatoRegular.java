package CustomControls;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewLatoRegular extends TextView{
    public TextViewLatoRegular(Context context) {
        super(context);
        setTypeface(context);
    }

    public TextViewLatoRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(context);
    }

    public TextViewLatoRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(context);
    }
    private void setTypeface(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), "Font/Lato-Regular.ttf"));

    }

}
