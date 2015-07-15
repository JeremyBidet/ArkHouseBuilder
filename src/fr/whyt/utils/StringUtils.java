/**
 * 
 */
package fr.whyt.utils;


/**
 * @author Jeremy
 *
 */
public class StringUtils {
	
	public static String padRight(String str, int size, int border_left) {
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<border_left; i++) {
			sb.append(' ');
		}
		
		sb.append(str);
		
		for(int i=0; i<size-str.length()-border_left; i++) {
			sb.append(' ');
		}
		
		return sb.toString();
	}
	
	public static String padLeft(String str, int size, int border_right) {
		StringBuilder sb = new StringBuilder();

		for(int i=0; i<size-str.length()-border_right; i++) {
			sb.append(' ');
		}
		
		sb.append(str);
		
		for(int i=0; i<border_right; i++) {
			sb.append(' ');
		}
		
		return sb.toString();
	}
	
	public static String padCenter(String str, int size) {
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<(size-str.length())/2; i++) {
			sb.append(' ');
		}
		
		sb.append(str);
		
		for(int i=0; i<(size-str.length())/2; i++) {
			sb.append(' ');
		}
		
		return sb.toString();
	}
	
}
