
// Import necessary libraries for the program
import java.io.*;
import java.net.MalformedURLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This Methods class is all the methods that I am using becasue I am lazy and
 * having no methods hurts my head.
 * All of these methods are what allows the screen time track to work
 * The methods print the menus, read from the csv file, track your time, write
 * your time into a csv file
 * Prints out all the symptoms and what happens to you if you have an excessive
 * screen time
 * 
 * @author Ethan Ieong
 * @version 1.0
 * @since 2023-04-11
 */

// Start of Methods class
public class Methods {

    // Create a private static Scanner object for user input
    private static Scanner input = new Scanner(System.in);

    /**
     *
     * Displays the main menu of the program and prompts the user for their choice.
     * Menu includes options for tracking screen time, displaying information,
     * learning about side effects of too much screen time, setting break reminders,
     * checking world screen time statistics, and quitting the program.
     * 
     * @return void
     */
    public static void mainMenu() {
        // Code below prints out the main menu and asks for the user's input
        System.out.println("\n");
        System.out.println("================================================================");
        System.out.println("|              Main Menu                                       |");
        System.out.println("================================================================");
        System.out.println("| What would you like to do:                                   |");
        System.out.println("|        1. Track Screen Time                                  |");
        System.out.println("|        2. Display Information                                |");
        System.out.println("|        3. Side Effects of too much screen time               |");
        System.out.println("|        4. Break/Reminder                                     |");
        System.out.println("|        5. World Screen Time                                  |");
        System.out.println("|        6. Quit                                               |");
        System.out.println("================================================================");
        System.out.println("Enter your choice:");
    }

    /**
     * Reads a string slowly by printing each character with a 10 millisecond delay
     * in between.
     * 
     * @param str the string to be read slowly
     * @throws InterruptedException if the thread is interrupted while sleeping
     * @return void
     */
    public static void readSlowly(String str) throws InterruptedException {
        // Loop through each character in the string
        for (int i = 0; i < str.length(); i++) {
            // Print the current character
            System.out.print(str.charAt(i));
            // Pause for 10 milliseconds before outputting the next character
            Thread.sleep(10);
        }
    }

