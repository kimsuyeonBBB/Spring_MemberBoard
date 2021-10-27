package com.hiball.web.common.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.WordUtils;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import com.hiball.api.domain.CodeDomain;
import com.hiball.api.domain.GamePatternRecord;
import com.hiball.api.domain.GameRecord;
import com.hiball.api.domain.PlayerInfo;
import com.hiball.web.common.domain.CommonDomain;
import com.hiball.web.common.enums.ColumnsEnum;
import com.hiball.web.common.param.CommonParam;

/**
 * All dao method will be create ColumnResultHandler instance 
 * So global value(GameRecord, GamePatternRecord) don't share 
 * @author birdhead
 *
 */
public class ColumnResultHandler extends AbstractResultHandler implements ResultHandler {
	private List<Map<ColumnsEnum, Object>> resultList = new ArrayList<>();
	private Map<ColumnsEnum, Object> addedValueMap = null;
	private ColumnsEnum[] columnsEnumArray;
	private GameRecord record = null;
	private GamePatternRecord patternRecord = null;
	private CodeDomain codeDomain = null;
	private PlayerInfo playerInfo = null;
	
	public <D extends CommonParam> ColumnResultHandler(D param, ColumnsEnum[] columnsEnums) {
		propertySetter(param, columnsEnums);
		this.columnsEnumArray = param.getColumnsEnumArray();
		if(param.getAddedValueMap() != null) {
			this.addedValueMap = param.getAddedValueMap();
		}
	}
	
	public <D extends CommonParam> ColumnResultHandler(D param, ColumnsEnum[] columnEnums, boolean coverYn) {
		coverPropertySetter(param, columnEnums);
		this.columnsEnumArray = param.getColumnsEnumArray();
		
		if(param.getAddedValueMap() != null) {
			this.addedValueMap = param.getAddedValueMap();
		}
	}
	
	public <D extends CommonParam> ColumnResultHandler(D param, ColumnsEnum[] columnsEnums
														, Map<ColumnsEnum, Object> addedValueMap) {
		propertySetter(param, columnsEnums);
		this.columnsEnumArray = param.getColumnsEnumArray();
		this.addedValueMap = addedValueMap;
	}
	
	@Override
	public List<Map<ColumnsEnum, Object>> getResult() {
		return listInMap(resultList);
	}
	
	@Override
	public void handleResult(ResultContext context) {
		Object obj = context.getResultObject();

		if(obj == null) return;
		Class<?> reflectClazz = null;
		
		if(obj instanceof GameRecord) {
			record = (GameRecord) obj;
			reflectClazz = record.getClass();
			if(addedValueMap != null) 
				valueAdd(reflectClazz, record);
			
			columnsAdd(reflectClazz, record);
		} else if(obj instanceof GamePatternRecord) {
			patternRecord = (GamePatternRecord) obj;
			reflectClazz = patternRecord.getClass();
			
			if(addedValueMap != null) 
				valueAdd(reflectClazz, patternRecord); 
			
			columnsAdd(reflectClazz, patternRecord);
		} else if(obj instanceof CodeDomain) {
			codeDomain = (CodeDomain) obj;
			reflectClazz = codeDomain.getClass();
			
			if(addedValueMap != null) 
				valueAdd(reflectClazz, codeDomain);
			columnsAdd(reflectClazz, codeDomain);
		} else if(obj instanceof PlayerInfo) {
			playerInfo = (PlayerInfo) obj;
			reflectClazz = playerInfo.getClass();
			
			if(addedValueMap != null) 
				valueAdd(reflectClazz, playerInfo);
			columnsAdd(reflectClazz, playerInfo);
		}
		
	}
	
	private <D extends CommonDomain> void columnsAdd(Class<?> reflectRecord, D record) {
		getterForLoop(reflectRecord, record);
	}
	
