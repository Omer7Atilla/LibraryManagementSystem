import java.util.ArrayList;

class OnlineArticle extends LibraryMaterial{
	public String publisher;
	public Date accessDate;
	public String nameOfArticle;
	public String DOI;
	public static int articleCount;
	public static ArrayList<OnlineArticle> articleArray = new ArrayList<OnlineArticle>();
	
	OnlineArticle(String name, String doi, String publisher_name){
		nameOfArticle = name;
		DOI = doi;
		publisher = publisher_name;
		cost = calculateCost();
	}
	
	@Override
	public double calculateCost() {
		if (publisher.equals("ACM")) {
			this.cost = 150.0;
			return cost;
		}
		else if (publisher.equals("IEEE")) {
			this.cost = 200.0;
			return cost;
		}
		else {
			this.cost = 100.0;
			return cost;
		}
	}
}
