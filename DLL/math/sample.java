import java.util.Scanner;
public class sample{
	static {
		System.loadLibrary("sample");
	}

	public native int add(int num1, int num2);
	public native int sub(int num1, int num2);
	public native int mul(int num1, int num2);
	public native int div(int num1, int num2);

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int num1 = 0, num2 = 0;
		System.out.print("Enter first number: ");
		num1 = scanner.nextInt();
		System.out.print("Enter second number: ");
		num2 = scanner.nextInt();
		System.out.println("Addition : "+new sample().add(num1,num2));
		System.out.println("Subtraction : "+new sample().sub(num1,num2));
		System.out.println("Multiplication : "+new sample().mul(num1,num2));
		System.out.println("Division : "+new sample().div(num1,num2));
		
	}
}
