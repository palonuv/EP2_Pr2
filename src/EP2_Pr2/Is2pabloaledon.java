/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EP2_Pr2;
import EP2_Pr2.Objeto;
import EP2_Pr2.Usuario;
import EP2_Pr2.Alquiler;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * //jorge.esteve@uv.es
 * @author alumno
 */
public class Is2pabloaledon {
    private static int idusuarios = 1;
    private static int idobjetos = 1;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ParseException {
        // TODO code application logic here
        Scanner teclado;
        int opcion;
        HashSet<Usuario> listaUsuarios = new HashSet<Usuario>();
        HashSet<Objeto> listaObjetos = new HashSet<Objeto>();
        HashSet<Alquiler> listaAlquileres = new HashSet<Alquiler>();
        
        
        System.out.println("------ MENU ------\n");
        System.out.println("Introduzca el numero de la opcion que desea escoger\n");
        System.out.println("1- Alta de usuario\n2- Alta objeto\n3- Alquiler objeto");
        System.out.println("4- Listar todos los objetos\n5- Baja de objeto\n6- Mostrar saldos\n7- Modificar importe objeto\n8- Guardar saldos\n9- Salir");
        
        
        do{
            teclado = new Scanner(System.in);
            opcion = teclado.nextInt();
            switch(opcion){
                case 1:
                    System.out.println("Alta usuario\n");
                    Usuario x = AltaUsuario(idusuarios);
                    listaUsuarios.add(x);
                    idusuarios++;
                    break;
                case 2:
                    System.out.println("Alta objeto\n");
                    Objeto o = AltaObjeto(listaUsuarios, idobjetos);
                    listaObjetos.add(o);
                    idobjetos++;
                    break;
                case 3:
                    System.out.println("Alquiler objeto\n");
                    Alquiler a = AlquilerObjeto(listaUsuarios,listaObjetos);
                    listaAlquileres.add(a);
                    break;
                case 4:
                    System.out.println("Listar todos los objetos\n");
                    ListarTodo(listaUsuarios,listaObjetos,listaAlquileres);
                    break;
                case 5:
                    System.out.println("Baja de objeto\n");
                    BajaObjeto(listaObjetos);
                    break;
                case 6:
                    System.out.println("Mostrar saldos\n");
                    MostrarSaldos(listaAlquileres);
                    break;
                case 7:
                    System.out.println("Modificar importe objeto\n");
                    listaObjetos = ModificarImporteObjeto(listaObjetos);
                    break;
                case 8:
                    System.out.println("Guardar saldos\n");
                    GuardarSaldos(listaAlquileres);
                    break;
                case 9:
                    System.out.println("Salir\n");
                    break;
            }        
        }while(opcion != 9);
    } 
    
    public static Usuario AltaUsuario(int id){
        Scanner teclado;
        
        System.out.println("Introduzca el nombre del usuario: ");
        teclado = new Scanner(System.in);
        String n = teclado.nextLine();
        String c = new String();
        do{
            System.out.println("Introduzca un correo: ");
            teclado = new Scanner(System.in);
            c = teclado.nextLine();
        }while(!validarEmailSimple(c));
        Usuario x = new Usuario(n, c, id);
        
        return x;
    }
    
