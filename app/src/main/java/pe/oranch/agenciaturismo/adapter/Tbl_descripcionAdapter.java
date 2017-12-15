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
import pe.oranch.agenciaturismo.activities.ItemDescripcionActivity;
import pe.oranch.agenciaturismo.entidades.Tbl_descripcion;
import pe.oranch.agenciaturismo.entidades.Tbl_item;

import static pe.oranch.agenciaturismo.Config.APP_API_URL;

/**
 * Created by Daniel on 15/11/2017.
 */

public class Tbl_descripcionAdapter extends RecyclerView.Adapter<Tbl_descripcionAdapter.Tbl_descripcionHolder> {
    List<Tbl_descripcion> listaDescripcion;
    private Tbl_descripcionAdapter adaptador;
    private Context mContext;

    public Tbl_descripcionAdapter(Context context, List<Tbl_descripcion> listaDescripcion) {
        this.listaDescripcion = listaDescripcion;
        this.mContext= context;
        this.adaptador = this;
    }

    @Override
    public Tbl_descripcionAdapter.Tbl_descripcionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_descripcion,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new Tbl_descripcionAdapter.Tbl_descripcionHolder(vista);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final Tbl_descripcionAdapter.Tbl_descripcionHolder holder, final int position) {
        holder.item_titulo.setText(listaDescripcion.get(position).getTbl_descripcion_titulo().toString());
        holder.item_descripcion.setText(listaDescripcion.get(position).getTbl_descripcion_contenido().toString());
    }

    @Override
    public int getItemCount() {
        return listaDescripcion.size();
    }

    public class Tbl_descripcionHolder extends RecyclerView.ViewHolder{
        TextView item_titulo, item_descripcion;
        public Tbl_descripcionHolder(View itemView) {
            super(itemView);
            item_titulo = (TextView) itemView.findViewById(R.id.item_Titulo);
            item_descripcion = (TextView) itemView.findViewById(R.id.item_Descripcion);
        }
    }
}
