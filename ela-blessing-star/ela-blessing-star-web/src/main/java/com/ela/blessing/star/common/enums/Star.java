package com.ela.blessing.star.common.enums;

public enum Star {
    CAIXUKUN("1","蔡徐坤"),
    DUANAOJUAN("2","段奥娟"),
    FANCHENGCHENG("3","范丞丞"),
    HUACHENGYU("4","华晨宇"),
    LUHAN("5", "鹿晗"),
    LAIMEIYUN("6","赖美云"),
    LIUYUNING("7", "摩登兄弟"),
    LUOYUNXI("8", "罗云熙"),
    WUYIFAN("9", "吴亦凡"),
    WUXUANYI("10", "吴宣仪"),
    WANGZIYI("11", "王子异"),
    YOUCHANGJING("12", "尤长靖"),
    YANGYUNQING("13", "杨芸晴"),
    YANGYANG("14", "杨洋"),
    YANYALUN("15", "炎亚纶"),
    ZHANGYUNLEI("16", "张云雷"),
    ZHANGZINING("17", "张紫宁"),
    ZHUYILON("18", "朱一龙"),
    ZHUZHENGTING("19", "朱正廷")
    ;

    public String id;
    public String value;

    Star(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static Star fromId(String id) {
        for (Star t : Star.values()) {
            if (t.id.equals(id)) {
                return t;
            }
        }
        return null;
    }

    public static Star fromName(String name) {
        for (Star t : Star.values()) {
            if (t.name().equals(name)) {
                return t;
            }
        }
        return null;
    }
}
