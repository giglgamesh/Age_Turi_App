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
import pe.oranch.agenciaturismo.activities.SubmenuActivity;
import pe.oranch.agenciaturismo.entidades.Tbl_menu;
import pe.oranch.agenciaturismo.entidades.Tbl_oferta;

import static pe.oranch.agenciaturismo.Config.APP_API_URL;

/**
 * Created by Daniel on 15/11/2017.
 */

public class Tbl_ofertaAdapter extends RecyclerView.Adapter<Tbl_ofertaAdapter.Tbl_ofertaHolder> {
    List<Tbl_oferta> listaOferta;
    private Tbl_ofertaAdapter adaptador;
    private Context mContext;

    public Tbl_ofertaAdapter(Context context, List<Tbl_oferta> listaOferta) {
        this.listaOferta = listaOferta;
        this.mContext= context;
        this.adaptador = this;
    }

    @Override
    public Tbl_ofertaAdapter.Tbl_ofertaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_oferta,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new Tbl_ofertaAdapter.Tbl_ofertaHolder(vista);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final Tbl_ofertaAdapter.Tbl_ofertaHolder holder, final int position) {
        holder.item_titulo.setText(listaOferta.get(position).getTbl_oferta_titulo().toString());
        holder.item_descripcion.setText(listaOferta.get(position).getTbl_oferta_descripcion().toString());
        holder.item_descuento.setText(listaOferta.get(position).getTbl_oferta_cantidad().toString());
        holder.layoutmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listaOferta.size();
    }

    public class Tbl_ofertaHolder extends RecyclerView.ViewHolder{
        LinearLayout layoutmenu;
        TextView item_titulo, item_descripcion, item_descuento;
        public Tbl_ofertaHolder(View itemView) {
            super(itemView);
            item_titulo = (TextView) itemView.findViewById(R.id.item_Titulo);
            item_descripcion = (TextView) itemView.findViewById(R.id.item_Descripcion);
            item_descuento = (TextView) itemView.findViewById(R.id.item_Descuento);
            layoutmenu = (LinearLayout) itemView.findViewById(R.id.layoutMenu);
        }
    }
}
