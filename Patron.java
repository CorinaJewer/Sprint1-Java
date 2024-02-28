import java.util.ArrayList;

/**
 * The Patron class represents a library patron.
 * It stores information about the patron such as name, address, and contact details.
 * It also keeps track of the books borrowed by the patron.
 */

public class Patron {

    private static int nextId = 1;

    private int patronId; 
    private String name;   
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private String phone;
    private ArrayList <Book> borrowedBooks; 

    /**
     * Constructs a new Patron object with the specified details.
     *
     * @param name       The name of the patron.
     * @param address    The address of the patron.
     * @param city       The city of the patron.
     * @param province   The province of the patron.
     * @param postalCode The postal code of the patron.
     * @param phone      The phone number of the patron.
     */
    

    public Patron( String name, String address, String city, String province,String postalCode, String phone){

        this.patronId = nextId++;
        this.name = name;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.phone = phone;
        this.borrowedBooks = new ArrayList<>();      
    }

    /**
     * Gets the patron ID.
     *
     * @return The patron ID.
     */

    public int getPatronID(){
        return patronId;
    }

    /**
     * Gets the name of the patron.
     *
     * @return The name of the patron.
     */

    public String getName(){
        return name;
    }

    /**
     * Sets the name of the patron.
     *
     * @param name The new name of the patron.
     */

    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets the address of the patron.
     *
     * @return The address of the patron.
     */

    public String getAddress(){
        return address;
    }

    /**
     * Sets the address of the patron.
     *
     * @param address The new address of the patron.
     */

    public void setAddress(String address){
        this.address = address;
    }

    /**
     * Gets the city of the patron.
     *
     * @return The city of the patron.
     */

    public String getCity(){
        return city;
    }

    /**
     * Sets the city of the patron.
     *
     * @param city The new city of the patron.
     */

    public void setCity(String city){
        this.city = city;
    }

    /**
     * Gets the province of the patron.
     *
     * @return The province of the patron.
     */

    public String getProvince(){
        return province;
    }

    /**
     * Sets the province of the patron.
     *
     * @param province The new province of the patron.
     */

    public void setProvince(String province){
        this.province = province;
    }

    /**
     * Gets the postal code of the patron.
     *
     * @return The postal code of the patron.
     */

    public String getPostalCode(){
        return postalCode;
    }

    /**
     * Sets the postal code of the patron.
     *
     * @param postalCode The new postal code of the patron.
     */

    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }

    /**
     * Gets the phone number of the patron.
     *
     * @return The phone number of the patron.
     */

    public String getPhone(){
        return phone;
    }

    /**
     * Sets the phone number of the patron.
     *
     * @param phone The new phone number of the patron.
     */

    public void SetPhone(String phone){
        this.phone = phone;
    }

    /**
     * Gets the list of books borrowed by the patron.
     *
     * @return The list of borrowed books.
     */

    public ArrayList<Book> getBorrowedBooks(){
        return borrowedBooks;
    }

    /**
     * Adds a book to the list of borrowed books for the patron.
     *
     * @param book The book to be added.
     */

    public void addBorrowedBook(Book book) {
        borrowedBooks.add(book);
    }

     /**
     * Removes a book from the list of borrowed books for the patron.
     *
     * @param book The book to be removed.
     */

    public void removeBorrowedBook(Book book) {
        borrowedBooks.remove(book);
    }

    /**
     * Checks if the patron has borrowed a specific book.
     *
     * @param book The book to check.
     * @return True if the patron has borrowed the book, false otherwise.
     */

    public boolean hasBook(Book book) {
        return borrowedBooks.contains(book);
    }

    /**
     * Returns a string representation of the Patron object.
     *
     * @return A string representation of the Patron object.
     */

    public String toString(){
        return ("PATRON [Patron ID: " + this.patronId + " /" + "Name: " + this.name + " /" + "Address: " + this.address + " /"  + "City: " + this.city + " /" + "Province: " + this.province + " /" +  "Postal Code: " + this.postalCode + " /" + "Phone: " + this.phone  + "]");
    }

}

