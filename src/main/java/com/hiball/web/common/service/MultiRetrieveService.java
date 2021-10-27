package com.hiball.web.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import org.apache.commons.lang.WordUtils;
import org.springframework.stereotype.Component;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.hiball.api.domain.GameRecord;
import com.hiball.web.common.enums.ColumnsEnum;
import com.hiball.web.common.param.CommonParam;
import com.hiball.web.util.convertor.ValueConvertor;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * API 조회 결과가 List<Map<ColumnEnum, Object>> 또는 Map<ColumnEnum, Object>를 반환하는데
 * 여러 시즌의 기록을 통합하기 위한 역할을 하는 클래스이다.
 * 클래스의 각 메소드들은 Fuction을 전달 받아서 조회를 하게 된다.
 * 또한 메소드 들이 동작하기 위해선느 domain에 keyValue값이 할당 되어 있어야하며 그 형태는 propertName/value/propertyName/value의 형태를 가져야한다.
 * 사용 라이브러리 : google guava
 * @author birdhead
 *
 */
@Component
public class MultiRetrieveService {
	public static final int MIN_YEAR = 2014;
	/**
	 *  domain에 들어가 있는 두 개의 날짜 startDate, endDate를 있으면 비교를 하여 
	 *  연도차이를 구해서 리턴..
	 * @param domain
	 * @return
	 */
	public <D extends CommonParam> List<Integer> multiRetrieveYear(D domain) {
		List<Integer> yearList = new ArrayList<>();
		
		if(domain.getDayFactor() == -1 && domain.getStartDate() != null && !domain.getStartDate().isEmpty()
				&& domain.getEndDate() != null && !domain.getEndDate().isEmpty()) {
			int startYear = Integer.parseInt(domain.getStartDate().substring(0, 4));
			int endYear = Integer.parseInt(domain.getEndDate().substring(0,4));
			
			int yearGap = endYear - startYear;
			
			if(startYear != endYear) {
				for(int i=0; i<=yearGap; i++) {
					yearList.add(endYear - i);
				}
			}
		}
		
		return yearList;
	}
	
	public <D extends CommonParam, Key, Value> List<Map<Key, Value>> daoMultiRetrieve(List<Integer> yearList
																, D domain, Function<D, List<Map<Key, Value>>> retrieveMethod) {
		List<Map<Key, Value>> resultList = new LinkedList<>();

		for(Integer year : yearList) {
			domain.setYear(year);
			List<Map<Key, Value>> retrieveList = retrieveMethod.apply(domain);
			
			if(retrieveList.size() == 1 && retrieveList.get(0).get(ColumnsEnum.dataRetrieveYn).equals("N")) continue;
			else resultList.addAll(retrieveList);
			
		} // 조회 내용 resultList에 add
		
		//keyValue , DataSet[Multimap]형태로 데이터 구성하기 위한 로직...
		Map<Value, Multimap<Key, Value>> tempDataStructMap = convertMultiMapInListMap(resultList);
		
		List<Map<Key, Value>> lastResult = makeResult(domain, tempDataStructMap);
		
		return lastResult;
	}
	
	public <D extends CommonParam, Key, Value> List<Map<Key, Value>> playerTotalRecordRetrieve(List<Integer> yearList, D domain
																	, Function<D, Map<Key, Value>> totalRetrieveMethod
																	, Function<D, Map<Key, Value>> retrieveMethod) {
		List<Map<Key, Value>> resultList = new LinkedList<>();
		
		Map<Key, Value> resultMap = daoMultiRetrieveForMap(yearList, domain, totalRetrieveMethod);
		GameRecord totalRecord = mapDataBind(resultMap);
		
		for(Integer year : yearList) {
			domain.setYear(year);
			Map<Key, Value> map = retrieveMethod.apply(domain);
			
			if(map.get(ColumnsEnum.dataRetrieveYn).equals("N")) continue;
			
			resultList.add(map);
		}
		
		Map<Value, Multimap<Key, Value>> tempDataStructMap = convertMultiMapInListMap(resultList);
		
		Map<Key, Value> map = makeResultAsMap(domain, totalRecord, tempDataStructMap);
		
		resultList.add(map);
		
		return resultList;
	}
	
