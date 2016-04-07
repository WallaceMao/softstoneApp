package com.rishiqing;

import com.rishiqing.api.client.ApiClient;
import com.rishiqing.api.client.RKAClient;
import com.rishiqing.softstone.model.ApiRequest;
import com.rishiqing.softstone.model.ApiResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: user 毛文强
 * Date: 2016/3/29
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
public class MyTest {

    public static void main(String[] args){

        Map m = new HashMap<String, String>();
        m.put("username", "wallace@rishiqing.com");
        m.put("password", "123456");
        m.put("realName", "wallace");
        m.put("phoneNumber", "12312341234");
        m.put("outerId", "2016040711111");
        ApiRequest req = new ApiRequest("POST", "/v1/users", m);

        ApiClient client = new RKAClient();

        ApiResponse res = client.send(req);

        int code = res.getStatusCode();
        if(code == 200){
            System.out.println("response:----" + code + " " + res.getResponseJSON().toString());
        }else{
            System.out.println("response:----" + code);
        }

    }

    public static int[] getNext(String t){

        char[] p = t.toCharArray();

        int[] next = new int[p.length];

        next[0] = -1;

        int j = 0;

        int k = -1;

        while (j < p.length - 1) {

            if (k == -1 || p[j] == p[k]) {

                next[++j] = ++k;

            } else {

                k = next[k];

            }

        }

        return next;
    }

    public static void printArray(int[] arr){
        String str = "[";
        for(int i = 0; i < arr.length; i++){
            str += arr[i];
            if(i != arr.length - 1){
                str += ",";
            }
        }
        str += "]";
        System.out.println(str);
    }
}
