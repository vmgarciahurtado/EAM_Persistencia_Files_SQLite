package com.example.victor.files_sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.victor.files_sqlite.Controlador.CtlUsuario;
import com.example.victor.files_sqlite.Modelo.Usuario;

public class MainActivity extends AppCompatActivity {

    EditText campoCedula, campoNombre, campoApellido, campoEdad;
    CtlUsuario controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        campoCedula = findViewById(R.id.campoCedula);
        campoNombre = findViewById(R.id.campoNombre);
        campoApellido = findViewById(R.id.campoApellido);
        campoEdad = findViewById(R.id.campoEdad);
        controlador = new CtlUsuario(this);
    }

    public void guardar(View view) {
        String cedula = campoCedula.getText().toString();
        String nombre = campoNombre.getText().toString();
        String apellido = campoApellido.getText().toString();
        int edad = Integer.parseInt(campoEdad.getText().toString());

        if (controlador.guardarUsuario(cedula, nombre, apellido, edad)) {
            Toast.makeText(this, "Almacenado correcto", Toast.LENGTH_SHORT).show();
            limpiar();
        } else {
            Toast.makeText(this, "Almacenado incorrecto", Toast.LENGTH_SHORT).show();
        }
    }


    public void consulta(View view) {
        String cedula = campoCedula.getText().toString();
        Usuario usuario = controlador.buscarUsuario(cedula);

        if (usuario != null) {
            campoNombre.setText(usuario.getNombre());
            campoApellido.setText(usuario.getApellido());
            campoEdad.setText(usuario.getEdad() + "");
        } else {
            Toast.makeText(this, "No se encuentra el usuario", Toast.LENGTH_SHORT).show();
        }
    }


    public void eliminar(View view) {
        String cedula = campoCedula.getText().toString();
        if (controlador.eliminarUsuario(cedula)) {
            Toast.makeText(this, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No se encuentra el usuario", Toast.LENGTH_SHORT).show();
        }
        limpiar();
    }

    private void limpiar() {
        campoCedula.setText(null);
        campoNombre.setText(null);
        campoApellido.setText(null);
        campoEdad.setText(null);
    }

    public void modificar(View view) {
        String cedula = campoCedula.getText().toString();
        String nombre = campoNombre.getText().toString();
        String apellido = campoApellido.getText().toString();
        int edad = Integer.parseInt(campoEdad.getText().toString());

        if (controlador.modificarUsuario(cedula, nombre, apellido, edad)) {
            Toast.makeText(this, "Almacenado correcto", Toast.LENGTH_SHORT).show();
            limpiar();
        } else {
            Toast.makeText(this, "Almacenado incorrecto", Toast.LENGTH_SHORT).show();
        }
    }

    public void listar(View view) {
        Intent intent = new Intent(MainActivity.this,ListadoUsuariosActivity.class);
        startActivity(intent);
    }
}
