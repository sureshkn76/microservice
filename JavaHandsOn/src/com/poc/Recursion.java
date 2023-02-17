package com.poc;

import com.poc.domain.FruitNode;

public class Recursion {
	
	public static void main(String[] args) {
		
		FruitNode root = setupData();
		printFruitNames(root);
	}

	
	public static void printFruitNames(FruitNode root) {
		System.out.println("Root Fruit is -> "+root.getFruitName());
		
		for(FruitNode node : root.getChildren()) {
			System.out.println("*** Inside ForLoop Fruit is -> "+node.getFruitName());			
			printFruitNames(node);
			System.out.println("*** After Recursion ForLoop Fruit is -> "+node.getFruitName());
		}
		System.out.println("Root Fruit Exit is -> "+root.getFruitName());
	}

	public static FruitNode setupData() {
		FruitNode root = new FruitNode("Fruits");

		FruitNode tropical = new FruitNode("Tropical");
		FruitNode subTropical = new FruitNode("Subtropical");
		root.addChild(tropical);
		root.addChild(subTropical);
		
		FruitNode mango = new FruitNode("Mango");
		FruitNode banana = new FruitNode("Banana");
		FruitNode guava = new FruitNode("Guava");
		tropical.addChild(mango);
		tropical.addChild(banana);
		tropical.addChild(guava);
		
		FruitNode orange = new FruitNode("Orange");
		FruitNode grapes = new FruitNode("Graps");		
		FruitNode lemon = new FruitNode("Lemon");		
		subTropical.addChild(orange);
		subTropical.addChild(grapes);
		subTropical.addChild(lemon);		
		
		return root;
	}

}
