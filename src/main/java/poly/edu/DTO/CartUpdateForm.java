package poly.edu.DTO;

import java.util.List;

public class CartUpdateForm {
    private List<CartItemUpdate> cartItemList;
    private String voucherCode;

    public List<CartItemUpdate> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItemUpdate> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }


}

