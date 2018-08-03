/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.dxtest.mvpdemo03.model;

import java.lang.annotation.Annotation;

/**
 * ....package: com.dxtest.mvpdemo03.model
 * <br>.author: dongxiang
 * <br>...date: 2018/5/2  17:23
 * <br>.descrp:
 * <br>..using:
 * <br>.e-mail:dongxiang_android_sdk@aliyun.com
 */

public class PkgClient {
    public static void main(String[]args){
        String pkgName=PkgClient.class.getPackage().getName();
        Package pkg=Package.getPackage(pkgName);
        Annotation[] annotations = pkg.getAnnotations();
        for (Annotation annotation:annotations) {
            if (annotation instanceof PkgAnnotation){
                PkgAnnotation pkgAnnotation= (PkgAnnotation) annotation;
                System.out.print(" "+pkgAnnotation.getClass().getName());
                /**
                 * <b>注解操作：</b>
                 * <p>
                 *     <li>1.pkgAnnotation还可以操作 该注解包下的所有类，比如初始化，检查等；</li>
                 *     <li>2.类似Struts 的@Namespace,可以放到包名上，标明一个包的namespace路径</li>
                 *
                 * </p>
                 * */
            }
        }
    }
}
