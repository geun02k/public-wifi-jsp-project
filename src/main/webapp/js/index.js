
window.onload = function() {
    $("#find-my-location").on("click", getMyLocation);
    $("#search-wifi-list").on("click", getWifiListByCurLocation);
    // document.querySelector("#find-my-location").addEventListener("click", getMyLocation);
    // document.querySelector("#search-wifi-list").addEventListener("click", getWifiListByCurLocation);
    //document.querySelector("#ind-my-location").addEventListener("click", geoFindMe);
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
 * 근처 WIFI 정보 보기
 */
function getWifiListByCurLocation() {

    $.ajax({
       type: "GET"         // HttpMethod
        , url: "/wifi/list" // 서버 URL
        , contentType: "application/json; charset=utf-8" // request 데이터타입
        , dataType: "text"  // response 데이터타입 (실제 서버에서 전달하는 response 타입과 일치해야함.)
        , async: true       //동기,비동기 설정
        , data: {           // requestBody : 서버에 보낼 데이터
             lat: $("#lat").val(),
             lnt: $("#lnt").val()
        }
        , success: function (data, status, xhr) { //통신성공 후 처리로직
            $("#wifi-list-tbody").html(data);
       }
       , error :function (xhr, data) { // 통신실패 후 처리로직
           alert("통신실패");
        }
    });
}

//------------------------------------------------------------------------

// 위치정보 가져오기 테스트코드
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
