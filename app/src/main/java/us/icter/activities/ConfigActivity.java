package us.icter.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


import us.icter.dirigentes.R;

public class ConfigActivity extends AppCompatActivity implements OnClickListener {
    Spinner spnCheckpoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        spnCheckpoint = (Spinner) findViewById(R.id.spnCheckpoint);
        String[] puntos = getResources().getStringArray(R.array.checkpoint);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, puntos);
        spnCheckpoint.setAdapter(adapter);

        Button btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        SharedPreferences prefs = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Long i = spnCheckpoint.getSelectedItemId();
        if (i == 0) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            AlertDialog alertDialog = alertDialogBuilder.setTitle("Error")
                    .setMessage("Debe eligir algún punto de control")
                    .setCancelable(true)
                    .create();
            alertDialog.show();
        } else {
            editor.putString(getString(R.string.config_name), i.toString());
            editor.commit();

            finish();
        }
    }
}
