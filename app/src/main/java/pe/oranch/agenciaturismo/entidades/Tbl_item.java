package pe.oranch.agenciaturismo.entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

/**
 * Created by Daniel on 15/11/2017.
 */

public class Tbl_item {
    private Integer tbl_item_id;
    private Integer tbl_sub_menu_id;
    private String tbl_item_titulo;
    private String tbl_item_subtitulo;
    private String tbl_item_des_subtitulo;
    private String tbl_item_des_corta;
    private String tbl_item_des_precio;
    private Double tbl_item_precio;
    private Integer tbl_detalle_id;
    private String dato;
    private Bitmap tbl_item_foto;
    private String tbl_item_ruta;
    private Integer tbl_item_estado;

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
            this.tbl_item_foto=Bitmap.createScaledBitmap(foto,alto,ancho,true);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer getTbl_item_id() {
        return tbl_item_id;
    }

    public void setTbl_item_id(Integer tbl_item_id) {
        this.tbl_item_id = tbl_item_id;
    }

    public Integer getTbl_sub_menu_id() {
        return tbl_sub_menu_id;
    }

    public void setTbl_sub_menu_id(Integer tbl_sub_menu_id) {
        this.tbl_sub_menu_id = tbl_sub_menu_id;
    }

    public String getTbl_item_titulo() {
        return tbl_item_titulo;
    }

    public void setTbl_item_titulo(String tbl_item_titulo) {
        this.tbl_item_titulo = tbl_item_titulo;
    }

    public String getTbl_item_subtitulo() {
        return tbl_item_subtitulo;
    }

    public void setTbl_item_subtitulo(String tbl_item_subtitulo) {
        this.tbl_item_subtitulo = tbl_item_subtitulo;
    }

    public String getTbl_item_des_subtitulo() {
        return tbl_item_des_subtitulo;
    }

    public void setTbl_item_des_subtitulo(String tbl_item_descripcion_subtitulo) {
        this.tbl_item_des_subtitulo = tbl_item_descripcion_subtitulo;
    }

    public String getTbl_item_des_corta() {
        return tbl_item_des_corta;
    }

    public void setTbl_item_des_corta(String tbl_item_descripcion_corta) {
        this.tbl_item_des_corta = tbl_item_descripcion_corta;
    }

    public String getTbl_item_des_precio() {
        return tbl_item_des_precio;
    }

    public void setTbl_item_des_precio(String tbl_item_descripcion_precio) {
        this.tbl_item_des_precio = tbl_item_descripcion_precio;
    }

    public Double getTbl_item_precio() {
        return tbl_item_precio;
    }

    public void setTbl_item_precio(Double tbl_item_precio) {
        this.tbl_item_precio = tbl_item_precio;
    }

    public Integer getTbl_detalle_id() {
        return tbl_detalle_id;
    }

    public void setTbl_detalle_id(Integer tbl_detalle_id) {
        this.tbl_detalle_id = tbl_detalle_id;
    }

    public Bitmap getTbl_item_foto() {
        return tbl_item_foto;
    }

    public void setTbl_item_foto(Bitmap tbl_item_foto) {
        this.tbl_item_foto = tbl_item_foto;
    }

    public String getTbl_item_ruta() {
        return tbl_item_ruta;
    }

    public void setTbl_item_ruta(String tbl_item_ruta) {
        this.tbl_item_ruta = tbl_item_ruta;
    }

    public Integer getTbl_item_estado() {
        return tbl_item_estado;
    }

    public void setTbl_item_estado(Integer tbl_item_estado) {
        this.tbl_item_estado = tbl_item_estado;
    }
}
