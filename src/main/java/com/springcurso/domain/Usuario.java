package com.springcurso.domain;

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

import org.hibernate.annotations.Columns;

import com.springcurso.domain.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter 
@Setter
@Entity(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 75, nullable = false )
	private String nome;
	
	@Column(length = 75, nullable = false, unique = true )
	private String email;
	
	@Column(length = 100, nullable = false )
	private String password;
	
	@Column(length = 20, nullable = false )
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToMany(mappedBy = "usuario")
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	
	@OneToMany(mappedBy = "usuario")
	private List<Estado_Pedido> estadoPedido = new ArrayList<Estado_Pedido>();
	
	
	

}

