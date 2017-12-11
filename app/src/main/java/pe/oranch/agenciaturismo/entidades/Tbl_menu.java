package pe.oranch.agenciaturismo.entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

/**
 * Created by Daniel on 15/11/2017.
 */

public class Tbl_menu {
    private Integer tbl_menu_id;
    private String tbl_menu_descripcion;
    private String tbl_menu_estado;
    private String tbl_menu_fecha;
    private String dato;
    private Bitmap tbl_menu_foto;
    private String tbl_menu_ruta;

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;

        try {
            byte[] byteCode= Base64.decode(dato,Base64.DEFAULT);
            //this.imagen= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);

            int alto=100;//alto en pixeles
            int ancho=150;//ancho en pixeles

            Bitmap foto= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);
            this.tbl_menu_foto=Bitmap.createScaledBitmap(foto,alto,ancho,true);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getTbl_menu_id() {
        return tbl_menu_id;
    }

    public void setTbl_menu_id(Integer tbl_menu_id) {
        this.tbl_menu_id = tbl_menu_id;
    }

    public String getTbl_menu_descripcion() {
        return tbl_menu_descripcion;
    }

    public void setTbl_menu_descripcion(String tbl_menu_descripcion) {
        this.tbl_menu_descripcion = tbl_menu_descripcion;
    }

    public String getTbl_menu_estado() {
        return tbl_menu_estado;
    }

    public void setTbl_menu_estado(String tbl_menu_estado) {
        this.tbl_menu_estado = tbl_menu_estado;
    }

    public String getTbl_menu_fecha() {
        return tbl_menu_fecha;
    }

    public void setTbl_menu_fecha(String tbl_menu_fecha) {
        this.tbl_menu_fecha = tbl_menu_fecha;
    }

    public Bitmap getTbl_menu_foto() {
        return tbl_menu_foto;
    }

    public void setTbl_menu_foto(Bitmap tbl_menu_foto) {
        this.tbl_menu_foto = tbl_menu_foto;
    }

    public String getTbl_menu_ruta() {
        return tbl_menu_ruta;
    }

    public void setTbl_menu_ruta(String tbl_menu_ruta) {
        this.tbl_menu_ruta = tbl_menu_ruta;
    }
}
