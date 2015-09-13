/*Course Number & Section: CIS5200
Assignment Designation: Project 1 Chapter 8.16
Name: Nikkita Hirayama
*/

package hugeinteger;

import java.io.IOException;

public class HugeInteger {

	
private int[] hugeInt;//teacher used intArray, but I changed to hugeInt
private int numDigits;  // stores the number of digits in intArray


	
	HugeInteger()//default constructor
	{
		hugeInt= new int[40];//contains 40 characters
		numDigits = 0;
	}
	HugeInteger(String s)
	{
	   hugeInt = new int[40];
	   numDigits = 0;
	   parse(s);
	 }

	
	void parse(String parseString)//I deleted public because unless it is labeled otherwise, it assumes public
	{
		
		//as the array begins with the last letter, we move backwards in the string
		for(int x = parseString.length()-1; x >= 0;x--)
		{
			switch(parseString.charAt(x))//check the character to make sure it is a number
			{
				case 0: case 1: case 2: case 3: case 4:
				case 5: case 6: case 7: case 8: case 9:
					this.hugeInt[numDigits] = parseString.charAt(x);//add number to hugeinteger
					numDigits++;
				break;
			}
		}
	}
	
	public String toString()
	{
		String toStringHolder = "";//temp variable to hold returnable String
		
		for(int x = this.numDigits-1; x >40; x++)//goes through each digit in the array
		{
		
			if(Integer.toString(this.hugeInt[x]) !=null)//makes sure not to include a null digit when adding to the String
				toStringHolder += Integer.toString(this.hugeInt[x]);
		}
		return toStringHolder;
	}
	
	int getHugeIntegerLength()
	{
		return this.numDigits;
	}
	
	static HugeInteger add(HugeInteger hugeInt1, HugeInteger hugeInt2)
	  {
		 HugeInteger hugeInt3 = new HugeInteger();//to store the added integers
		 int carryover = 0;
		 for(int x = 39; x > 0; x--)
			{
				hugeInt3.hugeInt[x]= hugeInt1.hugeInt[x] + hugeInt2.hugeInt[x]+carryover;
				if(hugeInt3.hugeInt[x]>9)//if the added number is greater than 9 we need to carry over to next digit
				{
					carryover = hugeInt3.hugeInt[x]/10;//will add number in the 10th digit
					hugeInt3.hugeInt[x]%=10;//will add thenumber in the ones
				}
				else//if the added number has no carryover, set carryover to 0
					carryover = 0;
			}   
		 return hugeInt3;
	  }

	void add(HugeInteger addInt)//my added version of add(allows you to add to the current huge integer 
	{
			int carryover = 0;//temporary variable that carries over numbers when adding
			for(int x = 39; x > 0; x--)
			{
				this.hugeInt[x]+= addInt.hugeInt[x]+carryover;
				if(this.hugeInt[x]>9)//if the added number is greater than 9 we need to carry over to next digit
				{
					carryover = this.hugeInt[x]/10;//will add number in the 10th digit
					this.hugeInt[x]%=10;//will add thenumber in the ones
				}
				else//if the added number has no carryover, set carryover to 0
					carryover = 0;
			}
	}
	
	
	
	
	void subtract(HugeInteger subInteger)
	{
		
			for(int x = 39; x >0; x--)
			{
				// check to make sure the subtraction will be above 0
				//if not we will need to carry over from the next digit
				if((this.hugeInt[x] - subInteger.hugeInt[x])>= 0)
				{		
					this.hugeInt[x]-= subInteger.hugeInt[x];
				}
				else
				{
					this.hugeInt[x-1] -=1;//will use to help subtract 
					this.hugeInt[x] = (this.hugeInt[x]+10) -subInteger.hugeInt[x];
				}
			}
		
	}
	
