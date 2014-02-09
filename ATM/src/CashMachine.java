/**
 * 
 * @author Jon Henrich
 * 
 */
public class CashMachine {
	/**
	 * Represents the quantity of one hundred (100) dollar bills available.
	 */
	private Integer oneHundred;
	/**
	 * Represents the quantity of fifty (50) bills available.
	 */
	private Integer fifty;
	/**
	 * Represents the quantity of twenty (20) dollar bills available.
	 */
	private Integer twenty;
	/**
	 * Represents the quantity of ten (10) dollar bills available.
	 */
	private Integer ten;
	/**
	 * Represents the quantity of five (5) dollar bills available.
	 */
	private Integer five;
	/**
	 * Represents the quantity of one (1) dollar bills available.
	 */
	private Integer one;

	private ValueSystem cashSystem;
	/**
	 * Single, unique instance of the cash machine.
	 */
	private static final CashMachine INSTANCE = new CashMachine();

	/**
	 * Private constructor.
	 */
	private CashMachine() {
		cashSystem = new CashMachineSystem();
		cashSystem.setValues();
		restock();
	}

	/**
	 * Restock the cash machine with the predetermined quantity of bills.
	 */
	public void restock() {
		this.oneHundred = 10;
		this.fifty = 10;
		this.twenty = 10;
		this.ten = 10;
		this.five = 10;
		this.one = 10;

		cashSystem.setQuantities();
	}

	/**
	 * Withdraw the specified amount from the cash machine.
	 * 
	 * @param amount
	 * @return <strong>true</strong> if the amount can be withdrawn.<br/>
	 *         <strong>false</strong> if the funds are not available.
	 */
	public boolean withdraw(Integer amount) {
		Integer[] trackedPieces = new Integer[cashSystem.getValuesArray().length];
		this.withdraw(cashSystem.getValuesArray(), amount, 0, trackedPieces);
		return false;
	}

	/**
	 * Reduce the specified amount according to the denominations available.
	 * 
	 * @param values
	 *            - Array of value denominations.
	 * @param amount
	 *            - Amount of currency to be processed.
	 * @param index
	 *            - Index corresponding to the current denomination.
	 */
	public void withdraw(Integer[] values, int amount, int index,
			Integer[] trackedPieces) {
		CashMachineSystem cash = (CashMachineSystem) cashSystem;
		int value = values[index];

		if (amount < value) {
			withdraw(values, amount, index + 1, trackedPieces);
		} else {
			int pieces = 0;
			int remaining = 0;
			int available = cash.getQuantityOfValue(index);
			
			pieces = amount / value;
			// Check that all pieces are available.
			// Take what IS available.
			if (pieces >= available && available != 0) {
				pieces = pieces - available;
				amount = amount - (amount * pieces);
			}
			trackedPieces[index] = pieces;
			remaining = amount % value;
			System.out.println(value + " - " + pieces);

			if (remaining > 0) {
				withdraw(values, remaining, index + 1, trackedPieces);
			}
		}
	}

	/**
	 * Return the remaining quantity of bills for the given denomination.
	 * 
	 * @param denomination
	 * @return String - Quantity of denomination
	 */
	public String status(Integer denomination) {
		CashMachineSystem cash = (CashMachineSystem) cashSystem;

		switch (denomination) {
		case 100:
			return "$100 - " + cash.getQuantityOfValue(0);
		case 50:
			return "$50 - " + cash.getQuantityOfValue(1);
		case 20:
			return "$20 - " + cash.getQuantityOfValue(2);
		case 10:
			return "$10 - " + cash.getQuantityOfValue(3);
		case 5:
			return "$5 - " + cash.getQuantityOfValue(4);
		case 1:
			return "$1 - " + cash.getQuantityOfValue(5);
		default:
			return "Invalid Command";
		}
	}

	/**
	 * Returns the unique instance of CashMachine.
	 * 
	 * @return CashMachine
	 */
	public static CashMachine getInstance() {
		// Using eager initialization
		return INSTANCE;
	}
}
