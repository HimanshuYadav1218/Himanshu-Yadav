// WAp to print any NUmber:
package Himanshu;
import java.util.Scanner;
public class WhileTableAnyOne {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of table:-");
		int n = sc.nextInt();
		{
			int i=n;
			while( i<=n*10)
			{
				System.out.println(i);
				i=i+n;
			}
		}
	}

}
