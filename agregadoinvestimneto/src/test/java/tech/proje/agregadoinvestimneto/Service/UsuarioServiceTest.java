package tech.proje.agregadoinvestimneto.Service;
import org.checkerframework.framework.qual.RequiresQualifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.proje.agregadoinvestimneto.Dtos.DtosUsuarioEntrada;
import tech.proje.agregadoinvestimneto.Entity.Usuario;
import tech.proje.agregadoinvestimneto.Respository.UsuarioRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioServicemo;

    @Captor
    private ArgumentCaptor<Usuario> usuarioAgumentoCaptor;

    @Captor
    private  ArgumentCaptor<UUID> uuidArgumentCaptor;
        @Nested
        class creareUser {
        @Test
        @DisplayName("Crianção de usuario com sucesso")
        void createSalveSuccess() {
            var user = new Usuario(
                    UUID.randomUUID(),
                    "email@",
                    "senha",
                    "nome",
                    Instant.now(),
                    null
            );
            doReturn(user).when(usuarioRepository).save(usuarioAgumentoCaptor.capture());
            var input = new DtosUsuarioEntrada(
                    "nome",
                    "senha",
                    "email@");


            var output = usuarioServicemo.createUsuairo(input);
            var captoruser = usuarioAgumentoCaptor.getValue();
            assertEquals(input.nome(), captoruser.getNome());
            assertEquals(input.email(), captoruser.getEmail());
            assertEquals(input.senha(), captoruser.getSenha());
            assertNotNull(output);
        }
    }
        @Test
        @DisplayName("Lançanso uma expition")
        void createExeption(){
            doThrow(new RuntimeException()).when(usuarioRepository).save(any());
            var input = new DtosUsuarioEntrada(
                    "nome",
                    "senha",
                    "email@");

            assertThrows(RuntimeException.class, () -> usuarioServicemo.createUsuairo(input));
        }
        


     @Nested
     class ListeUser{
           @Test
           @DisplayName("Retono com sucesso lista usuario")
            void listretunsuccesso(){

               var user = new Usuario(
                       UUID.randomUUID(),
                       "email@",
                       "senha",
                       "nome",
                       Instant.now(),
                       null
               );
               var userList = List.of(user);
               doReturn(userList).when(usuarioRepository).findAll();

               var output = usuarioServicemo.list();
               assertEquals(userList.size(),output.size());


           }
        }


     @Nested
    class deletebyId{
       @Test
       @DisplayName("Existencia e deleçãpo do id")
        void deletebyid(){
           doReturn(true).when(usuarioRepository).existsById(uuidArgumentCaptor.capture());

           doNothing().when(usuarioRepository).deleteById(uuidArgumentCaptor.capture());

           var userid = UUID.randomUUID();
           usuarioServicemo.deleteUserById(userid.toString());
           var listIdCaptor = uuidArgumentCaptor.getAllValues();
           assertEquals(userid,listIdCaptor.get(0));
           assertEquals(userid,listIdCaptor.get(1));
       }

    }
    @Nested
    class updateUser{
      @Test
      void upsdateUserSuccesso(){
          var user = new Usuario(
                  UUID.randomUUID(),
                  "email@",
                  "senha",
                  "nome",
                  Instant.now(),
                  null
          );
          doReturn(user).when(usuarioRepository).save(usuarioAgumentoCaptor.capture());
          doReturn(Optional.of(user)).when(usuarioRepository).findById(uuidArgumentCaptor.capture());
          var input = new DtosUsuarioEntrada(
                  "nome1",
                  "senha1",
                  "email@1");


          usuarioServicemo.userUpdate(user.getIdUser().toString(),input);
          var captoruser = usuarioAgumentoCaptor.getValue();
          var captoid = uuidArgumentCaptor.getValue();
          assertEquals(input.email(), captoruser.getEmail());
          assertEquals(input.nome(), captoruser.getNome());
          assertEquals(input.senha(), captoruser.getSenha());
          verify(usuarioRepository,timeout(1)).findById(uuidArgumentCaptor.getValue());
          verify(usuarioRepository,timeout(1)).save(usuarioAgumentoCaptor.getValue());

      }
    }


}

