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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pe.oranch.agenciaturismo.BottomNavigationViewHelper;
import pe.oranch.agenciaturismo.R;
import pe.oranch.agenciaturismo.adapter.Tbl_descripcionAdapter;
import pe.oranch.agenciaturismo.adapter.Tbl_itemAdapter;
import pe.oranch.agenciaturismo.adapter.Tbl_submenuAdapter;
import pe.oranch.agenciaturismo.entidades.Tbl_descripcion;
import pe.oranch.agenciaturismo.entidades.Tbl_item;
import pe.oranch.agenciaturismo.entidades.Tbl_sub_menu;
import pe.oranch.agenciaturismo.request.ListarDescripcionRequest;
import pe.oranch.agenciaturismo.request.ListarItemRequest;
import pe.oranch.agenciaturismo.request.ListarSubMenuRequest;

import static pe.oranch.agenciaturismo.Config.APP_API_URL;

//creado por daniel
public class ItemDescripcionActivity extends AppCompatActivity {
    RecyclerView idrecyclerlista;
    TextView activitytitulo, item_descripcionsubtitulo, item_precio;
    ImageView imagenmenu;
    RelativeLayout layoutbotonVolver;
    //LISTA DEL MENU
    ArrayList<Tbl_descripcion> listaDescripcion;
    //PARA EL REFRESH LAYOUT
    SwipeRefreshLayout swipeRefreshLayout;
    ScrollView scrollview01;
    //FIN REFRESH LAYOUT

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_descripcion);
        iniciarObjetos();
        obtenerItemDescripcion();
        iniciarBotMenu();
    }
    private void iniciarObjetos() {
        idrecyclerlista = (RecyclerView) findViewById(R.id.idRecyclerLista);
        //INICIALIZAR REFRESH LAYOUT
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.simpleSwipeRefreshLayout);
        //FIN REFRESH LAYOUT
        //REFRESH

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
        //Obtener datos Titulo
        activitytitulo = (TextView) findViewById(R.id.activityTitulo);
        Intent intentdatos = getIntent();
        final String titulo = intentdatos.getStringExtra("tbl_item_titulo");
        activitytitulo.setText(titulo);
        //Fin Obtener datos Titulo
        //Obtener datos SubTitulo
        item_descripcionsubtitulo = (TextView) findViewById(R.id.item_DescripcionSubtitulo);
        Intent intentdesc= getIntent();
        final String desc = intentdesc.getStringExtra("tbl_item_des_corta");
        item_descripcionsubtitulo.setText(desc);
        //FIN Obtener datos SubTitulo
        //Obtener datos Precio
        item_precio = (TextView) findViewById(R.id.item_Precio);
        Intent intentprec= getIntent();
        final String prec = intentprec.getStringExtra("tbl_item_des_precio");
        item_precio.setText(prec);
        //FIN Obtener datos Precio
        //Obtener datos Imagen
        imagenmenu = (ImageView) findViewById(R.id.imagenMenu);
        Intent intenturl= getIntent();
        final String url = intenturl.getStringExtra("tbl_item_ruta");
        String urlimagen;
        urlimagen = APP_API_URL + url;
        Picasso.with(ItemDescripcionActivity.this).load(urlimagen).into(imagenmenu);
        //FIN Obtener datos Precio
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
                        Intent intentReg2 = new Intent(ItemDescripcionActivity.this,OfertaActivity.class);
                        ItemDescripcionActivity.this.startActivity(intentReg2);
                        break;
                    case R.id.ic_nosotros:
                        Intent intentReg3 = new Intent(ItemDescripcionActivity.this,NosotrosActivity.class);
                        ItemDescripcionActivity.this.startActivity(intentReg3);
                        break;
                    case R.id.ic_contactenos:
                        Intent intentReg = new Intent(ItemDescripcionActivity.this,ContactenosActivity.class);
                        ItemDescripcionActivity.this.startActivity(intentReg);
                        break;
                }
                return false;
            }
        });
    }

    private void actualizarItems(){
        obtenerItemDescripcion();
    }

    private void obtenerItemDescripcion() {
        Intent intentdatos = getIntent();
        final int submenu = Integer.parseInt(intentdatos.getStringExtra("tbl_item_id"));

        Response.Listener<String> responseListenerLista = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    listaDescripcion = new ArrayList<>();
                    idrecyclerlista = findViewById(R.id.idRecyclerLista);
                    idrecyclerlista.setLayoutManager(new LinearLayoutManager(ItemDescripcionActivity.this));
                    idrecyclerlista.setHasFixedSize(true);

                    JSONObject jsonReponse = new JSONObject(response);
                    Tbl_descripcion tbl_descripcion=null;
                    JSONArray json=jsonReponse.optJSONArray("usuario");
                    for (int i=0;i<json.length();i++){
                        tbl_descripcion=new Tbl_descripcion();
                        JSONObject jsonObject=null;
                        jsonObject=json.getJSONObject(i);
                        tbl_descripcion.setTbl_descripcion_titulo(jsonObject.optString("tbl_descripcion_titulo"));
                        tbl_descripcion.setTbl_descripcion_contenido(jsonObject.optString("tbl_descripcion_contenido"));
                        listaDescripcion.add(tbl_descripcion);
                    }
                    Tbl_descripcionAdapter adapter=new Tbl_descripcionAdapter(ItemDescripcionActivity.this,listaDescripcion);
                    idrecyclerlista.setAdapter(adapter);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        };
        ListarDescripcionRequest listardescripcionRequest = new ListarDescripcionRequest(submenu,responseListenerLista);
        RequestQueue queue = Volley.newRequestQueue(ItemDescripcionActivity.this);
        queue.add(listardescripcionRequest);
    }
}
