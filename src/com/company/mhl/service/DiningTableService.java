package com.company.mhl.service;

import com.company.mhl.dao.DiningTableDAO;
import com.company.mhl.domain.DiningTable;

import java.util.List;

public class DiningTableService {
    private DiningTableDAO diningTableDAO = new DiningTableDAO();
    public List<DiningTable> list() {
        List<DiningTable> diningTables = diningTableDAO.queryMulti("select id, state from diningTable", DiningTable.class);
        return diningTables;
    }
    //根据餐桌id 查询对应的diningtable对象
    public DiningTable getDiningTableById (int id) {
        return diningTableDAO.querySingle("select * from diningTable where id = ?", DiningTable.class, id);
    }
    //如果餐桌可以预定的话 调用方法 对其状态进行更新
    public boolean orderDiningTable(int id, String orderName, String orderTel) {
        int update = diningTableDAO.update("update diningTable set state='已经预定', orderName=?, orderTel=? where id=?", orderName, orderTel, id);
        return update > 0;
    }
    //更新餐桌状态的方法
    public boolean updateDiningTableState(int id, String state) {
        int update = diningTableDAO.update("update diningTable set state = ? where id = ?", state, id);
        return update > 0;
    }
    //结账后更新餐桌状态
    public boolean updateDiningTableToFree(int id, String state) {
        int update = diningTableDAO.update("update diningTable set state = ?, orderName='',orderTel='' where id = ?", state, id);
        return update > 0;
    }
}
