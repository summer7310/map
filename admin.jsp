<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta http-equiv="Content-Type" content="text/html;charset=GB2312" />
    <html:base />
    
    <title>天津市公安局南开分局电缆光缆信息管理系统</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link type="image/x-icon" href="img/police.ico" rel="icon"/>
	<link rel="shortcut icon" type="image/ico" href="img/police.ico"/>
	<link rel="StyleSheet" href="js/openlayers/theme/default/style.css" type="text/css"/>
	<script src="js/openlayers/OpenLayers.pack.js"></script>
	<script src="js/jquery.js"></script>
	<script src="js/jquery.form.js"></script>
	<script src="js/varDefine.js"></script>
	<script src="js/getData.js"></script>
	<script src="js/utils.js"></script>
	<script src="js/dialogs.js"></script>
	<script src="js/shortcut.js"></script>
	<style type="text/css">
		body{
		margin: 2px 2px;
		padding: 0px;
		font-size:16px;
		}
       
        #policeController{
            height: 20px; 
            
            font-size:12px;
            position: absolute;
            top: 10px;
            right: 1px; 
            z-index: 10000000;
            border:1px solid #999999;
            background-color: #ffffff;
            filter:alpha(opacity=75); 
			-moz-opacity:0.75;
			opacity: 0.75; 
        }
        #map {
                width: 100%;
                height: 100%;
                border:1px solid #eeeeee;
                padding: 0px;
				margin: 0px;
            }
            
            #dialogHeader{
            	margin:0px;
            	padding:0px;
            	font-weight:bold;
            	border-bottom:1px solid #8CBED7;
            	height:37px;
            }
           #dialogHeader h1 {
            border-bottom:3px solid #8CBED7;
			color:#376585;
			float:left;
			font-size:14px;
			height:30px;
			line-height:32px;
			padding:2px 2px;
			margin:0;
            }
            #dialogItem{
            	margin:0px 0px;
            	padding:0;
            	clear:both;
            }
            
           
            #dialogItem a:link { text-decoration: none;}
			#dialogItem a:visited { text-decoration: none; }
			#dialogItem a:hover {  text-decoration: none;}
			#dialogItem a:active { text-decoration: none; }

            #dialogBody{
            	padding:5px 0px;
            	margin:0px;
            }
            
            #dialogBody form{
            	padding:0px;
            	margin:0px;
            }
            
            #dialogBody label
            {
            	padding:0px;
            	margin:0px;
            	line-height:25px;
            }
            
            #navarea{
			height: 20px; 
           	line-height:20px;
           	padding:2px;
            font-size:14px;
            position: absolute;
            bottom: 2px;
            left: 5px; 
            z-index: 10000000;
            border:1px solid #999999;
            background-color: #ffffff;
            filter:alpha(opacity=0); 
			-moz-opacity:0;
			opacity: 0; 
			
			}
			
			#navarea a:link { color:#000000;text-decoration: none;}
			#navarea a:visited { color:#000000;text-decoration: none; }
			#navarea a:hover {  color:#000000;text-decoration: none;}
			#navarea a:active { color:#000000;text-decoration: none; }
			
			#mysqldump{
			height: 20px; 
           	line-height:20px;
           	padding:2px;
            font-size:14px;
            position: absolute;
            bottom: 2px;
            left: 150px; 
            z-index: 10000000;
            border:1px solid #999999;
            background-color: #ffffff;
            filter:alpha(opacity=0); 
			-moz-opacity:0;
			opacity: 0; 
			
			}
			
			#mysqldump a:link { color:#000000;text-decoration: none;}
			#mysqldump a:visited { color:#000000;text-decoration: none; }
			#mysqldump a:hover {  color:#000000;text-decoration: none;}
			#mysqldump a:active { color:#000000;text-decoration: none; }
			
			#navgation{
			height: 400px; 
            width: 250px;
            font-size:14px;
            position: absolute;
            bottom: 2px;
            left: 5px; 
            z-index: 999999;
            border:1px solid #999999;
            background-color: #ffffff;
            filter:alpha(opacity=85); 
			-moz-opacity:0.85;
			opacity: 0.85; 
			display:none;
			}
			#navgationBody{overflow-y:auto;height:370px;}
			#navgationBody a:link { color:#000000;text-decoration: none;}
			#navgationBody a:visited { color:#000000;text-decoration: none; }
			#navgationBody a:hover {  color:#000000;text-decoration: underline;}
			#navgationBody a:active { color:#000000;text-decoration: none; }
			
			.navgationItem
			{
				text-align:right;
				clear:both;
				padding:3px 4px;
				margin:0px;
			}
			.navgationItem span{
				float:left;
			}
			.delButton{
				background:url("img/delNotOn.jpg") no-repeat scroll transparent;
				display:inline-block;
				width:11px;
				height:11px;	
			}
			#navgation2{
			height: 400px; 
            width: 280px;
            font-size:14px;
            position: absolute;
            bottom: 2px;
            left: 260px; 
            z-index: 999999;
            border:1px solid #999999;
            background-color: #ffffff;
            filter:alpha(opacity=85); 
			-moz-opacity:0.85;
			opacity: 0.85; 
			display:none;
			}
			#navgationHeader{
				font-size:14px;
				font-weight:bold;
				text-align:right;
				line-height:27px;
				height: 27px; 
				background-color:#E9ECF7;
				padding:0px 0px 0px 3px;
			}
          #navgationHeader img{border:0px;}
		  	#navgationSmall{
			height: 27px; 
            width: 250px;
            font-size:14px;
            position: absolute;
            bottom: 2px;
            left: 5px; 
            z-index: 999999;
            border:1px solid #999999;
            background-color: #ffffff;
            filter:alpha(opacity=85); 
			-moz-opacity:0.85;
			opacity: 0.85; 
			
			}
			#navgationSmall2{
			height: 27px; 
            width: 250px;
            font-size:14px;
            position: absolute;
            bottom: 2px;
            left: 260px; 
            z-index: 999999;
            border:1px solid #999999;
            background-color: #ffffff;
            filter:alpha(opacity=85); 
			-moz-opacity:0.85;
			opacity: 0.85; 
			
			}
       	   /*分行*/
			.clear{
				clear:both;
				height:0px;
			}
			
			/*分行*/
			.clear_height5{
				clear:both;
				height:5px;
			}
			
			/*分行蓝绿色背景*/
			.clear_blueheight5{
				clear:both;
				height:5px;
				background-color: #8CBED7;
			}
			.clear_blueheight1{
				clear:both;
				height:0px;
				width:100%;
				background-color: #8CBED7;
				padding:0;
				margin:0;
				
			}
			#clear_blueheight1{
				clear:both;
				height:1px;
				background-color: #8CBED7;
				
			}
			.clear_halfblueheight4{
				clear:both;
				height:4px;
				width:50%;
				background-color: #8CBED7;
			}
			
			/*分行*/
			.clear_height10{
				clear:both;
				height:10px;
			}
	</style>
	<script type="text/javascript">
		var mouseLonlat;
		var selectedFeature;
		var map;
		var layer;
		var drawControls;

		/*层定义*/
		var lightlinelayer;
		var linelayer;
		var policelightlinelayer;
		var pointlayer;
		var addlayer;
		var lineoutlayer;
		var subdeplayer;
		var repairlayer;
		var shortcutlayer;

		var titleTextLayer;//用于显示文字
		
		var lineDataList;//数据
		var pointDataList;//数据
		var polygonDataList;//数据

		var dataListShortcut;//用于快速定位

		var titleDict = [];
		titleDict['lightlinelayer'] = '绘制光缆';
		titleDict['linelayer'] = '绘制电缆';
		titleDict['policelightlinelayer'] = '绘制警务室光缆';
		
		titleDict['pointlayer'] = '标注井盖';
		titleDict['addlayer'] = '地图增项';
		titleDict['lineoutlayer'] = '线路出土点';
		titleDict['subdeplayer'] = '分局单位';
		titleDict['repairlayer'] = '维修点';
		titleDict['shortcutlayer'] = '快速定位';

		var categoryDict = [];
		categoryDict['lightlinelayer'] = '1';
		categoryDict['linelayer'] = '2';
		categoryDict['policelightlinelayer'] = '3';

		categoryDict['addlayer'] = '1';
		
		categoryDict['pointlayer'] = '1';
		categoryDict['lineoutlayer'] = '2';
		categoryDict['subdeplayer'] = '3';
		categoryDict['repairlayer'] = '4';
		categoryDict['shortcutlayer'] = '5';
		
		function onPopupClose(evt) {
			selectControl.unselect(selectedFeature);
		}
		function onFeatureUnselect() {
			if(!selectedFeature)
				return;
			if(selectedFeature.popup == null)
				return;
			map.removePopup(selectedFeature.popup);
			selectedFeature.popup.destroy();
			selectedFeature.popup = null;
        } 
		function onFeatureSelect(feature) {
			//alert(feature.id);
			onFeatureUnselect();
			vectorName = getBelongVectorName(lightlinelayer,linelayer,policelightlinelayer,pointlayer,addlayer,lineoutlayer,subdeplayer,repairlayer,shortcutlayer,feature);
			//getBelongVectorName
			var dialogTitle = titleDict[vectorName];
			
			htmlContent = '';
			if(vectorName == 'lightlinelayer' || vectorName == 'linelayer' || vectorName == 'policelightlinelayer')
			{
				
				var points = feature.geometry;
				var id = '';
				var titles = '';
				var linelength = '';
				var description = '';
				var fillColor = 'gray';
				var iconUrl = '';
				var iconUrl2 = '';
				var strokeWidth = '2';
				var categoryId = categoryDict[vectorName];
				if(lineDataList[feature.geometry.id]){
			 		id = lineDataList[feature.geometry.id].id;
			 		titles = lineDataList[feature.geometry.id].title;
			 		linelength = lineDataList[feature.geometry.id].linelength;
			 		description = lineDataList[feature.geometry.id].description;
			 		fillColor = lineDataList[feature.geometry.id].strokeColor;
			 		strokeWidth = lineDataList[feature.geometry.id].strokeWidth;
			 		iconUrl = lineDataList[feature.geometry.id].iconUrl;
			 		iconUrl2 = lineDataList[feature.geometry.id].iconUrl2;
			 	}
				htmlContent = getDialogHtmlForLine(feature.id,vectorName,dialogTitle,points,id,titles,linelength,description,fillColor,strokeWidth,categoryId,iconUrl,iconUrl2);
			}else if(vectorName == 'addlayer' )
			{
				var points = feature.geometry;
				var id = '';
				var titles = '';
				var description = '';
				var fillColor = 'gray';
				var iconUrl = '';
				var iconUrl2 = '';
				var categoryId = categoryDict[vectorName];
				if(polygonDataList[feature.geometry.id]){
			 		id = polygonDataList[feature.geometry.id].id;
			 		titles = polygonDataList[feature.geometry.id].title;
			 		description = polygonDataList[feature.geometry.id].description;
			 		fillColor = polygonDataList[feature.geometry.id].strokeColor;
			 		iconUrl = polygonDataList[feature.geometry.id].iconUrl;
			 		iconUrl2 = polygonDataList[feature.geometry.id].iconUrl2;
			 	}
				htmlContent = getDialogHtmlForPolygon(feature.id,dialogTitle,points,id,titles,description,fillColor,categoryId,iconUrl,iconUrl2);
			}else
			{
				var points = feature.geometry;
				var id = '';
				var titles = '';
				var description = '';
				var fillColor = 'gray';
				var iconUrl = '';
				var iconUrl2 = '';
				var categoryId = categoryDict[vectorName];
				if(pointDataList[feature.id]){
			 		id = pointDataList[feature.id].id;
			 		titles = pointDataList[feature.id].title;
			 		description = pointDataList[feature.id].description;
			 		fillColor = pointDataList[feature.id].strokeColor;
			 		iconUrl = pointDataList[feature.id].iconUrl;
			 		iconUrl2 = pointDataList[feature.id].iconUrl2;
			 	}
				htmlContent = getDialogHtmlForPoint(feature.id,dialogTitle,points,id,titles,description,fillColor,categoryId,iconUrl,iconUrl2);
			}
				
			selectedFeature = feature;     
			popup = new OpenLayers.Popup.FramedCloud("feature", mouseLonlat,null,htmlContent,null, true, onPopupClose);
			feature.popup = popup;
			map.addPopup(popup);
		            
		}
       
        function init()
        {

        	lightlinelayer = new OpenLayers.Layer.Vector("光缆线路(共${l1}个)");	
            linelayer = new OpenLayers.Layer.Vector("电缆线路(共${l2}个)");
            policelightlinelayer = new OpenLayers.Layer.Vector("警务室光缆(共${l3}个)");
            
            pointlayer = new OpenLayers.Layer.Vector("井盖标记(共${p1}个)");
            addlayer = new OpenLayers.Layer.Vector("地图增项(共${po1}个)");	
            lineoutlayer = new OpenLayers.Layer.Vector("线路出土点(共${p2}个)");
            subdeplayer = new OpenLayers.Layer.Vector("分局单位(共${p3}个)");
            repairlayer = new OpenLayers.Layer.Vector("维修点标记(共${p4}个)");	
            shortcutlayer = new OpenLayers.Layer.Vector("快速定位标记(共${p5}个)");

            titleTextLayer = new OpenLayers.Layer.Vector("标记名称");
           
            
			map = new OpenLayers.Map('map' ,{controls:[new OpenLayers.Control.MouseDefaults()]});
			OpenLayers.Feature.Vector.style['default']['strokeWidth'] = '4';
			OpenLayers.Feature.Vector.style['default']['strokeColor'] = '#EE9900';
			OpenLayers.Feature.Vector.style['default']['fillColor'] = '#EE9900';
			OpenLayers.Feature.Vector.style['select']['strokeWidth'] = '4';
			OpenLayers.Feature.Vector.style['select']['strokeColor'] = 'red';
			OpenLayers.Feature.Vector.style['select']['fillColor'] = 'red';
			
			map.events.register("mousemove", map, function(e) { 
                var position = this.events.getMousePosition(e);
				mouseLonlat = this.getLonLatFromPixel(e.xy);
   		 	});
            layer = new OpenLayers.Layer.TileCache("南开区灰度地图",
                ["http://" +mapserverip + "/"],
                "nankaiBW",
                {
                    'format': 'image/png',
					maxResolution: 0.15,
					maxExtent: new OpenLayers.Bounds(-180, -90, 180, 90),
					displayOutsideMaxExtent: true,
					numZoomLevels: 7
                }
            );

            layerColorFull = new OpenLayers.Layer.TileCache("南开区彩色地图",
                    ["http://" +mapserverip + "/"],
                    "nankai",
                    {
                        'format': 'image/png',
    					maxResolution: 0.15,
    					maxExtent: new OpenLayers.Bounds(-180, -90, 180, 90),
    					displayOutsideMaxExtent: true,
    					numZoomLevels: 7
                    }
                );
            
            map.addLayers([layer,layerColorFull,lightlinelayer,linelayer,policelightlinelayer,pointlayer,addlayer,lineoutlayer,subdeplayer,repairlayer,shortcutlayer,titleTextLayer]);
            map.setCenter(new OpenLayers.LonLat(-103, 0), 3);
			map.addControl(new OpenLayers.Control.PanZoomBar());
			map.addControl(new OpenLayers.Control.MousePosition());
			map.addControl(new OpenLayers.Control.LayerSwitcher());
			map.addControl(new OpenLayers.Control.KeyboardDefaults());

			var overview = new OpenLayers.Control.OverviewMap();
			map.addControl(overview);
			//overview.maximizeControl();            
 			
 			drawControls = {
                    point: new OpenLayers.Control.DrawFeature(pointlayer, OpenLayers.Handler.Point),
                    line: new OpenLayers.Control.DrawFeature(linelayer, OpenLayers.Handler.Path),
                    lightline: new OpenLayers.Control.DrawFeature(lightlinelayer, OpenLayers.Handler.Path),
                    policelightline: new OpenLayers.Control.DrawFeature(policelightlinelayer, OpenLayers.Handler.Path),
                    add: new OpenLayers.Control.DrawFeature(addlayer, OpenLayers.Handler.Polygon),
                    lineout: new OpenLayers.Control.DrawFeature(lineoutlayer, OpenLayers.Handler.Point),
                    repair: new OpenLayers.Control.DrawFeature(repairlayer, OpenLayers.Handler.Point),
                    subdep: new OpenLayers.Control.DrawFeature(subdeplayer, OpenLayers.Handler.Point),
                    shortcut: new OpenLayers.Control.DrawFeature(shortcutlayer, OpenLayers.Handler.Point)    
                };

            for(var key in drawControls) {
              map.addControl(drawControls[key]);
            }

            document.getElementById('noneToggle').checked = true;

            //
        	//鼠标选择
        	selectControl = new OpenLayers.Control.SelectFeature([lightlinelayer,policelightlinelayer,pointlayer,linelayer,addlayer,lineoutlayer,subdeplayer,repairlayer,shortcutlayer],
                        {hover:false, onSelect: onFeatureSelect, onUnselect: onFeatureUnselect});
        	map.addControl(selectControl);
        	selectControl.activate();

        	//初始化已填的相关信息
        	dataListPoint = initFeaturePoint(pointlayer,'1');
        	dataListLineout = initFeaturePoint(lineoutlayer,'2');
        	//dataListSubdep = initFeaturePoint(subdeplayer,'3');
        	dataListSubdep = initFeatureImagePoint(subdeplayer,'3','img/dep.png');
        	dataListRepair = initFeaturePoint(repairlayer,'4');
        	//dataListShortcut = initFeaturePoint(shortcutlayer,'5');
        	dataListShortcut = initFeatureImagePoint(shortcutlayer,'5','img/position.png');

        	tmpList1 = concatTripleArray(dataListPoint,dataListLineout,dataListSubdep);
        	tmpList2 = concatTwoArray(dataListRepair,dataListShortcut);
        	pointDataList = concatTwoArray(tmpList1,tmpList2);
        	
        	dataListLightline = initFeatureLine(lightlinelayer,"1");
        	dataListLine = initFeatureLine(linelayer,"2");
        	dataListPoliceLightline = initFeatureLine(policelightlinelayer,"3");

        	polygonDataList = initFeaturePolygon(addlayer,'1')
        	
        	//lineDataList = concatTwoArray(dataListLightline,dataListLine);
        	lineDataList = concatTripleArray(dataListLightline,dataListLine,dataListPoliceLightline);
        	//pointDataList
        	//保存数据到一个数组中，方便访问
        	//dataList = concatTripleArray(dataListPoint,dataListLightline,dataListLine);
        }

        OpenLayers.Util.onImageLoadError = function() {
            /**
             * For images that don't exist in the cache, you can display
             * a default image - one that looks like water for example.
             * To show nothing at all, leave the following lines commented out.
             */

            //this.src = "../img/blank.gif";
            //this.style.display = "";
        };

        function toggleControl(element) {
            for(key in drawControls) {
                var control = drawControls[key];
                if(element.value == key && element.checked) 
                {
                    control.activate();
                } else {
                    control.deactivate();
                }
            }
        }

        function submitForm()
		{ 
			$('#pointForm').ajaxSubmit();
            return false;
		}

		function changeColor(color)
		{
			$('#fillColor').val(color);
			$('#fillColor').css("background-color",color);
			return false;
		}

		
		function colorSelector(color)
		{
			$('#optionContent').val(color);
			$('#optionContent').css("background-color",color);
			return false;
		}

		function deleteSetting(id)
		{
			$('#navgationItem'+id).remove(); 
			deleteSettingById(id);
		}

		function saveSetting()
		{
			settingId = saveSettingWithContent($('#optionName').val(),$('#optionContent').val());
			//alert(settingId);
			//alert($('#optionName').val());
			//alert($('#optionContent').val());
			htmlContent = '<div class="navgationItem" id="navgationItem'+settingId+'"><span>'+$('#optionName').val()+'</span><span><input name="optionContent" value=""   style="height:12px;width:60px;font-size:14px;background-color: '+$('#optionContent').val()+';border:0px;" readonly="readonly"/></span><a href="" class="delButton" onClick="deleteSetting(\''+settingId+'\');return false;"></a></div>';
			$('.navgationBodySetting').append(htmlContent);
		}
		//navgationBodyShortcut
		function initShortcutList()
		{
			htmlContent = generateHTML(dataListShortcut);
			$('.navgationBodyShortcut').append(htmlContent);
		}
		//系统设置初始化
		function initSystemSetting()
		{
			settings = getSettingByCategoryId('1');
			htmlContent = '';
			for(index in settings)
			{
				//alert(settings[index].optionContent);
				//<div class="navgationItem"><span>井盖</span><span style="color:red;"><input name="optionContent" value=""   style="height:12px;width:60px;font-size:14px;background-color: green;border:0px;" readonly="readonly"/></span><a href="" class="delButton"></a></div>
				htmlContent = htmlContent + '<div class="navgationItem" id="navgationItem'+settings[index].id+'"><span>'+settings[index].optionName+'</span><span><input name="optionContent" value=""   style="height:12px;width:60px;font-size:14px;background-color: '+settings[index].optionContent+';border:0px;" readonly="readonly"/></span><a href="" class="delButton" onClick="deleteSetting(\''+settings[index].id+'\');return false;"></a></div>'
			}
			$('.navgationBodySetting').append(htmlContent);
		}
        $(document).ready(function(){
			init();
			//系统设置初始化
			initSystemSetting();
			initShortcutList();
			$('#navarea').mouseover(function(){
				$(this).css({'filter:alpha':'alpha(opacity=85)','-moz-opacity':'0.85','opacity':'0.85'});
			});
			$('#navarea').mouseout(function(){
				$(this).css({'filter:alpha':'alpha(opacity=0)','-moz-opacity':'0','opacity':'0'});
			});
			$('#mysqldump').mouseover(function(){
				$(this).css({'filter:alpha':'alpha(opacity=85)','-moz-opacity':'0.85','opacity':'0.85'});
			});
			$('#mysqldump').mouseout(function(){
				$(this).css({'filter:alpha':'alpha(opacity=0)','-moz-opacity':'0','opacity':'0'});
			});
			$('#downButton').click(function(){
				$('#navgation').hide();
				$('#navgationSmall').show();
				return false;
			});

			$('#upButton').click(function(){
				$('#navgationSmall').hide();
				$('#navgation').show();
				return false;
			});

			$('#downButton2').click(function(){
				$('#navgation2').hide();
				$('#navgationSmall2').show();
				return false;
			});

			$('#upButton2').click(function(){
				$('#navgationSmall2').hide();
				$('#navgation2').show();
				return false;
			});
			//
			$('#saveOptionSetting').click(function(){
				saveSetting();
				return false;
			});
        });
	</script>
  </head>
  
   <body>
      

        
        <div id="map" class="smallmap"></div>
		<div id="policeController">	
			<input type="radio" name="type" value="none" id="noneToggle" onclick="toggleControl(this);" checked="checked" />
			<label for="noneToggle">浏览地图</label>
			
			<input type="radio" name="type" value="point" id="pointToggle" onclick="toggleControl(this);" />
			<label for="pointToggle">标记井盖</label>
			
			<input type="radio" name="type" value="line" id="lineToggle" onclick="toggleControl(this);" />
			<label for="lineToggle">绘制电缆</label>
			
			<input type="radio" name="type" value="lightline" id="lightlineToggle" onclick="toggleControl(this);" />
			<label for="lightlineToggle">绘制光缆</label>
			
			<input type="radio" name="type" value="policelightline" id="policelightlineToggle" onclick="toggleControl(this);" />
			<label for="policelightlineToggle">绘制警务室光缆</label>
			
			<input type="radio" name="type" value="add" id="addToggle" onclick="toggleControl(this);" />
			<label for="addToggle">绘制地图增项</label>
			
			<input type="radio" name="type" value="lineout" id="lineoutToggle" onclick="toggleControl(this);" />
			<label for="lineoutToggle">标记线路出土点</label>
			
			<input type="radio" name="type" value="subdep" id="subdepToggle" onclick="toggleControl(this);" />
			<label for="subdepToggle">标记分局机构</label>
			
			<input type="radio" name="type" value="repair" id="repairToggle" onclick="toggleControl(this);" />
			<label for="repairToggle">标记维修点</label>
			
			<input type="radio" name="type" value="shortcut" id="shortcutToggle" onclick="toggleControl(this);" />
			<label for="shortcutToggle">标记快速定位点</label>
		</div>
		
		<div id="navarea">
			<a href="User.do?action=logout">退出系统</a>
		</div>
		<div id="mysqldump">
			<a href="Point.do?action=mysqlDump">导出数据</a>
		</div>
		<div id="navgation">
			<div id="navgationHeader"><span style="float:left;">快速定位</span><a href="#" id="downButton"><img src="img/down.png" /></a></div>
			<div class="clear"></div>
			<div id="navgationBody" class="navgationBodyShortcut">
						
			</div>
		</div>
		<div id="navgationSmall">
			<div id="navgationHeader"><span style="float:left;">快速定位</span><a href="#" id="upButton"><img src="img/up.png" /></a></div>
		</div>
		<div id="navgation2">
			<div id="navgationHeader"><span style="float:left;">系统设置</span><a href="#" id="downButton2"><img src="img/down.png" /></a></div>
			<div class="clear"></div>
			<div id="navgationBody" class="navgationBodySetting">
			
				<div class="clear"></div>
				<div>名称<input name="optionName" value="" style="width:100px;font-size:14px;" id="optionName"/>&nbsp;颜色<input name="optionContent" value="red"   style="width:60px;font-size:14px;background-color:red;" readonly="readonly" id="optionContent"/>&nbsp;<a href="" id="saveOptionSetting">保存</a></div>
				<div class="clear"></div>
				<div id="colorSelector" class="colorSelector" style="padding:2px 10px;">
					<a href="" style="width: 10px; height: 10px; background-color: green; margin: 0px; padding: 0pt 3px;" onclick="colorSelector('green');return false;">&nbsp;</a>
					<a href="" style="width: 10px; height: 10px; background-color: blue; margin: 0px; padding: 0pt 3px;" onclick="colorSelector('blue');return false;">&nbsp;</a>
					<a href="" style="width: 10px; height: 10px; background-color: red; margin: 0px; padding: 0pt 3px;" onclick="colorSelector('red');return false;">&nbsp;</a>
					<a href="" style="width: 10px; height: 10px; background-color: gray; margin: 0px; padding: 0pt 3px;" onclick="colorSelector('gray');return false;">&nbsp;</a>
					<a href="" style="width: 10px; height: 10px; background-color: yellow; margin: 0px; padding: 0pt 3px;" onclick="colorSelector('yellow');return false;">&nbsp;</a>
					<a href="" style="width: 10px; height: 10px; background-color: rgb(169, 10, 8); margin: 0px; padding: 0pt 3px;" onclick="colorSelector('#A90A08');return false;">&nbsp;</a>
					<a href="" style="width: 10px; height: 10px; background-color: rgb(119, 119, 204); margin: 0px; padding: 0pt 3px;" onclick="colorSelector('#7777CC');return false;">&nbsp;</a>
					<a href="" style="width: 10px; height: 10px; background-color: rgb(107, 178, 208); margin: 0px; padding: 0pt 3px;" onclick="colorSelector('#6BB2D0');return false;">&nbsp;</a>
					<a href="" style="width: 10px; height: 10px; background-color: rgb(51, 102, 153); margin: 0px; padding: 0pt 3px;" onclick="colorSelector('#336699');return false;">&nbsp;</a>
					<a href="" style="width: 10px; height: 10px; background-color: rgb(170, 42, 255); margin: 0px; padding: 0pt 3px;" onclick="colorSelector('#AA2AFF');return false;">&nbsp;</a>
					<a href="" style="width: 10px; height: 10px; background-color: rgb(255, 42, 127); margin: 0px; padding: 0pt 3px;" onclick="colorSelector('#FF2A7F');return false;">&nbsp;</a>
					<a href="" style="width: 10px; height: 10px; background-color: rgb(42, 255, 85); margin: 0px; padding: 0pt 3px;" onclick="colorSelector('#2AFF55');return false;">&nbsp;</a>
					<a href="" style="width: 10px; height: 10px; background-color: rgb(255, 224, 104); margin: 0px; padding: 0pt 3px;" onclick="colorSelector('#FFE068');return false;">&nbsp;</a>	
				</div>
			</div>
		</div>
		<div id="navgationSmall2">
			<div id="navgationHeader"><span style="float:left;">系统设置</span><a href="#" id="upButton2"><img src="img/up.png" /></a></div>
		</div>
  </body>
</html:html>
