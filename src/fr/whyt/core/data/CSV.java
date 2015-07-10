/**
 * 
 */
package fr.whyt.core.data;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;


/**
 * @author Jeremy
 *
 */
public class CSV {

	private final Path path;
	private final ArrayList<Header> header;
	private final ArrayList<Row> rows;
	
	public CSV(Path path, ArrayList<Header> header, ArrayList<Row> rows) {
		this.path = path;
		this.header = header;
		this.rows = rows;
	}
	
	public Path getPath() {
		return this.path;
	}
	
	public int size() {
		return this.header.size() * this.rows.size();
	}
	
	public int columns() {
		return this.header.size();
	}
	
	public ArrayList<Header> getHeader() {
		return this.header;
	}
	
	public Header getHeader(int column) {
		return this.header.get(column);
	}
	
	public Header getHeader(String name) {
		return this.header.stream().filter(h -> h.name.equals(name)).findFirst().get();
	}
	
	public int rows() {
		return this.rows.size();
	}
	
	public ArrayList<Row> getRows() {
		return this.rows;
	}
	
	public ArrayList<Row> getRows(CSVData filter) {
		return (ArrayList<Row>) this.rows.stream().filter(r -> r.contains(filter)).collect(Collectors.toList());
	}
	
	public ArrayList<Row> getRows(CSVData filter, Header header) {
		return (ArrayList<Row>) this.rows.stream().filter(r -> r.contains(filter, header)).collect(Collectors.toList());
	}
	
	public Row getRow(int row) {
		return rows.get(row);
	}
	
	public ArrayList<CSVData> getColumn(Header column) {
		return (ArrayList<CSVData>) this.rows.stream()
				.map(r -> r.get(column))
				.collect(Collectors.toList());
	}
	
	public ArrayList<CSVData> getColumn(int column) {
		return (ArrayList<CSVData>) this.rows.stream()
				.map(r -> r.get(this.header.get(column)))
				.collect(Collectors.toList());
	}
	
	public ArrayList<CSVData> getColumn(String column) {
		Header header = this.header.stream()
				.filter(h -> h.name.equals(column))
				.findFirst()
				.get();
		
		return (ArrayList<CSVData>) this.rows.stream()
				.map(r -> r.get(header))
				.collect(Collectors.toList());
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof CSV
				&& ((CSV) obj).path.equals(this.path)
				&& ((CSV) obj).header.equals(this.header)
				&& ((CSV) obj).rows.equals(this.rows);
	}
	
}
