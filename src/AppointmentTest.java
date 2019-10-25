import aaronjay.Appointment;
import aaronjay.Daily;
import aaronjay.Monthly;
import aaronjay.Onetime;
import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class AppointmentTest extends TestCase {


    public void testNewAppointment() {
        ArrayList<Appointment> appointmentBook = new ArrayList<Appointment>();
        String description;
        int year, month, day;
        int[] correctDate = new int[3];
        int appointmentNum = 0;

        //Tests creating a new daily appointment
        description = "1st Daily";
        year = 2019;
        month = 10;
        day = 24;
        correctDate[0] = year;
        correctDate[1] = month;
        correctDate[2] = day;
        appointmentBook.add(new Daily(description, year, month, day));
        assertEquals("Description should be correct", description, appointmentBook.get(appointmentNum).getDescription());
        assertTrue("Date should be correct" , Arrays.equals(correctDate, appointmentBook.get(appointmentNum).getDate()));
        appointmentNum++;

        //Tests creating a new Monthly appointment
        description = "1st Monthly";
        year = 2010;
        month = 4;
        day = 2;
        correctDate[0] = year;
        correctDate[1] = month;
        correctDate[2] = day;
        appointmentBook.add(new Monthly(description, year, month, day));
        assertEquals("Description should be correct", description, appointmentBook.get(appointmentNum).getDescription());
        assertTrue("Date should be correct" , Arrays.equals(correctDate, appointmentBook.get(appointmentNum).getDate()));
        appointmentNum++;

        //Tests creating a new Onetime appointment
        description = "1st Onetime";
        year = 2019;
        month = 10;
        day = 24;
        correctDate[0] = year;
        correctDate[1] = month;
        correctDate[2] = day;
        appointmentBook.add(new Onetime(description, year, month, day));
        assertEquals("Description should be correct", description, appointmentBook.get(appointmentNum).getDescription());
        assertTrue("Date should be correct" , Arrays.equals(correctDate, appointmentBook.get(appointmentNum).getDate()));
    }

    public void testOccursOn() {
        //Initializes the appointmentBook with appointments
        ArrayList<Appointment> appointmentBook = new ArrayList<Appointment>();
        appointmentBook.add(new Daily("1st Daily", 2019, 10, 24));
        appointmentBook.add(new Monthly("1st Monthly", 2010, 4, 2));
        appointmentBook.add(new Onetime("1st Onetime", 2019, 10, 24));
        int occurYear, occurMonth, occurDay, appointmentNum = 0;

        //Checks to see if occursOn works for daily appointments whether valid or invalid appointment
        occurYear = 2019;
        occurMonth = 10;
        occurDay = 30;
        assertTrue("Must return true since valid daily appointment", appointmentBook.get(appointmentNum).occursOn(occurYear,occurMonth,occurDay));
        occurYear = 2019;
        occurMonth = 10;
        occurDay = 20;
        assertFalse("Must return false since an invalid daily appointment", appointmentBook.get(appointmentNum).occursOn(occurYear,occurMonth,occurDay));
        appointmentNum++;

        //Checks to see if occursOn works for monthly appointments whether valid or invalid appointment
        occurYear = 2020;
        occurMonth = 1;
        occurDay = 2;
        assertTrue("Must return true since valid monthly appointment", appointmentBook.get(appointmentNum).occursOn(occurYear,occurMonth,occurDay));
        occurYear = 2000;
        occurMonth = 4;
        occurDay = 2;
        assertFalse("Must return false since an invalid monthly appointment", appointmentBook.get(appointmentNum).occursOn(occurYear,occurMonth,occurDay));
        appointmentNum++;

        //Checks to see if occursOn works for onetime appointments whether valid or invalid appointment
        occurYear = 2019;
        occurMonth = 10;
        occurDay = 24;
        assertTrue("Must return true since valid onetime appointment", appointmentBook.get(appointmentNum).occursOn(occurYear,occurMonth,occurDay));
        occurYear = 2019;
        occurMonth = 10;
        occurDay = 25;
        assertFalse("Must return false since an invalid onetime appointment", appointmentBook.get(appointmentNum).occursOn(occurYear,occurMonth,occurDay));
    }

    public void testSave() throws IOException {
        //Initializes the appointmentBook with appointments
        ArrayList<Appointment> appointmentBook = new ArrayList<Appointment>();
        appointmentBook.add(new Daily("1st Daily", 2019, 10, 24));
        appointmentBook.add(new Monthly("1st Monthly", 2010, 4, 2));
        appointmentBook.add(new Onetime("1st Onetime", 2019, 10, 24));
        int appointmentNum = 0;

        //Deletes the file if already existent since this is testing based on a file that doesnt exist yet but can also work if it already did
        File appointmentFile = new File("Appointments.txt");
        if(appointmentFile.exists()) {
            appointmentFile.delete();
        }

        //Saves the three appointments initialized into txt file
        appointmentBook.get(appointmentNum).save();
        appointmentNum++;
        appointmentBook.get(appointmentNum).save();
        appointmentNum++;
        appointmentBook.get(appointmentNum).save();

        //Checks if the result txt file is the same as the expected txt file
        byte[] f1 = Files.readAllBytes(Paths.get("AppointmentsExpected.txt"));
        byte[] f2 = Files.readAllBytes(Paths.get("Appointments.txt"));
        assertTrue("The result file should be same as expected file", Arrays.equals(f1, f2));
    }

    public void testLoad() throws IOException {
        //Initializes the appointmentBook with appointments
        ArrayList<Appointment> appointmentBook = new ArrayList<Appointment>();
        appointmentBook.add(new Daily("1st Daily", 2019, 10, 24));
        appointmentBook.add(new Monthly("1st Monthly", 2010, 4, 2));
        appointmentBook.add(new Onetime("1st Onetime", 2019, 10, 24));

        //Initializes as the same as appointment book but with added appointments that are to be loaded to check if methods functions correctly
        ArrayList<Appointment> expectedAppointmentBook = appointmentBook;
        expectedAppointmentBook.add(new Onetime("2nd Onetime", 2019, 3, 2));
        expectedAppointmentBook.add(new Daily("2nd Daily", 2111, 1, 1));
        expectedAppointmentBook.add(new Monthly("2nd Monthly", 2025, 8, 17));
        //Adds the appointments to be loaded into the expectedAppointmentBook
        appointmentBook.add(Appointment.load("2nd Onetime", "AppointmentsLoad.txt"));
        appointmentBook.add(Appointment.load("2nd Daily", "AppointmentsLoad.txt"));
        appointmentBook.add(Appointment.load("2nd Monthly", "AppointmentsLoad.txt"));

        //Checks each appointment one at a time for each book to see if they are the same
        for(int i = 0; i < appointmentBook.size(); i++) {
            assertTrue("The appointment should have loaded correctly", expectedAppointmentBook.get(i).equals(appointmentBook.get(i)));
        }
    }
}