package billydev.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.io.PrintWriter;

/**
 * @author Billy
 */
@Configuration
@Profile(value="Production")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setDataSource(dataSource);
        if (!manager.userExists("billy")) {
            manager.createUser(User.withUsername("billy")
                    .password(passwordEncoder()
                            .encode("123456")).roles("admin","student").build());
        }
        if (!manager.userExists("studentuser")) {
            manager.createUser(User.withUsername("studentuser").password(passwordEncoder().encode("123456")).roles("student").build());
        }
        return manager;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }





    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**","/class/**","/login.html");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //禁用跨站csrf攻击防御，后面的章节会专门讲解
                .formLogin()
                .loginPage("/login.html")//用户未登录时，访问任何资源都转跳到该路径，即登录页面
//                .loginProcessingUrl("/login")//登录表单form中action的地址，也就是处理认证请求的路径
                .usernameParameter("uname")///登录表单form中用户名输入框input的name名，不修改的话默认是username
                .passwordParameter("pword")//form中密码输入框input的name名，不修改的话默认是password
                .defaultSuccessUrl("/class/1")//登录认证成功后默认转跳的路径
                .loginProcessingUrl("/doLogin")
                .successHandler((req, resp, authentication) -> {
                    Object principal = authentication.getPrincipal();
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(principal));
                    out.flush();
                    out.close();
                })
                .failureHandler((req, resp, e) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write(e.getMessage());
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/login.html", "/login").permitAll()//不需要通过登录验证就可以被访问的资源路径
                .antMatchers("/actuator/**").hasRole("admin") //前面是资源的访问路径、后面是资源的名称或者叫资源ID
                .antMatchers("/student/**").hasRole("student")
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write("注销成功");
                    out.flush();
                    out.close();
                });

    }
}
