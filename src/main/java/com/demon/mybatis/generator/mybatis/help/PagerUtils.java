//package com.demon.mybatis.generator.mybatis.help;
//
//import com.github.pagehelper.Page;
//import com.xubei.framework.common.entity.Pager;
//
//public class PagerUtils {
//
//    public static <T> Pager<T> pageToPager(Page<T> page, Integer pageNo, Integer pageSize) {
//        Long totle;//总条数
//
//        totle = page.getTotal();
//
//        Pager<T> pager;
//        if (totle != 0) {
//            pager = new Pager<>(pageSize, totle.intValue());
//        } else {
//            pager = new Pager<>();
//            pager.setTotalRows(0);
//        }
//        pager.setData(page.getResult());
//        pager.setPageSize(pageSize);
//        pager.setTotalRows(totle.intValue());
//        pager.setCurrentPage(pageNo);
//
//        return pager;
//    }
//
////    static class Test {
////        public static void main(String[] args) {
////            Page<WxUser> page = new Page<>();
////            page.setTotal(100L);
////            page.set(0, new WxUser());
////
////            Pager<WxUser> pager = PagerUtils.pageToPager(page, 1, 100);
////            System.out.println(pager.toString());
////        }
////    }
//}
