package com.mbalem.demo_spring_rev_jpa.entity;

import java.io.Serializable;
import jakarta.persistence.*;

// 🔹 @Entity → indica que esta classe é uma entidade JPA, ou seja,
//    será mapeada para uma tabela no banco de dados.
@Entity

// 🔹 @Table(name = "autores") → define explicitamente o nome da tabela
// que será usada para armazenar os dados desta entidade.
// Caso não fosse definido, o Hibernate usaria o nome da classe ("autor").
@Table(name = "autores")
public class Autor implements Serializable {

  // 🔹 @Id → marca o campo como a chave primária da tabela.
  @Id

  // 🔹 @GeneratedValue → define a estratégia de geração automática do ID.
  // GenerationType.IDENTITY indica que o banco de dados é responsável por
  // gerar o valor (exemplo: auto_increment no MySQL).
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  // 🔹 @Column → personaliza o mapeamento da coluna.
  // name = "id_autor" → nome da coluna no banco.
  // nullable = false → impede valores nulos (NOT NULL).
  @Column(name = "id_autor", nullable = false)
  private Long id;

  // 🔹 Outro @Column → mapeia o atributo "nome" para a coluna "nome".
  // length = 45 → define o tamanho máximo no banco.
  // nullable = false → torna o campo obrigatório.
  @Column(name = "nome", length = 45, nullable = false)
  private String nome;

  // 🔹 Mesmo conceito aplicado ao campo "sobrenome".
  @Column(name = "sobrenome", length = 45, nullable = false)
  private String sobrenome;

  // Métodos getters e setters — usados pelo Hibernate para ler e escrever
  // valores.
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSobrenome() {
    return sobrenome;
  }

  public void setSobrenome(String sobrenome) {
    this.sobrenome = sobrenome;
  }

  // hashCode, equals e toString não são específicos do Spring,
  // mas são importantes para o Hibernate comparar entidades corretamente.
}
