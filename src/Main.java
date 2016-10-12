import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("type in the bot's password,\n If you got none the bot will rename itself if there's already an instance of it:");
		Scanner sc = new Scanner(System.in);
		String password = sc.nextLine();
		for(int i = 0; i < 200; i++){
			System.out.println("\n");
		}
		Interface inte = new Interface(password);
		inte.setVisible(true);
		
	}
	
//Test

}
