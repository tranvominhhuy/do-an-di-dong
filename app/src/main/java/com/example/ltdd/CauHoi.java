package com.example.ltdd;

public class CauHoi {
    public static final String TABLE_NAME="cau_hoi";

    public static final String COL_ID="id";
    public static final String COL_NOIDung="noi_dung";
    public static final String COL_LINHVUC_ID="linh_vuc_id";
    public static final String COL_PHUONG_AN_A = "phuong_an_a";
    public static final String COL_PHUONG_AN_B = "phuong_an_b";
    public static final String COL_PHUONG_AN_C = "phuong_an_c";
    public static final String COL_PHUONG_AN_D = "phuong_an_d";
    public static final String COL_DAP_AN = "dap_an";

    public static final String TAO_BANG="CREATE TABLE " + TABLE_NAME +" ( "
            + COL_ID +" INTEGER PRIMARY KEY,"
            + COL_NOIDung +" TEXT,"
            + COL_LINHVUC_ID +" INTEGER,"
            + COL_PHUONG_AN_A +" TEXT,"
            + COL_PHUONG_AN_B +" TEXT,"
            + COL_PHUONG_AN_C +" TEXT,"
            + COL_PHUONG_AN_D +" TEXT,"
            + COL_DAP_AN +" TEXT )";

    public static final String XOA_BANG="DROP TABLE IF EXIST "+TABLE_NAME;

    private int id;
    private String noi_dung;
    private int linh_vuc_id;
    private String phuong_an_a;
    private String phuong_an_b;
    private String phuong_an_c;
    private String phuong_an_d;
    private String dap_an;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoi_dung() {
        return noi_dung;
    }

    public void setNoi_dung(String noi_dung) {
        this.noi_dung = noi_dung;
    }

    public int getLinh_vuc_id() {
        return linh_vuc_id;
    }

    public void setLinh_vuc_id(int linh_vuc_id) {
        this.linh_vuc_id = linh_vuc_id;
    }

    public String getPhuong_an_a() {
        return phuong_an_a;
    }

    public void setPhuong_an_a(String phuong_an_a) {
        this.phuong_an_a = phuong_an_a;
    }

    public String getPhuong_an_b() {
        return phuong_an_b;
    }

    public void setPhuong_an_b(String phuong_an_b) {
        this.phuong_an_b = phuong_an_b;
    }

    public String getPhuong_an_c() {
        return phuong_an_c;
    }

    public void setPhuong_an_c(String phuong_an_c) {
        this.phuong_an_c = phuong_an_c;
    }

    public String getPhuong_an_d() {
        return phuong_an_d;
    }

    public void setPhuong_an_d(String phuong_an_d) {
        this.phuong_an_d = phuong_an_d;
    }

    public String getDap_an() {
        return dap_an;
    }

    public void setDap_an(String dap_an) {
        this.dap_an = dap_an;
    }

    public CauHoi (String noi_dung, int linh_vuc_id, String phuong_an_a, String phuong_an_b, String phuong_an_c, String phuong_an_d, String dap_an)
    {
        this.id = 0;
        this.noi_dung = noi_dung;
        this.linh_vuc_id = linh_vuc_id;
        this.phuong_an_a = phuong_an_a;
        this.phuong_an_b = phuong_an_b;
        this.phuong_an_c = phuong_an_c;
        this.phuong_an_d = phuong_an_d;
        this.dap_an = dap_an;
    }
}

