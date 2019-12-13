package components;

import java.util.WeakHashMap;

import core.*;

public class Sprite extends Component {
	
	private static WeakHashMap<Entity, String> values = new WeakHashMap<Entity, String>();
	
	public static boolean in( Entity entity ) {
		
		return values.containsKey( entity );
	}
	
	public static String of( Entity entity ) {

		return values.get( entity );
	}
	
	public static void of( Entity entity, String value ) {
		
		values.put( entity, value );
	}
}