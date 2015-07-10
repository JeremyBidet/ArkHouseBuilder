/**
 * 
 */
package fr.whyt.core;


/**
 * @author Jeremy
 *
 */
public class Compound {
	
	private final Architecture architecture;
	private final Component component;
	private final int quantity;
	
	public Compound(Architecture architecture, Component component, int quantity) {
		this.architecture = architecture;
		this.component = component;
		this.quantity = quantity;
	}

	public Architecture getArchitecture() {
		return this.architecture;
	}
	
	public Component getComponent() {
		return this.component;
	}
	
	public int getTotalResources(Resource filter) {
		return this.component.getResources(filter) * this.quantity;
	}
	
	public int getTotalResources() {
		return this.component.getResources().keySet().stream()
				.map(r1 -> this.component.getResources(r1) * this.quantity)
				.reduce((i, j) -> i+j)
				.get();
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Compound
				&& ((Compound) obj).architecture.equals(this.architecture)
				&& ((Compound) obj).component.equals(this.component)
				&& ((Compound) obj).quantity == this.quantity;
	}
	
	@Override
	public String toString() {
		return this.architecture + " " + this.component;
	}
	
}
