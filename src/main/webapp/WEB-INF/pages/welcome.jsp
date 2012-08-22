<!doctype html>
<!-- Conditional comment for mobile ie7 blogs.msdn.com/b/iemobile/ -->
<!--[if IEMobile 7 ]>    <html class="no-js iem7" lang="en"> <![endif]-->
<!--[if (gt IEMobile 7)|!(IEMobile)]><!--> <html class="no-js" lang="en"> <!--<![endif]-->

<head>
  <meta charset="utf-8">

  <title>Mood</title>
  <meta name="description" content="Track your mood and the mood of the world">

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

<body>

  <div id="container">
    <header>

    </header>
    <div id="main" role="main">
		${message}</br>
		Select your mood</br>
		<form method="post" action="mood/add">
			<input type="hidden" id="latitude" name="latitude" value="0"/>
			<input type="hidden" id="longitude" name="longitude" value="0"/>
			<input type="submit" id="mood" name="mood" value="Happy"/><input type="submit" id="mood" name="mood" value="Sad"/></br>
			<input type="submit" id="mood" name="mood" value="Relaxed"/><input type="submit" id="mood" name="mood" value="Stressed"/></br>
			<input type="submit" id="mood" name="mood" value="Excited"/><input type="submit" id="mood" name="mood" value="Anxious"/></br>
			<input type="submit" id="mood" name="mood" value="Clarity"/><input type="submit" id="mood" name="mood" value="Depressed"/></br>		
			<input type="submit" id="mood" name="mood" value="Brave"/><input type="submit" id="mood" name="mood" value="Tired"/></br>	
			<input type="submit" id="mood" name="mood" value="Warm"/><input type="submit" id="mood" name="mood" value="Cold"/></br>
			<input type="submit" id="mood" name="mood" value="Hyper"/><input type="submit" id="mood" name="mood" value="Nervous"/></br>
			<input type="submit" id="mood" name="mood" value="Confident"/><input type="submit" id="mood" name="mood" value="Scared"/></br>
			<input type="submit" id="mood" name="mood" value="Loved"/><input type="submit" id="mood" name="mood" value="Bored"/></br>
		</form>
		<br/>
		<div id="Output"></div>
    </div>

    <footer>

    </footer>
  </div> <!--! end of #container -->


  <!-- JavaScript at the bottom for fast page loading -->

  <!-- Grab Google CDN's jQuery, with a protocol relative URL; fall back to local if necessary -->
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
  <script>
	navigator.geolocation.getCurrentPosition(gotPosition, errorGettingPosition,{'enableHighAccuracy':true,'timeout':10000,'maximumAge':0});

	function gotPosition(pos) {
		document.getElementById("latitude").value = pos.coords.latitude;
		document.getElementById("longitude").value = pos.coords.longitude;
		
		var outputStr =
		"latitude:"+ document.getElementById("latitude").value +"<br>"+
		"longitude:"+ document.getElementById("longitude").value +"<br>"+
		"accuracy:"+ pos.coords.accuracy +"<br>"+

		"altitude:"+ pos.coords.altitude +"<br>"+
		"altitudeAccuracy:"+ pos.coords.altitudeAccuracy +"<br>"+
		"heading:"+ pos.coords.heading +"<br>"+
		"speed:"+ pos.coords.speed +"";

		<!-- document.getElementById("Output").innerHTML=outputStr; -->
	}

	function errorGettingPosition(err) {
		if(err.code==1) {
			alert("User denied geolocation.");
		} else if(err.code==2) {
			alert("Position unavailable.");
		} else if(err.code==3) {
			alert("Timeout expired.");
		} else {
			alert("ERROR:"+ err.message);
		}
	}
	</script>

  <!-- scripts concatenated and minified via ant build script-->
  <script src="../js/helper.js"></script>
  <!-- end scripts-->

</body>
</html>