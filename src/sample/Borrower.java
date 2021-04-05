package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;



public class Borrower {

    private static Scanner x;
    private String BName;
    private String bookBorrowed;
    private LocalDate TimeNow;
    private boolean BookStatusIsExpired;
    private String BorrowerInformation;
    private String HistoryRecord;
    private boolean UserIsValidate;
    private int Overdue;
    private ObservableList ListViewItem;

    public Borrower() {

        BName = "";
    }

    public void setListViewItem(ObservableList listViewItem) {
        ListViewItem = listViewItem;
    }

    public ObservableList getListViewItem() {
        return ListViewItem;
    }

    public int getOverdue() {
        return Overdue;
    }

    public void setOverdue(int overdue) {
        Overdue = overdue;
    }

    public boolean isUserIsValidate() {
        return UserIsValidate;
    }

    public void setUserIsValidate(boolean userIsValidate) {
        UserIsValidate = userIsValidate;
    }

    public boolean isBookStatusIsExpired() {
        return BookStatusIsExpired;
    }

    public void setBookStatusIsExpired(boolean bookStatusIsExpired) {
        BookStatusIsExpired = bookStatusIsExpired;
    }

    public void LendBook(String BName, String BookName) {

        try {
            //Convert BName and BookName into String
            TimeNow = LocalDate.now();
            bookBorrowed = BName + ":" + BookName + ":" + TimeNow + ":" + TimeNow.plusDays(14);

            Scanner scanBorrowBook = new Scanner(new File("BorrowBook.txt"));
            while (scanBorrowBook.hasNext()) {
                String line = scanBorrowBook.nextLine();
            }
            FileWriter FW = new FileWriter("BorrowBook.txt", true);
            PrintWriter PW = new PrintWriter(FW);

            PW.println(bookBorrowed);
            PW.close();
            scanBorrowBook.close();

        }
            catch(Exception e){

        }

        try {
            HistoryRecord = "T2: "+BName + ": Successfully borrowed "+BookName+" at "+ LocalDate.now();

            Scanner x = new Scanner(new File("History.txt"));
            while (x.hasNext()) {
                String line = x.nextLine();
            }
            FileWriter FW = new FileWriter("History.txt", true);
            PrintWriter PW = new PrintWriter(FW);

            PW.println(HistoryRecord);
            PW.close();
            x.close();
        }
        catch(Exception e){

        }


    }

    public void ReturnBook(String BName, String BookName) {

        String tempfile = "TempBorrowBook.txt";
        String pathfile = "BorrowBook.txt";

        File oldFile = new File(pathfile);
        File newFile = new File(tempfile);

        String BorrowName=""; String BorrowBookName=""; String BorrowDate =""; String ExpiryDate="";

        try{
            FileWriter fw = new FileWriter(tempfile, true);
            PrintWriter pw = new PrintWriter(fw);
            x = new Scanner(new File(pathfile));
            while (x.hasNext()){
                String BorrowBook = x.nextLine();
                String[] BorrowBookDetail = BorrowBook.split(":");
                BorrowName=BorrowBookDetail[0];
                BorrowBookName=BorrowBookDetail[1];
                BorrowDate=BorrowBookDetail[2];
                ExpiryDate=BorrowBookDetail[3];

                if(!BorrowName.equals(BName) || !BorrowBookName.equals(BookName)) {
                        pw.println(BorrowName + ":" + BorrowBookName + ":" + BorrowDate + ":" + ExpiryDate);
                    }
                }

            x.close();
            pw.close();
            oldFile.delete();
            File dump = new File(pathfile);
            newFile.renameTo(dump);

        }
        catch(Exception e){

            e.printStackTrace();

        }

        try {
            HistoryRecord = "T3: "+BName + ": Successfully return "+BookName+" At "+ LocalDate.now();

            Scanner x = new Scanner(new File("History.txt"));
            while (x.hasNext()) {
                String line = x.nextLine();
            }
            FileWriter FW = new FileWriter("History.txt", true);
            PrintWriter PW = new PrintWriter(FW);

            PW.println(HistoryRecord);
            PW.close();
            x.close();
        }
        catch(Exception e){

        }

    }

    public void ExtendBorrowTime(String BName, String BookName) {
        String tempfile = "TempBorrowBook.txt";
        String pathfile = "BorrowBook.txt";

        File oldFile = new File(pathfile);
        File newFile = new File(tempfile);

        TimeNow = LocalDate.now();
        String BorrowerName = "";
        String BorrowBookName = "";
        String BorrowDate = "";
        String ExpiryDate = "";
        try {
            FileWriter fw = new FileWriter(tempfile, true);
            PrintWriter pw = new PrintWriter(fw);
            x = new Scanner(new File(pathfile));
            while (x.hasNext()) {
                String BorrowBook = x.nextLine();
                String[] BorrowBookDetail = BorrowBook.split(":");
                BorrowerName = BorrowBookDetail[0];
                BorrowBookName = BorrowBookDetail[1];
                BorrowDate = BorrowBookDetail[2];
                ExpiryDate = BorrowBookDetail[3];

                if (BorrowerName.equals(BName) && BorrowBookName.equals(BookName)) {
                    pw.println(BorrowerName + ":" + BorrowBookName + ":" + TimeNow + ":" + TimeNow.plusDays(14));

                } else {
                    pw.println(BorrowerName + ":" + BorrowBookName + ":" + BorrowDate + ":" + ExpiryDate);
                }
            }
            x.close();
            pw.close();
            oldFile.delete();
            File dump = new File(pathfile);
            newFile.renameTo(dump);

        }catch (Exception e) {
            e.printStackTrace();
        }

        try {

            HistoryRecord = "T4: "+BName + ": Successfully extended "+BookName+" for another 2 weeks at "+ LocalDate.now();

            Scanner x = new Scanner(new File("History.txt"));
            while (x.hasNext()) {
                String line = x.nextLine();
            }
            FileWriter FW = new FileWriter("History.txt", true);
            PrintWriter PW = new PrintWriter(FW);

            PW.println(HistoryRecord);
            PW.close();
            x.close();
        }
        catch(Exception e){

        }




    }

