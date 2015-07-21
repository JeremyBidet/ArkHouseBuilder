/**
 * 
 */
package fr.whyt.core.data;

import java.util.ArrayList;


/**
 * @author Jeremy
 *
 */
public class Data {
	
	private final ArrayList<Compound> data;
	
	public Data(ArrayList<Compound> data) {
		this.data = data;
	}
	
	public Compound get(String compound) {
		return this.data.stream()
				.filter(c -> compound.startsWith(c.getArchitecture().name()) && compound.endsWith(c.getComponent().name()))
				.findFirst()
				.get();
	}
	
	public Compound get(String architecture, String component) {
		return this.data.stream()
				.filter(c -> architecture.equals(c.getArchitecture().name()) && component.equals(c.getComponent()))
				.findFirst()
				.get();
	}
	
	public void save() {
		DataParser.serialize(this);
	}
	
}
