package Himanshu;
import java.util.Scanner;
public class Average {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the first number:-");
		int a = sc.nextInt();
		Scanner sc1 = new Scanner(System.in);
		System.out.print("Enter the second number:-");
		int b = sc1.nextInt();
		Scanner sc3 = new Scanner(System.in);
		System.out.print("Enter the Third Number:-");
		int c = sc3.nextInt();
		 
		
		float d = a/3+b/3+c/3;
		 
		System.out.print("d:-"+d);

	}

}
