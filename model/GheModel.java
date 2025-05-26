package model;



import java.util.*;

public class GheModel implements Subject2 {
    private final String maGhe;
    private boolean daDat = false;
    private final List<Observer2> observers = new ArrayList<>();

    public GheModel(String maGhe) {
        this.maGhe = maGhe;
    }

    public String getMaGhe() {
        return maGhe;
    }

    public boolean isDaDat() {
        return daDat;
    }

    public void datGhe() {
        this.daDat = true;
        thongBaoCapNhat2();
    }

    @Override
    public void themObserver2(Observer2 obs) {
        observers.add(obs);
    }

    @Override
    public void xoaObserver2(Observer2 obs) {
        observers.remove(obs);
    }

    @Override
    public void thongBaoCapNhat2() {
        for (Observer2 obs : observers) {
            obs.capNhatGhe();
        }
    }
}
