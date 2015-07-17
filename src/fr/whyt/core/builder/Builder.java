/**
 * 
 */
package fr.whyt.core.builder;

import java.nio.file.Paths;
import java.util.ArrayList;

import fr.whyt.core.builder.Result.ResultType;
import fr.whyt.core.config.Config;
import fr.whyt.core.config.ConfigParser;
import fr.whyt.core.data.Data;
import fr.whyt.core.data.DataParser;


/**
 * @author Jeremy
 *
 */
public class Builder {
	
	private final Data data;
	private Config config;
	
	private Builder(Data data, Config config) {
		this.data = data;
		this.config = config;
	}
	
	public Result build(ResultType... result_types) {
		// TODO build the config according to rules and get results.
		// - Rules are defining how many compound (Architecture+Component) it needs to match the given config.
		// e.g. assume width=5, depth=5, floors=1, floors_height=2 (with simple example)
		// 		it needs at least 5x5=25 foundations, 5x5x2=50 walls, 5x5=25 ceiling/roof
		//		in this case, there are no doors, no windows, no sas, no fences, etc...
		// - Results are about
		// 			- quantities needed (total and by component, architecture, floors, fences, etc...),
		// 			- harvesting time (average, according to effectiveness and resources type),
		//			- and so much others helpfully...
		//
		Result result = new Result();
		
		for(ResultType rt : result_types) {
			Result.result_builder.get(rt).apply(null);
		}
		
		return result;
	}
	
	public void saveData() {
		this.data.save();
	}
	
	public void saveConfig() {
		this.config.save();
	}
	
	public void save() {
		this.saveData();
		this.saveConfig();
	}
	
	/* STATIC */
	public static Builder init() {
		return new Builder(DataParser.deserialize(), ConfigParser.deserialize(Paths.get("resources/config")));
	}
	
}
