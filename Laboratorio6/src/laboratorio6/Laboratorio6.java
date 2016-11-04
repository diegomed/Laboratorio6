/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/**
 *
 * @author Diego
 */
public class Laboratorio6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("cmd: para ver la lista de comandos");
        Controlador c = new Controlador();
        Scanner sc = new Scanner(System.in);
        while(true) {
            
            String inputUser = sc.nextLine();
            
            if(inputUser.equals("alta usuario")) {
                System.out.println("Ingrese datos del usuario:");
                System.out.println("Formato: nombre-sexo(m/f)-dia-mes(nro)-año");
                try{
                    inputUser = sc.nextLine(); //investigar try catch
                    String list[] = inputUser.split("-");
                    DataFecha nacimientoUsuario = new DataFecha(Integer.parseInt(list[4]),Integer.parseInt(list[3]),Integer.parseInt(list[2]));
                    //DataFecha martin = new DataFecha(1993,9,17);
                    boolean isValidName = c.altaUsuario(list[0], list[1].equals("m"), nacimientoUsuario);
                    if(isValidName){
                        System.out.println("alta usuario exitosa");
                    }
                    else{
                        System.out.println("usuario ya existente");
                    }
                }
                catch(Exception e){
                    System.out.println("Input de usuario invalido");
                }
                //c.altaUsuario("martin", true, martin);
                /*DataUsuario juans = c.verInfoUsuario("juan");
                DataUsuario martins = c.verInfoUsuario("martin");
                System.out.println(juans.getnick());
                System.out.println(juans.getsexo());
                System.out.println(martins.getnick());
                System.out.println(martins.getsexo());*/
                /*MgrUsuario mu = MgrUsuario.getInstance();
                System.out.println(mu.getLista());
                System.out.println(list[0]);
                System.out.println(list[1]);*/

                /*HashMap<String,Recursos> ListaArchivos = new HashMap<String,Recursos>();
                Archivos a = new Archivos("presupuesto", "juan", "soy un archivo", "C:/programfiles",true);
                ListaArchivos.put(a.getNombre(), a);
                System.out.println(ListaArchivos.get("presupuesto").getNombre());*/
            }
            else if(inputUser.equals("ver info usuario")){
                System.out.println("Nombre del usuario:");
                try{
                    inputUser = sc.nextLine();
                    DataUsuario infoUsuario = c.verInfoUsuario(inputUser);
                    System.out.println("Nombre: " + infoUsuario.getnick());
                    System.out.print("Sexo: ");
                    System.out.println((infoUsuario.getsexo()) ? "M" : "F");
                    System.out.println("Edad: " + infoUsuario.getedad());
                }
                catch(Exception e){
                    System.out.println("Usuario no existente");
                }
            }
            else if(inputUser.equals("crear recurso")){
                System.out.println("Ingrese datos del recurso:");
                System.out.println("Formato: nombre-creador-descripcion-ubicacion-tipo(archivo/carpeta)");
                try{
                    inputUser = sc.nextLine();
                    String list[] = inputUser.split("-");
                    boolean isValidPath = c.crearRecurso(list[0], list[1], list[2], list[3], list[4].equals("archivo"));
                    if(isValidPath){
                        System.out.println("Creación de recurso exitosa");
                    }
                    else{
                        System.out.println("El recurso ya existe en ese directorio");
                    }
                }
                catch(Exception e){
                    System.out.println("Input de recurso invalido");
                }
            }
            else if(inputUser.equals("cmd")){
                System.out.println("alta usuario");
                System.out.println("ver info usuario");
                System.out.println("crear recurso");
            }
            else{
                System.out.println("comando invalido");
            }
        }
    } 
}
