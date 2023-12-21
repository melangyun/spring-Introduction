package hello.hellospring.controller

import hello.hellospring.domain.Member
import hello.hellospring.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

// @Controller: 스프링이 컨테이너에서 관리함 (생성 후 관리 - 스프링 빈)
@Controller
class MemberController @Autowired constructor(
    private val memberService: MemberService
) {

    init {
        println("memberService = ${memberService.javaClass}")
    }

    /*
    HL 의존성 주입 방식 - 필드 주입 , setter 주입, 생성자 주입
    - 필드 주입: @Autowired private lateinit var memberService: MemberService
        : 필드 주입은 주로 간단한 의존성 주입에 적합.
            해당 클래스에 대한 의존성이 바뀔 수 없는 경우에 사용.
    - setter 주입: @Autowired fun setMemberService(memberService: MemberService) { this.memberService = memberService }
    - 생성자 주입: class MemberController @Autowired constructor(private val memberService: MemberService) { }

    일반적으로 생성자 주입이 권장됨
        - 복잡한 의존성 / 테스트 가능성 고려할때
        - 생성자 주입은 명시적으로 의존성을 주입하므로, 코드의 명확성을 높이고, 테스트 용이성 향상

    HL 생성자 주입 사용시 주입 순서가 중요할까?
    순서가 의존성 간 의존 관계를 나타내고, 순서를 지켜야하는 경우 의존성이 있을 수 있음
    순환 참조가 발생하는 경우에는 순서가 중요함
    또한 가독성에 영향

    HL 스프링 5부터 생성자가 하나만 있으면 @Autowired를 생략할 수 있음

    HL Spring에서 의존성 주입 최적화
    - 생성자 주입 선호
        - 생성자 주입을 사용하면 의존성을 명시적으로 표현하고, 불변성을 유지할 수 있다. 따라서 생성자 주입을 선호하는 것이 좋다.
        - 인터페이스 사용
        - 의존성 주입 순서
            - 순환 의존성이 있는 경우 @Lazy를 사용하여 해결
        - 컴포넌트 스캔 최적화
            - 스프링의 컴포넌트 스캔은 클래스 경로를 모두 탐색하기 때문에 많은 시간이 소요됨
            - 불필요한 패키지나 클래스 제외하여 스캔 범위를 좁히는 것이 좋음
     */

    @GetMapping("/members")
    fun list(model: Model): String {
        val members = memberService.findMembers()
        model.addAttribute("members", members)
        return "members/memberList"
    }

    @GetMapping("/members/new")
    fun createForm(): String {
        return "members/createMemberForm"
    }

    @PostMapping("/members/new")
    fun create(form: MemberForm): String {
        val member = Member(form.name)
        memberService.join(member)
        return "redirect:/"
    }

}