    public void CheckIfExpired(String BName, String BookName)throws IOException{

        String pathfile = "BorrowBook.txt";
        String ExpiryDate; String BorrowName=""; String BorrowBookName="";

        x = new Scanner(new File(pathfile));
        while (x.hasNext()) {
            String BorrowBook = x.nextLine();
            String[] BorrowBookDetail = BorrowBook.split(":");
            BorrowName = BorrowBookDetail[0];
            BorrowBookName = BorrowBookDetail[1];
            ExpiryDate = BorrowBookDetail[3];

            if(BorrowName.equals(BName) && BorrowBookName.equals(BookName)) {

                final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                final LocalDate FirstDate = LocalDate.now();
                final LocalDate SecondDate = LocalDate.parse(ExpiryDate, timeFormatter);
                final long DaysBetween = ChronoUnit.DAYS.between(FirstDate, SecondDate);
                if (DaysBetween <1) {
                   setBookStatusIsExpired(true);
                   setOverdue((int) Math.abs(DaysBetween));
                }else{
                   setBookStatusIsExpired(false);

                }
            }

        }
        x.close();
    }

    public void RegisterBorrower(String BName, String BDOB){

        try {
            //Convert Register information into line
            BorrowerInformation = BName + ":" + BDOB;

            Scanner scanBorrowBook = new Scanner(new File("Login.txt"));
            while (scanBorrowBook.hasNext()) {
                String line = scanBorrowBook.nextLine();
            }
            FileWriter FW = new FileWriter("Login.txt", true);
            PrintWriter PW = new PrintWriter(FW);

            PW.println(BorrowerInformation);
            PW.close();
            scanBorrowBook.close();

        }
        catch(Exception e){

        }

        try {
            HistoryRecord = "T1: "+BName+ ": Successfully registered as a Borrower at "+ LocalDate.now();

            Scanner x = new Scanner(new File("History.txt"));
            while (x.hasNext()) {
                String line = x.nextLine();
            }
            FileWriter FW = new FileWriter("History.txt", true);
            PrintWriter PW = new PrintWriter(FW);

            PW.println(HistoryRecord);
            PW.close();
            x.close();
        }
        catch(Exception e){

        }

    }

    public void ValidateUser(String BName)throws IOException{

        String pathfile = "Login.txt";

        String BorrowerName;


            Scanner x = new Scanner(new File(pathfile));
            while (x.hasNext()){
                String borrower = x.nextLine();
                String[] BorrowerDetail = borrower.split(":");
                BorrowerName = BorrowerDetail[0];


                if(BName.equals(BorrowerName)){

                 setUserIsValidate(true);
                 break;

                }else{
                 setUserIsValidate(false);
                }
            }
            x.close();


    }

    public void CalculateOverdue(String BName, String BookName) throws IOException {

        String pathfile = "Overdue.txt";

        int OverdueDate;
        String BorrowerOverdue;

        OverdueDate = getOverdue();

        BorrowerOverdue = BName + ":" + OverdueDate + ":" + BookName;


        Scanner x = new Scanner(new File(pathfile));
        while (x.hasNext()) {
            String line = x.nextLine();
        }
        FileWriter FW = new FileWriter("Overdue.txt", true);
        PrintWriter PW = new PrintWriter(FW);

        PW.println(BorrowerOverdue);
        PW.close();
        x.close();

        try {
            HistoryRecord = "T3: "+BName + ": Successfully return "+BookName+" but come with a fine of RM" +OverdueDate+ " At "+ LocalDate.now();

            x = new Scanner(new File("History.txt"));
            while (x.hasNext()) {
                String line = x.nextLine();
            }
            FW = new FileWriter("History.txt", true);
            PW = new PrintWriter(FW);

            PW.println(HistoryRecord);
            PW.close();
            x.close();
        }
        catch(Exception e){

        }
    }
    public void ShowHistory(String BorrowerName) throws IOException{

        ListViewItem = FXCollections.observableArrayList();
        String BName;
        String pathfile = "History.txt";
        Scanner x = new Scanner(new File(pathfile));
        x.useDelimiter(": ");
        while (x.hasNext()){
            String line = x.nextLine();
            String [] Historyline= line.split(": ");
            BName = Historyline[1];
            if(BName.equals(BorrowerName)){
                ListViewItem.add(Historyline[2]);
            }
            setListViewItem(ListViewItem);
        }
    }
    public void ShowHistory(String BorrowerName, String HistoryType)throws IOException{
        ListViewItem = FXCollections.observableArrayList();

        String BName;
        String HT;
        String pathfile = "History.txt";
        Scanner x = new Scanner(new File(pathfile));
        x.useDelimiter(": ");
        while (x.hasNext()){
            String line = x.nextLine();
            String [] Historyline= line.split(": ");
            BName = Historyline[1];
            HT=Historyline[0];
            if(BName.equals(BorrowerName) && HT.equals(HistoryType)){
                ListViewItem.add(Historyline[2]);
            }
            setListViewItem(ListViewItem);
        }
    }
}


