package pe.oranch.agenciaturismo.adapter;

import android.content.Context;
import android.content.Intent;
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

import pe.oranch.agenciaturismo.R;
import pe.oranch.agenciaturismo.activities.ItemActivity;
import pe.oranch.agenciaturismo.activities.SubmenuActivity;
import pe.oranch.agenciaturismo.entidades.Tbl_menu;
import pe.oranch.agenciaturismo.entidades.Tbl_sub_menu;

import static pe.oranch.agenciaturismo.Config.APP_API_URL;

/**
 * Created by Daniel on 15/11/2017.
 */

public class Tbl_submenuAdapter extends RecyclerView.Adapter<Tbl_submenuAdapter.Tbl_submenuHolder> {
    List<Tbl_sub_menu> listaSubMenu;
    private Tbl_submenuAdapter adaptador;
    private Context mContext;

    public Tbl_submenuAdapter(Context context, List<Tbl_sub_menu> listaSubMenu) {
        this.listaSubMenu = listaSubMenu;
        this.mContext= context;
        this.adaptador = this;
    }

    @Override
    public Tbl_submenuAdapter.Tbl_submenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_submenu,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new Tbl_submenuAdapter.Tbl_submenuHolder(vista);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final Tbl_submenuAdapter.Tbl_submenuHolder holder, final int position) {
        holder.menu_descripcion.setText(listaSubMenu.get(position).getTbl_sub_menu_descripcion().toString());
        for (int i=0;i<listaSubMenu.size();i++){
            String urlimagen;
            urlimagen = APP_API_URL + (listaSubMenu.get(position).getTbl_sub_menu_ruta().toString());
            Picasso.with(mContext).load(urlimagen).into(holder.imagenmenu);
        }

        holder.layoutmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(mContext,ItemActivity.class);
                intentReg.putExtra("tbl_sub_menu_id", listaSubMenu.get(position).getTbl_sub_menu_id().toString());
                intentReg.putExtra("tbl_sub_menu_descripcion", listaSubMenu.get(position).getTbl_sub_menu_descripcion().toString());
                mContext.startActivity(intentReg);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaSubMenu.size();
    }

    public class Tbl_submenuHolder extends RecyclerView.ViewHolder{
        LinearLayout layoutmenu;
        TextView menu_descripcion;
        ImageView imagenmenu;
        public Tbl_submenuHolder(View itemView) {
            super(itemView);
            menu_descripcion = (TextView) itemView.findViewById(R.id.menu_Descripcion);
            imagenmenu = (ImageView) itemView.findViewById(R.id.imagenMenu);
            layoutmenu = (LinearLayout) itemView.findViewById(R.id.layoutMenu);
        }
    }
}
