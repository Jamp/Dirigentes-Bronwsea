package us.icter.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import us.icter.dirigentes.R;
import us.icter.libs.Estacion;
import us.icter.libs.Pruebas;

public class QRActivity extends AppCompatActivity implements OnClickListener {
    Pruebas pruebas = new Pruebas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        String code = getIntent().getStringExtra(getString(R.string.config_extra));
        Estacion estacion = pruebas.getEstacion(code);

        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        ImageView imageQR = (ImageView) findViewById(R.id.imageQR);

        txtTitle.setText(estacion.getName());
        Log.e("TAG", String.valueOf(estacion.getDrawable()));
        imageQR.setImageDrawable(getResources().getDrawable(estacion.getDrawable()));

        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
