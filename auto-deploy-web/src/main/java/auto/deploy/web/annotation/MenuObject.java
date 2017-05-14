package auto.deploy.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @描述：自定义注解MenuObject，自动生成菜单数据
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月14日 下午9:19:11
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MenuObject {
	/* 本功能或菜单代码 */
	public String id();

	/* 方法对应的菜单或功能名称 */
	public String name();

	/* 父级菜单 */
	public String parent();

	/* 同级间排序 */
	public int rank();

	/* 是否叶子节点 */
	public int leaf();

	/* 节点图标 */
	public String icon();
}
