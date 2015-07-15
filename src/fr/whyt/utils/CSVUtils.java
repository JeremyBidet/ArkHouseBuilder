/**
 * 
 */
package fr.whyt.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import fr.whyt.core.Architecture;
import fr.whyt.core.Component;
import fr.whyt.utils.csv.CSV;
import fr.whyt.utils.csv.Data;
import fr.whyt.utils.csv.Header;
import fr.whyt.utils.csv.HeaderInfo;
import fr.whyt.utils.csv.Row;


/**
 * @author Jeremy
 *
 */
public class CSVUtils {
	
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
	
	public static CSV deserialize(Path path) {		
		try {
			BufferedReader br = Files.newBufferedReader(path);
			String line;
			
			// parse header
			line = br.readLine();
			Header header = new Header(
					(HashMap<String, HeaderInfo>) Arrays.asList(line.split(";")).stream()
							.collect(Collectors.toMap(s -> s, s -> new HeaderInfo(s, s.length()*2))));
			
			// parse rows
			ArrayList<Row> rows = new ArrayList<>();
			int row_index = 0;
			while((line = br.readLine()) != null) {
				int header_index = 0;
				String[] fields = line.split(";");
				HashMap<HeaderInfo, Data> row = new HashMap<>(fields.length);
				for(String field : fields) {
					Class<?> type = matchType(field);
					HeaderInfo hi = header.get(header_index++);
					if(row_index == 0) {
						hi.type(type);
					}
					row.put(hi, new Data(type, hi, Data.cast(type, field), row_index++));
				}
				rows.add(new Row(row));
			}
			
			return new CSV(path, header, rows);
			
		} catch ( IOException e ) {
			System.err.println("Unable to open this file : " + path);
			return null;
		}
	}
	
	public static void serialize(CSV csv, Path path) {
		try {
			StringBuilder sb = new StringBuilder();
			
			sb.append(csv.getHeader().get().values().stream()
					.map(h -> h.name())
					.reduce((s1, s2) -> s1 + ";" + s2)
					.get());
			sb.append(csv.getRows().stream()
					.map(r -> r.get().stream()
							.map(d -> d.toString())
							.reduce((s1, s2) -> s1 + ";" + s2)
							.get())
					.reduce((s1, s2) -> s1 + "\n" + s2)
					.get());
			
			path.toAbsolutePath().toFile().delete();
			BufferedWriter bw = Files.newBufferedWriter(path);
			bw.write(sb.toString());
			bw.close();
		} catch ( IOException e ) {
			System.err.println("Unable to open this file " + path);
		}
	}
	
}
