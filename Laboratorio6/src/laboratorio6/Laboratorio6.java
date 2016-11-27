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
        String path = ".";
        Controlador c = new Controlador();
        c.crearRecurso("root", "system", "Esta es la carpeta raiz", path, false);
        Usuario user = null;
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
                        user = c.getUser(list[0]);
                        System.out.println("alta usuario exitosa");
                        System.out.println("actualmente logueado como " + user.getnick());
                        c.agregarColaborador(user.getnick(), ".");
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
                if(user != null){
                    System.out.println("Ingrese datos del recurso:");
                    System.out.println("Formato: nombre-descripcion-tipo(archivo/carpeta)");
                    try{
                        inputUser = sc.nextLine();
                        String list[] = inputUser.split("-");
                        if(c.isColab(user.getnick(), path)){
                            boolean isValidPath = c.crearRecurso(list[0], user.getnick(), list[1], path + "/" + list[0], list[2].equals("archivo"));
                            if(isValidPath){
                                System.out.println("Creación de recurso exitosa");
                                if(!c.getRecursoType(path + "/" + list[0])){
                                    c.agregarColaborador(user.getnick(), path + "/" + list[0]);
                                }
                            }
                            else{
                                System.out.println("El recurso ya existe en ese directorio");
                            }
                        }
                        else{
                            System.out.println("Usted no es colaborador del directorio, no puede agregar recursos a el");
                        }
                    }
                    catch(Exception e){
                        System.out.println("Input de recurso invalido");
                    }
                }
                else{
                    System.out.println("Debe estar logueado para crear un recurso");
                }
            }
            else if(inputUser.equals("agregar colaborador")){
                if(user != null){
                    System.out.println("Ingrese datos del colaborador:");
                    System.out.println("Formato: nombre de usuario-ubicacion del recurso");
                    try{
                        inputUser = sc.nextLine();
                        String list[] = inputUser.split("-");
                        if(c.isColab(user.getnick(), list[1])){
                            boolean isValidColab = c.agregarColaborador(list[0], list[1]);
                            if(isValidColab){
                                System.out.println("colaborador agregado");
                            }
                            else{
                                System.out.println("colaborador ya existe para ese directorio");
                            }
                        }
                        else{
                            System.out.println("Usted no es colaborador de ese directorio, no puede agregar colaboradores a el");
                        }
                    }
                    catch(ArrayIndexOutOfBoundsException e){
                        System.out.println("Comando invalido");
                    }
                    catch(NullPointerException e){
                        System.out.println("Carpeta y/o usuario no existente");
                    }
                }
                else{
                    System.out.println("Debe estar logueado para agregar colaborador");
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
                HashMap<String,Recursos> ListaRecursosIn = c.getRecursosIn(path);
                for(Recursos recursoIn : ListaRecursosIn.values()){
                    if(c.getRecursoType(recursoIn.getUbicacion())){
                        System.out.println((char)27 + "[34m" + recursoIn.getNombre() + (char)27 + "[0m");
                    }
                    else{
                        System.out.println((char)27 + "[35m" + recursoIn.getNombre() + (char)27 + "[0m");
                    }
                }
                /*ArrayList<String> PathList = c.getPathList();
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
                }*/
            }
            else if(inputUser.equals("log in")){
                if(user == null){
                    System.out.println("Ingrese su nombre de usuario:");
                    try{
                        inputUser = sc.nextLine();
                        user = c.getUser(inputUser);
                        System.out.println("Logueado como " + user.getnick());
                    }
                    catch(Exception e){
                        System.out.println("Usuario no existente");
                    }
                }
                else{
                    System.out.println("Ya esta logueado como " + user.getnick());
                }
            }
            else if(inputUser.equals("log out")){
                if(user != null){
                    user = null;
                    System.out.println("Se ha deslogueado");
                }
                else{
                    System.out.println("Actualmente no hay ningun usuario logueado");
                }
            }
            else if(inputUser.equals("cmd")){
                System.out.println("alta usuario");
                System.out.println("ver info usuario");
                System.out.println("crear recurso");
                System.out.println("agregar colaborador");
                System.out.println("cd");
                System.out.println("dir");
                System.out.println("log in");
                System.out.println("log out");
                System.out.println("exit");
            }
            else if(inputUser.equals("exit")){
                break;
            }
            else{
                System.out.println("comando invalido");
            }
        }
    } 
}
