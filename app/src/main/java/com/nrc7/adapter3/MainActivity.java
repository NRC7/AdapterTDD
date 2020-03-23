package com.nrc7.adapter3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.nrc7.adapter3.adapter.BookAdapter;
import com.nrc7.adapter3.model.Book;
import com.nrc7.adapter3.model.DataSource;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, ListFragment.newInstance("", ""), "listFragment")
                .commit();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("detailsFragment");
        // Si no hay fragment de detalle, va hacia atras
        if (fragment == null) {
            super.onBackPressed();
        }
        // En caso contrario, escondo el detalle y muestro la lista nuevamente
        else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, ListFragment.newInstance("", ""), "listFragment")
                    .remove(fragment)
                    .commit();
        }
    }
}
