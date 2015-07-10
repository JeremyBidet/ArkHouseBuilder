/**
 * 
 */
package fr.whyt.core.config;

import java.nio.file.Path;
import java.util.HashMap;

import fr.whyt.core.Architecture;


/**
 * @author Jeremy
 *
 */
public class Config {
	
	private final Path file;
	
	public enum ArchitectureModel {
		FULL,
		HYBRID;
	}
	
	public enum FloorHeight {
		SIMPLE (1),
		DOUBLE (2);
		private final int height;
		private FloorHeight(int height) {
			this.height = height;
		}
		public int height() {
			return this.height;
		}
	}
	
	public enum Doors {
		NORMAL,
		DINAUSOR,
		BEHEMOTH;
	}
	
	public enum Position {
		CENTERED,
		BORDERED;
	}
	
	public enum Stairs {
		RAMP,
		LADDER;
	}
	
	// Can only be a rectangle (or square, obviously) for the moment,
	// GUI will be more permissive and block coordinates will be stored, instead of sides size
	private int 							width; 						// front and back size
	private int 							depth; 						// left and right size (from door view)
	// Structures
	private ArchitectureModel 				architecture_model; 		// full|hybrid
	private HashMap<Integer, Architecture>	main_architectures; 		// key=floor# ; value=thatch|wood|stone|metal|etc...
	private HashMap<Integer, FloorHeight> 	floors_height; 				// key=floor# ; value=simple|double
	private int 							floors;						// only floors (ground floor not included)
	private Doors 							main_door; 					// normal|dinausor|behemoth
	private Stairs 							indoor_stairs; 				// ladder|ramp
	private int 							fences; 					// outdoor fences quantity (to expand or secure house)
	private HashMap<Integer, Architecture>	fences_architectures; 		// key=fence# ; value=thatch|wood|stone|metal|etc...
	private HashMap<Integer, Doors> 		fences_doors; 				// key=fence# ; value=normal|dinausor|behemoth
	private HashMap<Integer, Integer>		fences_height;				// key=fence# ; value=fence height
	private boolean 						security_sas; 				// true|false
	private Doors 							security_sas_door; 			// security_sas=true only : normal|dinausor|behemoth
	private HashMap<Integer, Integer>	 	windows_per_side; 			// key=floor# ; value=window_quantity
	private HashMap<Integer, Position> 		windows_position;			// key=floor# ; value=centered|bordered
	private boolean 						indoor_roof_access; 				// true|false
	private Stairs 							indoor_roof_access_stair;	// roof_acces=true only : ladder|ramp
	private boolean 						outdoor_roof_acces; 		// ladder only
	
	protected Config(Path file) {
		this.file = file;
	}
	
	protected Config(
			Path file,
			int width, int depth,
			ArchitectureModel architecture_model, Architecture[] main_architectures,
			FloorHeight[] floors_height, int floors,
			Doors main_door, Stairs indoor_stairs,
			int fences, Architecture[] fences_architectures, Doors[] fences_doors, int[] fences_height,
			boolean security_sas, Doors security_sas_door,
			int[] windows_per_side, Position[] windows_position,
			boolean indoor_roof_access, Stairs indoor_roof_access_stair, boolean outdoor_roof_access) {
		
		this.file = file;
		
		assert width > 0 : "Width must be positive";
		assert depth > 0 : "Depth must be positive";
		
		assert floors >= 0 : "Floors must be positive or null";
		assert fences >= 0 : "Fences must be positive or null";
		
		assert floors == main_architectures.length-1;
		assert floors == floors_height.length-1;
		assert fences == fences_architectures.length;
		assert fences == fences_doors.length;
		assert fences == fences_height.length;
		assert floors == windows_per_side.length-1;
		assert floors == windows_position.length-1;
		
		this.width = width;
		this.depth = depth;
		
		this.architecture_model = architecture_model;
		
		this.main_architectures = new HashMap<>(floors);
		for(int i=0; i<floors+1; i++) {
			this.main_architectures.put(i, main_architectures[i]);
		}
		
		this.floors_height = new HashMap<>(floors);
		for(int i=0; i<floors+1; i++) {
			this.floors_height.put(i, floors_height[i]);
		}
		
		this.floors = floors;
		this.main_door = main_door;
		this.indoor_stairs = indoor_stairs;
		this.fences = fences;
		
		this.fences_architectures = new HashMap<>(fences);
		for(int i=0; i<fences; i++) {
			this.fences_architectures.put(i+1, fences_architectures[i]);
		}
		
		this.fences_doors = new HashMap<>(fences);
		for(int i=0; i<fences; i++) {
			this.fences_doors.put(i+1, fences_doors[i]);
		}
		
		this.fences_height = new HashMap<>(fences);
		for(int i=0; i<fences; i++) {
			this.fences_height.put(i+1, fences_height[i]);
		}
		
		this.security_sas = security_sas;
		this.security_sas_door = security_sas ? security_sas_door : null;
		
		this.windows_per_side = new HashMap<>();
		for(int i=0; i<floors+1; i++) {
			this.windows_per_side.put(i, windows_per_side[i]);
		}
		
		this.windows_position = new HashMap<>();
		for(int i=0; i<floors+1; i++) {
			this.windows_position.put(i, windows_position[i]);
		}
		
		this.indoor_roof_access = indoor_roof_access;
		this.indoor_roof_access_stair = indoor_roof_access ? indoor_roof_access_stair : null;
		this.outdoor_roof_acces = outdoor_roof_access; 
	}
	
