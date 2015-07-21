/**
 * 
 */
package fr.whyt.core.house;

import fr.whyt.core.data.Compound;


/**
 * @author Jeremy
 *
 */
public class Section {

	private final Compound compound;
	private final int quantity;
	
	public Section(Compound compound, int quantity) {
		this.quantity = quantity;
		this.compound = compound;
	}
	
	public Compound getCompound() {
		return this.compound;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Section
				&& ((Section) obj).compound.equals(this.compound)
				&& ((Section) obj).compound.equals(this.quantity);
	}
	
	@Override
	public String toString() {
		return this.quantity + "x " + this.compound;
	}
}
