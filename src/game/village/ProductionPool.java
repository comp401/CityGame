package game.village;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris Burgess on 5/28/2017.
 */
public class ProductionPool {

    private double food, rawOre, smeltedOre, lumber;

    private List<Boolean> changed;

    public ProductionPool() {
        food = 0;
        rawOre = 0;
        smeltedOre = 0;
        lumber = 0;
        changed = new ArrayList<>();
    }
    public void resetPools(){
        food = 0;
        rawOre = 0;
        smeltedOre = 0;
        lumber = 0;
        changed.clear();
    }

    public void setPool(IBuilding.Buildings type, double amount){
        if (Math.abs(amount - 0) < 0.001){
            changed.add(false);
        }else
            changed.add(true);
        switch (type){
            case FARM:
            case FISH:
            case BREEDER:
                setFood(amount);
                break;
            case MINE:
                setRawOre(amount);
                break;
            case BLACKSMITH:
                setSmeltedOre(amount);
                break;
            case MILL:
                setLumber(amount);
                break;
        }
    }

    public double getPool(IBuilding.Buildings type){
        switch (type){
            case FARM:
            case FISH:
            case BREEDER:
                return food;
            case MINE:
                return rawOre;
            case BLACKSMITH:
                return smeltedOre;
            case MILL:
                return lumber;
        }
        return 0;
    }


    public double getFood() {
        return food;
    }

    public void setFood(double food) {
        if (food < 0) food = 0;

        this.food = food;
    }

    public double getRawOre() {
        return rawOre;
    }

    public void setRawOre(double rawOre) {
        if (rawOre < 0) rawOre = 0;
        this.rawOre = rawOre;
    }

    public double getSmeltedOre() {
        return smeltedOre;
    }

    public void setSmeltedOre(double smeltedOre) {
        if (smeltedOre < 0) smeltedOre = 0;

        this.smeltedOre = smeltedOre;
    }

    public double getLumber() {
        return lumber;
    }

    public void setLumber(double lumber) {
        if (lumber < 0) lumber = 0;
        this.lumber = lumber;
    }
   // 0 1 2 3
   // t f f t
}
