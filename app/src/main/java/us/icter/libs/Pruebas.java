package us.icter.libs;

import java.util.ArrayList;
import java.util.List;

import us.icter.dirigentes.R;

/**
 * Created by jamp on 12/5/16.
 */
public class Pruebas extends ArrayList<Estacion> {

    public Pruebas() {
        Estacion estacion;

        estacion = new Estacion("pdc1_reto1", "Punto 1 - Reto 1", "1", R.drawable.pdc1_reto1);
        add(estacion);
        estacion = new Estacion("pdc1_reto3", "Punto 1 - Reto 3", "1", R.drawable.pdc1_reto2);
        add(estacion);
        estacion = new Estacion("pdc1_reto4", "Punto 1 - Reto 4", "1", R.drawable.pdc1_reto3);
        add(estacion);
        estacion = new Estacion("pdc1_reto2", "Punto 1 - Reto 2", "1", R.drawable.pdc1_reto4);
        add(estacion);
        estacion = new Estacion("pdc1_reto5", "Punto 1 - Reto 5", "1", R.drawable.pdc1_reto5);
        add(estacion);

        estacion = new Estacion("pdc2_reto1", "Punto 2 - Reto 1", "2", R.drawable.pdc2_reto1);
        add(estacion);
        estacion = new Estacion("pdc2_reto2", "Punto 2 - Reto 2", "2", R.drawable.pdc2_reto2);
        add(estacion);
        estacion = new Estacion("pdc2_reto3", "Punto 2 - Reto 3", "2", R.drawable.pdc2_reto3);
        add(estacion);
        estacion = new Estacion("pdc2_reto4", "Punto 2 - Reto 4", "2", R.drawable.pdc2_reto4);
        add(estacion);
        estacion = new Estacion("pdc2_reto5", "Punto 2 - Reto 5", "2", R.drawable.pdc2_reto5);
        add(estacion);

        estacion = new Estacion("pdc3_reto1", "Punto 3 - Reto 1", "3", R.drawable.pdc3_reto1);
        add(estacion);
        estacion = new Estacion("pdc3_reto2", "Punto 3 - Reto 2", "3", R.drawable.pdc3_reto2);
        add(estacion);
        estacion = new Estacion("pdc3_reto3", "Punto 3 - Reto 3", "3", R.drawable.pdc3_reto3);
        add(estacion);
        estacion = new Estacion("pdc3_reto4", "Punto 3 - Reto 4", "3", R.drawable.pdc3_reto4);
        add(estacion);
        estacion = new Estacion("pdc3_reto5", "Punto 3 - Reto 5", "3", R.drawable.pdc3_reto5);
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
