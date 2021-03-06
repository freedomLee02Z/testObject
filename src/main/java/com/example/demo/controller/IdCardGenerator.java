package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class IdCardGenerator {
    public static final Map<String, Integer> areaCode = new HashMap<String, Integer>();

    static {
        IdCardGenerator.areaCode.put("北京市", 110000);
        IdCardGenerator.areaCode.put("市辖区", 110100);
        IdCardGenerator.areaCode.put("东城区", 110101);
        IdCardGenerator.areaCode.put("西城区", 110102);
        IdCardGenerator.areaCode.put("崇文区", 110103);
        IdCardGenerator.areaCode.put("宣武区", 110104);
        IdCardGenerator.areaCode.put("朝阳区", 110105);
        IdCardGenerator.areaCode.put("丰台区", 110106);
        IdCardGenerator.areaCode.put("石景山区", 110107);
        IdCardGenerator.areaCode.put("海淀区", 110108);
        IdCardGenerator.areaCode.put("门头沟区", 110109);
        IdCardGenerator.areaCode.put("房山区", 110111);
        IdCardGenerator.areaCode.put("通州区", 110112);
        IdCardGenerator.areaCode.put("顺义区", 110113);
        IdCardGenerator.areaCode.put("昌平区", 110114);
        IdCardGenerator.areaCode.put("大兴区", 110115);
        IdCardGenerator.areaCode.put("怀柔区", 110116);
        IdCardGenerator.areaCode.put("平谷区", 110117);
    }

    /**
     * 生成方法
     *
     * @return
     */
    @GetMapping("/api/getIdCard")
    public  JSONObject generate() {
        StringBuilder generater = new StringBuilder();
        generater.append(this.randomAreaCode());
        generater.append(this.randomBirthday());
        generater.append(this.randomCode());
        generater.append(this.calcTrailingNumber(generater.toString().toCharArray()));
        JSONObject json = new JSONObject();
        json.put("IdCard",generater.toString());
        return json;
    }


    /**
     * 随机地区
     *
     * @return
     */
    public int randomAreaCode() {
        int index = (int) (Math.random() * IdCardGenerator.areaCode.size());
        Collection<Integer> values = IdCardGenerator.areaCode.values();
        Iterator<Integer> it = values.iterator();
        int i = 0;
        int code = 0;
        while (i < index && it.hasNext()) {
            i++;
            code = it.next();
        }
        return code;
    }

    /**
     * 随机生日
     *
     * @return
     */
    public String randomBirthday() {
        Calendar birthday = Calendar.getInstance();
        birthday.set(Calendar.YEAR, (int) (Math.random() * 60) + 1950);
        birthday.set(Calendar.MONTH, (int) (Math.random() * 12));
        birthday.set(Calendar.DATE, (int) (Math.random() * 31));

        StringBuilder builder = new StringBuilder();
        builder.append(birthday.get(Calendar.YEAR));
        long month = birthday.get(Calendar.MONTH) + 1;
        if (month < 10) {
            builder.append("0");
        }
        builder.append(month);
        long date = birthday.get(Calendar.DATE);
        if (date < 10) {
            builder.append("0");
        }
        builder.append(date);
        return builder.toString();
    }

    /**
     * 随机产生3位数
     *
     * @return
     */
    public String randomCode() {
        int code = (int) (Math.random() * 1000);
        if (code < 10) {
            return "00" + code;
        } else if (code < 100) {
            return "0" + code;
        } else {
            return "" + code;
        }
    }


    /**
     * <p>18位身份证验证</p>
     * 根据〖中华人民共和国国家标准 GB 11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
     * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
     * 第十八位数字(校验码)的计算方法为：
     * 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
     * 2.将这17位数字和系数相乘的结果相加。
     * 3.用加出来和除以11，看余数是多少？
     * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2。
     * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
     **/
    public char calcTrailingNumber(char[] chars) {
        if (chars.length < 17) {
            return ' ';
        }
        int[] c = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        char[] r = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
        int[] n = new int[17];
        int result = 0;
        for (int i = 0; i < n.length; i++) {
            n[i] = Integer.parseInt(chars[i] + "");
        }
        for (int i = 0; i < n.length; i++) {
            result += c[i] * n[i];
        }
        return r[result % 11];
    }

    public  static void main(String[] args){
        System.out.println(new IdCardGenerator().generate());
    }
}