	public <D extends CommonParam, Key, Value> List<Map<Key, Value>> daoMultiRetrieve(List<Integer> yearList, D domain
																	, Function<D, Map<Key, Value>> totalRetrieveMethod
																	, Function<D, List<Map<Key, Value>>> retrieveMethod) {
		Map<Key, Value> resultMap = daoMultiRetrieveForMap(yearList, domain, totalRetrieveMethod);
		GameRecord totalRecord = mapDataBind(resultMap);
		
		List<Map<Key, Value>> resultList = new ArrayList<>();
		
		for(Integer year : yearList) {
			domain.setYear(year);
			List<Map<Key, Value>> retrieveList = retrieveMethod.apply(domain);
			
			/**
			* 조회해온 데이터의 길이가 1인 경우 조회 결과가 없는 경우일수도 있으므로 이 경우 
			* @See com.hiball.web.common.handler.ColumnResultHandler 의 listInMap에서 
			* dataRetrieveYn이라는 값에 데이터 조회 결과가 없다는 뜻으로 만든 N이라는 값이 있는지 확인한다.
			*/
			if(retrieveList.size() == 1 && retrieveList.get(0).get(ColumnsEnum.dataRetrieveYn).equals("N")) continue;
			else resultList.addAll(retrieveList);
			System.out.println("List: " + retrieveList);		
		} // 조회 내용 resultList에 add
		
		Map<Value, Multimap<Key, Value>> tempDataStructMap = convertMultiMapInListMap(resultList);
		
		List<Map<Key, Value>> lastResult = makeResult(domain, totalRecord, tempDataStructMap);
		
		
		return lastResult;
	}
	
	
	private <Key, Value> Map<Value, Multimap<Key, Value>> convertMultiMapInListMap(List<Map<Key, Value>> listMap) {
		Map<Value, Multimap<Key, Value>> tempDataStructMap = new LinkedHashMap<>();
		
		for(Map<Key, Value> map : listMap) {
			Value keyValue = map.get(ColumnsEnum.keyValue);
			
			if(!tempDataStructMap.containsKey(keyValue)) {
				Multimap<Key, Value> initMM = Multimaps.forMap(map);
				tempDataStructMap.put(keyValue, initMM);
			} else {
				Multimap<Key, Value> newInstance = ArrayListMultimap.create();
				Multimap<Key, Value> initMM = tempDataStructMap.get(keyValue);
				Multimap<Key, Value> newMM = Multimaps.forMap(map);
				
				newInstance.putAll(initMM);
				newInstance.putAll(newMM);
				
				tempDataStructMap.put(keyValue, newInstance);
			}
		}
		
		return tempDataStructMap;
	}
	
	@SuppressWarnings("unchecked")
	private <D extends CommonParam, Key, Value> Map<Key, Value> makeResultAsMap(D domain, GameRecord totalRecord, Map<Value, Multimap<Key, Value>> mmInMap) {
		Set<Value> mapKey = mmInMap.keySet();
		
		Map<Key, Value> resultMap = null;
		
		for(Value value : mapKey) {
			if(value.equals(ColumnsEnum.playInning)) continue;
			
			Map<Key, Value> map = mergeMap(mmInMap.get(value));
			GameRecord record = mapDataBind(map);
			setLeagueStats(record, totalRecord);
			keyValueBind(record, (String)value);
			Set<Key> keySets = map.keySet();

			resultMap = (Map<Key, Value>) resultMapMake(record, keySets);
		}
		
		return resultMap;
	}
	
	private <D extends CommonParam, Key, Value> List<Map<Key, Value>> makeResult(D domain, Map<Value, Multimap<Key, Value>> mmInMap) {
		List<Map<Key, Value>> resultList = new ArrayList<>();
		
		Set<Value> mapKey = mmInMap.keySet();
		
		for(Value value : mapKey) {
			Map<Key, Value> map = mergeMap(mmInMap.get(value));
			GameRecord record = mapDataBind(map);
			keyValueBind(record, (String)value);
			Set<Key> keySets = map.keySet();
			@SuppressWarnings("unchecked")
//			Map<Key, Value> realMap = (Map<Key, Value>) resultMapMake(record, domain);
			Map<Key, Value> realMap = (Map<Key, Value>) resultMapMake(record, keySets);
			
			resultList.add(realMap);
		}
		
		return resultList;
	}
	
