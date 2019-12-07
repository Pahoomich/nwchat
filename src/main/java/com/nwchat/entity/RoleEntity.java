package com.nwchat.entity;

import javax.persistence.*;
import java.util.Objects;

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
	@GeneratedValue
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

		return Objects.equals(id, that.id) &&
				Objects.equals(role, that.role);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, role);
	}
}
