package a4;

public class Tree {
	Node root;
	
	public void add(boolean[] data)
	{
		Node nodeToAdd = new Node(data);
		if (root == null){root = nodeToAdd;}
		
	}
}
