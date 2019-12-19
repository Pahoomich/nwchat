package com.nwchat.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "order", schema = "public", catalog = "nwchat")
public class OrderEntity {
	private Integer id;
	private String num;
	private String text;
	private Integer creatorId;

	private Integer managerId;
	private UserEntity manager;
	private Date at;
	private String title;
	private List<CheckListItemEntity> checkListItemsById;
	private Collection<ReportEntity> reportsById;
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

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
	@Column(name ="state", nullable = false)
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
	@Basic
	@Column(name = "manager_id", nullable = true, insertable= false , updatable = false)
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

	@Basic
	@Column(name = "at", nullable = true)
	public Date getAt() {
		return at;
	}

	public void setAt(Date at) {
		this.at = at;
	}

	@Basic
	@Column(name = "title", nullable = true)
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
		return Objects.hash(id, num, text, creatorId, manager,managerId, at, title, state,checkListItemsById);
	}

	@OneToMany(mappedBy = "ordersByOrderId")
	public List<CheckListItemEntity> getCheckListItemsById() {
		return checkListItemsById;
	}

	public void setCheckListItemsById(List<CheckListItemEntity> checkListItemsById) {
		this.checkListItemsById = checkListItemsById;
	}
	@ManyToOne
	@JoinColumn(name = "manager_id", referencedColumnName = "id", nullable = false)
	public UserEntity getManager(){return manager;}

	public void setManager(UserEntity manager) {
		this.manager = manager;
	}


	@OneToMany(mappedBy = "ordersByOrderId")
	public Collection<ReportEntity> getReportsById() {
		return reportsById;
	}

	public void setReportsById(Collection<ReportEntity> reportsById) {
		this.reportsById = reportsById;
	}


}
