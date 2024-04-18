package com.example.api.com.front.Service;

import com.example.api.com.front.Model.Usuario;
import com.example.api.com.front.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Usuario> listarUsuariosPorIdCrescente() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        usuarios.sort(Comparator.comparingLong(Usuario::getId)); // Ordena por ID crescente
        return usuarios;
    }

    /*
    explicação do metodo de listarUsuarioPorIdCrescente
    primeiro ele vai fazer o findAll né, de todos os usuarios da lista
    depois, usuarios.sort vai ordenar a lista de usuarios usando um comparador (Comparator.comparingLong(Usuario::getId)
    o que significa que a lista sera ordenada com base no ID de cada usuario, de forma crescente
    a ordem crescente é o padrao quando usando o comparinglong, se fosse ordem decrescente eu poderia colocar
    Comparator.comparingLong(Usuario::getId).reversed(), o que iria reverter a lista, fazendo com que fosse ordem desc.
     */
    public Usuario criarUsuario(Usuario usuario) {
        String encoder = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha((encoder));
        return usuarioRepository.save(usuario);
    }

    public Usuario editarUsuario(Usuario usuario, long id) {
        if (usuarioRepository.existsById(id)) {
            usuario.setId(id);
            String encoder = this.passwordEncoder.encode(usuario.getSenha());
            usuario.setSenha((encoder));
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public boolean deletarUsuario(long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Usuario> buscaPorNome(String nome) {
        return usuarioRepository.findAllByNome(nome);
    }

    public Usuario buscaPorId(long id) {
        if (usuarioRepository.existsById(id)) {
            return usuarioRepository.findById(id).orElse(null);
        }
        return null;
    }

    public int quantidadeUsuarios() {
        return usuarioRepository.findAll().size();
    }


    public boolean validarSenha(Usuario usuario) {
        String senha = usuarioRepository.getById(usuario.getId()).getSenha();
        Boolean valid = passwordEncoder.matches(usuario.getSenha(), senha);
        return valid;
    }
}
