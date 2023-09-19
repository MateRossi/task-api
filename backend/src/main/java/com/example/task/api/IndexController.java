package com.example.task.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String iniciar() {return "Servidor iniciado.";}

    @GetMapping("/teste")
    public String teste() {return "Executando um GET";}

    @GetMapping("/login")
    public String loginParametros(@RequestParam("usuario") String usuario,
                                  @RequestParam("senha") String senha) {
        return "Login Parâmetros >> Usuário: "+ usuario + " Senha: "+ senha;
    }

    @GetMapping("/login/{usuario}/password/{senha}")
    public String loginVariaveis(@PathVariable("usuario") String usuario,
                                  @PathVariable("senha") String senha) {
        return "Login Variáveis >> Usuário: "+ usuario + " Senha: "+ senha;
    }

    @PostMapping()
    public String post() {return "Executando um POST";}

    @PostMapping("/login")
    public String loginFormulario(@RequestParam("usuario") String usuario,
                                  @RequestParam("senha") String senha) {
        return "Login Formulário >> Usuário: "+ usuario + " Senha: "+ senha;
    }

    @PutMapping
    public String put() {
        return "Executando um PUT";
    }

    @DeleteMapping
    public String delete() {
        return "Executando um DELETE";
    }
}
