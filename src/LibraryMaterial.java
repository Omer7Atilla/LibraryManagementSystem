
abstract class LibraryMaterial implements LibraryData{
	double cost;
	
	double getCost() {
		return cost;
	}
	
	void setCost(double Cost) {
		cost = Cost;
	}
	
	@Override
	public double calculateCost() {
		return cost;
	}
}
