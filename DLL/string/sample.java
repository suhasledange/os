import java.util.Scanner;
public class sample{
	static {
		System.loadLibrary("sample");
	}

	public native String oper(String s1, String s2);

	public static void main(String args[]) {
	
		Scanner sc = new Scanner(System.in);
		String s1,s2;
		System.out.print("Enter first string: ");
		s1 = sc.nextLine();
		System.out.print("Enter second string: ");
		s2 = sc.nextLine();
		System.out.println("Result: "+new sample().oper(s1,s2));
	}
}
