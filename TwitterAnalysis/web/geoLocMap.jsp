<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<script src="http://www.openlayers.org/api/OpenLayers.js"></script>
<script>
	function init() {
		var vectorLayer = new OpenLayers.Layer.Vector("Overlay");
		//handling surrogate strings in Javascript is a nightmare so here comes the code from Google.com

		 	var UnicodeString = (function() {
				function surrogatePairToCodePoint(charCode1, charCode2) {
					return ((charCode1 & 0x3FF) << 10) + (charCode2 & 0x3FF)
							+ 0x10000;
				}

				function stringToCodePointArray(str) {
					var codePoints = [], i = 0, charCode;
					while (i < str.length) {
						charCode = str.charCodeAt(i);
						if ((charCode & 0xF800) == 0xD800) {
							codePoints.push(surrogatePairToCodePoint(charCode, str
									.charCodeAt(++i)));
						} else {
							codePoints.push(charCode);
						}
						++i;
					}
					return codePoints;
				}

				function codePointArrayToString(codePoints) {
					var stringParts = [];
					for ( var i = 0, len = codePoints.length, codePoint, offset, codePointCharCodes; i < len; ++i) {
						codePoint = codePoints[i];
						if (codePoint > 0xFFFF) {
							offset = codePoint - 0x10000;
							codePointCharCodes = [ 0xD800 + (offset >> 10),
									0xDC00 + (offset & 0x3FF) ];
						} else {
							codePointCharCodes = [ codePoint ];
						}
						stringParts.push(String.fromCharCode.apply(String,
								codePointCharCodes));
					}
					return stringParts.join("");
				}

				function UnicodeString(arg) {
					if (this instanceof UnicodeString) {
						this.codePoints = (typeof arg == "string") ? stringToCodePointArray(arg)
								: arg;
						this.length = this.codePoints.length;
					} else {
						return new UnicodeString(arg);
					}
				}

				UnicodeString.prototype = {
					slice : function(start, end) {
						return new UnicodeString(this.codePoints.slice(start, end));
					},

					toString : function() {
						return codePointArrayToString(this.codePoints);
					}
				};

				return UnicodeString;
			})();
		 
		//end of code from google.com
		map = new OpenLayers.Map("mapdiv", {
			controls : [ new OpenLayers.Control.Navigation(),
					new OpenLayers.Control.PanZoomBar() ],
			numZoomLevels : 10
		});
		map.addLayer(new OpenLayers.Layer.OSM());
		epsg4326 = new OpenLayers.Projection("EPSG:4326"); //WGS 1984 projection
		projectTo = map.getProjectionObject(); //The map projection (Spherical Mercator)
		var lonLat = new OpenLayers.LonLat(-0.1279688, 51.5077286).transform(
				epsg4326, projectTo);
		var zoom = 14;
		map.setCenter(lonLat, zoom);

		<s:iterator value="tweetGeoLocation" var="geoLocation" status="stat">
		// Define markers as "features" of the vector layer:
		var feature = new OpenLayers.Feature.Vector(
				new OpenLayers.Geometry.Point("${longitude}", "${latitude}")
						.transform(epsg4326, projectTo), {
					description : UnicodeString("${tweetText}")
				});
		lonLat = new OpenLayers.LonLat("${longitude}", "${latitude}")
				.transform(epsg4326, projectTo);
		vectorLayer.addFeatures(feature);
		</s:iterator>
		map.addLayer(vectorLayer);
		map.setCenter(lonLat, zoom);
		//Add a selector control to the vectorLayer with popup functions
		var controls = {
			selector : new OpenLayers.Control.SelectFeature(vectorLayer, {
				onSelect : createPopup,
				onUnselect : destroyPopup
			})
		};
		function createPopup(feature) {
			feature.popup = new OpenLayers.Popup.FramedCloud("pop",
					feature.geometry.getBounds().getCenterLonLat(), null,
					'<div class="markerContent">'
							+ feature.attributes.description + '</div>', null,
					true, function() {
						controls['selector'].unselectAll();
					});
			//feature.popup.closeOnMove = true;
			map.addPopup(feature.popup);
		}

		function destroyPopup(feature) {
			feature.popup.destroy();
			feature.popup = null;
		}

		map.addControl(controls['selector']);
		controls['selector'].activate();
	}
</script>



<title>GeoLocations of the Query</title>
<style>
#mapdiv {
	width: 100%;
	height: 600px;
}
</style>
</head>
<body onload="init();">
	<h1>GeoLocations of selected Query on Map</h1>
	<p>
		Location Count:
		<s:property value="pointsOnMap" />
	</p>
	<div id="mapdiv"></div>
	<p>LatLong</p>
</body>
</html>