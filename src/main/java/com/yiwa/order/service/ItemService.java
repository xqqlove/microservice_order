package com.yiwa.order.service;

import com.yiwa.order.entity.Item;
import com.yiwa.order.properties.OrderProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ItemService {

    @Autowired
    private RestTemplate restTemplate;

//    @Value("${myspcloud.item.url}")
//    private String itemUrl;
    @Autowired
    private OrderProperties orderProperties;

    public Item queryItemById(Long id){
        return this.restTemplate.getForObject(orderProperties.getItem().getUrl()+id,Item.class);
    }
}
