package main.list;

import domain.Main;

import java.util.ArrayList;

public class MainService {
    private MainDAO dao;
    private static final MainService instance = new MainService();
    private MainService() {
        dao = new MainDAO();
    }
    public static MainService getInstance() {
        return instance;
    }

    public ArrayList<Main> mainPageS() {
        return dao.mainPage();
    }

}
