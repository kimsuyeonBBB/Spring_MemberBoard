define([
	'jquery', 'underscore', 'backbone', 'common'
], 
function($, _, Backbone, Common) {
	var calcObj = new Object();
	
	var distributeDataByZone = function(data) {
		var zoneData = _.groupBy (data, function(item){
			return item.exPitchZoneCode;
		});
		
		return zoneData;
	}
	
	var distributeDataByBallCode = function(data) {
		var ballCodeData = _.groupBy (data, function(item){
			return item.distBallCode;
		});
		
		return ballCodeData;
	}
	
	var filteringDataByZoneCode = function(data, zoneCode) {
		var zoneData = _.filter (data, function(item){
			if (zoneCode == item.exPitchZoneCode){
				return true;
			}
			return false;
		});
		
		return zoneData;
	}
	
	var calcAvgForZone = function(data) {
		var zoneData = distributeDataByZone(data);
		var avgForZone = new Object();
		
		_.each(zoneData, function(item,key){
			avgForZone[key] = calcStats(item)["avg"];
		});
		
		return avgForZone;
	};
	
	var calcRateForZone = function(data, type) {
		var numerator = 0;
		var denominator = 0;
		var statObj = null;
		var rate = 0.0;
		
		var zoneData = distributeDataByZone(data);
		var denominator = data.length;
		var rateForZone = new Object();
		
		_.each(zoneData, function(item, key){
			statObj = calcStats(item);
			numerator = statObj[type];
			
			rate = (numerator/denominator);
			
			rateForZone[key] = (rate*100).toFixed(1);
		});
		
		return rateForZone;
	};
	
	var calcRateForBallCode = function(data, denoObj, type) {
		var statObj = null;
		var rate = 0.0;
		var ballCodeName = null;
		var ballCodeData = distributeDataByBallCode(data);
		var rateForBallCode = new Object();
		var totalCnt = 0;
		
		var typeCode = null;
		
		_.each(ballCodeData, function(item, key){
			var numerator = 0;
			var denominator = 0;
			
			ballCodeName = (Common.ballCodeMultiName(key)).eng;
			statObj = calcStats(item);
			
			_.each(type, function(typeItem){
				typeCode = (Common.hitResult(typeItem)).shortCode;
				tmpNumerator = statObj[typeCode];
				if(tmpNumerator != null && tmpNumerator !="undefined"){
					numerator += tmpNumerator;
				}
			});
			
			if (typeof denoObj != "undefined" && denoObj != null) {
				denominator = denoObj[key];
			} else {
				denominator = item.length;
			}

			if (denominator != null && denominator!=0) {
				rate = (numerator/denominator);
			}
			
			rateForBallCode[ballCodeName] = (rate*100).toFixed(1);
			rateForBallCode[ballCodeName+"_operation"] = numerator+"/"+denominator;
		});
		
		return rateForBallCode;
	};
	
	var calcAvgForBallCode = function(data) {
		var ballCodeData = distributeDataByBallCode(data);
		var avgForBallCode = new Object();
		var ballCodeName = "";
		_.each(ballCodeData, function(item,key){
			ballCodeName = Common.ballCodeMultiName(key);
			avgForBallCode[ballCodeName.eng] = calcStats(item)["avg"];
		});
		
		return avgForBallCode;
	}
	
	var calcPitchingCntForZone = function (data) {
		var zoneData = distributeDataByZone(data);
		var pitCountForZone = new Object();
		
		_.each(zoneData, function(item, key){
			pitCountForZone[key] = item.length;
		});
		
		return pitCountForZone;
	}
	
	var calcPitchingCntForBallCode = function (data) {
		var zoneData = distributeDataByBallCode(data);
		var pitCountForBallCode = new Object();
		
		_.each(zoneData, function(item, key){
			pitCountForBallCode[key] = item.length;
		});
		
		return pitCountForBallCode;
	}
	
	var calcPlayInning = function(outCount) {
		var inning = parseInt(outCount / 3);
		var remainOutCount = outCount % 3;
		
		return parseFloat(inning + "." + remainOutCount);
	}
	
	var calcEra = function(er, ip) {
		return ((er * 9) / ip).toFixed(3);
	}
	
	var calcWhip = function(hit, bb, ip) {
		return ((hit + bb) / ip).toFixed(3);
	}
	
	var calcStats = function(data) {
		var stats = new Object();
		var b1b = 0,b2b = 0,b3b = 0,hr = 0,bb = 0,k = 0,ab = 0,tpa = 0,hit = 0,totalOnBase = 0,tb = 0,fo = 0, pitched=0
			,go = 0,contact=0,standing=0,swing=0, ibb=0,hbp=0, sb=0, sf=0, gdp=0, outCount=0, er=0, r=0, situationCnt=0;
		var avg = 0.0, obp = 0.0, slg = 0.0, isoP = 0.0, ops = 0.0, ip = 0;
		
		_.each(data, function(item){
			b1b += item.b1b;
			b2b += item.b2b;
			b3b += item.b3b;
			hit += item.b1b+item.b2b+item.b3b;
			hr  += item.hr;
			bb  += item.bb;
			k   += item.k;
			fo  += item.fo;
			go  += item.go;
			ibb += item.ibb;
			hbp += item.hbp;
			sb  += item.sb;
			sf += item.sf;
			gdp += item.gdp;
			tpa += item.tpa;
			ab  += item.ab;
			er += item.er;
			r += item.r;
			situationCnt += item.situationCnt;
			outCount += (item.outCount - item.beforeOutCount);
			totalOnBase += item.b1b+item.b2b+item.b3b+item.hr+item.bb+item.hbp;
			if (item.hitTypeCode == 6302 || item.hitTypeCode == 6306)
				contact += 1;
			if (item.hitTypeCode == 6301)
				standing += 1;
			if (item.hitTypeCode == 6303 || item.hitTypeCode == 6304)
				swing += 1;
			ip += item.ip;
			pitched += 1;
		});
		tb = b1b+(2*b2b)+(3*b3b)+(3*hr);
		
		if (ab !=null && ab != 0) {
			avg = (hit+hr)/ab;
			obp = totalOnBase/ab;
			slg = tb/ab;
			isoP = (tb-b1b)/ab;
			ops = slg+obp;
		}
		
		if(isNaN(situationCnt)){
			situationCnt = data.length;
		}
		
		stats["b1b"] = b1b;
		stats["b2b"] = b2b;
		stats["b3b"] = b3b;
		stats["hr"] = hr;
		stats["bb"] = bb;
		stats["k"] = k;
		stats["fo"] = fo;
		stats["go"] = go;
		stats["tpa"] = tpa;
		stats["ab"] = ab;
		stats["hit"] = hit;
		stats["ibb"] = ibb;
		stats["hbp"] = hbp;
		stats["sb "] = sb;
		stats["sf"] = sf;
		stats["k"] = k;
		stats["gdp"] = gdp;
		stats["avg"] = avg.toFixed(3); 
		stats["obp"] = obp.toFixed(3);
		stats["slg"] = slg.toFixed(3);
		stats["isoP"] = isoP.toFixed(3);
		stats["ops"] = ops.toFixed(3);
		stats["contact"] = contact;
		stats["standing"] = standing;
		stats["swing"] = swing;
		stats["situationCnt"] = situationCnt;
		stats["playInning"] = calcPlayInning(outCount);
		stats["ip"] = ip.toFixed(3);
		stats["er"] = er;
		stats["r"] = r;
		stats["era"] = calcEra(er, ip);
		stats["whip"] = calcWhip(hit, bb, ip);
		stats["pitched"] = pitched;
		
		return stats
	}
	
	
	calcObj["distributeDataByZone"] = distributeDataByZone;
	calcObj["distributeDataByBallCode"] = distributeDataByBallCode;
	calcObj["calcAvgForBallCode"] = calcAvgForBallCode; 
	calcObj["calcAvgForZone"] = calcAvgForZone;
	calcObj["calcStats"] = calcStats;
	calcObj["filteringDataByZoneCode"] = filteringDataByZoneCode;
	calcObj["calcRateForZone"] = calcRateForZone;
	calcObj["calcRateForBallCode"] = calcRateForBallCode;
	calcObj["calcPitchingCntForZone"] = calcPitchingCntForZone;
	calcObj["calcPitchingCntForBallCode"] = calcPitchingCntForBallCode;
	
	return calcObj;
});