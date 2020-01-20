package co.chiraggada.furn360.AR.Helper;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import co.chiraggada.furn360.AR.ArActivity;

/** Helper to ask camera permission. */
    public final class CameraPermissionHelper {
        private static final int CAMERA_PERMISSION_CODE = 0;
        private static final String CAMERA_PERMISSION = Manifest.permission.CAMERA;

        /** Check to see we have the necessary permissions for this app.
         * @param activity*/
        public static boolean hasCameraPermission(ArActivity activity) {
            return ContextCompat.checkSelfPermission(activity, CAMERA_PERMISSION)
                    == PackageManager.PERMISSION_GRANTED;
        }

        /** Check to see we have the necessary permissions for this app, and ask for them if we don't.
         * @param activity*/
        public static void requestCameraPermission(ArActivity activity) {
            ActivityCompat.requestPermissions(
                    activity, new String[] {CAMERA_PERMISSION}, CAMERA_PERMISSION_CODE);
        }

        /** Check to see if we need to show the rationale for this permission.
         * @param activity*/
        public static boolean shouldShowRequestPermissionRationale(ArActivity activity) {
            return ActivityCompat.shouldShowRequestPermissionRationale(activity, CAMERA_PERMISSION);
        }

        /** Launch Application Setting to grant permission.
         * @param activity*/
        public static void launchPermissionSettings(ArActivity activity) {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
            activity.startActivity(intent);
        }
    }
