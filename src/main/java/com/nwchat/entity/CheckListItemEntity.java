package com.nwchat.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "check_list_item", schema = "public", catalog = "nwchat")
public class CheckListItemEntity {
	private int id;
	private Integer orderId;
	private String name;
	private Date dateStartWork;
	private Date dateEndWork;
	private Integer reportId;
	private Collection<CheckListItemChatEntity> checkListItemChatsById;
	private OrderEntity ordersByDocId;
	private ReportEntity reportsByReportId;

	@Id
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "order_id", nullable = true)
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer docId) {
		this.orderId = docId;
	}

	@Basic
	@Column(name = "name", nullable = true, length = -1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "date_start_work", nullable = true)
	public Date getDateStartWork() {
		return dateStartWork;
	}

	public void setDateStartWork(Date dateStartWork) {
		this.dateStartWork = dateStartWork;
	}

	@Basic
	@Column(name = "date_end_work", nullable = true)
	public Date getDateEndWork() {
		return dateEndWork;
	}

	public void setDateEndWork(Date dateEndWork) {
		this.dateEndWork = dateEndWork;
	}

	@Basic
	@Column(name = "report_id", nullable = true)
	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CheckListItemEntity that = (CheckListItemEntity) o;


		return Objects.equals(id, that.id) &&
				Objects.equals(orderId, that.orderId) &&
				Objects.equals(name, that.name) &&
				Objects.equals(dateStartWork, that.dateStartWork) &&
				Objects.equals(dateEndWork, that.dateEndWork) &&
				Objects.equals(reportId, that.reportId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, orderId, name,dateStartWork,dateEndWork,reportId);
	}

	@OneToMany(mappedBy = "checkListItemsByCheckListItemId")
	public Collection<CheckListItemChatEntity> getCheckListItemChatsById() {
		return checkListItemChatsById;
	}

	public void setCheckListItemChatsById(Collection<CheckListItemChatEntity> checkListItemChatsById) {
		this.checkListItemChatsById = checkListItemChatsById;
	}

	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
	public OrderEntity getOrdersByDocId() {
		return ordersByDocId;
	}

	public void setOrdersByDocId(OrderEntity ordersByDocId) {
		this.ordersByDocId = ordersByDocId;
	}

	@ManyToOne
	@JoinColumn(name = "report_id", referencedColumnName = "id", insertable = false, updatable = false)
	public ReportEntity getReportsByReportId() {
		return reportsByReportId;
	}

	public void setReportsByReportId(ReportEntity reportsByReportId) {
		this.reportsByReportId = reportsByReportId;
	}
}
