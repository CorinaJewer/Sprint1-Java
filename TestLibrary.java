import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
 
/**
 * This class provides a simple command-line interface to test the functionality
 * of the Library class.
 */

public class TestLibrary {

    /**
     * The main method where the testing of the Library class takes place.
     * 
     * @param args (not used).
     */
    
    public static void main(String[] args) {

        /**
        * Constructs a new Library object.
        */

        Library library = new Library();

        // Load initial library data
       
        loadData(library);

        // Show initial library data

        System.out.println();
        System.out.println(library);
        System.out.println();

        // Search by Author, Title or ISBN

        System.out.println("Search results by Author");
        System.out.println("------------------------");
        System.out.println(library.searchByAuthor("Danielle Steele"));
        System.out.println();
        System.out.println("Search results by ISBN");
        System.out.println("----------------------");
        System.out.println(library.searchByISBN("123458D"));
        System.out.println();
        System.out.println("Search results by Title");
        System.out.println("-----------------------");
        System.out.println(library.searchByTitle("The Wish"));

        System.out.println();

        // List of Books

        System.out.println("Available Books");
        System.out.println("---------------");
        ArrayList<Book> allBooks = library.getAllBooks();
        for (Book book : allBooks) {
           System.out.println(book);
        }

        System.out.println();

        // List of Authors

        System.out.println("Available Authors");
        System.out.println("-----------------");
        ArrayList<Author> allAuthors = library.getAllAuthors();      
        for (Author author : allAuthors) {
           System.out.println(author);     
        }

        System.out.println();

        // List of Patrons

        System.out.println("Available Patrons");
        System.out.println("-----------------");
        ArrayList<Patron> allPatrons = library.getAllPatrons();
        for (Patron patron : allPatrons) {
            System.out.println(patron);
        }

        // Open Scanner for user inputs to test library methods

        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("Borrowing Books");
        System.out.println("---------------");
  
        // Prompt user to select a book. While true loop to allow for multiple entries.

        while (true){

            System.out.println("Enter the Book ID of the book you want to borrow:");
            int bookID = scanner.nextInt();

            // Find the book in the list by its id

            Book bookToBorrow = null;
            for (Book book : allBooks) {
                if (book.getBookId() == (bookID)) {
                    bookToBorrow = book;
                    break;
                }
            }

            // Prompt user to select a patron

            System.out.println("Enter the Patron ID of the patron who wants to borrow the book:");
            int patronID = scanner.nextInt();

            // Find patron in list by it's id

            Patron borrowingPatron = null;
            for (Patron patron : allPatrons) {
                if (patron.getPatronID() == (patronID)) {
                    borrowingPatron = patron;
                    break;
            }}

            // Borrow a book using library borrowBook method

            library.borrowBook(borrowingPatron, bookToBorrow);

            System.out.println();

            // Print the borrowing patron's borrowedBooks array if it's not empty. For each loop to display each book line by line.

            System.out.println("List of borrowed books for " + borrowingPatron.getName());
            System.out.println();
            ArrayList<Book> borrowedBooks = borrowingPatron.getBorrowedBooks();
            if (borrowedBooks.isEmpty()) {
                System.out.println(borrowingPatron.getName() + " has no books borrowed.");
            } else {
                for (Book book : borrowedBooks) {
                    System.out.println(book);
                }
            }

            System.out.println();

            System.out.println("Do you want to borrow more books? (y/n)");
            String reply = scanner.next();
            if (!reply.equalsIgnoreCase("y")) {
                break; // Exit if the user doesn't want to return more books
            }
        }

        System.out.println();

        System.out.println("Returning Books");
        System.out.println("---------------");


        while (true) {

            // Prompt user to select a patron

            System.out.println("Enter the Patron ID of the patron who wants to return a book:");
             int patronID = scanner.nextInt();

            // Find patron in list by it's id

            Patron returningPatron = null;
            for (Patron patron : allPatrons) {
                if (patron.getPatronID() == (patronID)) {
                    returningPatron = patron;
                    break;
            }}

            // Prompt user to select a book

            System.out.println("Enter the Book ID of the book you want to return:");
             int bookID = scanner.nextInt();

            // Find the book in the list by its id

            Book bookToReturn = null;
            for (Book book : allBooks) {
                if (book.getBookId() == (bookID)) {
                    bookToReturn = book;
                    break;
                }
            }

            // Return a book using the library class returnBook method

            library.returnBook(returningPatron, bookToReturn);

            // Print the returning patron's borrowedBooks array if it is not empty. For each loop to print out each book in the list line by line.

            System.out.println();
        
            System.out.println("Borrowed Books for " + returningPatron.getName());
             ArrayList <Book> borrowedBooks = returningPatron.getBorrowedBooks(); 
            if (borrowedBooks.isEmpty()) {
                System.out.println(returningPatron.getName() + " has no books borrowed.");
            } else {               
                for (Book book : borrowedBooks) {
                    System.out.println(book);                 
                }          
            }

            System.out.println();

            System.out.println("Do you want to return more books? (y/n)");
            String answer = scanner.next();
            if (!answer.equalsIgnoreCase("y")) {
                break; // Exit the loop if the user doesn't want to return more books
            }
        }

        System.out.println(library);


        System.out.println("Deleting Authors/Books/Patrons");
        System.out.println("------------------------------");

        //Prompt user to select an author to delete

        System.out.println("Enter the Author ID of the author you want to delete:");
        System.out.println();
        for (Author author : library.getAllAuthors()) {
            System.out.println("ID: " + author.getAuthorID() + ", Name: " + author.getName());
        }

        // Find author in it's list by id
        
        int authorIDToDelete = scanner.nextInt();
        
        Author authorToDelete = null;
        for (Author author : library.getAllAuthors()) {
            if (author.getAuthorID() == authorIDToDelete) {
                authorToDelete = author;
                break;
            }
        }

        // Delete an author using the Library class deleteAuthor method

        library.deleteAuthor(authorToDelete);

        System.out.println();

        // Prompt user to select book to delete

        System.out.println("Enter the Book ID of the book you want to delete:");
        System.out.println();
        for (Book book : library.getAllBooks()) {
            System.out.println("ID: " + book.getBookId() + ", Title: " + book.getTitle());
        }   

        int bookIDToDelete = scanner.nextInt();

        Book bookToDelete = null;
        for (Book book : library.getAllBooks()) {
            if (book.getBookId() == bookIDToDelete) {
                bookToDelete = book;
                break;
            }
        }
        library.deleteBook(bookToDelete);

        System.out.println();

        // Prompt user to select a patron to delete

        System.out.println("Enter the Patron ID of the patron you want to delete:");
        System.out.println();
        for (Patron patron : library.getAllPatrons()) {
            System.out.println("ID: " + patron.getPatronID() + ", Name: " + patron.getName());
        }   
        
        int patronIDToDelete = scanner.nextInt();

        Patron patronToDelete = null;
        for (Patron patron : library.getAllPatrons()) {
            if (patron.getPatronID() == patronIDToDelete) {
                patronToDelete = patron;
                break;
            }
        }
       
        library.deletePatron(patronToDelete);

        System.out.println();

        // Display current library status

        System.out.println(library);

        System.out.println();


        System.out.println("Let's choose a book and update its due date to a previous date and set it's status to Overdue so we can test the overdueBooksList method.");
        System.out.println();
        for (Book book : library.getAllBooks()) {
            System.out.println("ID: " + book.getBookId() + ", Title: " + book.getTitle());
        }  

        int bookIDToUpdate = scanner.nextInt();

        Book bookToUpdate = null;
        for (Book book : allBooks) {
            if (book.getBookId() == (bookIDToUpdate)) {
                bookToUpdate = book;
                break;
        }}
        bookToUpdate.setDueDate(LocalDate.now().minusDays(1));
        bookToUpdate.setStatus(Status.OVERDUE);

        // Close the scanner after all input operations are done

        scanner.close(); 

        // Display current library status

        System.out.println(library);

        library.OverdueBookList();

        System.out.println();
      
    }