	public Path getFile() {
		return this.file;
	}
	
	public int 								getWidth					() { return this.width; }
	public int 								getDepth					() { return this.depth; }
	public ArchitectureModel 				getArchitectureModel		() { return this.architecture_model; }
	public HashMap<Integer, Architecture>	getMainArchitectures		() { return this.main_architectures; }
	public HashMap<Integer, FloorHeight>	getFloorsHeight				() { return this.floors_height; }
	public int 								getFloors					() { return this.floors; }
	public Doors 							getMainDoor					() { return this.main_door; }
	public Stairs 							getIndoorStairs				() { return this.indoor_stairs; }
	public int 								getFences					() { return this.fences; }
	public HashMap<Integer, Architecture>	getFencesArchitectures		() { return this.fences_architectures; }
	public HashMap<Integer, Doors>			getFencesDoors				() { return this.fences_doors; }
	public HashMap<Integer, Integer>		getFencesHeight				() { return this.fences_height; }
	public boolean							isSecuritySas				() { return this.security_sas; }
	public Doors						 	getSecuritySasDoor			() { return this.security_sas_door; }
	public HashMap<Integer, Integer> 		getWindowsPerSide			() { return this.windows_per_side; }
	public HashMap<Integer, Position> 		getWindowsPosition			() { return this.windows_position; }
	public boolean 							isIndoorRoofAccess			() { return this.indoor_roof_access; }
	public Stairs 							getIndoorRoofAccessStair	() { return this.indoor_roof_access_stair; }
	public boolean 							isOutdoorRoofAcces			() { return this.outdoor_roof_acces; }
	
	public void setWidth					(int width) 											{ this.width = width; }
	public void setDepth					(int depth) 											{ this.depth = depth; }
	public void setArchitectureModel		(ArchitectureModel architecture_model)	 				{ this.architecture_model = architecture_model; }
	public void setMainArchitectures		(HashMap<Integer, Architecture> main_architectures) 	{ this.main_architectures = main_architectures; }
	public void setFloorsHeight				(HashMap<Integer, FloorHeight> floors_height) 			{ this.floors_height = floors_height; }
	public void setFloors					(int floors) 											{ this.floors = floors; }
	public void setMainDoor					(Doors main_door) 										{ this.main_door = main_door; }
	public void setIndoorStairs				(Stairs indoor_stairs) 									{ this.indoor_stairs = indoor_stairs; }
	public void setFences					(int fences) 											{ this.fences = fences; }
	public void setFencesArchitectures		(HashMap<Integer, Architecture> fences_architectures)	{ this.fences_architectures = fences_architectures; }
	public void setFencesDoors				(HashMap<Integer, Doors> fences_doors) 					{ this.fences_doors = fences_doors; }
	public void setFencesHeight				(HashMap<Integer, Integer> fences_height) 				{ this.fences_height = fences_height; }
	public void setSecuritySas				(boolean security_sas) 									{ this.security_sas = security_sas; }
	public void setSecuritySasDoor			(Doors security_sas_door) 								{ this.security_sas_door = security_sas_door; }
	public void setWindowsPerSide			(HashMap<Integer, Integer> windows_per_side) 			{ this.windows_per_side = windows_per_side; }
	public void setWindowsPosition			(HashMap<Integer, Position> windows_position) 			{ this.windows_position = windows_position; }
	public void setIndoorRoofAccess			(boolean indoor_roof_access) 							{ this.indoor_roof_access = indoor_roof_access; }
	public void setIndoorRoofAccessStair	(Stairs indoor_roof_access_stair)						{ this.indoor_roof_access_stair = indoor_roof_access_stair; }
	public void setOutdoorRoofAcces			(boolean outdoor_roof_acces) 							{ this.outdoor_roof_acces = outdoor_roof_acces; }

	
	public void save() {
		ConfigRW.serialize(this);
	}
	

	/** STATIC **/
	public static Config init(Path file) {
		return ConfigRW.deserialize(file);
	}
	
	public static void save(Config config) {	
		ConfigRW.serialize(config);
	}
	
	public static Result build(Config config) {
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
		
		
		return null;
	}
	
}
