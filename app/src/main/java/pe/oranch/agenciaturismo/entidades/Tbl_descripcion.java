package pe.oranch.agenciaturismo.entidades;

/**
 * Created by Daniel on 15/11/2017.
 */

public class Tbl_descripcion {
    private Integer tbl_descripcion_id;
    private String tbl_descripcion_titulo;
    private String tbl_descripcion_contenido;
    private Integer tbl_descripcion_estado;

    public Integer getTbl_descripcion_id() {
        return tbl_descripcion_id;
    }

    public void setTbl_descripcion_id(Integer tbl_descripcion_id) {
        this.tbl_descripcion_id = tbl_descripcion_id;
    }

    public String getTbl_descripcion_titulo() {
        return tbl_descripcion_titulo;
    }

    public void setTbl_descripcion_titulo(String tbl_descripcion_titulo) {
        this.tbl_descripcion_titulo = tbl_descripcion_titulo;
    }

    public String getTbl_descripcion_contenido() {
        return tbl_descripcion_contenido;
    }

    public void setTbl_descripcion_contenido(String tbl_descripcion_contenido) {
        this.tbl_descripcion_contenido = tbl_descripcion_contenido;
    }

    public Integer getTbl_descripcion_estado() {
        return tbl_descripcion_estado;
    }

    public void setTbl_descripcion_estado(Integer tbl_descripcion_estado) {
        this.tbl_descripcion_estado = tbl_descripcion_estado;
    }
}
