package us.icter.libs;

/**
 * Created by jamp on 12/5/16.
 */
public class Estacion {
    String code;
    String name;
    String point;
    int drawable;

    public Estacion(String code, String name, String point, int drawable) {
        this.code = code;
        this.name = name;
        this.point = point;
        this.drawable = drawable;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getPoint() {
        return point;
    }

    public int getDrawable() {
        return drawable;
    }
}
