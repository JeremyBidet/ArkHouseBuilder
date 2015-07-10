/**
 * 
 */
package fr.whyt.core.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;


/**
 * @author Jeremy
 *
 */
public class Row {

	private static int index = 0;
	private final int row_number;
	private final HashMap<Header, CSVData> row;
	
	public Row(HashMap<Header, CSVData> row) {
		this.row = row;
		this.row_number = Row.index++;
	}

	public int column() {
		return this.row.size();
	}
	
	public int row() {
		return this.row_number;
	}
	
	public CSVData get(Header column) {
		return row.get(column);
	}
	
	public CSVData get(int column) {
		return this.row.entrySet().stream()
				.filter( e -> e.getKey().column == column)
				.map( e -> e.getValue() )
				.findFirst()
				.get();
	}
	
	public ArrayList<CSVData> get() {
		return (ArrayList<CSVData>) this.row.entrySet().stream()
				.sorted( (e1, e2) -> e1.getKey().column < e2.getKey().column ? -1 : 1 )
				.map( e -> e.getValue() )
				.collect(Collectors.toList());
	}
	
	public boolean contains(CSVData filter) {
		if( this.row.entrySet().stream().filter( e -> e.getValue().equals(filter)).count() > 0 ) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean contains(CSVData filter, Header header) {
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
	
}
