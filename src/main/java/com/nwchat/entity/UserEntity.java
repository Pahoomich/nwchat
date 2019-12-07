package com.nwchat.entity;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "public", catalog = "nwchat")
public class UserEntity {
	@Id
	@Column(name = "id", nullable = false)
	private int id;
	@Basic
	@Column(name = "login", nullable = false, length = 255)
	private String login;
	@Basic
	@Column(name = "password", nullable = false, length = 255)
	private String password;
	@Basic
	@Column(name = "firstname", nullable = false, length = 255)
	private String firstname;
	@Basic
	@Column(name = "lastname", nullable = false, length = 255)
	private String lastname;

	@Basic
	@Column(name = "active", nullable = true)
	private Integer active;

	@Basic
	@Column(name = "role_id", nullable = true)
	private Integer roleId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = true, insertable = false, updatable = false)
	private RoleEntity role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity rol) {
		this.role = role;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserEntity that = (UserEntity) o;

		if (id != that.id) return false;
		if (login != null ? !login.equals(that.login) : that.login != null) return false;
		if (password != null ? !password.equals(that.password) : that.password != null) return false;
		if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
		if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
		if (active != null ? !active.equals(that.active) : that.active != null) return false;
		if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (login != null ? login.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
		result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
		result = 31 * result + (active != null ? active.hashCode() : 0);
		result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
		return result;
	}
}
