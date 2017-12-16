package pe.oranch.agenciaturismo.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import pe.oranch.agenciaturismo.R;
import pe.oranch.agenciaturismo.adapter.Tbl_paisAdapter;
import pe.oranch.agenciaturismo.entidades.Tbl_pais;
import pe.oranch.agenciaturismo.recursos.PrefManager;
import pe.oranch.agenciaturismo.request.ListarPaisRequest;
import pe.oranch.agenciaturismo.request.RegistrarClienteRequest;
import pe.oranch.agenciaturismo.utilities.Utils;

public class TutorialActivity extends AppCompatActivity {
    //Variables INICIO
    private ViewPager viewPager;
    private TutorialActivity.MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    //private Button btnSkip, btnNext;
    private PrefManager prefManager;
    //FIN VARIABLES INICIO
    ArrayList<Tbl_pais> listaPais;
    EditText nombre_persona, correo_persona, telefono_persona, pais_persona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_tutorial);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        //btnSkip = (Button) findViewById(R.id.btn_skip);
        //btnNext = (Button) findViewById(R.id.btn_next);


        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.tutorial_slider1,
                R.layout.tutorial_slider2,
                R.layout.tutorial_slider3,
                R.layout.tutorial_slider4,
                R.layout.tutorial_slider5};

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        myViewPagerAdapter = new TutorialActivity.MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        //btnSkip.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        launchHomeScreen();
        //    }
        //});

        //btnNext.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
        //        int current = getItem(+1);
        //        if (current < layouts.length) {
                    // move to next screen
        //            viewPager.setCurrentItem(current);
        //        } else {
        //            launchHomeScreen();
                    //LO QUE HACE EL BOTON INICIAR
        //        }
        //    }
        //});
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(TutorialActivity.this, PrincipalActivity.class));
        finish();
    }

    //	viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            //if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
            //    btnNext.setText(getString(R.string.start));
            //    btnSkip.setVisibility(View.GONE);
            //} else {
                // still pages are left
            //    btnNext.setText(getString(R.string.siguiente));
            //    btnSkip.setVisibility(View.VISIBLE);
            //}
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public void EnviarSuscribete(View view) {
        String paisnombre;
        String clienombre;
        String cliemail;
        String cliecelular;
        String cliefecha;
        Date fechayhora=new Date();
        if (inputValidation()){
            if(isInternetOn()) {
                clienombre = nombre_persona.getText().toString();
                cliecelular = telefono_persona.getText().toString();
                cliemail = correo_persona.getText().toString();
                paisnombre = pais_persona.getText().toString();
                cliefecha=(fechayhora.getYear()+1900)+"-"+(fechayhora.getMonth()+1)+"-"+fechayhora.getDate();
                    Response.Listener<String> responseListenerc2RegistrarMenu = new Response.Listener<String>(){
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonReponse = new JSONObject(response);
                            }
                            catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    };
                    RegistrarClienteRequest registrarclienteRequest = new RegistrarClienteRequest(paisnombre,clienombre,cliemail,cliecelular,cliefecha,responseListenerc2RegistrarMenu);
                    RequestQueue queuer = Volley.newRequestQueue(TutorialActivity.this);
                    queuer.add(registrarclienteRequest);
                    //FIN INGRESO SEGUNDO

                launchHomeScreen();
            }else{
                showOffline();
            }
        }

    }

    private void showOffline() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(TutorialActivity.this, R.style.AppCompatAlertDialogStyle);
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

        ConnectivityManager cm = (ConnectivityManager) TutorialActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
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
        //IniciarObjetos();
        if(nombre_persona.getText().toString().equals("")) {
            Toast.makeText(TutorialActivity.this.getApplicationContext(), R.string.nombre_correo,
                    Toast.LENGTH_LONG).show();
            return false;
        }

        if(telefono_persona.getText().toString().equals("")) {
            Toast.makeText(TutorialActivity.this.getApplicationContext(), R.string.telefono_correo,
                    Toast.LENGTH_LONG).show();
            return false;
        }

        if(correo_persona.getText().toString().equals("")) {
            Toast.makeText(TutorialActivity.this.getApplicationContext(), R.string.correo_persona,
                    Toast.LENGTH_LONG).show();
            return false;
        }

        if(pais_persona.getText().toString().equals("")) {
            Toast.makeText(TutorialActivity.this.getApplicationContext(), R.string.mensaje_pais,
                    Toast.LENGTH_LONG).show();
            return false;
        }

        return true;

    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            final View viewspin = layoutInflater.inflate(layouts[4], container, false);
            container.addView(view);
            nombre_persona = (EditText) view.findViewById(R.id.nombre_Persona);
            telefono_persona = (EditText) view.findViewById(R.id.telefono_Persona);
            correo_persona = (EditText) view.findViewById(R.id.correo_Persona);
            pais_persona = (EditText) view.findViewById(R.id.pais_Persona);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
