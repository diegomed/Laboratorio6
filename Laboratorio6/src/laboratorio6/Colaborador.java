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
public class Colaborador {
    private Usuario colaborador;
    private DataFecha fechaAgregado;
    
    public Colaborador(Usuario u){
        DataFecha now = new DataFecha();
        colaborador = u;
        fechaAgregado = now;
    }
    
    public Usuario getUsuarioColaborador(){
        return colaborador;
    }
    
    public DataFecha getFechaAgregado(){
        return fechaAgregado;
    }
}
