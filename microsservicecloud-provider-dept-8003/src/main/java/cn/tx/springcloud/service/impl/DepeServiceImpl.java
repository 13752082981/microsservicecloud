package cn.tx.springcloud.service.impl;

import cn.tx.springcloud.dao.DeptDao;
import cn.tx.springcloud.entities.Dept;
import cn.tx.springcloud.service.DeptService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepeServiceImpl implements DeptService {

    @Autowired
    private DeptDao dao;

    @Override
    @Transactional
    @LcnTransaction
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
