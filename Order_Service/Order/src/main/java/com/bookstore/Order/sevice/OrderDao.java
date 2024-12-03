package com.bookstore.Order.sevice;

import com.bookstore.Order.dto.OrderDto;
import com.bookstore.Order.external.User;
import com.bookstore.Order.model.Order;

import java.util.List;

public interface OrderDao {

    public OrderDto PlaceOrder(long userId,long addressId);
    public OrderDto PlaceOrderByCartId(User user ,long addressId,long cartId);
    public boolean cancelOrder(long orderId);
    public List<OrderDto> getAllOrders();
    public List<OrderDto> getAllOrdersForUser(long userId);


}
