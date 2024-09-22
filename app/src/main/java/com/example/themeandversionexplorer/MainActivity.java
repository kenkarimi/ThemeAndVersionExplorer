package com.example.themeandversionexplorer;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/*Difference between Action Bar & Toolbar:
 * ACTION BAR: The element present at the top of the activity screen. It is a salient feature of an android application that has a consistent presence over ALL its activities.
 * It provides a visual structure to the app and contains some of the frequently used elements for the users. All applications that use the default theme provided by the Android(Theme.MaterialComponents.DayNight.DarkActionBar),
 * contains an ActionBar by default. However, developers can customize it in several ways depending upon their needs. Components that can be included in the ActionBar are:
 * App Icon, Navigation drawer, Title and Subtitle, Action button, Action overflow menu(three dots)
 * TOOLBAR: Basically the advanced successor of the ActionBar. It is much more flexible and customizable in terms of appearance and functionality.
 * Unlike ActionBar, its position is not hardcoded i.e., not at the top of an activity. Developers can place it anywhere in the activity according to the need just like any other View in android.
 * Toolbar uses material design theme features of Android and thus provides it backward compatibility up to API 7(Android 2.1). Features supported by the Toolbar are much more focused and customizable than the ActionBar.
 * Following are the components that can be added to a Toolbar: App Icon, Navigation drawer, Title and Subtitle, ActionMenu items, Multiple custom views such as TextView, ImageView, etc.
 * More: https://www.geeksforgeeks.org/difference-between-actionbar-and-toolbar-in-android/
 * */

/*
* STATUS BAR: The status bar(or notification bar) in Android is an interface element located at the top of the screen. It displays battery information, device time, notification icons, and other system status details.
* */

public class MainActivity extends AppCompatActivity {

    private TextInputLayout tilFullname, tilEmail;
    private TextInputEditText tietFullname, tietEmail;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain);
        tilFullname = (TextInputLayout) findViewById(R.id.tilFullname);
        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        tietFullname = (TextInputEditText) findViewById(R.id.tietFullname);
        tietEmail = (TextInputEditText) findViewById(R.id.tietEmail);

        /*Devices under sdk level 21 don't have a status bar so trying to set the status bar color causes an exception, hence the below conditional that checks the sdk level before trying to set the status bar color
        * Although the minSdk level of this project is 23, meaning we can omit the conditional and just set the status bar color, we'll still keep it as good practice just in case we ever lower the minSdk level(likely never)
        * NOTE: An app of minSdk level 23 cannot be installed in a device running a lower version of android unless the minSdk is lowered in the app's module level build.gradle.
        * */
        if(android.os.Build.VERSION.SDK_INT >= 21) {
            StatusBarColor sbc = new StatusBarColor(MainActivity.this);
            Window window = this.getWindow();
            sbc.statusBarColor(window);
        }

        /*For this code to work, the main app theme or the theme for this specific activity can't be NoActionBar. There'll be an error cause we're trying to enable or set a title on an action bar that isn't there.*/
        getSupportActionBar().setDisplayShowHomeEnabled(false); //Home is presented as either an activity icon or logo.
        getSupportActionBar().setTitle("Login");

        HideKeyboard hk = new HideKeyboard();
        hk.hideKeyboard(MainActivity.this);

        dialog = new ProgressDialog(this, R.style.Theme_ProgressDialog); //Theme.ProgressDialog is represented as Theme_ProgressDialog with because it has a dot and that can't be used here.
        dialog.setMessage("This is a progress alert");
        dialog.setCancelable(false);
        //dialog.show();
    }
}
