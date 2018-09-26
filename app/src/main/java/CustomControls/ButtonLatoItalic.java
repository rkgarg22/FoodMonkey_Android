package CustomControls;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by kartikeya on 22/09/2018.
 */

public class ButtonLatoItalic extends Button {

    public ButtonLatoItalic(Context context) {
        super(context);
        setTypeface(context);
    }

    public ButtonLatoItalic(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(context);
    }

    public ButtonLatoItalic(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(context);
    }

    private void setTypeface(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), "Font/Lato-Bold.ttf"));

    }
}