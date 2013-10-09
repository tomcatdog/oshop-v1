package me.xiaoy.mvc.manager.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.xiaoy.core.common.page.PageList;
import me.xiaoy.mvc.manager.goods.dao.ControlDao;
import me.xiaoy.mvc.manager.goods.dao.ControlPropertyDao;
import me.xiaoy.mvc.manager.goods.dao.GoodsDao;
import me.xiaoy.mvc.manager.goods.dao.ImagePathDao;
import me.xiaoy.mvc.manager.goods.dao.InfoDao;
import me.xiaoy.mvc.manager.goods.dao.PriceListDao;
import me.xiaoy.mvc.manager.goods.dao.PropertyDao;
import me.xiaoy.mvc.manager.goods.dao.PropertyModelDao;
import me.xiaoy.mvc.manager.goods.entity.GoodsImagePath;
import me.xiaoy.mvc.manager.goods.entity.GoodsInfo;
import me.xiaoy.mvc.manager.goods.entity.GoodsProperty;
import me.xiaoy.mvc.manager.goods.entity.GoodsPropertyModel;
import me.xiaoy.mvc.manager.goods.entity.GoodsType;
import me.xiaoy.mvc.manager.goods.entity.StockControl;
import me.xiaoy.mvc.manager.goods.entity.StockControlProperty;
import me.xiaoy.mvc.manager.goods.entity.StockPriceList;
import me.xiaoy.mvc.manager.goods.service.GoodsService;

@Service("goodsService")
@Transactional
public class GoodsServiceImp implements GoodsService {
	@Resource
	private GoodsDao goodsDao;
	@Resource
	private PropertyModelDao propertyModelDao;
	@Resource
	private InfoDao infoDao;
	@Resource
	private PropertyDao propertyDao;
	@Resource
	private ControlDao controlDao;
	@Resource
	private ControlPropertyDao controlPropertyDao;
	@Resource
	private ImagePathDao imagePathDao;
	@Resource
	private PriceListDao priceListDao;

	public Long saveGoodsType(GoodsType goodsType) {
		return (Long) goodsDao.save(goodsType);
	}

	public PageList<GoodsType> query(PageList<GoodsType> page,GoodsType goodsType) {
		return goodsDao.list(page, goodsType);
	}

	public void delete(Long[] ids) {
		goodsDao.delete(ids);
	}

	public void delete(Long id) {
		goodsDao.delete(id);
	}
	
	public void deleteGoodsInfoById(Long id) {
		List<StockControl>scList=controlDao.getStockControlByGoodsId(id);
		if(scList.size()>0){
			StringBuffer scIds=new StringBuffer();
			for(int i=0;i<scList.size();i++){
				scIds.append(scList.get(i).getStockId()+",");
			}
			String StockId = scIds.substring(0,scIds.length()-1);
			controlPropertyDao.deleteStockControlPropertyInStockId(StockId);
			controlDao.deleteStockControlInStockId(StockId);
		}
		propertyDao.deleteGoodsPropertyByGoodsId(id);
		infoDao.delete(id);
	}

	public GoodsType getGoodsTypeById(Long id) {
		return goodsDao.get(id);
	}

	public void saveGoodsPropertyModel(GoodsPropertyModel goodsPropertyModel) {
		propertyModelDao.save(goodsPropertyModel);
	}

	public void updateGoodsType(GoodsType goodsType) {
		goodsDao.update(goodsType);
	}

	public List<GoodsPropertyModel> getGoodsPropertyModelBygoodsTypeId(
			Long goodsTypeId) {
		return propertyModelDao.getGoodsPropertyModelBygoodsTypeId(goodsTypeId);
	}

	public void deleteGoodsPropertyModelBygoodsTypeId(Long goodsTypeId) {
		propertyModelDao.deleteGoodsPropertyModelBygoodsTypeId(goodsTypeId);
	}

	public Long saveGoodsInfo(GoodsInfo goodsInfo) {
		return (Long) infoDao.save(goodsInfo);
	}

