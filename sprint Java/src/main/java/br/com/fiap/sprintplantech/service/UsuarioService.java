package br.com.fiap.sprintplantech.service;

import br.com.fiap.sprintplantech.dto.request.UsuarioRequest;
import br.com.fiap.sprintplantech.dto.response.UsuarioResponse;
import br.com.fiap.sprintplantech.entity.Agricultor;
import br.com.fiap.sprintplantech.entity.Usuario;
import br.com.fiap.sprintplantech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class UsuarioService implements ServiceDTO<Usuario, UsuarioRequest, UsuarioResponse>{

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private AgricultorService agricultorService;

    @Override
    public Collection<Usuario> findAll(Example<Usuario> example) {
        return repo.findAll( example );
    }

    @Override
    public Usuario findById(Long id) {
        return repo.findById( id ).orElse(null);
    }

    @Override
    public Usuario save(Usuario e) {
        return repo.save( e );
    }

    @Override
    public Usuario toEntity(UsuarioRequest dto) {
        if (Objects.isNull( dto )) return null;

        var agricultor = this.findById(dto.agricultor().id());

        return Usuario.builder()
                .nomeUsuario(dto.nomeUsuario())
                .senha(dto.senha())
                .nomeCompleto(dto.nomeCompleto())
                .email(dto.email())
                .dataCriacao(dto.dataCriacao())
                .agricultor(agricultor.getAgricultor())
                .build();
    }

    @Override
    public UsuarioResponse toResponse(Usuario e) {
        return UsuarioResponse.builder()
                .id(e.getId())
                .nomeUsuario(e.getNomeUsuario())
                .nomeCompleto(e.getNomeCompleto())
                .email(e.getEmail())
                .dataCriacao(e.getDataCriacao())
                .agricultor(agricultorService.toResponse(Agricultor.builder().build()))
                .build();
    }
}
