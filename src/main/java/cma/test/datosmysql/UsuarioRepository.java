//
// Document   : UsuarioRepository
// Created on : 03/13/2015, 09:58:08 AM
// Author     : Carmelo
//
package cma.test.datosmysql;

import java.util.List;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends
        CrudRepository<Usuario, Long>,
        QueryDslPredicateExecutor<Usuario> {

    // Obtener una lista de usuarios con el apellido indicado
    List<Usuario> findByApellido(String apellido);

    // Obtener una lista de los usuarios con edad menor a la indicada
    List<Usuario> findByEdadLessThan(Integer edad);

}
