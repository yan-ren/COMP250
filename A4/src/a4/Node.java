package a4;

import java.util.ArrayList;
import java.util.Arrays;

public class Node 
{
	boolean[] pixels=new boolean[33];
	private String moveDescription;
	private Node parent;
	ArrayList<Node> children = new ArrayList<Node>();
	//
	public Node()
	{
		
	}
	public Node(boolean[] pixels){		
		this.pixels = pixels;			
	}
	//
	public boolean[] getPixels() {
		return pixels;
	}
	public void setPixels(boolean[] pixels) {
		this.pixels = pixels;
	}
	public String getMoveDescription() {
		return moveDescription;
	}
	public void addMoveDescription(String moveDescription) {
		this.moveDescription = this.moveDescription +","+ moveDescription;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public ArrayList<Node> getChildren() {
		return children;
	}
	//
	public void addChild(Node child){
		children.add(child);
	}
	//
	@Override
	public String toString() {
		return "Node [pixels=" + Arrays.toString(pixels) + ", moveDescription=" + moveDescription + ", children="
				+ children + "]";
	}
	//
	public int getChildrenNumber()
	{
		return this.children.size();
	}
	//

}
