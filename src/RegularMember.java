import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDateTime;
import java.time.Duration;
//import java.time.temporal.ChronoUnit;

class RegularMember implements LibraryData, Comparable<RegularMember>{
	public String name;
	public int id;
	private ArrayList<Book> checkedOutBooks = new ArrayList<Book>();//There was static in here
	private ArrayList<OnlineArticle> accessableOnlineArticles = new ArrayList<OnlineArticle>();
	private int checkedOutBookCount = 0;
	private int accessibleOnlineArticleCount = 0;
	public static ArrayList<RegularMember> memberArray = new ArrayList<RegularMember>();
	
	RegularMember(String name, int id) {
		this.name = name;
		this.id = id;
		setCheckedOutBookCount(0);
		setAccessibleOnlineArticleCount(0);
	}
	
	double[] bookOverdues;
	double[] articleOverdues;
	
	@Override
	public double calculateCost() {
	    double total = 0.0;

	    LocalDateTime now = LocalDateTime.now();
	    
	    double[] forBook = new double[checkedOutBooks.size()];
	    double[] forArticle = new double[accessableOnlineArticles.size()];

	    int i = 0;
	    for (Book b : checkedOutBooks) {
	    	if (b.dueDate == null) {
	            forBook[i++] = 0;
	            continue;
	        }
	    	
	        LocalDateTime due = LocalDateTime.of(b.dueDate.year, b.dueDate.month, b.dueDate.day, 0, 0);

	        if (now.isAfter(due)) {
	            long overdueDays = Duration.between(due, now).toDays();
	            total += overdueDays * b.feeByDay;
	            forBook[i] = overdueDays * b.feeByDay;
	            i++;
	        }
	    }

	    int j = 0;
	    for (OnlineArticle oa : accessableOnlineArticles) {
	    	if (oa.accessDate == null) {
	            forArticle[j++] = 0;
	            continue;
	        }
	    	
	        LocalDateTime access = LocalDateTime.of(oa.accessDate.year, oa.accessDate.month, oa.accessDate.day, 0, 0);
	        long daysAccessed = Duration.between(access, now).toDays();

	        if (daysAccessed > 30) {
	            total += (daysAccessed-30) * 10;
	            forArticle[j] = ((daysAccessed-30) * 10);
	        }else {
	        	forArticle[j] = 0;
	        }
	       
	        j++;
	    }
	    
	    bookOverdues = forBook;
	    articleOverdues = forArticle;
	    
	    return total;
	}
	
	public ArrayList<Book> getCheckedOutBooks() {//Removed static from here
		return checkedOutBooks;
	}
	
	public void setCheckedOutBooks(ArrayList<Book> checkedOutBooks) {
		this.checkedOutBooks = checkedOutBooks;
	}
	
	public ArrayList<OnlineArticle> getAccessableOnlineArticles() {
		return accessableOnlineArticles;
	}
	
	public void setAccessableOnlineArticles(ArrayList<OnlineArticle> accessableOnlineArticles) {
		this.accessableOnlineArticles = accessableOnlineArticles;
	}
	
	boolean addBook(Book b) {
		if (getCheckedOutBookCount() == 0) {
			checkedOutBooks.add(b);
			setCheckedOutBookCount(getCheckedOutBookCount() + 1);
			return false;
		}
		return true;
	}
	
	boolean addOA(OnlineArticle oa) {
		if (getAccessibleOnlineArticleCount() == 0) {
			accessableOnlineArticles.add(oa);
			setAccessibleOnlineArticleCount(getAccessibleOnlineArticleCount() + 1);
			return false;
		}
		return true;
	}
	
	void displayInfo() {
		if (memberArray.isEmpty()) {
			System.out.println();
			return;
		}
		
		System.out.println("Checked Out Books:");
		if (checkedOutBookCount > 0) {  
	        for (int i = 0; i < getCheckedOutBookCount(); i++) {
	            System.out.print("Book Name: " + checkedOutBooks.get(i).nameOfBook);
	            System.out.println();
	            System.out.println("ISBN: "+checkedOutBooks.get(i).ISBN);
	            System.out.print("The book entitled '"+checkedOutBooks.get(i).nameOfBook);
	            System.out.println("' has an overdue charge");
	            calculateCost();
	            System.out.println("of "+bookOverdues[i]+" TRL");
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
	            System.out.print("The article entitled '"+accessableOnlineArticles.get(i).nameOfArticle);
	            calculateCost();
	            System.out.print("' has an overdue charge of "+articleOverdues[i]);
	            System.out.println(" TRL");
	            }
	    }else {
	    	System.out.println("None");
	    }
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
	
	void returnBook(String ISBN) {
		if (checkedOutBookCount != 0) {
	        Iterator<Book> book_iterator = checkedOutBooks.iterator();
	        while (book_iterator.hasNext()) {
	            Book currentBook = book_iterator.next();
	            if (ISBN.equals(currentBook.ISBN)) {
	                book_iterator.remove();
	                setCheckedOutBookCount(getCheckedOutBookCount() - 1);
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
	            }
	        }
	    }
	}

	@Override
	public int compareTo(RegularMember o) {
		if (this.calculateCost() > o.calculateCost()) {
			return 1;
		}
		else if (this.calculateCost() < o.calculateCost()) {
			return -1;
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return "Member Name: "+this.name+", ID: "+this.id+"\n";
	}
}
