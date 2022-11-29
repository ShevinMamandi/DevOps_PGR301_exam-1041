package no.shoppifly;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.bind.annotation.*;
import io.micrometer.core.instrument.Gauge;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
public class ShoppingCartController implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private final CartService cartService;
    private HashMap<String, Cart> theCart = new HashMap();
    private Timer productTimer;
    private MeterRegistry meterRegistry;


    @Autowired
    public ShoppingCartController(CartService cartService, MeterRegistry meterRegistry) {
        this.cartService = cartService;
        this.meterRegistry = meterRegistry;
        productTimer = meterRegistry.timer("checkout_latency");
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

    @Timed
    @PostMapping(path = "/cart/checkout")
    public String checkout(@RequestBody Cart cart) {
        long startTime = System.currentTimeMillis();
        meterRegistry.counter("delete_cart").increment();
        String result = cartService.checkout(cart);
        // record time to execute the method
        productTimer.record(Duration
                .ofMillis(System.currentTimeMillis()
                        - startTime));
        theCart.put(cart.getId(), cart);
        return result;
    }

    /**
     * Updates a shopping cart, replacing it's contents if it already exists. If no cart exists (id is null)
     * a new cart is created.
     *
     * @return the updated cart
     */
    @Timed
    @PostMapping(path = "/cart")
    public Cart updateCart(@RequestBody Cart cart) {
        meterRegistry.counter("create_cart").increment();
        //theCart.put(cart.getId(), cart);
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

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        // Denne meter-typen "Gauge" rapporterer hvor mye penger som totalt finnes i banken
        Gauge.builder("carts", cartService,
                        b -> b.getAllsCarts().size()).register(meterRegistry);
        
        Gauge.builder("cartsvalue", cartService,
                        b -> b.total())
                .register(meterRegistry);

        Gauge.builder("checkouts", theCart,
                b -> b.values().size()).register(meterRegistry);
    }

}