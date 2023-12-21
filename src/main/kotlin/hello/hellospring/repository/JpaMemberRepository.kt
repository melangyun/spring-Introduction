package hello.hellospring.repository

import hello.hellospring.domain.Member
import jakarta.persistence.EntityManager

class JpaMemberRepository(private val entityManager: EntityManager) : MemberRepository {
    override fun save(member: Member): Member {
        entityManager.persist(member)
        return member
    }

    override fun findById(id: Long?): Member? {
        return entityManager.find(Member::class.java, id)
    }

    override fun findByName(name: String?): Member? {
        return entityManager
            .createQuery("select m from Member m where m.name = :name", Member::class.java)
            .setParameter("name", name)
            .resultList
            .firstOrNull()
    }

    override fun findAll(): List<Member> {
        // JPQL -> 객체 지향 쿼리 언어
        // 객체를 대상으로 쿼리를 날림
        return entityManager
            .createQuery("select m from Member m", Member::class.java) // select 대상이 'm'
            .resultList
    }

}