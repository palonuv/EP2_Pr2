/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EP2_Pr2;
import EP2_Pr2.Usuario;
import java.util.Date;
import java.util.Random;
/**
 *
 * @author alumno
 */
public class Objeto {
    private String nombre;
    private float importe; //IMPORTE POR DIA DE ALQUILER
    private int idusuario;
    private int idobjeto;
    Date fechaini;
    Date fechafin;
    
    public Objeto(String n, float i, int idus, int idob, Date fechaini, Date fechafin){
        this.nombre = n;
        this.importe = i;
        this.idusuario = idus;
        this.idobjeto = idob;
        this.fechaini = fechaini;
        this.fechafin = fechafin;
    }

    public Objeto(){
        
    }
    
    public void setNombre(String n){
        this.nombre = n;
    }
    
    public void setImporte(float i){
        this.importe = i;
    }
    
    public void setIdUsuario(int id){
        this.idusuario = id;
    }
    
    public void setIdObjeto(int id){
        this.idobjeto = id;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public float getImporte(){
        return importe;
    }
    
    public int getIdUsuario(){
        return idusuario;
    }
    
    public int getIdObjeto(){
        return idobjeto;
    }
    
    public Date getFechaInicio(){
        return fechaini;
    }
    
    public Date getFechaFin(){
        return fechafin;
    }
    
    public void Mostrar(){
        System.out.println("Codigo del objeto: " + idobjeto);
        System.out.println("Descripcion: " + nombre);
        System.out.println("Fecha de disponibilidad: " + fechaini + " - " + fechafin);
        System.out.println("Coste del prestamo por dia: " + importe);
    }
}
