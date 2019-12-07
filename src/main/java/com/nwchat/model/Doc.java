//package com.nwchat.model;
//
//import javax.persistence.*;
//import java.sql.Timestamp;
//
//@Entity
//@Table(name = "docs")
//public class Doc {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "id")
//	private long id;
//
//	@Column(name = "title")
//	private String title;
//
//	@Column(name = "text")
//	private String text;
//
//	@Column(name = "date_create")
//	private Timestamp dateCreate;
//
//	@Column(name = "date_update")
//	private Timestamp dateUpdate;
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getText() {
//		return text;
//	}
//
//	public void setText(String text) {
//		this.text = text;
//	}
//
//	public Timestamp getDateCreate() {
//		return dateCreate;
//	}
//
//	public void setDateCreate(Timestamp dateCreate) {
//		this.dateCreate = dateCreate;
//	}
//
//	public Timestamp getDateUpdate() {
//		return dateUpdate;
//	}
//
//	public void setDateUpdate(Timestamp dateUpdate) {
//		this.dateUpdate = dateUpdate;
//	}
//
//	@PrePersist
//	protected void onCreate() {
//		dateCreate = new Timestamp(System.currentTimeMillis());
//		dateUpdate = dateCreate;
//	}
//
//	@PreUpdate
//	protected void onUpdate() {
//		dateUpdate = new Timestamp(System.currentTimeMillis());
//	}
//}
