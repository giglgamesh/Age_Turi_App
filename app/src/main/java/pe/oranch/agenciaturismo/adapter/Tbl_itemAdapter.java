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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import pe.oranch.agenciaturismo.R;
import pe.oranch.agenciaturismo.activities.ItemActivity;
import pe.oranch.agenciaturismo.activities.ItemDescripcionActivity;
import pe.oranch.agenciaturismo.entidades.Tbl_item;
import pe.oranch.agenciaturismo.entidades.Tbl_sub_menu;

import static pe.oranch.agenciaturismo.Config.APP_API_URL;

/**
 * Created by Daniel on 15/11/2017.
 */

public class Tbl_itemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Tbl_item> listaItem;
    private Tbl_itemAdapter adaptador;
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
    public Tbl_itemAdapter(Context context, List<Tbl_item> listaItem) {
        this.listaItem = listaItem;
        this.mContext= context;
        this.adaptador = this;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if(viewType==VIEW_ITEM) {
            View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item,parent,false);
            RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            vista.setLayoutParams(layoutParams);
            vh = new Tbl_itemAdapter.Tbl_itemHolder(vista);
        }else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_item, parent, false);

            vh = new Tbl_itemAdapter.ProgressViewHolder(v);
        }
        return vh;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof Tbl_menuAdapter.Tbl_menuHolder) {
            ((Tbl_itemHolder)holder).item_titulo.setText(listaItem.get(position).getTbl_item_titulo().toString());
            ((Tbl_itemHolder)holder).item_subtitulo.setText(listaItem.get(position).getTbl_item_subtitulo().toString());
            ((Tbl_itemHolder)holder).item_descripcionsubtitulo.setText(listaItem.get(position).getTbl_item_des_subtitulo().toString());
            ((Tbl_itemHolder)holder).item_descripcioncorta.setText(listaItem.get(position).getTbl_item_des_corta().toString());
            for (int i = 0; i < listaItem.size(); i++) {
                String urlimagen;
                urlimagen = APP_API_URL + (listaItem.get(position).getTbl_item_ruta().toString());
                Picasso.with(mContext).load(urlimagen).into(((Tbl_itemHolder)holder).imagenmenu);
            }

            ((Tbl_itemHolder)holder).layoutmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intentReg = new Intent(mContext,SubmenuActivity.class);
                    //intentReg.putExtra("tbl_menu_id", listaSubMenu.get(position).getTbl_sub_menu_id().toString());
                    //mContext.startActivity(intentReg);
                    Intent intentReg = new Intent(mContext, ItemDescripcionActivity.class);
                    intentReg.putExtra("tbl_item_id", listaItem.get(position).getTbl_item_id().toString());
                    intentReg.putExtra("tbl_item_titulo", listaItem.get(position).getTbl_item_titulo().toString());
                    intentReg.putExtra("tbl_item_des_corta", listaItem.get(position).getTbl_item_des_corta().toString());
                    intentReg.putExtra("tbl_item_des_precio", listaItem.get(position).getTbl_item_des_precio().toString());
                    intentReg.putExtra("tbl_item_ruta", listaItem.get(position).getTbl_item_ruta().toString());
                    mContext.startActivity(intentReg);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return listaItem.get(position)!=null? VIEW_ITEM: VIEW_PROG;
    }
    @Override
    public int getItemCount() {
        return listaItem.size();
    }

    public class Tbl_itemHolder extends RecyclerView.ViewHolder{
        LinearLayout layoutmenu;
        TextView item_titulo, item_subtitulo, item_descripcionsubtitulo, item_descripcioncorta;
        ImageView imagenmenu;
        public Tbl_itemHolder(View itemView) {
            super(itemView);
            item_titulo = (TextView) itemView.findViewById(R.id.item_Titulo);
            item_subtitulo = (TextView) itemView.findViewById(R.id.item_Subtitulo);
            item_descripcionsubtitulo = (TextView) itemView.findViewById(R.id.item_DescripcionSubtitulo);
            item_descripcioncorta = (TextView) itemView.findViewById(R.id.item_DescripcionCorta);
            imagenmenu = (ImageView) itemView.findViewById(R.id.imagenMenu);
            layoutmenu = (LinearLayout) itemView.findViewById(R.id.layoutMenu);
        }
    }
}
