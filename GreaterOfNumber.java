// WAP TO FIND THE GREATER NUM OF TWO NUMBER:
package Himanshu;

import java.util.Scanner;
public class GreaterOfNumber {
	public static void main(String[] args)
	{
		// VALUE TAKEN BY USER:
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of a:-");
		int a = sc.nextInt();
		
		Scanner sc1 = new Scanner(System.in);
		System.out.print("Enter the number of b:-");
		int b = sc1.nextInt();
		
		if(a>b)
			System.out.print("a is > than b");
		else
			System.out.print("b is > than a");
	}

}
