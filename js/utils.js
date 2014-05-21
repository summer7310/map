/*
 * 获得所在的层.
 */
function getBelongVectorName(lightlinelayer,lineLayer,policelightlinelayer,pointLayer,addlayer,lineoutlayer,subdeplayer,repairlayer,shortcutlayer,feature)
{
	for(index in lightlinelayer.features)
	{
		if(lightlinelayer.features[index].geometry.id == feature.geometry.id)
		{
			return 'lightlinelayer';
		}
	}
	
	for(index in lineLayer.features)
	{
		if(lineLayer.features[index].geometry.id == feature.geometry.id)
		{
			return 'linelayer';
		}
	}
	
	for(index in policelightlinelayer.features)
	{
		if(policelightlinelayer.features[index].geometry.id == feature.geometry.id)
		{
			return 'policelightlinelayer';
		}
	}
	
	for(index in pointlayer.features)
	{
		if(pointlayer.features[index].geometry.id == feature.geometry.id)
		{
			return 'pointlayer';
		}
	}
	
	for(index in addlayer.features)
	{
		if(addlayer.features[index].geometry.id == feature.geometry.id)
		{
			return 'addlayer';
		}
	}
	
	for(index in lineoutlayer.features)
	{
		if(lineoutlayer.features[index].geometry.id == feature.geometry.id)
		{
			return 'lineoutlayer';
		}
	}
	for(index in subdeplayer.features)
	{
		if(subdeplayer.features[index].geometry.id == feature.geometry.id)
		{
			return 'subdeplayer';
		}
	}
	for(index in repairlayer.features)
	{
		if(repairlayer.features[index].geometry.id == feature.geometry.id)
		{
			return 'repairlayer';
		}
	}
	
	return 'shortcutlayer';
}

//显示提示文字
function showPointTextTips(point, titleText)
{
	var style_point = {
            
            strokeWidth: 0,
            strokeDashstyle: "solid",
            pointRadius: 0,
            label:titleText,
            fontSize:'12px',
            fontFamily:'宋体',
            labelXOffset:15,
            labelYOffset:15,
            labelAlign:'m'
    };
	
	
	pointFeature = new OpenLayers.Feature.Vector(point, null, style_point);
	titleTextLayer.addFeatures([pointFeature]);
}

/*
 *	在地图上画出所有的点并保存点的相关信息到字典中
 */
function initFeaturePoint(vectorLayer,categoryId){
	//get point json
	point_data = getFeatureData('point',categoryId);
    var target_info = [];        
	// create a point feature
	for(index in point_data){
		single_point = point_data[index].point.split(" ");
		pointX = single_point[0];
		pointY = single_point[1];
		
		//画点
		var point = new OpenLayers.Geometry.Point(Number(pointX), Number(pointY));
		
		
		var style_point = {
                strokeColor: point_data[index].strokeColor,
                strokeWidth: 2,
                strokeDashstyle: "solid",
                pointRadius: 6,
                strokeOpacity: 0.8,
                fillOpacity: 0.8,
                //label:point_data[index].title,
                fontSize:'12px',
                fontFamily:'宋体',
                labelXOffset:0,
                labelYOffset:-15,
                labelAlign:'m',
                fillColor: point_data[index].strokeColor
        };
		
		//显示标记文字,把文字放在底下一层，这样上面的就不会选不上了
		var pointText = new OpenLayers.Geometry.Point(Number(pointX), Number(pointY));
		showPointTextTips(pointText, point_data[index].title);
		
		pointFeature = new OpenLayers.Feature.Vector(point, null, style_point);
		vectorLayer.addFeatures([pointFeature]);
			
		/*收集点的信息*/
		id = point_data[index].id;
		titles = point_data[index].title;
		description = point_data[index].description;
		strokeColor = point_data[index].strokeColor;
		iconUrl = point_data[index].iconUrl;
		iconUrl2 = point_data[index].iconUrl2;
		array_index = pointFeature.id;
		target_info[array_index] = {"id":id,"title":titles,"description":description,"strokeColor":strokeColor,"iconUrl":iconUrl,"iconUrl2":iconUrl2,"pointX":pointX,"pointY":pointY};
		//alert(target_info[array_index]);
	}
	return target_info;
}

