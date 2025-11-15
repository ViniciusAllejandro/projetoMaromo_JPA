package com.mbalem.demo_spring_rev_jpa.controller;
// 🔹 Define o pacote onde a classe está localizada.  

//    Como ela está em um subpacote de "com.mbalem.demo_spring_rev_jpa",
//    o Spring Boot consegue detectá-la automaticamente no escaneamento de componentes.

import com.mbalem.demo_spring_rev_jpa.dao.AutorDao;
// 🔹 Importa a classe AutorDao, que contém a lógica de persistência usando JPA (EntityManager).

import com.mbalem.demo_spring_rev_jpa.entity.Autor;
// 🔹 Importa a classe de entidade Autor, que representa a tabela "autores" no banco de dados.

import org.springframework.beans.factory.annotation.Autowired;
// 🔹 Importa a anotação @Autowired, usada para injetar automaticamente
//    uma instância de AutorDao gerenciada pelo Spring (injeção de dependência).

import org.springframework.web.bind.annotation.RequestBody;
// 🔹 Importa a anotação @RequestBody, que indica que o parâmetro de um método
//    deve ser preenchido com o corpo (JSON) da requisição HTTP.

import org.springframework.web.bind.annotation.RequestMapping;
// 🔹 Importa a anotação @RequestMapping, usada para definir a rota base (endpoint)
//    a partir da qual os métodos do controller vão responder.
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// 🔹 Importa @RestController, que combina @Controller + @ResponseBody.
//    Indica ao Spring que esta classe deve ser registrada como um controlador REST,
//    e que todos os métodos retornam diretamente dados (JSON), não páginas HTML.

import org.springframework.web.bind.annotation.PostMapping;
// 🔹 Importa @PostMapping, usada para mapear requisições HTTP do tipo POST
//    a um método específico do controller.
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
// 🔹 Informa ao Spring que esta classe é um controlador REST,
// permitindo que ele receba e responda requisições HTTP (JSON).

@RequestMapping("/autores")
// 🔹 Define a rota base para todos os endpoints deste controller.
// Assim, o método abaixo responderá a requisições em "/autores".

public class AutorController {
  // 🔹 Declaração da classe pública AutorController, responsável por receber
  // requisições
  // relacionadas à entidade Autor e repassá-las ao DAO para persistência.

  @Autowired
  // 🔹 Diz ao Spring para injetar automaticamente uma instância de AutorDao
  // (não precisamos criar manualmente com "new AutorDao()").
  // Isso funciona porque AutorDao está anotado com @Repository e é gerenciado
  // pelo Spring.

  private AutorDao dao;
  // 🔹 Cria um atributo do tipo AutorDao, responsável por realizar
  // operações de banco de dados (insert, select, etc.) da entidade Autor.

  @PostMapping
  // 🔹 Mapeia requisições HTTP POST para o método abaixo.
  // Ou seja, quando o cliente enviar um POST para /autores, este método será
  // executado.

  public Autor salvar(@RequestBody Autor autor) {
    // 🔹 Declara um método público chamado "salvar" que recebe um objeto Autor
    // preenchido automaticamente com o corpo JSON da requisição (graças ao
    // @RequestBody).
    // Exemplo do corpo no Postman:
    // {
    // "nome": "Machado",
    // "sobrenome": "de Assis"
    // }

    dao.save(autor);
    // 🔹 Chama o método save() do AutorDao, que faz a persistência no banco via
    // JPA.
    // Aqui o autor é salvo na tabela "autores".

    return autor;
    // 🔹 Retorna o próprio objeto Autor salvo.
    // O Spring converte automaticamente esse objeto em JSON na resposta HTTP.
  }

  // Indica que este método responderá requisições HTTP do tipo PUT,
  // utilizadas normalmente para atualizar recursos existentes.
  @PutMapping
  public Autor atualizar(@RequestBody Autor autor) {
    // @RequestBody faz o Spring pegar o JSON enviado no corpo da requisição
    // e converter automaticamente em um objeto Autor preenchido.

    // Chama o método do DAO responsável por atualizar o autor no banco de dados.
    dao.update(autor);

    // Retorna o próprio objeto Autor atualizado.
    // O Spring converte esse objeto automaticamente em JSON na resposta HTTP.
    return autor;
  }

  @DeleteMapping("{id}")
  public String remover(@PathVariable Long id) {

    // Chama o método de exclusão no DAO/Repository, passando o ID recebido na URL.
    // Nesse ponto, assumimos que o método delete(id) já sabe localizar ou criar uma
    // referência
    // da entidade e executar a remoção corretamente via JPA/Hibernate.
    dao.delete(id);

    // Retorna uma mensagem simples confirmando a exclusão.
    // Em aplicações REST reais, o ideal seria retornar um ResponseEntity com status
    // HTTP apropriado,
    // mas aqui retornamos apenas uma String para fins didáticos.
    return "Autor id " + id + " foi excluido com sucesso.";
  }

  // Mapeia requisições HTTP GET para o endpoint "/{id}"
  @GetMapping("{id}")
  public Autor getById(@PathVariable Long id) { // Recebe o ID passado na URL como parâmetro

    return dao.findById(id); // Chama o DAO para buscar o Autor pelo ID e retorna o resultado
  }

  // Mapeia requisições HTTP GET para o endpoint "/"
  @GetMapping
  public List<Autor> getAll() { // Método para retornar todos os autores cadastrados

    return dao.findByAll(); // Chama o DAO e retorna a lista completa de Autores
  }

  // Mapeia requisições GET para "/nomeOrSobrenome"
  @GetMapping("nomeOrSobrenome")
  public List<Autor> getAutoresByNomeOrSobrenome(@RequestParam String termo) {
    // Recebe um parâmetro de consulta da URL ?termo=valor

    return dao.findAllByNomeOrSobrenome(termo);
    // Busca autores cujo nome OU sobrenome contenham o termo informado
  }

  // Mapeia GET para "/total"
  @GetMapping("total")
  public Long getTotalDeAutores() { // Método para retornar o número total de autores na tabela

    return dao.getTotalElements(); // Chama o DAO e retorna a contagem de registros
  }

}