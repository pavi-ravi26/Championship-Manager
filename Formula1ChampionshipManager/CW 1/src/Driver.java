import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.Scanner;

abstract class Driver implements Comparable<Driver>{
    public String DrName;
    public String DrLocation;
    public String DrTeam;
    public int[] DrStats= new int[10];
    public int Points;
    public int NoOfRaces;


    public Driver(){}

    public Driver (String Name,String Location,String Team,int[] Stats){
         this.DrName=Name;
         this.DrLocation=Location;
         this.DrTeam=Team;
         this.DrStats=Stats;
    }

    public String getDrName() {
        return DrName;
    }

    public void setDrName(String drName) {
        DrName = drName;
    }

    public String getDrLocation() {
        return DrLocation;
    }

    public void setDrLocation(String drLocation) {
        DrLocation = drLocation;
    }

    public String getDrTeam() {
        return DrTeam;
    }

    public void setDrTeam(String drTeam) {
        DrTeam = drTeam;
    }

    public int[] getDrStats() {
        return DrStats;
    }

    public void setDrStats(int[] drStats) {
        DrStats = drStats;
    }

    public int getPoints() {
        return Points;
    }

    public void setPoints(int points) {
        Points = points;
    }

    public int getNoOfRaces() {
        return NoOfRaces;
    }

    public void setNoOfRaces(int noOfRaces) {
        NoOfRaces = noOfRaces;
    }

    public static void checkTeam(ArrayList<Driver> driver, String team){
        for (Driver d:driver){
            if (team.equals(d.getDrTeam())){
                System.out.println("Team is already exists");
                break;
            }else{
                continue;
            }
        }
    }

    public static int checkName(ArrayList<Driver> driver,String name){
        int index =0;
        for (Driver d:driver){
            if (name.equals(d.getDrName())){
                index=driver.indexOf(d);
            }
        }
        return index;
    }

    public static void checkTeam(ArrayList<Driver> driver, String team, Scanner input){
        for (Driver d:driver){
            if (team.equals(d.getDrTeam())){
                System.out.println("Enter Driver's Name: ");
                String name = input.nextLine();
                d.setDrName(name);
                break;
            }
        }
    }


    public static String printStats(Driver driver){
        String pos = " ";
        for (int d:driver.getDrStats()){
            pos = pos.concat(d+" , ");
        }
        return pos;
    }
    //for testing purposes
    public static void initialiseDriver(ArrayList<Driver> driver){
        Driver d1 = new Formula1Driver("John","Canada","Ferrari", new int[]{1, 3, 2, 6, 0, 0, 3, 2, 0, 0});
        Driver d2 = new Formula1Driver("Peter","Singapore","Benz", new int[]{0,0,4,2,3,0,0,1,6,6});
        Driver d3 = new Formula1Driver("Sam","London","Audi", new int[]{2,0,4,5,0,1,0,1,1,2});
        Driver d4 = new Formula1Driver("Will","Swizz","BMW ", new int[]{0,0,4,2,5,6,2,3,0,1});
        Driver d5 = new Formula1Driver("Hamton","India","Hyndai", new int[]{4,2,1,0,3,0,2,0,1,7});
        Driver d6 = new Formula1Driver("Roy","India","Honda", new int[]{3,4,6,2,1,9,0,0,4,3});
        Driver d7 = new Formula1Driver("Wix","NewZealand","Dodge", new int[]{6,2,4,6,8,1,2,1,1,0});
        Driver d8 = new Formula1Driver("Eman","London","Jaguar", new int[]{1,0,0,2,3,5,7,3,2,0});
        Driver d9 = new Formula1Driver("Joy","Australia","MG", new int[]{0,0,3,2,5,6,0,0,1,3});
        Driver d10 = new Formula1Driver("Ben","Africa","Mazda", new int[]{5,6,1,2,7,6,8,9,0,1});
        driver.add(d1);
        driver.add(d2);
        driver.add(d3);
        driver.add(d4);
        driver.add(d5);
        driver.add(d6);
        driver.add(d7);
        driver.add(d8);
        driver.add(d9);
        driver.add(d10);
        for (int i=0;i<10;i++) {
            Formula1Driver.calcPoints(driver, Formula1Driver.getPointStats(), i);
        }
    }
    public static void initialiseRace(ArrayList<Formula1Driver> Races){
        Formula1Driver r1 = new Formula1Driver("1/12/21",new String[]{"John","Sam","Peter","Will","Roy","Hamton","Wix","Eman","Ben","Joy"});
        Formula1Driver r2 = new Formula1Driver("3/12/21",new String[]{"Roy","Hamton","Wix","Eman","Ben","Joy","John","Sam","Peter","Will"});
        Formula1Driver r3 = new Formula1Driver("5/12/21",new String[]{"Will","Roy","Hamton","John","Wix","Eman","Ben","Sam","Peter","Joy"});
        Races.add(r1);
        Races.add(r2);
        Races.add(r3);
    }

}