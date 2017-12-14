package pe.oranch.agenciaturismo.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import pe.oranch.agenciaturismo.Config;

/**
 * Created by Daniel on 15/11/2017.
 */

public class ListarOfertaRequest extends StringRequest {
    private static final String LISTAR_OFERTA_REQUEST_URL= Config.APP_API_URL + Config.LISTAR_OFERTA;
    private Map<String,String> params;
    public ListarOfertaRequest(int estado, Response.Listener<String> listener){
        super(Method.POST, LISTAR_OFERTA_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put ("tbl_oferta_estado",estado+"");
    }

    @Override
    public Map<String,String> getParams() {
        return params;
    }
}
