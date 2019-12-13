package systems;

import java.util.ArrayList;

import components.*;
import core.*;

public class WatchPlayer extends System_ {

	@Override
	public void apply( Entity[] entities ) {

		double x = 0;
		double y = 0;
		
		ArrayList<Entity> watchers = new ArrayList<Entity>();
		
		for ( Entity        entity: entities )
		if  ( Direction.in( entity )         )
		if  ( X        .in( entity )         )
		if  ( Y        .in( entity )         ) {
			
			if ( Controlled.in( entity ) )
			if ( Controlled.of( entity ) ) {
				
				x = X.of( entity );
				y = Y.of( entity );
				
			} else watchers.add( entity );
		}
		
		for ( Entity watcher: watchers ) {
			
			double dX = X.of( watcher ) - x;
			double dY = Y.of( watcher ) - y;
			
			if ( dX * dX + dY * dY > 1.3 ) {
				
				if ( Math.abs( dX ) >
				     Math.abs( dY )
				)
					Direction.of( watcher, dX > 0 ? "left" : "right" ); else
					Direction.of( watcher, dY > 0 ? "up"   : "down"  );
			}
		}
	}
}
