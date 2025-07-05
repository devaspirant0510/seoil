##하이버네이트 설정사항
#Student.hbm.xml 파일에서
<generator class="assigned" />
assigned는 Hibernate가 데이터베이스에서 ID 값을 자동으로 생성하지 않고
사용자가 직접 id 값을 할당하는 방식을 사용하도록 설정하는 옵션
Hibernate는 새로운 객체가 저장될 때 id 값을 데이터베이스에 저장하기 전에 애플리케이션 코드에서 수동으로 id 값을 할당

#Student.java
@Id: id 필드를 기본 키로 설정
@SequenceGenerator: student_seq라는 시퀸스를 정의
name = "student_seq": 시퀸스 생성기의 이름을 지정
sequenceName = "student_seq": 실제 데이터베이스 시퀸스의 이름을 지정(student_seq라는 시퀸스가 데이터베이스에 존재해야 함)
allocationSize = 1: 시퀸스 값이 한 번에 1씩 증가하도록 설정합니다.
@GeneratedValue: GenerationType.SEQUENCE를 사용하여 시퀸스를 자동으로 생성하도록 지정
strategy = GenerationType.SEQUENCE: 시퀸스를 사용하도록 지정
generator = "student_seq": 사용할 시퀸스 생성기의 이름을 지정

#완성코드
@Id
@SequenceGenerator
(name = "student_seq",sequenceName ="student_seq",allocationSize = 1 )
@GeneratedValue
(strategy = GenerationType.SEQUENCE, generator = "student_seq")
private Integer id;

#main함수에서 입력 확인
Transaction tx=session.beginTransaction();
Student student=new Student();
student.setSid("25010120");
student.setName("홍길동");
student.setHp("010-1111-1111");
session.save(student);
tx.commit();

#오류시 테스트 방법
DROP TABLE student CASCADE CONSTRAINTS;  --테이블 강제삭제
drop sequence student_seq; --시퀸스삭제
select sequence_name from user_sequences; --시퀸스 확인

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

## dao callback사용하기
dao 코드에서 Session session=HibernateUtil.getCurrentSession();
session.beginTransaction();과 session.getTransaction().commit();
HibernateUtil.closeSession();이 반복되게 되는데 이 코드를 간략화시키는 방법

public class HibernateUtilWrapper {

    public static void doTransaction(HibernateTransactionCallback callback) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getCurrentSession();
            tx = session.beginTransaction();

            callback.doInTransaction(session);

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    public interface HibernateTransactionCallback {
        void doInTransaction(Session session);
    }
}

@Repository
public class MainDao {

    public MainDao() {
        System.out.println("dao 생성자 동작확인");
    }

    public void insert(Student student) {
        HibernateUtilWrapper.doTransaction(session -> {
            session.save(student);
        });
    }
}

##JpaRespository를 Spring lagacy에서 사용하기
public interface StudentRepository extends JpaRepository<Student,Integer>{ }
#jpa사용시 확인사항
-라이브러리
-사용법
#자바애플리케이션에서 사용법(main()함수)
#웹프로젝트에서 사용법
