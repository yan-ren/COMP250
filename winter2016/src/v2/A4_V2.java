package v2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class A4_V2{
	
	public final static int[][] triplets = {{0,1,2}, {3,4,5},
							{6,7,8}, {7,8,9}, {8,9,10}, {9,10,11},{10,11,12},
							{13,14,15},{14,15,16},{15,16,17},{16,17,18},{17,18,19},
							{20,21,22},{21,22,23},{22,23,24},{23,24,25},{24,25,26},
							{27,28,29},{30,31,32},
							{12,19,26},{11,18,25},
							{2,5,10},{5,10,17},{10,17,24},{17,24,29},{24,29,32},
							{1,4,9},{4,9,16},{9,16,23},{16,23,28},{23,28,31},
							{0,3,8},{3,8,15},{8,15,22},{15,22,27},{22,27,30},
							{7,14,21},{6,13,20}
	};

	public static void main(String[] args) {		
		HiRiQ W=new HiRiQ((byte) 3);
		//check if W is already a solved configuration
		if(W.IsSolved()==true){System.out.println("input configure is already solved");System.exit(0);}
		
		System.out.println("starting configure");
		W.print(); 
		
		Node root = new Node();
		root.setCongigWeight(W.getConfig(), W.getWeight());

		int level=1; 

		/*
		 * build a virtual tree, explore this tree using BFS
		 * use a Set to store all explored node to avoid traverse same node twice
		 * two arrayLists are for traversing the tree
		 */
		Set<Node> allNodes = new HashSet<Node>();
		//ArrayList<Node> allNodes = new ArrayList<Node>();
		ArrayList<Node> currentLevel = new ArrayList<Node>();		
		ArrayList<Node> nextLevel = new ArrayList<Node>();
		//add root node to Tree Set, add root to current level, we start with root node.
		//arraylist nextLevel is for storing the children of each node in currentLevel
		allNodes.add(root);
		currentLevel.add(root);
		//
		while(true){			
			System.out.println("current level: "+ level+", nodes in currentLevel:  "+ currentLevel.size());
			if(currentLevel.size()==0){break;}
			//create next level of tree by analyzing each node in current level
			for(int i=0; i<currentLevel.size();i++)
			{
				//each node in current will have #true in check list
				boolean[] checkList;
				//when nodes in current level is over 1000, perform W-sub, when nodes in current level is less than 1000
				//perform W or B-sub to increase nodes samples
				if(currentLevel.size() < 1000){
					checkList = checkSubstitutionTriplets_WB(currentLevel.get(i));	
				}else{
					checkList = checkSubstitutionTriplets_W(currentLevel.get(i));
				}
				//
				for(int j=0; j<checkList.length; j++){
					//for each child node
					if(checkList[j]==true){
						Node nodeToBeAdded = new Node(currentLevel.get(i));
						nodeToBeAdded = doSubstitution(nodeToBeAdded, j);
						//check if we already traverse the nodeToBeAdded
						if(allNodes.contains(nodeToBeAdded)==true){
							continue;
						}else{
							allNodes.add(nodeToBeAdded);
						}
						//
						W.setConfig_Weight(nodeToBeAdded.getConfig(), nodeToBeAdded.getWeight());
						
//						if(level > 18){
//							W.print();
//						}
						
						//check if nodeToBeAdded contains a solved config
						if(W.IsSolved()==true)
						{
							System.out.println("configure is solved");
							System.out.println(nodeToBeAdded);
							W.print();
							System.exit(0);
						}
						else
						{
							nextLevel.add(nodeToBeAdded);
						}
						
					}										
				}
			}						
			//next level creation finished
			//clear current level, pass next level to current level and clear next level
			currentLevel.clear();
			currentLevel.addAll(nextLevel);
			nextLevel.clear();
			
			currentLevel.trimToSize();
			nextLevel.trimToSize();
			level++;
			}		
		}
	
	//perform W or B-substitution at tripletsIndex
	public static Node doSubstitution(Node a, int tripletsIndex){
		HiRiQ config = new HiRiQ();
		config.setConfig_Weight(a.getConfig(), a.getWeight());
		
		boolean[] pixels = new boolean[33];
		pixels = config.load(pixels);
		
		Node newNode = a;
		String moveDescription;
		
		if(pixels[triplets[tripletsIndex][0]]==pixels[triplets[tripletsIndex][1]])
		{
			if(pixels[triplets[tripletsIndex][0]]==false){
				pixels[triplets[tripletsIndex][0]]=true;
				pixels[triplets[tripletsIndex][1]]=true;
				pixels[triplets[tripletsIndex][2]]=false;
				moveDescription = triplets[tripletsIndex][0]+"B"+triplets[tripletsIndex][2];
				newNode.addMoveDescription(moveDescription);
			}else{
				pixels[triplets[tripletsIndex][0]]=false;
				pixels[triplets[tripletsIndex][1]]=false;
				pixels[triplets[tripletsIndex][2]]=true;
				moveDescription = triplets[tripletsIndex][0]+"W"+triplets[tripletsIndex][2];
				newNode.addMoveDescription(moveDescription);
			}
		}
		else
		{
			if(pixels[triplets[tripletsIndex][1]]==false){
				pixels[triplets[tripletsIndex][1]]=true;
				pixels[triplets[tripletsIndex][2]]=true;
				pixels[triplets[tripletsIndex][0]]=false;
				moveDescription = triplets[tripletsIndex][0]+"B"+triplets[tripletsIndex][2];
				newNode.addMoveDescription(moveDescription);
			}else{
				pixels[triplets[tripletsIndex][1]]=false;
				pixels[triplets[tripletsIndex][2]]=false;
				pixels[triplets[tripletsIndex][0]]=true;
				moveDescription = triplets[tripletsIndex][0]+"W"+triplets[tripletsIndex][2];
				newNode.addMoveDescription(moveDescription);
			}
		}
		config.store(pixels);
		newNode.setCongigWeight(config.getConfig(), config.getWeight());
		return newNode;
	}
	
	//check Triplets that satisfy W-substitution and B-substitution
	//return a boolean array containing 38 element, each element corresponding to one Triplet
	//if Triplet can do W-sub or B-sub, then boolean element in the array is true. if not, boolean element is false.
	public static boolean[] checkSubstitutionTriplets_WB(Node a)
	{
		HiRiQ config = new HiRiQ();
		config.setConfig_Weight(a.getConfig(), a.getWeight());
		boolean[] pixels = new boolean[33];
		pixels = config.load(pixels);
		boolean[] tripletsCheckList = new boolean[triplets.length];
		for(int i = 0; i<triplets.length; i++){
			//allow W and B sub
			if(pixels[triplets[i][0]]==pixels[triplets[i][1]] || pixels[triplets[i][1]]==pixels[triplets[i][2]]){
				if(pixels[triplets[i][0]]!=pixels[triplets[i][2]]){					
					tripletsCheckList[i] = true;					
				}				
			}
		}
		return tripletsCheckList;
	}
	
	//check Triplets that satisfy W-substitution
	//return a boolean array containing 38 element, each element corresponding to one Triplet
	//if Triplet can do W-sub, then boolean element in the array is true. if not, boolean element is false.
	public static boolean[] checkSubstitutionTriplets_W(Node a)
	{
		HiRiQ config = new HiRiQ();
		config.setConfig_Weight(a.getConfig(), a.getWeight());
		// array pixels stores the boolean configuration
		boolean[] pixels = new boolean[33];
		pixels = config.load(pixels);
		// tripletsCheckList stores which Triplet can do W-substitution
		boolean[] tripletsCheckList = new boolean[triplets.length];
		for(int i = 0; i<triplets.length; i++){
			if((pixels[triplets[i][0]]==true) && (pixels[triplets[i][1]] ==true) && (pixels[triplets[i][2]] == false)){		
				tripletsCheckList[i] = true;					
			}
			else if((pixels[triplets[i][0]]==false) && (pixels[triplets[i][1]] ==true) && (pixels[triplets[i][2]] == true))
			{
				tripletsCheckList[i] = true;
			}
				
		}
		return tripletsCheckList;
	}
}

