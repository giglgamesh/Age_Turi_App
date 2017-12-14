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
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pe.oranch.agenciaturismo.BottomNavigationViewHelper;
import pe.oranch.agenciaturismo.R;
import pe.oranch.agenciaturismo.adapter.Tbl_ofertaAdapter;
import pe.oranch.agenciaturismo.entidades.Tbl_oferta;
import pe.oranch.agenciaturismo.request.ListarOfertaRequest;
import pe.oranch.agenciaturismo.utilities.Utils;

//creado por daniel
public class OfertaActivity extends AppCompatActivity {
    RecyclerView idrecyclerlista;
    //LISTA DEL MENU
    ArrayList<Tbl_oferta> listaOferta;
    //PARA EL REFRESH LAYOUT
    SwipeRefreshLayout swipeRefreshLayout;
    ScrollView scrollview01;
    //FIN REFRESH LAYOUT
    //fragmento y firebase
    private FirebaseAnalytics mFirebaseAnalytics;
    //fin fragmento y firebase


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta);
        obtenerOfertas();
        iniciarObjetos();
        initUtils();
        iniciarBotMenu();
        FirebaseMessaging.getInstance().subscribeToTopic("AgenciaTurismo");
    }
    private void initUtils() {
        new Utils(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
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
        MenuItem menuItem= menu.getItem(1);
        menuItem.setChecked(true);
        //FIN NAVEGADOR
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:
                        Intent intentReg = new Intent(OfertaActivity.this,PrincipalActivity.class);
                        OfertaActivity.this.startActivity(intentReg);
                        break;
                    case R.id.ic_oferta:

                        break;
                    case R.id.ic_nosotros:

                        break;
                    case R.id.ic_contactenos:
                        Intent intentReg4 = new Intent(OfertaActivity.this,ContactenosActivity.class);
                        OfertaActivity.this.startActivity(intentReg4);
                        break;
                }
                return false;
            }
        });
    }
    private void actualizarItems(){
        obtenerOfertas();
    }

    private void obtenerOfertas() {
        final int estado = 1;

        Response.Listener<String> responseListenerLista = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    listaOferta = new ArrayList<>();
                    idrecyclerlista = findViewById(R.id.idRecyclerLista);
                    idrecyclerlista.setLayoutManager(new LinearLayoutManager(OfertaActivity.this));
                    idrecyclerlista.setHasFixedSize(true);

                    JSONObject jsonReponse = new JSONObject(response);
                    Tbl_oferta tbl_oferta=null;
                    JSONArray json=jsonReponse.optJSONArray("usuario");
                    for (int i=0;i<json.length();i++){
                        tbl_oferta=new Tbl_oferta();
                        JSONObject jsonObject=null;
                        jsonObject=json.getJSONObject(i);
                        tbl_oferta.setTbl_oferta_titulo(jsonObject.optString("tbl_oferta_titulo"));
                        tbl_oferta.setTbl_oferta_descripcion(jsonObject.optString("tbl_oferta_descripcion"));
                        tbl_oferta.setTbl_oferta_cantidad(jsonObject.optString("tbl_oferta_cantidad"));
                        listaOferta.add(tbl_oferta);
                    }
                    Tbl_ofertaAdapter adapter=new Tbl_ofertaAdapter(OfertaActivity.this,listaOferta);
                    idrecyclerlista.setAdapter(adapter);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        };
        ListarOfertaRequest listarofertaRequest = new ListarOfertaRequest(estado,responseListenerLista);
        RequestQueue queue = Volley.newRequestQueue(OfertaActivity.this);
        queue.add(listarofertaRequest);
    }
}