    /**
     * Displays the users weekly or daily screen time
     * 
     * @param choice the user's choice
     */
    public static void usersScreenTime(int choice) {

        // Define a String variable that stores the name of a file
        String fileName = "screenTime.csv";
        // Create a new File object based on the filename
        File file = new File(fileName);

        if (choice == 1) {
            try {
                Scanner inputScanner = new Scanner(file); // Create a Scanner object to read from the file
                int count = 0;
                int sumHours = 0;
                int sumMinutes = 0;

                // Loop while there is more data to read and count is less than 7
                while (inputScanner.hasNext() && count < 7) {

                    // Read the next line from the file
                    String data = inputScanner.nextLine();

                    // Split the line into an array of strings using a comma as a delimiter
                    String[] values = data.split(",");

                    // Add the value of the fourth element in the array to sumHours
                    sumHours += Integer.parseInt(values[3]);

                    // Add the value of the fifth element in the array to sumMinutes
                    sumMinutes += Integer.parseInt(values[4]);
                }
                inputScanner.close(); // Close the Scanner object
                System.out.println("Your weekly screen time is " + sumHours + " hours and " + sumMinutes + " minutes."); // Print
                                                                                                                         // the
                                                                                                                         // sum
                                                                                                                         // of
                                                                                                                         // hours
                                                                                                                         // and
                                                                                                                         // minutes
                                                                                                                         // to
                                                                                                                         // the
                                                                                                                         // console
            } catch (Exception e) { // Catch any exceptions that occur in the try block
                e.printStackTrace(); // Print a stack trace of the exception
            }
        } else if (choice == 2) { // Check if the user's choice is 2
            try { // Begin a try block
                Scanner inputScanner = new Scanner(file); // Create a Scanner object to read from the file
                int todaysDataHours = 0; // Define an integer variable to store today's screen time in hours
                int todaysDataMinutes = 0; // Define an integer variable to store today's screen time in minutes
                int year = LocalDate.now().getYear(); // Get the current year
                DayOfWeek Day = LocalDate.now().getDayOfWeek(); // Get the current day of the week
                Month Month = LocalDate.now().getMonth(); // Get the current month

                while (inputScanner.hasNext()) { // Loop while there is more data to read
                    String data = inputScanner.nextLine(); // Read the next line from the file
                    String[] values = data.split(","); // Split the line into an array of strings using a comma as a
                                                       // delimiter
                    if (values[0].equals(Day) && values[1].equals(Month) && values[2].equals(year)) // Check if the
                                                                                                    // first, second,
                                                                                                    // and third
                                                                                                    // elements of the
                                                                                                    // array match
                                                                                                    // today's date
                        todaysDataHours += Integer.parseInt(values[3]); // Add the value of the fourth element in the
                                                                        // array to todaysDataHours
                    todaysDataMinutes += Integer.parseInt(values[4]); // Add the value of the fifth element in the array
                                                                      // to todaysDataMinutes
                }
                inputScanner.close(); // Close the Scanner object
                System.out.println("Your weekly screen time is " + todaysDataHours + " hours and " + todaysDataMinutes
                        + " minutes."); // Print today's screen time to the console

            }

            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Allows user to track their screen time and end it and the method stores the
     * user's screen time in a csv file
     * 
     * @param counter
     * @param stop
     * @param Break
     * @param track
     * @throws InterruptedException
     */
    // This is a method named ScreenTimeTracker that takes four parameters: counter,
    // stop, Break, and track
    public static void ScreenTimeTracker(int counter, boolean stop, boolean Break, boolean track)
            throws InterruptedException {

        // This variable stores the start time in nanoseconds since the epoch
        long startTime = System.nanoTime();

        // If the counter is 0, Break is false, and track is true, then start tracking
        // screen time
        if (counter == 0 && !Break && track) {

            // Print a message indicating that tracking has started
            System.out.println("\nYou have started tracking your time\n");

            // Get the current time and format it as a string
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            String formattedTime = currentTime.format(formatter);

            // Print the formatted time indicating when screen time tracking started
            System.out.println("Screen time started at: " + formattedTime);

        }

        // If stop is true, counter is greater than 0, and Break is false, then stop
        // tracking screen time
        if (stop && counter > 0 && !Break) {

            // If Break is true, subtract 10 minutes from the elapsed time
            if (Break) {
                // Get the current year, day of the week, and month
                int year = LocalDate.now().getYear();
                DayOfWeek Day = LocalDate.now().getDayOfWeek();
                Month Month = LocalDate.now().getMonth();

                // Get the current time in nanoseconds since the epoch and subtract 10 minutes
                // from it because the user is on a 10 minute break
                long endTime = System.nanoTime();
                long screenTime = (endTime - startTime) - 600000;

                // Calculate the hours and minutes elapsed and print the result
                long hours = screenTime / 3600000000L;
                long minutes = ((screenTime % 3600000000L) / 60000000L);
                System.out.println("Screen Time: " + hours + "h " + minutes + "min");

                // Store the screen time data in a CSV file
                storeDataInCSVFile(Day, Month, year, hours, minutes);
            }

            // If Break is false, stop tracking screen time and calculate the elapsed time
            else {
                // Set the track variable to false to indicate that tracking has stopped
                track = false;

                // Get the current year, day of the week, and month
                int year = LocalDate.now().getYear();
                DayOfWeek Day = LocalDate.now().getDayOfWeek();
                Month Month = LocalDate.now().getMonth();

                // Get the current time in nanoseconds since the epoch and calculate the elapsed
                // time
                long endTime = System.nanoTime();
                long screenTime = endTime - startTime;

                // Calculate the hours and minutes elapsed and print the result
                long hours = screenTime / 3600000000L;
                long minutes = (screenTime % 3600000000L) / 60000000L;
                System.out.println("Screen Time: " + hours + "h " + minutes + "min");

                // Store the screen time data in a CSV file
                storeDataInCSVFile(Day, Month, year, hours, minutes);
            }
        }
    }

    /**
     * Store's the user's screen time in a csv file
     * 
     * @param day
     * @param month
     * @param year
     * @param hours
     * @param minutes
     */
    public static void storeDataInCSVFile(DayOfWeek day, Month month, int year, long hours, long minutes) {

        // Try statement
        try {
            // Declare file writer and allow the file to add more data
            FileWriter storeData = new FileWriter(new File("screenTime.csv"), true);
            // Add scanner object to read the file
            Scanner fileInput = new Scanner(new File("screenTime.csv"));
            // Use delimeter to tell program that data is seperated by comma
            fileInput.useDelimiter(",");
            // Write to the file by putting in it's day month year hours and minutes
            storeData.write("\n" + day + "," + month + "," + year + "," + hours + "," + minutes + ",");
            // Close scanner object
            storeData.close();

            // If error catch it and print it's stack trace
        } catch (Exception E) {
            E.printStackTrace();
        }

    }

    /**
     * Displays the different menus available to the user, based on the menu
     * parameter.
     * 
     * @param menu a string representing the menu that the computer will display
     */
    public static void otherMenus(String menu) {
        // Prints out a menu depending on the given argument
        if (menu.equals("Screen Time")) { // Check if the menu argument equals "Screen Time"
            System.out.println("What would you like to know?");
            System.out.println("1. Weekly Screen Time");
            System.out.println("2. Today's Screen Time");
        }

        if (menu.equals("Symptoms")) { // Check if the menu argument equals "Symptoms"
            System.out.println(
                    "Spending too much time in front of a screen can lead to various symptoms and health issues");
            System.out.println("Enter a number to learn more info about each symptom:");
            System.out.println("1.Emotional Skills and Emotional Development");
            System.out.println("2.Issues with Problem Solving Skills");
            System.out.println("3.Weight");
            System.out.println("4.Physical Health");
        }

        if (menu.equals("breakOrReminder")) { // Check if the menu argument equals "breakOrReminder"
            System.out.println("Would you like to set a remidner or start a break");
            System.out.println("1. Start a break");
            System.out.println("2. Set a reminder");
        }

        if (menu.equals("worldScreenTime")) { // Check if the menu argument equals "worldScreenTime"
            System.out.println("What would you like to know");
            System.out.println("1. The average screen time of the world"); //
            System.out.println("2. A specific countries average screen time");
        }
    }

    /**
     * Displays the informtion of the symptom that the user has chosen
     * 
     * @param symptomChoice the number that the user enterred to learn more about
     *                      what symptom is with the number
     * @return
     */
    // Displays the information of the symptom that the user chose
    public static String[] symptoms(int symptomChoice) {
        try {
            // Open a scanner to read from the text file "textFile.txt"
            Scanner symptomFile = new Scanner(new File("textFile.txt"));
            // Create an array that holds 13 indivisual symptoms
            String[] symptomList = new String[13];
            // Create a varaible called index
            int index = 0;
            // Create an array that holds 3 lines from the file
            String[] indivisualSymptom = new String[3];
            // If file has a next line
            while (symptomFile.hasNext()) {
                // Hold the line from the file into the variable output
                String output = symptomFile.nextLine();
                // Add the line to the symptom list
                symptomList[index] = output;
                // Add 1 to the index value
                index++;
            }
            // Close scanner object
            symptomFile.close();
            // Switch statement based on the number that the user entered
            switch (symptomChoice) {
                // If symptomChoice = 1
                case 1:
                    // Print out the message
                    System.out.println("\n\nEmotional Skills and Emotional Development:\n");
                    // Copy the first 3 symptoms from the symptom list array to the individual
                    // symptom array
                    for (int i = 0; i < 3; i++) {
                        indivisualSymptom[i] = symptomList[i];
                    }
                    break;
                // If symptomChoice is 2
                case 2:
                    System.out.println("\n\nIssues with Problem Solving Skills:\n");
                    // Copy the next 3 symptoms from the symptom list array to the individual
                    // symptom array
                    for (int i = 3; i < 6; i++) {
                        indivisualSymptom[i - 3] = symptomList[i];
                    }
                    break;
                // If symptomChoice is 3
                case 3:
                    System.out.println("\n\nWeight:\n");
                    // Copy the next 3 symptoms from the symptom list array to the individual
                    // symptom array
                    for (int i = 6; i < 9; i++) {
                        indivisualSymptom[i - 6] = symptomList[i];
                    }
                    break;
                // If symptomChoice is 4
                case 4:
                    System.out.println("\n\nPhysical Health:\n");
                    // Copy the final 3 symptoms from the symptom list array to the individual
                    // symptom array
                    for (int i = 9; i < 12; i++) {
                        indivisualSymptom[i - 9] = symptomList[i];
                    }
                    break;

            }
            // Return the individual symptom array
            return indivisualSymptom;
            // If IOException print out the printStackTrace and return null
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 
     * Displays the options for setting a reminder or starting a break and executes
     * the corresponding action based on user input.
     * Makes a window telling the user to get off their screen if there is mouse
     * movement
     * Unable to exit screen unless pressing the button
     * 
     * @param counter an integer representing the screen time counter
     * @throws InterruptedException  if a thread is interrupted while sleeping
     * @throws MalformedURLException if a URL is not formatted correctly
     */
    // Set reminder for the user
    public static void setReminder(int counter) throws InterruptedException {
        // Display menu to user
        otherMenus("breakOrReminder");
        // Get user input for break or reminder
        int breakOrReminder = input.nextInt();
        boolean run = true;
        // Create an array with one boolean value set to true
        boolean[] buttonClicked = { true };
        // If user chose to take a break
        if (breakOrReminder == 1) {
            // Get the current mouse position
            Point previousMousePosition = MouseInfo.getPointerInfo().getLocation();
            // Display message to user
            System.out.println("You have started your 10 minute break");
            // Get the current time
            LocalTime currentTime = LocalTime.now();
            // Get the current minute
            int minuteTime = currentTime.getMinute();
            // Call a method to track screen time
            ScreenTimeTracker(counter, true, true, false); // Call a function to track screen time
            // Get default toolkit
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            // Get the screen size
            Dimension screenSize = toolkit.getScreenSize();
            // Create a new JFrame with a message
            JFrame window = new JFrame("GET OFF TAKE A BREAK FROM THE SCREEN");
            // Set the size of the window
            window.setSize(screenSize.width, screenSize.height);
            // Disable the close button so when you press the X button the window will not
            // close
            window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            // Display the window
            window.setVisible(true); // Display the window
            // While run is true and the button is clicked
            while (run && buttonClicked[0]) {
                // Get the current mouse position
                Point currentMousePosition = MouseInfo.getPointerInfo().getLocation(); // Get the current mouse position
                // Get the current minute
                int presentTime = currentTime.getMinute();
                // If 10 minutes have passed
                if (minuteTime - presentTime == 10 || minuteTime - presentTime == -10) {
                    System.out.println("Break is over"); // Display message to user
                    // The window closes
                    window.dispose();
                    // Set the flag to false and get out of the while loop
                    run = false;
                    // Wait for 3 seconds
                    Thread.sleep(3000);
                    // Exit the loop
                    break;
                }
                // If the user moved their mouse
                if (!currentMousePosition.equals(previousMousePosition)) {
                    // Calculate x coordinate for message
                    int x = (int) (screenSize.getWidth() / 2) - 50;
                    // Calculate y coordinate for message
                    int y = (int) (screenSize.getHeight() / 2) - 10;
                    // Display the window
                    window.setVisible(true);
                    // Create a button with a message
                    JButton button = new JButton("If busy click here");
                    // Check if button is clicked
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Hide the window
                            window.setVisible(false);
                            // Dispose of the window
                            window.dispose(); // Dispose of the window
                            // Set the button clicked flag to false
                            buttonClicked[0] = false;
                        }
                    });
                    // Create a new panel
                    JPanel panel = new JPanel() {
                        // Override the paintComponent method
                        @Override
                        protected void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            // Set the color to red
                            g.setColor(Color.BLACK);
                            // Set font of the message to Arial Bold, size 20
                            g.setFont(new Font("Arial", Font.BOLD, 20));
                            // Draw the message on the panel
                            g.drawString("GET OFF TAKE A BREAK FROM THE SCREEN", x, y);
                        }
                    };
                    // Set the button size and position
                    button.setBounds(50, 50, 100, 30);
                    // Add the button to the panel
                    panel.add(button); // Add the button to the panel
                    // Add the panel to the window
                    window.add(panel);
                    // Make the window always on top
                    window.setAlwaysOnTop(true);
                    // Make the window visibile
                    window.setVisible(true);
                }

            }

        }

        else if (breakOrReminder == 2) {
            // Ask the user when they wanted to be reminded
            System.out.println("When do you want to be reminded: (Enter in minutes) ");
            // Allow user to enter the number of minutes until they want to be reminded
            int reminder = input.nextInt();
            int time = reminder;
            // Start the recursive function
            System.out.println(recursiveFunction(reminder, time));

        }

    }

