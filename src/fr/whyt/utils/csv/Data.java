/**
 * 
 */
package fr.whyt.utils.csv;

import fr.whyt.utils.StringUtils;


/**
 * @author Jeremy
 *
 */
public class Data {
	
	public final Class<?> type;
	public final HeaderInfo header;
	public final int row;
	public final Object value;
	
	public Data(String type, HeaderInfo header, Object value, int row) throws ClassNotFoundException {
		this.type = Class.forName(type, false, null);
		this.header = header;
		this.value = value;
		this.row = row;
	}
	
	public Data(Class<?> type, HeaderInfo header, Object value, int row) {
		this.type = type;
		this.header = header;
		this.value = value;
		this.row = row;
	}
	
	public static Object cast(Class<?> type, Object value) {
		return type.cast(value);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Data
				&& ((Data) obj).type.equals(this.type)
				&& ((Data) obj).value.equals(this.value)
				&& ((Data) obj).header.equals(this.header);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(this.value.toString().length() > header.size()+1) {
			sb.append(this.value.toString().substring(0, header.size()-2));
		} else {
			sb.append(this.value.toString());
		}
		
		return StringUtils.padRight(sb.toString(), header.size(), 1);
	}
	
}
