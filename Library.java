import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * This class represents a library where books can be borrowed and returned and 
 * an overdue list generated.
 * It manages books, authors, and patrons.
 */

public class Library {

    private ArrayList<Book> allBooks;
    private ArrayList<Author> allAuthors;
    private ArrayList<Patron> allPatrons;

    /**
     * Constructs a new Library object.
     */

    public Library() {
        this.allBooks = new ArrayList<>();
        this.allAuthors = new ArrayList<>();
        this.allPatrons = new ArrayList<>();
    }

    /**
     * Retrieves all books in the library.
     * 
     * @return The list of all books in the library.
     */

    public ArrayList<Book> getAllBooks(){
        return allBooks;
    }

   /**
     * Retrieves all authors in the library.
     * 
     * @return The list of all authors in the library.
     */ 

    public ArrayList<Author> getAllAuthors(){
        return allAuthors;
    }

    /**
     * Retrieves all patrons in the library.
     * 
     * @return The list of all patrons in the library.
     */

    public ArrayList<Patron> getAllPatrons(){
        return allPatrons;
    }

    /**
     * Adds a book to the library.
     * 
     * @param book The book to be added.
     */

    public void addBook(Book book) {
        allBooks.add(book);
        Author author = book.getAuthor();
        if (author != null){
            author.addWrittenBook(book);
        }
    }

    /**
     * Deletes a book to the library.
     * 
     * @param book The book to be deleted.
     */

    public void deleteBook(Book book) {
        allBooks.remove(book);
        Author author = book.getAuthor();
    if (author != null) {
        author.deleteWrittenBook(book);
    }
        for (Patron patron : allPatrons) {
            patron.removeBorrowedBook(book);
        }
    }

   /**
     * Adds an author to the library.
     * 
     * @param author The author to be added.
     */

    public void addAuthor(Author author) {
        allAuthors.add(author);
    }

    // Iterator used as modifying the array while iterating over it.

    /**
     * Deletes an author from the library.
     * 
     * @param author The author to be deleted.
     */

    public void deleteAuthor(Author author) {
        // Remove books associated with the deleted author
        Iterator<Book> iterator = allBooks.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getAuthor().equals(author)) {
                author.deleteWrittenBook(book);
                iterator.remove(); 
            }
        }

        // Remove the author
        allAuthors.remove(author);
    }

    /**
     * Adds a patron to the library.
     * 
     * @param patron The patron to be added.
     */

    public void addPatron(Patron patron) {
        allPatrons.add(patron);
    }

    /**
     * Deletes a patron to the library.
     * 
     * @param patron The patron to be deleted.
     */

    public void deletePatron(Patron patron) {
        if (!patron.getBorrowedBooks().isEmpty()) {
            // If the patron has borrowed books, mark them as overdue
            for (Book book : patron.getBorrowedBooks()) {
                book.setStatus(Status.OVERDUE); 
            }
        }
        allPatrons.remove(patron);
    }

    /**
     * Searches for books in the library by title.
     * 
     * @param title The title of the book to search for.
     * @return A list of books with matching titles.
     */

    public ArrayList<Book> searchByTitle(String title) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getTitle().toLowerCase().equalsIgnoreCase(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Searches for books in the library by author.
     * 
     * @param authorName The name of the author to search for.
     * @return A list of books written by the specified author.
     */

    public ArrayList<Book> searchByAuthor(String authorName) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getAuthor().getName().toLowerCase().equalsIgnoreCase(authorName.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Searches for a book by its ISBN number.
     * 
     * @param isbn The ISBN number of the book.
     * @return The book with the specified ISBN, or null if not found.
     */

    public ArrayList<Book> searchByISBN(String ISBN) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getISBN().equals(ISBN)) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Allows a patron to borrow a book from the library.
     * 
     * @param patron The patron borrowing the book.
     * @param book The book to be borrowed.
     */

    public void borrowBook(Patron patron, Book book) {
        book.borrowBook(patron);
    }

    /**
     * Allows a patron to return a book to the library.
     * 
     * @param patron The patron returning the book.
     * @param book The book to be returned.
     */

    public void returnBook(Patron patron, Book book) {
        book.returnBook(patron);
    }

    /**
     * Generates a list of overdue books in the library.
     */

    public void OverdueBookList() {
        List<Book> overdueBooks = new ArrayList<>();
        for (Book book : allBooks) {
            if ((book.getBookStatus() == Status.CHECKED_OUT || book.getBookStatus() == Status.OVERDUE) && book.getDueDate() != null && LocalDate.now().isAfter(book.getDueDate())) {
                book.setStatus(Status.OVERDUE); // Update status to overdue
                overdueBooks.add(book); // Add overdue book to the list
            }
        }

    // Print the list of overdue books and update their Status

        if (!overdueBooks.isEmpty()) {
            System.out.println("Overdue books:");
            System.out.println("**************");
            for (Book book : overdueBooks) {
                System.out.println("BOOK [ID: " + book.getBookId() + " Title: '" + book.getTitle() + "']");
            }
        } else {
            System.out.println("No books are currently overdue.");
        }
    }

    /**
     * Generates a string representation of the library.
     * 
     * @return A string containing information about all books, authors, and patrons in the library.
     */

    @Override
    public String toString() {
        String result = "Library [\n\n";

        result += "BOOKS \n\n";
        for (Book book : allBooks){
            result += book + "\n\n"; // Append book 
        }
        result += "AUTHORS \n\n";
        for (Author author : allAuthors) {
            result += author + "\n\n"; // Append author
        }
        result += "PATRONS \n\n";
        for (Patron patron : allPatrons) {
            result += patron + "\n\n"; // Append patron
        }
        result += "]";
        return result;
    }
}