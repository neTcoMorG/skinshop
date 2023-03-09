package mc.jun.skinshop.domain.service.oauth;

public interface AuthService<T, P> {

    public T getAuth(String code);
    public P getProfile(String accessToken);
}
