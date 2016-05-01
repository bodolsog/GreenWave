/** Initialize Map */
function initialize() {

  /* Domestic center on Biala Podlaska. */
  var latlng = new google.maps.LatLng(52.03, 23.14);

  var map = new google.maps.Map(document.getElementById("map_canvas"), {
    zoom: 15,
    center: latlng,
    mapTypeId: google.maps.MapTypeId.ROADMAP,
    mapTypeControl: false,
    navigationControl: false,
    streetViewControl: false,
    backgroundColor: "#666970",
    zoomControl: false,
    scaleControl: true
  });

  map.addListener('click', function (event) {

    // Create marker
    var marker = new google.maps.Marker({
      position: event.latLng,
      draggable: true,
      map: map
    });

    // Add to database.
    controller.createMarker(marker);

    // Create infowindow
    marker.infowindow = new google.maps.InfoWindow({
      content: infowindowContent(marker)
    });

    // Update marker on end of dragging
    marker.addListener('dragend', function () {
      controller.updateMarker(this);
    });
  })
}


/**
 * Create infowidow content.
 * @param marker
 * @returns {string}    HTML content of infowindow.
 */
function infowindowContent(marker) {
  return "<div class='infowindow-content'>" +
    "   <dl>" +
    "       <dt>Coordinates:</dt>" +
    "       <dd>Lat:" + marker.getPosition().lat() + ", Lng: " + marker.getPosition().lng() + "</dd>" +
    "   </dl>" +
    "</div>";
}
