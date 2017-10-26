package game.village;


/**
 * Created by Chris Burgess on 5/27/2017.
 */
public abstract class Building implements IBuilding {
    private Buildings buildingType;
    private Vec3 location;
    private boolean contributeToVillage;
    private double production;
    private double xpPerTurn;

    private double foodUsed, rawOreUsed, smeltedOreUsed, lumberUsed;
    private static final double EP_BOUND = .00001;

    public Building(Buildings buildingType, Vec3 location, boolean contributeToVillage) {
        this.buildingType = buildingType;
        this.location = location;
        this.contributeToVillage = contributeToVillage;
        production = 0;
        xpPerTurn = 0;
    }

    public Building(Buildings buildingType, Vec3 location) {
        this(buildingType, location, true);
    }

    public double getProduction() {
        return production;
    }

    public void setProduction(double production) {
        this.production = production;
    }

    public double getXpPerTurn() {
        return xpPerTurn;
    }

    public void setXpPerTurn(double xpPerTurn) {
        this.xpPerTurn = xpPerTurn * getProduction();
    }

    abstract public void produce();

    public void calculateProduction(ProductionPool p, Buildings type) {
        double production;
        if (getProduction() == 0) production = type.baseProduction;
        else production = getProduction();
        double previousProduction = getProduction();

        if (type.foodReq != 0 && p.getFood() != 0 && Math.abs(foodUsed - type.foodReq) > EP_BOUND) {
            double difference = p.getFood() - (type.foodReq-foodUsed);
            if (difference >= 0) {
                p.setFood(difference);
                production += (type.foodReq-foodUsed) * type.scalar;
                foodUsed += type.foodReq-foodUsed;
            } else {
                p.setFood(0);
                production += (type.foodReq + difference) * type.scalar;
                foodUsed += (type.foodReq + difference);
            }
        }

        if (type.rawOreReq != 0 && p.getRawOre() != 0 && Math.abs(rawOreUsed - type.rawOreReq) > EP_BOUND) {
            double difference = p.getRawOre() - (type.rawOreReq-rawOreUsed);
            if (difference >= 0) {
                p.setRawOre(difference);
                production += (type.rawOreReq-rawOreUsed) * type.scalar;
                rawOreUsed += (type.rawOreReq-rawOreUsed);
            } else {
                p.setRawOre(0);
                production += (type.rawOreReq + difference) * type.scalar;
                rawOreUsed += (type.rawOreReq + difference);

            }
        }

        if (type.smeltedOreReq != 0 && p.getSmeltedOre() != 0 && Math.abs(smeltedOreUsed - type.smeltedOreReq) > EP_BOUND) {
            double difference = p.getSmeltedOre() - (type.smeltedOreReq-smeltedOreUsed);
            if (difference >= 0) {
                p.setSmeltedOre(difference);
                production += (type.smeltedOreReq-smeltedOreUsed) * type.scalar;
                smeltedOreUsed += (type.smeltedOreReq-smeltedOreUsed);
            } else {
                p.setSmeltedOre(0);
                production += (type.smeltedOreReq + difference) * type.scalar;
                smeltedOreUsed += (type.smeltedOreReq + difference);

            }
        }

        if (type.lumberReq != 0 && p.getLumber() != 0 && Math.abs(lumberUsed - type.lumberReq) > EP_BOUND) {
            double difference = p.getLumber() - (type.lumberReq-lumberUsed);
            if (difference >= 0) {
                p.setLumber(difference);
                production += (type.lumberReq-lumberUsed) * type.scalar;
                lumberUsed += (type.lumberReq-lumberUsed);
            } else {
                p.setLumber(0);
                production += (type.lumberReq + difference) * type.scalar;
                lumberUsed += (type.lumberReq + difference);

            }
        }
        setProduction(production);
        p.setPool(type, (p.getPool(type) - previousProduction) + production);

    }

    public void resetProductionValues(){
        setProduction(0);
        foodUsed = 0;
        smeltedOreUsed = 0;
        rawOreUsed = 0;
        lumberUsed = 0;
    }
    public Buildings getType() {
        return buildingType;
    }
}
