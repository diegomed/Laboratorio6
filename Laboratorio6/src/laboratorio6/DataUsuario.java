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
public class DataUsuario {
    private String nickname;
    private boolean sexo;
    private DataFecha nacimiento;
    private int edad;
    
    public DataUsuario(String nick, boolean sex, DataFecha nac) {
        int year, month, days;
        DataFecha ahora = new DataFecha();
        year = ahora.getA() - nac.getA();
        month = ahora.getMes() - nac.getMes();
        days = ahora.getD() - nac.getD();
        
        if (month > 0) {
            edad = year;
        } 
        else {
            if (month < 0) {
                edad = year-1;
            }
            else {
                if (days<0) {
                    edad = year-1;
                }
                else {
                    edad = year;
                }
            }
        }
        nickname = nick;
        sexo = sex;
        nacimiento = nac;
    }
    
    public String getnick(){
        return nickname;
    }
    public boolean getsexo(){
        return sexo;
    }
    public int getedad(){
        return edad;
    }
    public DataFecha getnac(){
        return nacimiento;
    }
}
