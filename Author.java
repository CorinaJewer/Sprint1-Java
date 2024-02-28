import java.util.ArrayList;

/**
 * Represents an author who writes books.
 */

public class Author{

    private static int nextId = 1;

    private int authorId;
    private String name;
    private String dateOfBirth;
    private ArrayList <Book> writtenBooks;

    /**
     * Constructs an Author object with the specified name and date of birth.
     *
     * @param name         The name of the author.
     * @param dateOfBirth  The date of birth of the author.
     */

    public Author(String name, String dateOfBirth){

        this.authorId = nextId++;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.writtenBooks = new ArrayList<>();    
    }

    /**
     * Gets the ID of the author.
     *
     * @return The author ID.
     */

    public int getAuthorID(){
        return authorId;
    }

     /**
     * Gets the name of the author.
     *
     * @return The author's name.
     */

    public String getName(){
        return name;
    }

    /**
     * Sets the name of the author.
     *
     * @param name The new name of the author.
     */

    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets the date of birth of the author.
     *
     * @return The date of birth.
     */

    public String getDateOfBirth(){
        return dateOfBirth;
    }

    /**
     * Sets the date of birth of the author.
     *
     * @param dateOfBirth The new date of birth of the author.
     */

    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Adds a book written by the author to the writtebBooks list.
     *
     * @param book The book to be added.
     */

    public void addWrittenBook(Book book){
        writtenBooks.add(book);
    }

    /**
     * Deletes a book written by the author from the writtebBooks list.
     *
     * @param book The book to be deleted.
     */


    public void deleteWrittenBook(Book book){
        writtenBooks.remove(book);
    }

    /**
     * Gets a list of books written by the author.
     * 
     * @return The list of books written by the author.
     */


    public ArrayList <Book> getWrittenBooks(){
        return writtenBooks;
    }

    /**
     * Returns a string representation of the author.
     *
     * @return A string containing the author's ID, name, and date of birth.
     */

    public String toString(){
        return ("AUTHOR [Author ID: " + this.authorId + " / Name: " + this.name + " / Date of Birth: " + this.dateOfBirth + "]");
    } 
}
