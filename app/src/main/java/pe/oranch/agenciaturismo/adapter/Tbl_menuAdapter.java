package pe.oranch.agenciaturismo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import java.util.List;

import pe.oranch.agenciaturismo.activities.ContactenosActivity;
import pe.oranch.agenciaturismo.activities.SubmenuActivity;
import pe.oranch.agenciaturismo.entidades.Tbl_menu;
import pe.oranch.agenciaturismo.R;
import static pe.oranch.agenciaturismo.Config.APP_API_URL;

/**
 * Created by Daniel on 15/11/2017.
 */

public class Tbl_menuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Tbl_menu> listaMenu;
    private Tbl_menuAdapter adaptador;
    private Context mContext;
    public  final int VIEW_ITEM = 1;
    public  final int VIEW_PROG = 0;
    public class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar)v.findViewById(R.id.progressBar);

        }
    }
    public Tbl_menuAdapter(Context context, List<Tbl_menu> listaMenu) {
        this.listaMenu = listaMenu;
        this.mContext= context;
        this.adaptador = this;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if(viewType==VIEW_ITEM) {
            View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_menu,parent,false);
            RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            vista.setLayoutParams(layoutParams);
            vh = new Tbl_menuAdapter.Tbl_menuHolder(vista);
        }else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_item, parent, false);

            vh = new Tbl_menuAdapter.ProgressViewHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof Tbl_menuHolder){
            ((Tbl_menuHolder)holder).menu_descripcion.setText(listaMenu.get(position).getTbl_menu_descripcion().toString());
            for (int i=0;i<listaMenu.size();i++){
                String urlimagen;
                urlimagen = APP_API_URL + (listaMenu.get(position).getTbl_menu_ruta().toString());
                Picasso.with(mContext).load(urlimagen).into(((Tbl_menuHolder)holder).imagenmenu);
            }

            ((Tbl_menuHolder)holder).layoutmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentReg = new Intent(mContext,SubmenuActivity.class);
                    intentReg.putExtra("tbl_menu_id", listaMenu.get(position).getTbl_menu_id().toString());
                    intentReg.putExtra("tbl_menu_descripcion", listaMenu.get(position).getTbl_menu_descripcion().toString());
                    mContext.startActivity(intentReg);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return listaMenu.get(position)!=null? VIEW_ITEM: VIEW_PROG;
    }

    @Override
    public int getItemCount() {
        return listaMenu.size();
    }

    public class Tbl_menuHolder extends RecyclerView.ViewHolder{
        LinearLayout layoutmenu;
        TextView menu_descripcion;
        ImageView imagenmenu;
        public Tbl_menuHolder(View itemView) {
            super(itemView);
            menu_descripcion = (TextView) itemView.findViewById(R.id.menu_Descripcion);
            imagenmenu = (ImageView) itemView.findViewById(R.id.imagenMenu);
            layoutmenu = (LinearLayout) itemView.findViewById(R.id.layoutMenu);
        }
    }
}
