/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio6;
import java.util.HashMap;
import java.util.ArrayList;
/**
 *
 * @author Diego
 */
public class Carpetas extends Recursos {
    HashMap<String,Recursos> recursosIn = new HashMap<String,Recursos>();
    ArrayList<String> listaColaboradores = new ArrayList<String>();
    
    public Carpetas(String name, String creator, String description, String ubication, boolean type) {
        super(name, creator, description, ubication, type);
    }
    
    public HashMap<String,Recursos> getListaRecursos() {
        return recursosIn;
    }
    
    public ArrayList<String> getListaColaboradores() {
        return listaColaboradores;
    }
}
