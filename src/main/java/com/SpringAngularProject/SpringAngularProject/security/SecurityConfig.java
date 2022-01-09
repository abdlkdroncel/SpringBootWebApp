package com.SpringAngularProject.SpringAngularProject.security;

import com.SpringAngularProject.SpringAngularProject.model.Role;
import com.SpringAngularProject.SpringAngularProject.security.jwt.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements  WebMvcConfigurer {

    @Value("${authentication.internal-api-key}")
    private String internalApiKey;

    @Autowired
    private CustomUserDetailService userDetailService;


    //kimlik doğrulama türünü ayarlamak için bu metodu kullanırız.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService)
                .passwordEncoder((passwordEncoder()));
    }

    //authentication manager metodu
    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //Yetkilendirme yapılandırma
    //Farklı yolları farklı roller için kısıtlanabilir...
    //sadece tanımlı kişilere erişim
    //farklı erişim gereksinimleri olan farklı urller sağlamak için kesinlikle kullanılır
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //cross origin resource share etkinleşitirilir.
        //farklı domainlerden erişim için
        http.cors();
        //saldırıları onlemek adına yapılır
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        //oturum açma ve üyelik dısındaki tüm isteklere erişmek için auth zorunlu
        http.authorizeRequests()
                //üye girişi ve üye sayfası herkese acık
                .antMatchers("/api/authentication/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/book").permitAll()
                .antMatchers("/api/book/**").hasRole(Role.ADMIN.name())
                .antMatchers("/api/internal/**").hasRole(Role.SYSTEM_MANAGER.name())
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(internalApiAuthenticationFilter(),JwtAuthorizationFilter.class);
    }

    @Bean
    public InternalApiAuthenticationFilter internalApiAuthenticationFilter(){
        return new InternalApiAuthenticationFilter(internalApiKey);
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter(){
        return new JwtAuthorizationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
            }
        };
    }


}