/*
 *	在地图上画出所有的点并保存点的相关信息到字典中
 */
function initFeatureImagePoint(vectorLayer,categoryId,imageUrl){
	//get point json
	point_data = getFeatureData('point',categoryId);
    var target_info = [];        
	// create a point feature
	for(index in point_data){
		single_point = point_data[index].point.split(" ");
		pointX = single_point[0];
		pointY = single_point[1];
		
		//画点
		var point = new OpenLayers.Geometry.Point(Number(pointX), Number(pointY));
		
		
		var style_point = {
				graphicWidth : 32,
				graphicHeight: 32,
				externalGraphic:imageUrl,
				graphicTitle:point_data[index].title
        };
		
		
		pointFeature = new OpenLayers.Feature.Vector(point, null, style_point);
		vectorLayer.addFeatures([pointFeature]);
		
		/*收集点的信息*/
		id = point_data[index].id;
		titles = point_data[index].title;
		description = point_data[index].description;
		strokeColor = point_data[index].strokeColor;
		iconUrl = point_data[index].iconUrl;
		iconUrl2 = point_data[index].iconUrl2;
		array_index = pointFeature.id;
		target_info[array_index] = {"id":id,"title":titles,"description":description,"strokeColor":strokeColor,"iconUrl":iconUrl,"iconUrl2":iconUrl2,"pointX":pointX,"pointY":pointY};
		//alert(target_info[array_index]);
	}
	return target_info;
}

/**
	在地图上画出所有的线并保存点的相关信息到字典中
*/
function initFeatureLine(vectorLayer,categoryId)
{
	     
	linestring_data = getFeatureData('line',categoryId);
	var target_info = []; 
	// create all line features from a list of points
   	for(index in linestring_data){
   		var pointList = [];
   		linestring_points = linestring_data[index].point.split(",");
   		for(inner_index in linestring_points){
   			single_point = linestring_points[inner_index].split(" ");
   			pointX = single_point[0];
			pointY = single_point[1];
			newPoint = new OpenLayers.Geometry.Point(Number(pointX), Number(pointY));
           	pointList.push(newPoint);
   		}
   		var linestring =  new OpenLayers.Geometry.LineString(pointList);
   		var style_line = {
                strokeColor: linestring_data[index].strokeColor,
                strokeOpacity: 0.8,
                strokeWidth: linestring_data[index].strokeWidth,
                pointRadius: 20,
                //label:linestring_data[index].title,
                fontSize:'12px',
                fontFamily:'宋体',
                labelXOffset:30,
                labelYOffset:10,
                labelAlign:'rm'
         };
   		lineFeature = new OpenLayers.Feature.Vector(linestring,null,style_line);
        vectorLayer.addFeatures([lineFeature]);
        
		//显示标记文字
        single_point = linestring_points[0].split(" ");
		pointX = single_point[0];
		pointY = single_point[1];
		var pointText = new OpenLayers.Geometry.Point(Number(pointX), Number(pointY));
		showPointTextTips(pointText, linestring_data[index].title);
        
        /*收集路的信息*/
		id = linestring_data[index].id;
		titles = linestring_data[index].title;
		linelength = linestring_data[index].linelength;
		description = linestring_data[index].description;
		strokeColor = linestring_data[index].strokeColor;
		strokeWidth = linestring_data[index].strokeWidth;
		iconUrl = linestring_data[index].iconUrl;
		iconUrl2 = linestring_data[index].iconUrl2;
		array_index = linestring.id;
		target_info[array_index] = {"id":id,"title":titles,"linelength":linelength,"description":description,"strokeColor":strokeColor,"strokeWidth":strokeWidth,"iconUrl":iconUrl,"iconUrl2":iconUrl2};
   	}
   	return target_info;       
}