    /**
     * Loads initial data into the library.
     * 
     * @param library The library object to which data will be loaded.
     */

    public static void loadData(Library library) {

        ArrayList<Author> authors = new ArrayList<>();
        ArrayList<Patron> patrons = new ArrayList<>();
        ArrayList<Book> books = new ArrayList<>();

        // Create authors

        authors.add(new Author("Danielle Steele", "04/15/1977"));
        authors.add(new Author("Jordan Peterson", "01/31/1969"));
        authors.add(new Author("Nicholas Sparks", "12/31/1965"));

        // Create patrons

        patrons.add(new Patron("Mini Mouse", "1 Main St", "Corner Brook", "A1A 1A1", "NL", "709-111-1111"));
        patrons.add(new Patron("Daisy Duck", "44 Forth Street", "Vancouver", "V5K 0A1", "BC", "604-555-5555"));
        patrons.add(new Patron("Betty Boop", "100 Vintage Street", "St. John's", "BC", "A4N2M8", "709-121-1212"));

        // Create books

        books.add(new Book("Upside Down", authors.get(0), "123456D", "Random House"));
        books.add(new Book("12 Rules For Life", authors.get(1), "123457H", "Random House"));
        books.add(new Book("The Wish", authors.get(2), "123458D", "Random House"));
        books.add(new Book("The Wish", authors.get(2), "123458D", "Random House"));

        // Add authors, patrons, and books to the library

        for (Author author : authors) {
            library.addAuthor(author);
        }
        for (Patron patron : patrons) {
            library.addPatron(patron);
        }
        for (Book book : books) {
            library.addBook(book);
        }
    }        
}

        
