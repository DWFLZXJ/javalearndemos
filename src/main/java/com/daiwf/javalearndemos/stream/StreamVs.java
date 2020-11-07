package com.daiwf.javalearndemos.stream;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @version [版本号，2020-11-7]
 * @文件名 StreamVs
 * @作者 daiwf
 * @创建时间 2020-11-7 15:48
 * @版权 Copyright daiwf. All Rights Reserved.
 * @描述 []
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class StreamVs {
    /*
     *1看看购物车中都有什么商品
     * 2、图书类的都给买
     * 3、其余商品买两件最贵的
     * 4、只需两件商品的名称和总价
     *
     */

    @Test
    public void odlCartHandle() {
        List<Sku> cartSkuList = CartService.getCartSkulist();
        //打印所有商品
        for (Sku sku : cartSkuList) {
            System.out.println(JSON.toJSONString(sku, true));
        }
        //图书类的过滤掉
        List<Sku> notBookList = new ArrayList<>();
        for (Sku sku : cartSkuList) {
            if (!sku.getSkuCategory().equals(SkuCategoryEnum.BOOKS)) {
                notBookList.add(sku);
                // System.out.println(JSON.toJSONString(sku,true));
            }
        }

        notBookList.sort(new Comparator<Sku>() {
            @Override
            public int compare(Sku o1, Sku o2) {
                if (o1.getSkuPrice() > o2.getSkuPrice()) {
                    return -1;
                } else if (o1.getSkuPrice() > o2.getSkuPrice()) {
                    return 1;
                }
                return 0;
            }
        });

        //top2
        List<Sku> top2SkuList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            top2SkuList.add(notBookList.get(i));
        }

        //求两件商品的总价
        Double money = 0.0;
        for (Sku sku : top2SkuList) {
            money += sku.getTotalPrice();
        }

        //获取两件商品的名称
        List<String> resultSkuNameList = new ArrayList<>();
        for (Sku sku : top2SkuList) {
            resultSkuNameList.add(sku.getSkuName());
        }
        //打印输出结果
        System.out.println(JSON.toJSONString(resultSkuNameList, true));
        System.out.println("商品总价：" + money);

    }

    //Stream流的方式进行操作
    public void newCartHandle() {
        AtomicReference<Double> money = new AtomicReference<>(Double.valueOf(0.0));
        List<String> resultSkuNameList = CartService.getCartSkulist()
                .stream()
                //1、打印商品信息
                .peek(sku -> {
                    System.out.println(JSON.toJSONString(sku, true));
                })
                .filter(sku -> !SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory()))
                .sorted(Comparator.comparing(Sku::getTotalPrice).reversed())
                //top2
                .limit(2)
                //累加商品总金额
                .peek(sku -> money.set(money.get() + sku.getTotalPrice()))
                .map(sku -> sku.getSkuName())
                .collect(Collectors.toList());
        System.out.println(JSON.toJSONString(resultSkuNameList, true));
        System.out.println("商品总价：" + money);
    }


}
