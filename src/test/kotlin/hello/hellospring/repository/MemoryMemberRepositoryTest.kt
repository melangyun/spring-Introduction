package hello.hellospring.repository

import hello.hellospring.domain.Member
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MemoryMemberRepositoryTest{
    val repository = MemoryMemberRepository()

    @AfterEach
    fun afterEach(){
        // 테스트는 서로 의존관계 없이 설계되어야 함
        repository.clearStore()
    }

    @Test
    fun save(){
        val member = Member()
        member.name = "spring"

        repository.save(member)

        val result = repository.findById(member.id)
        assertEquals(member, result)
        assertThat(member).isEqualTo(result)
    }

    @Test
    fun findByName(){
        val member1 = Member()
        member1.name = "spring1"
        repository.save(member1)

        val member2 = Member()
        member2.name = "spring2"
        repository.save(member2)

        val result = repository.findByName("spring1")
        assertThat(result).isEqualTo(member1)
    }

    @Test
    fun findAll(){
        val member1 = Member()
        member1.name = "spring1"
        repository.save(member1)

        val member2 = Member()
        member2.name = "spring2"
        repository.save(member2)

        val result = repository.findAll()
        assertThat(result.size).isEqualTo(2)
    }
}