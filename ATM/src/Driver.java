import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ATM 2014
 * 
 * Command-line "cash machine" application. 
 */

/**
 * Starts the application.
 * 
 * @author Jon Henrich
 * 
 */
public class Driver {

	/**
	 * Instance of CashMachine.
	 */
	private CashMachine cashMachine;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Driver();
	}

	/**
	 * Constructor.
	 */
	public Driver() {
		// Get the unique CashMachine instance
		cashMachine = CashMachine.getInstance();

		// start the UI
		init();
	}

	/**
	 * Initialize the interface.
	 */
	private void init() {
		BufferedReader commandLine;
		boolean continueLoop = true;

		System.out.println("Welcome to ATM.\nPlease enter a command...\n");
		
		while (continueLoop) {
			try {
				commandLine = new BufferedReader(new InputStreamReader(
						System.in));
				String input;

				input = commandLine.readLine();
				continueLoop = checkInput(input);
			} catch (IOException e) {
				System.out.println("Error reading input.");
			}
		}
	}

	/**
	 * Checks and filters the user input. Makes the correct action based on the
	 * input.
	 */
	private boolean checkInput(String input) {
		try {
			input = input.toLowerCase();
			input = input.replace("$", "");
			input = input.trim();
			
			// Get the command, then strip it from the other data
			String command = input.substring(0,1);
			input = input.replaceFirst(command, "");
			input = input.trim();

			if (command.equals("r")) { // Restock
				restock();
			}
			if (command.equals("q")) { // Quit
				return false;
			}
			if (command.equals("w")) { // Withdraw
				withdraw(Integer.parseInt(input));
				System.out.println("Machine balance:");
				status();
			}
			if (command.equals("i")) { // Display denomination(s)
				String[] denomsString = input.split("\\s+");
				Integer[] denomsInt = new Integer[denomsString.length];

				for (int i = 0; i < denomsString.length; i++) {
					denomsInt[i] = Integer.parseInt(denomsString[i]);
					System.out.println(cashMachine.status(denomsInt[i]));
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("Failure: Invalid Command");
		} catch (Exception e) {
			System.out.println("Failure: Invalid Command");
		}
		return true;
	}

	/**
	 * Restock the ATM.
	 */
	private void restock() {
		cashMachine.restock();
	}

	/**
	 * Withdraw money.
	 */
	private void withdraw(Integer amount) {
		cashMachine.withdraw(amount);
	}

	/**
	 * Displays the quantity of all denominations in the ATM.
	 */
	private void status() {
		String cashStatus = "";
		cashStatus += cashMachine.status(100) + "\n";
		cashStatus += cashMachine.status(50) + "\n";
		cashStatus += cashMachine.status(20) + "\n";
		cashStatus += cashMachine.status(10) + "\n";
		cashStatus += cashMachine.status(5) + "\n";
		cashStatus += cashMachine.status(1) + "\n";

		System.out.println(cashStatus);
	}
}
