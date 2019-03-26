package com.example.victor.files_sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.victor.files_sqlite.Controlador.CtlUsuario;
import com.example.victor.files_sqlite.Modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ListadoUsuariosActivity extends AppCompatActivity {

    private ListView lstUsuarios;
    CtlUsuario controlador;
    List<Usuario> listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_usuarios);

        controlador = new CtlUsuario(this);
        lstUsuarios = findViewById(R.id.lista);
        configurarLista();
    }

    private void configurarLista() {
        listaUsuarios = controlador.listarUsuario();
        List<String> listaAdapter = new ArrayList<>();
        ArrayAdapter<String> adapter;
        if (listaUsuarios.size() > 0) {
            for (int i = 0; i < listaUsuarios.size(); i++) {
                listaAdapter.add(listaUsuarios.get(i).getCedula() + "-"
                        + listaUsuarios.get(i).getNombre() + "-"
                        + listaUsuarios.get(i).getApellido() + "-"
                        + listaUsuarios.get(i).getEdad());
            }
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaAdapter);
            lstUsuarios.setAdapter(adapter);
        } else {
            listaAdapter.add("No hay registros en la base de datos");
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaAdapter);
            lstUsuarios.setAdapter(adapter);
        }
        lstUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mostrarInformacion(position);
            }
        });

    }

    private void mostrarInformacion(int position) {
        Toast.makeText(this, "Cedula: " + listaUsuarios.get(position).getCedula() + "\n" +
                "Nombre: " + listaUsuarios.get(position).getNombre() + "\n" +
                "Apellido: " + listaUsuarios.get(position).getApellido() + "\n" +
                "Edad: " + listaUsuarios.get(position).getEdad() + "\n", Toast.LENGTH_SHORT).show();
    }
}
