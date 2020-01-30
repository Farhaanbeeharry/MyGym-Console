import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

    public static void main(String[] args) {

        try {

            Socket socket = new Socket("localhost", 8888);

            System.out.println("\n\nWelcome to MyGym ! (Opening Time - 06:00 & Closing Time - 23:00)\n");

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            while (true) {

                System.out.println("Here are some useful commands:");
                System.out.println("- To show all bookings: LISTALL");
                System.out.println("- To show bookings for specific TrainerID: LISTPT <TrainerID>");
                System.out.println("- To show bookings for specific ClientID: LISTCLIENT <ClientID>");
                System.out.println("- To show bookings for specific Date: LISTDAY <YYYY-MM-DD>");
                System.out.println(
                        "- To add new booking: ADD <ClientID> <TrainerID> <SpecialismID> <HH:MM-HH:MM> <YYYY-MM-DD>");
                System.out.println(
                        "- To update existing booking: UPDATE <existingBookingID> <TrainerID> <SpecialismID> <HH:MM-HH:MM> <YYYY-MM-DD>");
                System.out.println("- To delete booking: DELETE <BookingID>");
                System.out.println("- To exit from the console application: QUIT or EXIT");
                System.out.println("COMMAND: ");

                Scanner scanner = new Scanner(System.in);

                String input = scanner.nextLine().toUpperCase();
                String inputString[] = input.split(" ");

                objectOutputStream.writeObject(inputString);
                objectOutputStream.reset();

                if ((inputString[0].equals("EXIT")) || (inputString[0].equals("QUIT"))) {
                    socket.close();
                    System.out.println("\nConsole Client Exit Successful !");
                    System.exit(0);

                } else if (inputString[0].equals("LISTALL")) {
                    ArrayList<String> allBooking = (ArrayList<String>) objectInputStream.readObject();
                    ArrayList<Booking> allBookings = new ArrayList();

                    for (int x = 0; x < allBooking.size(); x++) {
                        String lineText = allBooking.get(x);

                        String booking[] = lineText.split("#");
                        Booking listedBooking = new Booking();

                        listedBooking.setBookingID(booking[0]);
                        listedBooking.setCustomerID(booking[1]);
                        listedBooking.setTrainerID(booking[2]);
                        listedBooking.setSpecialismID(booking[3]);
                        listedBooking.setTimeSlot(booking[4]);
                        listedBooking.setDate(booking[5]);
                        listedBooking.setDuration(Calculations.getDuration(listedBooking.getTimeSlot()));

                        allBookings.add(listedBooking);

                    }

                    Display.displayAllBookings(allBookings);

                } else if (inputString[0].equals("LISTPT")) {

                    ArrayList<Booking> bookingsTrainerID = new ArrayList();

                    if (inputString.length == 1) {
                        System.out.println("Not enough arguments !\n");
                    } else if (inputString.length > 2) {
                        System.out.println("Too many arguments !\n");
                    } else if (inputString[1].length() != 5) {
                        System.out.println("TrainerID can have 5 characters only !\n");
                    } else if (!inputString[1].matches("[a-zA-Z0-9]+")) {
                        System.out.println("TrainerID should be alphanumeric !\n");
                    } else {

                        ArrayList<String> bookingTrainerID = (ArrayList<String>) objectInputStream.readObject();

                        if ((bookingTrainerID.size() > 0) && (bookingTrainerID.get(0).equals("NotFound"))) {
                            System.out.println("TrainerID not found !\n");
                        } else {
                            for (int x = 0; x < bookingTrainerID.size(); x++) {
                                String lineText = bookingTrainerID.get(x);

                                String booking[] = lineText.split("#");
                                Booking listedBooking = new Booking();

                                listedBooking.setBookingID(booking[0]);
                                listedBooking.setCustomerID(booking[1]);
                                listedBooking.setTrainerID(booking[2]);
                                listedBooking.setSpecialismID(booking[3]);
                                listedBooking.setTimeSlot(booking[4]);
                                listedBooking.setDate(booking[5]);
                                listedBooking.setDuration(Calculations.getDuration(listedBooking.getTimeSlot()));

                                bookingsTrainerID.add(listedBooking);

                            }

                            if (bookingsTrainerID.size() != 0) {
                                Display.displayBookingsTrainerID(bookingsTrainerID);
                            } else if (bookingsTrainerID.size() == 0) {
                                System.out.println("No booking for this TrainerID !\n");
                            }
                        }
                    }

                } else if (inputString[0].equals("LISTCLIENT")) {

                    ArrayList<Booking> bookingsClientID = new ArrayList();

                    if (inputString.length == 1) {
                        System.out.println("Not enough arguments !\n");
                    } else if (inputString.length > 2) {
                        System.out.println("Too many arguments !\n");
                    } else if (inputString[1].length() != 5) {
                        System.out.println("ClientID can have 5 characters only !\n");
                    } else if (!inputString[1].matches("[a-zA-Z0-9]+")) {
                        System.out.println("ClientID should be alphanumeric !\n");
                    } else {

                        ArrayList<String> bookingClientiD = (ArrayList<String>) objectInputStream.readObject();

                        if ((bookingClientiD.size() > 0) && (bookingClientiD.get(0).equals("NotFound"))) {
                            System.out.println("ClientID not found !\n");
                        } else {
                            for (int x = 0; x < bookingClientiD.size(); x++) {
                                String lineText = bookingClientiD.get(x);

                                String booking[] = lineText.split("#");
                                Booking listedBooking = new Booking();

                                listedBooking.setBookingID(booking[0]);
                                listedBooking.setCustomerID(booking[1]);
                                listedBooking.setTrainerID(booking[2]);
                                listedBooking.setSpecialismID(booking[3]);
                                listedBooking.setTimeSlot(booking[4]);
                                listedBooking.setDate(booking[5]);
                                listedBooking.setDuration(Calculations.getDuration(listedBooking.getTimeSlot()));

                                bookingsClientID.add(listedBooking);

                            }

                            if (bookingsClientID.size() != 0) {
                                Display.displayBookingsCustomerID(bookingsClientID);
                            } else if (bookingsClientID.size() == 0) {
                                System.out.println("No booking for this ClientID !\n");
                            }
                        }
                    }

                } else if (inputString[0].equals("LISTDAY")) {

                    ArrayList<Booking> bookingsDate = new ArrayList();

                    if (inputString.length == 1) {
                        System.out.println("Not enough arguments !\n");
                    } else if (inputString.length > 2) {
                        System.out.println("Too many arguments !\n");
                    } else if (Calculations.checkValidDate(inputString[1]) != 2) {
                        if (Calculations.checkValidDate(inputString[1]) == 0) {
                            System.out.println("Date cannot be in the past !\n");
                        } else if (Calculations.checkValidDate(inputString[1]) == 1) {
                            System.out.println("Invalid date format (YYYY-MM-DD) !\n");
                        }

                    } else {

                        ArrayList<String> bookingDate = (ArrayList<String>) objectInputStream.readObject();

                        for (int x = 0; x < bookingDate.size(); x++) {
                            String lineText = bookingDate.get(x);

                            String booking[] = lineText.split("#");
                            Booking listedBooking = new Booking();

                            listedBooking.setBookingID(booking[0]);
                            listedBooking.setCustomerID(booking[1]);
                            listedBooking.setTrainerID(booking[2]);
                            listedBooking.setSpecialismID(booking[3]);
                            listedBooking.setTimeSlot(booking[4]);
                            listedBooking.setDate(booking[5]);
                            listedBooking.setDuration(Calculations.getDuration(listedBooking.getTimeSlot()));

                            bookingsDate.add(listedBooking);

                        }

                        if (bookingsDate.size() != 0) {
                            Display.displayBookingsDate(bookingsDate);
                        } else if (bookingsDate.size() == 0) {
                            System.out.println("No booking for this Date !\n");
                        }

                    }

                } else if (inputString[0].equals("DELETE")) {

                    System.out.println(objectInputStream.readObject());

                } else if (inputString[0].equals("ADD")) {

                    System.out.println(objectInputStream.readObject());

                } else if (inputString[0].equals("UPDATE")) {

                    System.out.println(objectInputStream.readObject());

                } else {
                    System.out.println(objectInputStream.readObject());
                }

            }

        } catch (Exception e) {
            System.out.println("Exception caught when trying to listen on port");
            e.printStackTrace();
        }
    }
}
