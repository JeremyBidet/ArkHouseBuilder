/**
 * 
 */
package fr.whyt.core;

import java.util.TreeSet;


/**
 * @author Jeremy
 *
 */
public class Architecture implements Comparable<Architecture> {
	
	private final String name;
	private final int required;
	
	public Architecture(String name, int required) {
		this.name = name;
		this.required = required;
	}
	
	public String name() {
		return this.name;
	}
	
	public int required() {
		return this.required;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Architecture
				&& ((Architecture) obj).name.equals(this.name)
				&& ((Architecture) obj).required == this.required;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	@Override
	public int compareTo(Architecture o) {
		return this.name.hashCode() - o.name.hashCode() < 0 ? 1 : -1;
	}
	
	
	
	/** STATIC **/
	public static final TreeSet<Architecture> architectures = new TreeSet<Architecture>();
	public static void add(String architecture, int required) {
		Architecture.architectures.add(new Architecture(architecture, required));
	}
	public static Architecture get(String architecture) {
		return Architecture.architectures.stream()
				.filter(a -> a.name.equals(architecture))
				.findFirst()
				.get();
	}
	public static boolean has(String architecture) {
		return Architecture.architectures.contains(architecture);
	}

}
