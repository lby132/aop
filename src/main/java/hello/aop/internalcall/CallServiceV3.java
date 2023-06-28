package hello.aop.internalcall;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

/**
 * 참고: 생성자 주입은 순환 사이클을 만들기 때문에 실패한다.
 */
@Slf4j
@Component
public class CallServiceV3 {

    private final InternalService internalService;

    public CallServiceV3(InternalService internalService) {
        this.internalService = internalService;
    }

    public void external() {
        log.info("call external");
        internalService.internal();
    }

}
