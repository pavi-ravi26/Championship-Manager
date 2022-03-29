import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class Formula1Driver extends Driver  {

    public static int[][] PointStats ={{1,2,3,4,5,6,7,8,9,10},{25,18,15,12,10,8,6,4,2,1}};
    private static String[] Positions=new String[10];
    public static String date;

    public static String getDate() {
        return date;
    }

    public static void setDate(String races) {
        date = races;
    }

    public static int[][] getPointStats() {
        return PointStats;
    }

    public static void setPointStats(int[][] pointStats) {
        PointStats = pointStats;
    }

    public static String[] getPositions() {
        return Positions;
    }

    public static void setPositions(String[] positions) {
        Positions = positions;
    }



    public Formula1Driver(String Name, String Location, String Team, int[] Stats){
        super(Name,Location,Team,Stats);
    }

    public Formula1Driver() {
        super();
    }

    public Formula1Driver(String date,String[] Positions){
        this.date=date;
        this.Positions=Positions;
    }


    public static void calcPoints(ArrayList<Driver> driver,int[][] PointStats,int index){
        int points;
        int totPoints=0;
        int noOfRaces=0;
        Driver d = driver.get(index);
        int[] stats = d.getDrStats();
        for (int i=0;i<stats.length;i++) {
                if (stats[i]!=0){
                    points = stats[i]*PointStats[1][i];
                    totPoints+=points;
                    noOfRaces+=stats[i];
                }else{
                    continue;
                }
        }
        //System.out.println("Total Points is "+totPoints);
        d.setPoints(totPoints);
        d.setNoOfRaces(noOfRaces);
    }
    //sorting
    @Override                                              //Referred from https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/
    public int compareTo(Driver comparesto) {
        int comparepoints=((Driver)comparesto).getPoints();
        return comparepoints-getPoints();
    }

    @Override
    public String toString() {
        return " " + getDrName() + " | " + getDrTeam() + " | " + getDrStats().toString() + " | " + getNoOfRaces()+" | "+ getPoints();

    }
    //to write Driver position array in the text file
    public static String printPositions(Formula1Driver race){
        String pos = " ";
        for (String r:race.getPositions()){
            pos = pos.concat(r+" , ");
        }
        return pos;
    }

    //updatating driver's statistics when a new race is added
    public void updateDriver(ArrayList<Driver> driver,Formula1Driver Race){
        String[] arrDriver =Race.getPositions();
        //int[] arrPositions = getDrStats();
        for (int i=0;i< arrDriver.length;i++) {
            for (Driver d : driver) {
                int[] arrPositions = d.getDrStats();
                if (arrDriver[i].equals(d.getDrName())) {
                      arrPositions[i]+=1;
                      d.setDrStats(arrPositions);
                }else{
                    continue;
                }
            }
        }

    }
    //GUI methods

    // generate random race
    public Formula1Driver generateRandomRace(ArrayList<Formula1Driver> RacesList,ArrayList<Driver> DriverList){
        Random r = new Random();

        String[] pos = new String[10];
        for(int i=0;i<10;i++) {
            int randomIndex = r.nextInt(DriverList.size());
            System.out.println(r.nextInt(DriverList.size()));
            String randomElement = DriverList.get(randomIndex).getDrName();
            pos[i]=randomElement;

        }

        String date =new SimpleDateFormat("yyyyMMdd").format(new Date());

        Formula1Driver newRace = new Formula1Driver(date,pos);
        RacesList.add(newRace);
        newRace.updateDriver(DriverList,newRace);
        System.out.println("New race generated");
        return newRace;
    }
    public void sortByPosition(ArrayList<Driver> driver){

    }




    
        
    
}





