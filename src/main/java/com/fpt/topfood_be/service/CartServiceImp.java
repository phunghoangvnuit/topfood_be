package com.fpt.topfood_be.service;

import com.fpt.topfood_be.model.*;
import com.fpt.topfood_be.repository.CartItemRepository;
import com.fpt.topfood_be.repository.CartRepository;
import com.fpt.topfood_be.repository.FoodRepository;
import com.fpt.topfood_be.request.AddCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImp implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private FoodService foodService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Food food = foodService.findFoodById(req.getFoodId());

        Cart cart = cartRepository.findByCustomerId(user.getId());

        CartItem newCartItem =  new CartItem();
        newCartItem.setFood(food);
        newCartItem.setIngredients(req.getIngredients());


        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getFood().equals(newCartItem.getFood()) &&
                    cartItem.getIngredients().stream().map(IngredientsItem::getId).collect(Collectors.toSet())
                            .equals(newCartItem.getIngredients().stream().map(IngredientsItem::getId).collect(Collectors.toSet()))) {
                int newQuantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }

        newCartItem.setCart(cart);
        newCartItem.setQuantity(req.getQuantity());

        // Calculate the total price of the ingredients
        Long ingredientsTotalPrice = 0L;
        for (IngredientsItem ingredient : req.getIngredients()) {
            ingredientsTotalPrice += ingredient.getPrice();
        }

        // Set the total price of the cart item
        newCartItem.setTotalPrice(req.getQuantity() * (food.getPrice() + ingredientsTotalPrice));

        CartItem savedCartItem = cartItemRepository.save(newCartItem);

        cart.getItems().add(savedCartItem);

        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()){
            throw new Exception("cart item not found");
        }
        CartItem item = cartItemOptional.get();
        item.setQuantity(quantity);

        // 5*100 = 500

        item.setTotalPrice((item.getFood().getPrice() + item.getIngredients().stream().mapToLong(IngredientsItem::getPrice).sum()) * quantity);

        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);


        Cart cart = cartRepository.findByCustomerId(user.getId());

        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()){
            throw new Exception("cart item not found");
        }

        CartItem item = cartItemOptional.get();

        cart.getItems().remove(item);

        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
        Long total = 0L;

        for(CartItem cartItem : cart.getItems()){
            Long ingredientsTotalPrice = cartItem.getIngredients().stream()
                    .mapToLong(IngredientsItem::getPrice)
                    .sum();
            total += (cartItem.getFood().getPrice() + ingredientsTotalPrice) * cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if(optionalCart.isEmpty()){
            throw new Exception("cart not found with id " + id);
        }
        return optionalCart.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
//        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(userId);
        cart.setTotal(calculateCartTotals(cart));
        return cart;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
//        User user = userService.findUserByJwtToken(jwt);
        Cart cart = findCartByUserId(userId);

        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}
