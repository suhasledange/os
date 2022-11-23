import java.util.Scanner;
public class sample{
	static {
		System.loadLibrary("sample");
	}

	public native String oper(String s1, String s2);

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		String s1,s2;
		System.out.print("Enter first string: ");
		s1 = scanner.nextLine();
		System.out.print("Enter second string: ");
		s2 = scanner.nexLine();
		System.out.println("Result: "+new sample().oper(s1,s2));
	}
}