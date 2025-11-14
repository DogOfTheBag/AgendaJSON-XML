package alber;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Agenda {
    //he puesto el Set de DNI para que cuando te lo pasen no deje meter ni un repetido
    Set<String> dnis;
    HashMap<String,Persona> personas;
    File archivoJSON = new File("Agenda.json");
    File archivoXML = new File("Agenda.xml");


    public Agenda() {
        personas = new HashMap();
        dnis = new HashSet<>();
    }


    public void cargarDatosJSON() throws IOException {
        if(archivoJSON.length() == 0){
            System.out.println("No hay datos que cargar");
            return;
        }

        ObjectMapper mapper =  new ObjectMapper();
        Map<String,Persona> json = mapper.readValue(archivoJSON, new TypeReference<Map<String, Persona>>() {
        });
        for (Persona p : json.values()) {
            String dniPersona = p.getDni();
            dnis.add(dniPersona);
            personas.put(dniPersona,p);
        }
    }

    public void guardarDatosJSON() throws IOException {
        ObjectMapper mapper =  new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(archivoJSON,personas);

    }

    public void guardarDatosXML(){
        XmlMapper mapper = new XmlMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(archivoXML,personas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
        /*Para evitar repetidos usamos el Set de DNI, de forma que le pases tu el DNI, comprueba si hay ese DNI en el set
        * y si lo hay no te agrega la persona automaticamente.*/
    public boolean agregar(String dni, String nombre, long telefono) throws Exception {
        personas.put(dni,new Persona(dni, nombre, telefono));
        if(dnis.add(dni)){
            return true;
        }
        throw new Exception("Error al introducir a la persona, datos erróneos o ya hay un DNI igual en la agenda");
    }

    public boolean eliminar(String dni) throws Exception {
        for (Persona p : personas.values()) {
            if (p.getDni().equalsIgnoreCase(dni)){
                personas.remove(dni);
                return true;
            }
        }
        throw new Exception("DNI erroneo o no existe la persona");
    }

    public Persona recuperar(String dni) throws Exception {
        for (Persona p : personas.values()) {
            if(p.getDni().equalsIgnoreCase(dni)){
                return p;
            }
        }
        throw new Exception("No se ha encontrado una persona con este DNI");
    }

    @Override
    public String toString() {
        // que no se me olvide la existencia de StringBuilder que hace las cadenas mucho mejor que el + y ya
        StringBuilder sb = new StringBuilder("En la agenda tenemos las siguientes personas:\n");
        /*Lo nuevo a tener en cuenta de los hashmaps en el fore es que no le podemos pasar directamente personas
        * debido a que como el mapa como tal tiene clave valor, le pasarías ambas
        * de modo que si le quieres pasar el contenido, le pasas values y se queda como de normal*/
        for (Persona p : personas.values()) {
            sb.append(p);
        }
        return sb.toString();
    }
}
