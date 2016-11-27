/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio6;
import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public class Archivos extends Recursos {
    ArrayList<Comentario> ListaComentarios = new ArrayList<Comentario>();
    
    public Archivos(String name, String creator, String description, String ubication, boolean type) {
        super(name, creator, description, ubication, type);
    }
    
    public ArrayList<Comentario> getListaComentarios(){
        return ListaComentarios;
    }
    
    public void agregarComentario(Comentario coment){
        ListaComentarios.add(coment);
    }
}
