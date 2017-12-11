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
public class ContactenosActivity extends AppCompatActivity {
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
        MenuItem menuItem= menu.getItem(1);
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

                        break;
                }
                return false;
            }
        });
    }
}
