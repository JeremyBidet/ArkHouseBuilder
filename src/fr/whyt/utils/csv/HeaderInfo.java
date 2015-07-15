/**
 * 
 */
package fr.whyt.utils.csv;

import fr.whyt.utils.StringUtils;


/**
 * @author Jeremy
 *
 */
public class HeaderInfo {
	
	protected static int column_index_inc = 0;
	
	private Class<?> type;
	private final int column_index;
	private final String name;
	private int size;
	
	public HeaderInfo(String name) {
		this.type = null;
		this.column_index = HeaderInfo.column_index_inc++;
		this.name = name;
		this.size = this.name.length();
	}
	
	public int column() {
		return this.column_index;
	}
	
	public int size() {
		return this.size;
	}
	
	public Class<?> type() {
		return this.type;
	}
	
	public String name() {
		return this.name;
	}
	
	public void type(Class<?> type) {
		this.type = type;
		switch(type.getSimpleName()) {
			case "int": 	this.size = Integer.max(this.name.length() + CSV.margin, String.valueOf(Integer.MAX_VALUE).length()); break;
			case "double":	this.size = Integer.max(this.name.length() + CSV.margin, String.valueOf(Double.MAX_VALUE).length()); break;
			case "String":	this.size = Integer.max(this.name.length() + CSV.margin, 32); break;
			default: 		this.size = 4; break;
		}
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof HeaderInfo
				&& ((HeaderInfo) obj).column_index == this.column_index
				&& ((HeaderInfo) obj).name.equals(this.name)
				&& ((HeaderInfo) obj).type.equals(this.type);
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
	
	@Override
	public String toString() {
		return StringUtils.padRight(this.name, this.size, CSV.margin);
	}
	
}
