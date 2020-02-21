package v1;

import java.util.Arrays;

public class Node implements Comparable<Node>
{
	boolean[] pixels;
	private String moveDescription = "";
	//private Node parent;
	//ArrayList<Node> children = new ArrayList<Node>();
	//
	public Node()
	{
		pixels=new boolean[33];
	}
	public Node(Node a){		
		this.pixels = Arrays.copyOf(a.pixels, a.pixels.length);
		this.moveDescription = a.moveDescription;
	}
	//
	public boolean[] getPixels() {
		return pixels;
	}
	public String getMoveDescription() {
		return moveDescription;
	}
	public void addMoveDescription(String moveDescription) {
		if(this.moveDescription.equals("")){
			this.moveDescription = moveDescription;
		}
		else{this.moveDescription = this.moveDescription +","+ moveDescription;}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(pixels);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (!Arrays.equals(pixels, other.pixels))
			return false;
		return true;
	}
	/*
	//
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
		child.parent = this;
		children.add(child);
	}
	public void add(Node child){
		addChild(child);
	}
	//
	 */	
	@Override
	public String toString() {
		return "Node [moveDescription=" + moveDescription + "]";
	}
	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return moveDescription.compareTo(o.moveDescription);
	}
}
