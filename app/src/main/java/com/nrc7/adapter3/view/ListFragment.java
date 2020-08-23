package com.nrc7.adapter3.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nrc7.adapter3.R;
import com.nrc7.adapter3.adapter.BookAdapter;
import com.nrc7.adapter3.api.IData;
import com.nrc7.adapter3.databinding.FragmentListBinding;
import com.nrc7.adapter3.model.Book;
import com.nrc7.adapter3.api.DataSource;
import com.nrc7.adapter3.model.SerieIndicador;

import java.util.List;


public class ListFragment extends Fragment implements IData {

    // KEY del bundle
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Atributos dentro del Bundle
    private String mParam1;
    private String mParam2;

    // Una vez que tenga el layout RV dentro de la vista
    // NombreDelLayout + Binding
    FragmentListBinding listBinding;
    BookAdapter bookAdapter;

    public ListFragment() {
        // Required empty public constructor
    }

    // Creacion del Fragment
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    // Este metodo se ejecuta antes de onCreateView() y de onViewCreated()
    // Iniciar el llamado
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // Traer referencia al DataSource antes de la creacion de vista
        DataSource dataSource = new DataSource(this);
        dataSource.getIndicadorList("dolar");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        listBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_list, container, false);
        return listBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        listBinding.bookRv.setLayoutManager(manager);
        listBinding.bookRv.setHasFixedSize(true);

        // El adapter == null porque instancio el adapter dentro de otro metodo
    }

    // Los datos ahora estan dentro de este metodo
    @Override
    public void getIndicadorSerie(List<SerieIndicador> list) {
        // Mover Adapter
        bookAdapter = new BookAdapter(list, getContext());
        // Pasar el adapter al RV
        listBinding.bookRv.setAdapter(bookAdapter);
        // Mover el ClickListener
        bookAdapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
                // Contiene Fecha y Valor
                SerieIndicador serieIndicador = list.get(position);
                // Iniciar los fragments : getActivity().getSupportFragmentManager().beginTransaction()
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        // TODO: Cambiar los parametros que van al detalle
                        .add(R.id.container, DetailsFragment.newInstance(serieIndicador.getFecha(), String.valueOf(serieIndicador.getValor())), "detailsFragment")
                        .remove(fragmentManager.findFragmentByTag("listFragment"))
                        .replace(R.id.container, DetailsFragment.newInstance("fecha","800"), "detailsFragment")
                        .commit();

                // DetailsFragment.newInstance() == new DetailsFragment();

            }
        });
    }
}
