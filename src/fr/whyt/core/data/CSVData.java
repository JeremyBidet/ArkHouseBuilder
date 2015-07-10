/**
 * 
 */
package fr.whyt.core.data;


/**
 * @author Jeremy
 *
 */
public class CSVData {
	
	public final Class<?> type;
	public final Header header;
	public final int row;
	public final Object value;
	
	public CSVData(String type, Header header, Object value, int row) throws ClassNotFoundException {
		this.type = Class.forName(type, false, null);
		this.header = header;
		this.value = value;
		this.row = row;
	}
	
	public CSVData(Class<?> type, Header header, Object value, int row) {
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
		return obj instanceof CSVData
				&& ((CSVData) obj).type.equals(this.type)
				&& ((CSVData) obj).value.equals(this.value)
				&& ((CSVData) obj).header.equals(this.header);
	}
	
	@Override
	public String toString() {
		return this.row + ":" + this.type.getSimpleName() + ":" + this.header + ":" + this.value;
	}
	
}
