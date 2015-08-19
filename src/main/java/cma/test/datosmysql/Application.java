//
// Document   : Application
// Created on : 03/13/2015, 10:22:26 AM
// Author     : Carmelo
//
package cma.test.datosmysql;

import com.mysema.query.types.Predicate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext context = SpringApplication.run(Application.class)) {
            UsuarioRepository repository = context.getBean(UsuarioRepository.class);

            // Guardar un conjunto de usuarios
            repository.save(new Usuario("Juan", "Perez", 25));
            repository.save(new Usuario("Maria", "Lozz", 15));
            repository.save(new Usuario("Lina", "Ruixs", 23));
            repository.save(new Usuario("Dania", "Mars", 20));

            // Obtener los usarios con apellidos que terminan en la letra "z"
            QUsuario usuario = QUsuario.usuario;
            Predicate consulta1 = usuario.apellido.endsWith("z");
            Iterable<Usuario> usrs1 = repository.findAll(consulta1);

            print("Consulta QueryDSL #1", usrs1);
            
            // Aplicar mas de un filtro usando (and)
            // Busca los usuarios con edades entre 10 - 20 y con nombre terminado en "a"
            Predicate consulta2 = usuario.edad.between(10, 20).and(usuario.nombre.endsWith("a"));
            Iterable<Usuario> usrs2 = repository.findAll(consulta2);
            
            print("Consulta QueryDSL #2", usrs2);
        }
    }

    private static void print(String title, Iterable<Usuario> usrs) {
        System.out.println(title);
        usrs.forEach(u -> System.out.println(u));
        System.out.println();
    }
}
