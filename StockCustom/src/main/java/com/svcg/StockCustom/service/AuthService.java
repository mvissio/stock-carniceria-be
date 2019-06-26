package com.svcg.StockCustom.service;

import java.io.IOException;

public interface AuthService {

    String resetPasswordByEmail(String email) throws IOException;
}
