package poly.edu.Service;

import java.util.Collection;

import poly.edu.Entity.Cart;


public interface ShoppingCartService {
	
	Cart add(int id);
	void remove(int id);
	Cart update(int id, int qty);
	void clear();
	Collection<Cart> getItems();
	int getCount();
	double getAmount();
}
