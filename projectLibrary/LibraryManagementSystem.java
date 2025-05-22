import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class LibraryManagementSystem {
    static Scanner sc = new Scanner(System.in);

    // Hardcoded credentials
    static final String USERNAME = "admin";
    static final String PASSWORD = "admin123";

    // Book storage
    static ArrayList<Book> books = new ArrayList<>();
     static ArrayList<Member> members = new ArrayList<>();
      

    public static void main(String[] args) {
        if (login()) {
            showMainMenu();
        }
    }

static class Member {
    String memberId;
    String name;
    String contactNumber;
    String email;

    Member(String memberId, String name, String contactNumber, String email) {
        this.memberId = memberId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
    }
      public String getMemberId() {
        return memberId;
    }
    public String getName() {
        return name;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public String getEmail() {
        return email;
    }
}
    // Book class
    static class Book {
        String id;
        String title;
        String author;
        String genre;
        int quantity;

        Book(String id, String title, String author, String genre, int quantity) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.quantity = quantity;
        }

        void displayDetails() {
            System.out.println("Book ID   : " + id);
            System.out.println("Title     : " + title);
            System.out.println("Author    : " + author);
            System.out.println("Genre     : " + genre);
            System.out.println("Quantity  : " + quantity);
        }
    }

    // Login function
    public static boolean login() {
        while (true) {
            System.out.print("Enter Username: ");
            String username = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            if (username.equals(USERNAME) && password.equals(PASSWORD)) {
                System.out.println("\nLogin successful!\n");
                return true;
            } else {
                System.out.println("Invalid credentials. Try again.\n");
            }
        }
    }

    // Main Menu function
    public static void showMainMenu() {
        int option;
        do {
            System.out.println("--- Library Management System ---");
            System.out.println("1. Manage Books");
            System.out.println("2. Manage Members");
            System.out.println("3. Issue Books");
            System.out.println("4. Return Books");
            System.out.println("5. View Reports");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            option = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (option) {
                case 1:
                    manageBooksMenu();
                    break;
                case 2:
                    manageMembersMenu();
                    break;
                case 3:
                    issueBooksMenu();
                    break;
                case 4:
                    returnBooksMenu();
                    break;
                case 5:
                    viewReportsMenu();
                    break;
                case 6:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.\n");
            }
        } while (option != 6);
    }

    // Manage Books Menu
    public static void manageBooksMenu() {
        int choice;
        do {
            System.out.println("\n--- Manage Books ---");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Search Book");
            System.out.println("5. View All Books");
            System.out.println("6. Back to Main Menu");
            System.out.print("Select an option: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                     addBook();
                    break;
                case 2:
                    updateBook();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    searchBook();
                    break;
                case 5:
                    viewAllBooks();
                    break;
                case 6:
                    System.out.println("Returning to Main Menu...\n");
                    break;
                default:
                    System.out.println("Invalid option. Try again.\n");
            }
        } while (choice != 6);
    }
    public static void addBook() {
    System.out.println("\n--- Add New Book ---");

    String id;
    while (true) {
        System.out.print("Enter Book ID: ");
        id = sc.nextLine();
        boolean exists = false;
        for (Book book : books) {
            if (book.id.equalsIgnoreCase(id)) {
                exists = true;
                break;
            }
        }
        if (exists) {
            System.out.println("Book ID already exists. Please enter a unique Book ID.\n");
        } else {
            break;
        }
    }

    System.out.print("Enter Title: ");
    String title = sc.nextLine();

    System.out.print("Enter Author: ");
    String author = sc.nextLine();

    System.out.print("Enter Genre: ");
    String genre = sc.nextLine();

    int quantity;
    while (true) {
        System.out.print("Enter Quantity: ");
        if (sc.hasNextInt()) {
            quantity = sc.nextInt();
            sc.nextLine(); // consume newline
            if (quantity >= 0) break;
        } else {
            sc.nextLine(); // consume invalid input
        }
        System.out.println("Quantity must be a positive integer.");
    }

    // Add to list
    books.add(new Book(id, title, author, genre, quantity));
    System.out.println("\nBook added successfully!\n");
}

    // Update Book function
    public static void updateBook() {
        System.out.print("Enter Book ID to update: ");
        String bookId = sc.nextLine();

        Book bookToUpdate = null;
        for (Book book : books) {
            if (book.id.equalsIgnoreCase(bookId)) {
                bookToUpdate = book;
                break;
            }
        }

        if (bookToUpdate == null) {
            System.out.println("Book not found. Returning to menu...\n");
            return;
        }

        System.out.println("\nCurrent Book Details:");
        bookToUpdate.displayDetails();

        System.out.print("\nEnter new Title: ");
        String newTitle = sc.nextLine();

        System.out.print("Enter new Author: ");
        String newAuthor = sc.nextLine();

        System.out.print("Enter new Genre: ");
        String newGenre = sc.nextLine();

        int newQuantity;
        while (true) {
            System.out.print("Enter new Quantity: ");
            newQuantity = sc.nextInt();
            sc.nextLine(); // consume newline
            if (newQuantity >= 0) break;
            System.out.println("Quantity must be a positive integer.");
        }

        bookToUpdate.title = newTitle;
        bookToUpdate.author = newAuthor;
        bookToUpdate.genre = newGenre;
        bookToUpdate.quantity = newQuantity;

        System.out.println("\nBook updated successfully!\n");
    }
    public static void deleteBook(){
        
          System.out.println("\n--- Delete Book ---");
                System.out.print("Enter Book ID to delete: ");
                String deleteId = sc.nextLine();
                boolean found = false;

                for (int i = 0; i < books.size(); i++) {
                    if (books.get(i).id.equalsIgnoreCase(deleteId)) {
                        books.remove(i);
                        System.out.println("Book deleted successfully!");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Book ID not found. No book was deleted.");
                }
               
        
    }
    public static void searchBook(){
        
         System.out.println("\n--- Search Book ---");
    System.out.print("Enter Book ID to search: ");
    String searchId = sc.nextLine();
    boolean bookFound = false;

    for (Book b : books) {
        if (b.id.equalsIgnoreCase(searchId)) {
            System.out.println("Book found:");
            System.out.println("Book ID: " + b.id);
            System.out.println("Title: " + b.title);
            System.out.println("Author: " + b.author);
            System.out.println("Genre: " + b.genre);
            System.out.println("Quantity: " + b.quantity);
            bookFound = true;
            break;
        }
    }

    if (!bookFound) {
        System.out.println("Book ID not found in the system.");
    }
    
        
    }
    
    public static void viewAllBooks() {
    System.out.println("\n--- View All Books ---");

    if (books.isEmpty()) {
        System.out.println("The catalog is currently empty.\n");
        return;
    }

    System.out.printf("%-10s %-30s %-20s %-15s %-10s\n", 
                      "Book ID", "Title", "Author", "Genre", "Quantity");
    System.out.println("---------------------------------------------------------------------------");

    for (Book book : books) {
        System.out.printf("%-10s %-30s %-20s %-15s %-10d\n", 
                          book.id, book.title, book.author, book.genre, book.quantity);
    }

    System.out.println(); // for spacing
}


    // Manage Members Menu
    public static void manageMembersMenu() {
        int choice;
        do {
            System.out.println("\n--- Manage Members ---");
            System.out.println("1. Add Member");
            System.out.println("2. Update Member");
            System.out.println("3. Delete Member");
            System.out.println("4. Search Member");
            System.out.println("5. View All Members");
            System.out.println("6. Back to Main Menu");
            System.out.print("Select an option: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                   addMember();
                    break;
                case 2:
                    updateMember();
                    break;
                case 3:
                    deleteMember();
                    break;
                case 4:
                    searchMember();
                    break;
                case 5:
                    viewAllMembers();
                    break;
                case 6:
                    System.out.println("Returning to Main Menu...\n");
                    break;
                default:
                    System.out.println("Invalid option. Try again.\n");
            }
        } while (choice != 6);
    }

    // Issue Books Menu
    public static void issueBooksMenu() {
        int choice;
        do {
            System.out.println("\n--- Issue Books ---");
            System.out.println("1. Issue a Book");
            
            System.out.println("2. Back to Main Menu");
            System.out.print("Select an option: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    issueBook();
                    break;
                
                case 2:
                    System.out.println("Returning to Main Menu...\n");
                    break;
                default:
                    System.out.println("Invalid option. Try again.\n");
            }
        } while (choice != 2);
    }

    // Return Books Menu
   private static void returnBooksMenu() {
    System.out.println("\n--- Return Books ---");
    returnBook();  // <-- Call the method here
}
    
    public static void viewReportsMenu() {
    int choice;
    do {
        System.out.println("\n--- View Reports ---");
        System.out.println("1. Overdue Books");
        System.out.println("2. Books Issued Per Member");
        System.out.println("3. Back to Main Menu");
        System.out.print("Select an option: ");
        choice = sc.nextInt();
       // consume newline

        switch (choice) {
            case 1:
                viewOverdueBooks();
                break;
            case 2:
                viewBooksIssuedPerMember();
                break;
            case 3:
                System.out.println("Returning to Main Menu...\n");
                break;
            default:
                System.out.println("Invalid option. Try again.\n");
        }

    } while (choice != 3);
}

static ArrayList<IssuedBook> issuedBooks = new ArrayList<>();

static class IssuedBook {
    String bookId;
    String memberId;
    LocalDate dueDate;

    public IssuedBook(String bookId, String memberId, LocalDate dueDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.dueDate = dueDate;
    }
}



    public static void viewOverdueBooks() {
        System.out.println("\n--- Overdue Books Report ---");

        LocalDate currentDate = LocalDate.now();  // Step 2: current date
        double fineRate = 0.50;                   // Step 3: 0.50 LKR per day

        boolean hasOverdue = false;

        System.out.printf("%-10s %-12s %-12s %-15s %-10s\n", 
                          "Book ID", "Member ID", "Due Date", "Days Overdue", "Fine (LKR)");
        System.out.println("-----------------------------------------------------------------------");

        for (IssuedBook book : issuedBooks) {
            if (currentDate.isAfter(book.dueDate)) {
                long daysOverdue = ChronoUnit.DAYS.between(book.dueDate, currentDate);
                double fine = daysOverdue * fineRate;

                System.out.printf("%-10s %-12s %-12s %-15d %.2f\n",
                    book.bookId, book.memberId, book.dueDate, daysOverdue, fine);

                hasOverdue = true;
            }
        }

        if (!hasOverdue) {
            System.out.println("No overdue books found.");
        } else {
            System.out.println();  // For spacing
        }
    }
public static void viewBooksIssuedPerMember() {
    if (issuedBooks.isEmpty()) {
        System.out.println("No books have been issued.");
        return;
    }

    Map<String, Integer> memberBookCount = new HashMap<>();

    for (IssuedBook issuedBook : issuedBooks) {
        memberBookCount.put(
            issuedBook.memberId,
            memberBookCount.getOrDefault(issuedBook.memberId, 0) + 1
        );
    }

    System.out.println("Books Issued Per Member:");
    System.out.println("--------------------------");
    System.out.printf("%-15s %-20s%n", "Member ID", "Total Books Issued");
    System.out.println("----------------------------------------");

    for (Map.Entry<String, Integer> entry : memberBookCount.entrySet()) {
        System.out.printf("%-15s %-20d%n", entry.getKey(), entry.getValue());
    }
}



 

public static void addMember() {
        System.out.print("Enter Member ID: ");
        String memberId = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Contact Number: ");
        String contactNumber = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        members.add(new Member(memberId, name, contactNumber, email));
        System.out.println("\nMember added successfully!");
    }
    public static void updateMember() {
    System.out.print("Enter Member ID to update: ");
    String memberId = sc.nextLine();

    Member foundMember = null;
    for (Member member : members) {
        if (member.memberId.equalsIgnoreCase(memberId)) {
            foundMember = member;
            break;
        }
    }

    if (foundMember == null) {
        System.out.println("Member ID not found. Returning to menu.\n");
        return;
    }

    System.out.println("\n--- Current Member Details ---");
    System.out.println("Name: " + foundMember.name);
    System.out.println("Contact Number: " + foundMember.contactNumber);
    System.out.println("Email: " + foundMember.email);

    System.out.print("\nEnter new Name: ");
    String newName = sc.nextLine();

    System.out.print("Enter new Contact Number: ");
    String newContactNumber = sc.nextLine();

    System.out.print("Enter new Email: ");
    String newEmail = sc.nextLine();

    // Update member details
    foundMember.name = newName;
    foundMember.contactNumber = newContactNumber;
    foundMember.email = newEmail;

    System.out.println("\nMember details updated successfully!\n");
}
public static void deleteMember() {
    System.out.print("Enter Member ID to delete: ");
    String memberId = sc.nextLine();

    Member foundMember = null;
    for (Member member : members) {
        if (member.memberId.equalsIgnoreCase(memberId)) {
            foundMember = member;
            break;
        }
    }

    if (foundMember == null) {
        System.out.println("Member ID not found. Returning to menu.\n");
        return;
    }

    // Remove member from the list
    members.remove(foundMember);
    System.out.println("\nMember deleted successfully!\n");
}
private static void searchMember() {
        System.out.print("Enter Member ID to search: ");
        String memberId = sc.nextLine().trim();

        boolean found = false;
        for (Member member : members) {
            if (member.getMemberId().equalsIgnoreCase(memberId)) {
                System.out.println("Member Found:");
                System.out.println("ID: " + member.getMemberId());
                System.out.println("Name: " + member.getName());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Warning: Member with ID '" + memberId + "' does not exist.");
        }
    }
    private static void viewAllMembers() {
    if (members.isEmpty()) {
        System.out.println("No member records available.");
        return;
    }

    System.out.printf("%-10s %-20s %-15s %-25s\n", "Member ID", "Name", "Contact No.", "Email");
    System.out.println("----------------------------------------------------------------------");

    for (Member member : members) {
        System.out.printf("%-10s %-20s %-15s %-25s\n",
                member.getMemberId(),
                member.getName(),
                member.getContactNumber(),
                member.getEmail());
    }
}
    private static void issueBook() {
        System.out.print("Enter Book ID: ");
    String bookId = sc.nextLine();

    Book selectedBook = null;
    for (Book b : books) {
        if (b.id.equalsIgnoreCase(bookId)) {
            selectedBook = b;
            break;
        }
    }

    if (selectedBook == null) {
        System.out.println("❌ Book not found.");
        return;
    }

    if (selectedBook.quantity <= 0) {
        System.out.println("❌ Book is currently not available.");
        return;
    }
    
    System.out.print("Enter Member ID: ");
    String memberId = sc.nextLine();

    Member selectedMember = null;
    for (Member m : members) {
        if (m.memberId.equalsIgnoreCase(memberId)) {
            selectedMember = m;
            break;
        }
    }

    if (selectedMember == null) {
        System.out.println("❌ Member not found.");
        return;
    }

    

    System.out.print("Enter Due Date (YYYY-MM-DD): ");
    String dueDateInput = sc.nextLine();
    LocalDate dueDate;
    try {
        dueDate = LocalDate.parse(dueDateInput);
    } catch (Exception e) {
        System.out.println("❌ Invalid date format.");
        return;
    }

    // Create and record the issued book
    issuedBooks.add(new IssuedBook(bookId, memberId, dueDate));
    selectedBook.quantity--;

    System.out.println("✅ Book issued successfully! Due Date: " + dueDate);
}
private static void returnBook() {
    System.out.print("Enter Member ID: ");
    String memberId = sc.nextLine();

    Member member = null;
    for (Member m : members) {
        if (m.memberId.equalsIgnoreCase(memberId)) {
            member = m;
            break;
        }
    }

    if (member == null) {
        System.out.println("❌ Invalid Member ID.");
        return;
    }

    System.out.print("Enter Book ID: ");
    String bookId = sc.nextLine();

    IssuedBook foundIssued = null;
    for (IssuedBook ib : issuedBooks) {
        if (ib.bookId.equalsIgnoreCase(bookId) && ib.memberId.equalsIgnoreCase(memberId)) {
            foundIssued = ib;
            break;
        }
    }

    if (foundIssued == null) {
        System.out.println("❌ No record found for the given Member ID and Book ID.");
        return;
    }

    // Optional fine calculation
    LocalDate today = LocalDate.now();
    long overdueDays = ChronoUnit.DAYS.between(foundIssued.dueDate, today);
    double finePerDay = 50.0; // LKR
    double fine = 0;

    if (overdueDays > 0) {
        fine = overdueDays * finePerDay;
        System.out.println("⚠️ Book is overdue by " + overdueDays + " days.");
        System.out.println("💰 Overdue fine: LKR " + fine);
    }

    // Update book quantity
    for (Book b : books) {
        if (b.id.equalsIgnoreCase(bookId)) {
            b.quantity++;
            break;
        }
    }

    // Remove issued record
    issuedBooks.remove(foundIssued);

    System.out.println("✅ Book returned successfully.");
}
private final static void clearConsole() {
        try {
            // Windows command to clear the console
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            // Handle any exceptions
            System.err.println("Error clearing console: " + e.getMessage());
        }
    }
//linux cmd clear
/*
private final static void clearConsole() {
final String os = System.getProperty("os.name");
try {
if (os.contains("Linux")) {
System.out.print("\033\143");
} else if (os.contains("Windows")) {
new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); }
System.out.print("\033[H\033[2J");
System.out.flush();
} catch (final Exception e) {
// Handle the exception
System.err.println(e.getMessage());
}
}
*/
}

