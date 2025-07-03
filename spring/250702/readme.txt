#@mybatis패키지는 xml을 이용하여 처리
인터페이스 mybatis파일은 xml파일과 매칭되고
xml파일은 sql문으로 처리됨

##mybatisv2패키지는 어노테이션을 이용하여 처리\
@Mapper를 사용하지 않을 경우 스프링 빈 설정파일에서 다음 내용을 추가
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
<property name="basePackage" value="mybatisv2"></property>
</bean>
@Mapper을 사용할 경우  스프링 빈 설정파일에서 다음 내용을 추가
<mybatis-spring:scan base-package="mybatisv2"/>

##subquery를 사용하는 경우
연산기호를 사용할 경우 주의 사항 : <![CDATA[ sql문장 ]]>
<select id="boardsPage" resultType="board.model.Board">
<![CDATA[
select * from ( 
 select ROWNUM AS r,ID,TITLE,AUTHOR,CREATEDAT,CONTENT,ATTACHMENT,VIEWCNT,TYPE,ISDEL,UPDATEDAT,PARENTID,TAB
 from board 
 where ROWNUM <= #{no} * 5
 ORDER BY ID DESC) t 
 where r > (#{no} - 1) * 5 AND r <= #{no} * 5
 ]]>
</select>