	private void columnsAdd(Class<?> reflectRecord, GamePatternRecord patternRecord) {
		getterForLoop(reflectRecord, patternRecord);
	}

	private <D extends CommonDomain> void valueAdd(Class<?> reflectRecord, D record) {
		setterForLoop(reflectRecord, record);
	}
	
	private void valueAdd(Class<?> reflectRecord, GamePatternRecord patternRecord) {
		setterForLoop(reflectRecord, patternRecord);
	}
	
	private void setterForLoop(Class<?> reflectRecord, Object obj) {
		Set<ColumnsEnum> keySets = addedValueMap.keySet();
		
		for(ColumnsEnum key : keySets) {
			String upperProperty = WordUtils.capitalize(key.name());
			
			try {
				Method setMethod = reflectRecord.getMethod("set" + upperProperty, getReturnType(addedValueMap.get(key)));
				
				if(obj instanceof GameRecord) {
					setMethod.invoke(record, addedValueMap.get(key));
				} else if(obj instanceof GamePatternRecord) {
					setMethod.invoke(patternRecord, addedValueMap.get(key));
				} else if(obj instanceof CodeDomain) {
					setMethod.invoke(codeDomain, addedValueMap.get(key));
				}
			} catch (Exception e) {
//				e.printStackTrace();
			}
		}
	}
	
	//컬럼에 맞는 메소드를 찾는데 발견되징 않으면 null을 resultMap에 담아서 리턴
	private void getterForLoop(Class<?> reflectRecord, Object obj) {
		Map<ColumnsEnum, Object> resultMap = new HashMap<>();
		
		for(ColumnsEnum column : columnsEnumArray) {
			try {
				String upperPropert = WordUtils.capitalize(column.name());
				Method recordMethod = reflectRecord.getMethod("get" + upperPropert);
				savedInvokeResult(recordMethod, column, obj, resultMap);
			}catch(Exception e) {
				try {
					Method recordMethod = reflectRecord.getMethod("get" + column.name());
					savedInvokeResult(recordMethod, column, obj, resultMap);
				} catch(Exception ie) {
					resultMap.put(column, null);
				}
			}
		}
		
		resultMap.put(ColumnsEnum.dataRetrieveYn, "Y");
		
		resultList.add(resultMap);
	}
	
	private void savedInvokeResult(Method method, ColumnsEnum column
			, Object obj, Map<ColumnsEnum, Object> resultMap) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		if(obj instanceof GameRecord) {
			resultMap.put(column, method.invoke(record));
		} else if(obj instanceof GamePatternRecord) {
			resultMap.put(column, method.invoke(patternRecord));
		} else if(obj instanceof CodeDomain) {
			resultMap.put(column, method.invoke(codeDomain));
		} else if(obj instanceof PlayerInfo) 
			resultMap.put(column, method.invoke(playerInfo));
		
	}
	
	private List<Map<ColumnsEnum, Object>> listInMap(List<Map<ColumnsEnum, Object>> resultList) {
		if(resultList.size() == 0 
				|| resultList == null) {
			resultList = new ArrayList<>();
			Map<ColumnsEnum, Object> emptyResultMap = new HashMap<>();

			ColumnsEnum[] columnsEnumArray = this.columnsEnumArray;
			
			for(ColumnsEnum column : columnsEnumArray) {
				emptyResultMap.put(column, null);
			}
			
			emptyResultMap.put(ColumnsEnum.dataRetrieveYn, "N");
			
			resultList.add(emptyResultMap);
		} 
		
		return resultList;
	}
	
	protected Class<?> getReturnType(Object obj) {
		Class<?> returnType = null;
		
		if(obj instanceof String) {
			returnType = String.class;
		} else if(obj instanceof Integer) {
			returnType = Integer.class;
		} else if(obj instanceof BigDecimal) {
			returnType = BigDecimal.class;
		} else if(obj instanceof Double) {
			returnType = Double.class;
		}
		
		return returnType;
	}
}
