package cn.tx.springcloud.service.impl;

import cn.tx.springcloud.dao.DeptDao;
import cn.tx.springcloud.entities.Dept;
import cn.tx.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepeServiceImpl implements DeptService {

    @Autowired
    private DeptDao dao;

    @Override
    public boolean add(Dept dept) {
        return dao.addDept(dept);
    }

    @Override
    public Dept get(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Dept> list() {
        List<Dept> list = null;
        try {
            list = dao.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }



        return list;
    }
}
