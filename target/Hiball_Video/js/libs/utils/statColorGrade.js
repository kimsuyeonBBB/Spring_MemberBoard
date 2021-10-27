define([
	'jquery', 'underscore', 'backbone', 'common'
], 
function($, _, Backbone, Common) {
	var gradeObj = new Object();
	
	var hitRateGrade = function(rate) {
		var colorObj = new Object();
		var colorRgba = "";
		var fontColor = "rgba(255, 255, 255, 1.0)";
		if (typeof rate != "undefined") {
			if (rate >= 15) {
				colorRgba = "rgba(255, 0, 0, 1.0)";
			} else if (rate > 12 && rate< 15) {
				colorRgba = "rgba(255, 0, 0, 0.8)";
			} else if (rate > 9 && rate<= 12) {
				colorRgba = "rgba(255, 0, 0, 0.6)";
			} else if (rate > 6 && rate<= 9 ) {
				colorRgba = "rgba(255, 255, 0, 0.5)";
				fontColor = "rgba(0, 0, 0, 1.0)";
			} else if (rate > 3 && rate<= 6) {
				colorRgba = "rgba(0, 0, 255, 0.5)";
			} else if (rate > 0 && rate <= 3) {
				colorRgba = "rgba(0, 0, 255, 1.0)";
			} else {
				rate = (0).toFixed(1);
				colorRgba = "rgba(128, 128, 128, 0.5)";
			}
		} else {
			rate = (0).toFixed(1);
			colorRgba = "rgba(128, 128, 128, 0.1)";
		}
		
		colorObj["value"] = rate;
		colorObj["color"] = colorRgba;
		colorObj["fontColor"] = fontColor;
		
		return colorObj;
	}
	
	var gradeFor5by5Grid = function(rate) {
		var colorObj = new Object();
		var termPoint = 40;
		var colorRgb = "rgb(255, 255, 255)";
		var fontColor = "rgb(0, 0, 0)";
		if (typeof rate == "undefined") {
			rate = 0;
		}
		
		if (rate > 4) {
			var redColor = "rgb(255,";
			var greenPoint = 0;
			var bluePoint = 0;
			greenPoint = 255-(Math.round((rate-4)*(220/termPoint)*10));
			bluePoint = 255-(Math.round((rate-4)*(245/termPoint)*10));
			redColor+=greenPoint+","+bluePoint+")";
			colorRgb = redColor;
		} else if (rate < 4) {
			var blueColor = "rgb(";
			var greenPoint = 0;
			var redPoint = 0;
			var bluePoint = 0;
			redPoint = 255-(Math.round((4-rate)*(249/termPoint)*10));
			greenPoint = 255-(Math.round((4-rate)*(140/termPoint)*10));
			bluePoint = 255-(Math.round((4-rate)*(12/termPoint)*10));
			blueColor += redPoint+","+greenPoint+","+bluePoint+")";
			colorRgb = blueColor;
		} 
		
		colorObj["value"] = rate;
		colorObj["color"] = colorRgb;
		colorObj["fontColor"] = fontColor;
		
		return colorObj;
	}
	
	var flyOutRateGrade = function(rate) {
		var colorObj = new Object();
		var colorRgba = "";
		var fontColor = "rgba(255, 255, 255, 1.0)";
		if (typeof rate != "undefined") {
			if (rate >= 15) {
				colorRgba = "rgba(255, 0, 0, 1.0)";
			} else if (rate > 14 && rate< 15) {
				colorRgba = "rgba(255, 0, 0, 0.8)";
			} else if (rate > 12 && rate<= 14) {
				colorRgba = "rgba(255, 0, 0, 0.6)";
			} else if (rate > 8 && rate<= 12 ) {
				colorRgba = "rgba(255, 255, 0, 0.5)";
				fontColor = "rgba(0, 0, 0, 1.0)";
			} else if (rate > 3 && rate<= 8) {
				colorRgba = "rgba(0, 0, 255, 0.5)";
			} else if (rate > 0 && rate <= 3) {
				colorRgba = "rgba(0, 0, 255, 1.0)";
			} else {
				rate = (0).toFixed(1);
				colorRgba = "rgba(128, 128, 128, 0.1)";
			}
		} else {
			rate = (0).toFixed(1);
			colorRgba = "rgba(128, 128, 128, 0.1)";
		}
		
		colorObj["value"] = rate;
		colorObj["color"] = colorRgba;
		colorObj["fontColor"] = fontColor;
		
		return colorObj;
	}
	
	var groundOutRateGrade = function(rate) {
		var colorObj = new Object();
		var colorRgba = "";
		var fontColor = "rgba(255, 255, 255, 1.0)";
		if (typeof rate != "undefined") {
			if (rate >= 15) {
				colorRgba = "rgba(255, 0, 0, 1.0)";
			} else if (rate > 13 && rate< 15) {
				colorRgba = "rgba(255, 0, 0, 0.8)";
			} else if (rate > 11 && rate<= 13) {
				colorRgba = "rgba(255, 0, 0, 0.6)";
			} else if (rate > 7 && rate<= 11 ) {
				colorRgba = "rgba(255, 255, 0, 0.5)";
				fontColor = "rgba(0, 0, 0, 1.0)";
			} else if (rate > 4 && rate<= 7) {
				colorRgba = "rgba(0, 0, 255, 0.5)";
			} else if (rate > 0 && rate <= 4) {
				colorRgba = "rgba(0, 0, 255, 1.0)";
			} else {
				rate = (0).toFixed(1);
				colorRgba = "rgba(128, 128, 128, 0.1)";
			}
		} else {
			rate = (0).toFixed(1);
			colorRgba = "rgba(128, 128, 128, 0.1)";
		}
		
		colorObj["value"] = rate;
		colorObj["color"] = colorRgba;
		colorObj["fontColor"] = fontColor;
		
		return colorObj;
	}
	
	var swingRateGrade = function (rate) {
		var colorObj = new Object();
		var colorRgba = "";
		var fontColor = "rgba(255, 255, 255, 1.0)";
		if (typeof rate != "undefined") {
			if (rate >= 11) {
				colorRgba = "rgba(255, 0, 0, 1.0)";
			} else if (rate > 10 && rate< 11) {
				colorRgba = "rgba(255, 0, 0, 0.8)";
			} else if (rate > 9 && rate<= 10) {
				colorRgba = "rgba(255, 0, 0, 0.6)";
			} else if (rate > 7 && rate<= 9 ) {
				colorRgba = "rgba(255, 255, 0, 0.5)";
				fontColor = "rgba(0, 0, 0, 1.0)";
			} else if (rate > 6 && rate<= 7) {
				colorRgba = "rgba(0, 0, 255, 0.5)";
			} else if (rate > 0 && rate <= 6) {
				colorRgba = "rgba(0, 0, 255, 1.0)";
			} else {
				rate = (0).toFixed(1);
				colorRgba = "rgba(128, 128, 128, 0.1)";
			}
		} else {
			rate = (0).toFixed(1);
			colorRgba = "rgba(128, 128, 128, 0.1)";
		}
		
		colorObj["value"] = rate;
		colorObj["color"] = colorRgba;
		colorObj["fontColor"] = fontColor;
		
		return colorObj;
	}
	
	var standingRateGrade = function (rate) {
		var colorObj = new Object();
		var colorRgba = "";
		var fontColor = "rgba(255, 255, 255, 1.0)";
		if (typeof rate != "undefined") {
			if (rate >= 50) {
				colorRgba = "rgba(255, 0, 0, 1.0)";
			} else if (rate > 45 && rate< 50) {
				colorRgba = "rgba(255, 0, 0, 0.8)";
			} else if (rate > 40 && rate<= 45) {
				colorRgba = "rgba(255, 0, 0, 0.6)";
			} else if (rate > 30 && rate<= 40 ) {
				colorRgba = "rgba(255, 255, 0, 0.5)";
				fontColor = "rgba(0, 0, 0, 1.0)";
			} else if (rate > 25 && rate<= 30) {
				colorRgba = "rgba(0, 0, 255, 0.5)";
			} else if (rate > 0 && rate <= 25) {
				colorRgba = "rgba(0, 0, 255, 1.0)";
			} else {
				rate = (0).toFixed(1);
				colorRgba = "rgba(128, 128, 128, 0.1)";
			}
		} else {
			rate = (0).toFixed(1);
			colorRgba = "rgba(128, 128, 128, 0.1)";
		}
		
		colorObj["value"] = rate;
		colorObj["color"] = colorRgba;
		colorObj["fontColor"] = fontColor;
		
		return colorObj;
	}
	
	var hrRateGrade = function(rate, zone) {
		var colorObj = new Object();
		var colorRgba = "";
		var fontColor = "rgba(255, 255, 255, 1.0)";
		if (typeof rate != "undefined") {
			if (rate >= 5) {
				colorRgba = "rgba(255, 0, 0, 1.0)";
			} else if (rate > 4 && rate< 5) {
				colorRgba = "rgba(255, 0, 0, 0.8)";
			} else if (rate > 3 && rate<= 4) {
				colorRgba = "rgba(255, 0, 0, 0.6)";
			} else if (rate > 1 && rate<= 3) {
				colorRgba = "rgba(255, 255, 0, 0.5)";
				fontColor = "rgba(0, 0, 0, 1.0)";
			} else if (rate > 2 && rate<= 3) {
				colorRgba = "rgba(0, 0, 255, 0.5)";
			} else if (rate > 0 && rate <= 2) {
				colorRgba = "rgba(0, 0, 255, 1.0)";
			} else {
				rate = (0).toFixed(1);
				colorRgba = "rgba(128, 128, 128, 0.1)";
			}
		} else {
			rate = (0).toFixed(1);
			colorRgba = "rgba(128, 128, 128, 0.1)";
		}
		
		colorObj["value"] = rate;
		colorObj["color"] = colorRgba;
		colorObj["fontColor"] = fontColor;
		
		return colorObj;
	}
	
	var contactRateGrade = function(rate) {
		var colorObj = new Object();
		var colorRgba = "";
		var fontColor = "rgba(255, 255, 255, 1.0)";
		if (typeof rate != "undefined") {
			if (rate >= 70) {
				colorRgba = "rgba(255, 0, 0, 1.0)";
			} else if (rate > 65 && rate< 70) {
				colorRgba = "rgba(255, 0, 0, 0.8)";
			} else if (rate > 60 && rate<= 65) {
				colorRgba = "rgba(255, 0, 0, 0.6)";
			} else if (rate > 50 && rate<= 60) {
				colorRgba = "rgba(255, 255, 0, 0.5)";
				fontColor = "rgba(0, 0, 0, 1.0)";
			} else if (rate > 30 && rate<= 50) {
				colorRgba = "rgba(0, 0, 255, 0.5)";
			} else if (rate > 0 && rate <= 30) {
				colorRgba = "rgba(0, 0, 255, 1.0)";
			} else {
				rate = (0).toFixed(1);
				colorRgba = "rgba(128, 128, 128, 0.1)";
			}
		} else {
			rate = (0).toFixed(1);
			colorRgba = "rgba(128, 128, 128, 0.1)";
		}
		
		colorObj["value"] = rate;
		colorObj["color"] = colorRgba;
		colorObj["fontColor"] = fontColor;
		
		return colorObj;
	}
	
	var spotColorForHitResult = function(resultCode) {
		var colorCode = 'RGB(0,0,0)';
		if (resultCode == 6601) {//파울
			colorCode = 'RGB(0,255,255)';
		} else if (resultCode==6602) {//플라이아웃
			colorCode = 'RGB(123,123,123)';
		} else if (resultCode==6603) {//삼진아웃
			colorCode = 'RGB(188,58,58)';
		} else if (resultCode==6604) {//1루타
			colorCode = 'RGB(0,0,255)';
		} else if (resultCode==6605) {//땅볼아웃
			colorCode = 'RGB(79,167,167)';
		} else if (resultCode==6607) {//2루타
			colorCode = 'RGB(0,255,0)';
		} else if (resultCode==6610) {//3루타
			colorCode = 'RGB(255,0,0)';
		} else if (resultCode==6613) {//홈런
			colorCode = 'RGB(255,0,255)';
		}
	
		return colorCode;
	}
	
	var sbJudgedRateGrade = function (data, zone) {
		var rate = 0;
		var colorObj = new Object();
		var colorRgba = "";
		var fontColor = "rgba(255, 255, 255, 1.0)";
		if (typeof data != "undefined") {
			if (zone.indexOf("ballZone") > -1) {
				if (data.bJudgeRate >= 99) {
					colorRgba = "rgba(255, 0, 0, 1.0)";
				} else if (data.bJudgeRate < 99) {
					colorRgba = "rgba(0, 0, 255, 1.0)";
				}
				rate = "B: "+data.bJudgeRate+"%<br/>S: "+data.sJudgeRate;
			} else if (zone.indexOf("strikeZone") > -1) {
				if (data.sJudgeRate >= 99) {
					colorRgba = "rgba(255, 0, 0, 1.0)";
				} else if (data.sJudgeRate < 99) {
					colorRgba = "rgba(0, 0, 255, 1.0)";
				}
				rate = "S: "+data.sJudgeRate+"%<br/>B: "+data.bJudgeRate;
			}
		} else {
			rate = (0).toFixed(1);
			colorRgba = "rgba(128, 128, 128, 0.1)";
		}
		
		colorObj["value"] = rate;
		colorObj["color"] = colorRgba;
		colorObj["fontColor"] = fontColor;
		
		return colorObj;
	}
	
	var catcherZonePitchingBallRate = function(data){
		var rate = 0;
		var colorObj = new Object();
		var colorRgba = "";
		var fontColor = "rgba(255, 255, 255, 1.0)";
		
		if(typeof data != "undefined"){
			if(data > 8){
				colorRgba = "rgba(255, 0, 0, 1.0)";
			}else if(data <= 8 && data > 6){
				colorRgba = "rgba(255, 0, 0, 0.5)";
			}else if(data <= 6 && data > 5){
				colorRgba = "rgba(255, 255, 0, 0.5)";
				fontColor = "rgba(0, 0, 0, 1.0)";
			}else if(data <= 5 && data > 3){
				colorRgba = "rgba(0, 0, 255, 0.5)";
			}else if(data <= 3){
				colorRgba = "rgba(0, 0, 255, 1.0)";
			}
		}
		
		colorObj["value"] = data;
		colorObj["color"] = colorRgba;
		colorObj['fontColor'] = fontColor;
		
		return colorObj;
	}
	
	gradeObj["hitRateGrade"] = hitRateGrade;
	gradeObj["flyOutRateGrade"] = flyOutRateGrade;
	gradeObj["groundOutRateGrade"] = groundOutRateGrade;
	gradeObj["swingRateGrade"] = swingRateGrade;
	gradeObj["standingRateGrade"] = standingRateGrade;
	gradeObj["hrRateGrade"] = hrRateGrade;
	gradeObj["hitResultColor"] = spotColorForHitResult;
	gradeObj["contactRateGrade"] = contactRateGrade;
	gradeObj["sbJudgedRateGrade"] = sbJudgedRateGrade;
	gradeObj["catcherZonePitchingBallRate"] = catcherZonePitchingBallRate;
	gradeObj["gradeFor5by5Grid"] = gradeFor5by5Grid;
	
	return gradeObj;
});