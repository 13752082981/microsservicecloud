package cn.tx.springcloud.service;

import cn.tx.springcloud.entities.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component//必须添加
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public boolean add(Dept dept) {
                return false;
            }

            @Override
            public Dept get(Long id, String dname) {
                return new Dept().setDeptno(id).setDname(dname).setDb_source("该ID:"+id+"没有对应的信息---服务降级provider 已经关闭");
            }

            @Override
            public List<Dept> list() {
                return null;
            }
        };
    }
}
