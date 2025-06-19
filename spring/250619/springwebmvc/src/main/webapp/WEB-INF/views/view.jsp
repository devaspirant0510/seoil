<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>정보 확인</title>
    <!-- Bootstrap 4 CDN -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
    <!-- 홈 아이콘 버튼 -->
  <a href="/" class="btn btn-link">
     <i class="fas fa-home fa-2x"></i> 홈으로 가기
  </a>
  
        <div class="card">
            <div class="card-header text-center">
                <h3>사용자 정보</h3>
            </div>
            <div class="card-body">
                <table class="table table-bordered">
                    <tr>
                        <th scope="row" style="width: 20%;">아이디</th>
                        <!-- tag에 contenteditable은 편집모드로 변경 -->
                        <td>${pb.id}</td>
                    </tr>
                    <tr>
                        <th scope="row">이름</th>
                        <td>${pb.name}</td>
                    </tr>
                    <tr>
                        <th scope="row">전화번호</th>
                        <td>${pb.hp}</td>
                    </tr>
                    <tr>
                        <th scope="row">이메일</th>
                        <td>${pb.email}</td>
                    </tr>
                    <tr>
                        <th scope="row">메모</th>
                        <td>${pb.memo}</td>
                    </tr>
                </table>
            </div>
            <div class="card-footer text-center">
                <!-- <a href="javascript:updatebtn(this)" class="btn btn-primary">수정하기</a> -->
                <a href="/phonebook/updateform?id=${pb.id}" class="btn btn-primary">수정하기</a>
                <a href="/phonebook/delete?id=${pb.id}" class="btn btn-danger">삭제하기</a>
            </div>
        </div>
    </div>
	<script>
	function updatebtn(obj){
		//5개의 데이터가 있는 td을 contenteditable 모드로 변경하기
		let tds=document.querySelectorAll("td");
		console.log(tds);
		tds.forEach((td)=>{
			//tr.contenteditable="true"; 
			//하위에 있는 속성에 접근하는 경우 접근불가시 다음코드 이용
			td.setAttribute('contenteditable','true');
			td.style.backgroundColor='#ffffcc';
			//버튼의 값을 변경하기
			console.log(obj.innerText);
			obj.innerText='수정';
		})
	}
	</script>
    <!-- Bootstrap 4 JS, Popper.js, jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
</body>
</html>
