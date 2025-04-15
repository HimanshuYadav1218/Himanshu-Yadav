// WAP OF REMINDER OF TWO NUMBER
package Himanshu;
import java.util.Scanner;
public class Reminder {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the first number:-");
		int a = sc.nextInt();
		Scanner sc1 = new Scanner(System.in);
		System.out.print("Enter the second number:-");
		int b = sc1.nextInt();
		
		int c=a%b;// (REMINDER GIVE BUT CALL IT PERCENTAGE %)
		System.out.print("Reminder of number:-"+c);
		
	}

}
