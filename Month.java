// WAp to Find the month in days:
package Himanshu;

import java.util.Scanner;
public class Month {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the days:-");
		int days = sc.nextInt();
		
		int month = days/30;
		int day = days%30;
		
		System.out.println("month:-"+month);
		System.out.println("day:-"+day);
		
	}
}
