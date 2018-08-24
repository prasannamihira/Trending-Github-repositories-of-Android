package com.xapo.xapogithubtest.util;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Mihira on 21/8/2018.
 */
public class MessageUtil {

    /**
     * Show snack bar message
     *
     * @param coordinatorLayout
     * @param message
     * @param isError
     */
    public static void snackBarMessage(CoordinatorLayout coordinatorLayout, String message, boolean isError) {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, message, Snackbar.LENGTH_LONG);

        // Set message text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        if (isError) {
            textView.setTextColor(Color.YELLOW);
        } else {
            textView.setTextColor(Color.GREEN);
        }
        snackbar.show();
    }

    /**
     * Show progress message
     *
     * @param progressDialog
     * @param title
     * @param messageProgress
     */
    public static void showProgress(ProgressDialog progressDialog, String title, String messageProgress) {

        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle(title);
        progressDialog.setMessage(messageProgress);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    /**
     * Close progress dialog
     *
     * @param progressDialog
     */
    public static void closeProgress(ProgressDialog progressDialog) {
        if(progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
