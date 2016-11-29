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
                    System.out.println("Formato: nombre de usuario-ubicacion del recurso (o nombre si ud se encuentra en el directorio del mismo)");
                    try{
                        inputUser = sc.nextLine();
                        String list[] = inputUser.split("-");
                        String ubicacionDelRecurso;
                        if(list[1].startsWith("./")){
                            ubicacionDelRecurso = list[1];
                        }
                        else{
                            ubicacionDelRecurso = path + "/" +list[1];
                        }
                        if(c.isColab(user.getnick(), ubicacionDelRecurso)){
                            boolean isValidColab = c.agregarColaborador(list[0], ubicacionDelRecurso);
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
                        System.out.println("input de colaborador invalido");
                    }
                    catch(NullPointerException e){
                        System.out.println("Carpeta y/o usuario no existente");
                    }
                }
                else{
                    System.out.println("Debe estar logueado para agregar colaborador");
                }
            }
            else if(inputUser.equals("ingresar comentario")){
                if(user != null){
                    System.out.println("Ingrese datos del comentario:");
                    System.out.println("Formato: comentario-ubicacion del archivo (o nombre si ud se encuentra en el directorio del mismo)");
                    try{
                        inputUser = sc.nextLine();
                        String list[] = inputUser.split("-");
                        String ubicacionDelRecurso;
                        if(list[1].startsWith("./")){
                            ubicacionDelRecurso = list[1];
                        }
                        else{
                            ubicacionDelRecurso = path + "/" +list[1];
                        }
                        c.ingresarComentario(list[0], ubicacionDelRecurso, user);
                        System.out.println("comentario ingresado exitosamente");
                    }
                    catch(ArrayIndexOutOfBoundsException e){
                        System.out.println("input de comentario invalido");
                    }
                    catch(NullPointerException e){
                        System.out.println("Archivo no existente");
                    }
                }
                else{
                    System.out.println("Debe estar logueado para ingresar comentario");
                }
            }
            else if(inputUser.equals("ver info archivo")){
                System.out.println("Ingrese ingrese la ubicacion del archivo (o el nombre si ya se encuentra en el directorio del mismo):");
                try{
                    inputUser = sc.nextLine();
                    String list[] = inputUser.split("-");
                    String ubicacionDelRecurso;
                    if(list[0].startsWith("./")){
                        ubicacionDelRecurso = list[0];
                    }
                    else{
                        ubicacionDelRecurso = path + "/" +list[0];
                    }
                    Archivos file = c.verInfoArchivo(ubicacionDelRecurso);
                    System.out.println("Nombre: " + file.getNombre());
                    System.out.println("Creador: " + file.getCreador());
                    System.out.println("Descripcion: " + file.getDescripcion());
                    System.out.println("Ubicacion: " + file.getUbicacion());
                    System.out.print("Tipo: ");
                    System.out.println((file.getTipo()) ? "Archivo" : "Carpeta");
                    System.out.println("Fecha creacion: " + file.getcreacion().getD() + "/"
                                                            + file.getcreacion().getMes() + "/"
                                                            + file.getcreacion().getA() + " - "
                                                            + file.getcreacion().getH() + ":"
                                                            + file.getcreacion().getMin());
                    System.out.println("Fecha ultimo acceso: " + file.getultimoAcceso().getD() + "/"
                                                            + file.getultimoAcceso().getMes() + "/"
                                                            + file.getultimoAcceso().getA() + " - "
                                                            + file.getultimoAcceso().getH() + ":"
                                                            + file.getultimoAcceso().getMin());
                    System.out.println("Fecha modificacion: " + file.getmodificacion().getD() + "/"
                                                            + file.getmodificacion().getMes() + "/"
                                                            + file.getmodificacion().getA() + " - "
                                                            + file.getmodificacion().getH() + ":"
                                                            + file.getmodificacion().getMin());
                    System.out.println("Comentarios: ");
                    for(Comentario com : file.getListaComentarios()){
                        System.out.print("\t");
                        System.out.print((char)27 + "[32m" + com.getUser().getnick() + ": " + com.getComentario() + (char)27 + "[0m");
                        System.out.println((char)27 + "[32m" + " - (" + com.getFechaComentario().getD() + "/"
                                                            + com.getFechaComentario().getMes() + "/"
                                                            + com.getFechaComentario().getA() + " - "
                                                            + com.getFechaComentario().getH() + ":"
                                                            + com.getFechaComentario().getMin() + ")" + (char)27 + "[0m");
                    }
                }
                catch(Exception e){
                    System.out.println("Archivo no existente");
                }
            }
            else if(inputUser.equals("ver info carpeta")){
                System.out.println("Ingrese ingrese la ubicacion de la carpeta (o el nombre si ya se encuentra en el directorio de la mismo):");
                try{
                    inputUser = sc.nextLine();
                    String list[] = inputUser.split("-");
                    String ubicacionDelRecurso;
                    if(list[0].startsWith("./") || list[0].equals(".")){
                        ubicacionDelRecurso = list[0];
                    }
                    else{
                        ubicacionDelRecurso = path + "/" +list[0];
                    }
                    Carpetas folder = c.verInfoCarpeta(ubicacionDelRecurso);
                    System.out.println("Nombre: " + folder.getNombre());
                    System.out.println("Creador: " + folder.getCreador());
                    System.out.println("Descripcion: " + folder.getDescripcion());
                    System.out.println("Ubicacion: " + folder.getUbicacion());
                    System.out.print("Tipo: ");
                    System.out.println((folder.getTipo()) ? "Archivo" : "Carpeta");
                    System.out.println("Fecha creacion: " + folder.getcreacion().getD() + "/"
                                                            + folder.getcreacion().getMes() + "/"
                                                            + folder.getcreacion().getA() + " - "
                                                            + folder.getcreacion().getH() + ":"
                                                            + folder.getcreacion().getMin());
                    System.out.println("Fecha ultimo acceso: " + folder.getultimoAcceso().getD() + "/"
                                                            + folder.getultimoAcceso().getMes() + "/"
                                                            + folder.getultimoAcceso().getA() + " - "
                                                            + folder.getultimoAcceso().getH() + ":"
                                                            + folder.getultimoAcceso().getMin());
                    System.out.println("Fecha modificacion: " + folder.getmodificacion().getD() + "/"
                                                            + folder.getmodificacion().getMes() + "/"
                                                            + folder.getmodificacion().getA() + " - "
                                                            + folder.getmodificacion().getH() + ":"
                                                            + folder.getmodificacion().getMin());
                    System.out.println("Colaboradores: ");
                    for(Colaborador colab : folder.getListaColaboradores().values()){
                        System.out.print("\t");
                        System.out.print((char)27 + "[31m" + colab.getUsuarioColaborador().getnick() + (char)27 + "[0m");
                        System.out.println((char)27 + "[31m" + " - (" + colab.getFechaAgregado().getD() + "/"
                                                            + colab.getFechaAgregado().getMes() + "/"
                                                            + colab.getFechaAgregado().getA() + " - "
                                                            + colab.getFechaAgregado().getH() + ":"
                                                            + colab.getFechaAgregado().getMin() + ")" + (char)27 + "[0m");
                    }
                    System.out.println("Contiene los recursos: ");
                    for(Recursos fol : folder.getListaRecursos().values()){
                        System.out.print("\t");
                        if(fol.getTipo()){
                            System.out.println((char)27 + "[34m" + fol.getNombre() + (char)27 + "[0m");
                        }
                        else{
                            System.out.println((char)27 + "[35m" + fol.getNombre() + (char)27 + "[0m");
                        }
                    }
                }
                catch(Exception e){
                    System.out.println("Carpeta no existente");
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
                System.out.println("ingresar comentario");
                System.out.println("ver info archivo");
                System.out.println("ver info carpeta");
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
