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

}


