function getFeatureData(type,categoryId)
{
	var data;
	$.ajax({
	  async:false,
	  url: "Point.do?action=getFeature&type=" + type + "&categoryId="+categoryId,
	  cache: false,
	  dataType:"json",
	  success: function(json){
	    data = json;
  	  }
	});
	return data;	
}

/*ajax删除*/
function deleteFeatureByIdAndType(id,type)
{
	//alert("Point.do?action=deleteFeature&id=" + id + "&type="+type);
	var data;
	$.ajax({
	  async:false,
	  url: "Point.do?action=deleteFeature&id=" + id + "&type="+type,
	  cache: false,
	  dataType:"json",
	  success: function(json){
	    data = json;
  	  }
	});
	return data;
}

function deleteImage(objId, type, whichImage)
{
	if(!confirm("您确定要删除这个图片吗？点击确定后删除"))
		return;
	
	var data;
	$.ajax({
	  async:false,
	  url: "Point.do?action=deleteImage&id=" + objId + "&type="+type + "&whichImage="+whichImage,
	  cache: false,
	  dataType:"json",
	  success: function(json){
	    data = json;
  	  }
	});
	return data;
}

function deleteSettingById(id)
{
	//alert("Point.do?action=deleteFeature&id=" + id + "&type="+type);
	var data;
	$.ajax({
	  async:false,
	  url: "Point.do?action=deleteSetting&id=" + id,
	  cache: false,
	  dataType:"json",
	  success: function(json){
	    data = json;
  	  }
	});
	return data;
}

function saveSettingWithContent(optionName,optionContent)
{
	var data;
	$.ajax({
	  type: "POST",
	  async:false,
	  url: "Point.do?action=saveSetting",
	  data: "optionName=" + optionName + "&optionContent="+optionContent,
	  cache: false,
	  dataType:"json",
	  success: function(json){
	    data = json;
  	  }
	});
	return data;
}

function getSettingByCategoryId(categoryId)
{
	//alert("Point.do?action=deleteFeature&id=" + id + "&type="+type);
	var data;
	$.ajax({
	  async:false,
	  url: "Point.do?action=getSetting&categoryId=" + categoryId,
	  cache: false,
	  dataType:"json",
	  success: function(json){
	    data = json;
  	  }
	});
	return data;
}