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
public class Usuario {
    private String nickname;
    private boolean sexo;
    private DataFecha nacimiento;
    private int edad;
    
    public Usuario(String nick, boolean sex, DataFecha nac) {
        int year, month, days;
        /*DataFecha ahora = new DataFecha();
        year = ahora.getA() - nacimiento.getA();
        month = ahora.getMes() - nacimiento.getMes();
        days = ahora.getD() - nacimiento.getD();
        
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
        }*/
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
    
    public void setnick(String x){
        nickname=x;
    }
    public void setsexo(boolean x){
        sexo=x;
    }
    public void setnac(DataFecha x){
        nacimiento=x;
        //edad=x-current_date();
        }    
    public void setedad(int x){
        edad=x;
    }
    
    public DataUsuario getUserData() {
        DataUsuario du = new DataUsuario(this.getnick(), this.getsexo(), this.getnac());
        return du;
    }
}