/**
在地图上画出所有的多边形区域路并保存点的相关信息到字典中
*/
function initFeaturePolygon(vectorLayer,categoryId)
{
	polygon_data = getFeatureData('polygon',categoryId);
	var target_info = []; 
	for(index in polygon_data){
		var pointList = [];
		polygon_points = polygon_data[index].point.split(",");
		for(inner_index in polygon_points){
			single_point = polygon_points[inner_index].split(" ");
			pointX = single_point[0];
			pointY = single_point[1];
			newPoint = new OpenLayers.Geometry.Point(Number(pointX), Number(pointY));
	       	pointList.push(newPoint);
		}
		var linearRing = new OpenLayers.Geometry.LinearRing(pointList);
		var polygon = new OpenLayers.Geometry.Polygon([linearRing]);
		var style_polygon = {
                strokeColor: polygon_data[index].strokeColor,
                strokeWidth: 2,
                strokeOpacity: 0.8,
                fillOpacity: 0.8,
                //label:polygon_data[index].title,
                fontSize:'12px',
                fontFamily:'宋体',
                labelXOffset:0,
                labelYOffset:-15,
                labelAlign:'m',
                fillColor: polygon_data[index].strokeColor
        };
	    polygonFeature = new OpenLayers.Feature.Vector(polygon,null,style_polygon);
	    vectorLayer.addFeatures([polygonFeature]); 
	    
	    
	    //显示标记文字
	    single_point = polygon_points[0].split(" ");
		pointX = single_point[0];
		pointY = single_point[1];
		var pointText = new OpenLayers.Geometry.Point(Number(pointX), Number(pointY));
		showPointTextTips(pointText, polygon_data[index].title);
	    
	    /*收集图形的信息*/
		id = polygon_data[index].id;
		titles = polygon_data[index].title;
		description = polygon_data[index].description;
		type_id = polygon_data[index].type_id;
		strokeColor = polygon_data[index].strokeColor;
		iconUrl = polygon_data[index].iconUrl;
		iconUrl2 = polygon_data[index].iconUrl2;
		array_index = polygon.id;
		//alert(array_index);
		target_info[array_index] = {"id":id,"title":titles,"description":description,"type_id":type_id,"strokeColor":strokeColor,"iconUrl":iconUrl,"iconUrl2":iconUrl2};      
	}
	return target_info;  
}

/**
* 合并三个数组
*/
function concatTripleArray(array1,array2,array3){
	for(n in array2){
		array1[n] = array2[n];
	}
	for(n in array3){
		array1[n] = array3[n];
	}
	return array1;
}
/**
* 合并两个数组
*/
function concatTwoArray(array1,array2){
	for(n in array2){
		array1[n] = array2[n];
	}
	return array1;
}
/**
 * 
 * 复制线到某一层
 * @param desvectors
 * @param linestring
 * @return
 */
function cloneLine(vectorLayer,linestring)
{
	linestring_points = linestring.substring("LINESTRING".length + 1,linestring.length - 1);
	//alert(linestring_points);
	var pointList = [];
	line_points = linestring_points.split(",");
	for(inner_index in line_points){
		single_point = line_points[inner_index].split(" ");
		pointX = single_point[0];
		pointY = single_point[1];
		newPoint = new OpenLayers.Geometry.Point(Number(pointX)+0.2, Number(pointY)+0.2);
       	pointList.push(newPoint);
	}
	var linestringtmp =  new OpenLayers.Geometry.LineString(pointList);
   	var style_line = {
                strokeColor: "red",
                strokeOpacity: 1,
                strokeWidth: 6,
                pointRadius: 20
    };
   	//var tmpVector = vectorLayer == 'lightlinelayer' ? lightlinelayer : linelayer;
   	var tmpVector;
   	if(vectorLayer == 'lightlinelayer')
   		tmpVector = lightlinelayer;
   	else if(vectorLayer == 'linelayer')
   		tmpVector = linelayer;
   	else
   		tmpVector = policelightlinelayer;
   	
   	lineFeature = new OpenLayers.Feature.Vector(linestringtmp,null,style_line);
   	tmpVector.addFeatures([lineFeature]);
}

