package com.nwchat.entity;

import javax.persistence.*;

@Entity
@Table(name = "role", schema = "public", catalog = "nwchat")
public class RoleEntity {
	private String role;
	private int id;

	@Basic
	@Column(name = "role", nullable = false, length = 255)
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Id
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		RoleEntity that = (RoleEntity) o;

		if (id != that.id) return false;
		if (role != null ? !role.equals(that.role) : that.role != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = role != null ? role.hashCode() : 0;
		result = 31 * result + id;
		return result;
	}
}
