package a4;

public class A4 {
	
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
		//System.out.println(triplets[37].length);
		
		HiRiQ W=new HiRiQ((byte) 0) ;
		//W.print(); System.out.println(W.IsSolved());
		Node root = new Node();
		root.pixels = W.load(root.pixels);
		//System.out.println(root);
		
		
	}
	
	public static Node doSubstitution(Node a, int tripletsIndex){
		Node newNode = a;
		String moveDescription;
		if(newNode.pixels[triplets[tripletsIndex][0]]==newNode.pixels[triplets[tripletsIndex][1]])
		{
			if(newNode.pixels[triplets[tripletsIndex][0]]==false){
				newNode.pixels[triplets[tripletsIndex][0]]=true;
				newNode.pixels[triplets[tripletsIndex][1]]=true;
				moveDescription = triplets[tripletsIndex][0]+"B"+triplets[tripletsIndex][2];
				newNode.addMoveDescription(moveDescription);
			}else{
				newNode.pixels[triplets[tripletsIndex][0]]=false;
				newNode.pixels[triplets[tripletsIndex][1]]=false;
				moveDescription = triplets[tripletsIndex][0]+"W"+triplets[tripletsIndex][2];
				newNode.addMoveDescription(moveDescription);
			}
		}
		else
		{
			if(newNode.pixels[triplets[tripletsIndex][1]]==false){
				newNode.pixels[triplets[tripletsIndex][1]]=true;
				newNode.pixels[triplets[tripletsIndex][2]]=true;
				moveDescription = triplets[tripletsIndex][0]+"B"+triplets[tripletsIndex][2];
				newNode.addMoveDescription(moveDescription);
			}else{
				newNode.pixels[triplets[tripletsIndex][1]]=false;
				newNode.pixels[triplets[tripletsIndex][2]]=false;
				moveDescription = triplets[tripletsIndex][0]+"W"+triplets[tripletsIndex][2];
				newNode.addMoveDescription(moveDescription);
			}
		}
		return newNode;
	}
	public static boolean[] checkSubstitutionTriplets(Node a)
	{
		int countForSubstitutionTriplets = 0;
		boolean[] tripletsCheckList = new boolean[triplets.length]; 
		for(int i = 0; i<triplets.length; i++){
			if(a.pixels[triplets[i][0]]==a.pixels[triplets[i][1]] || a.pixels[triplets[i][1]]==a.pixels[triplets[i][2]]){
				tripletsCheckList[i] = true;
				countForSubstitutionTriplets++;
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

//initialize the configuration to one of 4 START setups n=0,1,2,3
  HiRiQ(byte n)
  {
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
  }
  
  //initialize the configuration to one of 4 START setups n=0,10,20,30

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