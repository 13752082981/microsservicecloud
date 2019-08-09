package cn.tx.springcloud.controller;

import cn.tx.springcloud.entities.Dept;
import cn.tx.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService service;

    @RequestMapping(value = "/dept/add",method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept){
        System.out.println("+++++++++++++");
        return service.add(dept);
    }
    @RequestMapping(value = "/dept/get/{id}&{username}",method = RequestMethod.GET)
    public Dept get(@PathVariable("id") Long id,@PathVariable("username") Long username){
        System.out.println(id);
        System.out.println(username);
        return service.get(id);
    }

    @RequestMapping(value = "/dept/list",method = RequestMethod.GET)
    public List<Dept> list(){
        List<Dept> list = service.list();

        return list;
    }
}
