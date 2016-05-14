package us.icter.dirigentes;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import us.icter.activities.ConfigActivity;
import us.icter.activities.ImageActivity;
import us.icter.activities.QRActivity;
import us.icter.activities.VideoActivity;
import us.icter.libs.Estaciones;
import us.icter.libs.Pruebas;
import us.icter.libs.PuntoAdapter;
import us.icter.libs.TodasAdapter;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    ArrayList<Estaciones> listaPruebas = new ArrayList<>();
    ArrayList<Estaciones> listaPunto = new ArrayList<>();
    TodasAdapter adapterTodas;
    PuntoAdapter adapterPruebas;
    String checkPoint;
    Firebase firebase;
    public Pruebas pruebas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        pruebas = new Pruebas();

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tab;
        tab = tabHost.newTabSpec("tabPoint")
                .setIndicator(getString(R.string.tab_point))
                .setContent(R.id.tab_punto);
        tabHost.addTab(tab);

        tab = tabHost.newTabSpec("tabAll")
                .setIndicator(getString(R.string.tab_all))
                .setContent(R.id.tab_todas);
        tabHost.addTab(tab);

        ListView listTodas = (ListView) findViewById(R.id.listTodas);
        adapterTodas = new TodasAdapter(this, listaPruebas);
        listTodas.setAdapter(adapterTodas);

        ListView listPrueba = (ListView) findViewById(R.id.listPatrulla);
        adapterPruebas = new PuntoAdapter(this, listaPunto);
        listPrueba.setAdapter(adapterPruebas);

        FloatingActionButton btnTest = (FloatingActionButton) findViewById(R.id.btnTest);
        btnTest.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        checkPoint = prefs.getString(getString(R.string.config_name), null);
        if (checkPoint == null) {
            Intent intent = new Intent(this, ConfigActivity.class);
            startActivity(intent);
        } else {
            firebaseComponent();
        }

    }

    private void firebaseComponent() {
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage(getResources().getString(R.string.loading_data));
        pDialog.setCancelable(false);
        pDialog.setIndeterminate(true);

        pDialog.show();
        firebase = new Firebase("https://brilliant-inferno-3084.firebaseio.com/");

        firebase
                .child("respuesta")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        listaPruebas.clear();

                        for (DataSnapshot d: snapshot.getChildren()){
                            Estaciones e = new Estaciones(
                                    d.getKey(),
                                    pruebas.getEstacion((String) d.child("codigo").getValue()).getName(),
                                    (String) d.child("patrulla").getValue(),
                                    (String) d.child("punto").getValue(),
                                    (Long) d.child("tipo").getValue(),
                                    (String) d.child("URL").getValue(),
                                    (Long) d.child("estado").getValue()
                                    );

                            listaPruebas.add(e);
                        }
                        adapterTodas.notifyDataSetChanged();
                        if (pDialog != null)
                            pDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

        firebase
                .child("respuesta")
                .orderByChild("punto")
                .equalTo(checkPoint)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        listaPunto.clear();

                        for (DataSnapshot d: snapshot.getChildren()){
                            Estaciones e = new Estaciones(
                                    d.getKey(),
                                    pruebas.getEstacion((String) d.child("codigo").getValue()).getName(),
                                    (String) d.child("patrulla").getValue(),
                                    (String) d.child("punto").getValue(),
                                    (Long) d.child("tipo").getValue(),
                                    (String) d.child("URL").getValue(),
                                    (Long) d.child("estado").getValue()
                            );

                            listaPunto.add(e);
                        }

                        listaPunto.add(new Estaciones());
                        adapterPruebas.notifyDataSetChanged();

                        if (pDialog != null)
                            pDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
    }

    @Override
    public void onClick(View view) {
        Estaciones p = (Estaciones) view.getTag();
        if (view.getId() == R.id.btnTest) {
            Dialog d = createDialog();
            d.show();
        } else if (view.getId() == R.id.btnSee) {
            Intent i;
            if (p.getType() == 3)
                i = new Intent(this, VideoActivity.class);
            else
                i = new Intent(this, ImageActivity.class);

            i.putExtra(getString(R.string.url_extra), p.getUrl());
            startActivity(i);
        } else {
            final ProgressDialog pDialog = new ProgressDialog(this);
            pDialog.setMessage(getResources().getString(R.string.update_data));
            pDialog.setCancelable(false);
            pDialog.setIndeterminate(true);

            pDialog.show();
            firebase
                    .child("respuesta").child(p.getId()).child("estado")
                    .setValue(1, new Firebase.CompletionListener() {
                        @Override
                        public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        pDialog.dismiss();
                        if (firebaseError != null) {

                        }
                        }
                    });

        }
    }

    //@Override
    public Dialog createDialog() {
        final String[] array = pruebas.getEstacionNombre(checkPoint);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.choice_test)
                .setItems(array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(MainActivity.this, QRActivity.class);
                    i.putExtra(getString(R.string.config_extra), pruebas.getCode(array[which]));
                    startActivity(i);
                    }
                });
        return builder.create();
    }
}

