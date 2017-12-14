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
import pe.oranch.agenciaturismo.entidades.Tbl_menu;

//creado por daniel
public class ContactenosActivity extends AppCompatActivity {
    RecyclerView idrecyclerlista;
    SwipeRefreshLayout swipeRefreshLayout;
    ScrollView scrollview01;
    //FIN REFRESH LAYOUT


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactenos);
        iniciarObjetos();
        iniciarBotMenu();
    }
    private void iniciarObjetos() {

    }

    private void iniciarBotMenu(){
        //NAVEGADOR
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem= menu.getItem(3);
        menuItem.setChecked(true);
        //FIN NAVEGADOR
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:
                        Intent intentReg = new Intent(ContactenosActivity.this,PrincipalActivity.class);
                        ContactenosActivity.this.startActivity(intentReg);
                        break;
                    case R.id.ic_oferta:
                        Intent intentReg2 = new Intent(ContactenosActivity.this,OfertaActivity.class);
                        ContactenosActivity.this.startActivity(intentReg2);
                        break;
                    case R.id.ic_nosotros:

                        break;
                    case R.id.ic_contactenos:

                        break;
                }
                return false;
            }
        });
    }
}
