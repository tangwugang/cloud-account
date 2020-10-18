在使用@EnableAuthorizationServer的同时也要使用@Configuration，不然认证无效

实现ClientDetailsService 接口，保存的secret 保存的是加密密码，还要保存原密码。client_secret=原密码（order-secret-8888）



### client mode
> http://127.0.0.1:18080/oauth/token?client_id=order-client&client_secret=order-secret-8888&grant_type=client_credentials

### password mode
> http://127.0.0.1:18080/oauth/token?client_id=order-client&client_secret=order-secret-8888&grant_type=password&username=admin&password=123456