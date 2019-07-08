package cn.tx.springcloud.controller;

import cn.tx.springcloud.entities.Dept;
import cn.tx.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService service;


    @RequestMapping(value = "/dept/get/{id}&{dname}",method = RequestMethod.GET)
    //一旦调用服务方法失败并抛出了错误信息后,会自动调用@HystrixCommand标注的fallbackMethod调用类中指定的方法
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public Dept get(@PathVariable("id") Long id,@PathVariable("dname") String dname){
        System.out.println("id=="+id+"dname="+dname);
        Dept dept = service.get(id);
        if(dept==null){
            throw new RuntimeException("该ID:"+id+"没有对应的数据");
        }
        return  dept;
    }

    public Dept processHystrix_Get(@PathVariable("id") Long id,@PathVariable("dname") String dname){

        return new Dept().setDeptno(id).setDname(dname).setDb_source("该ID:"+id+"没有对应的信息");
    }

    @RequestMapping(value = "/dept/list",method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "processHystrix_List")
    public List<Dept> list(){
        List<Dept> list = service.list();
        if(true){
            throw new RuntimeException("没有对应的数据");
        }
        return list;
    }

    public List<Dept> processHystrix_List(){
      Dept dept =   new Dept().setDeptno(1111l).setDname("333").setDb_source("222");
        List<Dept> list = new ArrayList<>();
        list.add(dept);
        return list;
    }
}
