package com.springcurso.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springcurso.domain.enums.EstadosdoPedido;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity(name = "pedido")
public class Pedido implements Serializable {
	
	/*Variavel de control que serve para verificar o objecto qwue iremos receber
	 * a sua versao e compsativel com avercao da class usada durante a serializacao
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 75, nullable = false )
	private String subject;
	
	@Column(columnDefinition = "text" )
	private String descricao;
	
	@Column(name = "criacao_data", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date criacaoData;
	
	@Column(length = 12, nullable = false )
	@Enumerated(EnumType.STRING)
	private EstadosdoPedido estado;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
	
	@Getter(onMethod = @__({@JsonIgnore}))
	@OneToMany(mappedBy = "pedido")
	private List<Estado_Pedido> estadopedido = new ArrayList<Estado_Pedido>();
	
	

}
