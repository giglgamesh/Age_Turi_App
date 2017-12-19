package pe.oranch.agenciaturismo.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
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
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
    private ImageView facebook,twitter,youtube,whatsapp,instagram;
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
        facebook = (ImageView) findViewById(R.id.about_website);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String facebookId = "fb://page/845310668961255";
                String urlPage = "https://www.facebook.com/World.International.Tacna/";

                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookId )));
                } catch (Exception e) {
                    //Abre url de pagina.
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlPage)));
                }
            }
        });
        whatsapp = (ImageView) findViewById(R.id.whatsapp_link);
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String smsNumber = "+51932121621";
                boolean isWhatsappInstalled = whatsappInstalledOrNot("com.whatsapp");
                if (isWhatsappInstalled) {

                    Intent sendIntent = new Intent("android.intent.action.MAIN");
                    sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                    sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(smsNumber) + "@s.whatsapp.net");//phone number without "+" prefix
                    startActivity(sendIntent);
                } else {
                    Uri uri = Uri.parse("market://details?id=com.whatsapp");
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                    Toast.makeText(NosotrosActivity.this, "WhatsApp no esta instalado", Toast.LENGTH_SHORT).show();
                    startActivity(goToMarket);
                }
            }
        });
        instagram = (ImageView) findViewById(R.id.instagram_link);
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(newInstagramProfileIntent(NosotrosActivity.this.getPackageManager(), "http://www.instagram.com/world_international_tcq"));
            }
        });
        twitter = (ImageView) findViewById(R.id.twitter_link);
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                try {
                    // get the Twitter app if possible
                    NosotrosActivity.this.getPackageManager().getPackageInfo("com.twitter.android", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=937024243098968065"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (Exception e) {
                    // no Twitter app, revert to browser
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/WorldInterTcq"));
                }
                NosotrosActivity.this.startActivity(intent);
            }
        });
    }
    public static Intent newInstagramProfileIntent(PackageManager pm, String url) {
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        try {
            if (pm.getPackageInfo("com.instagram.android", 0) != null) {
                if (url.endsWith("/")) {
                    url = url.substring(0, url.length() - 1);
                }
                final String username = url.substring(url.lastIndexOf("/") + 1);
                // http://stackoverflow.com/questions/21505941/intent-to-open-instagram-user-profile-on-android
                intent.setData(Uri.parse("http://instagram.com/_u/" + username));
                intent.setPackage("com.instagram.android");
                return intent;
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        intent.setData(Uri.parse(url));
        return intent;
    }
    private boolean whatsappInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
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
        MenuItem menuItem= menu.getItem(2);
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
