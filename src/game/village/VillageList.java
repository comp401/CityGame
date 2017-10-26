package game.village;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris Burgess on 5/27/2017.
 */
public class VillageList {
    private List<Village> villageList;
    public VillageList(){
        villageList = new ArrayList<>();
    }

    //TODO: check if other game.village exists with same name
    public boolean createVillage(String name, String[] owner, Vec3 pos){
        villageList.add(new Village(name, owner, pos));
        return true;
    }
    public boolean deleteVillage(String name){
        for (Village v: villageList){
            if (v.getName().equals(name)){
                villageList.remove(villageList.indexOf(v));
                return true;
            }
        }
        return false;
    }
    public String printVillageList(){
        String s = "(";
        for (int i = 0; i < villageList.size(); i++) {
            s+=villageList.get(i).getName();
            if (i != villageList.size() -1)
            s+= ", ";
        }
        s+=")";
        return s;
    }

    public Village getVillageByOwner(String name){
        for (Village v: villageList){
            if (v.getOwners()[0].equals(name)){
                return v;
            }
        }
        return null;
    }

    public List<Village> getVillageList() {
        return villageList;
    }
}
