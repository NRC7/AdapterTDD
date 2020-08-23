package com.nrc7.adapter3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.nrc7.adapter3.R;
import com.nrc7.adapter3.databinding.ListItemBookBinding;
import com.nrc7.adapter3.model.Book;
import com.nrc7.adapter3.model.SerieIndicador;

import org.w3c.dom.Text;

import java.util.List;

// SerieIndicadorAdapter
// El adapter es quien tiene la logica del comportamiento de las listas en android
// Todo Adapter necesita una lista entonces no lo puedes construir sin una
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    OnItemClickListener listener;
    List<SerieIndicador> serieList;
    Context context;

    //En el otro lado, en el fragment
    // Cuando tu digas new Adapter(), new Adapter(List<T> list)

    public BookAdapter(List<SerieIndicador> serieList, Context context) {
        this.serieList = serieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Crear ViewHolders onDemand, si necesito 10 vistas, 10 va a crear, etc.
        // Receta, que "siempre" se crea de la misma forma
        ListItemBookBinding bookBinding = ListItemBookBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new MyViewHolder(bookBinding);
    }

    // Warning: La position no puede ser final
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Llenar el viewHolder con la info recibida de cada elemento
        // Por ejemplo porner una imagen
        SerieIndicador serieIndicador = serieList.get(position);
        // 0 al 4
        // Scrolleas vas desde el 5 hace n-1

        // Las vistas estan dentro de bookBinding
        holder.bookBinding.fechaTv.setText(serieIndicador.getFecha());
        holder.bookBinding.valorTv.setText(String.valueOf(serieIndicador.getValor()));
        // Setear la imagen al ImageView

        // Le dice al adapter, la lsita orginal sufrio algun cambio
        // Por lo tanto actualizate
        // Recycler de Modelos, lista de eventos, lista de alumnos
        // notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        // Este metodo checkea la cantidad de elementos de la lista
        // Luego, le dice al adapter cuantos son los elementos que debe crear y/o completar con info
        // Receta
        return serieList.size();
    }

    // SI O SI lleva una lista basada en el POJO o Modelo
    // Una interface, el context.

    // Crear Interface para el click
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Sirve para utilizar el listener fuera de la clase
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Primer paso
    public class MyViewHolder extends RecyclerView.ViewHolder {

        // Autogenerada por Databinding que contiene las vistas
        ListItemBookBinding bookBinding;
        // Si el diseño del rectangula tiene una imagen, texteview y button
        // Aca los tengo que instanciar con findViewById o Binding

        public MyViewHolder(@NonNull ListItemBookBinding bookBinding) {
            super(bookBinding.getRoot());
            this.bookBinding = bookBinding;
            bookBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener.onItemClick(position);
                }
            });
        }
    }
}
