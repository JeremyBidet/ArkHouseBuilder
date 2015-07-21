/**
 * 
 */
package fr.whyt.core.data;

import java.util.TreeSet;


/**
 * @author Jeremy
 *
 */
public class Resource {
	
	private final String name;
	
	public Resource(String name) {
		this.name = name;
	}
	
	public String name() {
		return this.name;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Resource
				&& ((Resource) obj).name.equals(this.name);
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	
	/** STATIC **/
	public static final TreeSet<Resource> resources = new TreeSet<Resource>();
	public static void add(String resource, int level) {
		Resource.resources.add(new Resource(resource));
	}
	public static Resource get(String resource) {
		return Resource.resources.stream()
				.filter(a -> a.name.equals(resource))
				.findFirst()
				.get();
	}
	
}
