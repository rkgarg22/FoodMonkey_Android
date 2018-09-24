package CustomControls;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


public class TextViewIconStyle extends TextView {

    public TextViewIconStyle(Context context) {
            super(context);
            setTypeface(context);
        }

        public TextViewIconStyle(Context context, AttributeSet attrs) {
            super(context, attrs);
            setTypeface(context);
        }

        public TextViewIconStyle(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            setTypeface(context);
        }
        private void setTypeface(Context context) {
            setTypeface(Typeface.createFromAsset(context.getAssets(), "Font/fontawesome-webfont.ttf"));
        }

    }


