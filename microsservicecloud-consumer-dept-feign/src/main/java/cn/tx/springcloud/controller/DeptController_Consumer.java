package cn.tx.springcloud.controller;


import cn.tx.springcloud.entities.Dept;
import cn.tx.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class DeptController_Consumer {

    @Autowired
    private DeptClientService service;


    @RequestMapping(value = "/consumer/dept/add")
    public boolean addDept(Dept dept){
        return this.service.add(dept);
    }


    @RequestMapping(value = "/consumer/dept/get/{id}&{dname}")
    public Dept get(@PathVariable("id") Long id,@PathVariable("dname") String dname){
        System.out.println("feign:id=="+id+"dname=="+dname);
        return this.service.get(id, dname);
    }

    @RequestMapping(value = "/consumer/dept/list")
    public List<Dept> list(){
        return this.service.list();
    }


}
