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
public class MgrRecursos {
    private static MgrRecursos instance = null;
    private HashMap<String,Recursos> ListaArchivos = new HashMap<String,Recursos>();
    private HashMap<String,Recursos> ListaCarpetas = new HashMap<String,Recursos>();
    
    public MgrRecursos() {
        
    }
    
    public static MgrRecursos getInstance() {
        if(instance == null) {
            instance = new MgrRecursos();
        }
        return instance;
    }
    
    public void agregarRecurso(Recursos r) {
        if(r.getTipo() == true) {
            ListaArchivos.put(r.getNombre(), r);
        }
        else {
            ListaCarpetas.put(r.getNombre(), r);
        }
    }
    
    public HashMap<String,Recursos> getListaArchivos() {
        return ListaArchivos;
    }
    
    public HashMap<String,Recursos> getListaCarpetas() {
        return ListaCarpetas;
    }
}
