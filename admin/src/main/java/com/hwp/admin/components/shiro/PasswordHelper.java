package com.hwp.admin.components.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @Description shrio登录密码加密
 * @auther: zhangkele
 * @UpadteDate: 2019/3/4 9:14
 */
public class PasswordHelper {
    public static final String ALGORITHM_NAME = "md5"; // 基础散列算法
    public static final int HASH_ITERATIONS = 2; // 自定义散列次数

    public static String encryptPassword(String salt, String password) {
        String newPassword = new SimpleHash(ALGORITHM_NAME, password,
                ByteSource.Util.bytes(salt), HASH_ITERATIONS).toHex();
        return newPassword;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("-----------" + encryptPassword("sysadmin", "123456"));
        String rootPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/js/ueditor/jsp";
        System.out.println("rootPath:" + rootPath);
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        System.out.println("path:" + path);
    }
}