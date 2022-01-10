package com.config;

public enum PowerEnum {
    ADMIN(1),
    ORDER_MANAGE(2),
    PRODUCT_MANAGE(3);

    public  int value;


     PowerEnum(int i){
        this.value = i;
    }



}
