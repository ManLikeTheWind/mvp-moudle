
/**
 * <b>package-info 三个作用：</b>
 * <p>
 *     <li>1.为标注在包上Annotation提供便利</li>
 *     <li>2.声明包的私有类和常量；</li>
 *     <li>3.提供包的整体注释说明；</li>
 * </p>
 * 使用例子：http://strong-life-126-com.iteye.com/blog/806246
 * */
@PkgAnnotation
package com.dxtest.mvpdemo03.model;


/**
 * <b>声明友好类和包常量 @PkgAnnotation</b>
 * <p>例如：
 *      <br>一个保重有很多内部访问的 类或者常量，
 *      <br>就可以统一的放到package-info中；
 *      <br>进行集中管理，减少friendly类导出游走的情况；
 *  </p>
 *
 *  例子一：
 *  <br>包类： 声明一个包使用的公共类，强调的是包访问权限
 */
class PkClass{
    public void test(){

    }
}

/**
 * 例子二：
 * <br>包常量，只运行包内访问，使用于分“包”开发
 */
class PkConst{
    static final String PACKAGE_CONST="abc";

    static final int SUCCESS=0;
    static final int FAILURE=-1;
    static final int ERROR=-2;
}

