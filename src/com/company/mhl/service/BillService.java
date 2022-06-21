package com.company.mhl.service;

import com.company.mhl.dao.BillDAO;
import com.company.mhl.dao.DiningTableDAO;
import com.company.mhl.dao.MultiTableDAO;
import com.company.mhl.domain.Bill;
import com.company.mhl.domain.DiningTable;
import com.company.mhl.domain.MultiTableBean;

import java.util.List;
import java.util.UUID;

public class BillService {
    private DiningTableDAO diningTableDAO = new DiningTableDAO();
    private BillDAO billDAO = new BillDAO();
    private MenuService menuService = new MenuService();
    private DiningTableService diningTableService = new DiningTableService();
    private MultiTableDAO multiTableDAO = new MultiTableDAO();
    //1.生成账单
    //2.更新餐桌状态
    public boolean orderMenu(int menuId, int nums, int diningTableId) {
        String billId = UUID.randomUUID().toString();
        int update = billDAO.update("insert into bill values (null, ?, ?,?,?,?,now(), '未结账')",
                billId, menuId, nums, menuService.getMenuById(menuId).getPrice() * nums, diningTableId);
        if (update <= 0) {
            return false;
        }
        return diningTableService.updateDiningTableState(diningTableId, "就餐中");
    }

    // 返回所有的账单,给view调用
    public List<Bill> list() {
        return billDAO.queryMulti("select * from bill", Bill.class);
    }
    // 返回所有的账单并带有菜品名,给view调用
    public List<MultiTableBean> list2() {
        return multiTableDAO.queryMulti("select bill.*, name " +
                "from bill, menu " +
                "where bill.menuId = menu.id", MultiTableBean.class);

    }

    //返回某个餐桌未结账的账单 给view查询
    public boolean hasPayBillByDiningTableId (int diningTableId) {
        Bill bill = billDAO.querySingle("select * from bill where diningTableId = ? and state = '未结账' limit 0, 1", Bill.class, diningTableId);
        return bill != null;
    }
    //修改bill表的状态
    public boolean payBill(int diningTableId, String payMode) {
        //1. 修改bill表
        int update = billDAO.update("update bill set state=? where diningTableId = ? and state = '未结账'", payMode, diningTableId);
        if (update <= 0) {
            return false;
        }
        if (!diningTableService.updateDiningTableToFree(diningTableId, "空")) {
            return false;
        }
        return true;
    }
}
