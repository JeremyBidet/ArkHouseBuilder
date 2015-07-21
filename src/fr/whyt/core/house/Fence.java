/**
 * 
 */
package fr.whyt.core.house;

import java.util.ArrayList;
import java.util.Arrays;

import fr.whyt.core.data.Compound;


/**
 * @author Jeremy
 *
 */
public class Fence {
	
	private final ArrayList<Compound> compounds;
	
	public Fence(Compound... compounds) {
		this.compounds = (ArrayList<Compound>) Arrays.asList(compounds);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Fence
				&& ((Fence) obj).compounds.equals(this.compounds);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(Compound c : this.compounds) {
			sb.append(c).append('\n');
		}
		
		return sb.toString();
	}
	
}
