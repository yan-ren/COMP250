package a4;

import java.util.ArrayList;
import java.util.Arrays;

public class Node 
{
	boolean[] pixels=new boolean[33];
	String moveDescription;
	ArrayList<Node> children = new ArrayList<Node>();
	//
	public Node()
	{
		
	}
	public Node(boolean[] pixels){		
		this.pixels = pixels;			
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
