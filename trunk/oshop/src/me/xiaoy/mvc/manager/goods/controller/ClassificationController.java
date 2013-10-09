package me.xiaoy.mvc.manager.goods.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import me.xiaoy.core.base.BaseController;
import me.xiaoy.core.common.page.PageList;
import me.xiaoy.mvc.manager.goods.entity.GoodsPropertyModel;
import me.xiaoy.mvc.manager.goods.entity.GoodsType;
import me.xiaoy.mvc.manager.goods.service.GoodsService;

@Controller
@RequestMapping(value = "/goods/classification")
public class ClassificationController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClassificationController.class);
	
	@Autowired
	private GoodsService goodsService;
	private int[] grade = new int[10];;
	
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("goods_list");
		mv.addObject("base", base);
		return mv;
	}

	/**
	 * 跳转分类商品展示分页页面
	 * @param goodsType
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "goods_List")
	public ModelAndView getGoodsList(@ModelAttribute("goodsType")GoodsType goodsType, PageList<GoodsType> page) {
		logger.debug("查询分类商品管理 开始...");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("goods/classification/goodsList");
		PageList<GoodsType> list = goodsService.query(page, goodsType);
		mv.addObject("page", list);
		logger.debug("查询分类商品管理结束， 数量： " + list.getAllSize());
		return mv;
	}

	/**
	 * 跳转分类商品新增页面
	 * @return
	 */
	@RequestMapping(value = "add_Goods")
	public ModelAndView addGoods(@ModelAttribute("goodsType")GoodsType goodsType, PageList<GoodsType> page){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("goods/classification/addGoods");
		PageList<GoodsType> list = goodsService.query(page, goodsType);
		for(int i=0;i<10;i++)
			grade[i]=i;
		mv.addObject("page", list);
		mv.addObject("grade", grade);
		mv.addObject("title", "新增");
		mv.addObject("pmSize", 0);
		return mv;
	}
	
	/**
	 * 分类信息新增并保持
	 * @return ***.jsp分类展示页面
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "save_GoodsType", method=RequestMethod.POST)
	private String addGoodsType(@ModelAttribute("goodsType")GoodsType goodsType,String[] property,GoodsPropertyModel goodsPropertyModel,String isPropert) {
		logger.debug("保存分类信息  开始...");
		//保存分类商品
		Long goodsTypeId=goodsService.saveGoodsType(goodsType);
		//保存商品模板
		if(Integer.parseInt(isPropert)>0){
			for(int i=0;i<property.length;i++){
				goodsPropertyModel.setGoodsTypeId(goodsTypeId);
				goodsPropertyModel.setProperty(property[i]);
				goodsService.saveGoodsPropertyModel(goodsPropertyModel);
			}
		}
		logger.debug("保存分类信息 结束...");
		return "redirect:../classification/goods_List";
	}
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "update_GoodsType/{goodsTypeId}", method=RequestMethod.POST)
	private String updateGoodsType(@PathVariable Long goodsTypeId,@ModelAttribute("goodsType")GoodsType goodsType,String[] property,GoodsPropertyModel goodsPropertyModel,String isPropert) {
		logger.debug("更新分类商品   开始...");
		//更新分类商品
		goodsType.setGoodsTypeId(goodsTypeId);
		goodsService.updateGoodsType(goodsType);
		logger.debug("更新商品模板   开始...");
		//更新商品模板
		if(Integer.parseInt(isPropert)>0){
			goodsService.deleteGoodsPropertyModelBygoodsTypeId(goodsTypeId);
			for(int i=0;i<property.length;i++){
				goodsPropertyModel.setGoodsTypeId(goodsTypeId);
				goodsPropertyModel.setProperty(property[i]);
				goodsService.saveGoodsPropertyModel(goodsPropertyModel);
			}
		}
		logger.debug("更新分类商品及商品模板   结束...");
		return "redirect:../goods_List";
	}
	
	/**
	 * 批量删除分类商品信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String delete(Long[] ids) {
		logger.debug("批量删除分类商品信息delete方法， 参数： " +Arrays.toString(ids));
		goodsService.delete(ids);
		logger.debug("批量删除分类商品信息结束");
		return "redirect:../classification/goods_List";
	}
	
	@RequestMapping(value = "delete/{goodsTypeId}")
	public String delete(@PathVariable Long goodsTypeId) {
		logger.debug("删除单条分类商品信息， 参数： " + goodsTypeId);
		goodsService.delete(goodsTypeId);
		logger.debug("删除单条分类商品信息结束");
		return "redirect:../goods_List";
	}
	
	
	@RequestMapping(value = "edit/{goodsTypeId}")
	public ModelAndView edit(@PathVariable Long goodsTypeId,@ModelAttribute("goodsType")GoodsType goodsType, PageList<GoodsType> page) {
		logger.debug("修改单条分类商品信息， 参数： " + goodsTypeId);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("goods/classification/addGoods");
		PageList<GoodsType> list = goodsService.query(page, goodsType);
		mv.addObject("page", list);
		for(int i=0;i<10;i++)
			grade[i]=i;
		mv.addObject("title", "修改");
		mv.addObject("grade", grade);
		mv.addObject("goodsType", goodsService.getGoodsTypeById(goodsTypeId));
		List<GoodsPropertyModel> propertyModel=goodsService.getGoodsPropertyModelBygoodsTypeId(goodsTypeId);
		mv.addObject("propertyModel", propertyModel);
		mv.addObject("pmSize", propertyModel.size());
		logger.debug("修改单条分类商品信息，结束");
		return mv;
	}
}
