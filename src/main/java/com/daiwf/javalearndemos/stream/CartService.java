package com.daiwf.javalearndemos.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * @version [版本号，2020-11-7]
 * @文件名 CartService
 * @作者 daiwf
 * @创建时间 2020-11-7 15:55
 * @版权 Copyright daiwf. All Rights Reserved.
 * @描述 [造数据用]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CartService {
    private static List<Sku> cartlist =new ArrayList<Sku>(){
        {
            add(new Sku(12,"手机",40000.00,2,80000.00,SkuCategoryEnum.ELECTRONICS));
            add(new Sku(13,"时间简史",20000.00,2,40000.00,SkuCategoryEnum.BOOKS));
            add(new Sku(15,"java基础",20000.00,2,40000.00,SkuCategoryEnum.BOOKS));
            add(new Sku(16,"redis入门到放弃",20000.00,2,40000.00,SkuCategoryEnum.BOOKS));
            add(new Sku(17,"nginx全指南",20000.00,2,40000.00,SkuCategoryEnum.BOOKS));
            add(new Sku(14,"跑步机",40000.00,3,120000.00,SkuCategoryEnum.SPORTS));
        }
    };

    public static List<Sku> getCartSkulist(){
        return  cartlist;
    }



}
