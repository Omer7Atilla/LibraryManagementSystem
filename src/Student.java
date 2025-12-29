import java.util.ArrayList;
import java.util.Iterator;

class Student extends RegularMember{
	private int checkedOutBookCount = 0;
	private int accessibleOnlineArticleCount = 0;
	private ArrayList<Book> checkedOutBooks = new ArrayList<Book>();
	private ArrayList<OnlineArticle> accessableOnlineArticles = new ArrayList<OnlineArticle>();
	
	Student(String name, int id) {
		super(name, id);
	}
	
	public int getCheckedOutBookCount() {
		return checkedOutBookCount;
	}
	
	public void setCheckedOutBookCount(int checkedOutBookCount) {
		this.checkedOutBookCount = checkedOutBookCount;
	}
	
	public int getAccessibleOnlineArticleCount() {
		return accessibleOnlineArticleCount;
	}
	
	public void setAccessibleOnlineArticleCount(int accessibleOnlineArticleCount) {
		this.accessibleOnlineArticleCount = accessibleOnlineArticleCount;
	}
	
	//@Override
	public ArrayList<Book> getCheckedOutBooks() {
		return checkedOutBooks;
	}
	
	@Override
	public void setCheckedOutBooks(ArrayList<Book> checkedOutBooks) {
		this.checkedOutBooks = checkedOutBooks;
	}
	
	@Override
	public ArrayList<OnlineArticle> getAccessableOnlineArticles() {
		return accessableOnlineArticles;
	}
	
	@Override
	public void setAccessableOnlineArticles(ArrayList<OnlineArticle> accessableOnlineArticles) {
		this.accessableOnlineArticles = accessableOnlineArticles;
	}
	
	@Override
	boolean addBook(Book b) {
		if (getCheckedOutBookCount() < 2) {
			checkedOutBooks.add(b);
			setCheckedOutBookCount(getCheckedOutBookCount() + 1);
			return false;
		}
		return true;
	}
	
	@Override
	boolean addOA(OnlineArticle oa) {
		while (accessableOnlineArticles.size() <= 2) {
	        accessableOnlineArticles.add(null);
	    }
		
		if (getAccessibleOnlineArticleCount() < 2) {
			for (int i=0;i<2;i++) {
				if (accessableOnlineArticles.get(i) == null) {
					accessableOnlineArticles.set(i, oa);
					break;
				}
			}
			setAccessibleOnlineArticleCount(getAccessibleOnlineArticleCount() + 1);
			return false;
		}
		return true;
	}
	
	void displayInfo() {
		if (memberArray.isEmpty()) {
			return;
		}
		
		System.out.println("Checked Out Books:");
		if (checkedOutBookCount > 0) {  
	        for (int i = 0; i < getCheckedOutBookCount(); i++) {
	            System.out.print("Book Name: " + checkedOutBooks.get(i).nameOfBook);
	            System.out.println();
	            System.out.print("Date: " + checkedOutBooks.get(i).dueDate.day + "/");
	            System.out.print(checkedOutBooks.get(i).dueDate.month + "/");       
	            System.out.println(checkedOutBooks.get(i).dueDate.year);
	            System.out.println("ISBN: "+checkedOutBooks.get(i).ISBN);
	            System.out.print("The book entitled '"+checkedOutBooks.get(i).nameOfBook);
	            System.out.println("' has an overdue charge");
	            System.out.println("of "+checkedOutBooks.get(i).calculateCost()+" TRL");
	            System.out.println();
	        }
	    }else {
	    	System.out.println("None");
	    }
	    
		System.out.println("Obtained Online Articles:");
	    if (accessibleOnlineArticleCount > 0) {  
	        for (int i = 0; i < getAccessibleOnlineArticleCount(); i++) {
	            System.out.print("Article Name: " + accessableOnlineArticles.get(i).nameOfArticle);
	            System.out.println();
	            System.out.println("DOI: " + accessableOnlineArticles.get(i).DOI);
	            System.out.println();
	        }
	    }else {
	    	System.out.println("None");
	    }
	}
	
	void returnBook(String ISBN) {
		if (checkedOutBookCount != 0) {
	        Iterator<Book> book_iterator = checkedOutBooks.iterator();
	        while (book_iterator.hasNext()) {
	            Book currentBook = book_iterator.next();
	            if (ISBN.equals(currentBook.ISBN)) {
	                book_iterator.remove();
	                setCheckedOutBookCount(getCheckedOutBookCount() - 1);
	                return;
	            }
	        }
	    }
	}
	
	void returnOA(String DOI) {
		if (accessibleOnlineArticleCount != 0) {
	        Iterator<OnlineArticle> oa_iterator = accessableOnlineArticles.iterator();
	        while (oa_iterator.hasNext()) {
	            OnlineArticle currentArticle = oa_iterator.next();
	            if (DOI.equals(currentArticle.DOI)) {
	                oa_iterator.remove();
	                setAccessibleOnlineArticleCount(getAccessibleOnlineArticleCount() - 1);
	                return;
	            }
	        }
	    }
	}
	
	public double calculateCost() {
		return super.calculateCost();
	}
}
