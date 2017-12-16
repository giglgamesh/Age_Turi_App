package pe.oranch.agenciaturismo.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import pe.oranch.agenciaturismo.Config;


/**
 * Created by Daniel on 02/11/2017.
 */

public class RegistrarClienteRequest extends StringRequest {

    private static final String REGISTRAR_CLIENTE_REQUEST_URL= Config.APP_API_URL + Config.REGISTRAR_CLIENTE;
    private Map<String,String> params;
    public RegistrarClienteRequest(String paisnombre, String clienombre, String cliemail, String cliecelular, String cliefecha, Response.Listener<String> listener){
        super(Method.POST, REGISTRAR_CLIENTE_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put ("tbl_pais_nombre",paisnombre);
        params.put ("tbl_clie_nombre",clienombre);
        params.put ("tbl_clie_email",cliemail);
        params.put ("tbl_clie_celular",cliecelular);
        params.put ("tbl_clie_fecha_registro",cliefecha);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
