package CustomControls;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by kartikeya on 22/09/2018.
 */

public class ButtonLatoRegular extends Button {

    public ButtonLatoRegular(Context context) {
        super(context);
        setTypeface(context);
    }

    public ButtonLatoRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(context);
    }

    public ButtonLatoRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(context);
    }

    private void setTypeface(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), "Font/Lato-Bold.ttf"));

    }
}
