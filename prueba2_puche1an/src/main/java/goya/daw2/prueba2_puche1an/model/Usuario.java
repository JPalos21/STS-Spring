package goya.daw2.prueba2_puche1an.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    String nombre;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Puntuacion> puntos = new ArrayList<Puntuacion>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Puntuacion> getPuntos() {
        return puntos;
    }

    public void setPuntos(List<Puntuacion> puntos) {
        this.puntos = puntos;
    }

    public Usuario(String nombre) {
        super();
        this.nombre = nombre;
    }

    public Usuario() {
        super();
    }
    public void addPuntuacion(Puntuacion puntuacion) {
        puntos.add(puntuacion);
        puntuacion.setUsuario(this);
    }
    public void deletePuntuacion(Puntuacion puntuacion) {
        puntos.remove(puntuacion);
    }
}
