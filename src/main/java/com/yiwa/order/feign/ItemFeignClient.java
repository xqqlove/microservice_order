package com.yiwa.order.feign;

import com.yiwa.order.entity.Item;
import com.yiwa.order.fallback.ItemServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 声明这是一个feign客户端 并指明服务id
 */
@FeignClient(value = "app-item" ,fallback = ItemServiceFallback.class)
public interface ItemFeignClient {

    @RequestMapping(value = "/item/{id}",method = RequestMethod.GET)
    Item queryItemById(@PathVariable("id") Long id);
}
