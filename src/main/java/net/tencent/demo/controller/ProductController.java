package net.tencent.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.tencent.demo.domain.Product;
import net.tencent.demo.exception.CustomException;

/**
 * 商品管理模块的控制器
* <p>Title: ProductController</p>  
* <p>Description: </p>  
* @author xianxian 
* @date 2019年6月28日
 */
@Controller
@RequestMapping("product")
public class ProductController {

	/**
	 * 处理更新商品信息的请求的方法
	 * @param product
	 * @return
	 * @throws CustomException 
	 */
	@RequestMapping("updateProduct.do")
	public String updateProduct(Product product) throws CustomException
	{
		if(product.getProductId()==100)//把这种情况当做这个商品已经不存在了
		{
			throw new CustomException("商品已经不存在了");
		}else
		{
			//修改商品信息
			System.out.println("修改商品信息");
			return "index";
		}
	}
}
