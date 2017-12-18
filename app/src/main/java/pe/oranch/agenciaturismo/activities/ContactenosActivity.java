package pe.oranch.agenciaturismo.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import pe.oranch.agenciaturismo.BottomNavigationViewHelper;
import pe.oranch.agenciaturismo.R;
import pe.oranch.agenciaturismo.utilities.Utils;

//creado por daniel
public class ContactenosActivity extends AppCompatActivity {
    RecyclerView idrecyclerlista;
    SwipeRefreshLayout swipeRefreshLayout;
    ScrollView scrollview01;
    EditText nombre_correo, telefono_correo, correo_persona, mensaje_correo;
    //FIN REFRESH LAYOUT


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactenos);
        iniciarObjetos();
        iniciarBotMenu();
    }
    private void iniciarObjetos() {
        nombre_correo = (EditText) findViewById(R.id.nombre_Correo);
        telefono_correo = (EditText) findViewById(R.id.telefono_Correo);
        correo_persona = (EditText) findViewById(R.id.correo_Persona);
        mensaje_correo = (EditText) findViewById(R.id.mensaje_Correo);
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
                        Intent intentReg3 = new Intent(ContactenosActivity.this,NosotrosActivity.class);
                        ContactenosActivity.this.startActivity(intentReg3);
                        break;
                    case R.id.ic_contactenos:

                        break;
                }
                return false;
            }
        });
    }

    public void EnviarCorreo(View view) {
        String nombre;
        String correo;
        String telefono;
        String mensaje;
        if (inputValidation()){
            if(isInternetOn()) {
                nombre = nombre_correo.getText().toString();
                telefono = telefono_correo.getText().toString();
                correo = correo_persona.getText().toString();
                mensaje = mensaje_correo.getText().toString();
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"worldinternationaltcq@gmail.com"});
                //emailIntent.putExtra(Intent.EXTRA_BCC, new String[]{"email3@ekiketa.es"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Agencia APP: "+nombre);
                //Recordad que la barra invertida más "n" es un salto de linea "n" así, escribiremos el email con varios saltos de linea.
                String textoApp = mensaje+"\n"+"mensaje enviado por: "+nombre+"\n"+"correo: "+correo+"\n"+"telefono: "+telefono;
                emailIntent.putExtra(Intent.EXTRA_TEXT, textoApp);
                emailIntent.setType("message/rfc822");
                //Damos la opción al usuario que elija desde que app enviamos el email.
                startActivity(Intent.createChooser(emailIntent, "Selecciona aplicación..."));
            }else{
                showOffline();
            }
        }
    }
    private void showOffline() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(ContactenosActivity.this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle(R.string.sorry_title);
        builder.setMessage(R.string.device_offline);
        builder.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Utils.psLog("OK clicked.");
            }
        });
        builder.show();
    }
    public final boolean isInternetOn() {

        ConnectivityManager cm = (ConnectivityManager) ContactenosActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                return true;
            }
        } else {
            return false;
        }
        return false;
    }
    private boolean inputValidation() {

        if(nombre_correo.getText().toString().equals("")) {
            Toast.makeText(ContactenosActivity.this.getApplicationContext(), R.string.nombre_correo,
                    Toast.LENGTH_LONG).show();
            return false;
        }

        if(telefono_correo.getText().toString().equals("")) {
            Toast.makeText(ContactenosActivity.this.getApplicationContext(), R.string.telefono_correo,
                    Toast.LENGTH_LONG).show();
            return false;
        }

        if(correo_persona.getText().toString().equals("")) {
            Toast.makeText(ContactenosActivity.this.getApplicationContext(), R.string.correo_persona,
                    Toast.LENGTH_LONG).show();
            return false;
        }

        if(mensaje_correo.getText().toString().equals("")) {
            Toast.makeText(ContactenosActivity.this.getApplicationContext(), R.string.mensaje_correo,
                    Toast.LENGTH_LONG).show();
            return false;
        }

        return true;

    }
}
