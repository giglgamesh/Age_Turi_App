package pe.oranch.agenciaturismo.entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

/**
 * Created by Daniel on 15/11/2017.
 */

public class Tbl_oferta {
    private Integer tbl_oferta_id;
    private String tbl_oferta_titulo;
    private String tbl_oferta_descripcion;
    private String tbl_oferta_cantidad;
    private Integer tbl_oferta_estado;

    public Integer getTbl_oferta_id() {
        return tbl_oferta_id;
    }

    public void setTbl_oferta_id(Integer tbl_oferta_id) {
        this.tbl_oferta_id = tbl_oferta_id;
    }

    public String getTbl_oferta_titulo() {
        return tbl_oferta_titulo;
    }

    public void setTbl_oferta_titulo(String tbl_oferta_titulo) {
        this.tbl_oferta_titulo = tbl_oferta_titulo;
    }

    public String getTbl_oferta_descripcion() {
        return tbl_oferta_descripcion;
    }

    public void setTbl_oferta_descripcion(String tbl_oferta_descripcion) {
        this.tbl_oferta_descripcion = tbl_oferta_descripcion;
    }

    public String getTbl_oferta_cantidad() {
        return tbl_oferta_cantidad;
    }

    public void setTbl_oferta_cantidad(String tbl_oferta_cantidad) {
        this.tbl_oferta_cantidad = tbl_oferta_cantidad;
    }

    public Integer getTbl_oferta_estado() {
        return tbl_oferta_estado;
    }

    public void setTbl_oferta_estado(Integer tbl_oferta_estado) {
        this.tbl_oferta_estado = tbl_oferta_estado;
    }
}
