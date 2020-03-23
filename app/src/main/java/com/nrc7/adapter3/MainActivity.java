package com.nrc7.adapter3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.nrc7.adapter3.adapter.BookAdapter;
import com.nrc7.adapter3.model.Book;
import com.nrc7.adapter3.model.DataSource;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Una vez que tenga el layout RV dentro de la vista
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.bookRv);

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        recyclerView.setHasFixedSize(true);

        List<Book> bookList = new DataSource().getBooks();
        BookAdapter bookAdapter = new BookAdapter(bookList);
        // Pasar el adapter al RV
        recyclerView.setAdapter(bookAdapter);
    }
}
