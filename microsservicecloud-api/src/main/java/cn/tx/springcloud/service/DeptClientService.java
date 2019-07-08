package cn.tx.springcloud.service;

import cn.tx.springcloud.entities.Dept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

//@FeignClient(value = "MICROSSERVICECLOUD-DEPT")
@FeignClient(value = "MICROSSERVICECLOUD-DEPT",fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {
    @RequestMapping(value = "/dept/add",method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept);
    @RequestMapping(value = "/dept/get/{id}&{dname}",method = RequestMethod.GET)
    public Dept get(@PathVariable("id") Long id, @PathVariable("dname") String dname);

    @RequestMapping(value = "/dept/list",method = RequestMethod.GET)
    public List<Dept> list();


}