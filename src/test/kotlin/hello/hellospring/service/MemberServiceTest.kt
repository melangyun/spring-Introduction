package hello.hellospring.service

import hello.hellospring.domain.Member
import hello.hellospring.repository.MemoryMemberRepository

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MemberServiceTest {

    private lateinit var memberRepository: MemoryMemberRepository
    private lateinit var memberService: MemberService

    @BeforeEach
    fun beforeEach() {
        memberRepository = MemoryMemberRepository()
        // 이런걸 DI, Dependency Injection, 의존성 주입이라고 함
        memberService = MemberService(memberRepository!!)
    }

    @AfterEach
    fun afterEach() {
        memberRepository!!.clearStore()
    }

    @Test
    fun 회원가입() {
        // given
        val member = Member(name = "spring")

        // when
        val savedId = memberService.join(member)

        // then
        assertEquals(member, memberService.findOne(savedId))
    }

    @Test
    fun 중복_회원_예외() {
        // given
        val member1 = Member(name = "spring")
        val member2 = Member(name = "spring")

        // when
        memberService.join(member1)
        val e = assertThrows(IllegalStateException::class.java) {
            memberService.join(member2)
        }

        // then
        Assertions.assertThat(e.message).isEqualTo("이미 존재하는 회원입니다.")
    }
}