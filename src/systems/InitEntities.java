package systems;

import components.*;
import core.*;
import entities.*;

public class InitEntities extends System_ {

	public InitEntities() {
		
		new Flowers( 6.0,  6.0, 0.0, "pink"  );
		new Flowers( 1.0, 10.0, 0.0, "white" );
		new Flowers( 3.0,  5.0, 0.0, "white" );

		Entity       ground = new Entity();
		Animated.of( ground,     true );
		Height  .of( ground,      0.0 );
		Sprite  .of( ground, "ground" );
		Width   .of( ground,     16.0 );
		X       .of( ground,      8.0 );
		Y       .of( ground,      7.0 );
		Z       .of( ground,     -2.0 );
		
		new Ground( 3.5,  3.5,  7.0 );
		new Ground( 3.0, 10.5,  6.0 );
		new Ground( 17.0, 6.0, 12.0 );
		
		Entity       rock = new Entity();
		Animated.of( rock,   true );
		Width   .of( rock,    2.0 );
		Sprite  .of( rock, "rock" );
		Height  .of( rock,    0.8 );
		X       .of( rock,    9.0 );
		Y       .of( rock,    4.0 );
		Z       .of( rock,   -2.0 );
		
		Entity      bridge1 = new Entity();
		Burning.of( bridge1,        false  );
		Health .of( bridge1,          3.0  );
		Height .of( bridge1,          0.0  );
		Sprite .of( bridge1, "left_bridge" );
		Width  .of( bridge1,          2.0  );
		X      .of( bridge1,          7.0  );
		Y      .of( bridge1,          4.0  );
		Z      .of( bridge1,          0.0  );
		
		Entity      bridge2 = new Entity();
		Burning.of( bridge2,         false  );
		Health .of( bridge2,           3.0  );
		Height .of( bridge2,           0.0  );
		Sprite .of( bridge2, "right_bridge" );
		Width  .of( bridge2,           2.0  );
		X      .of( bridge2,          11.0  );
		Y      .of( bridge2,           4.0  );
		Z      .of( bridge2,           0.0  );
		
		Entity      crate = new Entity();
		Burning.of( crate,  false  );
		DX     .of( crate,    0.0  );
		DY     .of( crate,    0.0  );
		DZ     .of( crate,    0.0  );
		Health .of( crate,    3.0  );
		Height .of( crate,    1.2  );
		Sprite .of( crate, "crate" );
		Width  .of( crate,    1.6  );
		X      .of( crate,   13.0  );
		Y      .of( crate,    6.0  );
		Z      .of( crate,    0.0  );
		
		Entity      smallTree = new Entity();
		Burning.of( smallTree,       false  );
		Health .of( smallTree,         2.0  );
		Height .of( smallTree,         2.0  );
		Sprite .of( smallTree, "small_tree" );
		Width  .of( smallTree,         1.0  );
		X      .of( smallTree,        14.0  );
		Y      .of( smallTree,         9.0  );
		Z      .of( smallTree,         0.0  );
		
		Entity      largeTree1 = new Entity();
		Burning.of( largeTree1,       false  );
		Health .of( largeTree1,         3.0  );
		Height .of( largeTree1,         3.0  );
		Sprite .of( largeTree1, "large_tree" );
		Width  .of( largeTree1,         2.0  );
		X      .of( largeTree1,         1.5  );
		Y      .of( largeTree1,         4.0  );
		Z      .of( largeTree1,         0.0  );
		
		Entity      largeTree2 = new Entity();
		Burning.of( largeTree2,       false  );
		Health .of( largeTree2,         3.0  );
		Height .of( largeTree2,         3.0  );
		Sprite .of( largeTree2, "large_tree" );
		Width  .of( largeTree2,         2.0  );
		X      .of( largeTree2,         4.5  );
		Y      .of( largeTree2,         2.0  );
		Z      .of( largeTree2,         0.0  );
		
		Entity     house = new Entity();
		Height.of( house,    4.0  );
		Sprite.of( house, "house" );
		Width .of( house,    4.0  );
		X     .of( house,   15.5  );
		Y     .of( house,    0.5  );
		Z     .of( house,    0.0  );
		
		Entity     streetLamp = new Entity();
		Height.of( streetLamp,          4.0  );
		Sprite.of( streetLamp, "street_lamp" );
		Width .of( streetLamp,          1.0  );
		X     .of( streetLamp,         12.0  );
		Y     .of( streetLamp,          2.0  );
		Z     .of( streetLamp,          0.0  );
		
		Entity     light = new Entity();
		Height.of( light,    1.0  );
		Sprite.of( light, "light" );
		Width .of( light,    1.0  );
		X     .of( light,   12.0  );
		Y     .of( light,    2.0  );
		Z     .of( light,   3.0   );
		
		Entity     vignette = new Entity();
		Height.of( vignette,       0.0  );
		Sprite.of( vignette, "vignette" );
		Width .of( vignette,      16.0  );
		X     .of( vignette,       8.0  );
		Y     .of( vignette,      58.0  );
		Z     .of( vignette,     100.0  );
		
		Entity         trainer = new Entity();
		Animated  .of( trainer,     false );
		Controlled.of( trainer,      true );
		Cry       .of( trainer, "trainer" );
		Direction .of( trainer,      "up" );
		DX        .of( trainer,       0.0 );
		DY        .of( trainer,       0.0 );
		DZ        .of( trainer,       0.0 );
		Height    .of( trainer,       1.5 );
		Sprite    .of( trainer, "trainer" );
		Width     .of( trainer,      0.75 );
		X         .of( trainer,       3.0 );
		Y         .of( trainer,       7.0 );
		Z         .of( trainer,       0.0 );
		
		Entity        pidgey = new Entity();
		Animated .of( pidgey,   false  );
		Cry      .of( pidgey, "pidgey" );
		Direction.of( pidgey,   "left" );
		DX       .of( pidgey,     0.0  );
		DY       .of( pidgey,     0.0  );
		DZ       .of( pidgey,     0.0  );
		Height   .of( pidgey,     1.0  );
		Sprite   .of( pidgey, "pidgey" );
		Type     .of( pidgey, "flying" );
		Width    .of( pidgey,    0.75  );
		X        .of( pidgey,    14.0  );
		Y        .of( pidgey,     9.0  );
		Z        .of( pidgey,     2.0  );
		
		Entity         charmander = new Entity();
		Animated  .of( charmander, false );
		Controlled.of( charmander,        false );
		Cry       .of( charmander, "charmander" );
		DX        .of( charmander,          0.0 );
		DY        .of( charmander,          0.0 );
		DZ        .of( charmander,          0.0 );
		Direction .of( charmander,         "up" );
		Height    .of( charmander,          1.0 );
		Sprite    .of( charmander, "charmander" );
		Type      .of( charmander,       "fire" );
		Width     .of( charmander,        0.75  );
		X         .of( charmander,         4.5  );
		Y         .of( charmander,         9.0  );
		Z         .of( charmander,         0.1  );		
	}
	
	@Override
	public void apply( Entity[] entities ) {}
}
