package com.bookstore.Order.mapper;

import com.bookstore.Order.dto.OrderDto;
import com.bookstore.Order.external.Address;
import com.bookstore.Order.external.User;
import com.bookstore.Order.model.Order;
import com.bookstore.Order.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    private static Utility utility;

    @Autowired
    public void setUtility(Utility utility) {
        OrderMapper.utility = utility;
    }


    public static OrderDto orderMapperToOrderDto(Order order){
        OrderDto orderDto=new OrderDto();
       // orderDto.setUserId(order.getUserId());
        System.out.println(order.getOrderDate());
        orderDto.setOrderDate(order.getOrderDate());
        System.out.println(order.getUserId());
        long id=order.getUserId();
        User user=utility.getUser(id);
        //orderDto.setUser(user);
        orderDto.setName(user.getFirstName()+" "+user.getLastName());

        orderDto.setEmail(user.getEmailId());
        List<Address> addresses = user.getAddress();
        Address userAddress = addresses.stream()
                .filter(adres -> adres.getId() == order.getAddressId())
                .findFirst()
                .orElse(null);
        orderDto.setAddress(userAddress);
        orderDto.setQuantity(order.getQuantity());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setOrderItems(order.getOrderItems());

        return orderDto;


    }
}
