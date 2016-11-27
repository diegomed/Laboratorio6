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
        if(r.getTipo()) {
            ListaArchivos.put(r.getUbicacion(), r);
        }
        else {
            ListaCarpetas.put(r.getUbicacion(), r);
        }
    }
    
    public void agregarColaborador(Colaborador colab, String ubicacionColab){
        Carpetas carpetaColaborada = (Carpetas)ListaCarpetas.get(ubicacionColab);
        carpetaColaborada.agregarColaborador(colab);
    }
    
    public void agregarACarpetaContenedora(Recursos rec){
        Carpetas carp = (Carpetas) this.getListaCarpetas().get(rec.getUbicacion().substring(0, rec.getUbicacion().lastIndexOf("/")));
        carp.agregarRecursoIn(rec);
    }
    
    public HashMap<String,Recursos> getRecursosIn(String path){
        Carpetas carp = (Carpetas) this.getListaCarpetas().get(path);
        return carp.getListaRecursos();
    }
    
    public HashMap<String,Recursos> getListaArchivos() {
        return ListaArchivos;
    }
    
    public HashMap<String,Recursos> getListaCarpetas() {
        return ListaCarpetas;
    }
    
    public HashMap<String,Recursos> getListaRecursos() {
        HashMap<String,Recursos> ListaRecursos = new HashMap<String,Recursos>();
        ListaRecursos.putAll(ListaArchivos);
        ListaRecursos.putAll(ListaCarpetas);
        return ListaRecursos;
    }
}
