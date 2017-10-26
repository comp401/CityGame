package game;

import game.village.IBuilding;
import game.village.Vec3;
import game.village.Village;
import game.village.VillageList;

public class Main {

    public static void main(String[] args) {
        VillageList villageList = new VillageList();
        String s[] = new String[]{"hello", "heaa"};

        villageList.createVillage("Town of Name!", s, new Vec3(1, 1, 1));

        Village v = villageList.getVillageByOwner("hello");

        v.createBuilding(IBuilding.Buildings.FARM, new Vec3(0, 0, 0));
        v.createBuilding(IBuilding.Buildings.FARM, new Vec3(0, 0, 0));
        v.createBuilding(IBuilding.Buildings.FARM, new Vec3(0, 0, 0));
        v.createBuilding(IBuilding.Buildings.FARM, new Vec3(0, 0, 0));
        v.createBuilding(IBuilding.Buildings.FARM, new Vec3(0, 0, 0));

        //v.addBuilding(new Farm(new structs.Vec3(0,0,0)))


        v.getSubList(IBuilding.Buildings.FARM).forEach(b -> System.out.println(b.getXpPerTurn()));
        System.out.println(v.getXpPerTurn());

        System.out.println("POOL VALUES:");
        System.out.println("food:    " + v.getProductionPool().getFood());
        System.out.println("raw:     " + v.getProductionPool().getRawOre());
        System.out.println("smelted: " + v.getProductionPool().getSmeltedOre());
        System.out.println("lumber:  " + v.getProductionPool().getLumber());
        System.out.println(IBuilding.Buildings.FARM.smeltedOreReq);


    }
}
