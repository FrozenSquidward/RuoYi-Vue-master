package com.bingfeng.pp.goushi;

import java.util.HashMap;
import java.util.Map;

public class QuMap {

    public static final Map<Object,String> QU_TYPE = new HashMap<Object,String>(){{
        put("0", "YESNO"); //是非题
        put("1", "RADIO"); //单选题
        put("2", "CHECKBOX"); //多选题
        put("3", "FILLBLANK"); //填空题
        put("6", "MULTIFILLBLANK"); // 多项填空题
        put("5", "ANSWER"); // 多行填空题
//        put("6", "BIGQU"); // 大题
        put("7", "ENUMQU"); // 枚举题
        put("10", "SCORE"); // 评分题
        put("11", "ORDERQU"); // 排序题

//        put("11", "CHENRADIO"); // 矩阵单选题
//        put("12", "CHENFBK"); // 矩阵填空题
//        put("13", "CHENCHECKBOX"); // 矩阵多选题
//        put("14", "COMPCHENRADIO"); // 复合矩阵单选题

        put("16", "PAGETAG"); // 分页标记
        put("17", "PARAGRAPH"); // 分段标记
        put("18", "COMPCHECKBOX"); // 复合多选
    }};


    public static final Map<Integer,String> YES_NO_OPTION = new HashMap<Integer,String>(){{
        put(0, "OPTION1");
    }};
}
