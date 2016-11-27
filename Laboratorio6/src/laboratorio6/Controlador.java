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
public class Controlador {
    private String nickname, nombre, creador, descripcion, ubicacion, colaborador, ubicacionColab;
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
    public String getColaborador(){
        return colaborador;
    }
    public String getUbicacionColab(){
        return ubicacionColab;
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
            Archivos rec = new Archivos(this.getNombre(), this.getCreador(), this.getDescripcion(), this.getUbicacion(), this.getTipo());
            if(!rec.getUbicacion().equals(".")){
                mr.agregarACarpetaContenedora(rec);
            }
            mr.agregarRecurso(rec);
        }
        else{
            Carpetas rec = new Carpetas(this.getNombre(), this.getCreador(), this.getDescripcion(), this.getUbicacion(), this.getTipo());
            if(!rec.getUbicacion().equals(".")){
                mr.agregarACarpetaContenedora(rec);
            }
            mr.agregarRecurso(rec);
        }
    }
    
    //Agregar colaborador
    public boolean addColab(String nickname, String ubicacion){
        boolean colabYaExiste = false;
        MgrRecursos mr = MgrRecursos.getInstance();
        Carpetas carpetaColaborada = (Carpetas) mr.getListaCarpetas().get(ubicacion);
        HashMap<String,Colaborador> listaColaboradores = carpetaColaborada.getListaColaboradores();
        
        for (String colaborador : listaColaboradores.keySet()) {
            if ((listaColaboradores.get(colaborador)).getUsuarioColaborador().getnick().equals(nickname)) {
                colabYaExiste = true;
            }
        }

        if(!colabYaExiste){
            colaborador = nickname;
            ubicacionColab = ubicacion;
            return true;
        }
        return false;
    }
    
    public void confirmColab(){
        MgrUsuario mu = MgrUsuario.getInstance();
        Colaborador colab = new Colaborador(mu.getUsuario(colaborador));
        MgrRecursos mr = MgrRecursos.getInstance();
        mr.agregarColaborador(colab, ubicacionColab);
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
    
    public boolean agregarColaborador(String nickname, String ubicacion){
        boolean isValidColab = this.addColab(nickname, ubicacion);
        if(!isValidColab){
            return isValidColab;
        }
        this.confirmColab();
        return isValidColab;
    }
    
    //metodos de ayuda
    public ArrayList<String> getPathList(){
        MgrRecursos mr = MgrRecursos.getInstance();
        ArrayList<String> pathList = new ArrayList<String>(mr.getListaArchivos().keySet());
        pathList.addAll(mr.getListaCarpetas().keySet());
        return pathList;
    }
    
    public ArrayList<String> getCarpetasPathList(){
        MgrRecursos mr = MgrRecursos.getInstance();
        ArrayList<String> pathList = new ArrayList<String>(mr.getListaCarpetas().keySet());
        return pathList;
    }
    
    public ArrayList<String> getArchivosPathList(){
        MgrRecursos mr = MgrRecursos.getInstance();
        ArrayList<String> pathList = new ArrayList<String>(mr.getListaArchivos().keySet());
        return pathList;
    }
    
    public boolean getRecursoType(String path){
        MgrRecursos mr = MgrRecursos.getInstance();
        return mr.getListaRecursos().get(path).getTipo();
    }
    
    public Usuario getUser(String name){
        return MgrUsuario.getInstance().getUsuario(name);
    }
    
    public boolean isColab(String user, String path){
        MgrRecursos mr = MgrRecursos.getInstance();
        Carpetas carp = (Carpetas) mr.getListaCarpetas().get(path);
        return carp.getListaColaboradores().containsKey(user);
    }
    
    public HashMap<String,Recursos> getRecursosIn(String path){
        MgrRecursos mr = MgrRecursos.getInstance();
        return mr.getRecursosIn(path);
    }
}
