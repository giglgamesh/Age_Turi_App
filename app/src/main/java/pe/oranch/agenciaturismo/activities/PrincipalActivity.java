package pe.oranch.agenciaturismo.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
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
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

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
import pe.oranch.agenciaturismo.entidades.Tbl_menu;
import pe.oranch.agenciaturismo.request.ListarMenuRequest;

//creado por daniel
public class PrincipalActivity extends AppCompatActivity {
    RecyclerView idrecyclerlista;
    //LISTA DEL MENU
    ArrayList<Tbl_menu> listaMenu;
    //PARA EL REFRESH LAYOUT
    SwipeRefreshLayout swipeRefreshLayout;
    ScrollView scrollview01;
    //FIN REFRESH LAYOUT

    //FUNCION PARA RETORNAR Y NO SALIR
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(this)
                    .setTitle(getResources().getString(R.string.app_name))
                    .setMessage("Realmente desea salir?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            finish();
                            System.exit(0);
                        }})
                    .setNegativeButton(android.R.string.no, null).show();
        }
        return true;
    }
    //FIN DE LA FUNCION

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        iniciarObjetos();
        obtenerMenu();
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
                        Intent intentReg = new Intent(PrincipalActivity.this,ContactenosActivity.class);
                        PrincipalActivity.this.startActivity(intentReg);
                        break;
                }
                return false;
            }
        });
    }

    private void actualizarItems(){
        obtenerMenu();
    }

    private void obtenerMenu() {
        final int estado = 1;

        Response.Listener<String> responseListenerLista = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    listaMenu = new ArrayList<>();
                    idrecyclerlista = findViewById(R.id.idRecyclerLista);
                    idrecyclerlista.setLayoutManager(new LinearLayoutManager(PrincipalActivity.this));
                    idrecyclerlista.setHasFixedSize(true);

                    JSONObject jsonReponse = new JSONObject(response);
                    Tbl_menu tbl_menu=null;
                    JSONArray json=jsonReponse.optJSONArray("usuario");
                    for (int i=0;i<json.length();i++){
                        tbl_menu=new Tbl_menu();
                        JSONObject jsonObject=null;
                        jsonObject=json.getJSONObject(i);

                        tbl_menu.setTbl_menu_descripcion(jsonObject.optString("tbl_menu_descripcion"));
                        tbl_menu.setTbl_menu_ruta(jsonObject.optString("tbl_menu_ruta"));
                        listaMenu.add(tbl_menu);
                    }
                    Tbl_menuAdapter adapter=new Tbl_menuAdapter(PrincipalActivity.this,listaMenu);
                    idrecyclerlista.setAdapter(adapter);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        };
        ListarMenuRequest listarmenuRequest = new ListarMenuRequest(estado,responseListenerLista);
        RequestQueue queue = Volley.newRequestQueue(PrincipalActivity.this);
        queue.add(listarmenuRequest);
    }
}
