package opensource.h3nryc0ding.livechat.security

import opensource.h3nryc0ding.livechat.config.AppConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource

@Configuration
class CorsConfig(
    private val appConfig: AppConfig,
) {
    @Bean
    fun corsFilter(): CorsWebFilter {
        val config =
            CorsConfiguration().apply {
                allowCredentials = true
                addAllowedOrigin(appConfig.FRONTEND_URI.toString())
                addAllowedHeader("*")
                addAllowedMethod("*")
            }
        val source =
            UrlBasedCorsConfigurationSource().apply {
                registerCorsConfiguration("/**", config)
            }
        return CorsWebFilter(source)
    }
}
