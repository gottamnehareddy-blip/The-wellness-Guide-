import java.util.*;

// User class
class User {
    String username;
    String password;

    ArrayList<String> hobbies = new ArrayList<>();
    ArrayList<String> reminders = new ArrayList<>();
    ArrayList<String> moodTracker = new ArrayList<>();
    HashMap<String, Boolean> physicalHabits = new HashMap<>();

    User(String username, String password) {
        this.username = username;
        this.password = password;

        // default physical health habits
        physicalHabits.put("Hydration", false);
        physicalHabits.put("Exercise", false);
        physicalHabits.put("Sleep", false);
        physicalHabits.put("Healthy Eating", false);
    }
}

public class WellnessGuide {

    static Scanner sc = new Scanner(System.in);
    static HashMap<String, User> users = new HashMap<>();
    static Queue<String> reminderQueue = new LinkedList<>();
    static User login() {
        System.out.print("Enter username: ");
        String username = sc.next();
        System.out.print("Enter password: ");
        String password = sc.next();
        if (users.containsKey(username) && users.get(username).password.equals(password)) {
            System.out.println("Login successful!");
            return users.get(username);
        }
        System.out.println("Invalid username or password");
        return null;
    }
    static void signup() {
        System.out.print("Enter username: ");
        String username = sc.next();
        if (users.containsKey(username)) {
            System.out.println("User already exists!");
            return;
        }
        System.out.print("Enter password: ");
        String password = sc.next();
        System.out.print("Confirm password: ");
        String confirm = sc.next();
        if (!password.equals(confirm)) {
            System.out.println("Passwords do not match!");
            return;
        }
        users.put(username, new User(username, password));
        System.out.println("Signup successful!");
    }
    static void physicalHealth(User user) {
        System.out.println("Daily Physical Tips:");
        String[] tips = {
                "Drink enough water",
                "Exercise 30 minutes",
                "Sleep 7-8 hours",
                "Eat balanced meals"
        };
        for (String tip : tips) System.out.println("- " + tip);
        System.out.println("\nHabit Tracker:");
        int completed = 0;
        for (String habit : user.physicalHabits.keySet()) {
            System.out.print("Did you complete " + habit + "? (yes/no): ");
            String response = sc.next().toLowerCase();
            boolean done = response.equals("yes");
            user.physicalHabits.put(habit, done);
            if (done) completed++;
        }
        System.out.println("Progress: " + completed + "/" + user.physicalHabits.size() + " habits completed");
        printExtraOptions();
    }
    static void mentalHealth(User user) {
        String[] tips = {
            "Have a phone-free morning",
            "Meditate for 10 minutes",
            "Do something kind"
        };
        System.out.println("Mental Wellness Tips:");
        int completed = 0;
        for (String tip : tips) {
            System.out.print("Did you " + tip + "? (yes/no): ");
            String response = sc.next().toLowerCase();
            boolean done = response.equals("yes");
            if (done) completed++;
        }
        System.out.println("Progress: " + completed + "/" + tips.length + " activities completed");
        System.out.print("How do you feel today?: ");
        String mood = sc.next();
        user.moodTracker.add(mood);
        System.out.println("Mood recorded successfully!");
        printExtraOptions();
    }
    static void manageHobbies(User user) {
        if (user.hobbies.isEmpty()) {
            System.out.println("Your hobbies list is empty.");
            System.out.println("Here are some popular hobbies you might enjoy:");
            System.out.println("- Drawing");
            System.out.println("- Dancing");
            System.out.println("- Singing");
            System.out.println("- Reading");
            System.out.println("- Playing an instrument");
        } else {
            System.out.println("Your hobbies:");
            for (String h : user.hobbies) {
                System.out.println("- " + h);
            }
            int completed = 0;
            for (String h : user.hobbies) {
                System.out.print("Did you do " + h + " today? (yes/no): ");
                String response = sc.next().toLowerCase();
                boolean done = response.equals("yes");
                if (done) completed++;
            }
            System.out.println("Progress: " + completed + "/" + user.hobbies.size() + " hobbies practiced today");
        }
        System.out.println("\nWhat would you like to do?");
        System.out.println("1 Add Hobby");
        System.out.println("2 Search Hobby");
        System.out.println("3 Delete Hobby");
        System.out.println("4 Go Back");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Enter hobby: ");
                sc.nextLine();
                String hobby = sc.nextLine();
                user.hobbies.add(hobby);
                System.out.println("Hobby added!");
                break;
            case 2:
                System.out.print("Enter hobby to search: ");
                sc.nextLine();
                String key = sc.nextLine();
                if (user.hobbies.contains(key))
                    System.out.println("Hobby found!");
                else
                    System.out.println("Hobby not found!");
                break;
            case 3:
                System.out.print("Enter hobby to delete: ");
                sc.nextLine();
                String del = sc.nextLine();
                if (user.hobbies.remove(del))
                    System.out.println("Hobby removed!");
                else
                    System.out.println("That hobby was not in your list.");
                break;
            case 4:
                System.out.println("Returning to main menu...");
                return;
        }
        printExtraOptions();
    }
    static void reminders(User user) {
        System.out.print("Enter reminder: ");
        String reminder = sc.next();
        reminderQueue.add(reminder);
        user.reminders.add(reminder);
        System.out.println("Reminder set!");
    }
    static void searchHobby(User user) {
        System.out.print("Enter hobby to search: ");
        String key = sc.next();
        if (user.hobbies.contains(key)) System.out.println("Hobby found!");
        else System.out.println("Hobby not found!");
    }
    static void sortHobbies(User user) {
        Collections.sort(user.hobbies);
        System.out.println("Sorted hobbies:");
        for (String h : user.hobbies) System.out.println(h);
    }
    static void printExtraOptions() {
        System.out.println("\n4 Set Reminder");
        System.out.println("5 Search Hobby");
        System.out.println("6 Sort Hobbies");
    }
    public static void main(String[] args) {
        User currentUser = null;
        while (true) {
            System.out.println("\n1 Signup");
            System.out.println("2 Login");
            System.out.println("3 Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    signup();
                    break;
                case 2:
                    currentUser = login();
                    if (currentUser != null) {
                        while (true) {
                            System.out.println("\n--- Wellness Guide ---");
                            System.out.println("1 Physical Health");
                            System.out.println("2 Mental Health");
                            System.out.println("3 Hobbies");
                            System.out.println("7 Logout");

                            int option = sc.nextInt();
                            if (option == 7) break;

                            switch (option) {
                                case 1: physicalHealth(currentUser); break;
                                case 2: mentalHealth(currentUser); break;
                                case 3: manageHobbies(currentUser); break;
                                case 4: reminders(currentUser); break;
                                case 5: searchHobby(currentUser); break;
                                case 6: sortHobbies(currentUser); break;
                            }
                        }
                    }
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }
}