import java.util.ArrayList;

class Book extends LibraryMaterial{
	public String nameOfBook;
	public String ISBN;
	public Date dueDate;
	public double feeByDay = 50;
	public static int bookCount;
	public static ArrayList<Book> bookArray = new ArrayList<Book>();
	
	Book(String n, String isbn_no, Date date, double price){
		nameOfBook = n;
		ISBN = isbn_no;
		dueDate = new Date(date.day, date.month, date.year);
		cost = price;
	}
	
	Book(String n, String isbn_no, double price){
		nameOfBook = n;
		ISBN = isbn_no;
		cost = price;
	}
	
	Book(String n, String isbn_no, Date date){
		nameOfBook = n;
		ISBN = isbn_no;
		dueDate = new Date(date.day, date.month, date.year);
		cost = calculateCost();
	}
	
	Book(String n, String isbn_no){
		nameOfBook = n;
		ISBN = isbn_no;
		cost = calculateCost();
	}
	
	Book(String isbn_no, Date date){
		ISBN = isbn_no;
		dueDate = new Date(date.day, date.month, date.year);
		cost = calculateCost();
	}
	
	@Override
	public String toString() {
		return "Book Name: "+this.nameOfBook+"\nISBN: "+this.ISBN+"\nThe cost is "+this.getCost()+"\n";
	}
	
	public void displayInfo() {
		for (int i=0;i<bookArray.size();i++) {
			if (bookArray.get(i) != null) {
				System.out.print("Book titled '"+bookArray.get(i).nameOfBook+"' that has");
				System.out.print(" ISBN# "+bookArray.get(i).ISBN+" is due");
				System.out.print(" "+bookArray.get(i).dueDate.day+"/");
				System.out.print(bookArray.get(i).dueDate.month+"/");
				System.out.print(bookArray.get(i).dueDate.year);
				System.out.println();
			}
		}
	}
	
	@Override
	public double calculateCost() {
		return cost;
	}
	
	public static void authenticateISBN(String ISBN) throws ISBNMismatchException{
		int sum = 0;
		int digit_no = 0;
		
		for (int i=0; i<ISBN.length();i++) {
			char ch = ISBN.charAt(i);
			
			if (Character.isDigit(ch)) {
				int digit = Character.getNumericValue(ch);
				
				if (i%2 == 0) {
					sum +=digit*1;
				}
				else {
					sum +=digit*3;
				}
				digit_no++;
			}
		}
		if (digit_no != 13) {
			throw new ISBNMismatchException("Exception: ISBN must be 13 digits.");
		}
		if (sum%10 != 0) {
			throw new ISBNMismatchException("Exception: Invalid ISBN-13 - checksum failed.");
		}
	}
}
