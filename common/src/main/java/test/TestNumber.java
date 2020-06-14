package test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class TestNumber {

    /*
     * 计算公式 求百分比 XXXX={XXX1-XXX2}÷XXX2×100
     */
    public static BigDecimal average(BigDecimal BigDecimal1, BigDecimal BigDecimal2) {
        BigDecimal bd3 = new BigDecimal(100);
        DecimalFormat df = new DecimalFormat("######0.0000");
        BigDecimal d = BigDecimal1.subtract(BigDecimal2);
        System.out.println("d=" + d);
        BigDecimal f = d.divide(BigDecimal2, 6);
        System.out.println("f=" + f);
        BigDecimal e = d.divide(BigDecimal2, 6, BigDecimal.ROUND_HALF_UP);
        System.out.println("e=" + e);
        BigDecimal d2ToBigDeWithPre = new BigDecimal(df.format(e.multiply(bd3).doubleValue()));
        return d2ToBigDeWithPre;
    }

    /*
     * 计算公式 求百分比 XXXX={XXX1-XXX2}÷XXX2×100
     */
    public static BigDecimal computational(BigDecimal BigDecimal1, BigDecimal BigDecimal2) {
        BigDecimal bd3 = new BigDecimal(100);
        DecimalFormat df = new DecimalFormat("######0.0000");
        BigDecimal d = BigDecimal1.subtract(BigDecimal2);
        System.out.println("d=" + d);
        BigDecimal f = d.divide(BigDecimal2, 6);
        System.out.println("f=" + f);
        BigDecimal e = d.divide(BigDecimal2, 6, BigDecimal.ROUND_HALF_UP);
        System.out.println("e=" + e);
        BigDecimal d2ToBigDeWithPre = new BigDecimal(df.format(e.multiply(bd3).doubleValue()));
        return d2ToBigDeWithPre;
    }

    /**
     * @Title: main @Description: TODO(这里用一句话描述这个方法的作用) @param @param args
     * 设定文件 @return void 返回类型 @throws
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        double b = 1.9186;
        double c = 1.9201;
        double a = 100;
        BigDecimal bd1 = new BigDecimal(b);
        BigDecimal bd2 = new BigDecimal(c);
        BigDecimal bd3 = new BigDecimal(a);
        DecimalFormat df = new DecimalFormat("######0.0000");

        BigDecimal d = bd1.subtract(bd2);
        BigDecimal e = d.divide(bd2, 4, BigDecimal.ROUND_HALF_UP);
        BigDecimal d2ToBigDeWithPre = new BigDecimal(df.format(e.multiply(bd3).doubleValue()));
        System.out.println(df.format(d2ToBigDeWithPre));
        System.out.println(d2ToBigDeWithPre);

        System.out.println(new BigDecimal((df.format(2.76666666))));

        DecimalFormat df1 = new DecimalFormat("######0.00");

        double d1 = 3.23456;
        double d2 = 0.0;
        double d3 = 2.0;
        df1.format(d1);
        df1.format(d2);
        df1.format(d3);
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);

        System.out.println("ssssssssssssssss" + computational(new BigDecimal(3124.2039), new BigDecimal(3469.0660)));
        ;

    }
}
