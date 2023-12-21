package hello.hellospring

import hello.hellospring.aop.TimeTraceAop
import hello.hellospring.repository.JdbcTemplateMemberRepository
import hello.hellospring.repository.JpaMemberRepository
import hello.hellospring.repository.MemberRepository
import hello.hellospring.repository.MemoryMemberRepository
import hello.hellospring.service.MemberService
import jakarta.persistence.EntityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource


@Configuration
class SpringConfig(
//    private val dataSource: DataSource
//    private val em: EntityManager
    private val memberRepository: MemberRepository
) {

    @Bean
    fun memberService(): MemberService {
        return MemberService(memberRepository())
    }

    @Bean
    fun memberRepository(): MemberRepository {
//         return MemoryMemberRepository()
//        return JdbcTemplateMemberRepository(dataSource) // JdbcTemplate 사용
//        return JpaMemberRepository(em) // JPA 사용
        return memberRepository
    }

//    @Bean
//    fun timeTraceAop(): TimeTraceAop {
//        return TimeTraceAop()
//    }
}
