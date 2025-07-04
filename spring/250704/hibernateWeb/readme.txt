##web에서 하이브네이트 사용하기
#maven web 프로젝트 기본설정
#HibernateUtil.java를 작성하여 세션관리
#src/resources/하이브네이트 설정파일(db접속) 
<mapping resource="Student.hbm.xml" /> resource경로 확인
#src/resources/테이블 맴핑설정파일확인(Student.hbm.xml)
해당파일에서 반드시 확인해야하는 사항은
<class name="hibernate.Student" table="student">
클래스 name은 src/main/java/vo객체 경로를 확인하여 작성
#dao파일에서 HibernateUtil.java를 이용하여 세션이 연결되는지 확인
참고)별도의 설정파일명을 로드 없이도 웹에서는 하이브네이트 설정파일이 로드됨
아래의 코드가 설정파일을 로드하는 역할을 함
sessionFactory=new Configuration().configure().buildSessionFactory();
