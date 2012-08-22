<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:f="http://java.sun.com/jsf/core"
      xmlns:c="http://java.sun.com/jsp/jstl/core">
<%@ page import="org.gg.mood.model.MoodEntry, java.util.ArrayList" %>
<head>
  <meta charset="utf-8">

  <title>Mood</title>
  <meta name="description" content="A fun app called Dinner with Friends">

  <!-- Mobile viewport optimization h5bp.com/ad -->
  <meta name="HandheldFriendly" content="True">
  <meta name="MobileOptimized" content="320">
  <meta name="viewport" content="width=device-width">

  <!-- Home screen icon  Mathias Bynens mathiasbynens.be/notes/touch-icons -->
  <!-- For iPhone 4 with high-resolution Retina display: -->
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../img/h/apple-touch-icon.png">
  <!-- For first-generation iPad: -->
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../img/m/apple-touch-icon.png">
  <!-- For non-Retina iPhone, iPod Touch, and Android 2.1+ devices: -->
  <link rel="apple-touch-icon-precomposed" href="../img/l/apple-touch-icon-precomposed.png">
  <!-- For nokia devices: -->
  <link rel="shortcut icon" href="../img/l/apple-touch-icon.png">

  <!-- iOS web app, delete if not needed. https://github.com/h5bp/mobile-boilerplate/issues/94 -->
  <!-- <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black"> -->
  <!-- <script>(function(){var a;if(navigator.platform==="iPad"){a=window.orientation!==90||window.orientation===-90?"img/startup-tablet-landscape.png":"img/startup-tablet-portrait.png"}else{a=window.devicePixelRatio===2?"img/startup-retina.png":"img/startup.png"}document.write('<link rel="apple-touch-startup-image" href="'+a+'"/>')})()</script> -->
  
  <!-- The script prevents links from opening in mobile safari. https://gist.github.com/1042026 -->
  <!-- <script>(function(a,b,c){if(c in b&&b[c]){var d,e=a.location,f=/^(a|html)$/i;a.addEventListener("click",function(a){d=a.target;while(!f.test(d.nodeName))d=d.parentNode;"href"in d&&(d.href.indexOf("http")||~d.href.indexOf(e.host))&&(a.preventDefault(),e.href=d.href)},!1)}})(document,window.navigator,"standalone")</script> -->
  
  <!-- Mobile IE allows us to activate ClearType technology for smoothing fonts for easy reading -->
  <meta http-equiv="cleartype" content="on">

  <!-- more tags for your 'head' to consider h5bp.com/d/head-Tips -->

  <!-- Main Stylesheet -->
  <link rel="stylesheet" href="../css/style.css">

  <!-- All JavaScript at the bottom, except for Modernizr which enables HTML5 elements & feature detects -->
  <script src="../js/libs/modernizr-2.0.6.min.js"></script>
</head>

<body onload="initialize()">
    <div id="map_canvas" style="height: 600px; width: 800px;"></div>
    <button onclick="toggleHeatmap()">Toggle Heatmap</button>
    <button onclick="changeGradient()">Change gradient</button>
    <button onclick="changeRadius()">Change radius</button>
    <button onclick="changeOpacity()">Change opacity</button>
  </body>


  <!-- JavaScript at the bottom for fast page loading -->

  <!-- Grab Google CDN's jQuery, with a protocol relative URL; fall back to local if necessary --> 
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
  <script>window.jQuery || document.write('<script src="../js/libs/jquery-1.7.1.min.js"><\/script>')</script>
  <script src="https://maps.googleapis.com/maps/api/js?sensor=false&libraries=visualization"></script>
  <script>
  var map, pointarray, heatmap;

  var moodData = [               
            <%  ArrayList moodList = (ArrayList)request.getAttribute("moodList");
              	for (int i=0; i < moodList.size()-1; i++) { 
              		MoodEntry me = (MoodEntry)moodList.get(i); %>
   new google.maps.LatLng(<%= me.getLatitude() %>,<%= me.getLongitude() %>),
          	<% } 
              	MoodEntry me = (MoodEntry)moodList.get(moodList.size()-1); %>
   new google.maps.LatLng(<%= me.getLatitude() %>,<%= me.getLongitude() %>)
];  
  
  function initialize() {
      var mapOptions = {
        zoom: 13,
        center: new google.maps.LatLng(42.4448287,-82.9040256),
        mapTypeId: google.maps.MapTypeId.SATELLITE
      };

      map = new google.maps.Map(document.getElementById('map_canvas'),
          mapOptions);

      pointArray = new google.maps.MVCArray(moodData);

      heatmap = new google.maps.visualization.HeatmapLayer({
        data: pointArray
      });

      heatmap.setMap(map);
    }

    function toggleHeatmap() {
      heatmap.setMap(heatmap.getMap() ? null : map);
    }

    function changeGradient() {
      var gradient = [
        'rgba(0, 255, 255, 0)',
        'rgba(0, 255, 255, 1)',
        'rgba(0, 191, 255, 1)',
        'rgba(0, 127, 255, 1)',
        'rgba(0, 63, 255, 1)',
        'rgba(0, 0, 255, 1)',
        'rgba(0, 0, 223, 1)',
        'rgba(0, 0, 191, 1)',
        'rgba(0, 0, 159, 1)',
        'rgba(0, 0, 127, 1)',
        'rgba(63, 0, 91, 1)',
        'rgba(127, 0, 63, 1)',
        'rgba(191, 0, 31, 1)',
        'rgba(255, 0, 0, 1)'
      ]
      heatmap.setOptions({
        gradient: heatmap.get('gradient') ? null : gradient
      });
    }

    function changeRadius() {
      heatmap.setOptions({radius: heatmap.get('radius') ? null : 20});
    }

    function changeOpacity() {
      heatmap.setOptions({opacity: heatmap.get('opacity') ? null : 0.2});
    }
  </script> 
  </script>

  <!-- scripts concatenated and minified via ant build script-->
  <script src="../js/helper.js"></script>
  <!-- end scripts-->
</html>