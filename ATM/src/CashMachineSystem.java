import java.util.ArrayList;

/**
 * Implements a value system for an ATM machine.
 * 
 * @author Jon Henrich
 * 
 */
public class CashMachineSystem implements ValueSystem {

	public final Integer ONE_HUNDRED = 100;

	public final Integer FIFTY = 50;

	private final Integer TWENTY = 20;

	private final Integer TEN = 10;

	public final Integer FIVE = 5;

	public final Integer ONE = 1;

	/**
	 * Constructor.
	 */
	public CashMachineSystem() {
		setValues();
	}

	@Override
	public void setValues() {
		values.clear(); // Reset the list
		values.add(ONE_HUNDRED);
		values.add(FIFTY);
		values.add(TWENTY);
		values.add(TEN);
		values.add(FIVE);
		values.add(ONE);
	}

	@Override
	public void setQuantities() {
		quantities.clear();
		for (int i = 0; i < values.size(); i++) {
			quantities.add(10);
		}
	}

	public void setQuantity(Integer index, Integer quantity) {
		quantities.set(index, quantity);
	}

	@Override
	public ArrayList<Integer> getValuesList() {
		return values;
	}

	@Override
	public Integer[] getValuesArray() {
		Integer[] valuesArray = {};
		return values.toArray(valuesArray);
	}

	public Integer getQuantityOfValue(Integer index) {
		return quantities.get(index);
	}

}