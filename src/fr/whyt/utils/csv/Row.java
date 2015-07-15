/**
 * 
 */
package fr.whyt.utils.csv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;


/**
 * @author Jeremy
 *
 */
public class Row {

	private static int row_index_inc = 0;
	
	private final int row_number;
	private final HashMap<HeaderInfo, Data> row;
	
	public Row(HashMap<HeaderInfo, Data> row) {
		this.row = row;
		this.row_number = Row.row_index_inc++;
	}

	public int columns() {
		return this.row.size();
	}
	
	public int row() {
		return this.row_number;
	}
	
	public Data get(String column) {
		return row.get(column);
	}
	
	public Data get(int column) {
		return this.row.entrySet().stream()
				.filter(e -> e.getKey().column() == column)
				.map(e -> e.getValue())
				.findFirst()
				.get();
	}
	
	public ArrayList<Data> get() {
		return (ArrayList<Data>) this.row.entrySet().stream()
				.sorted((e1, e2) -> e1.getKey().column() < e2.getKey().column() ? -1 : 1)
				.map(e -> e.getValue())
				.collect(Collectors.toList());
	}
	
	public boolean contains(Data filter) {
		if( this.row.values().stream().filter(v -> v.equals(filter)).count() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean contains(Data filter, String header) {
		if( this.row.get(header).equals(filter) ) {
			return true;
		} else {
			return false;
		}
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Row
				&& ((Row) obj).row_number == this.row_number
				&& ((Row) obj).row.equals(this.row);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append( this.row_number < 100 ? ' ' : "" );
		sb.append( this.row_number <  10 ? ' ' : "" );
		sb.append( this.row_number );
		sb.append( this.row.values().stream().map(d -> d.toString()).reduce((s1, s2) -> s1 + s2).get() );
		
		return sb.toString();
	}
	
}
