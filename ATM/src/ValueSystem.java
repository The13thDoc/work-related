import java.util.ArrayList;

/**
 * Interface representing a system of constant, whole-number values.
 * 
 * @author Jon Henrich
 * 
 */
public interface ValueSystem {
	/**
	 * Contains the constant values.
	 */
	public ArrayList<Integer> values = new ArrayList<Integer>();
	/**
	 * Contains the constant values' quantities.
	 */
	public ArrayList<Integer> quantities = new ArrayList<Integer>();

	/**
	 * Add each value to the List.
	 */
	public void setValues();

	/**
	 * Set the quantity of the specified value.
	 */
	public void setQuantities();

	/**
	 * Return the array of values as a List.
	 * 
	 * @return List of values.
	 */
	public ArrayList<Integer> getValuesList();

	/**
	 * Return the array of values as an array.
	 * 
	 * @return Array of values.
	 */
	public Integer[] getValuesArray();
}