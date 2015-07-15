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
	
	private static int column_index_inc = 0;
	
	private Class<?> type;
	private final int column_index;
	private final int size;
	private final String name;
	
	public HeaderInfo(String name, int size) {
		this.type = null;
		this.column_index = HeaderInfo.column_index_inc++;
		this.size = size;
		this.name = name;
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
		return StringUtils.padCenter(this.name, this.size);
	}
	
}
