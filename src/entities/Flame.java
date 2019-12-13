package entities;

import core.*;
import components.*;

public class Flame extends Entity {

	public Flame( double x, double y, double z, Entity tiedTo ) {
		
		if ( tiedTo != null ) TiedTo.of( this, tiedTo );
		Animated.of( this,                       true );		
		Burning .of( this,                       true );
		Health  .of( this, tiedTo != null ? 3.0 : 0.1 );
		Height  .of( this,                        1.0 );
		Sprite  .of( this,                    "flame" );		
		Width   .of( this,                        1.0 );
		X       .of( this,                          x );
		Y       .of( this,                          y );
		Z       .of( this,                          z );		
	}
}
