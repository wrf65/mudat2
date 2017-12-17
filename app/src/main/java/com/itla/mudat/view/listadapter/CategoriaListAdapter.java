package com.itla.mudat.view.listadapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itla.mudat.R;
import com.itla.mudat.entily.Categoria;

import java.util.List;

/**
 * Created by ITregional 3 on 14/12/2017.
 */

public class CategoriaListAdapter extends BaseAdapter {

    private List<Categoria> categorias;
    private Activity context;

    public CategoriaListAdapter(List<Categoria> categorias, Activity context)
    {
        this.categorias = categorias;
        this.context = context;
    }

    @Override
    public int getCount() {
        return categorias.size();
    }

    @Override
    public Object getItem(int i) {
        return categorias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
        {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.listacategoriarow, null);
        }

        TextView lcNombre = (TextView) view.findViewById(R.id.lcNombre);
        TextView lcDescripcion = (TextView) view.findViewById(R.id.lcDescripcion);

        Categoria u = categorias.get(i);
        lcNombre.setText(u.getNombre());
        lcDescripcion.setText(u.getDescripcion());

        return view;
    }
}
