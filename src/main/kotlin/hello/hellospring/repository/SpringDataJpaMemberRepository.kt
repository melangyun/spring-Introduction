package hello.hellospring.repository

import hello.hellospring.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

// JpaRepository <T, ID> : T는 엔티티, ID는 엔티티의 PK 타입
// JpaRepository를 상속받으면 SpringDataJpa가 구현체를 만들어서 Spring Bean에 등록해줌
// 인터페이스만 만들어두면 구현체를 자동으로 만들어줌
interface SpringDataJpaMemberRepository : JpaRepository<Member, Long>, MemberRepository {
    // select m from Member m where m.name = ?
    override fun findByName(name: String?): Member?
}