package hello.hellospring.repository

import hello.hellospring.domain.Member
import org.springframework.stereotype.Repository

//@Repository
class MemoryMemberRepository : MemberRepository {

    private val store = HashMap<Long, Member>()
    // 실무에서는 동시성 문제 때문에 ConcurrentHashMap을 사용해야 함
    private var sequence = 0L // AtomicLong 사용 권장

    override fun save(member: Member): Member {
        member.id = ++sequence
        store[member.id!!] = member
        return member
    }

    override fun findById(id: Long?): Member? {
        return store[id]
    }

    override fun findByName(name: String?): Member? {
        return store.values.find { it.name == name }
    }

    override fun findAll(): List<Member> {
        return store.values.toList()
    }

    fun clearStore() {
        store.clear()
    }
}