/*计算线的长度*/
function getLengthOfALine(linestring)
{
	//alert(linestring);
	linestring_points = linestring.substring("LINESTRING".length + 1,linestring.length - 1);
	//alert(linestring_points);
	var pointList = [];
	line_points = linestring_points.split(",");
	lineLength = 0;
	for(inner_index in line_points){
		if(inner_index > 0)
		{
			single_point = line_points[inner_index].split(" ");
			pointX = Number(single_point[0]);
			pointY = Number(single_point[1]);
	       	
			old_single_point = line_points[inner_index - 1].split(" ");
			old_pointX = Number(old_single_point[0]);
			old_pointY = Number(old_single_point[1]);
			
			lineLength = lineLength + Math.sqrt((pointX - old_pointX) * (pointX - old_pointX) + (pointY - old_pointY) * (pointY - old_pointY));
		}
	}
	return lineLength.toFixed(4);
}

function deleteFeature(featureId)
{
	
	if(!confirm("您确定要删除这个地图标记吗？"))
		return;
	var feature = null;
	//alert(lightlinelayer.getFeatureById(featureId));
	feature = lightlinelayer.getFeatureById(featureId);
	//alert(feature);
	if(feature != null)
	{
		if(lineDataList[feature.geometry.id])
		{
			deleteFeatureByIdAndType(lineDataList[feature.geometry.id].id,'line');
		}
		map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
		lightlinelayer.destroyFeatures([feature]);
		return;
	}
	feature = null;
	feature = linelayer.getFeatureById(featureId);
	if(feature != null)
	{
		if(lineDataList[feature.geometry.id])
		{
			deleteFeatureByIdAndType(lineDataList[feature.geometry.id].id,'line');
		}
		map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
        linelayer.destroyFeatures([feature]);
		return;
	}
	
	feature = null;
	feature = policelightlinelayer.getFeatureById(featureId);
	if(feature != null)
	{
		if(lineDataList[feature.geometry.id])
		{
			deleteFeatureByIdAndType(lineDataList[feature.geometry.id].id,'line');
		}
		map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
        policelightlinelayer.destroyFeatures([feature]);
		return;
	}
	
	feature = null;
	feature = pointlayer.getFeatureById(featureId);
	if(feature != null)
	{
		if(pointDataList[feature.id])
		{
			deleteFeatureByIdAndType(pointDataList[feature.id].id,'point');
		}
		map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
        pointlayer.destroyFeatures([feature]);
		return;
	}
	feature = null;
	feature = addlayer.getFeatureById(featureId);
	if(feature != null)
	{
		//alert(polygonDataList[feature.geometry.id]);
		if(polygonDataList[feature.geometry.id])
		{
			//alert(polygonDataList[feature.geometry.id].id);
			deleteFeatureByIdAndType(polygonDataList[feature.geometry.id].id,'polygon');
			//alert(1112);
		}
		map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
        addlayer.destroyFeatures([feature]);
		return;
	}
	feature = null;
	feature = lineoutlayer.getFeatureById(featureId);
	if(feature != null)
	{
		if(pointDataList[feature.id])
		{
			deleteFeatureByIdAndType(pointDataList[feature.id].id,'point');
		}
		map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
        lineoutlayer.destroyFeatures([feature]);
        
		return;
	}
	feature = null;
	feature = subdeplayer.getFeatureById(featureId);
	if(feature != null)
	{
		if(pointDataList[feature.id])
		{
			deleteFeatureByIdAndType(pointDataList[feature.id].id,'point');
		}
		map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
        subdeplayer.destroyFeatures([feature]);
        
		return;
	}
	feature = null;
	feature = repairlayer.getFeatureById(featureId);
	if(feature != null)
	{
		if(pointDataList[feature.id])
		{
			deleteFeatureByIdAndType(pointDataList[feature.id].id,'point');
		}
		map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
        repairlayer.destroyFeatures([feature]);
		return;
	}
	feature = null;
	feature = shortcutlayer.getFeatureById(featureId);
	if(feature != null)
	{
		
		if(pointDataList[feature.id])
		{
			deleteFeatureByIdAndType(pointDataList[feature.id].id,'point');
		}
		map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
        shortcutlayer.destroyFeatures([feature]);
		return;
	}
}