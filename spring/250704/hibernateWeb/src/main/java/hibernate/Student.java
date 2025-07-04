package hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

//xml파일로 테이블을 연결할 경우 @Entity를 필요없음
@ToString
public class Student implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 순번. */
	@Id
	@SequenceGenerator
	(name = "student_seq",sequenceName ="student_seq",allocationSize = 1 )
	@GeneratedValue
	(strategy = GenerationType.SEQUENCE, generator = "student_seq")
	//@Column(name="id", unique = true, nullable = false)
	private Integer id;

	/** 학번. */
	private String sid;

	/** 이름. */
	private String name;

	/** 전화번호. */
	private String hp;

	/**
	 * 생성자.
	 */
	public Student() {
	}

	/**
	 * 순번을 설정합니다..
	 * 
	 * @param id
	 *            순번
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 순번을 가져옵니다..
	 * 
	 * @return 순번
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * 학번을 설정합니다..
	 * 
	 * @param sid
	 *            학번
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}

	/**
	 * 학번을 가져옵니다..
	 * 
	 * @return 학번
	 */
	public String getSid() {
		return this.sid;
	}

	/**
	 * 이름을 설정합니다..
	 * 
	 * @param name
	 *            이름
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 이름을 가져옵니다..
	 * 
	 * @return 이름
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 전화번호을 설정합니다..
	 * 
	 * @param hp
	 *            전화번호
	 */
	public void setHp(String hp) {
		this.hp = hp;
	}

	/**
	 * 전화번호을 가져옵니다..
	 * 
	 * @return 전화번호
	 */
	public String getHp() {
		return this.hp;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Student other = (Student) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
