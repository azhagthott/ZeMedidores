package com.zecovery.android.zemedidores.views.assignments.fragments.executetest;

import android.Manifest;
import android.app.Activity;
import android.support.v4.app.Fragment;

import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;
import com.google.firebase.auth.FirebaseAuth;

import static com.zecovery.android.zemedidores.data.Constant.RESIZE_PHOTO_PIXELS_PERCENTAGE;

/**
 * Created by fbarrios80 on 03-07-17.
 */

public class SavePhotos extends Activity {

    private SavePhotosCallback callback;
    private MagicalCamera magicalCamera;
    private MagicalPermissions magicalPermissions;
    private String photoFolder;
    private String photoName;

    public SavePhotos(SavePhotosCallback callback) {
        this.callback = callback;
    }

    public void save(String pushKey, String photoFolder, String photoName, Activity activity, Fragment fragment) {

        this.photoFolder = photoFolder;
        this.photoName = photoName;

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

            if (pushKey != null && photoFolder != null && photoName != null) {

                String[] permissions = new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                };

                magicalPermissions = new MagicalPermissions(activity, permissions);
                magicalCamera = new MagicalCamera(activity, RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions);
                magicalCamera.takeFragmentPhoto(fragment);

                callback.savePhoto();
            } else {
                callback.savePhotoError();
            }
        }
    }
}
