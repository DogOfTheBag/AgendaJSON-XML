package alber;
/*Lo dicho antes, para poder convertir en bytes los datos de la clase persona
* debemos implementar la interfaz Serializable*/
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;

/*Las anotaciones para cambiar de orden y que queden más bonitos los ficheros */
@JsonPropertyOrder({"nombre", "dni", "telefono"})
public class Persona implements Serializable {
    @JacksonXmlProperty(localName = "DNI")
    @JsonProperty("DNI")
    private String dni;

    @JacksonXmlProperty(localName = "Nombre")
    @JsonProperty("Nombre")
    private String nombre;

    @JacksonXmlProperty(localName = "Telefono")
    @JsonProperty("Telefono")
    private long telefono;

    public Persona(String dni, String nombre, long telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    //necesitamos este constructor para que el mapper cree las personas desde el fichero
    public Persona() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
                ", telefono: " + telefono + ", DNI: " + dni + "\n";
    }
}
