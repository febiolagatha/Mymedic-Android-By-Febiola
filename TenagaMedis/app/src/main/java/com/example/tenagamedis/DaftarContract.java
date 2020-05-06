package com.example.tenagamedis;

import android.provider.BaseColumns;

public class DaftarContract {

    private DaftarContract() {}

    public static final class DaftarEntry implements BaseColumns {
        public static final String TABLE_NAME = "table_name";
        public static final String COLUMN_1 = "Nama";
        public static final String COLUMN_2 = "Nomor";
        public static final String COLUMN_3 = "Penyakit";
        public static final String COLUMN_4 = "Kosasih";
        public static final String COLUMN_5 = "timestamp";
    }


}
