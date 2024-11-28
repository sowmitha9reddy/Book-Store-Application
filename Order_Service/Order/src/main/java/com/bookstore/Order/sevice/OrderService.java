package com.bookstore.Order.sevice;

import com.bookstore.Order.dto.OrderDto;
import com.bookstore.Order.exceptionhandler.OrderNotFoundException;
import com.bookstore.Order.external.Address;
import com.bookstore.Order.external.Cart;
import com.bookstore.Order.external.User;
import com.bookstore.Order.mapper.OrderMapper;
import com.bookstore.Order.model.Order;
import com.bookstore.Order.model.OrderItem;
import com.bookstore.Order.repository.OrderRepository;
import com.bookstore.Order.util.Utility;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements OrderDao {

    @Autowired
    private OrderRepository orderRepository;

   @Autowired
   private Utility utility;


    @Override
    @Transactional
    public OrderDto PlaceOrder(long userId,long addressId) {

            //Fetch cart items for the user
            List<Cart> cartItems = utility.cartItemsForUser(userId);

            if (cartItems.isEmpty()) {
                throw new IllegalArgumentException("No items in the cart for user: " + userId);
            }


            // Create a new Order entity
            Order order = new Order();
            order.setUserId(userId);
            order.setCancel(false);
            order.setAddressId(addressId);

            // Calculate total price and total quantity, and associate order items
            float totalPrice = 0;
            int totalQuantity = 0;

            for (Cart item : cartItems) {

                // Create an OrderItem entity for each cart item
                OrderItem orderItem=new OrderItem();

                orderItem.setBookId(item.getBookId());
                orderItem.setPrice(item.getTotalPrice());
                orderItem.setQuantity(item.getQuantity());
                orderItem.setOrder(order); //set order item to the appropriate order

                // Add order item to the order
                order.getOrderItems().add(orderItem);

                // Update total price and total quantity
                totalPrice += item.getTotalPrice();
                totalQuantity += item.getQuantity();
            }

            //  Set the total price and total quantity in the Order
            order.setTotalPrice(totalPrice);
            order.setQuantity(totalQuantity);


            orderRepository.save(order);

            //  Clear the cart
            utility.clearCartForUser(userId);

            return OrderMapper.orderMapperToOrderDto(order);



    }



    @Override
    public boolean cancelOrder(long orderId) {

        Order order=orderRepository.findById(orderId).orElseThrow(() ->new OrderNotFoundException("Order Id Not Found"));

        //Check if the order is already canceled
        if (order.isCancel()) {
            return false; // The order is already canceled
        }

        //  Set the cancel from false to true
        order.setCancel(true);

        //  Save the updated order
        orderRepository.save(order);

        //order is cancelled add book back to the book database
        for (OrderItem orderItem : order.getOrderItems()) {
            utility.changeBookQuantity(orderItem.getBookId(),orderItem.getQuantity());

        }
        return true;

    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> allOrders=orderRepository.findAll();
        return allOrders.stream().map(order -> OrderMapper.orderMapperToOrderDto(order)).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getAllOrdersForUser(long userId) {
        List<Order> allOrdersOfUser=orderRepository.findAllByUserId(userId);
        return  allOrdersOfUser.stream().map(order -> OrderMapper.orderMapperToOrderDto(order)).collect(Collectors.toList());
    }
}
