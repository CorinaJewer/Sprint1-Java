import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a book in the library.
 */

public class Book implements Borrowable{

    private static final Map<String, Integer> copiesByISBN = new HashMap<String, Integer>();

    private static int nextId = 1;
    private int bookId;
    private String title;
    private Author author;
    private String ISBN;
    private String publisher;
    private Status bookStatus;
    private LocalDate dueDate;

    /**
     * Constructs a new Book object.
     * 
     * @param title     The title of the book.
     * @param author    The author of the book.
     * @param ISBN      The ISBN of the book.
     * @param publisher The publisher of the book.
     */

    public Book(String title, Author author, String ISBN, String publisher) {

        this.bookId = nextId++;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.bookStatus = Status.AVAILABLE;
       
        copiesByISBN.putIfAbsent(ISBN, 0);
        copiesByISBN.put(ISBN, copiesByISBN.get(ISBN) + 1);
    }

    /**
     * Retrieves the ID of the book.
     * 
     * @return The ID of the book.
     */

    public int getBookId(){
        return bookId;
    }

    /**
     * Retrieves the title of the book.
     * 
     * @return The title of the book.
     */

    public String getTitle(){
        return title;
    }

    /**
     * Sets the title of the book.
     * 
     * @param title The new title of the book.
     */

    public void setTitle(String title){
        this.title = title;
    }

    /**
     * Retrieves the author of the book.
     * 
     * @return The author of the book.
     */

    public Author getAuthor(){
        return author;
    }

    /**
     * Sets the author of the book.
     * 
     * @param author The new author of the book.
     */

    public void setAuthor(Author author){
        this.author = author;
    }

    /**
     * Retrieves the ISBN of the book.
     * 
     * @return The ISBN of the book.
     */

    public String getISBN(){
        return ISBN;
    }

    /**
     * Sets the ISBN of the book.
     * 
     * @param ISBN The new ISBN of the book.
     */

    public void setISBN(String ISBN){
        this.ISBN = ISBN;
    }

    /**
     * Retrieves the publisher of the book.
     * 
     * @return The publisher of the book.
     */

    public String getPublisher(){
        return publisher;
    }

    /**
     * Sets the publisher of the book.
     * 
     * @param publisher The new publisher of the book.
     */

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    /**
     * Retrieves a map containing the number of copies available for each ISBN.
     * 
     * @return A map where the keys are ISBNs and the values are the number of copies available.
     */

    public static Map<String, Integer> getCopiesByISBN() {
        return copiesByISBN;
    }

    /**
     * Retrieves the book status.
     * 
     * @return The status of the book.
     */

    public Status getBookStatus(){
        return this.bookStatus;
    }

    /**
     * Sets the status of the book.
     * 
     * @param bookStatus The new status of the book.
     */

    public void setStatus(Status bookStatus){
        this.bookStatus = bookStatus;
    }

    /**
     * Retrieves the due date of the book.
     * 
     * @return The due date of the book.
     */

    public LocalDate getDueDate(){
        return dueDate;
    }

    /**
     * Sets the due date of the book.
     * 
     * @param dueDate The new due date of the book.
     */

    public void setDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }

    /* If the book has a status of available, update it to checked out, set it's due date for two weeks time, add the book to the borrowedBook array and decrease the count of available copies of the book by 1 in the copiesByISBN map.*/

    /**
     * Allows a patron to borrow the book.
     * 
     * @param patron The patron borrowing the book.
     */

    @Override
    public void borrowBook(Patron patron) {

        if (bookStatus == Status.AVAILABLE) {
            bookStatus = Status.CHECKED_OUT;
            dueDate = LocalDate.now().plusWeeks(2);
            patron.addBorrowedBook(this);
            copiesByISBN.put(ISBN, copiesByISBN.get(ISBN) - 1); // Decrement count of available copies
            System.out.println("Book ID: " + this.bookId + " '" + title + "' borrowed by " + patron.getName() + ".");
        } else {
            System.out.println("Sorry, Book ID: " + this.bookId + " is currently borrowed. Check back soon!");
        }
    }

    /**
     * Allows a patron to return the book.
     * 
     * @param patron The patron returning the book.
     */
    
    @Override
    public void returnBook(Patron patron) {

        if ((bookStatus == Status.CHECKED_OUT || bookStatus == Status.OVERDUE) && patron.hasBook(this)) {
            bookStatus = Status.AVAILABLE;
            patron.removeBorrowedBook(this);
            copiesByISBN.put(ISBN, copiesByISBN.get(ISBN) + 1); // Increment count of available copies
            System.out.println("Book ID: " + this.bookId + " '" + title + "' returned by " + patron.getName() + ".");
        } else {
            System.out.println("Unable to Return: " + this.title + " was not found in " + patron.getName() + "'s list of borrowed books.");
        }
    }
        
     /**
     * Returns a string representation of the book.
     * 
     * @return A string containing information about the book.
     */

    public String toString(){
        return ("BOOK: [Book ID: " + this.bookId + " /" + "Title: " + this.title + " /" + "Author: " +  this.author.getName() + " /" + "ISBN: " +  this.ISBN + " /" + "Publisher: " + this.publisher + " /" + "Status: " +  this.bookStatus + " /" + "Copies Available to Borrow: " + copiesByISBN.getOrDefault(ISBN, 0) + "]");
    }
}
