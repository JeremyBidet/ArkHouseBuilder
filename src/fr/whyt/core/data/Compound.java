/**
 * 
 */
package fr.whyt.core.data;


/**
 * @author Jeremy
 *
 */
public class Compound {
	
	private final Architecture architecture;
	private final Component component;
	
	public Compound(Architecture architecture, Component component) {
		this.architecture = architecture;
		this.component = component;
	}

	public Architecture getArchitecture() {
		return this.architecture;
	}
	
	public Component getComponent() {
		return this.component;
	}
	
	public int getResources(Resource filter) {
		return this.component.getResources(filter);
	}
	
	public int getResources() {
		return this.component.getResources().keySet().stream()
				.map(r1 -> this.component.getResources(r1))
				.reduce((i, j) -> i+j)
				.get();
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Compound
				&& ((Compound) obj).architecture.equals(this.architecture)
				&& ((Compound) obj).component.equals(this.component);
	}
	
	@Override
	public String toString() {
		return this.architecture + " " + this.component;
	}
	
}
