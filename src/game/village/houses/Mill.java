package game.village.houses;

import game.village.Building;
import game.village.Vec3;

/**
 * Created by Chris Burgess on 5/28/2017.
 */
public class Mill extends Building {



    public Mill(Buildings buildingType, Vec3 location) {
        super(buildingType, location);
    }

    @Override
    public void produce() {

    }
}
