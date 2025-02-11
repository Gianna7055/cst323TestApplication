package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import com.gcu.model.OrderModel;

@Service
public class OrdersDataService implements DataAccessInterface<OrderModel>
{
    JdbcTemplate jdbcTemplateObject;
    String query = "";
    String databaseName = "orders";

    public OrdersDataService(DataSource dataSource)
    {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public List<OrderModel> findAll()
    {
        query = "SELECT * FROM " + databaseName;
        List<OrderModel> orders = new ArrayList<OrderModel>();
        try
        {
            // Execute SQL Query and loop over results
            SqlRowSet srs = jdbcTemplateObject.queryForRowSet(query);
            while (srs.next())
            {
                orders.add(new OrderModel(srs.getLong("ID"), srs.getString("ORDER_NO"), srs.getString("PRODUCT_NAME"),
                        srs.getFloat("PRICE"), srs.getInt("QUANTITY")));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return orders;
    }

    public OrderModel findById(int id)
    {
        return null;
    }

    public boolean create(OrderModel order)
    {
        query = "INSERT INTO " + databaseName + "(ORDER_NO, PRODUCT_NAME, PRICE, QUANTITY) VALUES(?, ?, ?, ?)";
        try
        {
            // Execute SQL Insert
            jdbcTemplateObject.update(query, order.getOrderNo(), order.getProductName(), order.getPrice(),
                    order.getQuantity());
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(OrderModel order)
    {
        return true;
    }

    public boolean delete(OrderModel order)
    {
        return true;
    }
}
