package pe.oranch.agenciaturismo.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import pe.oranch.agenciaturismo.Config;

/**
 * Created by Daniel on 15/11/2017.
 */

public class ListarPaisRequest extends StringRequest {
    private static final String LISTAR_PAIS_REQUEST_URL= Config.APP_API_URL + Config.LISTAR_PAIS;
    private Map<String,String> params;
    public ListarPaisRequest(int estado, Response.Listener<String> listener){
        super(Method.POST, LISTAR_PAIS_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put ("tbl_pais_estado",estado+"");
    }

    @Override
    public Map<String,String> getParams() {
        return params;
    }
}
