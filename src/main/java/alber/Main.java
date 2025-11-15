package alber;


/*Cabe recalcar que es el mismo ejercicio de la agenda, simplemente quitando la carga y guardado de datos por fichero
* de texto, y pasando a json y XML*/
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        /*DEFINICION DE VARIABLES*/
        boolean salir = false;
        int eleccion;
        String dni;
        String nombre;
        long telefono;
        /*DEFINICION DE VARIABLES*/

        Scanner sc = new Scanner(System.in);
        Agenda agenda = new Agenda();
        agenda.cargarDatosJSON(); //(AQUI PRIMERO HAREMOS LA CARGA DE DATOS)
        //bucle con el menu
        while (!salir){
            System.out.println("\n1--------------------------------------- Agregar persona");
            System.out.println("2--------------------------------------- Buscar persona");
            System.out.println("3--------------------------------------- Eliminar persona");
            System.out.println("4--------------------------------------- Mostrar agenda");
            System.out.println("5--------------------------------------- Guardar y salir");
            System.out.println("\n Introduce la opción deseada");
            eleccion = sc.nextInt();
            //PARA LIMPIAR EL BUFFER Y QUE NO COJA LO SUELTO AHI, SOLO PARA NUMEROS YA QUE NO HAY SALTO DE LINEA
            sc.nextLine();

            switch (eleccion){
                //agregar
                case 1:
                    System.out.println("Introduce el dni de la persona");
                    dni = sc.nextLine();

                    System.out.println("Introduce el nombre de la persona");
                    nombre = sc.nextLine();

                    System.out.println("Introduce el telefono de la persona");
                    telefono = sc.nextLong();
                    //PARA LIMPIAR EL BUFFER Y QUE NO COJA LO SUELTO AHI, SOLO PARA NUMEROS YA QUE NO HAY SALTO DE LINEA
                    sc.nextLine();

                    try {
                        agenda.agregar(dni,nombre,telefono);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        Thread.sleep(1000);
                    }
                    break;

                //buscar
                case 2:
                    System.out.println("Introduce el dni de la persona a buscar");
                    dni = sc.nextLine();

                    try {
                        System.out.println(agenda.recuperar(dni));
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        Thread.sleep(1000);
                    }
                    break;

                //eliminar
                case 3:
                    System.out.println("Introduce el dni de la persona a eliminar");
                    dni = sc.nextLine();

                    try{
                        agenda.eliminar(dni);
                        System.out.println("Persona eliminada correctamente");
                        Thread.sleep(1000);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        Thread.sleep(1000);
                    }
                break;

                //mostrar agenda
                case 4:
                    System.out.println(agenda);
                    Thread.sleep(2000);
                    break;

                //guardar y salir
                case 5:
                    System.out.println("Guardando contenido...");
                    agenda.guardarDatosJSON();
                    agenda.guardarDatosXML();
                    Thread.sleep(1000);
                    System.out.println("Guardado correctamente, que tengas un buen dia!");
                    salir = true;
                    break;
            }

        }
    }
}