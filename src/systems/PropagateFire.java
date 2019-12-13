package systems;

import components.*;
import core.*;
import entities.*;

public class PropagateFire extends System_ {

	@Override
	public void apply( Entity[] entities ) {

		for ( Entity      entity: entities )
		if  ( Burning.in( entity )         )
		if  ( Burning.of( entity )         )
		if  ( Width  .in( entity )         )
		if  ( Height .in( entity )         )
		if  ( X      .in( entity )         )
		if  ( Y      .in( entity )         )
		if  ( Z      .in( entity )         )
			
		for ( Entity      otherEntity:  entities )
		if  ( Burning.in( otherEntity )          )
		if  ( Burning.of( otherEntity ) == false )
		if  ( Width  .in( otherEntity )          )
		if  ( Height .in( otherEntity )          )
		if  ( X      .in( otherEntity )          )
		if  ( Y      .in( otherEntity )          )
		if  ( Z      .in( otherEntity )          )
		if  (
			Math.abs( X.of( entity ) - X.of( otherEntity ) ) < ( Width.of( entity ) + Width.of( otherEntity ) + 1.0 ) / 2.0 &&
			Math.abs( Y.of( entity ) - Y.of( otherEntity ) ) < ( Width.of( entity ) + Width.of( otherEntity ) + 1.0 ) / 2.0 &&
			Math.abs( Z.of( entity ) - Z.of( otherEntity ) ) < ( Height.of( entity ) + Height.of( otherEntity ) + 2.0 ) / 2.0
		) {			
			Burning.of( otherEntity, true );
			new Flame(
				X.of( otherEntity ),
				Y.of( otherEntity ),
				Z.of( otherEntity ) + Height.of( otherEntity ),
				otherEntity
			);
		}
	}

}
