package com.fqy.qzdtest;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class ExcelReadTest {

    public static void main(String[] args) {

        String filename = "C:\\Users\\qzd\\Desktop\\优惠券码库20210308032404801.xlsx";

        List<UserCoupon> list = new ArrayList<>();

        /*EasyExcel.read(filename, UserCoupon.class, new ReadListener() {
            @Override
            public void onException(Exception e, AnalysisContext analysisContext) throws Exception {

            }

            @Override
            public void invokeHead(Map map, AnalysisContext analysisContext) {
                Set set = map.keySet();
                for (Object o : set) {
                    System.out.println(o.toString()+":"+map.get(o));
                }
            }

            @Override
            public void invoke(Object o, AnalysisContext analysisContext) {
                System.out.println("解析一条Student对象：" + JSON.toJSONString(o));
                list.add((UserCoupon) o);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }

            @Override
            public boolean hasNext(AnalysisContext analysisContext) {
                return false;
            }
        }).sheet().doRead();*/

        EasyExcel.read(filename, UserCoupon.class,new AnalysisEventListener() {
            @Override
            public void invoke(Object o, AnalysisContext analysisContext) {
                System.out.println("解析一条Student对象：" + JSON.toJSONString(o));
                list.add((UserCoupon) o);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet().doRead();

        list.forEach(u->System.out.println(u.toString()));
    }
}
