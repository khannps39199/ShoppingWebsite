package poly.edu.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import poly.edu.Entity.Cart;

@SessionScope
@Service

public class ShoppingCartServiceImpl implements ShoppingCartService {
	static Map<Integer,Cart> list;
	public static Map<Integer, Cart> map= new HashMap<>();
	double amount=0;
	@Override
	public Cart add(int id) {
		 if (!list.containsKey(id)) {
		        return null; 
		    }
		    Cart item = list.get(id);
		    if (map.containsKey(id)) {
		        map.get(id).setQuantity(map.get(id).getQuantity()+1);; 
		    } else {
		        item.setQuantity(1); 
		        map.put(id, item);
		    }

		    return map.get(id);
	}

	@Override
	public void remove(int id) {
			 map.remove(id);
	}

	@Override
	public Cart update(int id, int qty) {
	    if (map.containsKey(id)) {
	        map.get(id).setQuantity(qty);
	    }
	    return map.get(id);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Collection<Cart> getItems() {
		return map.values();
	}

	@Override
	public int getCount() {
		return map.size();
	}

	@Override
	public double getAmount() {
		map.forEach((id,item)->amount=amount+item.getQuantity()*item.getProduct().getPrice());
		return amount;
	}

}
