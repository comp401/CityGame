package game.village;

/**
 * Created by Chris Burgess on 5/27/2017.
 */
public interface IBuilding {
    enum Buildings {

        //1 farm feeds 4
        //TODO balance so enchanter doesn't pull before miner
        //           w  base, food,  raw,   smel,  lum
        FORT        (0, 1),
        BARRACKS    (2, .1,    1,     0,    .5,   .5  ,  0), //.9 -> 1 from food, .5 mill, .5 blacksmith
        BLACKSMITH  (4, .1,   .5,     2,     0,   .3  ,  0), //.9 -> 2 unsmelted .5 food .3 lumber
        BREEDER     (3, .7,    1,     0,    .1,    0   , 0), // .3 -> 1 farm, .1 blacksmith. produces 1.5 unites of food at full operation
        ENCHANTER   (1,  0,   .25,    0,     1,    0  ,  0), //1 -> .25 food and 1 blacksmith
        FARM        (6, .8,    0,     0,    .2,    0   , 0),// .2 -> .2 black
        FISH        (6, .5,    0,     0,     0,    .4 ,  0),//.05 -> .4 lumber
        MINE        (6, .5,    1.3,   0,    .3,    .8,   0),//.5 -> .3 from blacksmith 1.3 from food and .8 from mill
        POTION      (1,  0,    2,     0,    .1,    0   , 0), // 1 -> 2 farms .1 blacksmith
        MILL        (5, .5,    1,     0,    .2,    0   , 0); // .5 -> .2 blacksmith 1 from food

        public int weight;
        public double baseProduction;
        public double foodReq, rawOreReq, smeltedOreReq, lumberReq;
        public double scalar;

        private Buildings(int weight) {
            this.weight = weight;
        }

        Buildings(int weight, double baseProduction) {
            this.weight = weight;
            this.baseProduction = baseProduction;
        }

        Buildings(int weight, double baseProduction, double foodReq, double rawOreReq, double smeltedOreReq, double lumberReq, double scalar) {
            this.weight = weight;
            this.baseProduction = baseProduction;
            this.foodReq = foodReq;
            this.rawOreReq = rawOreReq;
            this.smeltedOreReq = smeltedOreReq;
            this.lumberReq = lumberReq;
            this.scalar = calcScalar(this);
        }
        private double calcScalar(Buildings type){
            return (1.0-baseProduction)/(type.foodReq + type.rawOreReq + type.smeltedOreReq + type.lumberReq);
        }
    }
    Buildings getType();
    public double getXpPerTurn();
    public void setXpPerTurn(double xpPerTurn);
    void calculateProduction(ProductionPool p, Buildings type);
    public double getProduction();
    public void resetProductionValues();
    public void setProduction(double production);

}
