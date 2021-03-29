package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 此时为@RequestMapping标在类上的情况
 * 下面写的这个默认的情况就是request的value，写全应该就是@RequestMapping(value="/haha")
 * 为当前类所有的方法的请求地址指定一个基准路径
 * @RequestMapping("/haha")就是指定了访问时必须以/haha在/handle01前才能访问成功
 * 现在的真实请求就是/haha/handle01  与HelloController.java中的/handle01请求就不一样了
 * 此时就不会出现“不能映射handler01”的错误了
 * @author lfy
 * haha/handle01
 */
@RequestMapping("/haha")
@Controller
public class RequestMappingTestController {
	
	@RequestMapping("/handle01")
	public String handle01(){
		System.out.println("RequestMappingTestController...handle01");
		return "success";
	}
	
	/**
	 * RequestMapping的其他属性
	 * method：限定请求方式、
	 * 		HTTP协议中的所有请求方式：
	 * 			【GET】, HEAD, 【POST】, PUT, PATCH, DELETE, OPTIONS, TRACE
	 * 		GET、POST
	 * 		method=RequestMethod.POST：只接受这种类型的请求，默认是什么都可以；
	 * 			不是规定的方式报错：4xx:都是客户端错误    在post下点击（get请求）就不是规定的方式报错
	 * 				405 - Request method 'GET' not supported
	 * params：规定请求参数
	 * params 和 headers支持简单的表达式：
	 * 		param1: 表示请求必须包含名为 param1 的请求参数
	 * 			eg：params={"username"}:
	 * 				发送请求的时候必须带上一个名为username的参数(?username=参数名)；没带都会404
	 * 
	 * 		!param1: 表示请求不能包含名为 param1 的请求参数
	 * 			eg:params={"!username"}
	 * 				发送请求的时候必须不携带上一个名为username的参数；带了都会404（这么写？username=  后面不加参数也不行  ）
	 * 		param1 != value1: 表示请求包含名为 param1 的请求参数，但其值不能为 value1
	 * 			eg：params={"username!=123"}
	 * 				发送请求的时候;携带的username值必须不是123(不带username或者username不是123)
	 * 
	 * 		{“param1=value1”, “param2”}: 请求必须包含名为 param1 和param2 的两个请求参数，且 param1 参数的值必须为 value1
	 * 			eg:params={"username!=123","pwd","!age"}
	 * 				请求参数必须满足以上规则；
	 * 				请求的username不能是123，必须有pwd的值，不能有age
	 * headers：规定请求头；也和params一样能写简单的表达式
	 * 	
	 * 
	 * 
	 * consumes：只接受内容类型是哪种的请求，规定请求头中的Content-Type
	 * produces：告诉浏览器返回的内容类型是什么，给响应头中加上Content-Type:text/html;charset=utf-8
	 */
	@RequestMapping(value="/handle02",method=RequestMethod.POST)
	public String handle02(){
		System.out.println("handle02...");
		return "success";
	}
	
	/**
	 * 要username=1&pwd=1
	 * username不能为123，有多个参数时用&联结，不能有名为age的参数
	 * @return
	 */
	@RequestMapping(value="/handle03",params={"username!=123","pwd","!age"})
	public String handle03(){
		System.out.println("handle03....");
		return "success";
	}
	
	/**
	 * headers请求头属性
	 * User-Agent：浏览器信息；
	 * 让火狐能访问，让谷歌不能访问
	 * 
	 * 谷歌：请求头信息
	 * User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36
	 * 火狐;
	 * User-Agent	Mozilla/5.0 (Windows NT 6.3; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0
	 * @return
	 * 
	 */
	@RequestMapping(value="/handle04",headers={"User-Agent=Mozilla/5.0 (Windows NT 6.3; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0"})
	public String handle04(){
		System.out.println("handle04....");
		return "success";
	}

}
