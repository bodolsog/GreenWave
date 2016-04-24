// Create and configure new <script> element with type and src.
var script = document.createElement('script');
script.type = 'text/javascript';
script.src = 'http://maps.google.com/maps/api/js?key=' + apiKey + '&sensor=false';

// Appennd to head section.
var head = document.getElementsByTagName('head')[0];
head.appendChild(script);