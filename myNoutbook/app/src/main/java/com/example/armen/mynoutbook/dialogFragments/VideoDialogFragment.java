package com.example.armen.mynoutbook.dialogFragments;

import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.armen.mynoutbook.R;
import com.example.armen.mynoutbook.providers.PrProvider;


public class VideoDialogFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.video_item, container, false);
        initializationVideoView(v);
        return v;
    }

    private void initializationVideoView(View view) {
        final VideoView videoView = view.findViewById(R.id.infoVideo);
        videoView.setVideoURI(Uri.parse(PrProvider.getItemPosition().getVideoUrl()));
        MediaController mediaController = new MediaController(getDialog().getContext());
        videoView.setMediaController(mediaController);
        videoView.start();
    }
}
