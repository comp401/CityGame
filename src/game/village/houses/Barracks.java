package game.village.houses;


import game.village.Building;
import game.village.IBuilding;
import game.village.Vec3;

/**
 * Created by Chris Burgess on 5/27/2017.
 */
public class Barracks extends Building {
    public Barracks(IBuilding.Buildings buildingType, Vec3 location) {
        super(buildingType, location);
    }

    @Override
    public void produce() {

    }
}
