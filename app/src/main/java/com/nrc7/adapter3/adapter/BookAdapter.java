package com.nrc7.adapter3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.nrc7.adapter3.R;
import com.nrc7.adapter3.databinding.ListItemBookBinding;
import com.nrc7.adapter3.model.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    List<Book> bookList;
    OnItemClickListener listener;

    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
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

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Llenar el viewHolder con la info recibida de cada elemento
        // Por ejemplo porner una imagen
        Book book = bookList.get(position);

        // Las vistas estan dentro de bookBinding
        holder.bookBinding.nameTv.setText(book.getName());
        holder.bookBinding.authorTv.setText(book.getAuthor());
    }

    @Override
    public int getItemCount() {
        // Este metodo checkea la cantidad de elementos de la lista
        // Luego, le dice al adapter cuantos son los elementos que debe crear y/o completar con info
        // Receta
        return bookList.size();
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
