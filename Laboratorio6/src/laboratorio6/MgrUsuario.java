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
public class MgrUsuario {
    private static MgrUsuario instance = null;
    private HashMap<String,Usuario> ListaUsuarios = new HashMap<String,Usuario>();
    
    public MgrUsuario() {
        
    }
    
    public static MgrUsuario getInstance() {
        if(instance == null) {
            instance = new MgrUsuario();
        }
        return instance;
    }
    
    public HashMap<String,Usuario> getLista() {
        return ListaUsuarios;
    }
    
    public void agregarUsuario(Usuario unUsuario) {
        ListaUsuarios.put(unUsuario.getnick(),unUsuario);
    }
    
    public Usuario getUsuario(String name) {
        return ListaUsuarios.get(name);
    }
}
