package no.shoppifly;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.web.bind.annotation.*;
import io.micrometer.core.instrument.Gauge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
public class ShoppingCartController {

    @Autowired
    private final CartService cartService;
    /*
    private Map<String, Cart> theCart = new HashMap();

    private MeterRegistry meterRegistry;
    */
    public ShoppingCartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(path = "/cart/{id}")
    public Cart getCart(@PathVariable String id) {
        return cartService.getCart(id);
    }

    /**
     * Checks out a shopping cart. Removes the cart, and returns an order ID
     *
     * @return an order ID
     */
    @PostMapping(path = "/cart/checkout")
    public String checkout(@RequestBody Cart cart) {
        return cartService.checkout(cart);
    }

    /**
     * Updates a shopping cart, replacing it's contents if it already exists. If no cart exists (id is null)
     * a new cart is created.
     *
     * @return the updated cart
     */
    @PostMapping(path = "/cart")
    public Cart updateCart(@RequestBody Cart cart) {
        return cartService.update(cart);
    }

    /**
     * return all cart IDs
     *
     * @return
     */
    @GetMapping(path = "/carts")
    public List<String> getAllCarts() {
        return cartService.getAllsCarts();
    }
/*
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        // Verdi av total
        Gauge.builder("cart_count", theCart,
                b -> b.values().size()).register(meterRegistry);

        // Denne meter-typen "Gauge" rapporterer hvor mye penger som totalt finnes i banken
        Gauge.builder("bank_sum", theCart,
                        b -> b.values()
                                .stream()
                                .map(Cart::getItems)
                                .mapToInt(Integer::intValue)
                                .sum())
                .register(meterRegistry);
    }*/

}