// WAP OF SUM OF TWO NUMBER BUT VALUE TAKEN BY USER
package Himanshu;
import java.util.Scanner;
public class SumUser {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the first nummber:-");
		int a = sc.nextInt();
		Scanner sc1 = new Scanner(System.in);
		System.out.print("Enter the second number:-");
		int b = sc1.nextInt();
		
		int c=a+b;
		System.out.print("Total the number:-"+c);
		
	}

}