    private static boolean validarEmailSimple(String email){

        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
    
    public static Objeto AltaObjeto(HashSet<Usuario> listaUsuarios, int idobjeto) throws ParseException{
        Scanner teclado;
        int idusuario;
        //1-MOSTRAR LISTADO DE USUARIOS DADOS DE ALTA
        ListarUsuarios(listaUsuarios);
        //2-INTRODUCIR POR TECLADO EL ID DEL USUARIO
        System.out.println("Introduzca el id del usuario: ");
        teclado = new Scanner(System.in);
        idusuario = teclado.nextInt();
        //3-DESCRIPCION DEL OBJETO
        System.out.println("Introduzca el nombre del objeto: ");
        teclado = new Scanner(System.in);
        String n = teclado.nextLine();
        //4-FECHAS
        System.out.println("Introduzca la fecha inicio: ");
        Date date1 = new Date();
        Date date2 = new Date();
        try{
            teclado = new Scanner(System.in);
            String d1 = teclado.nextLine();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            date1 = formatoFecha.parse(d1);
        }catch(InputMismatchException ex){System.out.println("Fecha equivocada, prueba de nuevo");}
        
        try{
            System.out.println("Introduzca la fecha fin: ");
            teclado = new Scanner(System.in);
            String d2 = teclado.nextLine();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            date2 = formatoFecha.parse(d2);
        }catch(InputMismatchException ex){System.out.println("Fecha equivocada, prueba de nuevo");}
        //5-COSTE
        System.out.println("Introduzca el importe del objeto: ");
        teclado = new Scanner(System.in);
        float c = teclado.nextInt();
        
        Objeto o = new Objeto(n,c,idusuario,idobjeto,date1,date2);
        
        return o;
    }
    
    public static void BajaObjeto(HashSet<Objeto> listaObjetos){
        boolean encontrado = false;
        int i = 0;
        int idob;
        
        ListarObjetos(listaObjetos);
        
        Iterator<Objeto> iteratorObjetos = listaObjetos.iterator();
            
        System.out.println("Selecciona el identficador del objeto que deseas dar de baja: ");
        Scanner sc = new Scanner(System.in);
        idob = sc.nextInt();

        while(iteratorObjetos.hasNext() && !encontrado){
            if(iteratorObjetos.next().getIdObjeto() == idob){
                encontrado = true;
                iteratorObjetos.remove();
            }
        }
        
    }
    
    public static void MostrarSaldos(HashSet<Alquiler> listaAlquileres){
        Iterator<Alquiler> iteratorAlquileres = listaAlquileres.iterator();
        int saldo = 0;
        
        while(iteratorAlquileres.hasNext()){
            saldo += iteratorAlquileres.next().getImporteTotal();
        }
        
        System.out.println("Saldo total usuario = " + saldo);
        System.out.println("Saldo total Startup = " + saldo*0.1);
    }
    
    public static void ListarUsuarios(HashSet<Usuario> listaUsuarios){
        Iterator<Usuario> iteratorUsuarios = listaUsuarios.iterator();
        while(iteratorUsuarios.hasNext()){
            iteratorUsuarios.next().Mostrar();
        }
    }
    
    public static void ListarObjetos(HashSet<Objeto> listaObjetos){
        Iterator<Objeto> iteratorObjetos = listaObjetos.iterator();
        while(iteratorObjetos.hasNext()){
            iteratorObjetos.next().Mostrar();
        }
    }
    
    public static Alquiler AlquilerObjeto(HashSet<Usuario> listaUsuarios, HashSet<Objeto> listaObjetos) throws ParseException{
        Scanner teclado;
        Alquiler x = new Alquiler();
        boolean enc = false;
        
        ListarUsuarios(listaUsuarios);
        System.out.println("Introduce el id del usuario que va a alquilar: ");
        teclado = new Scanner(System.in);
        int idus = teclado.nextInt();
        ListarObjetos(listaObjetos);
        System.out.println("Introduce el id del objeto que quiere alquilar: ");
        teclado = new Scanner(System.in);
        int idob = teclado.nextInt();
        
        System.out.println("Introduzca la fecha inicio: ");
        teclado = new Scanner(System.in);
        String d1 = teclado.nextLine();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = formatoFecha.parse(d1);
        System.out.println("Introduzca la fecha fin: ");
        teclado = new Scanner(System.in);
        String d2 = teclado.nextLine();
        Date date2 = formatoFecha.parse(d2);
        
        Iterator<Objeto> iteratorObjetos = listaObjetos.iterator();
        while(iteratorObjetos.hasNext() && !enc){
            Objeto o = new Objeto();
            o = iteratorObjetos.next();
            if(o.getIdObjeto() == idob){
                if(date1.after(o.getFechaInicio()) && date2.before(o.getFechaFin())){
                    //ALQUILER
                    int dias = (int) ((date2.getTime() - date1.getTime())/86400000);
                    float coste = dias * o.getImporte();
                    x = new Alquiler(o, idus,coste,date1,date2);
                    enc = true;
                }
            }
        } 
        return x;
    }
    
    public static void ListarTodo(HashSet<Usuario> listaUsuarios, HashSet<Objeto> listaObjetos, HashSet<Alquiler> listaAlquileres){
        Iterator<Usuario> iteratorUsuarios = listaUsuarios.iterator();
        while(iteratorUsuarios.hasNext()){
            Usuario u = new Usuario();
            u = iteratorUsuarios.next();
            u.Mostrar();
            ListarObjetosUsuario(u,listaObjetos, listaAlquileres);
        }
    }
    
    public static void ListarObjetosUsuario(Usuario u, HashSet<Objeto> listaObjetos, HashSet<Alquiler> listaAlquileres){
        Iterator<Objeto> iteratorObjetos = listaObjetos.iterator();
        while(iteratorObjetos.hasNext()){
            Objeto o = new Objeto();
            o = iteratorObjetos.next();
            if(o.getIdUsuario() == u.getId()){
                o.Mostrar();
                if(!MostrarAlquileresObjeto(o,listaAlquileres)){
                    System.out.println("El objeto no tiene prestamos");
                }
            }
        }
    }
    
    public static boolean MostrarAlquileresObjeto(Objeto o, HashSet<Alquiler> listaAlquileres){
        boolean enc = false;
        
        Iterator<Alquiler> iteratorAlquileres = listaAlquileres.iterator();
        while(iteratorAlquileres.hasNext()){
            Alquiler a = new Alquiler();
            a = iteratorAlquileres.next();
            if(o.getIdObjeto() == a.getObjeto().getIdObjeto()){
                enc = true;
                a.Mostrar();                
            }
        }
        return enc;
    }
    
    public static HashSet<Objeto> ModificarImporteObjeto(HashSet<Objeto> listaObjetos){
        Scanner teclado;
        int idobjeto;
        HashSet<Objeto> aux = new HashSet<Objeto>();
        
        ListarObjetos(listaObjetos);
        System.out.println("Introduce el id del objeto: ");
        teclado = new Scanner(System.in);
        idobjeto = teclado.nextInt();
        
        Iterator<Objeto> iteratorObjetos = listaObjetos.iterator();
        while(iteratorObjetos.hasNext()){
            Objeto o = new Objeto();
            o = iteratorObjetos.next();
            if(o.getIdObjeto() == idobjeto){
                o.Mostrar();
                System.out.println("Introduce el nuevo importe del objeto: ");
                teclado = new Scanner(System.in);
                int coste = teclado.nextInt();
                o.setImporte(coste);
            }
            aux.add(o);
        }
        
        return aux;
    }
    
    public static void GuardarSaldos(HashSet<Alquiler> listaAlquileres) throws IOException{
        Iterator<Alquiler> iteratorAlquileres = listaAlquileres.iterator();
        int saldo = 0;
        
        while(iteratorAlquileres.hasNext()){
            saldo += iteratorAlquileres.next().getImporteTotal();
        }

        Path path = Paths.get("saldos.txt");
        Charset utf8 = StandardCharsets.UTF_8;
        try (BufferedWriter w = Files.newBufferedWriter(path, utf8)){
            w.write("Saldo total usuarios: " + saldo);
            w.newLine();
            w.write("Saldo total Startup: " + saldo*0.1);
            w.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}