	private <D extends CommonParam, Key, Value> List<Map<Key, Value>> makeResult(D domain, GameRecord totalRecord
																		, Map<Value, Multimap<Key, Value>> mmInMap) {
		List<Map<Key, Value>> resultList = new ArrayList<>();
		
		Set<Value> mapKey = mmInMap.keySet();
		for(Value value : mapKey) {
			Map<Key, Value> map = mergeMap(mmInMap.get(value));
			
			GameRecord playerRecord = mapDataBind(map);
			
			setLeagueStats(playerRecord, totalRecord);
			
			keyValueBind(playerRecord, (String)value);
			Set<Key> keySets = map.keySet();
			@SuppressWarnings("unchecked")
			Map<Key, Value> realMap = (Map<Key, Value>) resultMapMake(playerRecord, keySets);
			
			resultList.add(realMap);
		}
		
		return resultList;
	}
	
	private void setLeagueStats(GameRecord setteeRecord, GameRecord setterRecord) {
		setteeRecord.setLeagueAvgWoba(setterRecord.getWoba());
		setteeRecord.setLeagueTotalR(setterRecord.getR());
		setteeRecord.setLeagueTotalTpa(setterRecord.getTpa());
		setteeRecord.setLeagueAvgObp(setterRecord.getObp());
		setteeRecord.setLeagueWsb(setterRecord.getWsb());
	}
	
