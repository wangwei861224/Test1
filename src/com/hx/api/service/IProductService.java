package com.hx.api.service;

import com.hx.api.dao.Pagination;
import com.hx.api.entity.Dealer;
import com.hx.api.entity.Product;
import com.hx.api.model.InterfaceResponse;
import com.hx.api.model.ProductParmModel;

public interface IProductService {

	public void doGetProductList(InterfaceResponse rsp, ProductParmModel model);
	
	public Pagination<Product> getProducts(ProductParmModel model, Dealer dealer);
	
	public void doGetProductInfo(InterfaceResponse rsp, ProductParmModel model);
	
	public Product getProductById(Integer productId);
	
	public void doAddProductInfo(InterfaceResponse rsp, ProductParmModel model);
	
	public void addProduct(Product product);
	
	public void doUpdateProductInfo(InterfaceResponse rsp, ProductParmModel model);
	
	public void updateProduct(Product product);
	
	public void doUpdateProductStatus(InterfaceResponse rsp, ProductParmModel model);
	
	public void doUpdateProductStatusBatch(InterfaceResponse rsp, ProductParmModel model);
	
	public void doSoldStatistics(InterfaceResponse rsp, ProductParmModel model);
}
