package us.icter.libs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import us.icter.dirigentes.R;

/**
 * Created by jamp on 11/5/16.
 */
public class TodasAdapter extends ArrayAdapter<Estaciones> {
    Context context;
    ArrayList<Estaciones> data;
    LayoutInflater inflater;

    public TodasAdapter(Context context, ArrayList<Estaciones> pruebas) {
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
            convertView = inflater.inflate(R.layout.item_pruebas, null);

            holder = new ViewHolder();
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.subTitle = (TextView) convertView.findViewById(R.id.subTitle);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (currentPrueba.getType() == 2)
            holder.icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_camera_alt_black_24dp));
        else
            holder.icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_videocam_black_24dp));

        if (!currentPrueba.isStatus())
            holder.subTitle.setText("En desarrollo");
        else
            holder.subTitle.setText("Superada");

        holder.title.setText(currentPrueba.getName());

        return convertView;
    }

    class ViewHolder {
        public ImageView icon;
        public TextView title;
        public TextView subTitle;
    }
}
