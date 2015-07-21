/**
 * 
 */
package fr.whyt.core.data;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.TreeSet;


/**
 * @author Jeremy
 *
 */
public class Component implements Comparable<Component> {
	
	private final String name;
	private final int level;
	private final HashMap<Resource, Integer> resources;
	
	public Component(String name, int level, HashMap<Resource, Integer> resources) {
		this.name = name;
		this.level = level;
		this.resources = resources;
	}
	
	public String name() {
		return this.name;
	}
	
	public int level() {
		return this.level;
	}
	
	public HashMap<Resource, Integer> getResources() {
		return this.resources;
	}
	
	public int getResources(Resource filter) {
		return this.resources.get(filter);
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Component
				&& ((Component) obj).name.equals(this.name)
				&& ((Component) obj).level == this.level
				&& ((Component) obj).resources.equals(this.resources);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.name);
		sb.append(" (");
		try {
			sb.append(
				this.resources.entrySet().stream()
					.map(e -> e.getKey()+":"+e.getValue())
					.reduce((r1, r2) -> r1 + " " + r2)
					.get()
			);
		} catch (NoSuchElementException e) {
			sb.append("");
		}
		sb.append(")");
		
		return sb.toString();
	}
	
	@Override
	public int compareTo(Component o) {
		return this.name.hashCode() - o.name.hashCode() < 0 ? 1 : -1;
	}
	
	
	/** STATIC **/
	public static final TreeSet<Component> components = new TreeSet<Component>();
	public static void add(String component, int level, HashMap<Resource, Integer> resources) {
		Component.components.add(new Component(component, level, resources));
	}
	public static Component get(String component) {
		return Component.components.stream()
				.filter(c -> c.name.equals(component))
				.findFirst()
				.get();
	}
	public static boolean has(String component) {
		return Component.components.contains(component);
	}
}
