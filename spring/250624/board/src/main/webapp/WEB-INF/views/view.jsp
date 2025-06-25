<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>글 보기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
     <div class="container mt-5">
    <button class="btn btn-primary btn-lg" 
    onclick="location.href='/board/list'">
      <i class="fas fa-arrow-left"></i> 뒤로가기
    </button>
  	</div>
  
        <h2>글 보기</h2>

        <!-- 글 정보 -->
        <div class="mb-3">
            <label for="id" class="form-label d-inline">글번호 (ID): </label>
            <p id="id" class="d-inline">${page.id}</p>
        </div>

        <div class="mb-3">
            <label for="title" class="form-label d-inline">제목: </label>
            <h4 id="title" class="d-inline">${page.title}</h4>
        </div>

        <div class="mb-3">
            <label for="author" class="form-label d-inline">작성자: </label>
            <p id="author" class="d-inline">${page.author}</p>
        </div>

        <div class="mb-3">
            <label for="createdate" class="form-label d-inline">작성일: </label>
            <p id="createdate" class="d-inline">${page.createdate}</p>
        </div>

        <div class="mb-3">
            <label for="viewcnt" class="form-label d-inline">조회수: </label>
            <p id="viewcnt" class="d-inline">${page.viewcnt}</p>
        </div>

        <div class="mb-3">
            <label for="content" class="form-label d-inline">내용: </label>
            <p id="content">${page.content}</p>
        </div>

        <div class="mb-3">
            <label for="attachment" class="form-label d-inline">첨부파일: </label>
            <p id="attachment">${page.attachment}</p>
        </div>

        <!-- 수정, 삭제 버튼 -->
        <div class="mt-4">
            <button class="btn btn-secondary">수정</button>
            <button class="btn btn-danger">삭제</button>
        </div>
    </div>

    <!-- Bootstrap JS (선택사항) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    
</body>
</html>
