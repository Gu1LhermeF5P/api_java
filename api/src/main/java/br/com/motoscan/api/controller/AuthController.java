package br.com.motoscan.api.controller;

import br.com.motoscan.api.model.Usuario;
import br.com.motoscan.api.repository.UsuarioRepository;
import br.com.motoscan.api.service.TokenService; // Importe o novo serviço
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager; // Importe
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; // Importe
import org.springframework.security.core.Authentication; // Importe
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager; // Adicionado

    @Autowired
    private TokenService tokenService; // Adicionado

    @PostMapping("/register")
public ResponseEntity<String> registrar(@RequestBody Usuario usuario) {
    if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado.");
    }

    // --- LOG DE DEPURAÇÃO ---
    System.out.println("--- REGISTRO ---");
    System.out.println("Senha recebida: " + usuario.getSenha());
    String senhaCodificada = passwordEncoder.encode(usuario.getSenha());
    System.out.println("Senha codificada (hash): " + senhaCodificada);
    System.out.println("-----------------");
    // --- FIM DO LOG ---

    usuario.setSenha(senhaCodificada);
    usuarioRepository.save(usuario);
    return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso!");
}

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String senha = credentials.get("senha");
        
        // Cria o objeto de autenticação para o Spring processar
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, senha);

        try {
            // O AuthenticationManager tentará autenticar. Se falhar, lança uma exceção.
            Authentication authentication = authenticationManager.authenticate(authToken);
            
            // Se a autenticação for bem-sucedida, geramos o token JWT
            String token = tokenService.generateToken(authentication);
            
            // Retornamos o token no corpo da resposta
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            // Se a autenticação falhar, retornamos 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha inválidos.");
        }
    }
}