    /**
     * 
     * A recursive function that sets a reminder after a specified number of
     * minutes.
     * 
     * @param reminder The number of minutes until the reminder should be displayed.
     * @return A message indicating that it is time to take a break.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    // Recursive function for the reminder
    public static String recursiveFunction(int reminder, int time) throws InterruptedException {
        // If reminder variable is 0, then tell the user
        if (reminder == 0)
            return "It has been " + time + " minutes take a break";
        else {
            // Stop the program for a minute
            Thread.sleep(60000);
            // Recall the function and subtract the time by one minute
            return recursiveFunction(reminder - 1, time);
        }

    }

    /**
     * Reads data from a CSV file and returns a 2D array of strings containing the
     * data.
     * 
     * @return a 2D array of strings representing the data in the CSV file
     * @throws IOException if there is an error reading the file
     */

    // Read from the CSV file and store the values into a 2D array
    public static String[][] readFromCSVFile() throws IOException {
        // Specify the file name and path
        String textfile = "text.txt";

        // Create a scanner to read the file
        Scanner scanner = new Scanner(new File(textfile));

        // Initialize variables to keep track of rows and columns
        int rows = 0;
        int cols = 0;

        // Loop through the file to count the number of rows and columns
        while (scanner.hasNextLine()) {
            // Split each line into an array of values
            String[] values = scanner.nextLine().split(",");

            // Update the number of columns if necessary
            cols = Math.max(cols, values.length);

            // Increment the row count
            rows++;
        }

        // Close the scanner
        scanner.close();

        // Create a 2D array with the appropriate dimensions
        String[][] data = new String[rows][cols];

        // Reopen the scanner to read the file again
        scanner = new Scanner(new File(textfile));

        // Reset the row count
        rows = 0;

        // Loop through the file again to fill the 2D array
        while (scanner.hasNextLine()) {
            // Split each line into an array of values
            String[] values = scanner.nextLine().split(",");

            // Fill each column of the current row with the corresponding value
            for (int col = 0; col < values.length; col++) {
                data[rows][col] = values[col].toString();
            }

            // Increment the row count
            rows++;
        }

        // Close the scanner
        scanner.close();

        // Return the 2D array of data
        return data;
    }

