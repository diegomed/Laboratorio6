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
        //System.out.println((char)27 + "[35mThis text would show up red" + (char)27 + "[0m");
        String path = ".";
        Controlador c = new Controlador();
        c.crearRecurso("root", "system", "Esta es la carpeta raiz", path, false);
        Scanner sc = new Scanner(System.in);
        while(true) {
            
            System.out.print(path + ">");
            String inputUser = sc.nextLine();
            
            if(inputUser.equals("alta usuario")) {
                System.out.println("Ingrese datos del usuario:");
                System.out.println("Formato: nombre-sexo(m/f)-dia-mes(nro)-año");
                try{
                    inputUser = sc.nextLine();
                    String list[] = inputUser.split("-");
                    DataFecha nacimientoUsuario = new DataFecha(Integer.parseInt(list[4]),Integer.parseInt(list[3]),Integer.parseInt(list[2]));
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
                System.out.println("Formato: nombre-creador-descripcion-tipo(archivo/carpeta)");
                try{
                    inputUser = sc.nextLine();
                    String list[] = inputUser.split("-");
                    boolean isValidPath = c.crearRecurso(list[0], list[1], list[2], path + "/" + list[0], list[3].equals("archivo"));
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
            else if(inputUser.startsWith("cd ")){
                String cmdPath = inputUser.substring(3);
                String tempPath = ".";
                try{
                    if(cmdPath.startsWith(".") && !cmdPath.equals("..")){
                        tempPath = cmdPath;
                    }
                    else if(cmdPath.equals("..")){
                        tempPath = path.substring(0, path.lastIndexOf("/"));
                    }
                    else{
                        tempPath = path + "/" + cmdPath;
                    }
                }
                catch(Exception e){
                    System.out.println("No puedes salir del directorio raiz");
                }
                ArrayList<String> carpetasPathList = c.getCarpetasPathList();
                boolean doesPathExist = false;
                
                for(String carpetaPath : carpetasPathList){
                    if(carpetaPath.equals(tempPath)){
                        doesPathExist = true;
                    }
                }
                
                if(doesPathExist){
                    path = tempPath;
                }
                else{
                    System.out.println("Ubicacion no existente");
                }
            }
            else if(inputUser.equals("dir")){
                ArrayList<String> PathList = c.getPathList();
                for(String recursoPath : PathList){
                    if(recursoPath.startsWith(path) && !recursoPath.equals(path)){
                        String tempPath = recursoPath.substring(path.length() + 1);
                        if(tempPath.indexOf("/") == -1){
                            if(c.getRecursoType(recursoPath)){
                                System.out.println((char)27 + "[34m" + recursoPath.substring(path.length() + 1) + (char)27 + "[0m");
                            }
                            else{
                                System.out.println((char)27 + "[35m" + recursoPath.substring(path.length() + 1) + (char)27 + "[0m");
                            }
                        }
                    }
                }
            }
            else if(inputUser.equals("cmd")){
                System.out.println("alta usuario");
                System.out.println("ver info usuario");
                System.out.println("crear recurso");
                System.out.println("cd");
            }
            else{
                System.out.println("comando invalido");
            }
        }
    } 
}