	public void saveGoodsProperty(GoodsProperty goodsProperty) {
		propertyDao.save(goodsProperty);
	}

	
	public PageList<GoodsInfo> queryGoodsInfo(PageList<GoodsInfo> page,
			GoodsInfo goodsInfo) {
		return infoDao.list(page, goodsInfo);
	}

	
	public GoodsInfo getGoodsInfoById(Long id) {
		return infoDao.get(id);
	}

	
	public void updateGoodsInfo(GoodsInfo goodsInfo) {
		infoDao.update(goodsInfo);
	}

	public List<GoodsProperty> getgoodsPropertyByGoodsId(Long goodsId) {
		return propertyDao.getgoodsPropertyByGoodsId(goodsId);
	}

	
	public void deleteGoodsPropertyByGoodsId(Long goodsId) {
		propertyDao.deleteGoodsPropertyByGoodsId(goodsId);
	}

	
	public void deleteGoodsInfoById(Long[] ids) {
		for(int k=0;k<ids.length;k++){
			List<StockControl>scList=controlDao.getStockControlByGoodsId(ids[k]);
			if(scList.size()>0){
				StringBuffer scIds=new StringBuffer();
				for(int i=0;i<scList.size();i++){
					scIds.append(scList.get(i).getStockId()+",");
				}
				String StockId = scIds.substring(0,scIds.length()-1);
				controlPropertyDao.deleteStockControlPropertyInStockId(StockId);
				controlDao.deleteStockControlInStockId(StockId);
			}
		}
		propertyDao.deleteGoodsPropertyByGoodsId(ids);
		infoDao.delete(ids);
	}

	
	public Long saveStockControl(StockControl stockControl) {
		return (Long) controlDao.save(stockControl);
	}

	
	public void deleteStockControlByGoodsId(Long goodsId) {
		controlDao.deleteStockControlByGoodsId(goodsId);
	}

	
	public List<StockControl> getStockControlByGoodsId(Long goodsId) {
		return controlDao.getStockControlByGoodsId(goodsId);
	}

	
	public void saveStockControlProperty(StockControlProperty stockControlProperty) {
		controlPropertyDao.save(stockControlProperty);
	}

	public List<StockControlProperty> findStockControlPropertyInStockId(String stockIds) {
		return controlPropertyDao.findStockControlPropertyInStockIds(stockIds);
	}

	
	public void saveGoodsImagePath(GoodsImagePath goodsImagePath) {
		imagePathDao.save(goodsImagePath);
		
	}

	
	public List<GoodsImagePath> getGoodsImagePathByGoodsId(Long goodsId) {
		return imagePathDao.getGoodsImagePathByGoodsId(goodsId);
	}

	
	public void updateStockControl(StockControl stockControl) {
		controlDao.updateStockControl(stockControl);
		
	}

	
	public void deleteStockControlInStockId(String StockId) {
		controlPropertyDao.deleteStockControlPropertyInStockId(StockId);
		controlDao.deleteStockControlInStockId(StockId);
		
	}

	
	public void updateStockControlProperty(StockControlProperty stockControlProperty) {
		controlPropertyDao.updateStockControlProperty(stockControlProperty);
	}

	
	public void deleteStockControlPropertyInScpId(String scpIds) {
		controlPropertyDao.deleteStockControlPropertyInScpId(scpIds);
	}

	
	public List<StockPriceList> getStockPriceListByGoodsId(Long goodsId) {
		return priceListDao.getStockPriceListByGoodsId(goodsId);
	}

	
	public void saveStockPriceList(StockPriceList stockPriceList) {
		priceListDao.save(stockPriceList);
		
	}

	
	public void updateStockPriceList(StockPriceList stockPriceList) {
		priceListDao.update(stockPriceList);
		
	}

	
	public void updateGoodsImagePath(GoodsImagePath goodsImagePath) {
		imagePathDao.update(goodsImagePath);
	}

	
	public void deleteGoodsImagePathInimageId(String imageIds) {
		imagePathDao.deleteGoodsImagePathInimageId(imageIds);
		
	}

	public void deleteGoodsImagePathByGoodsId(Long goodsId) {
		imagePathDao.deleteGoodsImagePathByGoodsId(goodsId);
		
	}
}
