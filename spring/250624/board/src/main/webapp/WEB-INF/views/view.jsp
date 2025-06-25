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
        <h2>글 보기</h2>

        <!-- 글 정보 -->
        <div class="mb-3">
            <label for="id" class="form-label d-inline">글번호 (ID): </label>
            <p id="id" class="d-inline">123</p>
        </div>

        <div class="mb-3">
            <label for="title" class="form-label d-inline">제목: </label>
            <h4 id="title" class="d-inline">이곳에 제목을 입력하세요</h4>
        </div>

        <div class="mb-3">
            <label for="author" class="form-label d-inline">작성자: </label>
            <p id="author" class="d-inline">홍길동</p>
        </div>

        <div class="mb-3">
            <label for="createdate" class="form-label d-inline">작성일: </label>
            <p id="createdate" class="d-inline">2025-06-25</p>
        </div>

        <div class="mb-3">
            <label for="viewcnt" class="form-label d-inline">조회수: </label>
            <p id="viewcnt" class="d-inline">1234</p>
        </div>

        <div class="mb-3">
            <label for="content" class="form-label d-inline">내용: </label>
            <p id="content">이곳에 글의 내용을 입력하세요. 글 내용은 여러 줄로 길게 작성할 수 있습니다.</p>
        </div>

        <div class="mb-3">
            <label for="attachment" class="form-label d-inline">첨부파일: </label>
            <p id="attachment">첨부파일 없음</p>
        </div>

        <!-- 수정, 삭제 버튼 -->
        <div class="mt-4">
            <button class="btn btn-secondary">수정</button>
            <button class="btn btn-danger">삭제</button>
        </div>
    </div>

    <!-- Bootstrap JS (선택사항) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
