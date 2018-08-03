/**
 * <b>package-info不是平常类，作用有三：</b>
 * <li>1、为标注在报上Annnotation提供便利</li>
 * <li>2、声明包的私有类和常量；</li>
 * <li>3、提供包的整体注释说明；</li>
 *使用例子：http://strong-life-126-com.iteye.com/blog/806246
 * <p>
 *     <b>本包是 创建一些基类：</b>
 *     <li>1.CallBack</li>
 *     <li>2.BaseModel</li>
 *     <li>3.DataModel</li>
 *
 *     <li>4.BaseView</li>
 *     <li>5.BaseActivity</li>
 *     <li>6.BaseFragment</li>
 *
 *     <li>7.BasePresenter</li>
 * </p>
 * */
package com.dxtest.mvpdemo03.base;

/**
 * <b>声明友好类和包常量@PkgAnnotation</b>
 * <br>这个比较简单，而且很实用，比如：
 * <li>1.一个包中有很多内部访问的<b>类</b>或<b>常量</b>，</li>
 * <li>2.就可以统一的放到package-info类中，</li>
 * <li>3.这样就方便，而且集中管理，减少friendly类到处游走的情况;</li>
 * */
/**例子1： 堡垒，生命一个包使用的公共类，强调的是包访问权限*/
class PkgClass{
    public void test(){}
}
/**包常量，只运行保内访问，适用于分“包”开发*/
class PkgConst{
    static final String PACKAGE_CONST="abc";

}





