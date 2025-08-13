package tech.proje.agregadoinvestimneto.Service;


import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tech.proje.agregadoinvestimneto.Dtos.DtosAccountEntrada;
import tech.proje.agregadoinvestimneto.Dtos.DtosAccountSaida;
import tech.proje.agregadoinvestimneto.Dtos.DtosUsuarioEntrada;
import tech.proje.agregadoinvestimneto.Entity.Account;
import tech.proje.agregadoinvestimneto.Entity.BillingAnddress;
import tech.proje.agregadoinvestimneto.Entity.Usuario;
import tech.proje.agregadoinvestimneto.Respository.AccountRepository;
import tech.proje.agregadoinvestimneto.Respository.BillingAnddressRepositoy;
import tech.proje.agregadoinvestimneto.Respository.UsuarioRepository;

import javax.swing.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repositoryUsuario;

    @Autowired
    private BillingAnddressRepositoy billingAnddressRepositoy;

    @Autowired
    private AccountRepository accountRepository;


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

    public void salveAcoount(String userid, DtosAccountEntrada dtosAcoount) {
        var user = repositoryUsuario.findById(UUID.fromString(userid))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

            var account = new Account(
                    dtosAcoount.descrip(),
                    user,
                    null,
                    new ArrayList<>()


            );

          var  accountCreate = accountRepository.save(account);

           var  billingAnddres = new BillingAnddress(
                   accountCreate.getIdAccout(),
                   dtosAcoount.street(),
                   dtosAcoount.number(),
                   account
           );
           billingAnddressRepositoy.save(billingAnddres);

    }

    public List<DtosAccountSaida> listaDeAccounts(String userid) {
        var user = repositoryUsuario.findById(UUID.fromString(userid))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var listaAccouts = user.getAccounts().stream().
                map(ac -> new DtosAccountSaida(ac.getIdAccout().toString(),ac.getDescription())).toList();
         return  listaAccouts;
    }

}
