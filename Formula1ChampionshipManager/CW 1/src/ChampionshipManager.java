import java.io.IOException;

public interface ChampionshipManager {
   // public void  createDriver(int count);
   public void  createDriver();
    public void deleteDriver();
    public void changeDriver();
    public void showDriverStatistics();
    public void viewDriverTable();
    public void addRace();
    public void storeDetails() throws IOException;
    public void loadDetails();




}
