package Himanshu;

import java.util.Scanner;
public class Drivingliensence {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the age:-");
		int age = sc.nextInt();
		{
			if(age>=18)
				System.out.print("You can drive:-"+age);
			else
				System.out.print("You can't drive:-"+age);
		}
	}

}
