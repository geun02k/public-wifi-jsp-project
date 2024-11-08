
window.onload = function() {
    document.querySelector("#find-me").addEventListener("click", getMyLocation);
    //document.querySelector("#find-me").addEventListener("click", geoFindMe);
}

/**
 * 내 위치 가져오기
 */
function getMyLocation() {
    const lat = document.querySelector("#lat"); // 위도
    const lnt = document.querySelector("#lnt"); // 경도

    function success(position) {
        lat.value = parseFloat(position.coords.latitude);
        lnt.value = parseFloat(position.coords.longitude);
        // console.log("getMyLocation 리턴값 : " + position.coords.latitude + ", " + position.coords.longitude);
        // console.log("getMyLocation 셋팅값 : " + lat.value + ", " + lnt.value);
    }

    function error() {
        alert("현재 위치를 가져올 수 없음");
    }

    if (!navigator.geolocation) {
        alert("브라우저가 위치 정보를 지원하지 않음");
    } else {
        navigator.geolocation.getCurrentPosition(success, error);
    }
}

/**
 * 위치정보 가져오기 테스트코드
 */
function geoFindMe() {
    const status = document.querySelector("#status");
    const mapLink = document.querySelector("#map-link");

    mapLink.href = "";
    mapLink.textContent = "";

    function success(position) {
        const latitude = position.coords.latitude;
        const longitude = position.coords.longitude;

        status.textContent = "";
        mapLink.href = `https://www.openstreetmap.org/#map=18/${latitude}/${longitude}`;
        mapLink.textContent = `위도: ${latitude} °, 경도: ${longitude} °`;
    }

    function error() {
        status.textContent = "현재 위치를 가져올 수 없음";
    }

    if (!navigator.geolocation) {
        status.textContent = "브라우저가 위치 정보를 지원하지 않음";
    } else {
        status.textContent = "위치 파악 중…";
        navigator.geolocation.getCurrentPosition(success, error);
    }
}
