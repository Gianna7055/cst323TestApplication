package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import com.gcu.data.entity.OrderEntity;
import com.gcu.data.repository.OrdersRepository;
import com.gcu.model.OrderModel;

public class OrdersBusinessService implements OrdersBusinessInterface
{
    @Autowired
    OrdersRepository service;

    public void test()
    {
        System.out.println("Hello from the OrdersBusinessService");
    }

    public List<OrderModel> getOrders()
    {
        List<OrderModel> ordersDomain = new ArrayList<OrderModel>();
        List<OrderEntity> ordersEntity = (List<OrderEntity>) service.findAll();
        for (OrderEntity entity : ordersEntity)
        {
            ordersDomain.add(new OrderModel(entity.getId(), entity.getOrderNo(), entity.getProductName(),
                    entity.getPrice(), entity.getQuantity()));
        }
        // Return all orders
        return ordersDomain;
    }

    public void initialize()
    {
        System.out.println("Initialize method in OrdersBusinessService");
    }

    public void destroy()
    {
        System.out.println("Destroy method in OrdersBusinessService");
    }
}
