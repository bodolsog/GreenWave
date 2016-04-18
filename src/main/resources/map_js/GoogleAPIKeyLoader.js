var head = document.getElementsByTagName('head')[0];
var script = document.createElement('script');
script.type = 'text/javascript';
script.src = 'http://maps.google.com/maps/api/js?key=' + api + '&sensor=false';
head.appendChild(script);