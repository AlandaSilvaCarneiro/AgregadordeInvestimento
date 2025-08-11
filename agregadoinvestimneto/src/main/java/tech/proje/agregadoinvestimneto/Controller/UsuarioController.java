package tech.proje.agregadoinvestimneto.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.proje.agregadoinvestimneto.Dtos.DtosUsuarioEntrada;
import tech.proje.agregadoinvestimneto.Dtos.DtosUsuarioSaida;
import tech.proje.agregadoinvestimneto.Entity.Usuario;
import tech.proje.agregadoinvestimneto.Service.UsuarioService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/ususario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody DtosUsuarioEntrada body){
        var idUsuario = usuarioService.createUsuairo(body);
        return ResponseEntity.created(URI.create("v1/usuario/"+idUsuario.toString())).build();
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<Usuario> byidUser(@PathVariable("idUser") String idUser){
        var user = usuarioService.getById(idUser);
        if (user.isPresent()){
            return ResponseEntity.ok(user.get());
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/lista")
    public ResponseEntity<List<Usuario>> listaUsuarios(){
        var list = usuarioService.list();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteByid(@PathVariable("id")String id ){
            usuarioService.deleteUserById(id);
         return ResponseEntity.noContent().build();
    }
}
