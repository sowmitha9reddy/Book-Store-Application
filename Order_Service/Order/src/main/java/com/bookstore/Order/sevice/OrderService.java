package com.bookstore.Order.sevice;

import com.bookstore.Order.dto.OrderDto;
import com.bookstore.Order.exceptionhandler.OrderNotFoundException;

import com.bookstore.Order.external.Cart;
import com.bookstore.Order.external.User;
import com.bookstore.Order.mapper.OrderMapper;
import com.bookstore.Order.model.Order;
import com.bookstore.Order.model.OrderItem;
import com.bookstore.Order.repository.OrderRepository;
import com.bookstore.Order.util.EmailService;
import com.bookstore.Order.util.Utility;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements OrderDao {

   // @Autowired
    private final  OrderRepository orderRepository;

   //@Autowired
   private final Utility utility;

    //@Autowired
    private final EmailService emailService;
    @Autowired
   public OrderService(OrderRepository orderRepository,Utility utility,EmailService emailService){
       this.orderRepository = orderRepository;
       this.utility = utility;
       this.emailService = emailService;
   }

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
        User user=utility.getUser(userId);
        //send email for the user
        emailService.sendEmail(user.getEmailId(),"Your Order placed successfully...",OrderMapper.orderMapperToOrderDto(order).toString());


        //  Clear the cart
            utility.clearCartForUser(userId);


            return OrderMapper.orderMapperToOrderDto(order);



    }



    @Override
    @Transactional
    public OrderDto PlaceOrderByCartId(User user ,long addressId,long cartId) {

        //Fetch cart items for the user
        Cart cartItem = utility.getCartByCartId(cartId);



        // Create a new Order entity
        Order order = new Order();

        order.setUserId(user.getUserId());
        order.setCancel(false);
        order.setOrderDate(LocalDate.now());

        order.setAddressId(addressId);

        // Calculate total price and total quantity, and associate order items
        float totalPrice = 0;
        int totalQuantity = 0;



            // Create an OrderItem entity for each cart item
            OrderItem orderItem=new OrderItem();

            orderItem.setBookId(cartItem.getBookId());
            orderItem.setPrice(cartItem.getTotalPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order); //set order item to the appropriate order

            // Add order item to the order
            order.getOrderItems().add(orderItem);

            // Update total price and total quantity
            totalPrice += cartItem.getTotalPrice();
            totalQuantity += cartItem.getQuantity();


        //  Set the total price and total quantity in the Order
        order.setTotalPrice(totalPrice);
        order.setQuantity(totalQuantity);


        orderRepository.save(order);


        //send email for the user
        emailService.sendEmail(user.getEmailId(),"Your Order placed successfilly...",OrderMapper.orderMapperToOrderDto(order).toString());

        //  Clear the cart
        utility.clearCartByCartId(cartId);

        return OrderMapper.orderMapperToOrderDto(order);

    }

//Cancel The Order
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

    //Get All Orders
    @Override
    @CircuitBreaker(name = "companyBreaker")//,fallbackMethod = "companyBreakerFallback")
    public List<OrderDto> getAllOrders() {
        List<Order> allOrders=orderRepository.findAll();
        return allOrders.stream().map(OrderMapper::orderMapperToOrderDto).collect(Collectors.toList());
    }

    public String companyBreakerFallback(){
        return "Services unavailable try after some time or refresh";
    }

    //Get All orders For the User
    @Override
    public List<OrderDto> getAllOrdersForUser(long userId) {
        List<Order> allOrdersOfUser=orderRepository.findAllByUserId(userId);
        return  allOrdersOfUser.stream().map(order -> OrderMapper.orderMapperToOrderDto(order)).collect(Collectors.toList());
    }
}
