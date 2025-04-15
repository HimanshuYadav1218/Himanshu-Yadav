// WAP OF SWAP TWO NUMBER BUT VALUE TAKEN BY USER:
package Himanshu;
import java.util.Scanner;

public class SwapTwoNumber {
	public static void main(String[] args)
	{
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the a:-");
		int a = sc.nextInt();
		
		Scanner sc1 = new Scanner(System.in);
		System.out.print("Enter the b:-");
		int b = sc1.nextInt();
		
		a=a^b;
		b=a^b;  //BINARY CONVERSION
		a=a^b;
		 
		System.out.println("a:-"+a);
		System.out.println("b:-"+b);
		
	}

}
