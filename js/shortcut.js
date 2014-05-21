function generateHTML(navList){
	htmltext = "<ul>";
 	for(index in navList){
 		htmltext = htmltext + "<li>"
 		+ "<a href=\"javascript:setCenterById(\'" + index + "\')\" class=\"link_item\">"
 		+ navList[index].title
 		+ "</a></li>"
 	}
 	htmltext = htmltext + "</ul>";
 	return htmltext;
}

function setCenterById(index){
	onFeatureUnselect();
	centerLonlat = new OpenLayers.LonLat(Number(dataListShortcut[index].pointX), Number(dataListShortcut[index].pointY));
	map.panTo(centerLonlat);
	selectedFeature = shortcutlayer.getFeatureById(index); 
	//alert(selectedFeature);
	htmlContent = getPublishedDialogHtml('',dataListShortcut[index].title,dataListShortcut[index].description,dataListShortcut[index].iconUrl,dataListShortcut[index].iconUrl2,'','');
	popup = new OpenLayers.Popup.FramedCloud("feature", centerLonlat ,null,htmlContent,null, true, onPopupClose);
	selectedFeature.popup = popup;
	map.addPopup(popup);
	return;
}