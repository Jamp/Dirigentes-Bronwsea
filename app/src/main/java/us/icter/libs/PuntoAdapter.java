package us.icter.libs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import us.icter.dirigentes.R;

/**
 * Created by jamp on 11/5/16.
 */
public class PuntoAdapter extends ArrayAdapter<Estaciones> {
    Context context;
    ArrayList<Estaciones> data;
    LayoutInflater inflater;

    public PuntoAdapter(Context context, ArrayList<Estaciones> pruebas) {
        super(context, -1, pruebas);

        this.context = context;
        this.data = pruebas;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Estaciones currentPrueba = data.get(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_punto, null);

            holder = new ViewHolder();
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.subTitle = (TextView) convertView.findViewById(R.id.subTitle);

            holder.btnSee = (ImageButton) convertView.findViewById(R.id.btnSee);
            holder.btnCheck = (ImageButton) convertView.findViewById(R.id.btnCheck);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (currentPrueba.getType() == 2)
            holder.icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_camera_alt_black_24dp));
        else
            holder.icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_videocam_black_24dp));

        holder.subTitle.setText(currentPrueba.getPatrulla());
        holder.title.setText(currentPrueba.getName());

        holder.btnSee.setTag(currentPrueba);
        holder.btnCheck.setTag(currentPrueba);

        //if (currentPrueba.isStatus()) {
        //    holder.btnCheck.setVisibility(View.INVISIBLE);
        //}

        return convertView;
    }

    class ViewHolder {
        public ImageView icon;
        public TextView title;
        public TextView subTitle;

        public ImageButton btnSee;
        public ImageButton btnCheck;
    }
}
