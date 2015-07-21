/**
 * 
 */
package fr.whyt.core.house;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Jeremy
 *
 */
public class House {
	
	private final Floor ground_floor;
	private final HashMap<Integer, Floor> floors;
	private final HashMap<Integer, Fence> fences;
	private final SecuritySas security_sas;
	
	public House(Floor ground_floor, Floor[] floors, Fence[] fences, SecuritySas security_sas) {
		this.ground_floor = ground_floor;
		
		this.floors = new HashMap<>();
		for(int i=1; i<floors.length; ++i) {
			this.floors.put(i, floors[i]);
		}
		
		this.fences = new HashMap<>();
		for(int i=1; i<fences.length; ++i) {
			this.fences.put(i, fences[i]);
		}
		
		this.security_sas = security_sas;
	}

	
	public Floor getGroundFloor() {
		return this.ground_floor;
	}

	public HashMap<Integer, Floor> getFloors() {
		return this.floors;
	}
	
	public Floor getFloor(int floor) {
		if( this.floors.containsKey(floor) ) {
			return this.floors.get(floor);
		} else {
			return null;
		}
	}
	
	public HashMap<Integer, Fence> getFences() {
		return this.fences;
	}
	
	public Fence getFence(int fence) {
		if( this.fences.containsKey(fence) ) {
			return this.fences.get(fence);
		} else {
			return null;
		}
	}
	
	public SecuritySas getSecuritySas() {
		return this.security_sas;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof House
				&& ((House) obj).ground_floor.equals(this.ground_floor)
				&& ((House) obj).floors.equals(this.floors)
				&& ((House) obj).fences.equals(this.fences)
				&& ((House) obj).security_sas.equals(this.security_sas);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Ground floor : ").append(this.ground_floor).append('\n');
		sb.append("Floors :\n");
		for(Map.Entry<Integer, Floor> e : this.floors.entrySet()) {
			sb.append("\tFloor ").append(e.getKey()).append(" :\n").append(e.getValue()).append('\n');
		}
		sb.append("Fences :\n");
		for(Map.Entry<Integer, Fence> e : this.fences.entrySet()) {
			sb.append("\tFence ").append(e.getKey()).append(" :\n").append(e.getValue()).append('\n');
		}
		sb.append("Security sas : ").append(this.security_sas);
		
		return sb.toString();
	}
	
}
