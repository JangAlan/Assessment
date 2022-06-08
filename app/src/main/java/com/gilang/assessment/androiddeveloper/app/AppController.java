package com.gilang.assessment.androiddeveloper.app;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import com.google.android.material.snackbar.Snackbar;

import androidx.multidex.BuildConfig;
import androidx.multidex.MultiDex;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.view.ContextThemeWrapper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.gilang.assessment.androiddeveloper.R;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AppController extends Application {
    private static AppController mInstance;
    private AppSessionManager sessionManager;
    private static final String TAG = "CiwaruFCMIDService";
    private int REQUEST_WRITE_STORAGE_REQUEST_CODE = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        sessionManager = new AppSessionManager(getApplicationContext());

        Boolean sDir = createDirIfNotExists();
        Log.d("Directory",sDir.toString());

    }

    //get FCM ID
    /*public void getFCMID(Activity activity){
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(activity,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                AppConfig.IMEI = instanceIdResult.getToken();
                Log.d("firebaseToken",AppConfig.IMEI);

            }
        });
    }*/

    //permission write
    public void requestAppPermissions(Activity activity) {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }

        if (hasReadPermissions() && hasWritePermissions() && hasCameraPermissions()) {
            return;
        }

        ActivityCompat.requestPermissions(activity,
                new String[] {
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                }, REQUEST_WRITE_STORAGE_REQUEST_CODE); // your request code
    }

    public boolean hasReadPermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    public boolean hasWritePermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    public boolean hasCameraPermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);
    }


    public static Context getAppContext() {
        return mInstance;
    }

    public AppSessionManager getSessionManager() {
        return sessionManager;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static String getStringData(Context ctx, String sData){
        String sResult = "";
        sResult = AppConfig.pref.getString(sData, AppConfig.EMPTY_STRING);
        return  sResult;
    }

    /*public void displayImage(Context context,String uri, ImageView imageView) {
        Glide.with(context)
                .load(uri)
                .into(imageView);
    }

    public void displayImageDrawable(Context context,int placeHolder, ImageView imageView) {
        Glide.with(context)
                .load("")
                .into(imageView);
    }

    public void displayImagePicasso(Context context, String uri, ImageView imageView) {
        Picasso picasso = Picasso.with(context);
        picasso.setIndicatorsEnabled(false);
        picasso.load(uri)
                .into(imageView);
    }*/

    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++){
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                //hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getDateTime() {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        return dateFormat.format(date) + getTime();
    }

    public String getDateTimeDDMMYYYY() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        return dateFormat.format(date) + " " + getTimeFormat();
    }

    public String getDateTime_YYYYMMDD() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date) + " " + getTimeFormat();
    }

    public String getDateTime_DDMMYYYY() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        Date date = new Date();

        return dateFormat.format(date);
    }

    public String getDateTimeDDMMYYYY_Min30() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        //AppConfig.REQID = dateFormat.format(date);

        Calendar c = Calendar.getInstance();

        c.add(Calendar.DATE, -30);

        return dateFormat.format(c.getTime());
    }

    public String getDateTimeDDMMYYYY_Min180() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        //AppConfig.REQID = dateFormat.format(date);

        Calendar c = Calendar.getInstance();

        c.add(Calendar.DATE, -180);

        return dateFormat.format(c.getTime());
    }

    public String getDateTimeDDMMYYYY_30() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        //AppConfig.REQID = dateFormat.format(date);

        Calendar c = Calendar.getInstance();

        c.add(Calendar.DATE, 30);

        return dateFormat.format(c.getTime());
    }

    public String getDateNTime() {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return  dateFormat.format(date) + " " + getTime();
    }

    public String getDate() {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return  dateFormat.format(date);
    }

    public String getTime() {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();

        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        //12 hour format
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        String sHour = String.valueOf(hour);
        String sMinute= String.valueOf(minute);
        String sSecond = String.valueOf(second);

        if (sHour.length() < 2) sHour = "0" + sHour;
        if (sMinute.length() < 2) sMinute = "0" + sMinute;
        if (sSecond.length() < 2) sSecond = "0" + sSecond;

        return sHour + sMinute + sSecond;
    }

    public String getTimeFormat() {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();

        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        //12 hour format
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        String sHour = String.valueOf(hour);
        String sMinute= String.valueOf(minute);
        String sSecond = String.valueOf(second);

        if (sHour.length() < 2) sHour = "0" + sHour;
        if (sMinute.length() < 2) sMinute = "0" + sMinute;
        if (sSecond.length() < 2) sSecond = "0" + sSecond;

        return sHour + ":" +  sMinute + ":" + sSecond;
    }


    public String getSHA1(String input)throws NoSuchAlgorithmException{

        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public static String getSHA256(String input) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public String getSignaturePost(String sCommand, String sUserId)throws NoSuchAlgorithmException{
        //AppConfig.REQID = getDateTime();
        String date = getDateTime();

        String sResult = getSHA256(sCommand + sUserId + AppConfig.SIGN + date + " POST");//AppConfig.REQID + "POST");

        return sResult;
    }

    public String getSignaturePut(String sCommand, String sUserId, String sPass)throws NoSuchAlgorithmException{
        //AppConfig.REQID = getDateTime();
        String date = getDateTime();

        String sPassword = getSHA1(sPass);
        Log.d("SHA", sPassword);
        String sResult = getSHA256(sPassword + sCommand + sUserId + AppConfig.SIGN + date + "PUT");//AppConfig.REQID + "PUT");

        return sResult;

    }

    public String getHashPass(String sUserId, String sPass) throws  NoSuchAlgorithmException{
        String sPassword = getSHA1(getSHA1(sPass) + getSHA1(sUserId) + getSHA1("m4yoRa@)!&"));

        return sPassword;
    }

    public String getHashIdMD(String sUserId)throws NoSuchAlgorithmException{
        //AppConfig.REQID = getDateTime();
        String date = getDateTime();

        String sResult = md5(getDateTime() + sUserId +  AppConfig.SIGN);

        return sResult;
    }

    public String getVersionName(){

        String versionName = BuildConfig.VERSION_NAME;
        return versionName;

    }

    /*public void CustomeDialog(Context context, String sTextIsi){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        TextView txtIsi = (TextView)dialog.findViewById(R.id.text_isi);
        TextView txtDismis = (TextView)dialog.findViewById(R.id.text_dismiss);

        txtIsi.setText(sTextIsi);
        txtDismis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }*/

    public void errorDialog(Context context, String msg){
        @SuppressLint("RestrictedApi") AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(context, com.google.android.material.R.style.Animation_AppCompat_Dialog));
        dialog.setMessage(msg)
                .setCancelable(false)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                }).show();
    }

    public void showForceUpdateDialog(final Context context){
        String appname = context.getResources().getString(R.string.app_name);
        @SuppressLint("RestrictedApi") AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(context, com.google.android.material.R.style.Animation_AppCompat_Dialog));

        alertDialogBuilder.setTitle("Update Available");
        alertDialogBuilder.setMessage("Versi terbaru " + appname + " tersedia di Play Store. Harap segera update versi terbaru " + appname + " anda.");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                try{
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName()));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }catch (android.content.ActivityNotFoundException anfe){
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName()));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }

                dialog.cancel();
            }
        });
        alertDialogBuilder.show();
    }

    public String toCurrencyUS(double dValue) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        String sCur = nf.format(dValue);
        sCur = sCur.replace("$", "");
        sCur = sCur.replace(".00", "");
        return sCur;
    }

    public String toCurrencyIDR(double dValue) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        String sCur = nf.format(dValue);
        sCur = sCur.replace("$", "Rp ");
        sCur = sCur.replace(".00", "");
        sCur = sCur.replace(",", ".");
        return sCur;
    }

    public String toNumberFormatIDR(double dValue) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        String sCur = nf.format(dValue);
        sCur = sCur.replace("$", "");
        sCur = sCur.replace(".00", "");
        sCur = sCur.replace(",", ".");
        return sCur;
    }

    public String getFileName(String sPath){
        String sResult;
        sResult = sPath.substring(sPath.lastIndexOf('/') + 1);
        return sResult;
    }

    public String getFileNameDoc(String sPath){
        String sResult;
        sResult = sPath.substring(sPath.lastIndexOf('=') + 1);
        sResult = sResult + ".pdf";
        return sResult;
    }

    public String getFileNameAttach(String sPath){
        String sResult;
        sPath = sPath.replace("<a>","");
        sResult = sPath.substring(sPath.lastIndexOf('>') + 1);
        return sResult;
    }

    public String getFileSize(double lengthFile){
        String sResult;
        DecimalFormat precision = new DecimalFormat("0.00");
        double dFileSize = lengthFile / 1024;
        sResult = "(" + precision.format(dFileSize) + " kb)";
        return sResult;
    }

    public String getFileExtension(String sPath){
        String sResult;
        sResult = sPath.substring(sPath.lastIndexOf(".") + 1);
        return sResult;
    }

    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month-1];
    }

    public static boolean createDirIfNotExists() {
        boolean ret = true;

        File file = new File(AppConfig.STORAGE_CARD + "/Download/");
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("CreateFolder : ", "Problem creating folder");
                ret = false;
            }
        }
        return ret;
    }

    public String getBetweenStrings(String text, String textFrom, String textTo) {
        String result = "";
        result = text.substring(text.indexOf(textFrom) + textFrom.length(),
                text.length());
        result = result.substring(0, result.indexOf(textTo));
        return result;
    }

    public String getBetweenStringsFrom(String text, String textFrom) {
        String result = "";
        result = text.substring(text.indexOf(textFrom) + textFrom.length(),
                text.length());
        //result = result.substring(0, result.indexOf(textTo));
        return result;
    }

    public void SnackbarMessage(View view, String msg, int duration){
        final Snackbar snackbar = Snackbar.make(view, msg, duration);
        snackbar.setAction("Tutup", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.setActionTextColor(Color.CYAN);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.DKGRAY);
        TextView textView = (TextView) snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextSize(12);
        textView.setTextColor(Color.WHITE);
        textView.setMaxLines(5);
        snackbar.show();
    }

}
