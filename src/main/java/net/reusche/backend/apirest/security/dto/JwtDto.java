package net.reusche.backend.apirest.security.dto;

import net.reusche.backend.apirest.security.entity.interfaces.UsuarioInfo;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtDto {

    private String token;
    private String bearer = "Bearer";
    private UsuarioInfo usuario;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDto(String token, UsuarioInfo usuario, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.usuario = usuario;
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public UsuarioInfo getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioInfo usuario) {
        this.usuario = usuario;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
