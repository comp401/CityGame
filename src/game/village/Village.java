package game.village;


import game.village.houses.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Chris Burgess on 5/25/2017.
 */
public class Village {
    private String name;
    private String[] owners;
    private int level;
    private int xp;
    private List<IBuilding> buildingList;
    private Vec3 position;

    private ProductionPool productionPool;
    private double xpPerTurn;

    public Village(String name, String[] owners, Vec3 position) {
        this.name = name;
        this.owners = owners;
        this.position = position;
        level = 0;
        xp = 0;
        buildingList = new ArrayList<>();
        productionPool = new ProductionPool();
        //add fort to list
    }

    public void createBuilding(IBuilding.Buildings type, Vec3 location) {
        switch (type) {
            case FORT:
                buildingList.add(new Fort(type, location));
                break;
            case BARRACKS:
                buildingList.add(new Barracks(type, location));
                break;
            case BLACKSMITH:
                buildingList.add(new Blacksmith(type, location));
                break;
            case BREEDER:
                buildingList.add(new Breeder(type, location));
                break;
            case ENCHANTER:
                buildingList.add(new Enchanter(type, location));
                break;
            case FARM:
                buildingList.add(new Farm(type, location));
                break;
            case FISH:
                buildingList.add(new Fish(type, location));
                break;
            case MINE:
                buildingList.add(new Mine(type, location));
                break;
            case POTION:
                buildingList.add(new Potion(type, location));
                break;
            case MILL:
                buildingList.add(new Mill(type, location));
                break;

        }
        calcProductionRates();
        calcXpRates();
    }



    private void calcXpRates() {


        double average = 0;
        double div = 0;
        //number of a kind of a house and houses av
        for (IBuilding.Buildings type : IBuilding.Buildings.values()) {
            average += (type.weight * getBuildingCount(type));
            div += type.weight;
        }
        average /= div;


        for (IBuilding.Buildings type : IBuilding.Buildings.values()) {
            List<IBuilding> list = getSubList(type);
            for (int i = 0; i < list.size(); i++) {
                if (i <= Math.floor(average))
                    list.get(i).setXpPerTurn(10);
                else
                    list.get(i).setXpPerTurn(funcHouse((i - Math.floor(average))));

            }
        }

        xpPerTurn = 0;
        for (IBuilding b : buildingList) {
            xpPerTurn += b.getXpPerTurn();
        }
    }

    private void calcProductionRates() {
        getProductionPool().resetPools();
        for (IBuilding b : buildingList) {
            b.resetProductionValues();
        }
        for (int i = 0; i < buildingList.size() * 2; i++) { //TODO write method to check when done
            for (IBuilding b : buildingList) {
                b.calculateProduction(getProductionPool(), b.getType());
            }
//            for (int j = 0; j < buildingList.size(); j++) {
//                buildingList.get(j).calculateProduction(getProductionPool(), buildingList.get(j).getType());
//            }
        }

    }

    public String getName() {
        return name;
    }

    public String[] getOwners() {
        return owners;
    }

    public List<IBuilding> getBuildingList() {
        return buildingList;
    }

    public List<IBuilding> getSubList(IBuilding.Buildings type) {
        return buildingList.stream().filter(b -> b.getType().equals(type)).collect(Collectors.toList());
    }

    public List<IBuilding> getNotSubList(IBuilding.Buildings type) {
        return buildingList.stream().filter(b -> !b.getType().equals(type)).collect(Collectors.toList());
    }

    public int getBuildingCount(IBuilding.Buildings type) {
        int x = 0;
        for (IBuilding b : buildingList) {
            if (b.getType().equals(type)) x++;
        }
        return x;
    }

    public double funcHouse(double x) {
        // y=-2.53377x^{1.8}+0.996198;
        return 10 * (-2.53377 * ((Math.pow((1.0 / 10.0) * x, 1.8))) + 0.996198);
    }

    public double getProductionPoolOfType(IBuilding.Buildings type) {
        List<IBuilding> sub = getSubList(type);
        double production = 0;
        for (int i = 0; i < sub.size(); i++) {
            production += sub.get(i).getProduction();
        }
        return production;
    }

    public double getXpPerTurn() {
        return xpPerTurn;
    }

    public ProductionPool getProductionPool() {
        return productionPool;
    }
}
