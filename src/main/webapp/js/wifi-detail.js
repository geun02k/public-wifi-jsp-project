
window.onload = function() {
    $("#bntAddBookmarkGroup").on("click", addBookmark);
}

function addBookmark() {

    var groupCode = $("#bookmarkGroup option:selected").val();
    var wifiMngrNo = $("#mngrNo").text();
    console.log($("#bookmarkGroup option:selected").val());
    console.log("groupCode = " + groupCode);
    console.log("wifiMngrNo = " + wifiMngrNo);

    // validation check
    if(groupCode == null || groupCode.toString().trim() == '' || parseInt(groupCode) <= 0) {
        alert("북마크 그룹 이름을 선택하세요.");
        return;
    }
    if(mngrNo == null || mngrNo.toString().trim() == '') {
        alert("유효하지 않은 와이파이 관리번호입니다.");
        return;
    }

    var obj = {
        groupCode: groupCode,
        wifiMngrNo: wifiMngrNo
    };

    $.ajax({
        type: "POST",    // HttpMethod
        url: "/bookmark",  // 서버 URL
        contentType: "application/json; charset=utf-8",  // request 데이터타입
        dataType: "text",   // response 데이터타입 (실제 서버에서 전달하는 response 타입과 일치해야함.)
        async: true,        //동기,비동기 설정
        data: JSON.stringify(obj),  // requestBody : 서버에 보낼 데이터
        success: function (data, status, xhr) { //통신성공 후 처리로직
            if(data == "SUCCESS") {
                alert("북마크 추가되었습니다.");
                window.location.reload(); // 재조회를 위한 새로고침
            } else if(data == "FAIL_5") {
                alert("필수 입력값 미존재로 북마크 추가 불가합니다.");
            } else if(data == "FAIL_10") {
                alert("동일 북마크그룹, 관리번호에 대한 북마크가 존재해 북마크 추가 불가합니다.");
            } else {
                alert("북마크 추가에 실패했습니다.");
            }
        },
        error :function (xhr, data) { // 통신실패 후 처리로직
            alert("통신실패");
            return false;
        }
    });
}