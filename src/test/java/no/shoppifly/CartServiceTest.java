package no.shoppifly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartServiceTest {

    @Test
    public void shouldRemoveCartAfterCheckout() {

        CartService service = new NaiveCartImpl();
        //added cart1
        Cart theCart = Cart.builder().build();
        service.update(theCart);
        assertEquals(1, service.getAllsCarts().size());

        //added cart2
        Cart theCart2 = Cart.builder().build();
        service.update(theCart2);
        assertEquals(2, service.getAllsCarts().size());

        String orderId = service.checkout(theCart);
        assertNotNull(orderId);

        // Jim; This must be wrong, right? Shouldn't the cart be removed after checkout
        assertEquals(1, service.getAllsCarts().size());
    }

}
