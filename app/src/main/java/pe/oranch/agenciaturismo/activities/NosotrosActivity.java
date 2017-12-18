package pe.oranch.agenciaturismo.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import pe.oranch.agenciaturismo.BottomNavigationViewHelper;
import pe.oranch.agenciaturismo.R;
import pe.oranch.agenciaturismo.utilities.Utils;

//creado por daniel
public class NosotrosActivity extends AppCompatActivity {
    private MapView mMapView;
    private TextView aboutwebsite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nosotros);
        iniciarObjetos();
        iniciarBotMenu();
        initilizeMap(savedInstanceState);
        bindShopInfo();
    }

    private void iniciarObjetos() {
        aboutwebsite = (TextView) findViewById(R.id.about_website);
        aboutwebsite.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(aboutwebsite.getText().toString()));
                startActivity(intent);
            }
        });

    }
    private void initilizeMap(Bundle savedInstanceState) {
        if (Utils.isGooglePlayServicesOK(this)) {
            mMapView = (MapView) findViewById(R.id.mapView);
            mMapView.onCreate(savedInstanceState);
            mMapView.onResume();
            try {
                MapsInitializer.initialize(getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void bindShopInfo() {

        try {
            mMapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                    googleMap.getUiSettings().setZoomControlsEnabled(false);


                    double latitude = -18.0136869;
                    double longitude = -70.2525957;

                    MarkerOptions marker = new MarkerOptions().position(new LatLng( latitude, longitude)).title("World International");
                    marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(
                            new LatLng(latitude, longitude)).zoom(15.1f).build();

                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),new GoogleMap.CancelableCallback() {
                        @Override
                        public void onFinish() {

                            Utils.psLog("Finish Camera update.");
                        }

                        @Override
                        public void onCancel() {
                            Utils.psLog("Cancel Camera update.");
                        }
                    });


                    googleMap.addMarker(marker);
                }
            });

        } catch (Exception e) {
            Utils.psErrorLogE("Error in map initialize.", e);
        }

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
                        Intent intentReg = new Intent(NosotrosActivity.this,PrincipalActivity.class);
                        NosotrosActivity.this.startActivity(intentReg);
                        break;
                    case R.id.ic_oferta:
                        Intent intentReg2 = new Intent(NosotrosActivity.this,OfertaActivity.class);
                        NosotrosActivity.this.startActivity(intentReg2);
                        break;
                    case R.id.ic_nosotros:

                        break;
                    case R.id.ic_contactenos:
                        Intent intentReg4 = new Intent(NosotrosActivity.this,ContactenosActivity.class);
                        NosotrosActivity.this.startActivity(intentReg4);
                        break;
                }
                return false;
            }
        });
    }
}
