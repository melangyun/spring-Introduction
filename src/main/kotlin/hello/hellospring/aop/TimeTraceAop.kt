package hello.hellospring.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component


// 사실 그냥 Configuration으로 등록을 많이함
@Component // Spring Bean으로 등록
@Aspect
class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") // hello.hellospring 패키지 하위에 있는 모든 메서드를 다 적용
    fun execute(joinPoint: ProceedingJoinPoint): Any? {
        val start = System.currentTimeMillis()
        println("START: ${joinPoint.toShortString()}")

        return try {
            joinPoint.proceed() // 다음 메서드로 진행
        } finally {
            val finish = System.currentTimeMillis()
            val timeMs = finish - start
            println("END: ${joinPoint.toShortString()} $timeMs ms")
        }

    }
}