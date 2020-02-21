package v2;

public class Node
{
	//this variable is for tracking sequence of substitution
	private String moveDescription = "";
	//Node only store two integer to stand for boolean array to save memory space
	private int config;
	private byte weight;	
	//
	public Node()
	{
		this.config = 0;
		this.weight = 0;		
	}
	public Node(Node a){		
		this.config = a.config;
		this.weight = a.weight;
		this.moveDescription = a.moveDescription;
	}
	//
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + config;
		result = prime * result + weight;
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
		if (config != other.config)
			return false;
		if (weight != other.weight)
			return false;
		return true;
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
	public int getConfig() {
		return config;
	}
	public byte getWeight() {
		return weight;
	}
	public void setCongigWeight(int config, byte weight){
		this.config = config;
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "Node [moveDescription=" + moveDescription + "]";
	}
}
