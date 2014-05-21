/*
 * 点：生成对话框内容.
 * deleteImage:第二个参数点线面
 */
function getDialogHtmlForPoint(featureId,dialogTitle,points,id,titles,description,fillColor,categoryId,iconUrl,iconUrl2){
	   iconUrlFileHtml = iconUrl == '' ? '':'<img src="uploads/'+iconUrl+'" style="width:380px;height:290px;" alt="标志图片"/><br/><a href="#" onClick="deleteImage('+id+',\'point\',1);return false;">删除这张图片</a><br/><br/>';
	   iconUrlFileHtml2 = iconUrl2 == '' ? '':'<img src="uploads/'+iconUrl2+'" style="width:380px;height:290px;" alt="标志图片"/><br/><a href="#" onClick="deleteImage('+id+',\'point\',2);return false;">删除这张图片</a><br/><br/>';
	   
	   html = '<div id="dialogContent" style="height:400px;width:400px; overflow-y: auto; font-size: 12px;padding:0px;" class="dialogContent">'
			+ '<div id="dialogHeader">'
			+ '	<h1 id="dialogTitle">' + dialogTitle + '</h1>'
			+ '</div>'
			+ '<div class="clear"></div>'
			+ '<div id="dialogBody">'
			+ '<form name="pointForm" id="pointForm" method="post" action="/Map/Point.do?action=save" enctype="multipart/form-data" style="margin: 0pt;">'
			+ '	<input name="points" value="' + points + '" id="points" type="hidden">'
			+ '	<input name="categoryId" value="'+categoryId+'" id="categoryId" type="hidden">'
			+ '	<input name="id" value="'+id+'" id="id" type="hidden">'
			+ '	<input name="iconUrl" value="'+iconUrl+'" id="iconUrl" type="hidden">'
			+ '	<input name="iconUrl2" value="'+iconUrl2+'" id="iconUrl2" type="hidden">'
			
			+ '	<div class="dialogItem">'
			+ '		<label for="title">标题</label>'
			+ '		<input gtbfieldid="53" name="title" value="'+titles+'" id="title" style="width: 380px; font-size: 12px;" type="text">'
			+ '	</div>'
			+ '	<div class="dialogItem">	'
			+ '		<label for="description">描述</label>'
			+ '		<textarea name="description" id="description" style="width: 380px; height: 100px; font-size: 12px;">'+description+'</textarea>'
			+ '	</div>'
			+ '	<div class="dialogItem">'
			+ iconUrlFileHtml
			+ '		<label for="iconUrl">标志图片</label>'
			+ '		<input name="iconUrlFile" value="" style="width: 320px; font-size: 12px;" type="file">'
			+ '	</div>'
			+ '	<div class="dialogItem">'
			+ iconUrlFileHtml2
			+ '		<label for="iconUrl">标志图片</label>'
			+ '		<input name="iconUrlFile2" value="" style="width: 320px; font-size: 12px;" type="file">'
			+ '	</div>'
			+ '	<div class="dialogItem">'
			+ '		<label for="fillColor">填充颜色</label>'
			+ '		<input gtbfieldid="55" readOnly="true" name="fillColor" value="'+fillColor+'" id="fillColor" style="width: 80px; font-size: 12px;background-color:'+fillColor+';" type="text" class="iColorPicker">'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:green;margin:0px;padding:0 3px;" onClick="changeColor(\'green\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:blue;margin:0px;padding:0 3px;" onClick="changeColor(\'blue\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:red;margin:0px;padding:0 3px;" onClick="changeColor(\'red\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:gray;margin:0px;padding:0 3px;" onClick="changeColor(\'gray\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:yellow;margin:0px;padding:0 3px;" onClick="changeColor(\'yellow\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#A90A08;margin:0px;padding:0 3px;" onClick="changeColor(\'#A90A08\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#7777CC;margin:0px;padding:0 3px;" onClick="changeColor(\'#7777CC\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#6BB2D0;margin:0px;padding:0 3px;" onClick="changeColor(\'#6BB2D0\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#336699;margin:0px;padding:0 3px;" onClick="changeColor(\'#336699\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#AA2AFF;margin:0px;padding:0 3px;" onClick="changeColor(\'#AA2AFF\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#FF2A7F;margin:0px;padding:0 3px;" onClick="changeColor(\'#FF2A7F\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#2AFF55;margin:0px;padding:0 3px;" onClick="changeColor(\'#2AFF55\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#FFE068;margin:0px;padding:0 3px;" onClick="changeColor(\'#FFE068\');return false;">&nbsp;</a>'
			+ '	</div>'
			+ '	<div class="dialogItem">'
			+ '		<input style="border-left: 3px solid rgb(140, 190, 215); height: 25px; line-height: 25px; font-size: 12px;" id="pointFormSubmit" value="保存" type="button" onClick="submitForm()">'
			+ '		<input style="border-left: 3px solid rgb(140, 190, 215); height: 25px; line-height: 25px; font-size: 12px;" id="pointFormSubmit" value="删除" type="button" onClick="deleteFeature(\''+featureId+'\')">'
			+ '	</div>'
			+ '	</form>'
			+ '</div>'
			+ '</div>';
		return html;
}

/*
 * 面：生成对话框内容.
 */
function getDialogHtmlForPolygon(featureId,dialogTitle,points,id,titles,description,fillColor,categoryId,iconUrl,iconUrl2){
	   iconUrlFileHtml = iconUrl == '' ? '':'<img src="uploads/'+iconUrl+'" style="width:380px;height:290px;" alt="标志图片"/><br/><a href="#" onClick="deleteImage('+id+',\'polygon\',1);return false;">删除这张图片</a><br/><br/>';
	   iconUrlFileHtml2 = iconUrl2 == '' ? '':'<img src="uploads/'+iconUrl2+'" style="width:380px;height:290px;" alt="标志图片"/><br/><a href="#" onClick="deleteImage('+id+',\'polygon\',2);return false;">删除这张图片</a><br/><br/>';
	   
	   html = '<div id="dialogContent" style="height:400px;width:400px; overflow-y: auto; font-size: 12px;padding:0px;" class="dialogContent">'
			+ '<div id="dialogHeader">'
			+ '	<h1 id="dialogTitle">' + dialogTitle + '</h1>'
			+ '</div>'
			+ '<div class="clear"></div>'
			+ '<div id="dialogBody">'
			+ '<form name="pointForm" id="pointForm" method="post" action="/Map/Point.do?action=save" enctype="multipart/form-data" style="margin: 0pt;">'
			+ '	<input name="points" value="' + points + '" id="points" type="hidden">'
			+ '	<input name="categoryId" value="'+categoryId+'" id="categoryId" type="hidden">'
			+ '	<input name="id" value="'+id+'" id="id" type="hidden">'
			+ '	<input name="iconUrl" value="'+iconUrl+'" id="iconUrl" type="hidden">'
			+ '	<input name="iconUrl2" value="'+iconUrl2+'" id="iconUrl2" type="hidden">'
			+ '	<div class="dialogItem">'
			+ '		<label for="title">标题</label>'
			+ '		<input gtbfieldid="53" name="title" value="'+titles+'" id="title" style="width: 380px; font-size: 12px;" type="text">'
			+ '	</div>'
			+ '	<div class="dialogItem">	'
			+ '		<label for="description">描述</label>'
			+ '		<textarea name="description" id="description" style="width: 380px; height: 100px; font-size: 12px;">'+description+'</textarea>'
			+ '	</div>'
			+ '	<div class="dialogItem">'
			+ iconUrlFileHtml
			+ '		<label for="iconUrl">标志图片</label>'
			+ '		<input name="iconUrlFile" value="" style="width: 320px; font-size: 12px;" type="file">'
			+ '	</div>'
			+ '	<div class="dialogItem">'
			+ iconUrlFileHtml2
			+ '		<label for="iconUrl">标志图片</label>'
			+ '		<input name="iconUrlFile2" value="" style="width: 320px; font-size: 12px;" type="file">'
			+ '	</div>'
			+ '	<div class="dialogItem">'
			+ '		<label for="fillColor">填充颜色</label>'
			+ '		<input gtbfieldid="55" readOnly="true" name="fillColor" value="'+fillColor+'" id="fillColor" style="width: 80px; font-size: 12px;background-color:'+fillColor+';" type="text" class="iColorPicker">'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:green;margin:0px;padding:0 3px;" onClick="changeColor(\'green\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:blue;margin:0px;padding:0 3px;" onClick="changeColor(\'blue\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:red;margin:0px;padding:0 3px;" onClick="changeColor(\'red\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:gray;margin:0px;padding:0 3px;" onClick="changeColor(\'gray\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:yellow;margin:0px;padding:0 3px;" onClick="changeColor(\'yellow\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#A90A08;margin:0px;padding:0 3px;" onClick="changeColor(\'#A90A08\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#7777CC;margin:0px;padding:0 3px;" onClick="changeColor(\'#7777CC\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#6BB2D0;margin:0px;padding:0 3px;" onClick="changeColor(\'#6BB2D0\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#336699;margin:0px;padding:0 3px;" onClick="changeColor(\'#336699\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#AA2AFF;margin:0px;padding:0 3px;" onClick="changeColor(\'#AA2AFF\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#FF2A7F;margin:0px;padding:0 3px;" onClick="changeColor(\'#FF2A7F\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#2AFF55;margin:0px;padding:0 3px;" onClick="changeColor(\'#2AFF55\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#FFE068;margin:0px;padding:0 3px;" onClick="changeColor(\'#FFE068\');return false;">&nbsp;</a>'
			+ '	</div>'
			+ '	<div class="dialogItem">'
			+ '		<input style="border-left: 3px solid rgb(140, 190, 215); height: 25px; line-height: 25px; font-size: 12px;" id="pointFormSubmit" value="保存" type="button" onClick="submitForm()">'
			+ '		<input style="border-left: 3px solid rgb(140, 190, 215); height: 25px; line-height: 25px; font-size: 12px;" id="pointFormSubmit" value="删除" type="button" onClick="deleteFeature(\''+featureId+'\')">'
			+ '	</div>'
			+ '	</form>'
			+ '</div>'
			+ '</div>';
		return html;
}
/*
 * 线：生成对话框内容.
 */
function getDialogHtmlForLine(featureId,vectorName,dialogTitle,points,id,titles,linelength,description,fillColor,strokeWidth,categoryId,iconUrl,iconUrl2){
	   iconUrlFileHtml = iconUrl == '' ? '':'<img src="uploads/'+iconUrl+'" style="width:380px;height:290px;" alt="标志图片"/><br/><a href="#" onClick="deleteImage('+id+',\'line\',1);return false;">删除这张图片</a><br/><br/>';
	   iconUrlFileHtml2 = iconUrl2 == '' ? '':'<img src="uploads/'+iconUrl2+'" style="width:380px;height:290px;" alt="标志图片"/><br/><a href="#" onClick="deleteImage('+id+',\'line\',2);return false;">删除这张图片</a><br/><br/>';
	   //alert(getLengthOfALine('' + points));
	   html = '<div id="dialogContent" style="height:400px;width:400px; overflow-y: auto; font-size: 12px;padding:0px;" class="dialogContent">'
			+ '<div id="dialogHeader">'
			+ '	<h1 id="dialogTitle">' + dialogTitle + '</h1>'
			+ '</div>'
			+ '<div class="clear"></div>'
			+ '<div id="dialogBody">'
			+ '<form name="pointForm" id="pointForm" method="post" action="/Map/Point.do?action=save" enctype="multipart/form-data" style="margin: 0pt;">'
			+ '	<input name="points" value="' + points + '" id="points" type="hidden">'
			+ '	<input name="categoryId" value="'+categoryId+'" id="categoryId" type="hidden">'
			+ '	<input name="id" value="'+id+'" id="id" type="hidden">'
			+ '	<input name="iconUrl" value="'+iconUrl+'" id="iconUrl" type="hidden">'
			+ '	<input name="iconUrl2" value="'+iconUrl2+'" id="iconUrl2" type="hidden">'
			+ '	<div class="dialogItem">'
			+ '		<label for="title">标题</label>'
			+ '		<input gtbfieldid="53" name="title" value="'+titles+'" id="title" style="width: 380px; font-size: 12px;" type="text">'
			+ '	</div>'
			+ '	<div class="dialogItem">	'
			+ '		<label for="description">描述</label>'
			+ '		<textarea name="description" id="description" style="width: 380px; height: 100px; font-size: 12px;">'+description+'</textarea>'
			+ '	</div>'
			+ '	<div class="dialogItem">'
			+ '		<label for="title">长度' +　'(长度约为'+getLengthOfALine('' + points) * 100+'米)</label>'　
			+ '		<input gtbfieldid="53" name="linelength" value="'+linelength+'" id="linelength" style="width: 380px; font-size: 12px;" type="text">'
			+ '	</div><br/>'
			+ '	<div class="dialogItem">'
			+ iconUrlFileHtml
			+ '		<label for="iconUrl">标志图片</label>'
			+ '		<input name="iconUrlFile" value="" style="width: 320px; font-size: 12px;" type="file">'
			+ '	</div>'
			+ '	<div class="dialogItem">'
			+ iconUrlFileHtml2
			+ '		<label for="iconUrl">标志图片</label>'
			+ '		<input name="iconUrlFile2" value="" style="width: 320px; font-size: 12px;" type="file">'
			+ '	</div>'
			+ '	<div class="dialogItem">'
			+ '		<label for="fillColor">填充颜色</label>'
			+ '		<input gtbfieldid="55" readOnly="true" name="fillColor" value="'+fillColor+'" id="fillColor" style="width: 80px; font-size: 12px;background-color:'+fillColor+';" type="text" class="iColorPicker">'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:green;margin:0px;padding:0 3px;" onClick="changeColor(\'green\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:blue;margin:0px;padding:0 3px;" onClick="changeColor(\'blue\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:red;margin:0px;padding:0 3px;" onClick="changeColor(\'red\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:gray;margin:0px;padding:0 3px;" onClick="changeColor(\'gray\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:yellow;margin:0px;padding:0 3px;" onClick="changeColor(\'yellow\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#A90A08;margin:0px;padding:0 3px;" onClick="changeColor(\'#A90A08\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#7777CC;margin:0px;padding:0 3px;" onClick="changeColor(\'#7777CC\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#6BB2D0;margin:0px;padding:0 3px;" onClick="changeColor(\'#6BB2D0\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#336699;margin:0px;padding:0 3px;" onClick="changeColor(\'#336699\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#AA2AFF;margin:0px;padding:0 3px;" onClick="changeColor(\'#AA2AFF\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#FF2A7F;margin:0px;padding:0 3px;" onClick="changeColor(\'#FF2A7F\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#2AFF55;margin:0px;padding:0 3px;" onClick="changeColor(\'#2AFF55\');return false;">&nbsp;</a>'
			+ '	<a href="" style="width:10px;height:10px;border 0px ;background-color:#FFE068;margin:0px;padding:0 3px;" onClick="changeColor(\'#FFE068\');return false;">&nbsp;</a>'
			+ '	</div>'
			+ '	<div class="dialogItem">'
			+ '		<label for="title">线条宽度</label>'
			+ '		<input gtbfieldid="53" name="strokeWidth" value="'+strokeWidth+'" id="strokeWidth" style="width: 30px; font-size: 12px;" type="text">'
			+ '	</div>'
			+ '	<div class="dialogItem">'
			+ '		<input style="border-left: 3px solid rgb(140, 190, 215); height: 25px; line-height: 25px; font-size: 12px;" id="pointFormSubmit" value="保存" type="button" onClick="submitForm()">'
			+ '		<input style="border-left: 3px solid rgb(140, 190, 215); height: 25px; line-height: 25px; font-size: 12px;" id="pointFormSubmit" value="复制" type="button" onClick="cloneLine(\''+vectorName+'\',\''+points+'\')">'
			+ '		<input style="border-left: 3px solid rgb(140, 190, 215); height: 25px; line-height: 25px; font-size: 12px;" id="pointFormSubmit" value="删除" type="button" onClick="deleteFeature(\''+featureId+'\')">'
			+ '	</div>'
			+ '	</form>'
			+ '</div>'
			+ '</div>';
		return html;
}
/**
 * 前台提示对话框.
 * @param dialogTitle
 * @param titles
 * @param description
 * @param iconUrl
 * @return
 */
function getPublishedDialogHtml(dialogTitle,titles,description,iconUrl,iconUrl2,lineString,lineLength){
	   lineLengthTitle = '';
	   //if(lineString != '')
		 //  lineLengthTitle = '(大约' + getLengthOfALine('' + lineString) * 100 + '米)'
	   if(lineLength != '')
		   lineLengthTitle = '(' + lineLength + '米)';
	   dialogWidth = 380;
	   dialogHeight = 'height:400px;';
	   iconUrlFileHtml = iconUrl == '' ? '':'<img src="uploads/'+iconUrl+'" style="width:380px;height:290px;" alt="标志图片"/><br/>';
	   iconUrlFileHtml2 = iconUrl2 == '' ? '':'<img src="uploads/'+iconUrl2+'" style="width:380px;height:290px;" alt="标志图片"/><br/>';
	   dialogTitleHtml = dialogTitle =='' ? '<h1 id="dialogTitle">'  + titles + lineLengthTitle + '</h1>':'	<h1 id="dialogTitle">' + dialogTitle + ':' + titles + lineLengthTitle + '</h1>';
	   if(iconUrl == '' && iconUrl2 == '')
	   {
		   dialogWidth = 300;
		   dialogHeight = '';
	   }
	   html = '<div id="dialogContent" style="'+dialogHeight+'width: '+dialogWidth+'px; overflow-y: auto; font-size: 12px;" class="dialogContent">'
			+ '<div id="dialogHeader">'
			+ dialogTitleHtml
			+ '</div>'
			+ '<div class="clear"></div>'
			+ '<div id="dialogBody">'
			+ '	<div class="dialogItem">	'
			+ description
			+ '	</div>'
			+ '	<div class="dialogItem">'
			+ iconUrlFileHtml
			+ '	</div>'
			+ '	<div class="dialogItem">'
			+ iconUrlFileHtml2
			+ '	</div>'
			+ '</div>'
			+ '</div>';
		return html;
}
