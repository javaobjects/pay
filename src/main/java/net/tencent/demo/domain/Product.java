package net.tencent.demo.domain;
/**
 * 商品实体类
* <p>Title: Product</p>  
* <p>Description: </p>  
* @author xianxian 
* @date 2019年6月28日
 */
public class Product {
	private Integer productId;
	private String productName;
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName="
				+ productName + "]";
	}
	public Product(Integer productId, String productName) {
		super();
		this.productId = productId;
		this.productName = productName;
	}
	public Product() {
		super();
	}
}
