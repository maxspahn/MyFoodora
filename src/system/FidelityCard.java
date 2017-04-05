package system;

/**A fidelity card gives benefits to the customer.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public interface FidelityCard {
	
	/** Compute the price of an order.
	 * @param order Order to be evaluated.
	 * @return Price of the order, with respect to the FidelityCard.
	 */
	public double computePrice(Order order);

}
