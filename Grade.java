// WAP TO FIND THE GRADE IN MARKS:
package Himanshu;

import java.util.Scanner;
public class Grade {
	public static void main(String[] args)
	{
	Scanner sc = new Scanner(System.in);
	System.out.print("Enter the marks:-");
	int marks = sc.nextInt();
	{
		if(marks>=90)
			System.out.print("Grade A");
		else if(marks>=80)
			System.out.print("Grade B");
		else if(marks>=70)
			System.out.print("Grade c");
		else if(marks>=60)
			System.out.print("Grade D");
		else if(marks>=33)
			System.out.print("Pass");
		else if(marks<=33)
			System.out.print("Fail");
	}
	}
	}
