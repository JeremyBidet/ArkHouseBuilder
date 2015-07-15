/**
 * 
 */
package fr.whyt.utils.csv;

import java.util.HashMap;


/**
 * @author Jeremy
 *
 */
public class Header {
	
	private final HashMap<String, HeaderInfo> columns;
	
	public Header(HashMap<String, HeaderInfo> columns) {
		this.columns = columns;
	}
	
	
	public int columns() {
		return this.columns.size();
	}
	
	public HashMap<String, HeaderInfo> get() {
		return this.columns;
	}
	
	public HeaderInfo get(int column) {
		return columns.values().stream().filter(h -> h.column() == column).findFirst().get();
	}
	
	public HeaderInfo get(String column) {
		return columns.get(column);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Header
				&& ((Header) obj).columns.equals(this.columns);
	}
	
	@Override
	public String toString() {
		return "   " + this.columns.values().stream()
				.sorted( (e1, e2) -> e1.column() < e2.column() ? -1 : 1 )
				.map(hi -> hi.toString())
				.reduce((hi1, hi2) -> hi1 + hi2).get();
	}
	
}
