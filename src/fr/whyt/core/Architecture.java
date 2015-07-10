/**
 * 
 */
package fr.whyt.core;

import java.util.TreeSet;


/**
 * @author Jeremy
 *
 */
public class Architecture {
	
	private final String name;
	private final int level;
	
	public Architecture(String name, int level) {
		this.name = name;
		this.level = level;
	}
	
	public String name() {
		return this.name;
	}
	
	public int level() {
		return this.level;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Architecture
				&& ((Architecture) obj).name.equals(this.name)
				&& ((Architecture) obj).level == this.level;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	
	/** STATIC **/
	public static final TreeSet<Architecture> architectures = new TreeSet<Architecture>();
	public static void add(String architecture, int level) {
		Architecture.architectures.add(new Architecture(architecture, level));
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
