package pe.oranch.agenciaturismo.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import pe.oranch.agenciaturismo.BottomNavigationViewHelper;
import pe.oranch.agenciaturismo.R;

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

    public void EnviarCorreo(View view) {

    }
}
