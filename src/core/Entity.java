package core;

import java.util.ArrayList;

public class Entity {
	
	private static ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public Entity() {
		
		entities.add( this );
	}
	
	public static Entity[] getEntities() {
		
		return entities.toArray( new Entity[ 0 ] );
	}
	
	public void remove() {
		
		entities.remove( this );
	}
}