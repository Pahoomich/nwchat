package com.nwchat.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "order", schema = "public", catalog = "nwchat")
public class OrderEntity {
	private int id;
	private String num;
	private String text;
	private Integer creatorId;
	private Timestamp dateUpdate;
	private Timestamp dateCreate;
	private Integer managerId;
	private Date at;
	private String title;
	private Collection<CheckListItemEntity> checkListItemsById;
	private Collection<ReportEntity> reportsById;

	@Id
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "num", nullable = true, length = -1)
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	@Basic
	@Column(name = "text", nullable = true, length = -1)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Basic
	@Column(name = "creator_id", nullable = true)
	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	@Basic
	@Column(name = "date_update", nullable = true)
	public Timestamp getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Timestamp dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	@Basic
	@Column(name = "date_create", nullable = true)
	public Timestamp getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Timestamp dateCreate) {
		this.dateCreate = dateCreate;
	}

	@Basic
	@Column(name = "manager_id", nullable = true)
	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	@Basic
	@Column(name = "at", nullable = true)
	public Date getAt() {
		return at;
	}

	public void setAt(Date at) {
		this.at = at;
	}

	@Basic
	@Column(name = "title", nullable = true, length = -1)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		OrderEntity that = (OrderEntity) o;

		if (id != that.id) return false;
		if (num != null ? !num.equals(that.num) : that.num != null) return false;
		if (text != null ? !text.equals(that.text) : that.text != null) return false;
		if (creatorId != null ? !creatorId.equals(that.creatorId) : that.creatorId != null) return false;
		if (dateUpdate != null ? !dateUpdate.equals(that.dateUpdate) : that.dateUpdate != null) return false;
		if (dateCreate != null ? !dateCreate.equals(that.dateCreate) : that.dateCreate != null) return false;
		if (managerId != null ? !managerId.equals(that.managerId) : that.managerId != null) return false;
		if (at != null ? !at.equals(that.at) : that.at != null) return false;
		if (title != null ? !title.equals(that.title) : that.title != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (num != null ? num.hashCode() : 0);
		result = 31 * result + (text != null ? text.hashCode() : 0);
		result = 31 * result + (creatorId != null ? creatorId.hashCode() : 0);
		result = 31 * result + (dateUpdate != null ? dateUpdate.hashCode() : 0);
		result = 31 * result + (dateCreate != null ? dateCreate.hashCode() : 0);
		result = 31 * result + (managerId != null ? managerId.hashCode() : 0);
		result = 31 * result + (at != null ? at.hashCode() : 0);
		result = 31 * result + (title != null ? title.hashCode() : 0);
		return result;
	}

	@OneToMany(mappedBy = "ordersByDocId")
	public Collection<CheckListItemEntity> getCheckListItemsById() {
		return checkListItemsById;
	}

	public void setCheckListItemsById(Collection<CheckListItemEntity> checkListItemsById) {
		this.checkListItemsById = checkListItemsById;
	}

	@OneToMany(mappedBy = "ordersByOrderId")
	public Collection<ReportEntity> getReportsById() {
		return reportsById;
	}

	public void setReportsById(Collection<ReportEntity> reportsById) {
		this.reportsById = reportsById;
	}
}