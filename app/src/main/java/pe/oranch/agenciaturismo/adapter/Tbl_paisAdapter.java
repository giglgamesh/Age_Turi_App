package pe.oranch.agenciaturismo.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pe.oranch.agenciaturismo.R;
import pe.oranch.agenciaturismo.entidades.Tbl_oferta;
import pe.oranch.agenciaturismo.entidades.Tbl_pais;

/**
 * Created by Daniel on 15/11/2017.
 */

public class Tbl_paisAdapter extends ArrayAdapter<Tbl_pais> {

    StringBuffer sb=null;
    ArrayList<Tbl_pais> listapais;
    private Context mContext;
    private Tbl_paisAdapter tbl_paisAdapter;
    private boolean isFromView = false;

    public Tbl_paisAdapter(Context context, int resource,  List<Tbl_pais> listaPais) {
        super(context, resource, listaPais);
        this.listapais = (ArrayList<Tbl_pais>)listaPais;
        this.mContext = context;
        this.tbl_paisAdapter = this;
    }
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(final int position, View convertView,
                              ViewGroup parent) {

        final Tbl_paisAdapter.ComidaHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.lista_pais, null);
            Spinner.LayoutParams layoutParams = new Spinner.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            convertView.setLayoutParams(layoutParams);
            holder = new Tbl_paisAdapter.ComidaHolder();

            holder.textocomidas= (TextView) convertView.findViewById(R.id.textoComidas);
            convertView.setTag(holder);

        } else {
            holder = (Tbl_paisAdapter.ComidaHolder) convertView.getTag();
        }


        holder.textocomidas.setText((listapais.get(position).getTbl_pais_iso().toString()));

        return convertView;
    }

    public class ComidaHolder {
        TextView textocomidas;
    }

}