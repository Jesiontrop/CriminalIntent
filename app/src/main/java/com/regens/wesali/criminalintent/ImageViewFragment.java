package com.regens.wesali.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;

import java.io.File;

public class ImageViewFragment extends DialogFragment {
    private static final String ARG_IMAGE = "image";

    private ImageView mPhotoView;
    private File mPhotoFile;

    public static ImageViewFragment newInstance(File file) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_IMAGE, file);

        ImageViewFragment fragment = new ImageViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstaceState) {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_picture, null);
        mPhotoFile = (File) getArguments().getSerializable(ARG_IMAGE);

        mPhotoView = (ImageView) v.findViewById(R.id.dialog_picture_image_view);
        updatePhotoView();

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Image of crime")
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }

    private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
    }
}
