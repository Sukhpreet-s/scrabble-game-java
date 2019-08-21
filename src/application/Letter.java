package application;

public class Letter {
	
	public char character;
	public int point;
	public int quantity;
	
	public Letter() {}
	public Letter(char character, int point, int quantity) {
		this.character = character;
		this.point = point;
		this.quantity = quantity;
	}
	
	public void decreaseQuantity() {
		this.quantity = this.quantity - 1;
	}

}
