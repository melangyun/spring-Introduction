package hello.hellospring.service

import hello.hellospring.domain.Member
import hello.hellospring.repository.MemberRepository
import org.springframework.transaction.annotation.Transactional

@Transactional
class MemberService(private val memberRepository: MemberRepository) {

    fun join(member: Member): Long {
        // 같은 이름이 있는 중복 회원X
        validateDuplicateMember(member)
        memberRepository.save(member)
        return member.id!!
    }

    private fun validateDuplicateMember(member: Member) {
        memberRepository.findByName(member.name)?.let {
            throw IllegalStateException("이미 존재하는 회원입니다.")
        }
    }

    /*
    전체 회원 조회
    */
    fun findMembers(): List<Member> {
        // 서비스 클래스는 비즈니스에 가까운 용어를 사용하는 것이 좋음
        // repository는 기계적으로 선택하는 것이 좋음
        return memberRepository.findAll()
    }

    fun findOne(memberId: Long): Member? {
        return memberRepository.findById(memberId)
    }
}