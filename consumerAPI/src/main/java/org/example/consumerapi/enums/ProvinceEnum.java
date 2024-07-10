package org.example.consumerapi.enums;

public enum ProvinceEnum {
    PROVINCE ("Tỉnh"),
    CITY ("Thành phố");

    private String name;

    ProvinceEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean equalsName(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProvinceEnum that = (ProvinceEnum) o;
        return name.equals(that.name);
    }
}
