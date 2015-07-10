/**
 * 
 */
package fr.whyt.core.data;


/**
 * @author Jeremy
 *
 */
public class Header {
	
	private static int index = 0;
	public final int column;
	public final String name;
	
	public Header(String name) {
		this.column = Header.index++;
		this.name = name;
	}	

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Header
				&& ((Header) obj).column == column
				&& ((Header) obj).name.equals(this.name);
	}
	
	@Override
	public String toString() {
		return this.column + ":" + this.name;
	}

}
