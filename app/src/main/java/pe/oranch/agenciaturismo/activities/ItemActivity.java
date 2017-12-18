package pe.oranch.agenciaturismo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pe.oranch.agenciaturismo.BottomNavigationViewHelper;
import pe.oranch.agenciaturismo.R;
import pe.oranch.agenciaturismo.adapter.Tbl_itemAdapter;
import pe.oranch.agenciaturismo.adapter.Tbl_submenuAdapter;
import pe.oranch.agenciaturismo.entidades.Tbl_item;
import pe.oranch.agenciaturismo.entidades.Tbl_sub_menu;
import pe.oranch.agenciaturismo.request.ListarItemRequest;
import pe.oranch.agenciaturismo.request.ListarSubMenuRequest;

//creado por daniel
public class ItemActivity extends AppCompatActivity {
    RecyclerView idrecyclerlista;
    TextView activitytitulo;
    RelativeLayout layoutbotonVolver;
    //LISTA DEL MENU
    ArrayList<Tbl_item> listaItem;
    //PARA EL REFRESH LAYOUT
    SwipeRefreshLayout swipeRefreshLayout;
    ScrollView scrollview01;
    //FIN REFRESH LAYOUT

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submenu);
        iniciarObjetos();
        obtenerSubMenu();
        iniciarBotMenu();
    }
    private void iniciarObjetos() {
        idrecyclerlista = (RecyclerView) findViewById(R.id.idRecyclerLista);
        //INICIALIZAR REFRESH LAYOUT
        scrollview01 = findViewById(R.id.ScrollView01);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.simpleSwipeRefreshLayout);
        //FIN REFRESH LAYOUT
        //REFRESH
        scrollview01.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {

            @Override
            public void onScrollChanged() {
                int scrollY = scrollview01.getScrollY();
                if(scrollY == 0) swipeRefreshLayout.setEnabled(true);
                else swipeRefreshLayout.setEnabled(false);

            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        actualizarItems();

                    }
                },50);
                // cancel the Visual indication of a refresh
                //swipeRefreshLayout.setRefreshing(false);
                //actualizarItems();
            }
        });
        //FIN REFRESH
        activitytitulo = (TextView) findViewById(R.id.activityTitulo);
        Intent intentdatos = getIntent();
        final String titulo = intentdatos.getStringExtra("tbl_sub_menu_descripcion");
        activitytitulo.setText(titulo);
        //boton volver funcionalidad
        layoutbotonVolver = (RelativeLayout) findViewById(R.id.botonVolver);
        layoutbotonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        //fin boton volver
    }

    private void iniciarBotMenu(){
        //NAVEGADOR
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem= menu.getItem(0);
        menuItem.setChecked(true);
        //FIN NAVEGADOR
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:

                        break;
                    case R.id.ic_oferta:
                        Intent intentReg2 = new Intent(ItemActivity.this,OfertaActivity.class);
                        ItemActivity.this.startActivity(intentReg2);
                        break;
                    case R.id.ic_nosotros:
                        Intent intentReg3 = new Intent(ItemActivity.this,NosotrosActivity.class);
                        ItemActivity.this.startActivity(intentReg3);
                        break;
                    case R.id.ic_contactenos:
                        Intent intentReg = new Intent(ItemActivity.this,ContactenosActivity.class);
                        ItemActivity.this.startActivity(intentReg);
                        break;
                }
                return false;
            }
        });
    }

    private void actualizarItems(){
        obtenerSubMenu();
    }

    private void obtenerSubMenu() {
        Intent intentdatos = getIntent();
        final int submenu = Integer.parseInt(intentdatos.getStringExtra("tbl_sub_menu_id"));

        Response.Listener<String> responseListenerLista = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    listaItem = new ArrayList<>();
                    idrecyclerlista = findViewById(R.id.idRecyclerLista);
                    idrecyclerlista.setLayoutManager(new LinearLayoutManager(ItemActivity.this));
                    idrecyclerlista.setHasFixedSize(true);

                    JSONObject jsonReponse = new JSONObject(response);
                    Tbl_item tbl_item=null;
                    JSONArray json=jsonReponse.optJSONArray("usuario");
                    for (int i=0;i<json.length();i++){
                        tbl_item=new Tbl_item();
                        JSONObject jsonObject=null;
                        jsonObject=json.getJSONObject(i);
                        tbl_item.setTbl_item_id(Integer.parseInt(jsonObject.optString("tbl_item_id")));
                        tbl_item.setTbl_item_titulo(jsonObject.optString("tbl_item_titulo"));
                        tbl_item.setTbl_item_subtitulo(jsonObject.optString("tbl_item_subtitulo"));
                        tbl_item.setTbl_item_des_subtitulo(jsonObject.optString("tbl_item_des_subtitulo"));
                        tbl_item.setTbl_item_des_corta(jsonObject.optString("tbl_item_des_corta"));
                        tbl_item.setTbl_item_des_precio(jsonObject.optString("tbl_item_des_precio"));
                        tbl_item.setTbl_item_precio(Double.parseDouble(jsonObject.optString("tbl_item_precio")));
                        tbl_item.setTbl_detalle_id(Integer.parseInt(jsonObject.optString("tbl_detalle_id")));
                        tbl_item.setTbl_item_ruta(jsonObject.optString("tbl_item_ruta"));
                        listaItem.add(tbl_item);
                    }
                    Tbl_itemAdapter adapter=new Tbl_itemAdapter(ItemActivity.this,listaItem);
                    idrecyclerlista.setAdapter(adapter);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        };
        ListarItemRequest listaritemRequest = new ListarItemRequest(submenu,responseListenerLista);
        RequestQueue queue = Volley.newRequestQueue(ItemActivity.this);
        queue.add(listaritemRequest);
    }
}
