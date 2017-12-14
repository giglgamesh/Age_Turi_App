package pe.oranch.agenciaturismo.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
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
import pe.oranch.agenciaturismo.adapter.Tbl_menuAdapter;
import pe.oranch.agenciaturismo.adapter.Tbl_submenuAdapter;
import pe.oranch.agenciaturismo.entidades.Tbl_menu;
import pe.oranch.agenciaturismo.entidades.Tbl_sub_menu;
import pe.oranch.agenciaturismo.request.ListarMenuRequest;
import pe.oranch.agenciaturismo.request.ListarSubMenuRequest;

//creado por daniel
public class SubmenuActivity extends AppCompatActivity {
    RecyclerView idrecyclerlista;
    TextView activitytitulo;
    RelativeLayout layoutbotonVolver;
    //LISTA DEL MENU
    ArrayList<Tbl_sub_menu> listaSubMenu;
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
        final String titulo = intentdatos.getStringExtra("tbl_menu_descripcion");
        activitytitulo.setText(titulo);
        //boton volver funcionalidad
        layoutbotonVolver = (RelativeLayout) findViewById(R.id.botonVolver);
        layoutbotonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegP = new Intent(SubmenuActivity.this,PrincipalActivity.class);
                SubmenuActivity.this.startActivity(intentRegP);
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
                        Intent intentReg2 = new Intent(SubmenuActivity.this,OfertaActivity.class);
                        SubmenuActivity.this.startActivity(intentReg2);
                        break;
                    case R.id.ic_nosotros:

                        break;
                    case R.id.ic_contactenos:
                        Intent intentReg = new Intent(SubmenuActivity.this,ContactenosActivity.class);
                        SubmenuActivity.this.startActivity(intentReg);
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
        final int menuid = Integer.parseInt(intentdatos.getStringExtra("tbl_menu_id"));

        Response.Listener<String> responseListenerLista = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    listaSubMenu = new ArrayList<>();
                    idrecyclerlista = findViewById(R.id.idRecyclerLista);
                    idrecyclerlista.setLayoutManager(new LinearLayoutManager(SubmenuActivity.this));
                    idrecyclerlista.setHasFixedSize(true);

                    JSONObject jsonReponse = new JSONObject(response);
                    Tbl_sub_menu tbl_sub_menu=null;
                    JSONArray json=jsonReponse.optJSONArray("usuario");
                    for (int i=0;i<json.length();i++){
                        tbl_sub_menu=new Tbl_sub_menu();
                        JSONObject jsonObject=null;
                        jsonObject=json.getJSONObject(i);
                        tbl_sub_menu.setTbl_sub_menu_id(Integer.parseInt(jsonObject.optString("tbl_sub_menu_id")));
                        tbl_sub_menu.setTbl_sub_menu_descripcion(jsonObject.optString("tbl_sub_menu_descripcion"));
                        tbl_sub_menu.setTbl_sub_menu_ruta(jsonObject.optString("tbl_sub_menu_ruta"));
                        listaSubMenu.add(tbl_sub_menu);
                    }
                    Tbl_submenuAdapter adapter=new Tbl_submenuAdapter(SubmenuActivity.this,listaSubMenu);
                    idrecyclerlista.setAdapter(adapter);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        };
        ListarSubMenuRequest listarsubmenuRequest = new ListarSubMenuRequest(menuid,responseListenerLista);
        RequestQueue queue = Volley.newRequestQueue(SubmenuActivity.this);
        queue.add(listarsubmenuRequest);
    }
}
