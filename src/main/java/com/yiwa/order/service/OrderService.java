package com.yiwa.order.service;


import com.yiwa.order.entity.Item;
import com.yiwa.order.entity.Order;
import com.yiwa.order.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    private static final Map<String, Order> ORDER_DATA=new HashMap<>();
    static {
        Order order=new Order();
        order.setOrderId("202005110001");
        order.setCreateDate(new Date());
        order.setUpdateDate(new Date());
        order.setUserId(1L);
        List<OrderDetail> orderDetails=new ArrayList<>();

        Item item=new Item();
        item.setId(1L);
        orderDetails.add(new OrderDetail(order.getOrderId(),item));
        item = new Item(); // 构造第二个商品数据
        item.setId(2L);
        orderDetails.add(new OrderDetail(order.getOrderId(), item));
        order.setOrderDetailList(orderDetails);

        ORDER_DATA.put(order.getOrderId(),order);
    }

    @Autowired
    private ItemService itemService;

    /**
     * 根据订单id查询订单数据
     * @param orderId
     * @return
     */
    public Order queryOrderById(String orderId){
        Order order=ORDER_DATA.get(orderId);
        if (null==order) return null;
        List<OrderDetail> orderDetails=order.getOrderDetailList();
        for (OrderDetail o:orderDetails) {
            //通过商品微服务查询商品详细数据
            Item item = this.itemService.queryItemById(o.getItem().getId());
            if (null==item) continue;
            o.setItem(item);
        }
        return order;
    }

}
