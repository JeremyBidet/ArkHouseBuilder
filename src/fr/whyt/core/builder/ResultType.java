/**
 * 
 */
package fr.whyt.core.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author Jeremy
 *
 */
public enum ResultType {
	
	THATCH 			(1 << 0),  //     1 // resources, architecture
	WOOD 			(1 << 1),  //     2 // resources, architecture
	FIBER			(1 << 2),  //     4 // resources
	STONE 			(1 << 3),  //     8 // resources, architecture
	METALINGOT		(1 << 4),  //    16 // resources
	CEMENTINGPASTE	(1 << 5),  //    32 // resources
	POLYMER			(1 << 6),  //    64 // resources
	METAL 			(1 << 7),  //   128 // resources, architecture
	GROUNDFLOOR 	(1 << 8),  //   256 // assembly
	FLOORS			(1 << 9),  //   512 // assembly
	FENCES			(1 << 10), //  1024 // assembly
	SAS				(1 << 11), //  2048 // assembly
	FUNDATION		(1 << 12), //  4096 // component
	CEILING			(1 << 13), //  8192 // component
	WALL			(1 << 14), // 16384 // component
	FULLDOOR		(1 << 15), // 32768 // component
	
	ALL 			((SAS.result() << 1) - 1); // 65535 // FULLDOOR + WALL + ... + THATCH
	
	private final int result;
	
	ResultType(final int result) {
		this.result = result;
	}
	
	public int result() {
		return this.result;
	}
	
	public static ArrayList<ResultType> decompose(int x) {
		ArrayList<ResultType> x2 = new ArrayList<>();
		List<ResultType> values = Arrays.asList(ResultType.values());
		int shift;
		for(int i=0; (shift = 1<<i) <= x; i++) {
			int x_and_shift = x & shift;
			if( (x_and_shift) != 0 ) {
				x2.add(  values.stream().filter(rt -> rt.result == x_and_shift).findFirst().get() );
			}
		}
		return x2;
	}
	
	public static int compose(ResultType... result_types) {
		int result_type = 0;
		for(ResultType rt : result_types) {
			result_type |= rt.result;
		}
		return result_type;
	}
	
}
