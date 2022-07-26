<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>주소로 장소 표시하기</title>
<%@ include file="/resources/includes/link.jsp" %>
</head>
<body>
	<!-- 다음 주소 API시작 -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d6029e9ff26cd1b25c5eeb4aa4438711&libraries=services"></script>
	<script>
		let addr;
		function test() {
			new daum.Postcode({
				oncomplete : function(data) {
					if (data.userSelectedType == "R") { // 사용자가 선택한 주소가 도로명주소 인 경우
						addr = data.roadAddress;
					} else {
						addr = data.jibunAddress;
					}
				}
			}).open();
		}
		
		function test2() {
			var mapContainer = document.getElementById('map'), // 지도를 표시 할 div 
			mapOption = {
				center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
				level: 3 // 지도의 확대 레벨
			};  
		
			// 지도를 생성합니다    
			var map = new kakao.maps.Map(mapContainer, mapOption); 
			
			// 주소-좌표 변환 객체를 생성합니다
			var geocoder = new kakao.maps.services.Geocoder();
			
			// 주소로 좌표를 검색합니다
			geocoder.addressSearch(addr, function(result, status) {
			
			  // 정상적으로 검색이 완료됐으면 
				if (status === kakao.maps.services.Status.OK) {
					var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
					// 결과값으로 받은 위치를 마커로 표시합니다
					var marker = new kakao.maps.Marker({
				    map: map,
				    position: coords
					});
				
					// 인포윈도우로 장소에 대한 설명을 표시합니다
					var infowindow = new kakao.maps.InfoWindow({
			    	content: '<div style="width:150px;text-align:center;padding:6px 0;">주소</div>'
					});
					infowindow.open(map, marker);
					
					// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
					map.setCenter(coords);
				}
			});    
		}
		
	</script>
	<!-- 다음 주소 API끝 -->
	<div id="map" style="width: 50%; height: 350px; margin: 100px auot;"></div>
	<a href="javascript:test()" class="btn btn-primary">주소찾기</a>
	<a href="javascript:test2()" class="btn btn-primary">위치표시하기</a>
</body>
</html>