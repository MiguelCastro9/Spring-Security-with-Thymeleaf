package com.permissao.controller;

import com.permissao.model.Role;
import com.permissao.model.Usuario;
import com.permissao.service.NativeQueryService;
import com.permissao.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Miguel Castro
 */
@Controller
public class UsuarioController {
    
    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    private NativeQueryService nativeQueryService;
    
    @Autowired
    PasswordEncoder encoder;
        
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {

        if (error != null) {
            model.addAttribute("error", "E-mail ou senha inválidos, verifique também se o acesso está ativo.");
        }
        return "/login";
    }
    
    @GetMapping("/")
    public String listar(Model model) {
        
        model.addAttribute("usuarios", usuarioService.list());
        return "index";
    }
    
    @GetMapping("/cadastro")
    public String formCadastro(Model model) {
        
        Usuario usuario = new Usuario();
        model.addAttribute(usuario);
        return "cadastro";
    }
    
    @PostMapping("/save")
    public String salvar(Usuario usuario) {
        
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        usuarioService.save(usuario);
        nativeQueryService.execute("INSERT INTO users_roles (user_id, role_id) VALUES (" + usuario.getId() + "," + usuario.getPermissao() + ")");
        
        return "redirect:/";
    }
    
    @GetMapping("/edit/{id}")
    public ModelAndView FormEditar(@PathVariable("id") Long id) {
        
        ModelAndView andViewRedirectEditar = new ModelAndView("cadastro");
        Usuario usuario = usuarioService.update(id);
        andViewRedirectEditar.addObject("usuario", usuario);
        return andViewRedirectEditar;
    }

    @GetMapping("/delete/{id}")
    public String deletar(@PathVariable("id") Long id) {
        
        usuarioService.delete(id);
        return "redirect:/";
    }
    
}