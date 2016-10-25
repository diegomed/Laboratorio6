/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio6;
import java.util.Calendar;
/**
 *
 * @author Diego
 */
public class DataFecha {
        private int a,mes,d,min,h;
        public DataFecha(){
        
        Calendar now = Calendar.getInstance();
        
        a = now.get(Calendar.YEAR);
        mes = now.get(Calendar.MONTH) + 1;
        d = now.get(Calendar.DATE);
        h = now.get(Calendar.HOUR_OF_DAY);
        min = now.get(Calendar.MINUTE);
        }
        public DataFecha(int v, int w, int x, int y, int z){
            a=v;
            mes=w;
            d=x;
            h=y;
            min=z;
        }
        public DataFecha(int v, int w, int x){
            a=v;
            mes=w;
            d=x;
            h=00;
            min=00;
        }
        public int getA(){
            return a;
        }
        public int getMes(){
            return mes;
        }
        public int getD(){
            return d;
        }
        public int getH(){
            return h;
        }
        public int getMin(){
            return min;
        }
        public void setA(int x){
            a=x;
        }
        public void setMes(int x){
            mes=x;
        }
        public void setD(int x){
            d=x;
        }
        public void setH(int x){
            h=x;
        }
        public void setMin(int x){
            min=x;
        }
}
