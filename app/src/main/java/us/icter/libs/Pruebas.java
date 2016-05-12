package us.icter.libs;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import us.icter.dirigentes.R;

/**
 * Created by jamp on 12/5/16.
 */
public class Pruebas extends ArrayList<Estacion> {

    public Pruebas() {
        Estacion estacion;

        estacion = new Estacion("133", "Redes Sociales", "1", R.drawable.qrcode_133);
        add(estacion);

        estacion = new Estacion("134", "Prevención de Ebola", "1", R.drawable.qrcode_134);
        add(estacion);

        estacion = new Estacion("188", "Prevención de Ebola", "2", R.drawable.qrcode_134);
        add(estacion);
    }

    public Estacion getEstacion(String code) {
        for (Estacion e : this) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

    public String getCode(String name) {
        for (Estacion e : this) {
            if (e.getName().equals(name)) {
                return e.getCode();
            }
        }
        return null;
    }

    public String[] getEstacionNombre(String point) {
        List<String> lista = new ArrayList<>();
        int i = 0;
        for (Estacion e : this) {
            if (e.getPoint().equals(point)) {
                lista.add(e.getName());
                i += 1;
            }
        }

        String[] resultado = new String[i];
        resultado = lista.toArray(resultado);
        return resultado;
    }

    public int getSize() {
        return this.size();
    }
}
