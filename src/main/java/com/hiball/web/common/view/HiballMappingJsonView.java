package com.hiball.web.common.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class HiballMappingJsonView extends MappingJackson2JsonView{
	
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String[]> parameterMap = request.getParameterMap();
		/*BigDecimal outputSize = null;
		BigDecimal modelSize = null;
		BigDecimal baseSize = new BigDecimal(1024);*/
		
		if (parameterMap.containsKey("callback")) {
			String callback = parameterMap.get("callback")[0];
			if(StringUtils.isEmpty(callback)) {
				callback = "jsonpCallback";
			}
			Object value = filterModel(model);
			ObjectMapper objectMapper =  new ObjectMapper();
			String temp  = objectMapper.writeValueAsString(value);
			logger.info("*********************************Result Bytes:" + temp.getBytes());
			
			String jsonpResult = callback + "(" + objectMapper.writeValueAsString(value) + ")";
			response.getOutputStream().write(jsonpResult.getBytes("UTF-8"));
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} else {
			/*logger.info("*********************************Result Bytes:");
			Object value = filterModel(model);
			logger.info(model.keySet());
			ObjectMapper objectMapper =  new ObjectMapper();
			String temp  = objectMapper.writeValueAsString(value);
			logger.info("************************** OUTPUT ********************************");
			logger.info(temp);
			logger.info("************************** OUTPUT END ********************************");
			
			modelSize = new BigDecimal(temp.getBytes().length);
			outputSize = modelSize.divide(baseSize, 4, BigDecimal.ROUND_HALF_UP).divide(baseSize, 4, BigDecimal.ROUND_HALF_UP);
			logger.info(outputSize.toString()+"MB");*/
			System.out.println("&&&&&&&&&&&&&&&" + model + "&&&&&&&&&&&&&&&&");
			System.out.println("===============" + request + "==============");
			System.out.println("@@@@@@@@@@@@@@@" + response + "@@@@@@@@@@@@@@");
			super.renderMergedOutputModel(model, request, response);
		}
	}
}
