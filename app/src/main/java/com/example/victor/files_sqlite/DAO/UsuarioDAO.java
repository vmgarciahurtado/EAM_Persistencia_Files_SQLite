package com.example.victor.files_sqlite.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.example.victor.files_sqlite.Infraestructura.Conexion;
import com.example.victor.files_sqlite.Modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 3/19/19.
 */

public class UsuarioDAO {
    Conexion conexion;

    public UsuarioDAO(Activity activity){
        conexion = new Conexion(activity);
    }

    public boolean guardar(Usuario usuario){
        ContentValues registro = new ContentValues();
        registro.put("cedula",usuario.getCedula());
        registro.put("nombre",usuario.getNombre());
        registro.put("apellido",usuario.getApellido());
        registro.put("edad",usuario.getEdad());
        return conexion.ejecutarInsert("usuario",registro);
    }

    public Usuario buscar(String cedula){
        Usuario usuario = null;
        String consulta = "select nombre,apellido,edad " +"from usuario where " + "cedula="+cedula+"";
        Cursor temp = conexion.ejecutarSearch(consulta);

        if (temp.getCount() > 0){
            temp.moveToFirst();
            usuario = new Usuario(cedula,temp.getString(0),temp.getString(1),Integer.parseInt(temp.getString(2)));
        }
        conexion.cerrarConexion();
        return usuario;
    }

    public boolean eliminar(Usuario usuario){
        String tabla = "usuario";
        String condicion = "cedula=" +usuario.getCedula();
        return conexion.ejecutarDelete(tabla,condicion);
    }

    public boolean modificar(Usuario usuario){
        String tabla = "usuario";
        String condicion = "cedula=" +usuario.getCedula();
        ContentValues registro = new ContentValues();
        registro.put("nombre",usuario.getNombre());
        registro.put("apellido",usuario.getApellido());
        registro.put("edad",usuario.getEdad());
        return conexion.ejecutarUpdate(tabla,condicion,registro);
    }

    public List<Usuario> listar(){
        List<Usuario> listaUsuarios = new ArrayList<>();
        String consulta = "select cedula,nombre,apellido,edad from usuario";
        Cursor temp = conexion.ejecutarSearch(consulta);

        if (temp.moveToFirst()){
            do {
                Usuario usuario = new Usuario(temp.getString(0),temp.getString(1),temp.getString(2),Integer.parseInt(temp.getString(3)));
                listaUsuarios.add(usuario);
            }while (temp.moveToNext());
        }
        return listaUsuarios;
    }

}
