import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Formula1ChampionsManager implements ChampionshipManager {

    public ArrayList<Driver> DriverList = new ArrayList();
    public ArrayList<Formula1Driver> RacesList = new ArrayList();
    public TestClass test =new TestClass();

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        boolean flag=true;
        Formula1ChampionsManager Championship = new Formula1ChampionsManager();

        while (flag == true) {
            System.out.println("Formula 1 Racing Car Championship");
            System.out.println("Menu\n" +
                    "1. Create a new Driver\n" +
                    "2. Delete a Driver\n" +
                    "3. Change a Driver\n" +
                    "4. Driver statistics\n" +
                    "5. View Formula 1 Driver analysis table\n" +
                    "6. Add a race\n" +
                    "7. Store the entered details\n" +
                    "8. Retrieve Details\n" +
                    "9. Exit\n" +
                    "Enter your option : "
            );
           int option = input.nextInt();

            if (option == 1) {
                Championship.createDriver();
            } else if (option == 2) {
                Championship.deleteDriver();
            } else if (option == 3) {
                Championship.changeDriver();
            } else if (option == 4) {
                 Championship.showDriverStatistics();
            } else if (option == 5) {
                 Championship.viewDriverTable();
            } else if (option == 6) {
                 Championship.addRace();
            } else if (option == 7) {
                 Championship.storeDetails();
            } else if (option == 8) {
                 Championship.loadDetails();
            } else if (option == 9) {
                flag = false;
                System.out.println("System is Exiting...");
            }
        }
    }

    @Override
    public void createDriver() {
        test.initialiseDriver(DriverList);
        test.initialiseRace(RacesList);
        Scanner input = new Scanner(System.in);


        System.out.println("Enter Driver's Name: ");
        String name = input.nextLine();

        System.out.println("Enter Driver's Location: ");
        String location = input.nextLine();

        System.out.println("Enter Driver's Team(Car Constructors): ");
        String team = input.nextLine();

        Driver.checkTeam(DriverList,team);

        System.out.println("Enter Driver's Statistics ");
        int[] stats = new int[10];
        int position;
        for (int i =0;i<stats.length;i++)
        {
            System.out.print("Enter the number of "+(i+1)+"th positions in the season: ");
            position = input.nextInt();
            stats[i]=position;
        }

        Driver driver = new Formula1Driver(name,location,team,stats);
        DriverList.add(driver);

        //Calculating points,no of races automatically
        int index = DriverList.indexOf(driver);
        Formula1Driver.calcPoints(DriverList,Formula1Driver.getPointStats(),index);
        System.out.println("Driver is added");


    }

    @Override
    public void deleteDriver() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Driver's Name: ");
        String name = input.nextLine();
        int index =Driver.checkName(DriverList,name);
        DriverList.remove(index);
        System.out.println(name+" is removed.");

    }

    @Override
    public void changeDriver() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Driver's Team: ");
        String team = input.nextLine();
        Driver.checkTeam(DriverList,team,input);
        System.out.println("Driver is updated");
    }

    @Override
    public void showDriverStatistics() {
        Scanner input = new Scanner(System.in);
        Formula1Driver formula1 =new Formula1Driver();

        System.out.println("Enter Driver's Name: ");
        String name = input.nextLine();
        for (Driver d:DriverList){
            if (name.equals(d.getDrName())){
                System.out.println("Positions in each races: ");
                for (int pos:d.getDrStats()){
                    System.out.print(pos+"  ");
                }
                System.out.println();
                System.out.println("No of races "+(d.getNoOfRaces()));
                System.out.println("Total Points is "+(d.getPoints()));

            }
        }
    }


    @Override
    public void viewDriverTable() {
        //Formula1Driver f = new Formula1Driver();
        System.out.println("Displaying Driver Table");
        ArrayList<Driver> sortDriver = DriverList;
        Collections.sort(sortDriver);
        System.out.println(" " + "Driver Name" + " | " + "Driver Team" + " | " + "Driver Statistics" + " | " + "Total Points");
        for (Driver d:sortDriver){
            System.out.println(" " + d.getDrName() + "  " + d.getDrTeam() +  "  " + d.printStats(d) +  "  " + d.getPoints());
        }
        //Opening GUI window
        new newGUI(DriverList,RacesList);
    }


    @Override
    public void addRace() {

        Scanner input = new Scanner(System.in);

        System.out.println("Enter the date of new race: ");
        String date = input.nextLine();

        System.out.println("Enter Driver's Statistics ");

        //updating drivers for each positions
        String[] arrPositions = new String[10];
        for(int i=0;i<10;i++)
        {
            System.out.print("Enter the drivers name ("+(i+1)+") position: ");
            String driver = input.nextLine();
            arrPositions[i]=driver;
        }
        Formula1Driver race = new Formula1Driver(date,arrPositions);
        RacesList.add(race);
        race.updateDriver(DriverList,race);

        //System.out.println("Formula1 Championship table");
        //for (Formula1Driver r:RacesList){
        //    System.out.println(" " + r.getDate() + "  " + r.printPositions(r));
        //}
    }

    @Override
    public void storeDetails() throws IOException {
        //Formula1Driver f = new Formula1Driver();
        try {
            FileWriter data = new FileWriter("Formula1Championship.txt");

            for (Driver d : DriverList) {
                data.write(" " + d.getDrName() + "  " + d.getDrTeam() + "  " + d.getDrLocation() + "  " + d.printStats(d) + "  " + d.getNoOfRaces() + "  " + d.getPoints()+"\n");

            }

            for (Formula1Driver r : RacesList) {
                data.write(" " + r.getDate() + "  " + r.printPositions(r)+"\n");
            }

            System.out.println("Program has updated in the text file.");
            data.close();
        }catch (IOException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    @Override
    public void loadDetails() {
        try{
            File data= new File("Formula1Championship.txt");
            Scanner loadData = new Scanner(data);
            while(loadData.hasNextLine()){
                //String s=loadData.nextLine();
                //String s1= loadData.nextLine();
                for (Driver d: DriverList) {
                    String name = loadData.next();
                    d.setDrName(name);
                    System.out.println(name);
                    String loca = loadData.next();
                    d.setDrLocation(loca);
                    System.out.println(loca);
                    String team = loadData.next();
                    d.setDrTeam(team);
                    System.out.println(team);
                    int[] stats = new int[10];
                    int position;
                    for (int i = 0; i < stats.length; i++) {
                        System.out.print("Enter the number of " + (i + 1) + "th positions in the season: ");
                        position = loadData.nextInt();
                        loadData.next();
                        stats[i] = position;
                    }
                    d.setDrStats(stats);
                    int index = DriverList.indexOf(d);
                    Formula1Driver.calcPoints(DriverList, Formula1Driver.getPointStats(), index);


                }


            }
            loadData.close();
        }catch (FileNotFoundException e) {
            System.out.print("Couldn't load data");
            e.printStackTrace();
        }

    }
}
