package com.hcmute.g2store.service.impl;

import com.hcmute.g2store.dto.CartItemDTO;
import com.hcmute.g2store.entity.CartItem;
import com.hcmute.g2store.entity.CartItemKey;
import com.hcmute.g2store.entity.Customer;
import com.hcmute.g2store.entity.Product;
import com.hcmute.g2store.exception.CartItemException;
import com.hcmute.g2store.repository.CartItemRepo;
import com.hcmute.g2store.repository.CustomerRepo;
import com.hcmute.g2store.repository.ProductRepo;
import com.hcmute.g2store.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepo cartItemRepository;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ProductRepo productRepo;

    @Override
    public CartItem addCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional
    public CartItem updateQuantity(CartItem updateCartItem) {
        Optional<CartItem> cartItem = cartItemRepository.findById(updateCartItem.getId());
        if (cartItem.isPresent()) {
            updateCartItem.setCustomer(cartItem.get().getCustomer());
            updateCartItem.setProduct(cartItem.get().getProduct());
            return updateCartItem;
        }
        throw new CartItemException("CartItem with id " + updateCartItem.getId() + " not found");
    }


    @Override
    @Transactional
    public CartItemKey deleteCartItem(CartItemKey cartItemKey) {
        try {
            cartItemRepository.deleteById(cartItemKey);
            return cartItemKey;
        } catch (EmptyResultDataAccessException e) {
            throw new CartItemException("CartItem with id " + cartItemKey + " not found");
        }
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem getCartItemById(CartItemKey cartItemKey) {
        return cartItemRepository.findById(cartItemKey).orElse(null);
    }
    @Override
    public List<CartItemDTO> getCartItemByCustomerId(Integer customerId) {
        List<CartItem> cartItems = cartItemRepository.findAllByCustomer(customerId);

        List<CartItemDTO> cartItemDTOS = cartItems.stream()
                .map(cartItem -> Mapper.toCartItemDto(cartItem))
                .collect(Collectors.toList());

        return cartItemDTOS;
    }

}


