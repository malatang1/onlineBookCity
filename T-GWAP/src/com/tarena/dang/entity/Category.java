package com.tarena.dang.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable{
	private int id;
	private int turn;
	private String en_name;
	private String name;
	private String description;
	private int parent_id;
	private List<Category> subCats=new ArrayList<Category>();
	private int pnum;
	
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public List<Category> getSubCats() {
		return subCats;
	}
	public void setSubCats(List<Category> subCats) {
		this.subCats = subCats;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEn_name() {
		return en_name;
	}
	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
}
