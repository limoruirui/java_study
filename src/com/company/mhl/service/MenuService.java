package com.company.mhl.service;

import com.company.mhl.dao.MenuDAO;
import com.company.mhl.domain.Menu;

import java.util.List;

public class MenuService {
    private MenuDAO menuDAO = new MenuDAO();

    public List<Menu> list() {
        List<Menu> menus = menuDAO.queryMulti("select * from menu", Menu.class);
        return menus;
    }

    //根据id返回Menu对象
    public Menu getMenuById (int id) {
        return menuDAO.querySingle("select * from menu where id = ?", Menu.class, id);
    }
}
