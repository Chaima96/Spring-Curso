package com.springcurso.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springcurso.domain.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*ANOTACAOES DA BIBLIOTECA LOMBOK PARA GERECAO DE METODOS GETTERS E SETTERS E SEUS RESPECTIVOS CONSTRUTORES
 * TENDO UMA CLASSE MAIS PERCEPTIVEL E COM MENOS LINHAS DE CODIGO
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter 
@Setter

//ANOTACAO DA ENTIDADE USUARIO QUE SERA MAPEADA NO BANCO DE DADOS POR INTERMEDIO DO JPA
@Entity(name = "usuario")
public class Usuario implements Serializable {
	
	/* ANOTACOES DOS ATRIBUTOS DA CLASSE USUARIO QUE SERAO 
	 * QUE SERAO AS RESPECTIVAS COLUNAS DA ENTIDADE USUARIO NO 
	 * BANCO DE DADOS
	 * 
	 */
	
	
	/*Variavel de control que serve para verificar o objecto qwue iremos receber
	 * a sua versao e compsativel com avercao da class usada durante a serializacao
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 75, nullable = false )
	private String nome;
	
	@Column(length = 75, nullable = false, unique = true )
	private String email;
	
	@Getter(onMethod = @__({@JsonIgnore}))
	@Setter(onMethod = @__({@JsonProperty}))
	@Column(length = 100, nullable = false )
	private String password;
	
	@Column(length = 20, nullable = false )
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Getter(onMethod = @__({@JsonIgnore}))
	@OneToMany(mappedBy = "usuario")
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	
	@Getter(onMethod = @__({@JsonIgnore}))
	@OneToMany(mappedBy = "usuario")
	private List<Estado_Pedido> estadoPedido = new ArrayList<Estado_Pedido>();
	
	
	

}

