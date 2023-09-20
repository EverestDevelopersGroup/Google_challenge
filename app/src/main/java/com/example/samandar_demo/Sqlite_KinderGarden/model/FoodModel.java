package com.example.samandar_demo.Sqlite_KinderGarden.model;

public class FoodModel {

    private int id;
    private String bogcha_name;
    private String  viloyat ;
    private String  xizmatlar ;
    private String  kuntartib;
    private String  tili;
    private String  ratsion ;
    private String bola_soni ;
    private String  tolov_oy ;
    private String ustunlik_jihat;
    private String  bonus ;
    private String xavfsizlik;
    private String  raqam ;
    private String qoshimca_malumot;

    private byte[] image;

    public String getBogcha_name() {
        return bogcha_name;
    }

    public void setBogcha_name(String bogcha_name) {
        this.bogcha_name = bogcha_name;
    }

    public String getViloyat() {
        return viloyat;
    }

    public void setViloyat(String viloyat) {
        this.viloyat = viloyat;
    }

    public String getXizmatlar() {
        return xizmatlar;
    }

    public void setXizmatlar(String xizmatlar) {
        this.xizmatlar = xizmatlar;
    }

    public String getKuntartib() {
        return kuntartib;
    }

    public void setKuntartib(String kuntartib) {
        this.kuntartib = kuntartib;
    }

    public String getTili() {
        return tili;
    }

    public void setTili(String tili) {
        this.tili = tili;
    }

    public String getRatsion() {
        return ratsion;
    }

    public void setRatsion(String ratsion) {
        this.ratsion = ratsion;
    }

    public String getBola_soni() {
        return bola_soni;
    }

    public void setBola_soni(String bola_soni) {
        this.bola_soni = bola_soni;
    }

    public String getTolov_oy() {
        return tolov_oy;
    }

    public void setTolov_oy(String tolov_oy) {
        this.tolov_oy = tolov_oy;
    }

    public String getUstunlik_jihat() {
        return ustunlik_jihat;
    }

    public void setUstunlik_jihat(String ustunlik_jihat) {
        this.ustunlik_jihat = ustunlik_jihat;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getXavfsizlik() {
        return xavfsizlik;
    }

    public void setXavfsizlik(String xavfsizlik) {
        this.xavfsizlik = xavfsizlik;
    }

    public String getRaqam() {
        return raqam;
    }

    public void setRaqam(String raqam) {
        this.raqam = raqam;
    }

    public String getQoshimca_malumot() {
        return qoshimca_malumot;
    }

    public void setQoshimca_malumot(String qoshimca_malumot) {
        this.qoshimca_malumot = qoshimca_malumot;
    }

    public FoodModel(int id, String bogcha_name , String viloyat , String xizmatlar , String kuntartib , String tili , String ratsion , String bola_soni , String tolov_oy , String ustunlik_jihat , String bonus , String xavfsizlik , String raqam , String qoshimca_malumot , byte[] image) {
        this.id = id;
       this.bogcha_name = bogcha_name;
        this.viloyat = viloyat;
        this.xizmatlar = xizmatlar;
        this.kuntartib = kuntartib;
        this.tili = tili;
        this.ratsion = ratsion;
        this.bola_soni = bola_soni;
        this.tolov_oy = tolov_oy;
        this.ustunlik_jihat = ustunlik_jihat;
        this.bonus = bonus;
        this.xavfsizlik = xavfsizlik;
        this.raqam = raqam;
        this.qoshimca_malumot = qoshimca_malumot;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
