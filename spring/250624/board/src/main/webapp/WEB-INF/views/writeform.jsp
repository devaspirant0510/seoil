<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>입력 폼</title>
    <!-- Bootstrap CSS CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEJ6HhA6vi5E2Y1S8g0KAtWqytFGT+Gb4UjT79IZl2jHjGrbi3wwi5mX5wLPw" crossorigin="anonymous">
</head>
<body>
    <div class="container mt-5">
        <h2>입력 폼</h2>
        <form action="/board/write" method="post" >
            <!-- 제목 -->
            <div class="mb-3">
                <label for="title" class="form-label">제목</label>
                <input type="text" class="form-control" id="title" name="title" required>
            </div>

            <!-- 내용 -->
            <div class="mb-3">
                <label for="content" class="form-label">내용</label>
                <textarea class="form-control" id="content" name="content" rows="4" required></textarea>
            </div>

            <!-- 첨부파일 -->
            <div class="mb-3">
                <label for="attachment" class="form-label">첨부파일</label>
                <input type="file" class="form-control" id="attachment" name="attachment">
            </div>

            <!-- 작성자 -->
            <div class="mb-3">
                <label for="author" class="form-label">작성자</label>
                <input type="text" class="form-control" id="author" name="author" required>
            </div>

            <button type="submit" class="btn btn-primary">제출</button>
        </form>
    </div>

    <!-- Bootstrap JS CDN -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz4fnFO9gybYb3Z3tJlM2Y6gshYETkxU4Rym1Njlz5eVZN5wBUs0Qb2nxr" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0ey6sk0B9k0V8VV1M5f3sAgaRbFe49fFh0lYG/60bskfK2Q3lfRl7Hh5" crossorigin="anonymous"></script>
</body>
</html>
