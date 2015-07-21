/**
 * 
 */
package fr.whyt.house;



/**
 * @author Jeremy
 *
 */
public class Floor {
	
	private final Section foundations;
	private final Section walls;
	private final Section ceils;
	private final Section windows;
	private final Section door;
	
	
	public Floor(Section foundations, Section walls, Section ceils, Section windows, Section door) {
		this.foundations = foundations;
		this.walls = walls;
		this.ceils = ceils;
		this.windows = windows;
		this.door = door;
	}
	
	public Section getFoundations() {
		return this.foundations;
	}

	public Section getWalls() {
		return this.walls;
	}
	
	public Section getCeils() {
		return this.ceils;
	}

	public Section getWindows() {
		return this.windows;
	}

	public Section getDoor() {
		return this.door;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Floor
				&& ((Floor) obj).foundations.equals(this.foundations)
				&& ((Floor) obj).walls.equals(this.walls)
				&& ((Floor) obj).ceils.equals(this.ceils)
				&& ((Floor) obj).windows.equals(this.windows)
				&& ((Floor) obj).door.equals(this.door);
	}
	
	@Override
	public String toString() {
		return "Foundations : " + this.foundations + "\n"
				+ "Walls : " + this.walls + "\n"
				+ "Ceils : " + this.ceils + "\n"
				+ "Windows : " + this.windows + "\n"
				+ "Door : " + this.door;
	}
	
}
