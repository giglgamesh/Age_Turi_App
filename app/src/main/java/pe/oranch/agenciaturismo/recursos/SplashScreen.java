package pe.oranch.agenciaturismo.recursos;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import pe.oranch.agenciaturismo.R;
import pe.oranch.agenciaturismo.activities.TutorialActivity;
import pe.oranch.agenciaturismo.utilities.Utils;

/**
 * Created by Daniel on 08/12/2017.
 */

public class SplashScreen extends Activity {
    // Tiempo de duracion
    private static final long SPLASH_SCREEN_DELAY = 2000;
    TextView titulo, subtitulo, descripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Orientacion
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // exconder barra titulo
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_presentacion);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {


                Intent mainIntent = new Intent().setClass(
                        SplashScreen.this, TutorialActivity.class);
                startActivity(mainIntent);

                finish();
            }
        };


        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
    public void iniciarobjetos(){
        titulo = (TextView) findViewById(R.id.titulo_splash);
        titulo.setTypeface(Utils.getTypeFace(Utils.Fonts.NUNITO));
        subtitulo = (TextView) findViewById(R.id.subtitulo_splash);
        subtitulo.setTypeface(Utils.getTypeFace(Utils.Fonts.NUNITO));
        descripcion = (TextView) findViewById(R.id.descripcion_splash);
        descripcion.setTypeface(Utils.getTypeFace(Utils.Fonts.NUNITO));
    }
}