	static HugeInteger subtract(HugeInteger hugeInt1, HugeInteger hugeInt2)
	  {
		HugeInteger hugeInt3 = new HugeInteger();
		int subtract = 0;//will help keep track on whether or not we subtract a 1 from the next digit in line to carry over for a number
		for(int x = 39; x >0; x--)
		{
			// check to make sure the subtraction will be above 0
			//if not we will need to carry over from the next digit
			if((hugeInt1.hugeInt[x] - hugeInt2.hugeInt[x]-subtract)>= 0)
			{		
				hugeInt3.hugeInt[x] = hugeInt1.hugeInt[x]- hugeInt2.hugeInt[x]-subtract;
				subtract = 0;//change subtract to 0 as we did not carry over to help subtract
			}
			else
			{
				subtract = 1;//will use to help subtract 
				hugeInt3.hugeInt[x] = (hugeInt1.hugeInt[x]+10-subtract) - hugeInt2.hugeInt[x];
			}
		}
		return hugeInt3;
	  }

	
	
	
	
	
	boolean isEqualTo(HugeInteger compareInt) 
	{
		
			for(int x = 39; x >=0; x--)
			{
				if(this.hugeInt[x] != compareInt.hugeInt[x])
					return false;//return a false if there is a digit not equal
			}
			return true;//if false was not returned, then true will return
		
	}
	
	static boolean isEqualTo(HugeInteger hugeInt1, HugeInteger hugeInt2)
	  {
	    if(hugeInt1.isEqualTo(hugeInt2))
	    	return true;
	    return false;

	  }
	
	
	
	
	
	boolean isNotEqualTo(HugeInteger compareInt) 
	{
		if(isEqualTo(compareInt))//if they are equal then not equal to is false
			return false;
		return true;
	}
	
	 static boolean isNotEqualTo(HugeInteger hugeInt1, HugeInteger hugeInt2) throws IOException
	  {
		 if(hugeInt1.isNotEqualTo(hugeInt2))
			 return true;
		 return false;

	  }
	
	
	 
	 
	 boolean isGreaterThan(HugeInteger compareInt) 
	{
			for(int x =0; x<40; x++)
			{
				//check to see if array has reached the first numerical value
				if(Integer.toString(hugeInt[x])!=null || Integer.toString(compareInt.hugeInt[x]) != null)
				{	if(this.hugeInt[x] > compareInt.hugeInt[x])
						return true;
					else//exit look
						break;
				}
			}
			return false;
		
	}
	
	 static boolean isGreaterThan(HugeInteger hugeInt1, HugeInteger hugeInt2) 
	  {
	    if(hugeInt1.isGreaterThan(hugeInt2))
	    	return true;
	    return false;

	  }
	
	 
	 
	 
	 
	boolean isLessThan(HugeInteger compareInt)
	{
		if(isGreaterThan(compareInt) || isEqualTo(compareInt))//if it is greater than or equal return false
			return false;
		return true;
	}
	
	static boolean isLessThan(HugeInteger hugeInt1, HugeInteger hugeInt2) throws IOException
	{
		if(hugeInt1.isLessThan(hugeInt2))
			return true;
		return false;
	 }
	
	
	
	
	boolean isGreaterThanOrEqualTo(HugeInteger compareInt) 
	{
		if(isGreaterThan(compareInt) || isEqualTo(compareInt))//if greater than or equal, return true
			return true;
		return false;
	}
	
	  static boolean isGreaterThanOrEqualTo(HugeInteger hugeInt1, HugeInteger hugeInt2) 
	  {
	    // return true if the value represented by 
	    // elements of hugeInt1.intArray is greater than or equal to
	    // value represented by elements of hughInt2.intArray
		  if(hugeInt1.isGreaterThanOrEqualTo(hugeInt2))
			  return true;
		  return false;
	  }

	
	  
	
	boolean isLessThanOrEqualTo(HugeInteger compareInt) 
	{
		if(isLessThan(compareInt) || isEqualTo(compareInt))//if less than or equal return true
			return true;
		return false;
	}
	
	static boolean isLessThanOrEqualTo(HugeInteger hugeInt1, HugeInteger hugeInt2) throws IOException
	{
		if(hugeInt1.isLessThanOrEqualTo(hugeInt2))
			return true;
		return false;//if true is not returned then false is automatically returned
			
	}
	
	boolean isZero()//tests current object
	{
		if(hugeInt.toString() == null)//if the last integer is null there is nothing in this array as the first input is always in the last array
			return true;
		return false;
	}
	static boolean isZero(HugeInteger hugeInt1 )//teacher's addition
	  {
		 if(hugeInt1.toString() == null)//if the last integer is null there is nothing in this array as the first input is always in the last array
				return true;
			return false;
	    // return true if the value represented by 
	    // elements of hugeInt1.intArray is 0 
	  }
	
	
	
	  

	 

	}

