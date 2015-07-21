/**
 * 
 */
package fr.whyt.core.data;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import fr.whyt.utils.csv.CSV;
import fr.whyt.utils.csv.CSVParser;
import fr.whyt.utils.csv.Row;


/**
 * @author Jeremy
 *
 */
public class DataParser {
	
	public static final Path path = Paths.get("resources/data.csv");
	
	public static Data deserialize() {
		CSV csv = CSVParser.deserialize(path);
		
		ArrayList<Compound> compounds = new ArrayList<Compound>(csv.count());
		
		for(Row row : csv.rows()) {
			String 	architecture	= (String) 	row.get(csv.getHeader("Architecture")).value;
			int 	required		= (int) 	row.get(csv.getHeader("Required")).value;
			String 	component		= (String) 	row.get(csv.getHeader("Component")).value;
			int		level			= (int) 	row.get(csv.getHeader("Level")).value;
			
			HashMap<Resource, Integer> resources = new HashMap<Resource, Integer>();
			int resources_index_start = 4, resources_index_end = 10;
			for(int i=resources_index_start; i<resources_index_end+1; i++) {
				String name = csv.getHeader(i).name();
				int value = (int) row.get(i).value;
				if( value == 0 ) {
					continue;
				}
				resources.put(new Resource(name), value);
			}
			
			Architecture.add(architecture, required);
			Component.add(component, level, resources);
			
			compounds.add(new Compound(Architecture.get(architecture), Component.get(component)));
		}
		
		return new Data(compounds);
	}
	
	public static void serialize(Data data) {
		// TODO
		
		CSV csv = new CSV(path, null, null);
		CSVParser.serialize(csv, path);
	}
	
}
