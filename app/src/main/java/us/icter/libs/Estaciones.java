package us.icter.libs;

import java.util.ArrayList;

/**
 * Created by jamp on 11/5/16.
 */
public class Estaciones {
    private String id = "";
    private String name = "";
    private String patrulla = "";
    private String point = "<EMPTY_POINT>";
    private int type = 2;
    private String url = "";
    private boolean status = true;

    public Estaciones() {}

    public Estaciones(String id, String name, String patrulla, String point, Long type, String url, Long status) {
        this.id = id;
        this.name = name;
        this.patrulla = patrulla;
        this.point = point;
        this.type = type.intValue();
        this.url = url;
        this.status = (status != null && status == 1L);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPatrulla() {
        return patrulla;
    }

    public String getPoint() {
        return point;
    }

    public int getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public boolean isStatus() {
        return status;
    }
}
