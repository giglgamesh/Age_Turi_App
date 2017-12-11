package pe.oranch.agenciaturismo.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import java.util.List;

import pe.oranch.agenciaturismo.entidades.Tbl_menu;
import pe.oranch.agenciaturismo.R;
import static pe.oranch.agenciaturismo.Config.APP_API_URL;

/**
 * Created by Daniel on 15/11/2017.
 */

public class Tbl_menuAdapter extends RecyclerView.Adapter<Tbl_menuAdapter.Tbl_menuHolder> {
    List<Tbl_menu> listaMenu;
    private Tbl_menuAdapter adaptador;
    private Context mContext;

    public Tbl_menuAdapter(Context context, List<Tbl_menu> listaMenu) {
        this.listaMenu = listaMenu;
        this.mContext= context;
        this.adaptador = this;
    }

    @Override
    public Tbl_menuAdapter.Tbl_menuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_menu,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new Tbl_menuAdapter.Tbl_menuHolder(vista);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final Tbl_menuAdapter.Tbl_menuHolder holder, final int position) {
        holder.menu_descripcion.setText(listaMenu.get(position).getTbl_menu_descripcion().toString());
        for (int i=0;i<listaMenu.size();i++){
            String urlimagen;
            urlimagen = APP_API_URL + (listaMenu.get(position).getTbl_menu_ruta().toString());
            Picasso.with(mContext).load(urlimagen).into(holder.imagenmenu);
        }

        holder.menu_descripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listaMenu.size();
    }

    public class Tbl_menuHolder extends RecyclerView.ViewHolder{
        LinearLayout linearprincipal;
        TextView menu_descripcion;
        ImageView imagenmenu;
        public Tbl_menuHolder(View itemView) {
            super(itemView);
            menu_descripcion = (TextView) itemView.findViewById(R.id.menu_Descripcion);
            imagenmenu = (ImageView) itemView.findViewById(R.id.imagenMenu);
            linearprincipal = (LinearLayout) itemView.findViewById(R.id.linearPrincipal);
        }
    }
}
