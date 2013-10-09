package me.xiaoy.mvc.manager.goods.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import me.xiaoy.core.base.BaseController;
import me.xiaoy.core.common.page.PageList;
import me.xiaoy.core.constants.ResourceDefvalue;
import me.xiaoy.mvc.manager.goods.entity.GoodsImagePath;
import me.xiaoy.mvc.manager.goods.entity.GoodsInfo;
import me.xiaoy.mvc.manager.goods.entity.GoodsProperty;
import me.xiaoy.mvc.manager.goods.entity.GoodsPropertyModel;
import me.xiaoy.mvc.manager.goods.entity.GoodsType;
import me.xiaoy.mvc.manager.goods.entity.StockControl;
import me.xiaoy.mvc.manager.goods.entity.StockControlProperty;
import me.xiaoy.mvc.manager.goods.entity.StockPriceList;
import me.xiaoy.mvc.manager.goods.service.GoodsService;


@Controller
@RequestMapping(value = "/goods/info")
public class InfoController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(InfoController.class);
	
	@Autowired
	private GoodsService goodsService;
	@Resource
	private ResourceUploadManager resourceUploadManager;
	
	/**
	 * 跳转商品信息展示分页页面
	 * @param goodsType
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "info_List")
	public ModelAndView getGoodsList(@ModelAttribute("goodsInfo")GoodsInfo goodsInfo, PageList<GoodsInfo> page) {
		logger.debug("查询商品信息 开始...");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("goods/info/infoList");
		PageList<GoodsInfo> list = goodsService.queryGoodsInfo(page, goodsInfo);
		mv.addObject("page", list);
		logger.debug("查询商品信息管理结束， 数量： " + list.getAllSize());
		return mv;
	}

	/**
	 * 跳转新增商品信息页面
	 * @param goodsType
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "add_infoMain")
	public ModelAndView addinfoMain(@ModelAttribute("goodsType")GoodsType goodsType, PageList<GoodsType> page){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("goods/info/infoMain");
		PageList<GoodsType> list = goodsService.query(page, goodsType);
		mv.addObject("page", list);
		mv.addObject("title", "新增");
		mv.addObject("toType", 0);
		return mv;
	}
	

	/**
	 * 修改商品信息
	 * @param goodsId
	 * @param goodsType
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "edit_GoodsInfo/{goodsId}")
	public ModelAndView edit(@PathVariable Long goodsId,@ModelAttribute("goodsType")GoodsType goodsType, PageList<GoodsType> page) {
		logger.debug("修改单条商品属性信息， 参数： " + goodsId);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("goods/info/infoMain");
		PageList<GoodsType> list = goodsService.query(page, goodsType);
		mv.addObject("page", list);
		mv.addObject("title", "修改");
		mv.addObject("goodsInfo", goodsService.getGoodsInfoById(goodsId));
		mv.addObject("toType", 1);
		logger.debug("修改单条商品属性信息，结束 ");
		return mv;
	}
	 	
	/**
	 * 保存商品信息
	 * @return ***.jsp
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "save_GoodsInfo", method=RequestMethod.POST)
	private ModelAndView saveGoodsInfo(@ModelAttribute("goodsInfo")GoodsInfo goodsInfo,String title,int toType) {
		logger.debug("保存商品信息 开始...");
		ModelAndView mv = new ModelAndView();
		goodsInfo.setProperty(0L);
		if(goodsInfo.getGoodsId()!=null){
			goodsService.updateGoodsInfo(goodsInfo);
			mv.addObject("goodsId", goodsInfo.getGoodsId());
		}
		else{
			Long goodsId=goodsService.saveGoodsInfo(goodsInfo);
			mv.addObject("goodsId", goodsId);
		}
		logger.debug("保存商品信息 结束...");
		List<GoodsPropertyModel> propertyModel=goodsService.getGoodsPropertyModelBygoodsTypeId(goodsInfo.getGoodsTypeId());
		//判断商品类型是否有属性模板如果没有直接跳转到商品详情展示页面
		if(propertyModel.size()>0){
			mv.setViewName("goods/info/infoProperty");
			mv.addObject("propertyModel", propertyModel);
			//判断是否已增加商品属性
			List<GoodsProperty> goodsProperty=goodsService.getgoodsPropertyByGoodsId(goodsInfo.getGoodsId());
			//跳转类型(0:增加,1:修改)
			if(toType==1&&goodsProperty.size()>0){
			   mv.addObject("title", title);
			   mv.addObject("toType", toType);
			   mv.addObject("goodsProperty", goodsProperty);
			}
			else{
				mv.addObject("title", "新增");
				mv.addObject("toType", 0);
			 }
		}else{
			mv.setViewName("redirect:../info/info_List");
		}
		return mv;
	}
	

	
	/**
	 * 保存商品信息属性
	 * @return ***.jsp分类展示页面
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "save_GoodsProperty", method=RequestMethod.POST)
	private ModelAndView savegoodsProperty(@ModelAttribute("GoodsProperty")GoodsProperty goodsProperty,
			Long[] propertyModelId,String[] goodsPropertyIdValue,Long goodsId,int toType,String title) {
		logger.debug("保存商品信息属性 开始...");
		ModelAndView mv = new ModelAndView();
		//保存商品属性
		if(toType!=0)
			//更新商品属性
			goodsService.deleteGoodsPropertyByGoodsId(goodsId);
			for(int i=0;i<goodsPropertyIdValue.length;i++){
				goodsProperty.setGoodsId(goodsId);
				goodsProperty.setPropertyModelId(propertyModelId[i]);
				goodsProperty.setGoodsPropertyIdValue(goodsPropertyIdValue[i]);
				goodsService.saveGoodsProperty(goodsProperty);
			}
		logger.debug("保存商品信息属性 结束...");
		if(toType==1)
			mv.setViewName("redirect:../info/info_List");
		else{
			mv.setViewName("goods/info/infoUpload_pic");
			mv.addObject("rootpath", ResourceDefvalue.filepath+"/Goods/pic/");
			mv.addObject("imagesSize", 0);
			mv.addObject("title", title);
			mv.addObject("goodsId", goodsId);
			mv.addObject("toType", toType);
		}
		return mv;
	}
	

	/**
	 * 修改上传图片
	 * @param goodsId
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "edit_InfoUploadPic/{goodsId}")
	private ModelAndView editInfoUploadPic(@PathVariable Long goodsId){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("goods/info/infoUpload_pic");
		mv.addObject("title", "修改");
		mv.addObject("goodsId", goodsId);
		List<GoodsImagePath> list=goodsService.getGoodsImagePathByGoodsId(goodsId);
		//判断是否上传图片
 		if(list.size()>0){
 			mv.addObject("toType", 1);
		    mv.addObject("goodsImagePath", list);
		    mv.addObject("rootpath", ResourceDefvalue.filepath+"/Goods/pic/");
 		}
		else
			mv.addObject("toType", 0);
		return mv;
	}
	

	/**
	 * 保存商品库存信息
	 * @return ***.jsp
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "save_stockControl", method=RequestMethod.POST)
	private ModelAndView saveGoodsInfo(String[] salesOfProperty,String title,String[] controlPropertyType,
			StockControl stockControl,int toType,Long goodsId,Long[] stockId,Long[] oldStockId,String editType) {
		logger.debug("保存商品信息 开始...");
		ModelAndView mv = new ModelAndView();
		 //保存库存属性
		for(int i=0;i<salesOfProperty.length;i++){
			stockControl.setGoodsId(goodsId);
			stockControl.setSalesOfProperty(salesOfProperty[i]);
			stockControl.setControlPropertyType(Integer.parseInt(controlPropertyType[i]));
			//判断是否为修改
			if(!(editType.isEmpty())&&stockId!=null&&stockId.length>0){
				if(stockId[i]!=null){
					stockControl.setStockId(stockId[i]);
					goodsService.updateStockControl(stockControl);
				}else{
					goodsService.saveStockControl(stockControl);
				}
			}else{
			goodsService.saveStockControl(stockControl);
			}
		}
		if(!(editType.isEmpty())){
			StringBuffer newStockIds=new StringBuffer();
			//判断是否全部删除
			//部分删除
			if(stockId!=null&&stockId.length>0){
				ArrayList<Long> list = new ArrayList<Long>();
				for(int i=0;i<oldStockId.length;i++)
				   list.add(oldStockId[i]);
				for(int i=0;i<stockId.length;i++){
					for(int k=0;k<list.size();k++)
                    if(list.get(k).equals(stockId[i])){
                    	list.remove(k);
                    }
				}
				if(list.size()>0)
				for(Long StockIds:list){
					newStockIds.append(StockIds.toString()+",");
				}
			}else{
				for(Long StockIds:oldStockId){
					newStockIds.append(StockIds.toString()+",");
				}
			}
           if(newStockIds.length()>0&&newStockIds!=null){
			String StockId = newStockIds.substring(0,newStockIds.length()-1);
			goodsService.deleteStockControlInStockId(StockId);
           }
		}
		logger.debug("保存商品信息 结束...");
		if(!(editType.isEmpty()))
			mv.addObject("editType", editType);
		mv.setViewName("goods/info/stockSelect");
		mv.addObject("title", title);
		mv.addObject("goodsId", goodsId);
		mv.addObject("toType", toType);
		mv.addObject("stockControl", goodsService.getStockControlByGoodsId(goodsId));
		return mv;
	}
	
	
	
	/**
	 * 批量删除分类商品信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String delete(Long[] ids) {
		logger.debug("批量删除分类商品信息delete方法， 参数： " +Arrays.toString(ids));
		goodsService.deleteGoodsInfoById(ids);
		logger.debug("批量删除分类商品信息结束");
		return "redirect:../info/info_List";
	}
	
	@RequestMapping(value = "delete/{goodsId}")
	public String delete(@PathVariable Long goodsId) {
		logger.debug("删除单条商品信息属性， 参数： " + goodsId);
		goodsService.deleteGoodsInfoById(goodsId);
		logger.debug("删除单条商品信息属性结束");
		return "redirect:../info_List";
	}
	
	
	/**
	 * 上传文件 返回json对象
	 * @return
	 */
	@RequestMapping(value="upload",method=RequestMethod.POST)
	public ModelAndView upload(String goodsId,GoodsImagePath goodsImagePath){
		logger.debug("==========开始上传文件");
	        String filename ="";// 文件名
	        String localfilename="";//本地存放文件名
	        String fileExt = "";
	        String[] imageId=null;
	        String[] oldImageId=null;
	        String[] imagePath=null;
	        String picPath=null;
	        String[] oldImagePath=null;
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
			List<MultipartFile> files=multipartRequest.getFiles("file");
			String[] imageName=multipartRequest.getParameterValues("imageName");
			String[] homedisplay=multipartRequest.getParameterValues("homedisplay");
			//是否为修改
			if(Integer.parseInt(multipartRequest.getParameter("toType"))>0){
				imageId=multipartRequest.getParameterValues("imageId");
				oldImageId=multipartRequest.getParameterValues("oldImageId");
				imagePath=multipartRequest.getParameterValues("imagePath");
				oldImagePath=new String[imagePath.length];
				for(int i=0;i<imagePath.length;i++){
					oldImagePath[i] = imagePath[i].substring(imagePath[i].indexOf('/')+1);
				}
			}
			ArrayList<String> picPaths = new ArrayList<String>();
			ArrayList<String> oldIds = new ArrayList<String>();
			//次数
			int number=0;
			int success=0;
			int fail=0;
				for(MultipartFile file:files){
					number=number+1;
					if(file.getSize()>0){
						filename=file.getOriginalFilename();
						if (filename.lastIndexOf(".") != -1) {
							fileExt = filename.substring(filename.lastIndexOf("."), filename.length());
						}
						localfilename=UUID.randomUUID().toString();
						try {
							goodsImagePath.setGoodsId(Long.parseLong(goodsId));
							//是否为修改
							if(Integer.parseInt(multipartRequest.getParameter("toType"))>0&&imageId[number-1].length()>0){
								int dot = oldImagePath[number-1].lastIndexOf('.'); 
								localfilename= oldImagePath[number-1].substring(0, dot); 
								resourceUploadManager.upload("/Goods/pic/"+goodsId, file, localfilename, fileExt);
								goodsImagePath.setImageId(Long.parseLong(imageId[number-1]));
								picPath=goodsId+"/"+oldImagePath[number-1];
								goodsImagePath.setImagePath(picPath);
								goodsImagePath.setImageName(imageName[number-1]);
								goodsImagePath.setHomedisplay(Integer.parseInt(homedisplay[number-1]));
								goodsService.updateGoodsImagePath(goodsImagePath);
								oldIds.add(imageId[number-1]);
							}else{
								String picName=resourceUploadManager.upload("/Goods/pic/"+goodsId, file, localfilename, fileExt);
								picPath=goodsId+"/"+picName;
								goodsImagePath.setImageName(imageName[success]);
								goodsImagePath.setImagePath(picPath);
								goodsImagePath.setHomedisplay(Integer.parseInt(homedisplay[success]));
								goodsService.saveGoodsImagePath(goodsImagePath);
							}
							picPaths.add(picPath);
							success+=1;
						} catch (IOException e) {
						logger.error("==============上传文件出错 文件名：{}{}",filename,e);
						fail+=1;
						}
					}
				}
				logger.debug("上传文件成功{}失败{}",success,fail);
				StringBuffer newImageIds=new StringBuffer();
				//判断是否全部删除
				ArrayList<Long> list = new ArrayList<Long>();
				if(oldImageId!=null)
				if(oldImageId.length>0){
				for(int i=0;i<oldImageId.length;i++)
				   list.add(Long.parseLong(oldImageId[i]));
				if(imageId.length>0){
					for(int i=0;i<imageId.length;i++){
						for(int k=0;k<list.size();k++)
		                if(list.get(k).toString().equals(imageId[i].toString())){
		                	list.remove(k);
		                }
					}
					if(list.size()>0){
						for(Long imageIds:list){
							newImageIds.append(imageIds.toString()+",");
						}
						String imageIds = newImageIds.substring(0,newImageIds.length()-1);
						goodsService.deleteGoodsImagePathInimageId(imageIds);
					}
				}else
					goodsService.deleteGoodsImagePathByGoodsId(Long.parseLong(goodsId));
			}
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("success",success);
			map.put("fail",fail);
			map.put("picPaths", picPaths);
			super.MakeJson(true, map, false);//false 对应response.setContentType("text/html; charset=UTF-8");
		return null;
	}
	
	/**
	 * 跳转到销售商品展示页面
	 * @param goodsId
	 * @return
	 */
	@RequestMapping(value = "to_StockControl")
	public ModelAndView toStockControl(Long goodsId,int toType) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("goods/info/stockControl");
		mv.addObject("goodsId", goodsId);
		mv.addObject("toType", toType);
		mv.addObject("title", "新增");
		return mv;
	}
	
	/**
	 * 修改销售商品展示页面
	 * @param goodsId
	 * @return
	 */
	@RequestMapping(value = "edit_StockControl/{goodsId}")
	public ModelAndView editStockControl(@PathVariable Long goodsId) {
		ModelAndView mv = new ModelAndView();
		List<StockControl> list=goodsService.getStockControlByGoodsId(goodsId);
		if(list.size()<1)
			mv.addObject("toType", 0);
		else{
		    mv.addObject("toType", 1);
		    mv.addObject("stockControl",list);
		}
		mv.addObject("title", "修改");
		mv.setViewName("goods/info/stockControl");
		mv.addObject("editType", 1);
		mv.addObject("goodsId", goodsId);
		return mv;
	}
	
	/**
	 * 修改商品价格信息
	 * @param goodsId
	 * @return
	 */
	@RequestMapping(value = "edit_StockPricelist/{goodsId}")
	public ModelAndView editStockPricelist(@PathVariable Long goodsId) {
		ModelAndView mv = new ModelAndView();
		List<StockControlProperty> tempslist=null;
		List<StockControlProperty> scpGroup=null;
		List<StockPriceList> priceList=goodsService.getStockPriceListByGoodsId(goodsId);
		//判断是否已增加
		if(priceList.size()<1){
		List<StockControl> controlList=goodsService.getStockControlByGoodsId(goodsId);
		for(int i=0;i<controlList.size();i++){
			if(tempslist==null){
				scpGroup=goodsService.findStockControlPropertyInStockId(controlList.get(i).getStockId().toString()); 
				if(controlList.size()==1){
					List<StockControlProperty> scpGroup2=new ArrayList<StockControlProperty>();
					for(int m=0 ; m<scpGroup.size();m++){
						StockControlProperty scp=new StockControlProperty();
						StringBuffer scpId=new StringBuffer();
						StringBuffer scpname=new StringBuffer();
						scpId.append(scpGroup.get(m).getScpId().toString());
						scpname.append(scpGroup.get(m).getScpName().toString());
						scp.setTempString(scpId.toString());
						scp.setScpName(scpname.toString());
						scpGroup2.add(scp);
					}
					scpGroup=scpGroup2;
				}else{
					tempslist=scpGroup;
				}
		    }else{
		    	tempslist=scpGroup;
		    	scpGroup=null;
		    	scpGroup=goodsService.findStockControlPropertyInStockId(controlList.get(i).getStockId().toString());  
		    	scpGroup=getvarcharlist(tempslist,scpGroup);
		    }
		  }
		}
		if(priceList.size()<1){
			mv.addObject("toType", 0);
		    mv.addObject("title", "新增");
			mv.addObject("StockControlProperty", scpGroup);
		}
		else{
		    mv.addObject("toType", 1);
		    mv.addObject("title", "修改");
		    mv.addObject("priceList",priceList);
		}
		mv.setViewName("goods/info/stockPriceList");
		mv.addObject("goodsId", goodsId);
		return mv;
	}
	
	//商品价格信息排列组合
	public List<StockControlProperty> getvarcharlist(List<StockControlProperty> l1,List<StockControlProperty> l2){
		ArrayList<StockControlProperty> packageTypeList=new ArrayList<StockControlProperty>();
			for(int i=0 ; i<l1.size();i++){
				for(int t=0;t<l2.size();t++){
					StockControlProperty scp=new StockControlProperty();
					StringBuffer scpId=new StringBuffer();
					StringBuffer scpname=new StringBuffer();
					if(l1.get(i).getScpId()==null){
						scpId.append(l1.get(i).getTempString()+","+l2.get(t).getScpId().toString());
						scpname.append(l1.get(i).getScpName()+"+"+l2.get(t).getScpName().toString());
					}
					else{
						scpId.append((l1.get(i).getScpId().toString()+","+l2.get(t).getScpId().toString()).toString());
						scpname.append(l1.get(i).getScpName()+"+"+l2.get(t).getScpName().toString());
					}
					scp.setTempString(scpId.toString());
					scp.setScpName(scpname.toString());
					packageTypeList.add(scp);
				}
			}
		return packageTypeList;
	}
	/**
	 * 跳转到库存销售图片/文字选择页面
	 * @param goodsId
	 * @return
	 */
	@RequestMapping(value = "to_stockSelectPic")
	public ModelAndView stockSelectPic(Long stockId,Long goodsId,String title,int toType,String editType) {
		ModelAndView mv = new ModelAndView();
		List<GoodsImagePath> list=goodsService.getGoodsImagePathByGoodsId(goodsId);
        List<StockControlProperty> scpList=goodsService.findStockControlPropertyInStockId(stockId.toString());
	    if(scpList.size()<1)
			toType=0;
		else{
			mv.addObject("editType", editType);
			mv.addObject("stockControlProperty", scpList);
			toType=1;
		}
		mv.addObject("rootpath", ResourceDefvalue.filepath+"/Goods/pic/");
		mv.setViewName("goods/info/stockControlProperty");
		mv.addObject("goodsImagePath", list);
		mv.addObject("goodsId", goodsId);
		mv.addObject("toType", toType);
		mv.addObject("title", title);
		mv.addObject("stockId", stockId);
		return mv;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "save_stockControlProperty")
	private ModelAndView saveStockControlProperty(String[] scpName,String[] picPath,Long stockId,int toType,String title,
			Long goodsId,StockControlProperty stockControlProperty,Long[] scpId,Long[] oldScpId,String editType){
		ModelAndView mv = new ModelAndView();
		logger.debug("保存库存商品图片属性， 参数：开始 ");
		//保存库存属性
		if(scpName!=null)
		for(int i=0;i<scpName.length;i++){
			stockControlProperty.setStockId(stockId);
			stockControlProperty.setScpName(scpName[i]);
			stockControlProperty.setPicPath(picPath[i]);
			//判断是否为修改
			if(!(editType.isEmpty())&&scpId!=null&&scpId.length>0){
				if(scpId[i]!=null){
					stockControlProperty.setScpId(scpId[i]);
					goodsService.updateStockControlProperty(stockControlProperty);
				}else{
					goodsService.saveStockControlProperty(stockControlProperty);
				}
			}else{
			goodsService.saveStockControlProperty(stockControlProperty);
			}
		}
		//删除已移除的库存商品信息
		if(!(editType.isEmpty())){
			StringBuffer newScpIds=new StringBuffer();
			//判断是否全部删除
			if(scpId!=null&&scpId.length>0){
				ArrayList<Long> list = new ArrayList<Long>();
				for(int i=0;i<oldScpId.length;i++)
				   list.add(oldScpId[i]);
				for(int i=0;i<scpId.length;i++){
					for(int k=0;k<list.size();k++)
                    if(list.get(k).equals(scpId[i])){
                    	list.remove(k);
                    }
				}
				if(list.size()>0)
				for(Long StockIds:list){
					newScpIds.append(StockIds.toString()+",");
				}
			}else{
				for(Long scpIds:oldScpId){
					newScpIds.append(scpIds.toString()+",");
				}
			}
           if(newScpIds.length()>0&&newScpIds!=null){
			String scpIds = newScpIds.substring(0,newScpIds.length()-1);
			goodsService.deleteStockControlPropertyInScpId(scpIds);
           }
		}
		logger.debug("保存库存商品图片属性， 结束 ");
		mv.setViewName("goods/info/stockSelect");
		mv.addObject("title", title);
		mv.addObject("goodsId", goodsId);
		mv.addObject("toType", toType);
		mv.addObject("stockControl", goodsService.getStockControlByGoodsId(goodsId));
		mv.addObject("isOver", 1);
		return mv;
		
	}
	
	/**
	 * 保存商品价格信息
	 * @param packageType
	 * @param num
	 * @param price
	 * @param salesPrice
	 * @param toType
	 * @param title
	 * @param goodsId
	 * @param packageTypeName
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "save_stockPriceList")
	private ModelAndView saveStockPriceList(String[] packageType,String[] num,BigDecimal[] price,String[] salesPrice,
			int toType,String title,Long goodsId,String[] packageTypeName){
		ModelAndView mv = new ModelAndView();
		logger.debug("保存库存商品价格信息，开始 ");
		for(int i=0;i<price.length;i++){
			StockPriceList stockPriceList=new StockPriceList();
			stockPriceList.setGoodsId(goodsId);
			if(!(num[i].equals("")||num[i].equals(null)))
			   stockPriceList.setNum(Integer.parseInt(num[i]));
			stockPriceList.setPrice(price[i]);
			if(!(salesPrice[i].equals("")||salesPrice[i].equals(null))){
				BigDecimal bd=new BigDecimal(salesPrice[i]);
			   stockPriceList.setSalesPrice(bd);
			}
			stockPriceList.setPackageType(packageType[i]);
			stockPriceList.setPackageTypeName(packageTypeName[i]);
            goodsService.saveStockPriceList(stockPriceList);
		}
		logger.debug("保存库存商品图片属性， 结束 ");
		mv.setViewName("redirect:../info/info_List");
		return mv;
	}
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "edit_stockPriceList")
	private ModelAndView editStockPriceList(String[] packageType,String[] num,BigDecimal[] price,String[] salesPrice,
			int toType,String title,Long goodsId,String[] packageTypeName,Long[] spId){
		ModelAndView mv = new ModelAndView();
		logger.debug("保存库存商品价格信息，开始 ");
		for(int i=0;i<spId.length;i++){
			StockPriceList stockPriceList=new StockPriceList();
			stockPriceList.setId(spId[i]);
			stockPriceList.setGoodsId(goodsId);
			if(!(num[i].equals("")||num[i].equals(null)))
			   stockPriceList.setNum(Integer.parseInt(num[i]));
			stockPriceList.setPrice(price[i]);
			if(!(salesPrice[i].equals("")||salesPrice[i].equals(null))){
				BigDecimal bd=new BigDecimal(salesPrice[i]);
			   stockPriceList.setSalesPrice(bd);
			}
			stockPriceList.setPackageType(packageType[i]);
			stockPriceList.setPackageTypeName(packageTypeName[i]);
            goodsService.updateStockPriceList(stockPriceList);
		}
		logger.debug("保存库存商品图片属性， 结束 ");
		mv.setViewName("redirect:../info/info_List");
		return mv;
	}
	
	/**
	 * ajax获取分类商品对应属性模板值  
	 * @return 
	 *//*
	@RequestMapping(value = "getGoodsPropertyModelByAjax",method=RequestMethod.POST)
	@ResponseBody
	public void getGoodsPropertyModelByAjax(String goodsTypeId)
    {
		List<GoodsPropertyModel> propertyModel=goodsService.getGoodsPropertyModelBygoodsTypeId(Long.parseLong(goodsTypeId));
		System.out.println(propertyModel.get(0).getProperty());
		System.out.println(propertyModel.get(1).getProperty());
		outJson(true, propertyModel);

    }*/
	
}
