package com.gcu.controller.API;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.gcu.model.OrderModel;

import com.gcu.business.OrdersBusinessInterface;
import com.gcu.business.OrdersBusinessService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/service")
public class OrdersRestController
{
    OrdersBusinessInterface service = new OrdersBusinessService();

    @GetMapping(path = "/getjson", produces ={ MediaType.APPLICATION_JSON_VALUE })
    public List<OrderModel> getOrdersAsJson()
    {
        return service.getOrders();
    }

    @GetMapping(path = "/getxml", produces ={ MediaType.APPLICATION_XML_VALUE })
    public List<OrderModel> getOrdersAsXml()
    {
        return service.getOrders();
    }
}
