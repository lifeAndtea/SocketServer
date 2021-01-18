package socketserver.codeTest;

import java.util.Random;

public class CodeRollTestFirst {

    public static void main(String[] args) {
        int i = 0;
        while (i<1000){
            System.out.print(getRandomString(128));
            i++;
        }
    }

    //length用户要求产生字符串的长度
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
