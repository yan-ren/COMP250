package a4;

public class A4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void compare()
	{
		
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