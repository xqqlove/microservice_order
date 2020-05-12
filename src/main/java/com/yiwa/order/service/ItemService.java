package com.yiwa.order.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yiwa.order.entity.Item;
import com.yiwa.order.feign.ItemFeignClient;
import com.yiwa.order.properties.OrderProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private RestTemplate restTemplate;

    //    @Value("${myspcloud.item.url}")
//    private String itemUrl;
//    @Autowired
//    private OrderProperties orderProperties;

    //    @Autowired
//    private DiscoveryClient discoveryClient;
    @Autowired
    private ItemFeignClient itemFeignClient;

//    @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod")
    public Item queryItemById(Long id) {
        // 该方法走eureka注册中心调用(去注册中心根据app-item查找服务，这种方式必须先开启负载均衡@LoadBalanced)
//        String serviceId = "app-item";
//        List<ServiceInstance> serviceInstances= (List<ServiceInstance>) this.discoveryClient.getApplication(serviceId);
//        if (serviceInstances==null) return null;
//        ServiceInstance instance = serviceInstances.get(0);
//        instance.getHost();
//        instance.getPort();
        String itemUrl = "http://app-item/item/{id}";
//        Item item = this.restTemplate.getForObject(itemUrl, Item.class, id);
        Item item = this.itemFeignClient.queryItemById(id);
        System.out.println("订单系统调用商品服务，item：" + item);
        return item;
    }

    /**
     * 请求失败执行的方法
     *
     * @param id
     * @return
     */
//    public Item queryItemByIdFallbackMethod(Long id) {
//        return new Item(id, "查询商品信息出错!", null, null, null);
//    }
}
