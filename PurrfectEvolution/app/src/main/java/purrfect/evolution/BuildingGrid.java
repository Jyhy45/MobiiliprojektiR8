package purrfect.evolution;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by jyhy on 3/28/17.
 */

public class BuildingGrid {

    BuildingGrid(){
        buildingArray = new ArrayList<>();
        buildingArray.add(building1);
        buildingArray.add(building2);
        buildingArray.add(building3);
        buildingArray.add(building4);
        buildingArray.add(building5);
        buildingArray.add(building6);
        buildingArray.add(building7);
        buildingArray.add(building8);
        buildingArray.add(building9);
    }
    public void reset_buildings(){
        for (Building b:
             buildingArray) {
            b.setmBuildingLevel(0);
            b.setmBType(Building.BuildingType.NONE);
        }

    }
    public void reset_building(int indexOfArray){
        buildingArray.get(indexOfArray).setmBuildingLevel(0);
        buildingArray.get(indexOfArray).setmBType(Building.BuildingType.NONE);
    }
    public void reset_building(Building b){
        b.setmBType(Building.BuildingType.NONE);
        b.setmBuildingLevel(0);
    }

    private Building building1 = new Building();
    private Building building2 = new Building();
    private Building building3 = new Building();
    private Building building4 = new Building();
    private Building building5 = new Building();
    private Building building6 = new Building();
    private Building building7 = new Building();
    private Building building8 = new Building();
    private Building building9 = new Building();

    public ArrayList<Building> getBuildingArray() {
        return buildingArray;
    }

    private ArrayList<Building> buildingArray;


    public Building getBuilding1() {
        return building1;
    }

    public void setBuilding1(Building building1) {
        this.building1 = building1;
    }

    public Building getBuilding2() {
        return building2;
    }

    public void setBuilding2(Building building2) {
        this.building2 = building2;
    }

    public Building getBuilding3() {
        return building3;
    }

    public void setBuilding3(Building building3) {
        this.building3 = building3;
    }

    public Building getBuilding4() {
        return building4;
    }

    public void setBuilding4(Building building4) {
        this.building4 = building4;
    }

    public Building getBuilding5() {
        return building5;
    }

    public void setBuilding5(Building building5) {
        this.building5 = building5;
    }

    public Building getBuilding6() {
        return building6;
    }

    public void setBuilding6(Building building6) {
        this.building6 = building6;
    }

    public Building getBuilding7() {
        return building7;
    }

    public void setBuilding7(Building building7) {
        this.building7 = building7;
    }

    public Building getBuilding8() {
        return building8;
    }

    public void setBuilding8(Building building8) {
        this.building8 = building8;
    }

    public Building getBuilding9() {
        return building9;
    }

    public void setBuilding9(Building building9) {
        this.building9 = building9;
    }
}
