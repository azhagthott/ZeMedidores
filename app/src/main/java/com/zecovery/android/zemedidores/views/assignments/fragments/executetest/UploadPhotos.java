package com.zecovery.android.zemedidores.views.assignments.fragments.executetest;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.zecovery.android.zemedidores.data.CurrentUser;

import static com.zecovery.android.zemedidores.data.Constant.FIREBASE_STORAGE_BASE_URL;

/**
 * Created by fbarrios80 on 03-07-17.
 */

public class UploadPhotos extends Activity {

    private UploadPhotosCallback callback;

    public UploadPhotos(UploadPhotosCallback callback) {
        this.callback = callback;
    }

    public void upload(final String photoName, String localPath, String remoteFolder) {

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

            CurrentUser currentUser = new CurrentUser();
            String user = currentUser.sanitizedEmail(currentUser.email());

            String referenceUrl = FIREBASE_STORAGE_BASE_URL + user + "/" + remoteFolder + "/" + photoName;

            Log.d("referenceUrl", referenceUrl);

            if (photoName != null) {

                StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(referenceUrl);
                storageReference.putFile(Uri.parse("file://" + localPath)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        callback.photoUploaded();
                    }
                });
            } else {
                callback.uploadingPhotoError();
            }
        }
    }
}