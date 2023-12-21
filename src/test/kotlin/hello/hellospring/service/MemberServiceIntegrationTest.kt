package hello.hellospring.service

import hello.hellospring.domain.Member
import hello.hellospring.repository.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional // 테스트가 끝나면 DB를 Rollback 해줌
class MemberServiceIntegrationTest {
// 현재 JDBC를 사용한다고 하고, 설정을 제대로 해놓지 않아 돌아가지 않음
    @Autowired
    lateinit var memberService: MemberService
    @Autowired
    lateinit var memberRepository: MemberRepository

    @Test
    fun 회원가입() {
        // given
        val member = Member(name = "spring3")

        // when
        val savedId = memberService.join(member)

        // then
        val findMember = memberService.findOne(savedId)
        assertThat(member.name).isEqualTo(findMember?.name)
    }

    @Test
    fun 중복_회원_예외() {
        // given
        val member1 = Member(name = "spring4")
        val member2 = Member(name = "spring4")

        // when
        memberService.join(member1)
        val e = Assertions.assertThrows(IllegalStateException::class.java) {
            memberService.join(member2)
        }

        // then
        assertThat(e.message).isEqualTo("이미 존재하는 회원입니다.")
    }

}