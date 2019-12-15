package com.nwchat.entity;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "report", schema = "public", catalog = "nwchat")
public class ReportEntity {
	private int id;
	private String num;
	private String text;
	private Integer creatorId;
	private Integer orderId;
	private Date at;
	private Collection<CheckListItemEntity> checkListItemsById;
	private OrderEntity ordersByOrderId;
	private Integer state;
	private UserEntity manager;

	@Id
	@GeneratedValue
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
	@Column(name = "text", nullable = true)
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
	@Column(name = "order_id", nullable = true)
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	@Column(name = "state", nullable = true)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ReportEntity that = (ReportEntity) o;
		return id == that.id &&
				Objects.equals(num, that.num) &&
				Objects.equals(text, that.text) &&
				Objects.equals(creatorId, that.creatorId) &&
				Objects.equals(orderId, that.orderId) &&
				Objects.equals(at, that.at) &&
				Objects.equals(checkListItemsById, that.checkListItemsById) &&
				Objects.equals(ordersByOrderId, that.ordersByOrderId) &&
				Objects.equals(state, that.state) &&
				Objects.equals(manager, that.manager);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, num, text, creatorId, orderId, at, checkListItemsById, ordersByOrderId, state, manager);
	}

	@OneToMany(mappedBy = "reportsByReportId")
	public Collection<CheckListItemEntity> getCheckListItemsById() {
		return checkListItemsById;
	}

	public void setCheckListItemsById(Collection<CheckListItemEntity> checkListItemsById) {
		this.checkListItemsById = checkListItemsById;
	}

	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
	public OrderEntity getOrdersByOrderId() {
		return ordersByOrderId;
	}

	public void setOrdersByOrderId(OrderEntity ordersByOrderId) {
		this.ordersByOrderId = ordersByOrderId;
	}
	@ManyToOne
	@JoinColumn(name = "creator_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	public UserEntity getManager(){return manager;}

	public void setManager(UserEntity manager) {
		this.manager = manager;
	}
}
