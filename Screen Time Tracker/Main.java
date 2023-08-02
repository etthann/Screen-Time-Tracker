//Import all the ncessary libaries needed for this program
import java.io.IOException;
import java.util.Scanner;

/**
 * Code for a screen time tracker
 * Main class of the program
 * The class that runs all the code for the screen time tracker
 * 
 * @author Ethan Ieong
 * @version 1.0
 * @since 2023-04-11
 */

public class Main {
    private static Scanner input = new Scanner(System.in);
    private static int counter = 0;

    /**
     * The main method of the program.
     * Allows users to track their screen time, get informed about the symptoms of
     * having too much screen time,
     * and set reminders to take a break.
     *
     * @param args the command line arguments
     * @throws InterruptedException if the thread is interrupted while sleeping
     * @throws IOException          if an I/O error occurs
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        // Initialize a boolean variable to control the main loop
        boolean exit = false;
    
        // Display a welcome message to the user
        Methods.readSlowly("This program allows users to track their screen time, get informed about the symptoms of having too much screen time and allow them to set reminders to take a break\n");
    
        // Main program loop
        while (!exit) {
            // Wait for 2 seconds before displaying the main menu again
            Thread.sleep(2000);
    
            // Display the main menu options to the user
            Methods.mainMenu();
    
            // Read the user's choice from the console
            int choice = input.nextInt();
    
            // Handle the user's choice using a switch statement
            switch (choice) {
                case 1:
                    // Call the ScreenTimeTracker method with appropriate arguments
                    Methods.ScreenTimeTracker(counter, false, false, true);
    
                    // Ask the user if they want to continue tracking their screen time
                    System.out.print("Would you like to stop tracking your screen time?");
                    System.out.println("[Y/N]");
    
                    // Read the user's response from the console
                    input.nextLine();
                    String continueScreenTime = input.nextLine();
    
                    // If the user wants to continue tracking their screen time, call the ScreenTimeTracker method again with different arguments
                    if (continueScreenTime.equals("Y")) {
                        Methods.ScreenTimeTracker(counter, true, false, false);
                    }
    
                    // Increment the counter variable
                    counter += 1;
    
                    break;
    
                case 2:
                    // Display the Screen Time submenu and handle the user's choice using another loop
                    while (true) {
                        //Print out the screen time menu from the otherMenus method from the Methods class
                        Methods.otherMenus("Screen Time");
                        //Get the user's input
                        choice = input.nextInt();
                        //Call the usersScreenTime method
                        Methods.usersScreenTime(choice);
                        break;
                    }
    
                    break;
    
                case 3:
                    // Display the Symptoms submenu and handle the user's choice using another loop
                    while (true) {
                        //Print out the symptom menu from the otherMenus method from the Methods class
                        Methods.otherMenus("Symptoms");
                        //Get the user's input
                        int symptomChoice = input.nextInt();
                        //Store the 1D array value that the symptoms method returns into the variable symptomInfo
                        String[] symptomInfo = Methods.symptoms(symptomChoice);
    
                        // Loop through the symptom information and display it to the user
                        for (int i = 0; i < symptomInfo.length; i++) {
                            Methods.readSlowly(symptomInfo[i] + "\n\n");
                        }
    
                        break;
                    }
                    //Break out of the switch statement preventing it from going to the other cases
                    break;
    
                case 4:
                    // Call the setReminder method
                    Methods.setReminder(counter);
                    //stop the program from running the other cases
                    break;
    
                case 5:
                    // Display the World Screen Time submenu and call the worldDataScreenTime method with appropriate arguments
                    Methods.otherMenus("worldScreenTime");
                    //Get the user's input
                    int typeOfData = input.nextInt();
                    //Call the worldDataScreenTime method from the Methods class
                    Methods.worldDataScreenTime(typeOfData, Methods.readFromCSVFile());
    
                    break;
    
                case 6:
                    // Display a goodbye message and exit the loop
                    System.out.println("Thank you for using the program!");
                    //set the variable exit to true to break out of the while loop
                    exit = true;
                    //Break out of the switch statement
                    break;
            }
        }
    }
}
