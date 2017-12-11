package pe.oranch.agenciaturismo.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import pe.oranch.agenciaturismo.Config;

/**
 * Created by Daniel on 15/11/2017.
 */

public class ListarItemRequest extends StringRequest {
    private static final String LISTAR_ITEM_REQUEST_URL= Config.APP_API_URL + Config.LISTAR_ITEM;
    private Map<String,String> params;
    public ListarItemRequest(int submenu, Response.Listener<String> listener){
        super(Method.POST, LISTAR_ITEM_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put ("tbl_sub_menu_id",submenu+"");
    }

    @Override
    public Map<String,String> getParams() {
        return params;
    }
}
