/**
 * 
 */
package fr.whyt.utils.csv;

import fr.whyt.utils.StringUtils;


/**
 * @author Jeremy
 *
 */
public class Data<T extends Object> {
	
	public final Class<?> type;
	public final HeaderInfo header;
	public final int row;
	public final T value;
	
	public Data(Class<?> type, HeaderInfo header, T value, int row) {
		this.type = type;
		this.header = header;
		this.value = value;
		this.row = row;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Data
				&& ((Data<?>) obj).type.equals(this.type)
				&& ((Data<?>) obj).value.equals(this.value)
				&& ((Data<?>) obj).header.equals(this.header);
	}
	
	@Override
	public String toString() {		
		StringBuilder sb = new StringBuilder();
		if(this.value.toString().length() > header.size()-1-CSV.margin) {
			sb.append(this.value.toString().substring(0, header.size()-1-CSV.margin));
		} else {
			sb.append(this.value.toString());
		}
		
		return StringUtils.padRight(sb.toString(), header.size(), CSV.margin);
	}
	
	
	public static Data<?> cast(Class<?> type, HeaderInfo header, String field, int row_index) {
		try {
			switch(type.getSimpleName()) {
				case "int": return new Data<Integer>(type, header, Integer.parseInt(field), row_index++);
				case "double":	return new Data<Double>(type, header, Double.parseDouble(field), row_index++);
				case "String": return new Data<String>(type, header, field, row_index++);
				default: return new Data<Object>(type, header, (Object) field, row_index++);
			}
		} catch ( NumberFormatException e ) {
			System.err.println(field + " is not an Integer nor Double !");
			return null;
		}
	}
	
}
