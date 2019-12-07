package com.nwchat.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "check_list_item", schema = "public", catalog = "nwchat")
public class CheckListItemEntity {
	private int id;
	private Integer docId;
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
	public Integer getDocId() {
		return docId;
	}

	public void setDocId(Integer docId) {
		this.docId = docId;
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

		if (id != that.id) return false;
		if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (dateStartWork != null ? !dateStartWork.equals(that.dateStartWork) : that.dateStartWork != null)
			return false;
		if (dateEndWork != null ? !dateEndWork.equals(that.dateEndWork) : that.dateEndWork != null) return false;
		if (reportId != null ? !reportId.equals(that.reportId) : that.reportId != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (docId != null ? docId.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (dateStartWork != null ? dateStartWork.hashCode() : 0);
		result = 31 * result + (dateEndWork != null ? dateEndWork.hashCode() : 0);
		result = 31 * result + (reportId != null ? reportId.hashCode() : 0);
		return result;
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
