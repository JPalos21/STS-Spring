package goya.daw2.prueba2_puche1an.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import goya.daw2.prueba2_puche1an.model.Puntuacion;
import goya.daw2.prueba2_puche1an.model.Usuario;
import goya.daw2.prueba2_puche1an.repositorios.RepositorioPuntuacion;
import goya.daw2.prueba2_puche1an.repositorios.RepositorioUsuario;

@Service
public class ServicioQuiz {

    private RepositorioUsuario repositorioUsuario;
    private RepositorioPuntuacion repositorioPuntuacion;

    public ServicioQuiz(RepositorioUsuario repositorioUsuario, RepositorioPuntuacion repositorioPuntuacion) {

        this.repositorioUsuario = repositorioUsuario;
        this.repositorioPuntuacion =  repositorioPuntuacion;
    }

    public void guardarDatos(String nombre, int puntos) {

        Puntuacion puntuacion = new Puntuacion(puntos);
        Usuario usuario;

        List<Usuario> consulta = repositorioUsuario.findByNombre(nombre);
        if (consulta.isEmpty() || consulta == null) {
            usuario = new Usuario(nombre);
        }
        else {
            usuario = consulta.get(0);
        }
        usuario.addPuntuacion(puntuacion);
        repositorioUsuario.save(usuario);

        List<Puntuacion> consultaPuntuaciones = repositorioPuntuacion.findAllByOrderByPuntosDesc();
        if (consultaPuntuaciones.size() > 4) {
            Puntuacion ultimPuntuacion = consultaPuntuaciones.get(4);
            repositorioPuntuacion.delete(ultimPuntuacion);
            System.out.println("Se ha borrado la peor puntuación");
        }
        System.out.println("No se borró nada");
    }

    public List<Usuario> listarNombre() {
        return repositorioUsuario.findAllByOrderByNombreDesc();
    }
    public List<Puntuacion> listarPuntuacion() {
        return repositorioPuntuacion.findAllByOrderByPuntosDesc();
    }
}
