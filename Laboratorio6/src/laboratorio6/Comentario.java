/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio6;

/**
 *
 * @author Diego
 */
public class Comentario {
    private String comentario;
    private Usuario user;
    private DataFecha fechaComentario;
    
    public Comentario(String coment, Usuario u){
        DataFecha now = new DataFecha();
        comentario = coment;
        user = u;
        fechaComentario = now;
    }
    
    public String getComentario(){
        return comentario;
    }
    
    public Usuario getUser(){
        return user;
    }
    
    public DataFecha getFechaComentario(){
        return fechaComentario;
    }
}