class HiRiQ
{
//int is used to reduce storage to a minimum...
  public int config;
  public byte weight;

  public HiRiQ() {
	this.config = 0;
	this.weight = 0;
}
//initialize the configuration to one of 4 START setups n=0,1,2,3
  HiRiQ(byte n)
  {
	  if (n==0)
	  {config=65536/2;weight=1;}
	  else
	  if (n==1)
	  {config=1626;weight=6;}
	  else
	  if (n==2)
	  {config=-1140868948; weight=10;}
	  else
	  if (n==3)
	  {config=-411153748; weight=13;}
	  else if(n==4)
	  {
		  config=-1026781599; weight=21;
	  }
	  else
	  {config=-2147450879; weight=32;}
  
	  /*
	  if (n==0)
	   {config=65536/2;weight=1;}
	  else
		  if (n==1)
		  {config=4403916;weight=11;}
		  else
			  if (n==2)
			  {config=-1026781599; weight=21;}
			  else
			  {config=-2147450879; weight=32;}
	   */
  } 
  //initialize the configuration to one of 4 START setups n=0,10,20,30
  public void setConfig_Weight(int config, byte weight){
	  this.config = config;
	  this.weight = weight;
  }
  
  public int getConfig() {
	return config;
}

public void setConfig(int config) {
	this.config = config;
}

public byte getWeight() {
	return weight;
}

public void setWeight(byte weight) {
	this.weight = weight;
}

boolean IsSolved()
  {
	  return( (config==65536/2) && (weight==1) );
  }

//transforms the array of 33 booleans to an (int) cinfig and a (byte) weight.
  public void store(boolean[] B)
  {
  int a=1;
  config=0;
  weight=(byte) 0;
  if (B[0]) {weight++;}
  for (int i=1; i<32; i++)
   {
   if (B[i]) {config=config+a;weight++;}
   a=2*a;
   }
  if (B[32]) {config=-config;weight++;}
  }

//transform the int representation to an array of booleans.
//the weight (byte) is necessary because only 32 bits are memorized
//and so the 33rd is decided based on the fact that the config has the
//correct weight or not.
  public boolean[] load(boolean[] B)
  {
  byte count=0;
  int fig=config;
  B[32]=fig<0;
  if (B[32]) {fig=-fig;count++;}
  int a=2;
  for (int i=1; i<32; i++)
   {
   B[i]= fig%a>0;
   if (B[i]) {fig=fig-a/2;count++;}
   a=2*a;
   }
  B[0]= count<weight;
  return(B);
  }
  
//prints the int representation to an array of booleans.
//the weight (byte) is necessary because only 32 bits are memorized
//and so the 33rd is decided based on the fact that the config has the
//correct weight or not.
  public void printB(boolean Z)
  {if (Z) {System.out.print("[ ]");} else {System.out.print("[@]");}}
  
  public void print()
  {
  byte count=0;
  int fig=config;
  boolean next,last=fig<0;
  if (last) {fig=-fig;count++;}
  int a=2;
  for (int i=1; i<32; i++)
   {
   next= fig%a>0;
   if (next) {fig=fig-a/2;count++;}
   a=2*a;
   }
  next= count<weight;
  
  count=0;
  fig=config;
  if (last) {fig=-fig;count++;}
  a=2;

  System.out.print("      ") ; printB(next);
  for (int i=1; i<32; i++)
   {
   next= fig%a>0;
   if (next) {fig=fig-a/2;count++;}
   a=2*a;
   printB(next);
   if (i==2 || i==5 || i==12 || i==19 || i==26 || i==29) {System.out.println() ;}
   if (i==2 || i==26 || i==29) {System.out.print("      ") ;};
   }
   printB(last); System.out.println() ;

  }
}