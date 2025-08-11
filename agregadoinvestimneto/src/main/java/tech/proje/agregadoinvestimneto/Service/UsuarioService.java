package tech.proje.agregadoinvestimneto.Service;


import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.proje.agregadoinvestimneto.Dtos.DtosUsuarioEntrada;
import tech.proje.agregadoinvestimneto.Entity.Usuario;
import tech.proje.agregadoinvestimneto.Respository.UsuarioRepository;

import javax.swing.*;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repositoryUsuario;


    public UUID createUsuairo(DtosUsuarioEntrada dtosUsuarioEntrada) {
        // fazer um costrutor sem o id;
        var Entityusuario = new Usuario(
               null,
                dtosUsuarioEntrada.email(),
                dtosUsuarioEntrada.senha(),
                dtosUsuarioEntrada.nome(),
                Instant.now(),
                null);

        var  usersalve = repositoryUsuario.save(Entityusuario);
        return  usersalve.getIdUser();

    }
    public Optional<Usuario> getById(String idUser){
        var entiti = repositoryUsuario.findById(UUID.fromString(idUser));

       return entiti;
    }

    public List<Usuario> list(){
        return repositoryUsuario.findAll();
    }
    public void  deleteUserById(String idUser){
        var idDelete = UUID.fromString(idUser);
        var idExiste = repositoryUsuario.existsById(idDelete);
        if(idExiste){
            repositoryUsuario.deleteById(idDelete);
        }
    }
    public void userUpdate(String idUser, DtosUsuarioEntrada dtosUsuarioEntrada){
        var userexite = repositoryUsuario.findById(UUID.fromString(idUser));
        if(userexite.isPresent()){
            var user = userexite.get();
            if(dtosUsuarioEntrada.nome()!= null){
                user.setNome(dtosUsuarioEntrada.nome());
            }
            if(dtosUsuarioEntrada.email()!= null){
                user.setEmail(dtosUsuarioEntrada.email());
            }
            if(dtosUsuarioEntrada.senha()!= null){
                user.setSenha(dtosUsuarioEntrada.senha());
            }
            repositoryUsuario.save(user);
        }


    }

}
