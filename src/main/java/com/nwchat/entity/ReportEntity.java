package com.nwchat.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

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
		return Objects.equals(id, that.id) &&
				Objects.equals(num, that.num)&&
				Objects.equals(text, that.text)&&
				Objects.equals(creatorId, that.creatorId)&&
				Objects.equals(orderId, that.orderId)&&
				Objects.equals(at, that.at);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, num,text,creatorId,orderId,at);
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
