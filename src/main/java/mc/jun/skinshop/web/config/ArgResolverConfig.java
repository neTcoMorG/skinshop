package mc.jun.skinshop.web.config;

import lombok.RequiredArgsConstructor;
import mc.jun.skinshop.web.resolver.TokenArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ArgResolverConfig implements WebMvcConfigurer {

    private final TokenArgumentResolver tokenArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(tokenArgumentResolver);
    }
}
