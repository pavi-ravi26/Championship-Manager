import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;




class newGUI extends Formula1Driver implements ActionListener {
    public ArrayList<Driver> DriverList = new ArrayList();
    public ArrayList<Formula1Driver> RacesList = new ArrayList();
    DefaultTableModel tableModel1 = new DefaultTableModel();
    DefaultTableModel tableModel2 = new DefaultTableModel();
    JButton b1 = new JButton("Generate Race");
    JButton b2 = new JButton("Find Probability to win");
    JButton b3 = new JButton("Show race Table");
    JButton b4 = new JButton("Search");
    JTextField t4 = new JTextField(20);
    JFrame frame = new JFrame(" SimpleSwingExample ");
    JLabel l1 =new JLabel("Formula 1 Driver Table");
    JLabel l2 =new JLabel("Formula 1 Championship Table");

    public ArrayList<Driver> getDriverList() {
        return DriverList;
    }

    public void setDriverList(ArrayList<Driver> driverList) {
        DriverList = driverList;
    }

    public ArrayList<Formula1Driver> getRacesList() {
        return RacesList;
    }

    public void setRacesList(ArrayList<Formula1Driver> racesList) {
        RacesList = racesList;
    }

    newGUI(ArrayList<Driver> driver, ArrayList<Formula1Driver> Race){
        this.DriverList=driver;
        this.RacesList=Race;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);

        frame.setLayout(new FlowLayout());

        frame.getContentPane().add(l1);
        JScrollPane sp = new JScrollPane(newDTableModel());
        frame.add(sp);

        frame.getContentPane().add(newList());

        frame.getContentPane().add(l2);
        JScrollPane sp2 = new JScrollPane(newCTableModel());
        frame.add(sp2);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        frame.getContentPane().add(b1);
        frame.getContentPane().add(b2);
        frame.getContentPane().add(b3);

        frame.getContentPane().add(t4);
        frame.getContentPane().add(b4);





    }

    public JTable newDTableModel(){

        JTable table = new JTable(tableModel1);
        //tableModel.addColumn(new Object["Driver Name","Statistics","Points"]);
        tableModel1.addColumn("Driver Name");
        tableModel1.addColumn("Driver Statistics");
        tableModel1.addColumn("Total Points");

        for(int i=0;i<getDriverList().size();i++) {
            Driver d = getDriverList().get(i);
            tableModel1.addRow( new Object[]{d.getDrName(),printStats(d),d.getPoints()});
        }
        return  table;
    }
    public JTable newCTableModel(){

        JTable table2 = new JTable(tableModel2);
        //tableModel.addColumn(new Object["Driver Name","Statistics","Points"]);
        tableModel2.addColumn("Driver positions in order");
        tableModel2.addColumn("Date");

        for(int i=0;i<getRacesList().size();i++) {
            Formula1Driver r = getRacesList().get(i);
            tableModel2.addRow( new Object[]{printPositions(r),r.getDate()});
        }
        return  table2;
    }
    public JList newList(){
        DefaultListModel<String> l1 = new DefaultListModel<>();
        l1.addElement("Sort by high -> low ");
        l1.addElement("Sort by low -> high");
        l1.addElement("Sort by first positions");
        JList<String> list = new JList<>(l1);
        list.setBounds(100,100, 75,75);


        list.addListSelectionListener(e -> {

            if (list.getSelectedIndex() == 0) {  // sort Ascending Order
                int n= getDriverList().size();
                for (int i=n;i!=0;i--) {
                    tableModel1.removeRow(n-i);
                    Driver d = getDriverList().get(n-i);
                    tableModel1.insertRow(n-i,new Object[]{d.getDrName(),printStats(d),d.getPoints()});
                }

                System.out.println("Ascending Order");
            } else if (list.getSelectedIndex() == 1) { //sort Descending Order

                int n= getDriverList().size();
                for (int i=n;i!=0;i--) {
                    tableModel1.removeRow(n-i);
                    Driver d = getDriverList().get(i-1);
                    tableModel1.insertRow(n-i,new Object[]{d.getDrName(),printStats(d),d.getPoints()});
                }
                System.out.println("Descending Order");
            } else if (list.getSelectedIndex() == 2) {  //sort by first position

                //sorting by first position
                /*int i, j, temp;
                boolean swapped;
                for (i = 0; i < DriverList.size() - 1; i++)
                {
                    swapped = false;
                    for (j = 0; j < DriverList.size() - i - 1; j++)
                    {
                        if (DriverList.get(j).getDrStats()[0] > DriverList.get(j+1).getDrStats()[0])
                        {
                            // swap arr[j] and arr[j+1]
                            temp = arr[j];
                            DriverList.set(j,DriverList.get(j+1));
                            DriverList.set(j+1,DriverList.get(j));
                            arr[j] = arr[j + 1];
                            arr[j + 1] = temp;
                            swapped = true;
                        }
                    }

                    // IF no two elements were
                    // swapped by inner loop, then break
                    if (swapped == false)
                        break;*/
                int temp =DriverList.get(0).getDrStats()[0];
                int index=0;
                for(int j=1;j<DriverList.size()+1;j++) {
                    for (int i = 0; i < DriverList.size()-j; i++) {
                        if (DriverList.get(i + 1).getDrStats()[0] > temp) {
                            temp = DriverList.get(i + 1).getDrStats()[0];
                            index = i+1;
                        }
                    }
                    tableModel1.insertRow(j, new Object[]{DriverList.get(index).getDrName(), printStats(DriverList.get(index)), DriverList.get(index).getPoints()});
                }
            }
        });
        return list;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==b1){   //generate race
            Formula1Driver r = generateRandomRace(RacesList, DriverList);
            tableModel2.addRow( new Object[]{printPositions(r),r.getDate()});

        }else if (e.getSource()==b2){   //generate probabilistic race

        }else if (e.getSource()==b3){   //Show races sorted by date
            for(int i=0;i<getRacesList().size();i++) {
                Formula1Driver r = getRacesList().get(i);
                tableModel2.addRow( new Object[]{printPositions(r),r.getDate()});
            }

        }else{  //search races the given driver participate
            for (int i=0;i<tableModel2.getRowCount();i++){
                tableModel2.removeRow(i);
            }

            String name = t4.getText();
            for (Formula1Driver r:RacesList) {
                for (String n : r.getPositions()) {
                    if (name.equals(n)) {
                        tableModel2.addRow(new Object[]{printPositions(r), r.getDate()});
                        break;
                    }

                }
            }
            }
        }
    }













