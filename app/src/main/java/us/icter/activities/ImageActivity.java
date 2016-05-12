package us.icter.activities;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ortiz.touch.TouchImageView;

import java.io.IOException;
import java.io.InputStream;

import us.icter.dirigentes.R;

public class ImageActivity extends AppCompatActivity {
    public Bitmap bmp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        bmp = (Bitmap) getLastCustomNonConfigurationInstance();

        TouchImageView image = (TouchImageView) findViewById(R.id.img);

        if (bmp == null) {
            DownloadImage download = new DownloadImage();
            download.activity = this;
            download.imageView = image;
            download.url = getIntent().getStringExtra(getString(R.string.url_extra));
            download.execute();
        } else {
            image.setImageBitmap(bmp);
            image.setZoom(1f);
        }

        FloatingActionButton btnBack = (FloatingActionButton) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        final Bitmap bmp = this.bmp;
        return bmp;
    }

    private class DownloadImage extends AsyncTask<Void, Void, Bitmap> {
        public ImageActivity activity;
        public TouchImageView imageView;
        public String url;
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(activity);
            pDialog.setMessage(getResources().getString(R.string.loading_download));
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);

            pDialog.show();
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            try {
                InputStream in = new java.net.URL(url).openStream();
                activity.bmp = BitmapFactory.decodeStream(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return activity.bmp;
        }

        @Override
        protected void onPostExecute(Bitmap bmp) {
            super.onPostExecute(bmp);
            imageView.setImageBitmap(bmp);
            imageView.setZoom(1f);
            if (pDialog != null)
                pDialog.dismiss();
        }
    }
}
