package homework.Controllers;

import homework.Services.Interfaces.CartService;
import homework.Services.Interfaces.ProductService;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.lang.reflect.Field;
import java.util.*;

import static homework.Repositories.CartStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class CartTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    private CartService cartService;
    @Mock
    private ProductService productService;
    @Mock
    private User user;
    @InjectMocks
    Cart cart;
    private static Product prd1;
    private static Product prd2;
    public Map<Product, Integer> mapTestSetUp;
    public Map<Product, Integer> expResult;
    public Field cartStatus = Cart.class.getDeclaredField("status");
    public Field map = Cart.class.getDeclaredField("map");

    public CartTest() throws NoSuchFieldException {
    }

    @Before
    public void setUp() {
        cart = new Cart(user, cartService);
        prd1 = new Product(productService, "Product_1", 100);
        prd2 = new Product(productService, "Product_2", 100);
        mapTestSetUp = new HashMap<>();
        expResult = new HashMap<>();
        cartStatus.setAccessible(true);
        map.setAccessible(true);
    }

    public Object[] parametersForAdd() {
        return new Object[]{
                new Object[]{5, 6,
                        new HashMap<Product, Integer>() {{
                            put(prd1, 5);
                        }},
                        new HashMap<Product, Integer>() {{
                            put(prd1, 11);
                        }}
                },
                new Object[]{0, 1,
                        new HashMap<Product, Integer>(),
                        new HashMap<Product, Integer>() {{
                            put(prd1, 1);
                        }}
                },
                new Object[]{-2, 10,
                        new HashMap<Product, Integer>(),
                        new HashMap<Product, Integer>() {{
                            put(prd1, 10);
                        }}
                },
        };
    }

    /**
     * Тест добавление товара в корзину
     * Если в корзину добавляется, товар, который в ней лежит, то нужно суммировать предыдущее кол-во с новым
     * Если в корзину пытаются добавить 0шт. или отриц. число товара не производить никаких изменений с корзиной
     */
    @Test
    @Parameters(method = "parametersForAdd")
    public void add(int qt, int qt2,
                    Map<Product, Integer> expResult1,
                    Map<Product, Integer> expResult2) {

        when(cartService.add(cart.getCartProducts(), prd1, qt)).thenReturn(expResult1);
        assertEquals(expResult1, cart.add(prd1, qt));

        when(cartService.add(cart.getCartProducts(), prd1, qt2)).thenReturn(expResult2);
        assertEquals(expResult2, cart.add(prd1, qt2));
    }

    /**
     * Тест добавление товара в корзину, из которой уже был оформлен заказ
     * Корзина не должна изменяться
     */
    @Test
    public void addForOrderedState() throws IllegalAccessException {
        cartStatus.set(cart, ORDERED);

        Map<Product, Integer> addResult = new HashMap<>();
        addResult.put(prd1, 100);
        when(cartService.add(cart.getCartProducts(), prd1, 100)).thenReturn(addResult);
        verify(cartService, never()).add(cart.getCartProducts(), prd1, 100);
        assertEquals(expResult, cart.add(prd1, 100));

    }

    public Object[] parametersForRemove() {
        return new Object[]{
                new Object[]{3,
                        new HashMap<Product, Integer>() {{
                            put(prd1, 5);
                        }},
                        new HashMap<Product, Integer>() {{
                            put(prd1, 2);
                        }}
                },
                new Object[]{3,
                        new HashMap<Product, Integer>(),
                        new HashMap<Product, Integer>()
                },
                new Object[]{100,
                        new HashMap<Product, Integer>() {{
                            put(prd1, 5);
                        }},
                        new HashMap<Product, Integer>()
                },
                new Object[]{-5,
                        new HashMap<Product, Integer>() {{
                            put(prd1, 5);
                        }},
                        new HashMap<Product, Integer>() {{
                            put(prd1, 5);
                        }}
                },
                new Object[]{0,
                        new HashMap<Product, Integer>() {{
                            put(prd1, 5);
                        }},
                        new HashMap<Product, Integer>() {{
                            put(prd1, 5);
                        }}
                },
        };
    }

    /**
     * Тест удаление товара из корзины
     * @param qt - кол-во товара, которое нужно удалить из корзины
     * @param beforeRemove - задание состояния корзины на начало теста
     * @param expResult - ожидаемое состояние корзины после вызова метода
     */
    @Test
    @Parameters(method = "parametersForRemove")
    public void removeWithQuantity(int qt,
                                   Map<Product, Integer> beforeRemove,
                                   Map<Product, Integer> expResult) throws IllegalAccessException {
        map.set(cart, beforeRemove);

        when(cartService.remove(cart.getCartProducts(), prd1, qt)).thenReturn(expResult);
        assertEquals(expResult, cart.remove(prd1, qt));
    }

    @Test
    public void removeWithoutQuantity() throws IllegalAccessException {
        Map<Product, Integer> beforeRemove = new HashMap<>();
        beforeRemove.put(prd1, 10);
        map.set(cart, beforeRemove);

        when(cartService.remove(cart.getCartProducts(), prd1)).thenReturn(expResult);
        assertEquals(expResult, cart.remove(prd1));
    }
    /**
     * Тест удаление товара из корзины, из которой уже был оформлен заказ
     * Корзина не должна изменяться
     */
    @Test
    public void removeWithOrderedStatus() throws IllegalAccessException {
        mapTestSetUp.put(prd1, 10);
        cartStatus.set(cart, ORDERED);
        map.set(cart, mapTestSetUp);

        when(cartService.remove(cart.getCartProducts(), prd1)).thenReturn(expResult);
        assertEquals(mapTestSetUp, cart.remove(prd1));

        when(cartService.remove(cart.getCartProducts(), prd1, 10)).thenReturn(expResult);
        assertEquals(mapTestSetUp, cart.remove(prd1, 10));

        verify(cartService, times(0)).remove(mapTestSetUp, prd1);
    }

    @Test
    public void makeOrder() throws IllegalAccessException {
        //Test for normal case
        mapTestSetUp.put(prd1, 10);
        map.set(cart, mapTestSetUp);
        when(cartService.makeOrder(cart.getUserId(), cart.getCartId())).thenReturn(1);
        Integer actualResult = cart.makeOrder();
        assertEquals(cart.getOrderId(), actualResult);

        //Test for already created order
        assertEquals(cart.getOrderId(), cart.makeOrder());
        verify(cartService, times(1)).makeOrder(cart.getUserId(), cart.getCartId());
    }

    @Test
    public void makeOrderForEmptyCart() {
        assertNull(cart.makeOrder());
    }

    @Test
    public void cartPrice() throws IllegalAccessException {
        when(cartService.cartPrice(cart.getCartProducts())).thenReturn(0);
        assertEquals(0, cart.cartPrice());

        mapTestSetUp.put(prd1, 10);
        map.set(cart, mapTestSetUp);
        int price = prd1.getPrice() * 10;
        when(cartService.cartPrice(cart.getCartProducts())).thenReturn(price);
        System.out.println(cart.cartPrice());
        assertEquals(price, cart.cartPrice());

        mapTestSetUp.put(prd2, 10);
        price = price + prd2.getPrice() * 10;
        when(cartService.cartPrice(cart.getCartProducts())).thenReturn(price);
        System.out.println(cart.cartPrice());
        assertEquals(price, cart.cartPrice());

        cartStatus.set(cart, ORDERED);
        assertEquals(price, cart.cartPrice());

    }

    @Test
    public void getCart() {
        when(cartService.getCart(cart.getCartId())).thenReturn(cart);
        assertEquals(cart, Cart.getCart(cart.getCartId()));

        when(cartService.getCart(1000)).thenReturn(null);
        assertNull(Cart.getCart(1000));
    }

}