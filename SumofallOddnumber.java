package Himanshu;
import java.util.Scanner;

public class SumofallOddnumber {
	public static void main(String[] args)
	{
	int	s=0;
	Scanner sc = new Scanner(System.in);
	System.out.print("enter the last number:-");
	int a = sc.nextInt();
	{
		for(int i=1; i<=a; i=i+2)
			
	
		s=s+i;
	}
	System.out.println(s);
	
}
}