    /**
     * 
     * This method takes a choice parameter and a two-dimensional string array
     * averageWorldData. If choice is 1, it calculates
     * 
     * the average screen time usage of the world from the given data and prints it
     * to the console. If choice is 2, it prompts the user
     *
     * to enter a country name and displays that country's screen time average from
     * the given data.
     * 
     * @param choice           an integer value to indicate the type of operation to
     *                         perform, 1 for world average, 2 for a specific
     *                         country's average
     * 
     * @param averageWorldData a two-dimensional string array containing screen time
     *                         data for different countries
     */
    public static void worldDataScreenTime(int choice, String[][] averageWorldData) {
        // If choice is 1, calculate and display the world's average screen time usage
        if (choice == 1) {
            int averageHours = 0;
            int averageMinutes = 0;
            // Loop through each row of the array (excluding the first row which contains
            // headers)
            for (int r = 1; r < averageWorldData.length; r++) {
                // Check if both hours and minutes data are available for the current country
                if (averageWorldData[r][2] != null && !averageWorldData[r][2].isEmpty() &&
                        averageWorldData[r][1] != null && !averageWorldData[r][1].isEmpty()) {
                    // Split the hours and minutes data, convert to integers and add to the average
                    // values
                    String[] specificHour = (averageWorldData[r][1]).split("");
                    String[] specificMinute = (averageWorldData[r][2]).split("");
                    averageHours += Integer.parseInt(specificHour[0]);
                    averageMinutes += Integer.parseInt(specificMinute[0]);
                }
            }
            // Display the world's average screen time usage in hours and minutes
            System.out.printf("The world's average screen time usage is %d hours and %d minutes",
                    (averageHours / (averageWorldData.length - 1)), (averageMinutes / (averageWorldData.length - 1)));

        }

        // If choice is 2, display a specific country's screen time average based on
        // user input
        if (choice == 2) {
            String countryName = "";
            // Display the list of available countries
            System.out.println("Which country's screen time average would you like to see?");
            for (int r = 0; r < averageWorldData.length; r++) {
                System.out.println(averageWorldData[r][0]);
            }
            // Get user input for the country name
            System.out.println("\nEnter the country:");
            String countryScreenTime = input.nextLine().toLowerCase();
            // Format the country name correctly
            if (countryScreenTime.contains(" ")) {
                countryName = Character.toString(countryScreenTime.charAt(0)).toUpperCase()
                        + countryScreenTime.substring(1, countryScreenTime.indexOf(" "))
                        + Character.toString(countryScreenTime.charAt(countryScreenTime.indexOf(" ") + 1))
                        + countryScreenTime.substring(countryScreenTime.indexOf(" ") + 2).trim();
            } else {
                countryName = Character.toString(countryScreenTime.charAt(0)).toUpperCase()
                        + countryScreenTime.substring(1).trim();
            }
            // Use linear search to find the index of the requested country in the array
            int countryIndex = linearSearchAlgorithm(countryName, averageWorldData);
            // If the country is found, display its screen time average
            if (countryIndex != -1) {
                System.out.printf("%s's screen time average is %s and %s.", countryName,
                        averageWorldData[countryIndex][1], averageWorldData[countryIndex][2]);
            }
            // If the country is not found, display an error message and prompt the user to
            // enter another country
            else if (countryIndex == -1) {
                System.out.println("Country's data is not available please enter another country");
            }
        }
    }

    /**
     * 
     * This method performs linear search algorithm on a two-dimensional array to
     * search for a specific country's data.
     * 
     * @param countryName the name of the country to search for
     * @param countryData the two-dimensional array containing the data to search
     *                    through
     * @return the index of the row containing the country's data, or -1 if the
     *         country is not found in the array
     */
    //User linear search algorithm to search through the csv file and return a value if the data is found or not
    public static int linearSearchAlgorithm(String countryName, String[][] countryData) {
        int row = 0;
        //If the if the file has more data
        while (row < countryData.length) {
            //If the data is found in the file that is equal to the country that the user inputted return the row number
            if (countryData[row][0].equals(countryName)) {
                return row;
            }
            row++;
        }
        //If not found return -1
        return -1;
    }
}
