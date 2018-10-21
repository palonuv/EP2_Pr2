/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EP2_Pr2;
import EP2_Pr2.Objeto;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author alumno
 */
public class Usuario {
    private String nombre;
    private String correo;
    private int id;
    //Falta lista de objetos del usuario???
    
    public Usuario(String n, String c, int id){
        this.nombre = n;
        this.correo = c;
        //FALTA COMPROBAR EL FORMATO DEL CORREO
        this.id = id;
    }
    
    public Usuario(){
        
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getCorreo(){
        return correo;
    }
    
    public int getId(){
        return id;
    }
    
    public void setNombre(String n){
        this.nombre = n;
    }
    
    public void setCorreo(String c){
        this.correo = c;
        //FALTA COMPROBAR EL FORMATO DEL CORREO
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void Mostrar(){
        System.out.println("PROPIETARIO " + id);
        System.out.println("Nombre del propietario: " + nombre);
        System.out.println("Correo Electrónico: " + correo);
    }
}
