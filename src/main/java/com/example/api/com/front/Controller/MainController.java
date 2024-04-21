package com.example.api.com.front.Controller;

import com.example.api.com.front.Model.Usuario;
import com.example.api.com.front.Repository.UsuarioRepository;
import com.example.api.com.front.Service.UsuarioService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class MainController {
    @Autowired
    private UsuarioService usuarioService;

    //LISTA TODOS OS USUARIOS
    @GetMapping
    public ResponseEntity<List<Usuario>> listaUsuarios() {
        return ResponseEntity.status(200).body(usuarioService.listarUsuariosPorIdCrescente());
    }

    //CRIA UM USUARIO NOVO
    @CrossOrigin(origins = "*")
    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.status(201).body(usuarioService.criarUsuario(usuario));
    }

    //ATUALIZA UM USUARIO JA EXISTENTE
    @PutMapping("/{id}")
    public ResponseEntity<?> editarUsuario(@PathVariable long id, @Valid @RequestBody Usuario usuario) {
        Usuario usuarioAtualizado = usuarioService.editarUsuario(usuario, id);
        if (usuarioAtualizado == null) {
            String mensagem = "O usuário com o ID " + id + " não existe na base de dados";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }
        return ResponseEntity.ok(usuarioAtualizado);
    }

    //DELETA UM USUARIO
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable long id) {
        if (usuarioService.deletarUsuario(id)) {
            String mensagem = "A deleção do id: " + id + " foi realizada com sucesso";
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(mensagem);
        }
        String mensagem = "o id informado não existe na base de dados";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
    }

    //LISTA USUARIOS BUSCANDO PELO NOME
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Usuario>> listarPorNome(@PathVariable String nome) {
        List<Usuario> usuarios = usuarioService.buscaPorNome(nome);
        if (usuarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    //RETORNA O USUARIO DO ID PASSADO
    @GetMapping("/id/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable long id) {
        Usuario usuarioProcurado = usuarioService.buscaPorId(id);
        if (usuarioProcurado != null) {
            return ResponseEntity.ok(usuarioProcurado);
        } else {
            String mensagem = "o id informado não existe na base de dados";
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(mensagem);
        }
    }

    //RETORNA A QUANTIDADE DE USUARIOS CADASTRADOS
    @GetMapping("/quantidade-usuarios")
    public ResponseEntity<?> quantidadeUsuarios() {
        String mensagem = "A quantidade de usuarios cadastrados é: ";
        return ResponseEntity.status(200).body(mensagem + usuarioService.quantidadeUsuarios());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> erros = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((erro) ->{
            String nomeDoCampo = ((FieldError) erro).getField();
            String mensagemDeErro = erro.getDefaultMessage();
            erros.put(nomeDoCampo, mensagemDeErro);
        });
        return erros;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        // Aqui você pode implementar a lógica para lidar com a exceção
        String mensagem = "O usuario passado esta com uma estrutura incorreta JSON. Erro de leitura da mensagem HTTP: " + exception.getMessage();
        return mensagem;
    }

    @PostMapping("/login")
    public ResponseEntity<String> validarSenha(@RequestBody Usuario usuario) {
        Boolean valid = usuarioService.validarSenha(usuario);
        if (!valid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String mensagem = "Senha esta correta, login foi autorizado";
        return ResponseEntity.ok(mensagem);
    }

}
