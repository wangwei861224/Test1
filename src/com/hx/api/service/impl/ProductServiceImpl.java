package com.hx.api.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hx.api.common.web.CommonConfig;
import com.hx.api.dao.BaseDaoSupport;
import com.hx.api.dao.NamedQueryer;
import com.hx.api.dao.Pagination;
import com.hx.api.entity.Brand;
import com.hx.api.entity.Dealer;
import com.hx.api.entity.OrderList;
import com.hx.api.entity.Product;
import com.hx.api.entity.ProductSearchModel;
import com.hx.api.model.InterfaceResponse;
import com.hx.api.model.ProductModel;
import com.hx.api.model.ProductParmModel;
import com.hx.api.model.SoldStatisticsModel;
import com.hx.api.model.SoldStatisticsViewModel;
import com.hx.api.service.IAreaService;
import com.hx.api.service.IProductService;
import com.hx.api.service.IStoreOrderService;
import com.hx.api.service.IUserService;
import com.hx.api.service.MessagesUtil;
import com.hx.api.utils.DateUtils;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private BaseDaoSupport baseDaoSupport;
	@Autowired
	private IUserService userService;
	@Autowired
	private IAreaService areaService;
	@Autowired
	private IStoreOrderService storeOrderService;

	/**
	 * 获取商品列表业务处理
	 */
	@Override
	public void doGetProductList(InterfaceResponse rsp, ProductParmModel model) {
		try {
			//对经销商信息进行验证
			Dealer dealer = this.userService.getDealer(model.getPhone(), model.getToken());
			if(dealer == null) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_NOT_THROUGH);
				return;
			}
			List<ProductModel> productModels = new ArrayList<ProductModel>();
			//获取商品集合
			Pagination<Product> productResult = this.getProducts(model, dealer);
			List<Product> productList = productResult.getRecords();
			if(productList != null && productList.size() > 0) {
				for(Product product : productList) {
					ProductModel productModel = new ProductModel();
					//信息拷贝
					BeanUtils.copyProperties(product, productModel);
					productModel.setCreateTime(DateUtils.format(Long.valueOf(product.getCreateTime()), CommonConfig.PATTERN));
					productModel.setBrandId(product.getBrand().getId());
					productModel.setBrandName(product.getBrand().getBrandName());
					productModel.setSendCityText(this.areaService.getAreaNameById(product.getSendCity()));
					productModel.setSendShengText(this.areaService.getAreaNameById(product.getSendSheng()));
					productModel.setNotinCityText(this.areaService.getAreaText(product.getNotinCity()));
					productModel.setDealNumber(this.getProductDealNum(product));
					productModels.add(productModel);
				}
			}
			Pagination<ProductModel> resultData = new Pagination<ProductModel>(productResult);
			resultData.setRecords(productModels);
			rsp.setStateCode(InterfaceResponse.SUCCESS);
			rsp.setData(resultData);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			rsp.setStateCode(InterfaceResponse.INNER_ERROR);
			rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		}
	}
	
	

	/**
	 * 获取商品信息业务处理
	 */
	@Override
	public void doGetProductInfo(InterfaceResponse rsp, ProductParmModel model) {
		try {
			//对经销商信息进行验证
			Dealer dealer = this.userService.getDealer(model.getPhone(), model.getToken());
			if(dealer == null) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_NOT_THROUGH);
				return;
			}
			ProductModel productModel = new ProductModel();
			Product product = this.getProductById(model.getProductId());
			//信息拷贝
			BeanUtils.copyProperties(product, productModel);
			productModel.setCreateTime(DateUtils.format(Long.valueOf(product.getCreateTime()), CommonConfig.PATTERN));
			productModel.setBrandId(product.getBrand().getId());
			productModel.setBrandName(product.getBrand().getBrandName());
			productModel.setSendCityText(this.areaService.getAreaNameById(product.getSendCity()));
			productModel.setSendShengText(this.areaService.getAreaNameById(product.getSendSheng()));
			productModel.setNotinCityText(this.areaService.getAreaText(product.getNotinCity()));
			productModel.setDealNumber(this.getProductDealNum(product));
			rsp.setStateCode(InterfaceResponse.SUCCESS);
			rsp.setData(productModel);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			rsp.setStateCode(InterfaceResponse.INNER_ERROR);
			rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		}
		
	}

	

	/**
	 * 新增商品业务处理
	 */
	@Override
	public void doAddProductInfo(InterfaceResponse rsp, ProductParmModel model) {
		try {
			//对经销商信息进行验证
			Dealer dealer = this.userService.getDealer(model.getPhone(), model.getToken());
			if(dealer == null) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_NOT_THROUGH);
				return;
			}
			Product product = new Product();
			//信息拷贝
			BeanUtils.copyProperties(model, product);
			product.setDealer(dealer);
			product.setCreateTime(DateUtils.format(new Date(), CommonConfig.PATTERN_ONE));
			product.setText1(model.getDescribe());
			product.setText2(this.newSpec(model.getSpecification()));
			Brand brand = new Brand();
			brand.setId(model.getBrandId());
			product.setBrand(brand);
			//新增操作
			this.addProduct(product);
			rsp.setStateCode(InterfaceResponse.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			rsp.setStateCode(InterfaceResponse.INNER_ERROR);
			rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		}
	}

	

	/**
	 * 编辑商品业务处理
	 * @param rsp
	 * @param model
	 */
	@Override
	public void doUpdateProductInfo(InterfaceResponse rsp, ProductParmModel model) {
		try {
			//对经销商信息进行验证
			Dealer dealer = this.userService.getDealer(model.getPhone(), model.getToken());
			if(dealer == null) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_NOT_THROUGH);
				return;
			}
			Product product = this.getProductById(model.getId());
			//信息拷贝
			BeanUtils.copyProperties(model, product);
			product.setText1(model.getDescribe());
			product.setText2(this.newSpec(model.getSpecification()));
			Brand brand = new Brand();
			brand.setId(model.getBrandId());
			product.setBrand(brand);
			//更新操作
			this.updateProduct(product);
			rsp.setStateCode(InterfaceResponse.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			rsp.setStateCode(InterfaceResponse.INNER_ERROR);
			rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		}
	}
	
	


	/**
	 * 更改商品状态业务处理
	 * @param rsp
	 * @param model
	 */
	@Override
	public void doUpdateProductStatus(InterfaceResponse rsp, ProductParmModel model) {
		try {
			//对经销商信息进行验证
			Dealer dealer = this.userService.getDealer(model.getPhone(), model.getToken());
			if(dealer == null) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_NOT_THROUGH);
				return;
			}
			Product product = this.getProductById(model.getProductId());
			product.setStatus(model.getStatus());
			//更新操作
			this.updateProduct(product);
			rsp.setStateCode(InterfaceResponse.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			rsp.setStateCode(InterfaceResponse.INNER_ERROR);
			rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		}
	}


	
	/**
	 * 批量更改商品状态业务处理
	 */
	@Override
	public void doUpdateProductStatusBatch(InterfaceResponse rsp, ProductParmModel model) {
		try {
			//对经销商信息进行验证
			Dealer dealer = this.userService.getDealer(model.getPhone(), model.getToken());
			if(dealer == null) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_NOT_THROUGH);
				return;
			}
			String[] productIds = model.getProductIds().split(",");
			for(String productId : productIds) {
				if(!StringUtils.isEmpty(productId)) {
					Product product = this.getProductById(Integer.parseInt(productId));
					product.setStatus(model.getStatus());
					//更新操作
					this.updateProduct(product);
				}
			}
			rsp.setStateCode(InterfaceResponse.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			rsp.setStateCode(InterfaceResponse.INNER_ERROR);
			rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		}
	}

	

	/**
	 * 抛货统计业务处理
	 */
	@Override
	public void doSoldStatistics(InterfaceResponse rsp, ProductParmModel model) {
		try {
			//对经销商信息进行验证
			Dealer dealer = this.userService.getDealer(model.getPhone(), model.getToken());
			if(dealer == null) {
				rsp.setStateCode(InterfaceResponse.INNER_ERROR);
				rsp.setMessage(MessagesUtil.MSG_NOT_THROUGH);
				return;
			}
			DecimalFormat df = new DecimalFormat("0.00");
			ProductModel productModel = new ProductModel();
			ProductSearchModel searchModel = new ProductSearchModel();
			//抛货统计模型
			SoldStatisticsModel soldStatisticsModel = new SoldStatisticsModel();
			List<SoldStatisticsViewModel> soldStatisticsViewModels = new ArrayList<SoldStatisticsViewModel>();
			
			searchModel.setDealerId(dealer.getId());
			searchModel.setCycle(model.getCycle());
			//统计总数据
			String totalSql = searchModel.getSqlForTotal();
			List<Object[]> totalDataList = this.baseDaoSupport.find(Object[].class, totalSql);
			if(totalDataList != null && totalDataList.size() > 0) {
				Object[] totalData = totalDataList.get(0);
				soldStatisticsModel.setTotalOrderNum(totalData[0] == null ? 0 : Integer.valueOf(totalData[0].toString()));
				soldStatisticsModel.setTotalStripNum(totalData[1] == null ? 0 : Integer.parseInt(totalData[1].toString()));
				soldStatisticsModel.setTotalDealPrice(totalData[2] == null ? "0.00" : df.format(Double.valueOf(totalData[2].toString())));
			}
			
			//统计周期数据
			String sql = "";
			if(StringUtils.isEmpty(model.getCycle())) {
				//根据月统计
				sql = searchModel.getSqlForMonth();
			} else {
				//根据天统计
				sql = searchModel.getSqlForDay();
			}
			List<Object[]> datas = this.baseDaoSupport.find(Object[].class, sql);
			if(datas != null && datas.size() > 0) {
				for(int i = 0; i < datas.size(); i++) {
					SoldStatisticsViewModel viewModel = new SoldStatisticsViewModel();
					Object[] objs = datas.get(i);
					viewModel.setCycle(objs[0].toString());
					viewModel.setDealPrice(objs[1] == null ? "0.00" : df.format(Double.valueOf(objs[1].toString())));
					viewModel.setOrderNum(objs[2] == null ? 0 : Integer.valueOf(objs[2].toString()));
					viewModel.setStripNum(objs[3] == null ? 0 : Integer.parseInt(objs[3].toString()));
					soldStatisticsViewModels.add(viewModel);
				}
				soldStatisticsModel.setResultSet(soldStatisticsViewModels);
			}
			productModel.setSoldStatisticsModel(soldStatisticsModel);
			rsp.setStateCode(InterfaceResponse.SUCCESS);
			rsp.setData(productModel);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			rsp.setStateCode(InterfaceResponse.INNER_ERROR);
			rsp.setMessage(MessagesUtil.MSG_INNER_ERROR);
		}
		
	}


	/**
	 * 更新商品
	 * @param product
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateProduct(Product product) {
		this.baseDaoSupport.update(product);
	}



	/**
	 * 获取商品列表
	 */
	@Override
	public Pagination<Product> getProducts(ProductParmModel model, Dealer dealer) {
		ProductSearchModel searchModel = new ProductSearchModel();
		searchModel.setDealerId(dealer.getId());
		searchModel.setFieldSearch(model.getFieldSearch());
		searchModel.setStatus(model.getStatus());
		
		NamedQueryer queryer = searchModel.getHql();
		Pagination<Product> result = new Pagination<Product>();
		result.setPageNo(model.getPageNo());
		result.setPageSize(model.getPageSize());
		result = this.baseDaoSupport.find(queryer, result.getPageNo(), result.getPageSize(), Product.class);
		return result;
	}
	
	/**
	 * 统计商品的已成交条目
	 * @param product
	 * @return
	 */
	private Integer getProductDealNum(Product product) {
		Integer dealNum = 0;
		Set<OrderList> orderLists = product.getOrderLists();
		if(orderLists != null && orderLists.size() > 0) {
			for(OrderList ol : orderLists) {
				Integer status = this.storeOrderService.getStatusByCode(ol.getOrderCode());
				if(status == 9) {
					dealNum = dealNum + ol.getProductNumber();
				}
			}
		}
		return dealNum;
	}



	/**
	 * 根据商品ID获取商品实例
	 */
	@Override
	public Product getProductById(Integer productId) {
		return this.baseDaoSupport.get(Product.class, productId);
	}
	
	
	/**
	 * 新增商品
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addProduct(Product product) {
		this.baseDaoSupport.insert(Product.class, product);
	}
	


	/**
	 * 将规格的前半部分去"/""R"生成新字段，便于进行模糊查询
	 * @param spec
	 * @return
	 */
	private String newSpec(String spec) {
		String newSpec = "";
		String[] specs = spec.split(" ");
		newSpec = spec + specs[0].replace("/", "").replace("R", "");
		return newSpec;
	}
}
