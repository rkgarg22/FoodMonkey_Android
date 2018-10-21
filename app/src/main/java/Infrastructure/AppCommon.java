package Infrastructure;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Patterns;
import android.view.WindowManager;
import android.widget.ImageView;


import com.kartik.project.foodmonkey.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.MODE_PRIVATE;

public class AppCommon {
    public static AppCommon mInstance = null;
    static Context mContext;
    String pathOfFile = null;

    public static final int LOCATION_PERMISSION_REQUEST_CODE = 1100;

    public static AppCommon getInstance(Context _Context) {
        if (mInstance == null) {
            mInstance = new AppCommon();
        }
        mContext = _Context;
        return mInstance;
    }

    public boolean isUserLogIn() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, mContext.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(MYPerference.IS_USER_LOGIN, false);
    }

    public void setIsUserLogIn(boolean isUserLogIn) {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, mContext.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(MYPerference.IS_USER_LOGIN, isUserLogIn);
        mEditor.commit();
    }

    public boolean isEmailValid(String email) {
//        boolean isValid = false;
//
//        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
//        CharSequence inputStr = email;
//
//        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(inputStr);
//        if (matcher.matches()) {
//            isValid = true;
//        }
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public String getUserName() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, mContext.MODE_PRIVATE);
        return mSharedPreferences.getString(MYPerference.USER_NAME, "");
    }

    public void setUserName(String userName) {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, mContext.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(MYPerference.USER_NAME, userName);
        mEditor.commit();

    }

    public String getEmailAddress() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, mContext.MODE_PRIVATE);
        return mSharedPreferences.getString(MYPerference.EMAIL_ADDRESS, null);
    }


    public void setEmailAddress(String emailAddress) {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, mContext.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(MYPerference.EMAIL_ADDRESS, emailAddress);
        mEditor.apply();

    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public static void showDialog(Activity mactivity, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mactivity);
        builder.setTitle(title);
        builder.setNegativeButton(mContext.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    public String getRemindText() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, mContext.MODE_PRIVATE);
        return mSharedPreferences.getString(MYPerference.REMIND, "");
    }

    public void setRemindText(String remind) {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, mContext.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(MYPerference.REMIND, remind);
        mEditor.commit();

    }

    public static Bitmap getCircleBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(),
                bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(bitmap.getWidth() / 2,
                bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public String getUserId() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, mContext.MODE_PRIVATE);
        return mSharedPreferences.getString(MYPerference.USER_ID, null);
    }

    public void setUserId(String user_id) {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, mContext.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(MYPerference.USER_ID, user_id);
        mEditor.apply();
    }

    public static String getCurrentDate() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        final Calendar c = Calendar.getInstance();
        String formatted = format1.format(c.getTime());
        return formatted;
    }

    public static String getCurrentTime() {
        String time;
        int hrs, min;
        String minuteString = "";
        final Calendar c = Calendar.getInstance();
        hrs = c.get(Calendar.HOUR_OF_DAY);
        min = c.get(Calendar.MINUTE);
        if (min < 10) {
            minuteString = "0" + min;
        } else {
            minuteString = String.valueOf(min);
        }
        time = hrs + ":" + minuteString;
        return time;
    }

    public static String getDateMinusSevenDate(String date) {
        final Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = sdf.parse(date);
            c.setTime(d);
            c.add(Calendar.DATE, -7);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String formatted = sdf.format(c.getTime());
        return formatted;
    }

    public void setDeviceToken(String deviceToken) {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, mContext.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(MYPerference.DEVICE_TOKEN, deviceToken);
        mEditor.apply();
    }

    public String getDeviceToken() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, mContext.MODE_PRIVATE);
        return mSharedPreferences.getString(MYPerference.DEVICE_TOKEN, "");
    }

    public static String getUnixTime2(final String inputDateAsString, final String inputStringFormat) throws ParseException {
        DateFormat inputDateFormatter = new SimpleDateFormat(inputStringFormat, Locale.getDefault());
        final Date inputDate = inputDateFormatter.parse(inputDateAsString);
        final DateFormat getUnixDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String s = getUnixDateFormat.format(inputDate);
        return s;
    }

    public static String getNormalDate(final String inputDateAsString, final String inputStringFormat) throws ParseException {
        DateFormat inputDateFormatter = new SimpleDateFormat(inputStringFormat, Locale.getDefault());
        final Date inputDate = inputDateFormatter.parse(inputDateAsString);
        final DateFormat getUnixDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String s = getUnixDateFormat.format(inputDate);
        s = s.substring(0, 5);
        return s;
    }

    public static String getNormalTime(final String inputTime, final String inputStringFormat) throws ParseException {
        DateFormat inputDateFormatter = new SimpleDateFormat(inputStringFormat, Locale.getDefault());
        final Date inputDate = inputDateFormatter.parse(inputTime);
        final DateFormat getUnixDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String s = getUnixDateFormat.format(inputDate);
        return s;
    }

    public static String getFromMonthDateFromMonthSelected(String date) {
        final Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = sdf.parse(date);
            c.setTime(d);
            c.set(Calendar.DAY_OF_MONTH, 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String formatted = sdf1.format(c.getTime());
        return formatted;
    }

    public static String getFromMonthDate(int i) {
        int monthSelected = 0;
        Calendar c = Calendar.getInstance();
        String mDay = "01";

        String mDate;
        switch (i) {
            case 0:
                monthSelected = Calendar.JANUARY;

                break;
            case 1:
                monthSelected = Calendar.FEBRUARY;
                break;
            case 2:
                monthSelected = Calendar.MARCH;
                break;
            case 3:
                monthSelected = Calendar.APRIL;
                break;
            case 4:
                monthSelected = Calendar.MAY;
                break;
            case 5:
                monthSelected = Calendar.JUNE;
                break;
            case 6:
                monthSelected = Calendar.JULY;
                break;
            case 7:
                monthSelected = Calendar.AUGUST;
                break;
            case 8:
                monthSelected = Calendar.SEPTEMBER;
                break;
            case 9:
                monthSelected = Calendar.OCTOBER;
                break;
            case 10:
                monthSelected = Calendar.NOVEMBER;
                break;
            case 11:
                monthSelected = Calendar.DECEMBER;
                break;
        }
        mDate = c.get(Calendar.YEAR) + "-" + (monthSelected + 1) + "-" + mDay;
        return mDate;
    }

    public static String getToMonthDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            Date d = sdf.parse(date);
            c.setTime(d);
            c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String formatted = sdf.format(c.getTime());
        return formatted;
    }


    public String getSelectedDate(Calendar c) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String formatted = format1.format(c.getTime());
        return formatted;
    }

    public String getDateMinusSevenDaysDate(Calendar c) {
        c.add(Calendar.DATE, -7);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String formatted = format1.format(c.getTime());
        return formatted;
    }

    public String getBase64ImageString(Bitmap photo) {
        String imgString;
        if (photo != null) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 60, outputStream);
            byte[] profileImage = outputStream.toByteArray();
            imgString = Base64.encodeToString(profileImage, Base64.NO_WRAP);
        } else {
            imgString = "";
        }
        return imgString;
    }


    public void setVerified(boolean count) {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, mContext.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(MYPerference.isVerified, count);
        mEditor.apply();
    }


    public static Calendar getCalendarObjectAccordingToTypeSelected(int year, int Month, int Day) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();

        String date = year + "-" + Month + "-" + Day;
        Date date1 = new Date();
        try {
            date1 = format.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        c.setTime(date1);
        return c;
    }

    public static byte[] getBytes(Bitmap bitmap) {
        if (bitmap != null) {

        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        assert bitmap != null;
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        Bitmap mBitmap = null;
        if (image.length != 0) {
            mBitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        }
        return mBitmap;
    }

    public File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        return image;
    }

    public String getRealPathFromUri(Uri selectedImage) {
        String result;
        Cursor cursor = mContext.getContentResolver().query(selectedImage, null, null, null, null);
        if (cursor == null) {
            result = selectedImage.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public void setFilePath(String filePath) {
        pathOfFile = filePath;
    }

    public String getFilePath() {
        return pathOfFile;
    }

    public void setNonTouchableFlags(Activity activity) {
        if (activity != null) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    public boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    public void clearNonTouchableFlags(Activity mActivity) {
        if (mActivity != null) {
            mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    // By Kartik
    public void setUserObject(String UserObject) {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, mContext.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(MYPerference.USER_OBJECT, UserObject);
        mEditor.apply();
    }

    public String getUserObject() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, mContext.MODE_PRIVATE);
        return mSharedPreferences.getString(MYPerference.USER_OBJECT, "");
    }

    public void clearSharedPreference() {
        SharedPreferences preferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }


    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public boolean isAlphaNumeric(String serialID) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]+$");
        Matcher matcher = pattern.matcher(serialID);
        if (matcher.matches()) {
            // yay! alphanumeric!
            return true;
        } else {
            return false;
        }
    }

    public void setImageBlack(ImageView imageView) {
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        imageView.setColorFilter(filter);
    }

    public void setUserLatitude(double latitude) {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putFloat(MYPerference.USER_LATITUDE, (float) latitude);
        mEditor.apply();
    }

    public void setUserLongitude(double longitude) {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putFloat(MYPerference.USER_LONGITUDE, (float) longitude);
        mEditor.apply();
    }


    public float getUserLatitude() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getFloat(MYPerference.USER_LATITUDE, 0.0f);
    }

    public float getUserLongitude() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getFloat(MYPerference.USER_LONGITUDE, 0.0f);
    }

    public void setPostalCode(String postalCode) {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(MYPerference.USER_POSTCODE, postalCode);
        mEditor.apply();
    }

    public String getUserPostalCode() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(MYPerference.USER_POSTCODE, "");
    }

    public void setCustomerID(String customerID) {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(MYPerference.CUSTOMER_ID, customerID);
        mEditor.apply();
    }

    public String getCustomerID() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(MYPerference.CUSTOMER_ID, "");
    }

    public void setFirstName(String firstName) {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(MYPerference.FIRST_NAME, firstName);
        mEditor.apply();
    }

    public String getFirstName() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(MYPerference.FIRST_NAME, "");
    }

    public void setSurName(String surName) {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(MYPerference.SUR_NAME, surName);
        mEditor.apply();
    }

    public String getSurName() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(MYPerference.SUR_NAME, "");
    }

    public void setMiddleName(String middleName) {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(MYPerference.MIDDLE_NAME, middleName);
        mEditor.apply();
    }

    public String getMiddleName() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(MYPerference.MIDDLE_NAME, "");
    }


    public void setGender(String gender) {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(MYPerference.GENDER, gender);
        mEditor.apply();
    }

    public String getGender() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(MYPerference.GENDER, "");
    }


    public void setMobileNumber(String mobileNumber) {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(MYPerference.MOBILE_NUMBER, mobileNumber);
        mEditor.apply();
    }

    public String getMobileNumber() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(MYPerference.MOBILE_NUMBER, "");
    }

    public void setDateOfBirth(String dateOfBirth) {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(MYPerference.DATE_OF_BIRTH, dateOfBirth);
        mEditor.apply();
    }

    public String getDateOfBirth() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(MYPerference.DATE_OF_BIRTH, "");
    }

    public void setProfilePic(String profilePic) {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(MYPerference.PROFILE_PIC, profilePic);
        mEditor.apply();
    }

    public String getProfilePic() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(MYPerference.PROFILE_PIC, "");
    }

    public void setStatus(Integer status) {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putInt(MYPerference.STATUS, status);
        mEditor.apply();
    }

    public String getStatus() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(MYPerference.mPREFS_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(MYPerference.STATUS, "");
    }

    public String getFileToBase64(String filePath) {
        Bitmap bmp = null;
            ByteArrayOutputStream bos = null;
            byte[] bt = null;
            String encodeString = null;
            try{
                bmp = BitmapFactory.decodeFile(filePath);
                bos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 60, bos);
                bt = bos.toByteArray();
                encodeString = Base64.encodeToString(bt, Base64.DEFAULT);
            }catch (Exception e){
                e.printStackTrace();
            }
            return encodeString;
    }
}