package tech.proje.agregadoinvestimneto.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.proje.agregadoinvestimneto.Dtos.DtosAccountSaida;
import tech.proje.agregadoinvestimneto.Dtos.DtosUsuarioEntrada;
import tech.proje.agregadoinvestimneto.Dtos.DtosAccountEntrada;
import tech.proje.agregadoinvestimneto.Entity.Usuario;
import tech.proje.agregadoinvestimneto.Service.UsuarioService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/ususario")
@Tag(name= "Usuarios",description = "crud de ususarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    @Operation(summary = "Criação de ususario", description = "retorna o UUID do usuario",
    responses = {
            @ApiResponse(responseCode = "200",description = "criação com sucesso do usauario e retono do seu UUID"),
            @ApiResponse(responseCode = "400",description = "requisição inválida (erro de sintaxe, dados incorretos)")
    }

    )
    public ResponseEntity<Usuario> createUsuario(@RequestBody DtosUsuarioEntrada body){
        var idUsuario = usuarioService.createUsuairo(body);
        return ResponseEntity.created(URI.create("v1/usuario/"+idUsuario.toString())).build();
    }



    @GetMapping("/{idUser}")
    @Operation(summary = "De um Ususario", description = "retorna de um usuario especifico do banco do banco",
            responses = {
                    @ApiResponse(responseCode = "200",description = "Retorno com sucesso do Usuarios"),
                    @ApiResponse(responseCode = "400",description = "requisição inválida (erro de sintaxe, dados incorretos)")
            }

    )
    public ResponseEntity<Usuario> byidUser(@PathVariable("idUser") String idUser){
        var user = usuarioService.getById(idUser);
        if (user.isPresent()){
            return ResponseEntity.ok(user.get());
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/lista")
    @Operation(summary = "Rtorno com sucesso da lista de usuarios no banco", description = "retorna uma lista de usuarios no banco",
            responses = {
                    @ApiResponse(responseCode = "200",description = "Retorno com sucesso da lista de usuarios no banco"),
                    @ApiResponse(responseCode = "400",description = "requisição inválida (erro de sintaxe, dados incorretos)")
            }

    )
    public ResponseEntity<List<Usuario>> listaUsuarios(){
        var list = usuarioService.list();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletação de um usuario especifico no banco, passado o UUID", description = "não retorna nada só o HTTP ",
            responses = {
                    @ApiResponse(responseCode = "200",description = "Deletação do usuario com sucesso no banco "),
                    @ApiResponse(responseCode = "400",description = "requisição inválida (erro de sintaxe, dados incorretos)")
            }

    )
    public  ResponseEntity<Void> deleteByid(@PathVariable("id")String id ){
            usuarioService.deleteUserById(id);
         return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/account")
    @Operation(summary = "Criação de um Account", description = "não retorna nada so o HTTP",
            responses = {
                    @ApiResponse(responseCode = "200",description = "criação com sucesso de uma account no banco"),
                    @ApiResponse(responseCode = "400",description = "requisição inválida (erro de sintaxe, dados incorretos)")
            }

    )
    public ResponseEntity<Void> createdAccount(@PathVariable("id")String userid , DtosAccountEntrada dtosAcoount){
            usuarioService.salveAcoount(userid,dtosAcoount);
            return ResponseEntity.ok().build();
    }
    @GetMapping("{id}/account")
    @Operation(summary = "Retorna as account de um usuario no banco de dados", description = "retorna uma lista de account de um usario",
            responses = {
                    @ApiResponse(responseCode = "200",description = "Retorno bem sucedido d alista de account do usuario no banco de dados"),
                    @ApiResponse(responseCode = "400",description = "requisição inválida (erro de sintaxe, dados incorretos)")
            }

    )
    public ResponseEntity<List<DtosAccountSaida>> listaAccount(@PathVariable("id")String idUser){
        var listaAccountSaida = usuarioService.listaDeAccounts(idUser);
        return ResponseEntity.ok(listaAccountSaida);
    }
}
