package me.xiaoy.mvc.manager.goods.service;

import java.util.List;

import me.xiaoy.core.common.page.PageList;
import me.xiaoy.mvc.manager.goods.entity.GoodsImagePath;
import me.xiaoy.mvc.manager.goods.entity.GoodsInfo;
import me.xiaoy.mvc.manager.goods.entity.GoodsProperty;
import me.xiaoy.mvc.manager.goods.entity.GoodsPropertyModel;
import me.xiaoy.mvc.manager.goods.entity.GoodsType;
import me.xiaoy.mvc.manager.goods.entity.StockControl;
import me.xiaoy.mvc.manager.goods.entity.StockControlProperty;
import me.xiaoy.mvc.manager.goods.entity.StockPriceList;

public interface GoodsService {

	/**
	 * 保存分类商品信息
	 */
	public Long saveGoodsType(GoodsType goodsType);

	/**
	 * 保存商品信息
	 * 
	 * @param goodsInfo
	 * @return
	 */
	public Long saveGoodsInfo(GoodsInfo goodsInfo);

	public Long saveStockControl(StockControl stockControl);

	/**
	 * 更新商品类型
	 * 
	 * @param goodsType
	 */
	public void updateGoodsType(GoodsType goodsType);

	/**
	 * 更新商品信息
	 * 
	 * @param goodsInfo
	 */
	public void updateGoodsInfo(GoodsInfo goodsInfo);

	/**
	 * 保存商品模板对象
	 * 
	 * @param goodsPropertyModel
	 */
	public void saveGoodsPropertyModel(GoodsPropertyModel goodsPropertyModel);

	/**
	 * 保存商品属性对象
	 */
	public void saveGoodsProperty(GoodsProperty goodsProperty);

	/**
	 * 分页
	 * 
	 * @param page
	 * @param object
	 */
	public PageList<GoodsType> query(PageList<GoodsType> page,
			GoodsType goodsType);

	public PageList<GoodsInfo> queryGoodsInfo(PageList<GoodsInfo> page,
			GoodsInfo goodsInfo);

	/**
	 * 删除单条分类商品信息集合(数组)
	 * 
	 * @param ids
	 */
	public void delete(Long[] ids);

	public void deleteGoodsInfoById(Long[] ids);

	/**
	 * 删除单条分类商品信息By GoodsTypeId
	 * 
	 * @param id
	 */
	public void delete(Long id);

	public void deleteGoodsInfoById(Long id);

	/**
	 * 根据GoodsTypeById主键获取GoodsType对象
	 * 
	 * @param id
	 * @return GoodsType
	 */
	public GoodsType getGoodsTypeById(Long id);

	/**
	 * 根据GoodsInfoById主键获取GoodsInfo对象
	 * 
	 * @param id
	 * @return
	 */
	public GoodsInfo getGoodsInfoById(Long id);

	/**
	 * 根据GoodsTypeById获取GoodsPropertyModel对应模型集合
	 * 
	 * @param goodsTypeId
	 * @return
	 */
	public List<GoodsPropertyModel> getGoodsPropertyModelBygoodsTypeId(
			Long goodsTypeId);

	/**
	 * 根据goodsId获取GoodsProperty对应模型集合
	 * 
	 * @param goodsTypeId
	 * @return
	 */
	public List<GoodsProperty> getgoodsPropertyByGoodsId(Long goodsId);

	/**
	 * 根据GoodsTypeById删除GoodsPropertyModel对应模型集合
	 * 
	 * @param goodsTypeId
	 */
	public void deleteGoodsPropertyModelBygoodsTypeId(Long goodsTypeId);

	/**
	 * 根据goodsId删除GoodsProperty对应商品属性集合
	 * 
	 * @param goodsId
	 */
	public void deleteGoodsPropertyByGoodsId(Long goodsId);
	
	/**
	 * 根据goodsId删除StockControl对应商品属性集合
	 * @param goodsId
	 */
	public void deleteStockControlByGoodsId(Long goodsId);
	
	/**
	 * 根据StockId删除StockControl对应商品属性集合
	 * @param goodsId
	 */
	public void deleteStockControlInStockId(String StockId);
	
	/**
	 * 根据goodsId获取StockControl对应集合
	 * @param goodsTypeId
	 * @return
	 */
	public List<StockControl> getStockControlByGoodsId(Long goodsId);
	
	/**
	 * 根据goodsId获取GoodsImagePath对应集合
	 * @param goodsId
	 * @return
	 */
	public List<GoodsImagePath> getGoodsImagePathByGoodsId(Long goodsId);
	
	/**
	 * 保存
	 * @param stockControlProperty
	 */
	public void saveStockControlProperty(StockControlProperty stockControlProperty);
	
	/**
	 * 根据stockId集合 返回 StockControlProperty对象
	 * @param ids
	 * @return
	 */
	public List<StockControlProperty> findStockControlPropertyInStockId(String stockIds);
	
	/**
	 * 保存上传图片
	 * @param goodsImagePath
	 */
	public void saveGoodsImagePath(GoodsImagePath goodsImagePath);
	
	/**
	 * 更新库存商品展示信息
	 * @param stockControl
	 */
	public void updateStockControl(StockControl stockControl);
	
	/**
	 * 更新库存销售商品文字/图片内容
	 * @param stockControlProperty
	 */
	public void updateStockControlProperty(StockControlProperty stockControlProperty);
	
	/**
	 * 根据scpId集合删除销售商品文字/图片内容Stock_ControlProperty表中相应数据
	 * @param scpIds
	 */
	public void deleteStockControlPropertyInScpId(String scpIds);
	
	/**
	 * 根据goodsId获取StockPriceList对象集合
	 * @param goodsId
	 * @return
	 */
	public List<StockPriceList> getStockPriceListByGoodsId(Long goodsId);

	/**
	 * 保存商品价格信息
	 * @param stockPriceList
	 */
	public void saveStockPriceList(StockPriceList stockPriceList);
	
	/**
	 * 更新商品价格信息
	 * @param stockPriceList
	 */
	public void updateStockPriceList(StockPriceList stockPriceList);
	
	/**
	 * 更新上传图片信息
	 * @param goodsImagePath
	 */
	public void updateGoodsImagePath(GoodsImagePath goodsImagePath);
	
	/**
	 * 删除部分上传图片信息
	 * @param imageIds
	 */
	public void deleteGoodsImagePathInimageId(String imageIds);
	
	/**
	 * 根据goodsId删除该商品全部上传图片信息
	 * @param goodsId
	 */
	public void deleteGoodsImagePathByGoodsId(Long goodsId);
}
