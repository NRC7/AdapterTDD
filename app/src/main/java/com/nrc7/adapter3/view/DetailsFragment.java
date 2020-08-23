package com.nrc7.adapter3.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nrc7.adapter3.R;
import com.nrc7.adapter3.databinding.FragmentDetailsBinding;


public class DetailsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String fecha;
    private String valor;

    FragmentDetailsBinding detailsBinding;

    public DetailsFragment() {
        // Required empty public constructor
    }

    // Factory Methods
    public static DetailsFragment newInstance(String param1, String param2) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fecha = getArguments().getString(ARG_PARAM1);
            valor = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Reemplazar el metodo inflate, por DatabindingUtil.inflate
        detailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container,false);
        return detailsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Fecha original separada en dos partes
        // [0] : Desde la T hacia la izquerda
        // [1] : Desde la T hacia la derecha
        String[] fechaModificada = fecha.split("T");
        detailsBinding.detailsName.setText(fechaModificada[0]);
        detailsBinding.detailsAuthor.setText(valor);
    }
}
