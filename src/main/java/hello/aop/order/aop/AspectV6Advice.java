package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Advice {


//    @Around("hello.aop.order.aop.Pointcuts.orderAndService()")
//    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        try {
//            //@Before
//            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
//            Object result = joinPoint.proceed();
//            //@AfterReturning
//            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
//            return result;
//        } catch (Exception e) {
//            //@AfterThrowing
//            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
//            throw e;
//        } finally {
////            @After
//            log.info("[리소스 릴리] {}", joinPoint.getSignature());
//        }
//    }

    // 조인 포인트 실행 이전에 실행
    @Before("hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        //@Around에서만 ProceedingJoinPoint를 쓸 수 있음(ProceedingJoinPoint는 ProceedingJoinPoint.proceed()를 호출해야 다음 대상이 호출됨)
        // 하지만 @Before는 메서드 종료시 자동으로 다음타겟이 호출된다. 예외가 발생하면 호출되지 않음
        log.info("[before] {}", joinPoint.getSignature());
    }

    //조인 포인트가 정상 완료후 실행
    @AfterReturning(value = "hello.aop.order.aop.Pointcuts.orderAndService()", returning = "result") //doReturn()의 두번째 파라미터 이름과 매칭됨
    public void doReturn(JoinPoint joinPoint, Object result) {
        log.info("[return] {} return={}", joinPoint.getSignature(), result);
    }

    //메서드가 예외를 던지는 경우 실행
    @AfterThrowing(value = "hello.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[ex] {} message={}", ex);
    }

    //조인 포인트가 정상 또는 예외에 관계없이 실행(finally)
    @After(value = "hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after] {}", joinPoint.getSignature());
    }
}
