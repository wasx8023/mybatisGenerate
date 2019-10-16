//package com.demon.mybatis.generator.mybatis.help;
//
//import java.util.Collection;
//import java.util.Map;
//
///**
// * Ognl工具类
// * <p>
// * 主要是为了在ognl表达式访问静态方法时可以减少长长的类名称编写 Ognl访问静态方法的表达式
// * </p>
// *
// * @class@method(args) 示例使用:
// *  <pre>
// * 	&lt;if test=&quot;@Ognl@isNotEmpty(userId)&quot;&gt;
// * 	and user_id = #{userId}
// * &lt;/if&gt;
// * </pre>
// *
// * @author zyj
// */
//public class Ognl {
//	/**
//	 * 可以用于判断 Map,Collection,String,Array,Long是否为空
//	 *
//	 * @param o
//	 *            java.lang.Object.
//	 * @return boolean.
//	 */
//	public static boolean isEmpty(Object o) throws IllegalArgumentException {
//		if (o == null)
//			return true;
//		if (o instanceof String)
//		{
//			if (((String) o).trim().length() == 0)
//			{
//				return true;
//			}
//		}
//		else if (o instanceof Collection)
//		{
//			if (((Collection) o).isEmpty())
//			{
//				return true;
//			}
//		}
//		else if (o.getClass().isArray())
//		{
//			if (((Object[]) o).length == 0)
//			{
//				return true;
//			}
//		}
//		else if (o instanceof Map)
//		{
//			if (((Map) o).isEmpty())
//			{
//				return true;
//			}
//		}
//		else if (o instanceof Long)
//		{
//			if(((Long)o)==null)
//			{
//				return true;
//			}
//		}
//		else if (o instanceof Short)
//		{
//			if(((Short)o)==null)
//			{
//				return true;
//			}
//		}
//		else
//		{
//			return false;
//		}
//		return false;
//	}
//
//	/**
//	 * 可以用于判断 Map,Collection,String,Array是否不为空
//	 *
//	 * @param o
//	 * @return
//	 */
//	public static boolean isNotEmpty(Object o) {
//		return !isEmpty(o);
//	}
//
//	/**
//	 * 可以用于判断Long类型是否不为空
//	 *
//	 * @param o
//	 * @return
//	 */
//	public static boolean isNotEmpty(Long o) {
//		return !isEmpty(o);
//	}
//
//	/**
//	 * 判断是否为数字
//	 *
//	 * @param o
//	 * @return
//	 */
//	public static boolean isNumber(Object o) {
//		if (o == null)
//			return false;
//		if (o instanceof Number)
//		{
//			return true;
//		}
//		if (o instanceof String)
//		{
//			try
//			{
//				Double.parseDouble((String) o);
//				return true;
//			}
//			catch (NumberFormatException e)
//			{
//				return false;
//			}
//		}
//		return false;
//	}
//
//	public static boolean isUserId(String userId){
//
//		return userId.length()<11;
//
//
//	}
//
//	public static boolean isNotUserId(String userId){
//
//		return !isUserId(userId);
//
//
//	}
//
//}
