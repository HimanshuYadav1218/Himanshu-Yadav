// WAP TO FIND THE GREATER NUMBER OF THREE NUMBER:`
package Himanshu;

import java.util.Scanner;
public class GreaterOfThreeNumber {
	public static void main(String[] args)
	{
		// VALUE TAKEN BY USER 
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of a:-");
		int a = sc.nextInt();
		
		Scanner sc1 = new Scanner(System.in);
		System.out.print("Enter the number of b:-");
		int b = sc.nextInt();
		
		Scanner sc2 = new Scanner(System.in);
		System.out.print("Enter the number of c:-");
		int c = sc2.nextInt();
		{
			if(a>b)
				if(a>c)
					System.out.print("a is > than all Number");
				else
					System.out.print("c is > than all Number");
			else
				if(b>c)
					System.out.print("b is > than all number");
				else
					System.out.print("c is > than all Number");
		}
		}

}
