package CustomControls;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by kartikeya on 22/09/2018.
 */

public class EditTextLatoItalic extends EditText {
    public EditTextLatoItalic(Context context) {
        super(context);
        setTypeface(context);
    }

    public EditTextLatoItalic(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(context);
    }

    public EditTextLatoItalic(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(context);
    }

    private void setTypeface(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), "Font/Lato-Italic.ttf"));

    }
}
