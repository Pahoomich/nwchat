package com.nwchat.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "report", schema = "public", catalog = "nwchat")
public class ReportEntity {
	private int id;
	private String num;
	private Integer text;
	private Integer creatorId;
	private Integer orderId;
	private Date at;
	private Collection<CheckListItemEntity> checkListItemsById;
	private OrderEntity ordersByOrderId;

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
	@Column(name = "text", nullable = true)
	public Integer getText() {
		return text;
	}

	public void setText(Integer text) {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ReportEntity that = (ReportEntity) o;

		if (id != that.id) return false;
		if (num != null ? !num.equals(that.num) : that.num != null) return false;
		if (text != null ? !text.equals(that.text) : that.text != null) return false;
		if (creatorId != null ? !creatorId.equals(that.creatorId) : that.creatorId != null) return false;
		if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
		if (at != null ? !at.equals(that.at) : that.at != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (num != null ? num.hashCode() : 0);
		result = 31 * result + (text != null ? text.hashCode() : 0);
		result = 31 * result + (creatorId != null ? creatorId.hashCode() : 0);
		result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
		result = 31 * result + (at != null ? at.hashCode() : 0);
		return result;
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
}
