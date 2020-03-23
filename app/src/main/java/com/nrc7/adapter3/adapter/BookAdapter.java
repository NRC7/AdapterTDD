package com.nrc7.adapter3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.nrc7.adapter3.R;
import com.nrc7.adapter3.model.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder>{

    List<Book> bookList;

    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Crear ViewHolders onDemand, si necesito 10 vistas, 10 va a crear, etc.
        // Receta, que "siempre" se crea de la misma forma
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_book, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Llenar el viewHolder con la info recibida de cada elemento
        // Por ejemplo porner una imagen
        Book book = bookList.get(position);
        holder.nameTv.setText(book.getName());
        holder.authorTv.setText(book.getAuthor());
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

    // Primer paso
    public class MyViewHolder extends RecyclerView.ViewHolder {

        // Traer las vistas del list_item
        TextView nameTv;
        TextView authorTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // Inicializar las vistas (findviewById())
            nameTv = itemView.findViewById(R.id.nameTv);
            authorTv = itemView.findViewById(R.id.authorTv);
        }
    }
}
