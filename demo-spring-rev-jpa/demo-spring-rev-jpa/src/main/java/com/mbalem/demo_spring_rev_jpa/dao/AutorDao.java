package com.mbalem.demo_spring_rev_jpa.dao;
// 🔹 Define o pacote onde a classe está localizada.  

//    Como está dentro de "com.mbalem.demo_spring_rev_jpa", o Spring Boot
//    consegue detectá-la automaticamente durante o escaneamento de componentes.

import com.mbalem.demo_spring_rev_jpa.entity.Autor;
// 🔹 Importa a classe de entidade Autor, que representa a tabela "autores"
//    no banco de dados. Será usada como tipo genérico para as operações JPA.

import org.springframework.stereotype.Repository;
// 🔹 Importa a anotação @Repository, que indica que esta classe é um componente
//    de acesso a dados (Data Access Object - DAO).  
//    O Spring trata essa classe como parte da camada de persistência,  
//    permitindo injeção automática (@Autowired) e tradução de exceções SQL.

import org.springframework.transaction.annotation.Transactional;
// 🔹 Importa @Transactional, usada para gerenciar transações do banco de dados.
//    Quando aplicada a um método, garante que ele execute dentro de uma transação.
//    Se ocorrer erro, a transação é revertida automaticamente (rollback).

import jakarta.persistence.EntityManager;
// 🔹 Importa a interface EntityManager, principal responsável por interagir
//    com o banco de dados em JPA.  
//    Ele faz operações como persistir, atualizar, remover e consultar entidades.

import jakarta.persistence.PersistenceContext;
// 🔹 Importa a anotação @PersistenceContext, usada para injetar automaticamente
//    o EntityManager configurado pelo Spring (via JPA e Hibernate).
//    Assim, não é necessário criar manualmente um EntityManagerFactory.

@Repository
// 🔹 Marca a classe como um "repositório" de dados.
// Faz parte da arquitetura em camadas do Spring (Controller → Service →
// Repository).
// O Spring gerencia esta classe como um bean e permite que ela seja injetada
// em outras classes (como o AutorController).

public class AutorDao {
  // 🔹 Declaração da classe pública AutorDao, responsável por realizar
  // operações diretas no banco de dados sobre a entidade Autor.

  @PersistenceContext
  // 🔹 Injeta automaticamente o EntityManager gerenciado pelo Spring.
  // O EntityManager é o ponto de acesso para as operações JPA.
  // Cada transação terá sua própria instância, garantindo isolamento.

  private EntityManager manager;
  // 🔹 Cria uma variável privada "manager" do tipo EntityManager,
  // usada para persistir (inserir), buscar, atualizar e remover entidades.

  @Transactional(readOnly = false)
  // 🔹 Indica que este método será executado dentro de uma transação.
  // readOnly = false → significa que o método altera o banco de dados
  // (neste caso, com um INSERT).
  // Se fosse apenas leitura, poderíamos usar readOnly = true.

  public void save(Autor autor) {
    // 🔹 Método público que recebe um objeto Autor como parâmetro e o salva no
    // banco.
    // A responsabilidade de construir o objeto Autor (com nome, sobrenome etc.)
    // é da camada Controller.

    this.manager.persist(autor);
    // 🔹 Usa o EntityManager para inserir o objeto Autor na tabela "autores".
    // O método persist() marca o objeto como "gerenciado" pelo contexto JPA,
    // e o Hibernate gera automaticamente o comando SQL INSERT quando a
    // transação for confirmada (commit).

  }
}
