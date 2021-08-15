package ru.geekbrains.corelib.interfaces;

import ru.geekbrains.corelib.models.UserInfo;

public interface ITokenService {

    String generateToken(UserInfo user);

    UserInfo parseToken(String token);
}
