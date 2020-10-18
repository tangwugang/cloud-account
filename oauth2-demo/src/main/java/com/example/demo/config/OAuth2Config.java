package com.example.demo.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author twg
 * @since 2020/10/18
 */
@Slf4j
@AllArgsConstructor
@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    private PasswordEncoder passwordEncoder;

    private UserDetailsService userDetailsService;

    /*username、password */
    private AuthenticationManager authenticationManager;

    private TokenStore redisTokenStore;

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        /**
         * redis token 方式
         */
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(redisTokenStore);

    }

    /**
     * secret 保存的是加密密码，还要保存原密码。client_secret=原密码
     */

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("order-client")
//                .secret(passwordEncoder.encode("order-secret-8888"))
                .secret("$2a$10$haNuhSSH/.2lZTmsJdgJWOfyRMqimnKV/zitKSI2a04GSIncCDSAC")
                .authorizedGrantTypes("refresh_token", "authorization_code", "password","client_credentials")
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(7200)
                .scopes("all")
                .autoApprove(true)
                .and()
                .withClient("user-client")
                .secret(passwordEncoder.encode("user-secret-8888"))
                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                .accessTokenValiditySeconds(3600)
                .scopes("all");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
        security.checkTokenAccess("isAuthenticated()");
        security.tokenKeyAccess("isAuthenticated()");
    }
}
