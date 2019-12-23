package com.nwchat.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "order", schema = "public", catalog = "nwchat")
public class OrderEntity {
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Integer id;
	@Basic
	@Column(name = "num", nullable = true, length = -1)
	private String num;
	@Basic
	@Column(name = "text", nullable = true, length = -1)
	private String text;
	@Basic
	@Column(name = "creator_id", nullable = true)
	private Integer creatorId;
	@Basic
	@Column(name = "manager_id", nullable = true, insertable = false, updatable = false)
	private Integer managerId;
	@ManyToOne
	@JoinColumn(name = "manager_id", referencedColumnName = "id", nullable = false)
	private UserEntity manager;
	@Basic
	@Column(name = "at", nullable = true)
	private Date at;
	@Basic
	@Column(name = "title", nullable = true)
	private String title;
	@OneToMany(mappedBy = "ordersByOrderId")
	private List<CheckListItemEntity> checkListItemsById;
	@OneToMany(mappedBy = "ordersByOrderId")
	private Collection<ReportEntity> reportsById;
	@Basic
	@Column(name = "state", nullable = false)
	private Integer state;

	public OrderEntity() {
	}

	public OrderEntity(Integer id, String num, String text, Integer creatorId, Integer managerId, Date at, String title, List<CheckListItemEntity> checkListItemsById, Integer state) {
		this.id = id;
		this.num = num;
		this.text = text;
		this.creatorId = creatorId;
		this.managerId = managerId;
		this.at = at;
		this.title = title;
		this.checkListItemsById = checkListItemsById;
		this.state = state;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	//	@Basic
//	@Column(name = "date_update", nullable = true)
//	public Timestamp getDateUpdate() {
//		return dateUpdate;
//	}
//
//	public void setDateUpdate(Timestamp dateUpdate) {
//		this.dateUpdate = dateUpdate;
//	}
//
//	@Basic
//	@Column(name = "date_create", nullable = true)
//	public Timestamp getDateCreate() {
//		return dateCreate;
//	}
//
//	public void setDateCreate(Timestamp dateCreate) {
//		this.dateCreate = dateCreate;
//	}
////
	public Integer getManagerId() {
		return managerId;
	}

	//	@ManyToOne(targetEntity = UserEntity.class)
//	@JoinColumn(name = "manager_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
//	public Integer getManagerId() {
//		return managerId;
//	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}


	public Date getAt() {
		return at;
	}

	public void setAt(Date at) {
		this.at = at;
	}


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


		return Objects.equals(id, that.id) &&
				Objects.equals(num, that.num) &&
				Objects.equals(text, that.text) &&
				Objects.equals(creatorId, that.creatorId) &&
				Objects.equals(manager, that.manager) &&
				Objects.equals(managerId, that.managerId) &&
				Objects.equals(at, that.at) &&
				Objects.equals(state, that.state) &&
				Objects.equals(checkListItemsById, that.checkListItemsById) &&
				Objects.equals(title, that.title);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, num, text, creatorId, manager, managerId, at, title, state, checkListItemsById);
	}

	public List<CheckListItemEntity> getCheckListItemsById() {
		return checkListItemsById;
	}

	public void setCheckListItemsById(List<CheckListItemEntity> checkListItemsById) {
		this.checkListItemsById = checkListItemsById;
	}

	public UserEntity getManager() {
		return manager;
	}

	public void setManager(UserEntity manager) {
		this.manager = manager;
	}


	public Collection<ReportEntity> getReportsById() {
		return reportsById;
	}

	public void setReportsById(Collection<ReportEntity> reportsById) {
		this.reportsById = reportsById;
	}



	public String getNumNName() {
		return title + " №" + num;
	}
}