	private void keyValueBind(GameRecord record, String keyValue) {
		Map<String, Object> keyValueMap = keyValueMap(keyValue);
		Set<String> keySets = keyValueMap.keySet();
		
		Class<?> recordClazz = record.getClass();
		for(String key : keySets) {
			try {
				Class<?> methodReturnType = recordClazz.getMethod("get" + WordUtils.capitalize(key)).getReturnType();
				Method method = recordClazz.getMethod("set" + WordUtils.capitalize(key), methodReturnType);
				
				method.invoke(record, keyValueMap.get(key));
			} catch (Exception e) {
				try {
					Class<?> methodReturnType = recordClazz.getMethod("get" + key).getReturnType();
					Method method = recordClazz.getMethod("set" + key, methodReturnType);
					
					method.invoke(record, keyValueMap.get(key));
				} catch(Exception ie) {
					ie.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 쿼리 상에서 결과를 가져올 때 컬럼/값의 형태로 만들어진 key값이 있어야 한다.
	 * @param keyValue
	 * @return
	 */
	private Map<String, Object> keyValueMap(String keyValue) {
		//keyValue form ex. batterMembersId/4042/batterName/테임즈
		Class<?> recordClazz = GameRecord.class;
		Map<String, Object> keyValueMap = new HashMap<>();
		
		String[] keyValueArray = keyValue.split("/");
		
		for(int i=0, k=1; i<keyValueArray.length; i=i+2, k=k+2) {
			try {
				Class<?> returnType = recordClazz.getMethod("get" + WordUtils.capitalize(keyValueArray[i])).getReturnType();

				if(Integer.TYPE.getName()
						.equals(returnType.getName())) {
					int kValue = Integer.parseInt(keyValueArray[k]);
					keyValueMap.put(keyValueArray[i], kValue);
				} else if(String.class.getName().equals(returnType.getName())) {
					keyValueMap.put(keyValueArray[i], keyValueArray[k]);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return keyValueMap;
	}
	
	private <D extends CommonParam> Map<ColumnsEnum, Object> resultMapMake(GameRecord record, D domain) {
		Map<ColumnsEnum, Object> map = new HashMap<>();
		
		Class<?> recordClazz = record.getClass();
		
		ColumnsEnum[] ceArray = domain.getColumnsEnumArray();
		
		for(ColumnsEnum column : ceArray) {
			try{
				Method method = recordClazz.getMethod("get" + WordUtils.capitalize(column.name()));
				map.put(column, method.invoke(record));
			}catch(Exception e) {
				//첫글자를 대문자로 변경한뒤 메소드르 조회시 예외 발생할 경우 컬럼명그대로 메소드 실행
				try {
					Method method = recordClazz.getMethod("get" + column.name());
					map.put(column, method.invoke(record));
				} catch(Exception ie) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
	
	private <Key> Map<ColumnsEnum, Object> resultMapMake(GameRecord record, Set<Key> keySet) {
		Map<ColumnsEnum, Object> map = new HashMap<>();
		
		Class<?> recordClazz = record.getClass();
		
		for(Key key : keySet) {
			ColumnsEnum column = (ColumnsEnum) key;
			
			try{
				Method method = recordClazz.getMethod("get" + WordUtils.capitalize(column.name()));
				map.put(column, method.invoke(record));
			}catch(Exception e) {
				//첫글자를 대문자로 변경한뒤 메소드르 조회시 예외 발생할 경우 컬럼명그대로 메소드 실행
				try {
					Method method = recordClazz.getMethod("get" + column.name());
					map.put(column, method.invoke(record));
				} catch(Exception ie) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
	
	private <Key, Value> GameRecord mapDataBind(Map<Key, Value> map) {
		GameRecord record = new GameRecord();
		
		Class<?> recordClazz = record.getClass();
		
		Set<Key> keys = map.keySet();
		
		for(Key k : keys) {
			if(k.equals(ColumnsEnum.playInning)) continue;
			if(map.get(k) == null) continue;
			
			try {
				Method setMethod = recordClazz.getMethod("set" + WordUtils.capitalize(k.toString()), getResultType(map.get(k)));
				setMethod.invoke(record, map.get(k));
			} catch(Exception e) {
				try {
					Method setMethod = recordClazz.getMethod("set" + k.toString(), getResultType(map.get(k)));
					setMethod.invoke(record, map.get(k));
				} catch(Exception ie) {
					e.printStackTrace();
				}
			}
		}
		
		return record;
	}
	
	@SuppressWarnings("unchecked")
	public <D extends CommonParam, Key, Value> Map<Key, Value> daoMultiRetrieveForMap(List<Integer> yearList, D domain
																						, Function<D, Map<Key, Value>> totalRetrieveMethod
																						, Function<D, Map<Key, Value>> playerRetrieveMethod) {
		Map<Key, Value> totalMap = daoMultiRetrieveForMap(yearList, domain, totalRetrieveMethod);
		GameRecord totalRecord = mapDataBind(totalMap);
		
		Map<Key, Value> playerMap = daoMultiRetrieveForMap(yearList, domain, playerRetrieveMethod);
		GameRecord playerRecord = mapDataBind(playerMap);
		
		setLeagueStats(playerRecord, totalRecord);
		
		Set<Key> playerMapColumnSet = playerMap.keySet();
		
		return (Map<Key, Value>) resultMapMake(playerRecord, playerMapColumnSet);
	}
	
	/**
	 * @param yearList : 연도별 조회 가능하게 하는 Param
	 * @param domain : 조회시 조회 조건이 담겨 있는 Param
	 * @param retrieveMethod : 서비스로 부터 넘겨 받는 조회를 위한 함수~ 람다식으로 선언된 부분들..
	 * @return
	 */
	public <D extends CommonParam, Key, Value> Map<Key, Value> daoMultiRetrieveForMap(List<Integer> yearList
																, D domain, Function<D, Map<Key,Value>> retrieveMethod) {
		Multimap<Key, Value> mm = ArrayListMultimap.create();
		
		for(Integer year : yearList) {
			domain.setYear(year);
			Map<Key, Value> retrieveMap = retrieveMethod.apply(domain);
			
			// 조회된 데이터의 유무를 판단하여 없으면 기존에 있던 데이터에 추가하지 않고 continue;
			if(retrieveMap.containsKey(ColumnsEnum.dataRetrieveYn)) {
				if(retrieveMap.get(ColumnsEnum.dataRetrieveYn).equals("N")) {
					continue;
				}
			}
			
			Multimap<Key, Value> map = ValueConvertor.mapConvert(retrieveMap); 
			
			mm.putAll(map);
		}

		Map<Key, Value> resultMap = mergeMap(mm);

		return resultMap;
	}
	
	@SuppressWarnings("unchecked")
	private <Key, Value> Map<Key, Value> mergeMap(Multimap<Key, Value> mm) {
		return (Map<Key, Value>) mm.entries()
				.stream()
				.collect(groupingBy(Map.Entry::getKey, reducing(0, Map.Entry::getValue, mapValueSumFunc)));
	}
	
	
	
	
	private BinaryOperator<Object> mapValueSumFunc = (pre, post) -> {
		if(pre instanceof Integer && post instanceof Integer) {
			Integer preValue = (Integer) pre;
			Integer postValue = (Integer) post;
			
			return preValue + postValue;
		} else if(post instanceof String){
			return post;
		} else {
			return null;
		}
	};
	
	private Class<?> getResultType(Object obj) {
		Class<?> returnType = null;
		
		if(obj instanceof String) {
			returnType = String.class;
		} else if(obj instanceof Integer) {
			returnType = Integer.TYPE;
		} else if(obj instanceof BigDecimal) {
			returnType = BigDecimal.class;
		} else if(obj instanceof Double) {
			returnType = Double.class;
		}
		
		return returnType;
	}
}
