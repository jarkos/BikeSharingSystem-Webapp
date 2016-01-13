/**
 * Created by Jarek on 2015-12-26.
 */
var geocoder = new google.maps.Geocoder();

function updateMarkerStatus(str) {
    document.getElementById('markerStatus').innerHTML = str;
}

function updateMarkerPosition(latLng) {
    document.getElementById('info').innerHTML = [
        latLng.lat(),
        latLng.lng()
    ].join(', ');
    document.getElementById('inputLatitude').value = latLng.lat();
    document.getElementById('inputLongitude').value = latLng.lng();
}

function updateMarkerAddress(str) {
    document.getElementById('address').innerHTML = str;
    document.getElementById('inputLocationAddress').value = str;
}

function initialize() {
    var latLng = new google.maps.LatLng(51.759, 19.459);
    var map = new google.maps.Map(document.getElementById('mapCanvas'), {
        zoom: 12,
        center: latLng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });
    var marker = new google.maps.Marker({
        position: latLng,
        title: 'Point A',
        map: map,
        draggable: true
    });

    // Update current position info.
    updateMarkerPosition(latLng);
    geocodePosition(latLng);

    // Add dragging event listeners.
    google.maps.event.addListener(marker, 'dragstart', function() {
        updateMarkerAddress('Szukam adresu...');
    });

    google.maps.event.addListener(marker, 'drag', function() {
//                updateMarkerStatus('Dragging...');
        updateMarkerPosition(marker.getPosition());
    });

    google.maps.event.addListener(marker, 'dragend', function() {
        updateMarkerStatus('Drag ended');
        geocodePosition(marker.getPosition());
    });
}

// Onload handler to fire off the app.
google.maps.event.addDomListener(window, 'load', initialize);