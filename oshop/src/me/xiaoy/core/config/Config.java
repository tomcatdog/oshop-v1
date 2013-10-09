package me.xiaoy.core.config;

import java.util.List;

import me.xiaoy.core.utils.StringUtil;
import me.xiaoy.mvc.manager.system.parm.entity.SysParam;
import me.xiaoy.mvc.manager.system.parm.service.SysParamService;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Config {

	private static final Logger logger = LoggerFactory.getLogger(Config.class);
	
	private SysParamService sysParamService;
	
	public SysParamService getSysParamService() {
		return sysParamService;
	}

	public void setSysParamService(SysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

	private List<SysParam> params;
	
	private CaseInsensitiveMap paramMap = new CaseInsensitiveMap();
	
	private static Config instance=null;
	
	private Config(){}
	
	public synchronized static Config getInstance() {
		if(instance == null) {
			logger.debug("获取参数实例");
			instance = new Config();
			instance.init();
		}
		return instance;
	}
	
	protected void init() {
		if(sysParamService==null) {
			ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-core.xml");
			sysParamService = (SysParamService)context.getBean("sysParamService");
		}
		params = sysParamService.getInitParams();
		for(SysParam param : params) {
			paramMap.put(param.getParamName(), param.getParamValue());
		}
	}
	
	/**
	 * 获取参数， 查找顺序  -->内存-->数据库
	 * @param key
	 * @return
	 */
	public String get(String key){
		String value = "";
		Object obj = paramMap.get(key);
		if(obj == null) {
			value = sysParamService.getParam(key);
		} else {
			value = String.valueOf(obj);
		}
		if(StringUtil.isEmpty(value)) {
			logger.warn("不存在参数"+key);
		}
		return value;
	}
	
}
