/**
 * 
 * @author Jon Henrich
 * 
 */
public class CashMachine {
	/**
	 * Instance of ValueSystem.
	 */
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
		CashMachineSystem cash = (CashMachineSystem) cashSystem;
		Integer[] trackedPieces = cash.getQuantitiesArray();
		boolean isSuccessful = withdraw(cashSystem.getValuesArray(), amount, 0,
				trackedPieces);

		if (isSuccessful) {
			for (int index = 0; index < trackedPieces.length; index++) {
				cash.setQuantity(index, trackedPieces[index]);
			}
		}
		return false;
	}

	/**
	 * Reduce the specified amount according to the denominations available.
	 * 
	 * @param denom
	 *            - Array of value denominations.
	 * @param amount
	 *            - Amount of currency to be processed.
	 * @param index
	 *            - Index corresponding to the current denomination.
	 */
	public boolean withdraw(Integer[] denom, int amount, int index,
			Integer[] trackedPieces) {
		CashMachineSystem cash = (CashMachineSystem) cashSystem;
		int currentDenom = denom[index];

		if (amount < currentDenom) {
			return withdraw(denom, amount, index + 1, trackedPieces);
		} else {
			int pieces = 0;
			int available = cash.getQuantityOfValue(index);

			pieces = amount / currentDenom;

			// Check that all pieces are available.
			// Take what IS available.
			if (pieces <= available && available != 0) {
				available = available - pieces;
				amount = amount - (currentDenom * pieces);
				trackedPieces[index] = available;
			} else {
				if (index == (cashSystem.getValuesArray().length - 1)) {
					System.out.println("No denominations available to complete"
							+ " the withdrawl.");
					return false;
				}
			}

			if (amount > 0) {
				return withdraw(denom, amount, index + 1, trackedPieces);
			} else {
				return true;
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
