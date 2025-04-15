// WAP TO FIND EVEN AND ODD NUMBER BUT VALUE TAKEN BY USER:
package Himanshu;

import java.util.Scanner;
public class EvenOdd {
	
		public static void main(String[] args)
		{
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter the number:-");
			int num = sc.nextInt();
			{
				
				if(num%2==0) // IF ANY VALUE REMINDER(NUM==0,EVEN BUT NUM=!0,ODD
					System.out.print("Number is Even");
				else
					System.out.print("Number is Odd");
			
					
			}
		}
	}