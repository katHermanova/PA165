package cz.muni.fi.pa165.currency;

/*import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;*/
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages="cz.muni.fi.pa165.currency")
/*@EnableAspectJAutoProxy
@Aspect*/
public class AppConfig {

    /*@Pointcut(
            "execution(public String cz.muni.fi.pa165.currency.CurrencyConvertor.convert(..))"
    )
    public void monitor() { }*/

    /*@Pointcut(
            "execution(public String cz.muni.fi.pa165.currency.ExchangeRateTable.getExchangeRate(..))"
    )
    public void monitor2() { }*/

    /*@Bean
    public PerformanceMonitorInterceptor performanceMonitorInterceptor() {
        return new PerformanceMonitorInterceptor(true);
    }

    @Bean
    public Advisor performanceMonitorAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("cz.muni.fi.pa165.currency.AppConfig.monitor()");
        return new DefaultPointcutAdvisor(pointcut, performanceMonitorInterceptor());
    }*/

}