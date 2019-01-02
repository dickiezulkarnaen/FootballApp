package example.dickiezulkarnaen.com.footballappfinal.main.utils

import android.Manifest
import android.Manifest.permission
import android.Manifest.permission.WRITE_CALENDAR
import android.app.Activity
import android.support.v4.app.ActivityCompat
import android.content.DialogInterface
import android.os.Build
import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import example.dickiezulkarnaen.com.footballappfinal.main.matches.MatchAdapter


class CalendarPermissionHelper(val context: Context) {

    val MY_PERMISSIONS_REQUEST_WRITE_CALENDAR = 1000

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    fun checkPermission(): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, Manifest.permission.WRITE_CALENDAR)) {
                    val alertBuilder = AlertDialog.Builder(context)
                    alertBuilder.setCancelable(true)
                    alertBuilder.setTitle("Permission necessary")
                    alertBuilder.setMessage("Write calendar permission is necessary to write event!!!")
                    alertBuilder.setPositiveButton(android.R.string.yes, DialogInterface.OnClickListener { dialog, which -> ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.WRITE_CALENDAR), MY_PERMISSIONS_REQUEST_WRITE_CALENDAR) })
                    val alert = alertBuilder.create()
                    alert.show()
                } else {
                    ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.WRITE_CALENDAR), MY_PERMISSIONS_REQUEST_WRITE_CALENDAR)
                }
                return false
            } else {
                return true
            }
        } else {
            return true
        }
    }
}