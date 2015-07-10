/**
 * 
 */
package fr.whyt.core.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import fr.whyt.core.Architecture;
import fr.whyt.core.Component;


/**
 * @author Jeremy
 *
 */
public class DataRW {
	
	public static final Path path = Paths.get("resources/data.csv");
	
	private static final String integer_regex = "[-+]?\\d{," + String.valueOf(Integer.MAX_VALUE).length() + "}";
	private static final Pattern integer_pattern = Pattern.compile(integer_regex);
	
	private static final String architecture_regex = "\\w+";
	private static final Predicate<String> architecture_predicate = a -> Architecture.has(a);
	private static final Pattern architecture_pattern = Pattern.compile(architecture_regex);
	
	private static final String component_regex = "\\w+";
	private static final Predicate<String> component_predicate = c -> Component.has(c);
	private static final Pattern component_pattern = Pattern.compile(component_regex);
	
	public static Class<?> matchType(String field) {
		Matcher m;
		
		m = integer_pattern.matcher(field);
		if( m.matches() ) {
			return Integer.TYPE;
		}
		
		m = architecture_pattern.matcher(field);
		if( m.matches() && architecture_predicate.test(field) ) {
			return Architecture.class;
		}
		
		m = component_pattern.matcher(field);
		if( m.matches() && component_predicate.test(field) ) {
			return Component.class;
		}
		
		else {
			return null;
		}
	}
	
	public static CSV deserialize() {		
		try {
			BufferedReader br = Files.newBufferedReader(path);
			String line;
			
			line = br.readLine();
			ArrayList<Header> header = (ArrayList<Header>) Arrays.asList(line.split(";")).stream()
					.map(s -> new Header(s))
					.collect(Collectors.toList());
			
			ArrayList<Row> rows = new ArrayList<>();
			int row_index = 0;
			while( (line = br.readLine()) != null ) {
				int header_index = 0;
				String[] fields = line.split(";");
				HashMap<Header, CSVData> row = new HashMap<>(fields.length);
				for(String field : fields) {
					Header h = header.get(header_index);
					Class<?> type = matchType(field);
					row.put(h, new CSVData(type, h, CSVData.cast(type, field), row_index));
				}
				rows.add(new Row(row));
			}
			
			return new CSV(path, header, rows);
		} catch ( IOException e ) {
			System.err.println("Unable to open this file : " + path);
			return null;
		}
	}
	
	public static void serialize(CSV csv) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(csv.getHeader().stream()
					.map(h -> h.name)
					.reduce((s1, s2) -> s1 + ";" + s2)
					.get());
			sb.append(csv.getRows().stream()
					.map(r -> r.get().stream()
							.map(d -> d.toString())
							.reduce((s1, s2) -> s1 + ";" + s2)
							.get())
					.reduce((s1, s2) -> s1 + "\n" + s2)
					.get());
			
			BufferedWriter bw = Files.newBufferedWriter(path);
			bw.write(sb.toString());
			bw.close();
		} catch ( IOException e ) {
			System.err.println("Unable to open this file " + path);
		}
	}
	
}
