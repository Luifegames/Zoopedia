/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.de.conocimiento;

import java.util.Map;
import org.jpl7.Query;
import org.jpl7.Term;

/**
 *
 * @author LUISF
 */
public class BaseDeConocimiento {
   
    private final String t1 = "consult('sbc.pl')";
    private Query q1;
      
    
    public BaseDeConocimiento() {
        q1 = new Query(t1);
        q1.hasSolution();
    }

   
    public String getImage(String animal){
        
        return animal + ".png";
    }
    
    private String spacialChar(String text){
        char []sc = {'á','é','í','ó','ù'};
        char [] c = {'a','e','i','o','o'};
        
        for (int i = 0; i < c.length; i++) {
            text = text.replace(sc[i], c[i]);
        }
        
        return text;
    }
    public  Map<String, Term> random(){
               String consulta =  "clasifica(N,C,T,D)";
               Query q2 = new Query(consulta);
               Map<String, Term>[] accion = q2.allSolutions();
               System.out.println(accion.length);
               int i = (int) (Math.random() * accion.length);
               
               return accion[i];
            }
    public Map<String, Term> consulta(String animal) throws NoEncontradoException{
        
        if (animal.contains(" ")){
            animal = "'" + animal + "'";
        }
        
        String consulta = "clasifica("+ spacialChar(animal).trim().toLowerCase() +",C,T,D)";
        Query q2 = new Query(consulta);
        if (q2.hasSolution())
        {
        Map<String, Term> accion = q2.oneSolution();
        
        return accion;
        }
        else{
            throw new NoEncontradoException();
        }
    }
    
    
  /*  public static void main(String[] args) {
        BaseDeConocimiento bdc = new BaseDeConocimiento();
        
        System.out.println(bdc.consulta("leon").get("C"));
        }
    */
}
