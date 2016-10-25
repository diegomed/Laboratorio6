/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio6;

import java.util.HashMap;

/**
 *
 * @author Diego
 */
public class Recursos {
    String nombre, creador, descripcion, ubicacion;
    DataFecha creacion, ultimoAcceso, modificacion;
    boolean tipo;
    
    public Recursos(String name, String creator, String description, String ubication, boolean type) {
        DataFecha now = new DataFecha();
        nombre = name;
        creador = creator;
        descripcion = description;
        ubicacion = ubication;
        tipo = type;
        creacion = now;
        ultimoAcceso = now;
        modificacion = now;
    }
    
    public String getNombre(){
        return nombre;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public String getCreador(){
        return creador;
    }
    public String getUbicacion(){
        return ubicacion;
    }
    public boolean getTipo(){
        return tipo;
    }
    public DataFecha getcreacion(){
        return creacion;
    }
    public DataFecha getmodificacion(){
        return modificacion;
    }
    public DataFecha getultimoAcceso(){
           return ultimoAcceso;
    }
    
    
}
