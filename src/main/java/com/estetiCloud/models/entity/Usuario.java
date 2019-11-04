package com.estetiCloud.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id_Usuario;

	    @Column(unique = true, length = 20)
	    private String username;

	    @Column(length = 60)
	    private String password;

	    private boolean enable;

	    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	    @JoinTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id"),
	            inverseJoinColumns = @JoinColumn(name = "role_id"),
	    uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","role_id"})})
	    private List<Role> roles;


	    private static final long serialVersionUID =1L;


		public Long getId_Usuario() {
			return id_Usuario;
		}


		public void setId_Usuario(Long id_Usuario) {
			this.id_Usuario = id_Usuario;
		}


		public String getUsername() {
			return username;
		}


		public void setUsername(String username) {
			this.username = username;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public boolean isEnable() {
			return enable;
		}


		public void setEnable(boolean enable) {
			this.enable = enable;
		}


		public List<Role> getRoles() {
			return roles;
		}


		public void setRoles(List<Role> roles) {
			this.roles = roles;
		}


		public static long getSerialversionuid() {
			return serialVersionUID;
		}

}
