package Himanshu;
import java.util.Scanner;

public class Sumofinfinteoddnumber {
	public static void main(String[] args)
	{
		int m=0;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the last number:-");
		int a = sc.nextInt();
		{
			for(int i=1; i<=a; i=i+2)
				
				m=m+i;
			
			
		}
		System.out.println(m);
	}

}
