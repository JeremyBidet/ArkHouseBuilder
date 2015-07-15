/**
 * 
 */
package fr.whyt.utils.csv;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;


/**
 * @author Jeremy
 *
 */
public class CSV {

	protected static final int margin = 1;
	
	private final Path path;
	private final Header header;
	private final ArrayList<Row> rows;
	
	public CSV(Path path, Header header, ArrayList<Row> rows) {
		this.path = path;
		this.header = header;
		this.rows = rows;
		
		Row.row_index_inc = 0;
		HeaderInfo.column_index_inc = 0;
	}
	
	public Path getPath() {
		return this.path;
	}
	
	public int size() {
		return length() * count();
	}
	
	public int length() {
		return this.header.columns();
	}
	
	public Header getHeader() {
		return this.header;
	}
	
	public HeaderInfo getHeader(int column) {
		return this.header.get(column);
	}
	
	public HeaderInfo getHeader(String name) {
		return this.header.get(name);
	}
	
	public int count() {
		return this.rows.size();
	}
	
	public ArrayList<Row> rows() {
		return this.rows;
	}
	
	public ArrayList<Row> getRows(Data<?> filter) {
		return (ArrayList<Row>) this.rows.stream().filter(r -> r.contains(filter)).collect(Collectors.toList());
	}
	
	public ArrayList<Row> getRows(Data<?> filter, String header) {
		return (ArrayList<Row>) this.rows.stream().filter(r -> r.contains(filter, header)).collect(Collectors.toList());
	}
	
	public Row getRow(int row) {
		return rows.get(row);
	}
	
	public ArrayList<Data<?>> getColumn(int column) {
		return (ArrayList<Data<?>>) this.rows.stream()
				.map(r -> r.get(column))
				.collect(Collectors.<Data<?>>toList());
	}
	
	public ArrayList<Data<?>> getColumn(String column) {
		return (ArrayList<Data<?>>) this.rows.stream()
				.map(r -> r.get(column))
				.collect(Collectors.<Data<?>>toList());
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof CSV
				&& ((CSV) obj).path.equals(this.path)
				&& ((CSV) obj).header.equals(this.header)
				&& ((CSV) obj).rows.equals(this.rows);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("---").append(this.path.toAbsolutePath()).append('\n');
		sb.append(this.header).append('\n');
		
		for(Row row : this.rows) {
			sb.append(row.toString()).append('\n');
		}
		
		return sb.toString();
	}
	
}
