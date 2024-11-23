package u.utd.service;

import u.utd.dto.JwtAuthResponse;
import u.utd.dto.LoginDto;
import u.utd.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);

    JwtAuthResponse login(LoginDto loginDto);
}
