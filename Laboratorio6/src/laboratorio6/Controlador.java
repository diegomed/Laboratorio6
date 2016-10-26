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
public class Controlador {
    private String nickname, nombre, creador, descripcion, ubicacion;
    private DataFecha nacimiento;
    private boolean sexo, tipo;
    
    
    public Controlador() {
        
    }
    
    public boolean newUserNick(String nick) {
        boolean resultado = true;
        MgrUsuario manager = MgrUsuario.getInstance();
        HashMap<String,Usuario> List = manager.getLista();
        
        for (String name : List.keySet()) {
            if ((List.get(name)).getnick().equals(nick)) {
                resultado = false;
            }
        }
        
        if (resultado == true) {
            nickname = nick;
            return true;
        }
        return false;
        
    }
    
    public void newUserData(boolean sex, DataFecha nac) {
        sexo = sex;
        nacimiento = nac;
    }
    
    public void confirmUser() {
        MgrUsuario mu = MgrUsuario.getInstance();
        Usuario u = new Usuario(this.getnick(), this.getsexo(), this.getnac());
        mu.agregarUsuario(u);
    }
    
    public String getnick(){
        return nickname;
    }
    public boolean getsexo(){
        return sexo;
    }
    public DataFecha getnac(){
        return nacimiento;
    }
    public String getNombre() {
        return nombre;
    }
    public String getCreador() {
        return creador;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getUbicacion() {
        return ubicacion;
    }
    public boolean getTipo() {
        return tipo;
    }
    
    public DataUsuario infoUser(String nick) {
        MgrUsuario mu = MgrUsuario.getInstance();
        Usuario u = mu.getUsuario(nick);
        DataUsuario du = u.getUserData();
        return du;
    }
    
    public void newRec(String name, String description, boolean type) {
        nombre = name;
        descripcion = description;
        tipo = type;
    }
    
    public void recData(String creator, String ubication) {
        creador = creator;
        ubicacion = ubication;
    }
    
    public void confirmRec() {
        MgrRecursos mr = MgrRecursos.getInstance();
        if(tipo == true) {
            Archivos rec = new Archivos(this.getNombre(), this.getCreador(), this.getDescripcion(), this.getUbicacion(), this.getTipo());
        }
        else{
            Carpetas rec = new Carpetas(this.getNombre(), this.getCreador(), this.getDescripcion(), this.getUbicacion(), this.getTipo());
        }
        Archivos rec = new Archivos(this.getNombre(), this.getCreador(), this.getDescripcion(), this.getUbicacion(), this.getTipo());
        mr.agregarRecurso(rec);
    }
    
    public boolean altaUsuario(String nick, boolean sex, DataFecha nac) {
        boolean isValidName = this.newUserNick(nick);
        if(!isValidName){
            return isValidName;
        }
        this.newUserData(sex, nac);
        this.confirmUser();
        return isValidName;
    }
    
    public DataUsuario verInfoUsuario(String nick) {
        DataUsuario du = this.infoUser(nick);
        return du;
    }
}
