package com.poc.domain;

import java.util.ArrayList;
import java.util.List;

public class FruitNode {

	String fruitName;
	List<FruitNode> children;

	public FruitNode(String fruitName) {
		this.fruitName = fruitName;
		children = new ArrayList<>();
	}
	
	public String getFruitName() {
		return fruitName;
	}
	
	public void setFruitName(String fruitName) {
		this.fruitName = fruitName;
	}
	
	public List<FruitNode> getChildren() {
		return children;
	}
	
	public void addChild(FruitNode child) {
    	children.add(child);
    }
}
