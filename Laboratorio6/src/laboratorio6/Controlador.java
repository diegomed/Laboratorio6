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
    
    
    public Controlador() {}
    
    //Getters
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
    
    //alta usuario
    public boolean newUserNick(String nick) {
        boolean resultado = true;
        MgrUsuario manager = MgrUsuario.getInstance();
        HashMap<String,Usuario> List = manager.getLista();
        
        for (String name : List.keySet()) {
            if ((List.get(name)).getnick().equals(nick)) {
                resultado = false;
            }
        }
        
        if (resultado) {
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
    
    //ver info usuario
    public DataUsuario infoUser(String nick) {
        MgrUsuario mu = MgrUsuario.getInstance();
        Usuario u = mu.getUsuario(nick);
        DataUsuario du = u.getUserData();
        return du;
    }
    
    //Crear recurso
    public boolean newRec(String name, String description,String creator, String ubication, boolean type) {
        boolean resultado = true;
        MgrRecursos manager = MgrRecursos.getInstance();
        HashMap<String,Recursos> List = (type) ? manager.getListaArchivos() : manager.getListaCarpetas();
        
        for (String resourcePath : List.keySet()) {
            if ((List.get(resourcePath)).getUbicacion().equals(ubication)) {
                resultado = false;
            }
        }
        
        if (resultado) {
            nombre = name;
            descripcion = description;
            tipo = type;
            creador = creator;
            ubicacion = ubication;
            return true;
        }
        return false;
    }
    
    public void confirmRec() {
        MgrRecursos mr = MgrRecursos.getInstance();
        if(this.getTipo()) {
            Recursos rec = new Archivos(this.getNombre(), this.getCreador(), this.getDescripcion(), this.getUbicacion(), this.getTipo());
            mr.agregarRecurso(rec);
        }
        else{
            Recursos rec = new Carpetas(this.getNombre(), this.getCreador(), this.getDescripcion(), this.getUbicacion(), this.getTipo());
            mr.agregarRecurso(rec);
        }
    }
    
    //Comandos
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
    
    public boolean crearRecurso(String name, String creator, String description, String ubication, boolean type){
        boolean isValidResource = this.newRec(name, description,creator,ubication, type);
        if(!isValidResource){
            return isValidResource;
        }
        this.confirmRec();
        return isValidResource;
    }
}
