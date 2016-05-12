package us.icter.activities;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.VideoView;

import us.icter.dirigentes.R;

public class VideoActivity extends AppCompatActivity implements OnClickListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
    VideoView videoView;
    ProgressDialog progressDialog;
    FloatingActionButton btnControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        String url = getIntent().getStringExtra(getString(R.string.url_extra));

        videoView = (VideoView) findViewById(R.id.videoView);
        //videoView.setVideoPath(url.replace(".3gp", ".mp4"));
        videoView.setVideoPath(url);
        videoView.start();

        videoView.setOnPreparedListener(this);
        videoView.setOnCompletionListener(this);

        btnControl = (FloatingActionButton) findViewById(R.id.btnControl);
        FloatingActionButton btnBack = (FloatingActionButton) findViewById(R.id.btnBack);

        btnBack.setOnClickListener(this);
        btnControl.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading_download));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnControl:
                if (videoView.isPlaying()) {
                    btnControl.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_white_24dp));
                    videoView.pause();
                } else {
                    btnControl.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_white_24dp));
                    videoView.start();
                }
                break;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        btnControl.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_white_24dp));
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        btnControl.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_white_24dp));
    }
}
